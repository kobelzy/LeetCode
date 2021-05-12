package string;

import scala.Char;

/**
 * 125. 验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * 示例 1:
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 * 输入: "race a car"
 * 输出: false
 * @description:
 * @author: Patrick Star
 * @time: 2021/5/12 21:55
 */
public class No125_ValidPalindrome {
    public static void main(String[] args) {
        No125_ValidPalindrome t = new No125_ValidPalindrome();
        System.out.println(t.isPalindrome("A man, a plan, a canal: Panama"));
    }

    public boolean isPalindrome(String s) {
        if (s.length() == 0) return true;
        int head = 0, last = s.length() - 1;
        while (head < last) {
            while (head < last && !Character.isLetterOrDigit(s.charAt(head))) head++;
            while (head < last && !Character.isLetterOrDigit(s.charAt(last))) last--;
            if (Character.toLowerCase(s.charAt(head++)) != Character.toLowerCase(s.charAt(last--))) return false;
        }
        return true;
    }
}
