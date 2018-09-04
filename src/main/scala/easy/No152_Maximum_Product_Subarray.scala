package easy

/**
  * Created by Administrator on 2018/9/4.
  */
object No152_Maximum_Product_Subarray {
  def maxProduct(nums: Array[Int]): Int = {
    var max_product=nums.head
    for(i<-nums.indices){
      var index=i+1
      while(nums.slice(i,index).product>=max_product && index<=nums.length+1) {
        max_product=nums.slice(i,index).product
        index+=1
      }
    }
    max_product
  }

  def main(args: Array[String]): Unit = {
//val arr=Array(2,3,-2,4)
val arr=Array(-2,0,-1)
//val arr=Array(0,2)
    println(maxProduct(arr))
  }
}
