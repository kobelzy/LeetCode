package sort;

import java.util.Arrays;

/**
 * 堆排序
 * 详解：https://www.cnblogs.com/chengxiao/p/6129630.html
 * 详解：https://zhuanlan.zhihu.com/p/128892381
 * 堆排序是一种选择排序，整体主要由构建初始堆+交换堆顶元素和末尾元素并重建堆两部分组成。
 * 其中构建初始堆经推导复杂度为O(n)，在交换并重建堆的过程中，需交换n-1次，
 * 而重建堆的过程中，根据完全二叉树的性质，[log2(n-1),log2(n-2)...1]逐步递减，近似为nlogn。
 * 所以堆排序时间复杂度一般认为就是O(nlogn)级。
 * ⚠️：堆应该趋近于完全二叉树
 *
 * ⚠️：堆排序不是稳定的排序算法，因为在排序的过程，存在将堆的最后一个节点跟堆顶节点互换的操作，所以就有可能改变值相同数据的原始相对顺序。
 * @author Partric Star
 */
public class HeapSort {
    public static void main(String[] args) {
//        int[] arr = {5, 1, 7, 3, 1, 6, 9, 4};
        int[] arr = {16, 7, 3, 20, 17, 8};
        HeapSort t=new HeapSort();
        t.heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }


    /**
     * 创建堆，
     * @param arr 待排序列
     */
    public void heapSort(int[] arr) {
        int len=arr.length;
        //1.构建大顶堆
        for(int i=len/2-1;i>=0;i--){
            //从第一个非叶子结点从下至上，从右至左调整结构,len/2-1表示倒数第二层的最右节点
            adjustHeap(arr,i,len);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for(int j=len-1;j>0;j--){
            swap(arr,0,j);//将堆顶元素与末尾元素进行交换
            adjustHeap(arr,0,j);//重新对堆进行调整
        }
    }

    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     * @param arr
     * @param i
     * @param length
     */
    private void adjustHeap(int []arr,int i,int length){
        int temp = arr[i];//先取出当前元素i
        for(int k=i*2+1;k<length;k=k*2+1){//从i结点的左子结点开始，也就是2i+1处开始
            if(k+1<length && arr[k]<arr[k+1]){//如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if(arr[k] >temp){//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[k];
                i = k;
            }else{
                break;
            }
        }
        arr[i] = temp;//将temp值放到最终的位置
    }

    /**
     * 交换元素
     * @param arr
     * @param a
     * @param b
     */
    private  void swap(int []arr,int a ,int b){
        int temp=arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
