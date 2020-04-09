package easy;

/**
 * 面试题 05.06. 整数转换
 * 整数转换。编写一个函数，确定需要改变几个位才能将整数A转成整数B。
 *
 * 示例1:
 *
 *  输入：A = 29 （或者0b11101）, B = 15（或者0b01111）
 *  输出：2
 * 示例2:
 *
 *  输入：A = 1，B = 2
 *  输出：2
 * 提示:
 *
 * A，B范围在[-2147483648, 2147483647]之间
 */
public class Interview_0506 {
    public static void main(String[] args) {
        Interview_0506 init=new Interview_0506();
        int a=27;
        int b=15;
        System.out.println(init.convertInteger(a,b));
    }
    public int convertInteger(int A, int B) {
        return Integer.bitCount(A^B);
    }

}
