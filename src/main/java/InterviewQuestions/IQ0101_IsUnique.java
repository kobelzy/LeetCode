package InterviewQuestions;


/**
 * 面试题 01.01. 判定字符是否唯一
 * 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
 * 示例 1：
 * 输入: s = "leetcode"
 * 输出: false
 * 示例 2：
 * 输入: s = "abc"
 * 输出: true
 * 限制：
 * 0 <= len(s) <= 100
 * 如果你不使用额外的数据结构，会很加分。
 */
public class IQ0101_IsUnique {
    public static void main(String[] args) {
        IQ0101_IsUnique t = new IQ0101_IsUnique();

//        System.out.println(t.isUnique2("abc"));
//        char c='0';
//        System.out.println(1 << (c - 'a'));
//        System.out.println(Integer.toBinaryString(1 << (c - 'a')));
        System.out.println(Integer.toBinaryString(7));
        System.out.println(Integer.toBinaryString(1<<7));
        System.out.println(Integer.toBinaryString(2*2*2*2*2*2*2));
        System.out.println(Integer.toBinaryString(14));
        for (int i = 0; i < 100; i++) {
            System.out.println(i+":"+Integer.toBinaryString(1<<i));
        }
    }

    /**
     * 双指针解法
     *
     * @param astr
     * @return
     */
    public boolean isUnique(String astr) {
        if (astr.length() <= 1) return true;
        char[] chars = astr.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            for (int j = 0; j < i; j++) {
                if (chars[i] == chars[j]) return false;
            }
        }
        return true;
    }


    /**
     * 位运算解法：
     * 1<<n 的含义是 1*2^n ，也就是转换为2的n次方，得到的是只包含1的数
     * 5<<n 的含义是 5*2^n
     * @param astr
     * @return
     */
    public boolean isUnique2(String astr) {
        if (astr.length() <= 1) return true;
        int cache = 0;
        char[] chars = astr.toCharArray();
        for (char c : chars) {
            //c-'a'计算出该值相对于a的位置偏移
            // 1<< 将其转换为2^n 得到一个只有1个1的二进制串
            // 如果cache已经包含了一位该值，那么&操作结果就是1，则重复了
            if ((cache & 1 << (c - 'a')) != 0) return false;
            // 不存在，则将数据写入到cache中，用或操作。
            else cache |= 1 << (c - 'a');
        }
        return true;
    }
}
