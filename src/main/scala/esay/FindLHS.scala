package esay

/**
  * Created by Administrator on 2017/11/12.
  */
object FindLHS {
  def findLHS(nums: Array[Int]): Int = {
    if(nums.length==0) return 0
    val value2Count: Array[(Int, Int)] = nums.map((_, 1)).groupBy(_._1)
      .map { case (word, countIterator) => (word, countIterator.length) }.toArray.sortBy(_._1)
    val values2Count = scala.collection.mutable.HashMap[(Int, Int), Int]()
    if (value2Count.length == 1) return 0
    for (i <- 0 until value2Count.length - 1) {
      if (value2Count(i)._1 + 1 == value2Count(i + 1)._1) {
        values2Count((value2Count(i)._1, value2Count(i + 1)._1)) = value2Count(i + 1)._2 + value2Count(i)._2
      }
    }
    if(values2Count.isEmpty) 0
    else values2Count.maxBy(_._2)._2


  }


  def main(args: Array[String]): Unit = {
   println( findLHS(Array(1,1,1,1)))
  }
}
