package easy
import scala.util.control._
/**
  * Created by Administrator on 2017/11/9.
  */
object No1_TwoSum {
  val loop=new Breaks
  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    val map=scala.collection.mutable.Map[Int,Int]()
    for(i<-nums.indices){
     map(nums(i))=i
    }
    val result=scala.collection.mutable.Buffer[Int]()
    loop.breakable{
    for(i<-nums.indices){
      val complement=target-nums(i)
      if(map.contains(complement)&&map(complement)!=i){
        result+=i
        result+=map(complement)
        loop.break()
      }
    }}


    result.toArray
  }

  def main(args: Array[String]): Unit = {
    println(twoSum(Array(3,2,4),6).mkString(","))
  }
}
