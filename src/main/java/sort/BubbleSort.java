package sort;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {9,8,7,6,5,4,3,2,1};
        new BubbleSort().sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public void sort(int[] nums) {
        boolean hasChange = true;
        for (int i = 0, n = nums.length; i < n && hasChange; i++) {
            hasChange = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    hasChange = true;
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
