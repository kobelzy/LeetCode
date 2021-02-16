package array;

/**
 * 35. 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 * 示例 1:
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 示例 2:
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * 示例 3:
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * 示例 4:
 * 输入: [1,3,5,6], 0
 * 输出: 0
 */
public class No35_SearchInsertPosition {


    /**
     * 二分查找
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int min = 0;
        int max = nums.length - 1;
        int res = nums.length;
        while (min <= max) {
            int mid = min + ((max - min) >> 2);
            int tmp = nums[mid];
            if (target <= nums[mid]) {
                res = mid;
                max = mid - 1;
            } else min = mid + 1;
        }
        return res;
    }

    /**
     * 暴力求解
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert2(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < target) {
                if (i + 1 < nums.length && nums[i + 1] >= target) return i + 1;
            } else return i;
        }
        if (nums[nums.length - 1] < target) return nums.length;
        return -1;
    }

}
