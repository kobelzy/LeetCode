package dp;

/**
 * 740. 删除并获得点数
 * 给你一个整数数组 nums ，你可以对它进行一些操作。
 * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除每个等于 nums[i] - 1 或 nums[i] + 1 的元素。
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 * 示例 1：
 * 输入：nums = [3,4,2]
 * 输出：6
 * 解释：
 * 删除 4 获得 4 个点数，因此 3 也被删除。
 * 之后，删除 2 获得 2 个点数。总共获得 6 个点数。
 * 示例 2：
 * 输入：nums = [2,2,3,3,3,4]
 * 输出：9
 * 解释：
 * 删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
 * 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
 * 总共获得 9 个点数。
 * 提示：
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i] <= 104
 */
public class No740_DeleteAndEarn {
    /**
     * 状态转移方程：dp[i]=
     *
     * @param nums
     * @return
     */
    public int deleteAndEarn(int[] nums) {
        int maxVal = 0;
        for (int num : nums) maxVal = Math.max(maxVal, num);
        int[] sums = new int[maxVal + 1];
        for (int num : nums) sums[num] += num;

        int first = sums[0], second = Math.max(first, sums[1]);
        for (int i = 2; i < sums.length; i++) {
            int tmp = second;
            second = Math.max(first + sums[i], second);
            first = tmp;
        }
        return second;
    }

    public int deleteAndEarn2(int[] nums) {
        if(nums.length==0) return 0;
        if(nums.length==1) return nums[0];
        int maxVal = 0;
        for (int num : nums) maxVal = Math.max(maxVal, num);
        int[] sums = new int[maxVal + 1];
        for (int num : nums) sums[num]++;
        int[] dp = new int[maxVal + 1];
        dp[1] = sums[1];
        dp[2] = Math.max(sums[2] * 2, dp[1]);

        for (int i = 2; i <= maxVal; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + i * sums[i]);
        }
        return dp[maxVal];
    }
}
