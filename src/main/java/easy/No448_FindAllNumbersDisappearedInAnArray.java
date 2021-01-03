package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 448. 找到所有数组中消失的数字
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 *
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 *
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 *
 * 示例:
 *
 * 输入:
 * [4,3,2,7,8,2,3,1]
 *
 * 输出:
 * [5,6]
 */
public class No448_FindAllNumbersDisappearedInAnArray {
    public static void main(String[] args) {
        No448_FindAllNumbersDisappearedInAnArray test=new No448_FindAllNumbersDisappearedInAnArray();
        int[] nums={1,2,3,4,5,3,3};
        System.out.println(test.findDisappearedNumbers(nums));
    }
    public List<Integer> findDisappearedNumbers(int[] nums) {
        boolean[] bm=new boolean[nums.length];
        for (int num : nums)bm[num-1]=true;
        List<Integer> res=new ArrayList<Integer>();
        for (int i = 0; i < bm.length; i++) {
            if(bm[i]==false)res.add(i+1);
        }
    return res;
    }

    /**
     * 原地解决方案
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers2(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int index=Math.abs(nums[i])-1;
            nums[index]= - Math.abs(nums[index]);
        }
        List<Integer> res=new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]>0) res.add(i+1);
        }
        return res;
    }

}
