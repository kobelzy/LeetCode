package easy

/**
  * Auther: lzy
  * Description:
  * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
  *
  * 示例 1:
  *
  * 输入: 1
  * 输出: true
  * 解释: 20 = 1
  * Date Created by： 8:55 on 2019/1/8
  * Modified By：
  */

object No_231PowerofTwo {
    def isPowerOfTwo(n: Int): Boolean = {
        n>0 && (n & (n-1)) ==0
    }
}
