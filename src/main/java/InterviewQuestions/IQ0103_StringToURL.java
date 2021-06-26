package InterviewQuestions;

/**
 * 面试题 01.03. URL化
 * URL化。编写一种方法，将字符串中的空格全部替换为%20。假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。（注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）
 * 示例 1：
 * 输入："Mr John Smith    ", 13
 * 输出："Mr%20John%20Smith"
 * 示例 2：
 * 输入："               ", 5
 * 输出："%20%20%20%20%20"
 * 提示：
 * 字符串长度在 [0, 500000] 范围内。
 *
 * @author: Patrick Star
 * @time: 2021/6/26 22:34
 */
public class IQ0103_StringToURL {

    public static void main(String[] args) {
        IQ0103_StringToURL t = new IQ0103_StringToURL();
        System.out.println(t.replaceSpaces("Mr John Smith    ", 13));
        System.out.println(t.replaceSpaces("ds sdfs afs sdfa dfssf asdf             ", 27));
    }

    public String replaceSpaces(String S, int length) {
        char[] chars = S.toCharArray();
        int index = S.length() - 1;
        for (int i = length - 1; i >= 0; i--) {
            if (chars[i] == ' ') {
                chars[index--] = '0';
                chars[index--] = '2';
                chars[index--] = '%';
            } else {
                chars[index--] = chars[i];
            }
        }
        //new String第二个参数是count；
        return new String(chars, index + 1, chars.length - index - 1);
    }
}
