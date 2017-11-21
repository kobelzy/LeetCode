package esay

import scala.collection.mutable

/**
  * Created by taihe on 2017/11/21.
  */
object No665_CheckPossibility {
def checkPossibility(nums: Array[Int]): Boolean = {
if(nums.isEmpty) return false
  val min=nums.min
  val max=nums.max
if(sort(nums.toBuffer.drop(min))==0){
  return true
}
  if(sort(nums.toBuffer.drop(max))==0){
    return true
  }
false
}
def sort(nums:mutable.Buffer[Int]):Int={
  var index=0
  for(i<-0 until nums.length){
    for(j<-0 until nums.length-1-i){
      if(nums(j)>nums(j+1)){
        val flag=nums(j)
        nums(j)=nums(j+1)
        nums(j+1)=flag
        index+=1
      }
    }
  }
  index
}

  def main(args: Array[String]): Unit = {
//    println(sort(Array(1,2,3,3,5,6)))
    println(checkPossibility(Array(4,2,1)))
  }
}
