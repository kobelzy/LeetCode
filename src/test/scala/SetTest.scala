/**
  * Created by Administrator on 2017/11/23.
  */
object SetTest {
  def main(args: Array[String]): Unit = {
//    println(Set(1,2,1))
//    val arr=Array("","2","3","4")
//    println(arr.diff(Array("2",3,4)).mkString(","))
//    println(arr.mkString(","))

    val arrs=scala.collection.mutable.ListBuffer(1,2,3,4)
    arrs-=arrs.last
    arrs-=arrs.last
    println(arrs)
    println(" ".split(' ').length)
  }
}
