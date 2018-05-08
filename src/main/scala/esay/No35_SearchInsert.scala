package esay

/**
  * Created by taihe on 2017/11/28.
  */
object No35_SearchInsert {
  def searchInsert(nums: Array[Int], target: Int): Int = {
      if(nums.isEmpty) return 0
      if(nums.contains(target)) return nums.indexOf(target)
    if(nums(0)>target) return 0
      for(i<-1 until nums.length){
        if(nums(i-1)<target&&nums(i)>target)  return i
      }
    nums.length
  }

  def main(args: Array[String]): Unit = {
   println( searchInsert(Array(1,3,4,5),2))
  }
}
