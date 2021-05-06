package bitOperation;

/**
 * 136. 只出现一次的数字
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * 示例 1:
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 * 输入: [4,1,2,1,2]
 * 输出: 4
 */
public class No136_SingleNumber {
    public static void main(String[] args) {
        No136_SingleNumber no136SingleNumber = new No136_SingleNumber();
        int res=no136SingleNumber.singleNumber(new int[]{4,4,2,66,66});
        System.out.println(res);
    }

    /**
     * 自己和自己异或，结果为0；
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int flag=0;
        for (int num : nums) {
            flag ^= num;
        }
        return flag;
    }
}
