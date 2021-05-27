package array;

import java.util.Arrays;

/**
 * 4. 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * 示例 1：
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 示例 3：
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 * 示例 4：
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 * 示例 5：
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 * 提示：
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 *
 * @author: Patrick Star
 * @time: 2021/5/27 21:30
 */
public class No4_MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = {3, 4};

        No4_MedianOfTwoSortedArrays t = new No4_MedianOfTwoSortedArrays();
        System.out.println(t.findMedianSortedArrays2(nums1, nums2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        int len = nums1.length + nums2.length;
        int[] merged = new int[(len >> 1) + 1];
        int i1 = 0, i2 = 0, t = 0;
        while (i1 < len1 && i2 < len2 && t < merged.length) {
            if (nums1[i1] <= nums2[i2]) merged[t++] = nums1[i1++];
            else merged[t++] = nums2[i2++];
        }

        while (i1 < len1 && t < merged.length) merged[t++] = nums1[i1++];
        while (i2 < len2 && t < merged.length) merged[t++] = nums2[i2++];
        if ((len & 1) == 0) return (merged[merged.length - 1] + merged[merged.length - 2]) / 2.0;
        else return merged[merged.length - 1];
    }

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        int len = nums1.length + nums2.length;
        int mid = (len >> 1) + 1;
        int i1 = 0, i2 = 0, t = 0;
        int left = -1, right = -1;
        while (i1 < len1 && i2 < len2 && t < mid) {
            t++;
            if (nums1[i1] <= nums2[i2]) {
                left = right;
                right = nums1[i1++];
            } else {
                left = right;
                right = nums2[i2++];
            }
        }

        while (i1 < len1 && t < mid){
            left = right;
            right = nums1[i1++];
            t++;
        }
        while (i2 < len2 && t <mid){
            left = right;
            right = nums2[i2++];
            t++;
        }
        if ((len & 1) == 0) return (left+right) / 2.0;
        else return right;
    }

}
