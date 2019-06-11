package arithmetic;



import java.util.ArrayList;
import java.util.Arrays;

/**
 * 最大堆
 * Auther: Lzy
 * Description:
 * Date Created by： 10:26 on 2019/2/13
 * Modified By：
 */
public class MaxHeap<E extends Comparable<E>> {


    private ArrayList<E> data;

    public MaxHeap(int capacity) {
        data = new ArrayList<>(capacity);
    }

    public MaxHeap() {
        data = new ArrayList<>();
    }

    public int getSize() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 doesn't have parent");
        }
        return (index - 1) / 2;
    }


    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    private void swap(int i, int j) {
        E t = data.get(i);
        data.set(i, data.get(j));
        data.set(j, t);
    }

    /**
     * 功能实现:添加元素
     * Author: Lzy
     * Date: 2019/2/13 10:52
     * Param:
     * Return:
     */
    public void add(E e) {
        data.add(e);
        siftUp(data.size() - 1);
    }

    private void siftUp(int k) {
        //k不能是根节点，并且k的值要比父节点的值更大
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            swap(k, parent(k));
            k = parent(k);
        }
    }


    /**
     * 功能实现:删除堆顶元素
     * Author: Lzy
     * Date: 2019/2/13 11:20
     * Param:
     * Return:
     */
    public E extractMax() {
        E ret = findMax();
        //将堆顶元素和堆尾元素进行互换
        swap(0, data.size() - 1);
        //删除新的堆尾元素（目前的最大值）
        data.remove(data.size() - 1);

        siftDown(0);
        return ret;
    }

        /**得到堆顶元素 */
    public E findMax() {
        if (data.size() == 0) {
            throw new IllegalArgumentException("Can not findMax when heap is empty");
        }
        return data.get(0);
    }

        /**元素下移 */
    private void siftDown(int k) {
        while (leftChild(k) < data.size()) { //如果左节点存在
            int j = leftChild(k);
            if (j + 1 < data.size() && data.get(j).compareTo(data.get(j + 1)) < 0) {
                //右节点比左节点大，使用右子节点
                j = rightChild(k);
            }
            if (data.get(k).compareTo(data.get(j)) >= 0) {
                //如果子节点都比k节点大，则跳出循环
                break;
            }
            //将堆与k节点相比最大的一个值j节点进行替换
            swap(k, j);
            k = j;
        }
    }

    /**
     * 功能实现:替换元素
     * Author: Lzy
     * Date: 2019/2/13 11:32
     * Param:
     * Return:
     */
    public E replace(E e ){
        E ret=findMax();
        data.set(0,e);
        siftDown(0);
        return ret;
    }

    public MaxHeap(E[] arrs){
        data=new ArrayList<>(Arrays.asList(arrs));
        for(int i=parent(data.size() -1);i>=0;i++){
            siftDown(i);
        }
    }
}
