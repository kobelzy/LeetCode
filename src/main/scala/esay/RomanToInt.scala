package esay

/**
  * Created by taihe on 2017/11/15.
  */
object RomanToInt {
  def romanToInt(s:String):Int= {
    var num = 0
    val map = Map('M' -> 1000, 'D' -> 500, 'C' -> 100, 'L' -> 50, 'X' -> 10, 'V' -> 5, 'I' -> 1)
    for (i <- 0 until s.length - 1) {
      if (map(s(i)) > map(s(i + 1))) {
        num -= map(s(i))
      } else {
        num += map(s(i))
      }
    }
      num += map(s(s.length - 1))
    num
  }
  def main(args: Array[String]): Unit = {
    println(romanToInt("XXI"))
  }
}
