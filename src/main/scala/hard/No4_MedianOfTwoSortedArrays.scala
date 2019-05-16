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
        println(findMedianSortedArrays(Array(1, 3), Array(2, 4))) //2.5
        println(findMedianSortedArrays(Array(1, 3), Array(2)))   //2.0
        println(findMedianSortedArrays(Array(1, 3), Array(2)))   //2.0
        println(findMedianSortedArrays(Array(1, 3), Array.empty[Int]))   //2.0
    }

    def findMedianSortedArrays(nums1: Array[Int], nums2: Array[Int]): Double = {

        val len1 = nums1.length
        val len2 = nums2.length
        if (len1 == 0) {
            if ((len2 & 1) == 1) return nums2(len2 >> 1 + 1) else nums2(len2 >> 1)
        } else if (len2 == 0) {
            if ((len1 & 1) == 1) return nums1(len1 >> 1 + 1) else nums1(len1 >> 1)
        }
        if(nums1.head>nums2.last){

        }else if(nums2.head>nums1.last){

        }
        var i = 0
        var j = 0
        val halfLen = (len1 + len2) >> 1
        while (i < len1 && j < len2 && i + j == halfLen) {
            if (nums1(i) <= nums2(j)) i += 1 else j += 1
        }
        if ((len1 & 1) == 0 && (len2 & 1) == 0) (nums1(i) + nums2(j)) / 2d
        else if ((len1 & 1) == 1) nums1(i)
        else nums2(j)
    }

}
