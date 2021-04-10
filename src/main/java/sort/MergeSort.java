package sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {9,8,7,6,5,4,3,2,1};
        new MergeSort().sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public int[] sort(int[] nums){
        int[] temp = new int[nums.length];
        return sort(nums,0,nums.length-1,temp);
    }
    public int[] sort(int[] nums, int left, int right,int[] temp) {
        if (left < right) {
            int mid = left + ((right - left) >> 1);
            sort(nums, left, mid,temp);
            sort(nums, mid + 1, right,temp);
            merge(nums, left, mid, right, temp);
        }
        return nums;
    }

    private void merge(int[] nums, int left, int mid, int right, int[] temp) {
        int i = left, j = mid+1, t = 0;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) temp[t++] = nums[i++];
            else temp[t++] = nums[j++];
        }

        while (i <= mid) temp[t++] = nums[i++];
        while (j <= right) temp[t++] = nums[j++];
        t = 0;
        while (left <= right) nums[left++] = temp[t++];
    }
}
