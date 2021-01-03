package hashMap;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class BSortMapCommon {

    private static Map<Integer, BSortMapCommon> bulk = new HashMap<Integer, BSortMapCommon>();

    final private byte[] entrys; //entry数组
    private int count = 0;
    final private int keyLength; //key的字节大小
    final private int entryLength; //每一个Entry的字节数

    public BSortMapCommon(int capacity, int keyLen) {
        this.keyLength = keyLen;
        entryLength = keyLen + 1;
        entrys = new byte[capacity * entryLength];
    }

    public int size() {
        return count;
    }

    /**
     * 添加记录条目。条目都是已经排好序的。
     * src格式为<key,value>
     *
     * @param src
     */
    final public void add(byte src[]) {
        System.arraycopy(src, 0, entrys, count * entryLength, entryLength);
        count++;
    }

    private int compare(final int begin, final byte[] b) {
        int i = 0;
        for (; entrys[begin + i] == b[i] && i < b.length - 1; i++) ;
        return entrys[begin + i] - b[i];
    }

    /**
     * 获取与key关联的value值
     *
     * @param key
     * @return 如果不存在key关联的value则返回-1
     */
     public byte get(final byte[] key) {
        int i = 0;
        int j = count - 1;
        int mid;
        while (i <= j) {
            mid = (i + j) >> 1;
            final int ret = compare(mid * entryLength, key);
            if (ret == 0) {
                return entrys[mid * entryLength + keyLength]; //返回结果
            } else if (ret < 0) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }

        }
        return -1;

    }

//    public byte get(String key){
//         return         Arrays.binarySearch(entrys,key);
//    }


    public static void main(String args[]) throws IOException {
        File dir = new File("D:/workspace/partion_keyword/sort");
        File[] files = dir.listFiles();

        for (File f : files) {

            DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(f)));
            /*
             * f为根据key值已经排好序的文件
             * 文件格式为：
             * entrysCount:  一个整形数字，值为总的记录条目
             * keyLength: 一个整形数字，值为关键字的字节长度
             * <key,value>列表
             */
            final int entrysCount = in.readInt();
            final int keyLength = in.readInt();
            byte[] buffer = new byte[keyLength + 1];
            BSortMapCommon bst = new BSortMapCommon(entrysCount, keyLength);
            int i = 0;
            while (in.available() > 0) {
                int l = in.read(buffer);
                while (l != keyLength + 1) {
                    System.err.println("not equal." + l);
                }
                bst.add(buffer);
                i++;
            }
            bulk.put(keyLength, bst);
            if (entrysCount != i)
                System.err.println(f.getName() + ":" + entrysCount + "," + i);
            in.close();
        }

        BufferedReader read = new BufferedReader(new FileReader("D:/eclipse/workspace/conf/wiki_kws.data"));
        String line;
        int count = 0;
        long start = System.currentTimeMillis();
        while ((line = read.readLine()) != null) {
            byte key[] = line.trim().getBytes();
            BSortMapCommon bt = bulk.get(key.length);
            if (bt != null && bt.get(key) != -1) {
                count++;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println("count:" + count);

        int totalcount = 0;
        for (BSortMapCommon s : bulk.values()) {
            totalcount += s.size();
        }
        System.out.println("total count:" + totalcount);
        read.close();

        /*while(true){
            Scanner in = new Scanner(System.in);
            String key = in.next().trim();
            if(key.equalsIgnoreCase("exit"))
                break;
            int len = key.trim().getBytes().length;
            BSort bt = bulk.get(len);
            byte v = bt.get(key.getBytes());
            System.err.println(key+"="+v);
        }*/

    }
}