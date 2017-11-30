package esay

/**
  * Created by taihe on 2017/11/30.
  */
object No53_MaxSubArray {
  def maxSubArray(nums: Array[Int]): Int = {
    val maxSize=math.sqrt(nums.length).toInt
      var max=0
      val map=scala.collection.mutable.Map[Int,Array[Int]]()
    for(len<-2 to maxSize){
     for(i<-0 until nums.length-len){
       val buffer=scala.collection.mutable.ArrayBuffer[Int]()
       for(j<-i until len){
         buffer.append( nums(j))
       }
       max=math.max(max,buffer.sum)
       map(max)=buffer.toArray
     }
    }
   map.keys.max
  }
}
