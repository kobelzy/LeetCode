package hashMap.sort;


public class KVArraySortDataFormat<K, T> extends SortDataFormat<K, T[]> {

    @Override
    protected K getKey(T[] data, int pos) {
        return (K) data[2 * pos];
    }

    @Override
    public void swap(T[] data, int pos0, int pos1) {
        T tmpKey = data[2 * pos0];
        T tmpVal = data[2 * pos0 + 1];
        data[2 * pos0] = data[2 * pos1];
        data[2 * pos0 + 1] = data[2 * pos1 + 1];
        data[2 * pos1] = tmpKey;
        data[2 * pos1 + 1] = tmpVal;
    }

    @Override
    public void copyElement(T[] src, int srcPos, T[] dst, int dstPos) {
        dst[2 * dstPos] = src[2 * srcPos];
        dst[2 * dstPos + 1] = src[2 * srcPos + 1];
    }

    @Override
    public void copyRange(T[] src, int srcPos, T[] dst, int dstPos, int length) {
        System.arraycopy(src, 2 * srcPos, dst, 2 * dstPos, 2 * length);
    }

    @Override
    public T[] allocate(int length) {
        Object[] ts = new Object[2 * length];

        return (T[]) ts;
    }
}
