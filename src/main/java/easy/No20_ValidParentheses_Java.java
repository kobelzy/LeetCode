package easy;

import java.util.*;

/**
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 */
public class No20_ValidParentheses_Java {
    public static void main(String[] args) {
        No20_ValidParentheses_Java test = new No20_ValidParentheses_Java();
        System.out.println(test.isValid("()[]{}"));
        System.out.println(test.isValid("([)]"));
        System.out.println(test.isValid("]([)]"));
        System.out.println(test.isValid("]"));
    }

    public boolean isValid(String s) {
        int len = s.length();
        if((len & 1) ==1) return false;
        Map<Character, Character> map = new HashMap<>(3);
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) stack.push(c);
            else {
                if (!(!stack.isEmpty() && map.get(stack.pop()) == c)) return false;
            }
            //可选，如果超过长度一半，那么后边如论如何都无法消除所有括号
            if(stack.size()>(len>>1)) return false;
        }
        return stack.isEmpty();
    }
}
