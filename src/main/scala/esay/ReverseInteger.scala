package esay

/**
  * Created by Administrator on 2017/11/11.
  */
object ReverseInteger {
  def reverse(x: Int): Int = {

    val str=x.toString
    val reverseStr = str.reverse
    var isMinus = false
    if(str.startsWith("-")) isMinus=true
    var reverseInteger = removeZero(reverseStr).toInt
    if (isMinus) reverseInteger = 0 - reverseInteger
    reverseInteger
  }


  def removeZero(str: String): String = {
    val str1 = str.toBuffer
    if(str.endsWith("-")) str1.remove(str1.length-1)
    while (str1.startsWith("0")) {
      str1.remove(0)
    }
    if(str1.isEmpty) str1+='0'
    str1.mkString
  }

  def main(args: Array[String]): Unit = {
    println(reverse(1534236469))
//    println(removeZero("000234"))
  }
}
