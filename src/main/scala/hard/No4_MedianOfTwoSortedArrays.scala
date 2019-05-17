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
    println(findMedianSortedArrays(Array(2), Array(1,3,4,5,6))) //4.5
        println(findMedianSortedArrays(Array(1, 3), Array(2, 4))) //2.5
        println(findMedianSortedArrays(Array(1, 3), Array(2))) //2.0
        println(findMedianSortedArrays(Array(1, 3), Array(2))) //2.0
        println(findMedianSortedArrays(Array(1, 3), Array.empty[Int])) //2.0
    }

    def findMedianSortedArrays1(nums1: Array[Int], nums2: Array[Int]): Double = {
        val sorted = (nums1 ++ nums2).sorted
        val len = sorted.length
        val mid = len >> 1
        if ((len & 1) == 0) (sorted(mid - 1) + sorted(mid)) / 2d
        else sorted(mid)
    }

    /**
      * 我竟然一开始写这么复杂。。。。。。，没过。。。
      *
      * @param nums1
      * @param nums2
      * @return
      */
    def findMedianSortedArrays0(nums1: Array[Int], nums2: Array[Int]): Double = {

        val len1 = nums1.length
        val len2 = nums2.length
        val isNum1Odd = (len1 & 1) == 1
        val isNum2Odd = (len2 & 1) == 1
        var i = 0
        var j = 0
        val halfLen = (len1 + len2 + 1) >> 1
        while (i < len1 && j < len2 && i + j < halfLen) {
            if (nums1(i) <= nums2(j)) i += 1 else j += 1
        }
        if (isNum1Odd) nums1(i)
        else if (isNum2Odd) nums2(j)
        else (nums1(i) + nums2(j)) / 2d

    }

    /**
      * 使用快排,但是结果很慢。。。
      *
      * @param nums1
      * @param nums2
      * @return
      */
    def findMedianSortedArrays(nums1: Array[Int], nums2: Array[Int]): Double = {
        val nums = nums1 ++ nums2
        val len = nums.length
        val midNum = (len >> 1) +1
        return if ((len & 1) == 1) {
            findKthLargest(nums, midNum).toDouble
        } else {
            val mid1 = findKthLargest(nums, midNum)
            val mid2 = findKthLargest(nums, midNum - 1)
            (mid1 + mid2) / 2d
        }
    }


    def findKthLargest(nums: Array[Int], k: Int): Int = {
        val head = nums.head
        val (leftAndHead,right)=nums.partition(_>=head)
        val left=leftAndHead.filter(_!=head)
        return  if (k <= left.length)  findKthLargest(left, k)
        else if (k <= leftAndHead.length)  head
        else findKthLargest(right,k-leftAndHead.length)
    }


    /**
      * 灭霸的答案
      *
      * @param nums1
      * @param nums2
      * @return
      */
    def findMedianSortedArrays3(nums1: Array[Int], nums2: Array[Int]): Double = {
        val n1 = nums1.length
        val n2 = nums2.length
        if (n1 > n2)
            return findMedianSortedArrays3(nums2, nums1)

        var left = 0
        var right = n1
        var m1 = 0
        var m2 = 0
        val k = (n1 + n2 + 1) / 2

        while (left < right) {
            m1 = left + (right - left) / 2
            m2 = k - m1

            if (nums1(m1) < nums2(m2 - 1)) {
                left = m1 + 1
            } else {
                right = m1
            }
        }

        m1 = left
        m2 = k - m1

        val c1 = Math.max(
            if (m1 == 0) Integer.MIN_VALUE else nums1(m1 - 1),
            if (m2 == 0) Integer.MIN_VALUE else nums2(m2 - 1)
        )

        if ((n1 + n2) % 2 == 1)
            return c1

        val c2 = Math.min(
            if (m1 == n1) Integer.MAX_VALUE else nums1(m1),
            if (m2 == n2) Integer.MAX_VALUE else nums2(m2)
        )

        return 0.5 * (c1 + c2)
    }

}
