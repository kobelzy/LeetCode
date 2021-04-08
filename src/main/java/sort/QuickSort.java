package sort;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        QuickSort t=new QuickSort();
        int[] nums={2,3,4,1};
        t.quickSort(nums,0,nums.length-1);
        System.out.println(Arrays.toString(nums));
    }
    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) return;
        int lo = left+1;               // 小于分界点元素的最右侧的指针
        int hi = right;                // 大于分界点元素的最左侧的指针
        while (lo<=hi) {
            if (nums[lo]>nums[left]) { // 交换元素确保左侧指针指向元素小于分界点元素
                swap(nums, lo, hi);
                hi--;
            } else {
                lo++;
            }
        }
        lo--;                          // 回到小于分界点元素数组的最右侧
        swap(nums, left, lo);          // 将分界点元素移到左侧数组最右侧
        quickSort(nums, left, lo-1);
        quickSort(nums, lo+1, right);
    }


    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
