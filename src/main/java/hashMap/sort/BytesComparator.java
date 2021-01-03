package hashMap.sort;

import java.util.Comparator;

public class BytesComparator implements Comparator<byte[]> {
//    private int compare(final int begin, final byte[] b) {
//        int i = 0;
//        for (; entrys[begin + i] == b[i] && i < b.length - 1; i++) ;
//        return entrys[begin + i] - b[i];
//    }


    @Override
    public int compare(byte[] o1, byte[] o2) {
        int len1 = o1.length;
        int len2 = o2.length;
        int lim = Math.min(len1, len2);

        int k = 0;
        while (k < lim) {
            byte c1 = o1[k];
            byte c2 = o2[k];
            if (c1 != c2) {
                return c1 - c2;
            }
            k++;
        }
        return len1 - len2;
    }

    /**
     * 查询时候使用
     * @param data
     * @param startPos
     * @param endPos
     * @param other
     * @return
     */
    public int compare(byte[] data, int startPos, int endPos, byte[] other) {
        int len1 = endPos - startPos;
        int len2 = other.length;
        int lim = Math.min(len1, len2);

        int k = 0;
        while (k < lim) {
            byte c1 = data[startPos + k];
            byte c2 = other[k];
            if (c1 != c2) {
                return c1 - c2;
            }
            k++;
        }
        return len1 - len2;
    }
}
