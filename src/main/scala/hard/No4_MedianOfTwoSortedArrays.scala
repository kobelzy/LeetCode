package hard

/*4. 寻找两个有序数组的中位数
给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。

请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。

你可以假设 nums1 和 nums2 不会同时为空。

示例 1:

nums1 = [1, 3]
nums2 = [2]

则中位数是 2.0
示例 2:

nums1 = [1, 2]
nums2 = [3, 4]

则中位数是 (2 + 3)/2 = 2.5
 */
object No4_MedianOfTwoSortedArrays {
  def main(args: Array[String]): Unit = {
    println(findMedianSortedArrays(Array(1,3),Array(2,4))) //2.5
    println(findMedianSortedArrays(Array(1,3),Array(2)))   //2.0
    println(findMedianSortedArrays(Array(1,3),Array(2)))   //2.0
    println(findMedianSortedArrays(Array(1,3),Array.empty[Int]))   //2.0
  }
  def findMedianSortedArrays(nums1: Array[Int], nums2: Array[Int]): Double = {
    val len1 = nums1.length
    val len2 = nums2.length
    var i = 0
    var j = 0
    val outs = (len1 + len2) / 2 - 1
    var current = 0
    while (current < outs) {
      if (nums1(i) < nums2(j)  ){ if (i < len1 )  i += 1 else j+=1}
      else j += 1
      current += 1
    }
    val num1=if(len1==0) 0 else nums1(i)
    val num2=if(len2==0) 0 else nums2(j)
    if ((len1 + len2) % 2 == 0) (num1 + num2) / 2d else {
      if (num1 > num2) num1 else num2
    }
  }

}
