package hashMap;

import hashMap.sort.KVArraySortDataFormat;
import hashMap.sort.TimSort;
import org.apache.commons.lang3.compare.ObjectToStringComparator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * key 和 value 分别维护，使用数组
 */
public class KVSortObjectArray {


    private Object[] data; //entry数组
    private int count = 0;
    private int capacity;
    private static final float DEFAULT_GROW_FACTOR=1.2f;
    private static final int MAXIMUM_CAPACITY = (Integer.MAX_VALUE >> 1) - 500;
    private static final ObjectToStringComparator compare = ObjectToStringComparator.INSTANCE;

//    final private int keyLength; //key的字节大小
//    final private int entryLength; //每一个Entry的字节数

    public KVSortObjectArray() {
        new KVSortObjectArray(16);
    }

    public KVSortObjectArray(int capacity) {
        data = new Object[capacity * 2];
        this.capacity = capacity;
//        System.out.println("capacity:"+this.capacity);
    }

    public int size() {
        return count;
    }

    private void growArray() {
        int newCapacity = Math.min((int)(capacity * DEFAULT_GROW_FACTOR), MAXIMUM_CAPACITY);
        Object[] newData = new Object[newCapacity * 2];
        System.arraycopy(data, 0, newData, 0, capacity * 2);
        data = newData;
        capacity = newCapacity;
    }

    /**
     * 添加记录条目。条目都是已经排好序的。
     * src格式为<key,value>
     * todo 无法处理重复数据
     */
    final public void add(String key, Object value) {
//        System.out.println(count+"=="+capacity);
        if (count == capacity) growArray();
        data[2 * count] = key;
        data[2 * count + 1] = value;
//        System.arraycopy(src, 0, entrys, count * entryLength, entryLength);
        count++;
    }


    /**
     * 获取与key关联的value值
     *
     * @param key
     * @return 如果不存在key关联的value则返回-1
     */
    public Object get(String key) {
        int index = binarySearch(key);
        return index == -1 ? null : data[index + 1];
    }


    /**
     * 获取与key关联的value值
     *
     * @param key
     * @return 如果不存在key关联的value则返回-1
     */
    public int binarySearch(final String key) {
        int i = 0;
        int j = count - 1;
        int mid;
        while (i <= j) {
            mid = (i + j) >> 1;
            int ret=compare.compare(String.valueOf(data[mid * 2]),key);
//            final int ret = String.valueOf(data[mid * 2]).compareTo(key);
            if (ret == 0) {
                return mid * 2; //返回结果
            } else if (ret < 0) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }

        }
        return -1;

    }
    public void sort() {
        TimSort timSort = new TimSort(new KVArraySortDataFormat<String, String>());
        timSort.sort(data, 0, count, compare);
    }
    @Override
    public String toString() {
        return "BSortMap{" +
                " count=" + count +
                ", capacity=" + capacity +
                ", data=" + Arrays.toString(data) +
                '}';
    }



    public static void main(String args[]) throws IOException {
        Map<Integer, KVSortObjectArray> bulk = new HashMap<>();

        long start = System.currentTimeMillis();
        File file = new File("D:\\WorkSpace\\Scala\\LeetCode\\src\\main\\resources\\data\\sid_sample.csv");

        BufferedReader reader;
        reader = new BufferedReader(new FileReader(file));
        String line;

        /*
         * f为根据key值已经排好序的文件
         * 文件格式为：
         * entrysCount:  一个整形数字，值为总的记录条目
         * keyLength: 一个整形数字，值为关键字的字节长度
         * <key,value>列表
         */
        while ((line = reader.readLine()) != null) {
            String[] split = line.split("\\t");
            String key = split[0];
            long value = Long.parseLong(split[1]);
            int keyLen = key.length();
//            BSortMap3 bSortMap = bulk.computeIfAbsent(keyLen, k -> new BSortMap3(16));
            if (!bulk.containsKey(keyLen)) bulk.put(keyLen, new KVSortObjectArray(16));
            KVSortObjectArray bSortMap = bulk.get(keyLen);
            bSortMap.add(key, value);
        }


        /**排序*/
        for (Map.Entry<Integer, KVSortObjectArray> entry : bulk.entrySet()) {
            entry.getValue().sort();
        }
        reader.close();
        System.out.println((System.currentTimeMillis() - start) / 1000.0 + "s");

//        BSortMap3 bSortMap3 = bulk.get(19);
//        System.out.println(bSortMap3);
//        for (int i = 0; i < bSortMap3.data.length/2; i++) {
//            System.out.println(i+":"+bSortMap3.data[i * 2]);
//        }

        String testKey = "c49bpmrmQrKMC60HmelhgN2obRrAO7MfkppRHqEcl1fiA1oFBe7d9FdhNL";
        Object res = bulk.get(testKey.length()).get(testKey);
        System.out.println(res);
        boolean check = true;
        if (check) {
            reader = new BufferedReader(new FileReader(file));

            int count = 0;
            int error = 0;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split("\\t");
                String key = split[0];
                Long value = Long.parseLong(split[1]);
                long searchValue = (Long) bulk.get(key.length()).get(key);
                if (!value.equals(searchValue)) {
                    error++;
                    System.out.println("key="+key+";"+value + "=" + searchValue + ":" + false);
                }
                count++;

            }
            System.out.println((System.currentTimeMillis() - start) / 1000.0 + "s");
            System.out.println("count:" + count + ".error:" + error);

            reader.close();
        }


    }
}