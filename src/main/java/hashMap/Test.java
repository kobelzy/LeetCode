package hashMap;

import com.carrotsearch.sizeof.RamUsageEstimator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Test {
    private static final String CHARS = "1234567890abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ-";
    static String basePath = "D:\\WorkSpace\\Scala\\LeetCode\\src\\main\\resources\\data\\";
    static String filePath = basePath + "sid_big.csv";
//    static String filePath = basePath + "sid_sample.csv";

    public static void main(String[] args) {
        List<Entry> list = readFile();
        /**
         * 1 HashMap 存储
         * 200w 加载时间：0.802s，size：441.4 MB,对数时间：3.257s
         */
        System.out.println("------------------hashMap-----------------------------");
//        HashMapTest(list);
//        HashMapByStringTest(list);

        /**
         *  1 Object[] 存储
         * */
        System.out.println("------------------objectMap-----------------------------");
//        ObjMapTest(list);
        /**
         * 3 Byte[] 存储 嵌套
         * 加载时间：2.016s，size：83.7 MB ,对数时间：2.31s
         * */
        System.out.println("-------------------byte Map--------------------------");
        ByteMapTest(list);

    }


    public static void ByteMapTest(List<Entry> list) {
        NestSortMap bulk= new NestSortMap();
        long start = System.currentTimeMillis();
        for (Entry entry : list) {
            bulk.put(entry.getUuid(), entry.getSid());
        }
        for (Map.Entry<Integer, KVSortByteArray> entry : bulk.entrySet()) {
            entry.getValue().sort();
        }

//        System.out.println(bulk.get("c49bpmrmQrKMC60HmelhgN2obRrAO7MfkppRHqEcl1fiA1oFBe7d9FdhNL".length()));
        System.out.println(("加载时间：" + (System.currentTimeMillis() - start) / 1000.0 + "s") + "，size：" + RamUsageEstimator.humanSizeOf(bulk));
        start = System.currentTimeMillis();
        int count = 0;
        int error = 0;
        boolean check = true;
        if (check) {
            for (Entry entry : list) {
                String searchValue = bulk.getStr(entry.getUuid());
//                if (!entry.getSid().equals(searchValue)) error++;
                if (!searchValue.equals(entry.strSid)) error++;
                count++;
            }
            System.out.println(("对数时间：" + (System.currentTimeMillis() - start) / 1000.0 + "s"));

            System.out.println("count:" + count + ".error:" + error);

        }

    }

    public static void ObjMapTest(List<Entry> list) {
        Map<Integer, KVSortObjectArray> bulk = new HashMap<>();
        long start = System.currentTimeMillis();
        for (Entry entry : list) {
            int keyLen = entry.getUuid().length();

            if (!bulk.containsKey(keyLen)) bulk.put(keyLen, new KVSortObjectArray(16));
            KVSortObjectArray bSortMap = bulk.get(keyLen);
            bSortMap.add(entry.getUuid(), entry.getSid());
        }
        for (Map.Entry<Integer, KVSortObjectArray> entry : bulk.entrySet()) {
            entry.getValue().sort();
        }
//        System.out.println(bulk.get("c49bpmrmQrKMC60HmelhgN2obRrAO7MfkppRHqEcl1fiA1oFBe7d9FdhNL".length()));
        System.out.println(("加载时间：" + (System.currentTimeMillis() - start) / 1000.0 + "s") + "，size：" + RamUsageEstimator.humanSizeOf(bulk));
        start = System.currentTimeMillis();
        int count = 0;
        int error = 0;
        for (Entry entry : list) {
            String uuid = entry.getUuid();
            if (bulk.get(uuid.length()).get(uuid) == null) {
                System.out.println(entry);
                System.out.println(bulk.get(uuid.length()));
                System.out.println(bulk.get(uuid.length()).get(uuid));
            }
            long searchValue = (Long) bulk.get(uuid.length()).get(uuid);

            if (!entry.getSid().equals(searchValue)) {
                error++;
                System.out.println("key=" + uuid + ";" + entry.getSid() + "=" + searchValue + ":" + false);
            }
            count++;
        }
        System.out.println("对数时间：" + (System.currentTimeMillis() - start) / 1000.0 + "s");
        System.out.println("count:" + count + ".error:" + error);

    }

    /**
     * 使用string类型的value，200w数据，占用cong340MB ->440MB
     *
     * @param list
     */
    public static void HashMapTest(List<Entry> list) {


        Map<String, Long> map = new HashMap<>();
        long start = System.currentTimeMillis();
        for (Entry entry : list) {
            map.put(entry.uuid, entry.sid);
        }
        System.out.println(("加载时间：" + (System.currentTimeMillis() - start) / 1000.0 + "s") + "，size：" + RamUsageEstimator.humanSizeOf(map));
        start = System.currentTimeMillis();
        for (Entry entry : list) {
            if (entry.sid != map.get(entry.uuid)) {
                System.out.println("key=" + entry.uuid + ";" + map.get(entry.uuid) + "=" + entry.sid + ":" + false);
            }
        }
        System.out.println(("对数时间：" + (System.currentTimeMillis() - start) / 1000.0 + "s"));
    }

    public static void HashMapByStringTest(List<Entry> list) {
        Map<String, String> map = new HashMap<>();
        long start = System.currentTimeMillis();
        for (Entry entry : list) {
            map.put(entry.uuid, entry.strSid);
        }
        System.out.println(("加载时间：" + (System.currentTimeMillis() - start) / 1000.0 + "s") + "，size：" + RamUsageEstimator.humanSizeOf(map));
        start = System.currentTimeMillis();
        for (Entry entry : list) {
            if (!entry.strSid.equals(map.get(entry.uuid))) {
                System.out.println("key=" + entry.uuid + ";" + map.get(entry.uuid) + "=" + entry.sid + ":" + false);
            }
        }
        System.out.println(("对数时间：" + (System.currentTimeMillis() - start) / 1000.0 + "s"));
    }

    public static List<Entry> readFile() {

        File file = new File(filePath);
        List<Entry> list = new LinkedList<>();
        BufferedReader reader;
        String line;
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] split = line.split("\\t");
                String key = split[0];
                String sid = split[1];
                long sidLong = Long.parseLong(sid);

                list.add(new Entry(key, sidLong, sid));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("List大小：" + RamUsageEstimator.humanSizeOf(list));
        return list;
    }

    static class Entry {
        private String uuid;
        private Long sid;
        private String strSid;

        public String getUuid() {
            return uuid;
        }

        public Long getSid() {
            return sid;
        }

        public String getStrSid() {
            return strSid;
        }

        public Entry(String uuid, Long sid, String strSid) {
            this.uuid = uuid;
            this.sid = sid;
            this.strSid = strSid;
        }

    }
}
