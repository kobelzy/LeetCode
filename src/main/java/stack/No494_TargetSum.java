package stack;

/**
 * 494. 目标和
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 * 示例：
 * 输入：nums: [1, 1, 1, 1, 1], S: 3
 * 输出：5
 * 解释：
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * 一共有5种方法让最终目标和为3。
 * 提示：
 * 数组非空，且长度不会超过 20 。
 * 初始的数组的和不会超过 1000 。
 * 保证返回的最终结果能被 32 位整数存下。
 */
public class No494_TargetSum {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 1, 1};
        No494_TargetSum t = new No494_TargetSum();
        System.out.println(t.findTargetSumWays(nums, 3));
    }

    /**
     * DFS递归，只击败了7%
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        return dfs(nums, S, 0, sum);
    }

    private int dfs(int[] nums, int S, int i, int sum) {
        if (i >= nums.length )
            return sum == S ? 1 : 0;

        return dfs(nums, S, i + 1, sum + nums[i]) +
                dfs(nums, S, i + 1, sum - nums[i]);
    }
}
