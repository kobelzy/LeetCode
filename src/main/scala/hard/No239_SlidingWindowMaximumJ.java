package hard;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 滑动窗口最大值
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口 k 内的数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口最大值。
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * 注意：
 *
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 输入数组的大小，且输入数组不为空。
 * @program: LeetCode
 * @description:
 * @author: Lzy
 * @create: 2019-02-13 16:11
 **/
public class No239_SlidingWindowMaximumJ {
    public static void main(String[] args){
        int[] nums=new int[]{1,3,-1,-3,5,3,6,7};
        No239_SlidingWindowMaximumJ a=new No239_SlidingWindowMaximumJ();
        int[] result=a.maxSlidingWindow(nums,3);
        System.out.println(Arrays.toString(result));
    }
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len=nums.length;
        if(len==0){return new int[0];}
        PriorityQueue<Integer> p=new PriorityQueue<>((i1, i2) -> i2 - i1);
        int[] result=new int[len-k+1];
        for(int i=0;i<k;i++){
            p.add(nums[i]);
        }
        result[0]=p.peek();
        for(int i=k;i<len;i++){
            p.remove(nums[i-k]);
                p.add(nums[i]);
            result[i-k+1]=p.peek();
        }
        return   result;
    }
}
