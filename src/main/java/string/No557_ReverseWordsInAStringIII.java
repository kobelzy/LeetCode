package string;

/**
 * 557. 反转字符串中的单词 III
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * 示例：
 * 输入："Let's take LeetCode contest"
 * 输出："s'teL ekat edoCteeL tsetnoc"
 * 提示：
 * 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 */
public class No557_ReverseWordsInAStringIII {
    public static void main(String[] args) {
        No557_ReverseWordsInAStringIII t=new No557_ReverseWordsInAStringIII();
        System.out.println(t.reverseWords("Let's take LeetCode contest"));
    }
    public String reverseWords(String s) {
        int len = s.length();
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == ' ') continue;
            int fast = i;
            while (fast<len && s.charAt(fast)!= ' ') fast++; //fast最终为空格
            for (int j = fast-1; j >=i; j--) {
                sb.append(s.charAt(j));
            }
            i=fast;
            if(i<len && s.charAt(i)==' ')sb.append(' ');
        }
        return sb.toString();
    }
}
