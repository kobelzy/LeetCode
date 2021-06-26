package binarySearch;

/**
 * 33. 搜索旋转排序数组
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的索引，否则返回 -1 。
 * 示例 1：
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * 示例 2：
 *
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * 示例 3：
 *
 * 输入：nums = [1], target = 0
 * 输出：-1
 * 提示：
 *
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * nums 中的每个值都 独一无二
 * nums 肯定会在某个点上旋转
 * -10^4 <= target <= 10^4
 */
public class No33_SearchInRoatedSortedArray {
    public static void main(String[] args) {
//        int[] nums = new int[]{3,1};
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        No33_SearchInRoatedSortedArray t = new No33_SearchInRoatedSortedArray();
        System.out.println(t.search(nums, 0));
    }

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int len = nums.length;
        if (len == 1) return nums[0] == target ? 0 : -1;
        int last = nums[0];
        int revertIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < last) revertIndex = i;
            last = nums[i];
//            if(nums[i]<nums[0]){
//                revertIndex=i;
//                break;
//            }
        }
        int left = 0;
        int right =revertIndex==0?len-1: revertIndex - 1;
        if(target<nums[0]){
            left=revertIndex;
            right=len-1;
        }
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) return mid;
            else if ( nums[mid]>target) right=mid-1;
            else left=mid+1;
        }
        return -1;
    }


    public int search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int len = nums.length;
        if (len == 1) return nums[0] == target ? 0 : -1;
        int flag = nums[0];
        int revertIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < flag) revertIndex = i;
            flag = nums[i];
        }
        int left = 0;
        int right =revertIndex==0?len-1: revertIndex - 1;
        if(target<nums[0]){
            left=revertIndex;
            right=len-1;
        }
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) return mid;
            else if ( nums[mid]>target) right=mid-1;
            else left=mid+1;
        }
        return -1;
    }


}
