package medium

/**215. 数组中的第K个最大元素
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
        val arr=Array(3,3,3,3,4,3,3,3,3)
        println(findKthLargest(arr,1))
    }
    def findKthLargest(nums: Array[Int], k: Int): Int = {
        rs1(nums,k)
    }


    def rs1(arr:Array[Int],targetIndex:Int): Int = {
        if(arr.length<=2 &&arr.head==arr.last) return arr.head
        val (left,right)=arr.tail.partition(_>=arr.head)
        if(left.length>=targetIndex)
            return rs1(left,targetIndex)
        else
            return rs1(right:+arr.head,targetIndex-left.length)
    }


}
