package esay

import scala.collection.mutable

/**
  * Created by taihe on 2017/11/21.
  */
object No665_CheckPossibility {
def checkPossibility(nums: Array[Int]): Boolean = {
//if(nums.isEmpty) return false
//  val min=nums.min
//  val max=nums.max
//if(sort(nums.toBuffer.drop(min))==0){
//  return true
//}
//  if(sort(nums.toBuffer.drop(max))==0){
//    return true
//  }
//false
  val buffer=nums.toBuffer
  var index=0
  var i=1
  while(i<buffer.length|| index<=1){

    if(buffer(i-1)>buffer(i) ||index>1){
      index +=1
      if (i - 2 < 0 || nums(i - 2) <= nums(i)) nums(i - 1) = nums(i)
      else nums(i ) = nums(i-1)

    }
    i+=1
  }
  index<=1
}


  def main(args: Array[String]): Unit = {
//    println(sort(Array(1,2,3,3,5,6)))
    println(checkPossibility(Array(2,3,3,2,4)))
  }
}
