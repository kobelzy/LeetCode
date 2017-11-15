package esay

/**
  * Created by taihe on 2017/11/15.
  */
object RomanToInt {
  def romanToInt(s:String):Int={
    var i=0
    if(!s.contains(".")){ i=s.toInt}
    else{
     i=s.split("\\.")(0).toInt
    }
    i
  }

  def main(args: Array[String]): Unit = {
    println(romanToInt("3453543635433431432421"))
  }
}
