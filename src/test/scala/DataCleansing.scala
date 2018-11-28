import scala.util.Try

/**
  * Created by Administrator on 2017/11/20.
  */
object DataCleansing {
  def main(args: Array[String]): Unit = {
run()
//    val int:Integer=null
//    val int2:Int=100
//    val int:Integer=1
//    val int3:Integer=Int.box(int2)
//    val int4:Int=Int.unbox(int)
//    println(int4+1)
//    println(Try(int.toInt).toOption)
  }

  def run()={
//    println(1/0) //报错  java.lang.ArithmeticException: / by zero
//    println(0/0) ////报错  java.lang.ArithmeticException: / by zero
    println(-1d/0) //报错  Infinity
    println(0d/0) ////报错  NaN
    println(0/1) //0
    println("---")
    println(1d/0d) //Infinity
    println(0d/0d) //NaN
    println(0d/1d) //0.0

    println(100d/33) //3.0303030303030303
    println(100/33) //3
Double.PositiveInfinity
Double.NegativeInfinity
println((-1d/0d))
    println((0d/0d).isNaN)
  }
}
