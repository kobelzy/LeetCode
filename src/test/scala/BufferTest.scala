
/**
  * Created by Administrator on 2017/11/11.
  */
object BufferTest {
  def main(args: Array[String]): Unit = {
    val buffer=scala.collection.mutable.Buffer[Int](1,2,3,4)
    println(buffer.remove(-1))


  }
}
