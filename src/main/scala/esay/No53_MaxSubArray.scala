package esay
import scala.util.control._
/**
  * Created by taihe on 2017/11/30.
  */
object No53_MaxSubArray {
  def maxSubArray(nums: Array[Int]): Int = {
    val loop=new Breaks
    val maxSize=math.sqrt(nums.length).toInt
      var max=nums(0)
      val map=scala.collection.mutable.Map[Int,Array[Int]]()
loop.breakable {
  for (len <- 1 to maxSize) {
    if (len*len == nums.length) {
      map(nums.sum) = nums
      loop.break()
    }
    for (i <- 0 until nums.length - len * len) {
      val buffer = scala.collection.mutable.ArrayBuffer[Int]()
      for (j <- i until i + len * len) {
        buffer.append(nums( j))
      }
      max = math.max(max, buffer.sum)
      map(max) = buffer.toArray
    }
  }
}
   map.keys.max
  }

  def main(args: Array[String]): Unit = {
    println(maxSubArray(Array(1,-2)))
  }
}
