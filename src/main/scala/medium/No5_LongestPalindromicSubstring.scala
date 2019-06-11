package medium

/**
  * 5. 最长回文子串
  * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
  *
  * 示例 1：
  *
  * 输入: "babad"
  * 输出: "bab"
  * 注意: "aba" 也是一个有效答案。
  * 示例 2：
  *
  * 输入: "cbbd"5. 最长回文子串
  * 输出: "bb"
  */
object No5_LongestPalindromicSubstring {
    def main(args: Array[String]): Unit = {
        println(longestPalindrome("babad"))
//        for(i<-"abcba".indices){
//            println(i)
//        }
//        val i=1
//        for (j <- i - 1 to 0 by -1 if j>=0) {
//        println(i,j)
//        }
    }
        /**动态规划解法 */
    def longestPalindrome(s: String): String = {
        if (s.length < 2) return s
        val dp = Array.fill(s.length, s.length)(true) //表示从j到i的字符串你是否是回文字符串
        var right, left = 0
        for (i <- s.indices) {
            for (j <- i - 1 to 0 by -1 if j>=0) {
                dp(i)(j) = s(i) == s(j)                 &&
                        (i - j == 1 || dp(i - 1)(j + 1))
                if (dp(i)(j) && (i - j) >= (right - left)) {
                    right = i
                    left = j
                }
            }
        }
        println(left,right)
        s.substring(left, right + 1)
    }
}
