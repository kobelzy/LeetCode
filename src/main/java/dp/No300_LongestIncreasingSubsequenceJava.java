package dp;

/**
 * 300. 最长递增子序列
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * 示例:
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 *
 * @description:
 * @author: Patrick Star
 * @time: 2021/5/9 10:58
 */
public class No300_LongestIncreasingSubsequenceJava {

    public static void main(String[] args) {
//        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int[] nums = {0, 1, 0, 3, 2, 3}; //4
        No300_LongestIncreasingSubsequenceJava t = new No300_LongestIncreasingSubsequenceJava();
        System.out.println(t.lengthOfLIS(nums));//4
    }


    /**
     * 状态转移方程：dp[i]=max(dp[j])+1
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int res = dp[0];
        for (int i = 1; i < nums.length; i++) {
            int maxVal = 0;
            for (int j = 0; j < i; j++) {
                //不需要遍历到i，与i做比较，取最大的dp值
                if (nums[j] < nums[i]) maxVal = Math.max(maxVal, dp[j] );
            }
            //增加i位置对应的状态1
            dp[i] = maxVal+1;
            res = Math.max(dp[i], res);
        }
        return res;
    }

}
