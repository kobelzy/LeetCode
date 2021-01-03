package easy;

/**
 * 461. 汉明距离
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 * 注意：
 * 0 ≤ x, y < 231.
 * 示例:
 * 输入: x = 1, y = 4
 * 输出: 2
 * <p>
 * 解释:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 * ↑   ↑
 * <p>
 * 上面的箭头指出了对应二进制位不同的位置。
 */
public class No461_HammingDistance {
    public static void main(String[] args) {
        No461_HammingDistance test = new No461_HammingDistance();
        System.out.println(test.hammingDistance(1, 4));
    }

    public int hammingDistance(int x, int y) {
        int ors = x ^ y;
        int res = 0;
//        while (ors != 0) {
//            if ((ors & 1) == 1) res++;
//            ors = ors >> 1;
//        }
        //布赖恩·克尼根算法 名字起的就他妈炫酷
        while (ors != 0) {
            res += 1;
            ors &= (ors - 1);
        }
        return res;
    }

    public int hammingDistance2(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

}
