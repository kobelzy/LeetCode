package esay

/**
  * Created by Administrator on 2017/11/12.
  */
object FindLHS {
  def findLHS(nums: Array[Int]): Int = {
val value2Count: Array[(Int, Int)] =nums.map((_,1)).groupBy(_._1)
  .map{case (word,countIterator)=>(word,countIterator.length)}.toArray.sortBy(_._1)
//    println(value2Count.mkString)
//    result.foreach(line=>println(line._1,line._2.mkString))
    val values2Count=scala.collection.mutable.HashMap[(Int,Int),Int]()
for(i<-0 until value2Count.length-1){
  if(value2Count(i)._1+1==value2Count(i+1)._1){
    values2Count((value2Count(i)._1,value2Count(i+1)._1))=value2Count(i+1)._2+value2Count(i)._2
  }
}
     values2Count.maxBy(_._2)._2


  }


  def main(args: Array[String]): Unit = {
    findLHS(Array(1,3,2,2,5,2,3,7))
  }
}
