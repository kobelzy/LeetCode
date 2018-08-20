package easy

/**
  * Created by Administrator on 2017/11/25.
  */
object RemoveDuplicates {
def removeDuplicates(nums:Array[Int]):Int={
  var buffer=nums.toBuffer
  if(nums.isEmpty) return 0
  var j=0
  for(i<-nums.indices) {
  if(buffer(i)!=buffer(j)){
    j+=1
    buffer(j)=buffer(i)
  }
  }
buffer=buffer.splitAt(j)._1
  j +1
}

  def main(args: Array[String]): Unit = {
    println(removeDuplicates(Array(1,1,2)))
  }
}
