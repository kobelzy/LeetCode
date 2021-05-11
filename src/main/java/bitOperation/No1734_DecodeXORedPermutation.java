package bitOperation;

/**
 * 1734. 解码异或后的排列
 * 给你一个整数数组 perm ，它是前 n 个正整数的排列，且 n 是个 奇数 。
 * 它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i + 1] 。比方说，如果 perm = [1,3,2] ，那么 encoded = [2,1] 。
 * 给你 encoded 数组，请你返回原始数组 perm 。题目保证答案存在且唯一。
 * 示例 1：
 * 输入：encoded = [3,1]
 * 输出：[1,2,3]
 * 解释：如果 perm = [1,2,3] ，那么 encoded = [1 XOR 2,2 XOR 3] = [3,1]
 * 示例 2：
 * 输入：encoded = [6,5,4,6]
 * 输出：[2,4,1,5,3]
 * 提示：
 * 3 <= n < 105
 * n 是奇数。
 * encoded.length == n - 1
 * @description:
 * @author: Patrick Star
 * @time: 2021/5/11 21:54
 */
public class No1734_DecodeXORedPermutation {
    public int[] decode(int[] encode) {
        int n = encode.length + 1;
        int[] ans = new int[n];
        //求除了ans[n-1]的所有异或结果
        int a = 0;
        for (int i = 0; i < n - 1; i += 2) a ^= encode[i];
        //求ans的所有异或结果
        int b = 0;
        for (int i = 1; i <= n; i++) b ^= i;
        //求ans[n-1]
        ans[n - 1] = a ^ b;
        //从后往前求原始值；
        for (int i = n - 2; i >= 0; i--) {
            ans[i] = encode[i] ^ ans[i + 1];
        }
        return ans;
    }
}
