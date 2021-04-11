package sort;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        QuickSort t=new QuickSort();
        int[] nums={9,8,7,6,0,4,3,2,1};
        t.quickSort(nums,0,nums.length-1);
        System.out.println(Arrays.toString(nums));
    }
    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) return;
        int i = left+1;               // 小于分界点元素的最右侧的指针
        int j = right;                // 大于分界点元素的最左侧的指针
        while (i<=j) {
            if (nums[i]>nums[left]) { // 交换元素确保左侧指针指向元素小于分界点元素
                swap(nums, i, j);
                j--;
            } else {
                i++;
            }
        }
        /**
         * 这个时候i>=nums[left]，可能等于，也可能是刚大于nums[left]第一个值
         * i--正好取得nums[left]的最终排序位置
         * 将nums[left]的值换到i，并各自进行内部的排序即可
         */
        i--;                          // 回到小于分界点元素数组的最右侧
        swap(nums, left, i);          // 将分界点元素移到左侧数组最右侧
        quickSort(nums, left, i-1);
        quickSort(nums, i+1, right);
    }


    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public void qucikSort2(int[] nums,int left,int right){
        if(left<right){
            int i=partition(nums,left,right);
            qucikSort2(nums,left,i-1);
            qucikSort2(nums,i+1,right);
        }
    }
    private int partition(int[] nums,int left,int right){
        int i=left,j=right+1;
        int temp=nums[left];
        while(true){
            while(nums[++i]<temp) if(i==right) break;
            while(nums[--j]>temp) if(j==left) break;
            if(i>=j) break;
            swap(nums,i,j);
        }
        swap(nums,left,j);
        return j;
    }
}
