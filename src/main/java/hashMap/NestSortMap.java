package hashMap;

import java.util.HashMap;
import java.util.Map;

public class NestSortMap extends HashMap<Integer, KVSortByteArray> {
    private static final String CHARS = "1234567890abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ-";

    public void put(String key, String value) {
        put(key, Long.parseLong(value));
    }
    public void put(String key, long value) {
        int keyIndex = getKeyIndex(key);
        if (!this.containsKey(keyIndex)) this.put(keyIndex, new KVSortByteArray(16, key.length()));
        KVSortByteArray sortByteArray = this.get(keyIndex);
        sortByteArray.add(key, value);
    }

    /**
     * 返回-1时候是没有结果的情况
     *
     * @param key
     * @return
     */
    public long get(String key) {
        int keyIndex = getKeyIndex(key);
        if (this.containsKey(keyIndex)) {
            return this.get(keyIndex).getLong(key);
        }
        return -1L;
    }

    public String getStr(String key) {
        int keyIndex = getKeyIndex(key);
        if (this.containsKey(keyIndex)) {
            long searchValue = this.get(keyIndex).getLong(key);
            return searchValue == -1 ? null : String.valueOf(searchValue);
        }
        return null;
    }


    private int getKeyIndex(String key) {
        return key.length() * 100 + CHARS.indexOf(key.charAt(0));
    }
}
