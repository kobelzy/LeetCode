package arrays

/**
  * Created by Administrator on 2018/9/10.
  */
object No283_MoveZeroes {
  def moveZeroes(nums: Array[Int]): Array[Int] = {
    var curIndex = nums.length - 1
    var lastIndex = nums.length - 1
    var count = 0

    while (curIndex >= 0) {
      if (nums(curIndex)== 0) {
        count = lastIndex - curIndex
        for(i<-0 until count)
          nums(curIndex + i) = nums(curIndex + i + 1)
        }
      println(lastIndex)
        nums(lastIndex) = 0
        lastIndex -= 1
      }
      curIndex -= 1
    nums
  }

  def main(args: Array[String]): Unit = {
val arr=Array(0,1,0,3,12)
    moveZeroes(arr)
  }
}
