/**
  * Created by Administrator on 2017/11/9.
  */
object mapTest {
  def main(args: Array[String]): Unit = {
    val map=scala.collection.mutable.Map[Int,Int]()
    map(1)=1
    map(2)=2
    map(1)=2
    print(map)

  }
}
