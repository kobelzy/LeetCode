package hard

/**
  * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
  *
  * 你可以对一个单词进行如下三种操作：
  *
  * 插入一个字符
  * 删除一个字符
  * 替换一个字符
  * 示例 1:
  *
  * 输入: word1 = "horse", word2 = "ros"
  * 输出: 3
  * 解释:
  * horse -> rorse (将 'h' 替换为 'r')
  * rorse -> rose (删除 'r')
  * rose -> ros (删除 'e')
  * 示例 2:
  *
  * 输入: word1 = "intention", word2 = "execution"
  * 输出: 5
  * 解释:
  * intention -> inention (删除 't')
  * inention -> enention (将 'i' 替换为 'e')
  * enention -> exention (将 'n' 替换为 'x')
  * exention -> exection (将 'n' 替换为 'c')
  * exection -> execution (插入 'u')
  *
  * Auther: Lzy
  * Description:
  * Date Created by： 8:56 on 2019/1/16
  * Modified By：
  */

object No72_EditDistance {
    def main(args: Array[String]): Unit = {
        val w1 = "horse"
        val w2 = "ros"
        println(minDistance(w1, w2))
    }

    /*
    DP两部曲：
    1、状态：DP[i][j] ：word1的前i个字符=>word2前j个字符最少的步数
    2、DP方程
     */
    def minDistance(word1: String, word2: String): Int = {
        val (m, n) = (word1.length, word2.length)
        val dp = Array.ofDim[Int](m + 1, n + 1)

        for (i <- dp.indices) dp(i)(0) = i
        for (j <- dp.head.indices) dp(0)(j) = j

        for (i <- 1 to m; j <- 1 to n) {
            //i表示Word1所在的字符位，j表示word2
            dp(i)(j) = math.min(dp(i - 1)(j - 1) + (if (word1(i - 1) == word2(j - 1)) 0 else 1), //replace,如果直接相等，那么不用移动，如果不移动，那么需要交换一下，是1词
                math.min(dp(i - 1)(j) + 1,//insert
                    dp(i)(j - 1) + 1)//delete)
                )
        }
        return dp(m)(n)
    }
}
