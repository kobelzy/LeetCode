package array;

/**
 * 581. 最短无序连续子数组
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 * 示例 1：
 * 输入：nums = [2,6,4,8,10,9,15]
 * 输出：5
 * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * 示例 2：
 * 输入：nums = [1,2,3,4]
 * 输出：0
 * 示例 3：
 * 输入：nums = [1]
 * 输出：0
 * 提示：
 * 1 <= nums.length <= 104
 * -105 <= nums[i] <= 105
 */
public class No581_ShortestUnsortedContinuousSubarray {

    /**
     * 优先掌握
     * 这个算法背后的思想是无序子数组中最小元素的正确位置可以决定左边界，最大元素的正确位置可以决定右边界。
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {
        int len = nums.length;
        int left = 0, right = len - 1;
        while (left < right && nums[left] <= nums[left + 1]) left++;
        while (left < right && nums[right] >= nums[right - 1]) right--;
        int min = nums[left], max = nums[right];
        int l = left, r = right;
        for (int k = l; k <= r; k++) {
            if (nums[k] < min) {
                while (left >= 0 && nums[left] > nums[k]) left--;
                min = left >= 0 ? nums[left] : Integer.MIN_VALUE;
            }
            if (nums[k] > max) {
                while (right < len && nums[right] < nums[k]) right++;
                max = right < len ? nums[right] : Integer.MAX_VALUE;
            }
        }
        return right == left ? 0 : (right - 1) - (left + 1) + 1;
    }


    public int findUnsortedSubarray2(int[] nums) {
        int len = nums.length;
        int left = -1, min = Integer.MAX_VALUE;
        int right = -1, max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            if (nums[i] < max) right = i;
            else max = nums[i];

            if (nums[len - 1 - i] > min) left = len - 1 - i;
            else min = nums[i];
        }
        return right == -1 ? 0 : right - left + 1;
    }

    int MIN = -100005, MAX = 100005;



}
