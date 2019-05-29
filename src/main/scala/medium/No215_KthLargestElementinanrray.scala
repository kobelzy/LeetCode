package medium

/** 215. 数组中的第K个最大元素
  * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
  *
  * 示例 1:
  *
  * 输入: [3,2,1,5,6,4] 和 k = 2
  * 输出: 5
  * 示例 2:
  *
  * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
  * 输出: 4
  * 说明:
  *
  * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
  */

object No215_KthLargestElementinanrray {
  def main(args: Array[String]): Unit = {
    //        val arr=Array(3,2,1,5,6,4)
    val arr = Array(1, 3, 2)
    //        val arr=Array(3,3,3,3,4,3,3,3,3)
    println(findKthLargest(arr, 2))
  }

  def findKthLargest1(nums: Array[Int], k: Int): Int = {
    if (nums.length <= 2 && nums.head == nums.last) return nums.head
    val (left, right) = nums.tail.partition(_ >= nums.head)
    if (left.length >= k)
      return findKthLargest1(left, k)
    else
      return findKthLargest1(right :+ nums.head, k - left.length)
  }


  /**
    * 该方案更快一些
    *
    * @param nums
    * @param k
    * @return
    */
  def findKthLargest(nums: Array[Int], k: Int): Int = {
    val head = nums.head
    val (leftAndHead, right) = nums.partition(_ >= head)
    val left = leftAndHead.filter(_ != head)
    if (k <= left.length) findKthLargest(left, k)
    else if (k <= leftAndHead.length) head
    else findKthLargest(right, k - leftAndHead.length)
  }
}
