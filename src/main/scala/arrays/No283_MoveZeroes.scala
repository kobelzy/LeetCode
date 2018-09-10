package arrays

/**
  * Created by Administrator on 2018/9/10.
  */
object No283_MoveZeroes {
  def moveZeroes(nums: Array[Int]): Array[Int] = {
//    if(nums.isEmpty)



    var k = 0
    for(i<-nums.indices){
      if(nums(i) != 0) {
        nums(k) = nums(i)
        k+=1
      }
    }
    while(k < nums.length) {
      nums(k) = 0
      k+=1
    }
    nums

  }

  def main(args: Array[String]): Unit = {
val arr=Array(0,1,0,3,12)
    moveZeroes(arr)
  }
}
