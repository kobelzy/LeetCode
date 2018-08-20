package easy

/**
  * Created by taihe on 2017/11/13.
  */
object No9_IsPalidrome {
  def isPalidrome(x:Int):Boolean={
    val str=x.toString.toBuffer
    if(str(0).equals('-')) str.remove(0)
    str.reverse==str
  }

  def main(args: Array[String]): Unit = {
    print(isPalidrome(-2147447412))
  }
}
