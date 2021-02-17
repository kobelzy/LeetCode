package string;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 151. 翻转字符串里的单词
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * 说明：
 * 无空格字符构成一个 单词 。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * 示例 1：
 * 输入："the sky is blue"
 * 输出："blue is sky the"
 * 示例 2：
 * 输入："  hello world!  "
 * 输出："world! hello"
 * 解释：输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 * 输入："a good   example"
 * 输出："example good a"
 * 解释：如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * 示例 4：
 * 输入：s = "  Bob    Loves  Alice   "
 * 输出："Alice Loves Bob"
 * 示例 5：
 * 输入：s = "Alice does not even like bob"
 * 输出："bob like even not does Alice"
 * 提示：
 * 1 <= s.length <= 104
 * s 包含英文大小写字母、数字和空格 ' '
 * s 中 至少存在一个 单词
 * 进阶：
 * 请尝试使用 O(1) 额外空间复杂度的原地解法。
 */
public class No151_ReverseWordsInAString {
    public static void main(String[] args) {
//        String s = "  Bob    Loves  Alice   ";
        String s = "the sky is blue";
        No151_ReverseWordsInAString t = new No151_ReverseWordsInAString();
        System.out.println(t.reverseWords(s));
    }


    public String reverseWords(String s) {
        int len = s.length();
        StringBuilder res = new StringBuilder();
        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') continue;
            int end = i;
            while (i >= 0 && s.charAt(i) != ' ') i--;
            res.append(s, i+1, end + 1); //i+1是因为i定位到了空格，end+1是因为需要取到end这个字符
            res.append(" ");
        }
        return res.substring(0,res.length()-1);
    }

    /**
     * 正则表达式
     * @param s
     * @return
     */
    public String reverseWords2(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }


}
