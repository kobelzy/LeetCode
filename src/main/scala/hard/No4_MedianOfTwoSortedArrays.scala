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
        println(findMedianSortedArrays0(Array(2), Array(1, 3, 4, 5, 6))) //4.5
        println(findMedianSortedArrays0(Array(1, 3), Array(2, 4))) //2.5
        println(findMedianSortedArrays0(Array(1, 3), Array(2))) //2.0
        println(findMedianSortedArrays0(Array(1, 3), Array(2))) //2.0
        println(findMedianSortedArrays0(Array(1, 3), Array.empty[Int])) //2.0
        var i=100
    }

    /**
      * 最快
      * @param nums1
      * @param nums2
      * @return
      */
    def findMedianSortedArrays1(nums1: Array[Int], nums2: Array[Int]): Double = {
        val sorted = (nums1 ++ nums2).sorted
        val len = sorted.length
        val mid = len >> 1
        if ((len & 1) == 0) (sorted(mid - 1) + sorted(mid)) / 2d
        else sorted(mid)
    }

    /**
      * 这个第二快
      *
      * @param nums1
      * @param nums2
      * @return
      */
    def findMedianSortedArrays0(nums1: Array[Int], nums2: Array[Int]): Double = {
        val len1 = nums1.length
        val len2 = nums2.length
        val len=len1+len2
        val buffer = scala.collection.mutable.ArrayBuffer[Int]()
        var i = 0
        var j = 0
        val halfLen = (len + 1) >> 1
        //如果任意一个数组没有循环完，且加载过的数据没有超过一半（=是为了多加载一个数据，方便偶数个时候使用）
        while ((i < len1 || j < len2) && i+j <= halfLen) {
            if(i==len1){ //如果数组1走完了，那么数组二加入
                buffer += nums2(j)
                j+=1
            }else if(j==len2){//如果数组二走完了，那么数组一加入
                buffer += nums1(i)
                i+=1
            }else if (nums1(i) <= nums2(j)) { //如果数组一的值更小，将其加入
                buffer += nums1(i)
                i+=1
            } else {
                buffer +=nums2(j) //如果数组二的值更小，将其加入
                j+=1
            }
        }
        if((len&1)==0){//偶数个
            (buffer(buffer.length-2)+buffer.last)/2d
        }else{ //奇数个的情况可能出现只有一个值，结果直接将其返回即可
            if(buffer.length==1) buffer.last else   buffer(buffer.length-2)
        }
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
        val midNum = (len >> 1) + 1
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
        val (leftAndHead, right) = nums.partition(_ >= head)
        val left = leftAndHead.filter(_ != head)
        return if (k <= left.length) findKthLargest(left, k)
        else if (k <= leftAndHead.length) head
        else findKthLargest(right, k - leftAndHead.length)
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
