package hard

/** n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
  * *
  *
  *
  * 上图为 8 皇后问题的一种解法。
  * *
  * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
  * *
  * 示例:
  * *
  * 输入: 4
  * 输出: 2
  * 解释: 4 皇后问题存在如下两个不同的解法。
  * [
  * [".Q..",  // 解法 1
  * "...Q",
  * "Q...",
  * "..Q."],
  * *
  * ["..Q.",  // 解法 2
  * "Q...",
  * "...Q",
  * ".Q.."]
  * ]
  * Auther: Lzy
  * Description:使用位运算解
  * Date Created by： 8:57 on 2019/1/14
  * Modified By：
  */

object No_52_NQueensII {
    def main(args: Array[String]): Unit = {
        println(6 & -6)
    }
    var count=0
    def totalNQueens(n: Int): Int = {
        if (n < 1) return 0
        count=0
        dfs(n, 0, 0, 0, 0)
        return count
    }

    def dfs(n: Int, row: Int, cols: Int, pie: Int, na: Int): Unit = {
        if (row >= n) {
            count += 1;
            return
        }
        //得到当前所有空位
        /*

         */
        var bits: Int = (~(cols | pie | na)) & ((1 << n) - 1) // 2n-1

        while (bits != 0) {
            //取最低位的1
            val p = bits & -bits
            dfs(n, row + 1, cols | p, (pie | p) << 1, (na | p) >> 1)
            bits = bits & (bits - 1)//去掉最低位的1
        }


    }
}
