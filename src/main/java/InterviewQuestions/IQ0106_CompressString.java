package InterviewQuestions;

/**
 * 面试题 01.06. 字符串压缩
 * 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。
 * 示例1:
 *  输入："aabcccccaaa"
 *  输出："a2b1c5a3"
 * 示例2:
 *  输入："abbccd"
 *  输出："abbccd"
 *  解释："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。
 * 提示：
 * 字符串长度在[0, 50000]范围内。
 * @author: Patrick Star
 * @time: 2021/6/27 11:19
 */
public class IQ0106_CompressString {
    public static void main(String[] args) {
        IQ0106_CompressString t = new IQ0106_CompressString();
        System.out.println(t.compressString("aabcccccaaa"));
        System.out.println(t.compressString("abbccd"));
    }

    /**
     * 模拟
     * @param S
     * @return
     */
    public String compressString(String S) {
        if (S == null || S.length() <= 2) return S;
        char[] chars = S.toCharArray();
        int len = chars.length;
        char[] res = new char[len*2];
        int index = 0;
        for (int i = 0; i < len; i++) {
            char cur = chars[i];
            res[index++] = cur;
            int j = i;
            while (j < len-1 && chars[j + 1] == cur) j++;
            if (j >= i) {
                String diff =String.valueOf( j - i + 1);
                for (int k = 0; k < diff.length(); k++) {
                    res[index++]=diff.charAt(k);
                }
                i = j;
            }
        }
        return index >= len ? S : String.valueOf(res, 0, index);
    }
}