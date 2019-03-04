package arithmetic

object Binary {
  def bsearch[T <% Ordered[T]](nums: Array[T], target: T):Int = {
    var low = 0
    var high = nums.length - 1
    while (low <= high) {
      val mid = low + ((high - low) >> 1)
      if (nums(mid) > target) high = mid - 1
      else if (nums(mid) < target) low = mid + 1
      else {
        if ((mid == 0) || (nums(mid - 1) != target)) return mid
        else high = mid - 1
      }
    }
    -1
  }

  def asearch[T](nums: Array[T], target: T)(implicit ev$1: T => Ordered[T]):Int = {
    val len=nums.length-1
    var low = 0
    var high = len - 1
    while (low <= high) {
      val mid = low + ((high - low) >> 1)
      if (nums(mid) > target) high = mid - 1
      else if (nums(mid) < target) low = mid + 1
      else {
        if ((mid == len-1) || (nums(mid + 1) != target)) return mid
        else high = mid + 1
      }
    }
    -1
  }
//查找第一个大于等于给定值的元素
  def searchFirstMax[T <% Ordered[T]](nums:Array[T],target:T):Int={
    var low=0
    var high=nums.length-1
    while(low <=high){
      val mid=low +((high-low)>>1)
      if(nums(mid)>=target){
        if(mid==0 ||nums(mid-1) < target) return mid
        else high=mid
      }else{
        low=mid+1
      }
    }
    -1
  }
//查找最后一个小于等于给定值的元素

  def searchLastMin[T<%Ordered[T]](nums:Array[T],target:T):Int={
    val len=nums.length
    var low=0
    var high=len-1
    while(low<=high){
      val mid=low+((high-low)>>1)
      if(nums(mid)>target) high=mid -1
      else{
        if(mid==len-1 || nums(mid+1) >target) return mid
        else low =mid +1
      }
    }
    -1
  }
}
