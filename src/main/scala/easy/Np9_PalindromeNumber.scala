package easy


/**
  * 9. 回文数
  * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
  *
  * 示例 1:
  *
  * 输入: 121
  * 输出: true
  * 示例 2:
  *
  * 输入: -121
  * 输出: false
  * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
  * 示例 3:
  *
  * 输入: 10
  * 输出: false
  * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
  * 进阶:
  *
  * 你能不将整数转为字符串来解决这个问题吗？
  */
object Np9_PalindromeNumber {
  def main(args: Array[String]): Unit = {
    println(isPalindrome(2222222))
    println(isPalindrome(222222))
    println(isPalindrome(12321))
    println(isPalindrome(12331))
    println(isPalindrome(22))
    println(isPalindrome(21))
    println(isPalindrome(2))
  }

  def isPalindrome1(x: Int): Boolean = {
    if(x<0) return false
    var temp = x
    var length = 1
    while (temp > 9) {
      temp = temp / 10
      length += 1
    }
    val mid = length >> 1
    var index = 0
    while (index < mid) {
      index += 1
      val left = (x / math.pow(10, length - index).toInt) % 10
      val right = (x % math.pow(10, index).toInt) / math.pow(10, index - 1).toInt
      if (left != right) return false
    }
    true
  }

  def isPalindrome(x: Int): Boolean = {
    if(x<0) false else x==reverse(x,0)
  }

  /**
    * 加了尾递归优化反而更慢？
    * @param x
    * @param y
    * @return
    */
  @scala.annotation.tailrec
  def reverse(x:Int,y:Int):Int=if(x==0)y else reverse(x/10,y*10+x%10)
}
