package bitOperation;

/**
 * 169. 多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * 示例 1：
 * 输入：[3,2,3]
 * 输出：3
 * 示例 2：
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 * 进阶：
 * 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 *
 * @description:
 * @author: Patrick Star
 * @time: 2021/5/6 21:00
 */
public class No169_MajorityElementJava {
    public static void main(String[] args) {
        int[] nums = {3, 2, 3};
        No169_MajorityElementJava t = new No169_MajorityElementJava();
        System.out.println(t.majorityElement0(nums));
    }

    public int majorityElement0(int[] nums) {
        int res = 0, k = nums.length >> 1;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int n : nums) {
                count += (n >> i) & 1;
                if (count > k) {
                    res += 1 << i;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 摩尔投票法思路
     * 候选人(cand_num)初始化为nums[0]，票数count初始化为1。
     * 当遇到与cand_num相同的数，则票数count = count + 1，否则票数count = count - 1。
     * 当票数count为0时，更换候选人，并将票数count重置为1。
     * 遍历完数组后，cand_num即为最终答案。
     * <p>
     * 为何这行得通呢？
     * 投票法是遇到相同的则票数 + 1，遇到不同的则票数 - 1。
     * 且“多数元素”的个数> ⌊ n/2 ⌋，其余元素的个数总和<= ⌊ n/2 ⌋。
     * 因此“多数元素”的个数 - 其余元素的个数总和 的结果 肯定 >= 1。
     * 这就相当于每个“多数元素”和其他元素 两两相互抵消，抵消到最后肯定还剩余至少1个“多数元素”。
     * <p>
     * 无论数组是1 2 1 2 1，亦或是1 2 2 1 1，总能得到正确的候选人。
     * <p>
     * 作者：gfu
     * 链接：https://leetcode-cn.com/problems/majority-element/solution/3chong-fang-fa-by-gfu-2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int candNum = nums[0];
        int count = 0;
        for (int num : nums) {
            if (num == candNum) count += 1;
            else {
                count -= 1;
                if (count == 0) {
                    candNum = num;
                    count = 1;
                }
            }
        }
        return candNum;
    }
}
