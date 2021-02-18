package array;

import java.util.Arrays;

/**
 * 561. 数组拆分 I
 * 给定长度为 2n 的整数数组 nums ，你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从 1 到 n 的 min(ai, bi) 总和最大。
 * 返回该 最大总和 。
 *
 * 示例 1：
 * 输入：nums = [1,4,3,2]
 * 输出：4
 * 解释：所有可能的分法（忽略元素顺序）为：
 * 1. (1, 4), (2, 3) -> min(1, 4) + min(2, 3) = 1 + 2 = 3
 * 2. (1, 3), (2, 4) -> min(1, 3) + min(2, 4) = 1 + 2 = 3
 * 3. (1, 2), (3, 4) -> min(1, 2) + min(3, 4) = 1 + 3 = 4
 * 所以最大总和为 4
 * 示例 2：
 * 输入：nums = [6,2,6,5,1,2]
 * 输出：9
 * 解释：最优的分法为 (2, 1), (2, 5), (6, 6). min(2, 1) + min(2, 5) + min(6, 6) = 1 + 2 + 6 = 9
 * 提示：
 * 1 <= n <= 104
 * nums.length == 2 * n
 * -104 <= nums[i] <= 104
 */
public class No561_ArrayPartitionI {
    /**
     * 排序，证明：https://leetcode-cn.com/problems/array-partition-i/solution/jue-dui-neng-kan-dong-de-zheng-ming-fan-f7trz/
     *
     * @param nums
     * @return
     */
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i += 2) {
            res += nums[i];
        }
        return res;
    }

    public int arrayPairSum2(int[] nums) {
        int len=nums.length;
        int[] count = new int[20001];
        for (int x : nums) {
            count[x + 10000]++;
        }
        // borrow是向上借一个
        int ret = 0, borrow = 0;
        for (int i = -10000; i <= 10000; i++) {
            // count[i]是总共出现了多少个， -borrow是i-1有没有借走一个
            // +1是为了保证，count[i] - borrow是奇数的情况下，多算一个
            ret += (count[i + 10000] - borrow + 1) / 2 * i;

            // 更新borrow，如果count[i] - borrow是奇数，就得从i+1借一个
            // +2是java语言防止-1 % 2 == -1
            borrow = (count[i + 10000] - borrow + 2) % 2;
        }
        return ret;
    }

}
