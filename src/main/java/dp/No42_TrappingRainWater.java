package dp;

/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 * 提示：
 * n == height.length
 * 0 <= n <= 3 * 104
 * 0 <= height[i] <= 105
 *
 * @description:
 * @author: Patrick Star
 * @time: 2021/5/10 22:05
 */
public class No42_TrappingRainWater {
    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        No42_TrappingRainWater t = new No42_TrappingRainWater();
        System.out.println(t.trap(nums));
    }

    /**
     * 动态规划解法：
     * 每个柱，最多能放的，就是其左右最高的两个柱子围城的面积；
     * 求最高位置，用动态规划：
     * 左侧：
     * dp[i]=max(dp[i-1],height[i])
     * 边界：dp[0]=height[0[;
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int n = height.length;
        if (n < 3) return 0;
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return res;
    }

}
