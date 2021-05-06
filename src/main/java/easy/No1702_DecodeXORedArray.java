package easy;

/**
 * 1720. 解码异或后的数组
 * 未知 整数数组 arr 由 n 个非负整数组成。
 * 经编码后变为长度为 n - 1 的另一个整数数组 encoded ，其中 encoded[i] = arr[i] XOR arr[i + 1] 。例如，arr = [1,0,2,1] 经编码后得到 encoded = [1,2,3] 。
 * 给你编码后的数组 encoded 和原数组 arr 的第一个元素 first（arr[0]）。
 * 请解码返回原数组 arr 。可以证明答案存在并且是唯一的。
 * 示例 1：
 * 输入：encoded = [1,2,3], first = 1
 * 输出：[1,0,2,1]
 * 解释：若 arr = [1,0,2,1] ，那么 first = 1 且 encoded = [1 XOR 0, 0 XOR 2, 2 XOR 1] = [1,2,3]
 * 示例 2：
 * 输入：encoded = [6,2,7,3], first = 4
 * 输出：[4,2,0,7,4]
 * 提示：
 * 2 <= n <= 104
 * encoded.length == n - 1
 * 0 <= encoded[i] <= 105
 * 0 <= first <= 105
 */
public class No1702_DecodeXORedArray {

    /**
     * 复习一下异或的特点:
     * 一个值与自身的异或总是为 0   x ^ x = 0
     * 一个值与 0 异或等于本身    x ^ 0 = x
     * 可交换性   a ^ b = b ^ a
     * 可结合性   (a ^ b) ^ c = a ^ (b ^ c)
     * 根据以上的四个特点
     * 我们可以推导: a ^ b = c
     * 等式两边都增加对b的异或, 等价于  a ^ b ^ b = c ^ b
     * 等式左边的 b^b=0, a^0=a, 所以有  a = c ^ b
     * 最终相当于把 b 从等号左边转到等号右边来了.
     * <p>
     * 题目提供的信息有
     * encode[i] = decode[i] ^ decode[i+1]
     * 当我们想求 decode[i+1], 则有  decode[i+1] = decode[i] ^ encode[i]
     *
     * @param encoded
     * @param first
     * @return
     */
    public int[] decode(int[] encoded, int first) {
        int len = encoded.length + 1;
        int[] res = new int[len];
        res[0] = first;
        for (int i = 1; i < len; i++) {
            res[i] = res[i - 1] ^ encoded[i - 1];
        }
        return res;
    }

}
