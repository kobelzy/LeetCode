package InterviewQuestions;

/**
 * 面试题 01.02. 判定是否互为字符重排
 * 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
 * 示例 1：
 * 输入: s1 = "abc", s2 = "bca"
 * 输出: true
 * 示例 2：
 * 输入: s1 = "abc", s2 = "bad"
 * 输出: false
 * 说明：
 *
 * 0 <= len(s1) <= 100
 * 0 <= len(s2) <= 100
 * @author: Patrick Star
 * @time: 2021/6/26 21:55
 */
public class IQ0102_CheckPermutation {
    public static void main(String[] args) {
        IQ0102_CheckPermutation t = new IQ0102_CheckPermutation();
        System.out.println(t.CheckPermutation("abc", "cba"));
        System.out.println(t.CheckPermutation("aa", "bb"));
    }

    /**
     * 数组记录出现次数
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean CheckPermutation(String s1, String s2) {
        if (s1 == null && s2 == null) return true;
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;

        int[] cache = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            cache[s1.charAt(i) - 'a']++;
            cache[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (cache[i] != 0) return false;
        }
        return true;
    }

    /**
     * 位运算，未完成
     * 解决不了自身数据重复问题，比如aa和bb
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean CheckPermutation2(String s1, String s2) {
        if (s1 == null && s2 == null) return true;
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;

        int flag = 0;
        for (int i = 0; i < s1.length(); i++) {
            //不存在，或 ，存在异或
            // 0 1 ->1 0 0 ->0 1 1->0
            flag ^= 1 << (s1.charAt(i) - 'a');
            flag ^= 1 << (s2.charAt(i) - 'a');
        }
        return flag == 0;
    }
}
