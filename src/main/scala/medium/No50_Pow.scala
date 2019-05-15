package medium

/**
  * Auther: lzy
  * Description:
  * Date Created by： 9:12 on 2018/11/27
  * Modified By：
  */

object No50_Pow {
    def main(args: Array[String]): Unit = {
//        println(myPow(1d,-2147483648))//1.0
//        println(myPow(2d,-2147483648))//0.0
//        println(myPow3(2d,10))
        println(Int.MaxValue)
        println(Int.MinValue)
        println(math.pow(1, Int.MinValue))
        println(math.pow(2, Int.MinValue))
        println(myPow3(2, 3))
        val a = 101L
        println(a & 1)
    }

    /**
      * 求x的n次方
      * （1）直接调用math函数
      * （2）傻乘法
      * （3）分治算法,递归可能导致栈溢出
      * 需要考虑是否基数🤔，复杂度为N(logN)
      *
      * @param x
      * @param n
      * @return
      */
    def myPow(x: Double, n: Int): Double = {
        if (n == 0) 1
        else if (n < 0) 1 / myPow(x, -n)
        else if (n % 2 != 0) x * myPow(x, n - 1)
        else myPow(x * x, n.toInt / 2)
    }

    /**
      * 非递归形式
      *
      * @param x
      * @param n
      * @return
      */
    def myPow2(x: Double, n: Int): Double = {
        var index = n
        var vx = x
        if (n < 0) {
            vx = 1 / vx
            index = -index
        }
        var pow = 1d
        while (index != 0) {
            if (index % 2 != 0) pow *= vx
            //用来判断奇偶，和1做并集，如果结果为1那么是奇数
//            if ((n & 1) ==1) pow *= vx
            vx *= vx
            index >>= 1
        }
        pow
    }
    /**二分法 */
    def myPow3(x: Double, n: Int): Double = {
        var s = x
        if (n == 0) return 1
        var nLong = n.toLong
        if (nLong < 0) {
            nLong = -nLong
            s = 1.0 / s
        }
        var res = 1d
        while (nLong != 0) {
            if ((nLong & 1) == 1) res *= s
            s *= s
            nLong = nLong >> 1
        }
        res
    }
}
