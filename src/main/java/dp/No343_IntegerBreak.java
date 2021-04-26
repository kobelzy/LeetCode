package dp;

/**
 * 343. 整数拆分
 * 剑指 Offer 14- I. 剪绳子
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * 示例 1：
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 * 示例 2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 * 提示：
 * 2 <= n <= 58
 */
public class No343_IntegerBreak {
    public static void main(String[] args) {
        No343_IntegerBreak t=new No343_IntegerBreak();
        System.out.println(t.integerBreak(10));
    }
    /**
     * 动态规划解法
     */
    public int integerBreak(int n) {
        if (n < 2) return 0;
        if (n == 2) return 1;
        if (n == 3) return 2;
        int[] maxDP = new int[n + 1];
        maxDP[1] = 1;
        maxDP[2] = 2;
        maxDP[3] = 3;//只是初始化，不是最优解
        for (int i = 4; i <= n; i++) {
            int max = 0;
            for (int j = 1; j <= i / 2; j++) {
                int product = maxDP[j] * maxDP[i - j];
                if (max < product) max = product;
            }
            maxDP[i] = max;
        }
        return maxDP[n];
    }
}
