package medium

/**
  * Auther: lzy
  * Description:
  * Date Created by： 9:12 on 2018/11/27
  * Modified By：
  */

object No50_Pow {
    def main(args: Array[String]): Unit = {
//        println(myPow2(1d,-2147483648))
        println(myPow2(2d,10))
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
//            if (n % 2 != 0) pow *= vx
            if ((n & 1) ==0) pow *= vx
            vx *= vx
            index >>= 1
        }
        pow
    }
}
