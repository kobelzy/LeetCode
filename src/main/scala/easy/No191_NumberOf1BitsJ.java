package easy;
/**
 * @program: LeetCode
 * @description:
 * 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。
 *示例 1：
 *
 * 输入：00000000000000000000000000001011
 * 输出：3
 * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
 *提示：
 *
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。
 * @author: Lzy
 * @create: 2019-01-04 09:56
 **/
public class No191_NumberOf1BitsJ {
    public static void main(String[] args){
        String a= Integer.toBinaryString(8);
        System.out.println(a);
        int mark=9;
                mark&=mark-1;
        System.out.println(mark);
    }
    public int hammingWeight(int n) {
        int res=0;

        for(int mark=n;mark!=0;mark &= mark-1){
            res++;
        }
        return res;
    }

}
