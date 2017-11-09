/**
  * Created by Administrator on 2017/11/9.
  */
object mapTest {
  def main(args: Array[String]): Unit = {
    val map=Map[Int,Int](1->2,2->3)
    print(map.contains(3))
  }
}
