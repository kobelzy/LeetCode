package easy;


import java.util.PriorityQueue;

/**
 * 数据流中的第K大元素
 * 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。
 * <p>
 * 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。每次调用 KthLargest.add，返回当前数据流中第K大的元素。
 * <p>
 * 示例:
 * <p>
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 * 说明:
 * 你可以假设 nums 的长度≥ k-1 且k ≥ 1。
 *
 * @program: LeetCode
 * @description:
 * @author: Lzy
 * @create: 2019-02-13 13:48
 **/
public class No703_KthLargestElementInAStreamJ {
    public static void main(String[] args) {
        int k = 2;
        int[] nums = new int[]{4, 5, 8, 2};
        No703_KthLargestElementInAStreamJ obj = new No703_KthLargestElementInAStreamJ(k, nums);

        System.out.println(obj.add(3));
        System.out.println(obj.add(5));
        System.out.println(obj.add(10));
        System.out.println(obj.add(9));
        System.out.println(obj.add(4));
    }


    final PriorityQueue<Integer> q;
    final int k;

    public No703_KthLargestElementInAStreamJ(int k, int[] nums) {
        this.k = k;
        this.q = new PriorityQueue<>(k);
        for (int n : nums) {
            add(n);
        }
    }

    public int add(int val) {
        if (q.size() < k) {
            //不满足k个值，直接添加
            q.add(val);
        } else if (q.peek() < val) {
            //如果堆尾元素小于目标值，说明应该添加该值
            q.poll();//堆尾出队
            q.add(val);
        }
        //返回堆尾，也就是第k大元素
        return q.peek();
    }

}
