package hashMap.sort;


import java.util.Arrays;

/**
 * 第一个byte[]是key的部分
 * 第二个byte[]是整个数据结构
 */
public class KVByteSortDataFormat extends SortDataFormat<byte[], byte[]> {

    private final byte[] buffer;
    private final int K_WIDTH;
    private final int V_WIDTH;
    private final int ENTRY_WIDTH;

    public KVByteSortDataFormat(byte[] buffer, int kLength, int vLength) {
        this.buffer = buffer;
        this.K_WIDTH = kLength;
        this.V_WIDTH = vLength;
        this.ENTRY_WIDTH = K_WIDTH + V_WIDTH;
    }

    @Override
    public byte[] newKey() {
        return new byte[K_WIDTH];
    }

    @Override
    public byte[] getKey(byte[] data, int pos, byte[] reuse) {
        System.arraycopy(data, pos * ENTRY_WIDTH, reuse, 0, K_WIDTH);
        return reuse;
    }

    @Override
    public byte[] getKey(byte[] data, int pos) {
        return Arrays.copyOfRange(data, pos * ENTRY_WIDTH, pos * ENTRY_WIDTH + K_WIDTH);
    }

    public long getValue(byte[] data, int pos) {
//        byte[] bytes = Arrays.copyOfRange(data, pos * ENTRY_WIDTH + K_WIDTH, pos * ENTRY_WIDTH + ENTRY_WIDTH);
        return ByteUtils.BytesToLong(data,pos * ENTRY_WIDTH + K_WIDTH);
    }

    @Override
    public void swap(byte[] data, int pos0, int pos1) {
        int srcBasePos = pos0 * ENTRY_WIDTH;
        int dtsBasePos = pos1 * ENTRY_WIDTH;
        for (int i = 0; i < ENTRY_WIDTH; i++) {
            byte tmp = data[srcBasePos + i];
            data[srcBasePos + i] = data[dtsBasePos + i];
            data[dtsBasePos + i] = tmp;
        }
    }

    @Override
    public void copyElement(byte[] src, int srcPos, byte[] dst, int dstPos) {
        copyRange(src, srcPos, dst, dstPos, 1);
    }

    @Override
    public void copyRange(byte[] src, int srcPos, byte[] dst, int dstPos, int length) {
        System.arraycopy(src, srcPos * ENTRY_WIDTH, dst, dstPos * ENTRY_WIDTH, length * ENTRY_WIDTH);
    }

    @Override
    public byte[] allocate(int length) {
        assert (length <= buffer.length) :
                "the buffer is smaller than required: " + buffer.length + " < " + length;
        return buffer;
    }

    public static void main(String[] args) {
        byte[] t = new byte[100];
        byte[] buffer = new byte[100];
        String s = "22kagFPEhSDpGpfm";
        KVByteSortDataFormat sdf = new KVByteSortDataFormat(buffer, 16,8);
        for (int i = 0; i < s.length(); i++) {
            t[i] = s.getBytes()[i];
        }
        sdf.copyElement(t, 0, buffer, 0);
        System.out.println("not fxsit:" + new String(Arrays.copyOfRange(buffer, 0, 16)));
        System.out.println("not exsit:" + s);

        /**交换*/
        sdf.swap(t, 0, 1);
        System.out.println("swap0:" + new String(Arrays.copyOfRange(t, 0, 16)));
        System.out.println("swap1:" + new String(Arrays.copyOfRange(t, 24, 40)));

    }
}
