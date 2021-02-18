package array;

/**
 * 485. 最大连续 1 的个数
 * 给定一个二进制数组， 计算其中最大连续 1 的个数。
 * 示例：
 * 输入：[1,1,0,1,1,1]
 * 输出：3
 * 解释：开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.
 * 提示：
 * 输入的数组只包含 0 和 1 。
 * 输入数组的长度是正整数，且不超过 10,000。
 */
public class NO_485_MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            int j = i;
            while (j < nums.length && nums[j] == 1) j++;
            if (j - i > maxLen) maxLen = j - i;//这里j-i不需要+1，因为j已经向后移动了一位
            i = j;
        }
        return maxLen;
    }

    public int findMaxConsecutiveOnes2(int[] nums) {
        int count = 0;
        int maxCount = 0;
        for (int num : nums) {
            if (num == 1) {
                count++;
            } else {
                maxCount = Math.max(count, maxCount);
                count = 0;
            }
        }
        maxCount = Math.max(count, maxCount);
        return maxCount;
    }
}
