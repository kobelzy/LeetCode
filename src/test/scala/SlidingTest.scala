/**
  * Created by Administrator on 2017/11/25.
  */
object SlidingTest {
  def main(args: Array[String]): Unit = {

    val arr=Array(1,2,3,4,5,6)
//    arr.grouped(2).foreach(line=>println(line.mkString(",")))
    arr.sliding(4,3).foreach(line=>println(line.mkString(",")))
    println(arr.splitAt(3)._1.mkString(","))
//    arr.sliding(4).foreach(println)
  }
}
