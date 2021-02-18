package array;

/**
 * 209. 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * 示例 1：
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 * 提示：
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 */
public class No209_MinimumSizeSubarraySum {

    /**
     * 快慢指针，速度较快
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int len = nums.length;
        int left = 0;
        int res = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            while (sum >= target) { //注意这里是while循环
                res = Math.min(res, i - left+1);
                sum -= nums[left];
                left++;
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    /**
     * 双指针暴力，很慢
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen2(int target, int[] nums) {
        int maxCount = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0, j = i;
            while (j < nums.length && sum < target && j - i < maxCount) sum += nums[j++];
            if (sum >= target) maxCount = Math.min(maxCount, j - i);
        }
        return maxCount == Integer.MAX_VALUE ? 0 : maxCount;
    }
}
