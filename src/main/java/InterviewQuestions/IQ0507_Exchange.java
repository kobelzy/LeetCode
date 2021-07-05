package InterviewQuestions;

/**
 * 面试题 05.07. 配对交换
 * 配对交换。编写程序，交换某个整数的奇数位和偶数位，尽量使用较少的指令（也就是说，位0与位1交换，位2与位3交换，以此类推）。
 * 示例1:
 *  输入：num = 2（或者0b10）
 *  输出 1 (或者 0b01)
 * 示例2:
 *  输入：num = 3
 *  输出：3
 * 提示:
 * num的范围在[0, 2^30 - 1]之间，不会发生整数溢出。
 * @author: Patrick Star
 * @time: 2021/7/5 22:12
 */
public class IQ0507_Exchange {
    public static void main(String[] args) {
        IQ0507_Exchange t=new IQ0507_Exchange();
        System.out.println(t.exchangeBits2(2)); //1
        System.out.println(t.exchangeBits2(3)); //3
    }
    public int exchangeBits(int num) {
        System.out.println(Integer.toBinaryString(0x55555555));
        System.out.println(Integer.toBinaryString(0xaaaaaaaa));
        return (num & 0x55555555) << 1 | (num & 0xaaaaaaaa) >> 1;
    }

    public int exchangeBits2(int num) {
        //ob开头表示将字符串转二进制
        int v1=0b01010101010101010101010101010101;
        int v2=0b10101010101010101010101010101010;
        return (num & v1) << 1 | (num & v2) >> 1;
    }
}
