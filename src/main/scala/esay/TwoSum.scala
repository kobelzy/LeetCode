package esay

/**
  * Created by Administrator on 2017/11/9.
  */
object TwoSum {
  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    val map=scala.collection.mutable.Map[Int,Int]()
    for(i<-nums.indices){
     map(nums(i))=i
    }
    val result=scala.collection.mutable.Buffer[Int]()
    for(i<-nums.indices){
      val complement=target-nums(i)
      if(map.contains(complement)&&map(complement)!=i){
        println(i)
        result+=i
        result+=map(complement)
      }
    }
    println(map)
    result.toArray
  }

  def main(args: Array[String]): Unit = {
    println(twoSum(Array(3,2,4),6).mkString(","))
  }
}
