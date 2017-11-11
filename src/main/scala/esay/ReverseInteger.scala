package esay

/**
  * Created by Administrator on 2017/11/11.
  */
object ReverseInteger {
//  def reverse(x: Int): Int = {
//
//    val str=x.toString
//    val reverseStr = str.reverse
//    var isMinus = false
//    if(str.startsWith("-")) isMinus=true
//    var reverseInteger = removeZero(reverseStr).toInt
//    if (isMinus) reverseInteger = 0 - reverseInteger
//    reverseInteger
//  }
//
//
//  def removeZero(str: String): String = {
//    val str1 = str.toBuffer
//    if(str.endsWith("-")) str1.remove(str1.length-1)
//    while (str1.startsWith("0")) {
//      str1.remove(0)
//    }
//    if(str1.isEmpty) str1+='0'
//    str1.mkString
//  }

  def reverse(x: Int): Long = {
    var flag:Int=x
    var ans:BigInt=0
    val maxint=0x7fffffff
    val minint=0x80000000
    while(flag!=0){
      ans=ans*10+(flag%10)
      flag/=10
    }
if(ans<minint||ans>maxint)ans=0
    println(ans)
ans.toInt
  }
  def main(args: Array[String]): Unit = {
    println(reverse(1534256469))
//    println(removeZero("000234"))
  }
}
