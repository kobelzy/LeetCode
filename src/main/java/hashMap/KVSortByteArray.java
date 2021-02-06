package hashMap;

import hashMap.sort.ByteUtils;
import hashMap.sort.BytesComparator;
import hashMap.sort.KVByteSortDataFormat;
import hashMap.sort.TimSort;
import org.apache.commons.lang3.StringUtils;

import java.util.*;


public class KVSortByteArray {

    private byte[] data; //存放数据，k1,v1,k2,v2...依次存放
    private int capacity; //数组长度
    private int count = 0; //数据长度
    final private int keyLength; //key的字节长度
    final private int valueLength; //value的字节长度
    final private int entryLength; //key+value字节长度

    private final int MAXIMUM_CAPACITY; //数组最大长度
    private static final float DEFAULT_GROW_FACTOR = 1.2f; //数组扩容系数
    private static final BytesComparator COMPARATOR = new BytesComparator(); //byte排序



    public KVSortByteArray(int capacity, int keyLen) {
        this.keyLength = keyLen;
        this.valueLength = 8; //Long类型
        this.entryLength = keyLen + valueLength;
        this.capacity = capacity;
        data = new byte[capacity * entryLength];
        MAXIMUM_CAPACITY = Integer.MAX_VALUE / entryLength;
    }

    public int size() {
        return count;
    }


    /**
     * 添加数据，只支持 [String,long]
     */
    final public void add(String key, long value) {
        if (count == capacity) growArray(); //长度不足，则进行数组扩充

        byte[] keyBytes = key.getBytes();
        System.arraycopy(keyBytes, 0, data, count * entryLength, keyLength);

        byte[] valueBytes = ByteUtils.LongToBytes(value);
        System.arraycopy(valueBytes, 0, data, count * entryLength + keyLength, valueLength);

        count++;
    }

    /**
     * 扩充数组
     */
    private void growArray() {
        //新长度不能超过2GB
        int newCapacity = Math.min((int) (capacity * DEFAULT_GROW_FACTOR), MAXIMUM_CAPACITY);
        byte[] newData = new byte[newCapacity * entryLength];
        System.arraycopy(data, 0, newData, 0, capacity * entryLength); //旧数据复制
        //新变量赋值
        data = newData;
        capacity = newCapacity;
    }


    public long getLong(final String key) {
        int valuePos=get(key.getBytes());
        return valuePos== -1 ? -1L :ByteUtils.BytesToLong(data,valuePos);
    }

//    public String getStr(final String key){
//        int valuePos=get(key.getBytes());
//        return valuePos== -1 ? null :new String(data,valuePos,valueLength);
//    }

    /**
     * 获取与key关联的value值
     *
     * @param key
     * @return 如果不存在key关联的value则返回-1
     */
    public int get(final byte[] key) {
        int i = 0;
        int j = count - 1;
        int mid;
        while (i <= j) {
            mid = (i + j) >> 1;
            int midPos = mid * entryLength;
            final int ret = COMPARATOR.compare(data, midPos, midPos + keyLength, key);
            if (ret == 0) {
                int valuePos = midPos + keyLength;
                return valuePos;
//                return Arrays.copyOfRange(data, valuePos, valuePos + valueLength);
//                return ByteUtils.BytesToLong(data,valuePos);
            } else if (ret < 0) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }

        }
        return -1;
    }

    /**
     * 数组排序
     * todo 每次排序都需要创建TimSort对象，待优化
     */
    public void sort() {
        int len = count;
        if (len > 10) len = count / 2; //使用1/2长度即可进行排序

        byte[] buffer = new byte[len * entryLength];

        KVByteSortDataFormat sdf = new KVByteSortDataFormat(buffer, keyLength, valueLength);
        TimSort<byte[], byte[]> timSort = new TimSort<>(sdf);
        timSort.sort(data, 0, count, COMPARATOR);
    }

    public Set<String> getUuids() {
        KVByteSortDataFormat sdf = new KVByteSortDataFormat(new byte[0], keyLength, valueLength);
        Set<String> list = new HashSet<>();
        for (int i = 0; i < count; i++) {
            list.add(new String(sdf.getKey(data, i)));
        }
        return list;
    }
}