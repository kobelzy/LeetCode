package easy

/**
  * Auther: lzy
  * Description:
  * Date Created by： 9:56 on 2019/1/4
  * Modified By：
  */

object No191_NumberOf1Bits {
    def hammingWeight(n: Int): Int = {
        var res = 0
        var mark = n
        while (mark != 0) {
            mark &= mark - 1
            res += 1
        }
        res
    }
}
