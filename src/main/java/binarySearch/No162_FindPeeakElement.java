package binarySearch;

/**

 *
 * 这是两道求峰值的题目，不同点是，162可能有多个峰值；
 *
 *  * 162. 寻找峰值
 * 峰值元素是指其值大于左右相邻值的元素。
 * 给你一个输入数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 * 示例 1：
 * 输入：nums = [1,2,3,1]
 * 输出：2
 * 解释：3 是峰值元素，你的函数应该返回其索引 2。
 * 示例 2：
 * 输入：nums = [1,2,1,3,5,6,4]
 * 输出：1 或 5
 * 解释：你的函数可以返回索引 1，其峰值元素为 2；
 *      或者返回索引 5， 其峰值元素为 6。
 * 提示：
 * 1 <= nums.length <= 1000
 * -231 <= nums[i] <= 231 - 1
 * 对于所有有效的 i 都有 nums[i] != nums[i + 1]
 *
 */
public class No162_FindPeeakElement {
    /**
     * 162. 寻找峰值
     * 852. 山脉数组的峰顶索引
     * 都可求解
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        int left=0,right=nums.length-1;
        while(left<right){
            int mid=(right+left)>>1;
            if(nums[mid]>nums[mid+1])right=mid;
            else left=mid+1;
        }
        return left;
    }


    /**
     * 852. 山脉数组的峰顶索引
     * 符合下列属性的数组 arr 称为 山脉数组 ：
     * arr.length >= 3
     * 存在 i（0 < i < arr.length - 1）使得：
     * arr[0] < arr[1] < ... arr[i-1] < arr[i]
     * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
     * 给你由整数组成的山脉数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i 。
     * 示例 1：
     * 输入：arr = [0,1,0]
     * 输出：1
     * 示例 2：
     * 输入：arr = [0,2,1,0]
     * 输出：1
     * 示例 3：
     * 输入：arr = [0,10,5,2]
     * 输出：1
     * 示例 4：
     * 输入：arr = [3,4,5,1]
     * 输出：2
     * 示例 5：
     * 输入：arr = [24,69,100,99,79,78,67,36,26,19]
     * 输出：2
     * 提示：
     * 3 <= arr.length <= 104
     * 0 <= arr[i] <= 106
     * 题目数据保证 arr 是一个山脉数组
     * @param nums
     * @return
     */
    public int findPeakElement2(int[] nums) {
        int left = 0, right = nums.length - 1,ans=0;
        while(left<=right){
            int mid=(left+right)>>>1;
            if(nums[mid]>nums[mid+1]){
                ans=mid;
                right=mid-1;
            }else left=mid+1;
        }
        return ans;
    }
}
