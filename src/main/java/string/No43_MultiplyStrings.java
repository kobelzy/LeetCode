package string;

import java.util.Arrays;

/**
 * 43. 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * <p>
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * <p>
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 */
public class No43_MultiplyStrings {
    public static void main(String[] args) {
        No43_MultiplyStrings t = new No43_MultiplyStrings();
        System.out.println(t.multiply2("290", "99"));
    }



    public String multiply2(String num1, String num2) {
        int m = num1.length(), n = num2.length(); // 结果最多为 m + n 位数
        int[] res = new int[m + n];
        // 从个位数开始逐位相乘
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0'); // 乘积在 res 对应的索引位置
                int p1 = i + j, p2 = i + j + 1; //对应十位和各个位
                // 叠加到 res 上
                int sum = mul + res[p2]; //最大为81+9=90
                res[p2] = sum % 10;
                res[p1] += sum / 10; //最大是9，又可能超过啊。。。
            }
        }
        // 结果前缀可能存的 0(未使用的位)
        StringBuilder str = new StringBuilder();
        int i = 0;
        while (i < res.length) {
            if(str.length()==0 && res[i]==0) continue;
            str.append(res[i]);
            i++;
        }
        return str.length() == 0 ? "0" : str.toString();
    }
}
