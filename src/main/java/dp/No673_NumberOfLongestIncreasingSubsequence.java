package dp;

import java.util.Arrays;

/**
 * 673. 最长递增子序列的个数
 * 给定一个未排序的整数数组，找到最长递增子序列的个数。
 * 示例 1:
 * 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 * 示例 2:
 * 输入: [2,2,2,2,2]
 * 输出: 5
 * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 * 注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。
 * @description:
 * @author: Patrick Star
 * @time: 2021/5/9 16:59
 */
public class No673_NumberOfLongestIncreasingSubsequence {
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 4, 7};
        No673_NumberOfLongestIncreasingSubsequence t = new No673_NumberOfLongestIncreasingSubsequence();
        System.out.println(t.findNumberOfLIS(nums));
    }

    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] counts = new int[nums.length];
        int[] dp = new int[nums.length];
        //初始化状态：最长递增子序列的个数至少为1；最招递增子序列的长度至少为1
        Arrays.fill(counts, 1);
        Arrays.fill(dp, 1);

        int maxLen = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        counts[i] = counts[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        counts[i] += counts[j];
                    }
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (dp[i] == maxLen) res += counts[i];
        }
        return res;
    }
}
