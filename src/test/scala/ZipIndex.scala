/**
  * Created by taihe on 2017/11/13.
  */
object ZipIndex {
  def main(args: Array[String]): Unit = {
    val arr=Array(2,7,11)
    val index2Arr:Array[(Int, Int)]=arr.zipWithIndex.sortBy(_._1)
    println(index2Arr.mkString)
  }
}
