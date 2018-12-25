package easy

/**
  * Auther: lzy
  * Description:
  * 实现 int sqrt(int x) 函数。
  *
  * 计算并返回 x 的平方根，其中 x 是非负整数。
  *
  * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
  *
  * 示例 1:
  *
  * 输入: 4
  * 输出: 2
  * 示例 2:
  *
  * 输入: 8
  * 输出: 2
  * 说明: 8 的平方根是 2.82842...,
  * 由于返回类型是整数，小数部分将被舍去。
  * Date Created by： 9:05 on 2018/12/24
  * Modified By：
  */

object No69_Sqrt_x {
    def mySqrt(x: Int): Int = {
        if (x == 0 || x == 1) {
            x
        } else {
            //下界
            var l = 1
            //上界
            var r = x
            //结果值
            var res: Int=0
            while (l <= r) {
                val m = (l + r) / 2
                if (m == x / m) m
                else if (m > x / m) r = m - 1
                else {
                    l = m + 1;
                    res = m
                }
            }
            res
        }

    }
}
