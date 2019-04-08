package medium

/** 801. 使序列递增的最小交换次数
  * 我们有两个长度相等且不为空的整型数组 A 和 B 。
  *
  * 我们可以交换 A[i] 和 B[i] 的元素。注意这两个元素在各自的序列中应该处于相同的位置。
  *
  * 在交换过一些元素之后，数组 A 和 B 都应该是严格递增的（数组严格递增的条件仅为A[0] < A[1] < A[2] < ... < A[A.length - 1]）。
  *
  * 给定数组 A 和 B ，请返回使得两个数组均保持严格递增状态的最小交换次数。假设给定的输入总是有效的。
  *
  * 示例:
  * 输入: A = [1,3,5,4], B = [1,2,3,7]
  * 输出: 1
  * 解释:
  * 交换 A[3] 和 B[3] 后，两个数组如下:
  * A = [1, 3, 5, 7] ， B = [1, 2, 3, 4]
  * 两个数组均为严格递增的。
  * 注意:
  *
  * A, B 两个数组的长度总是相等的，且长度的范围为 [1, 1000]。
  * A[i], B[i] 均为 [0, 2000]区间内的整数。
  */
object No801_MinimumSwapsToMakeSequencesIncreasing {
  /**
    * 在第i位有一下三种情况
    * A(i-1) >=B(i) or B(i-1) >=A(i)
    * A(i-1) >=A(i) or B(i-1) >=B(i)
    * A(i-1) < A(i) or B(i-a) < B(i)
    *
    **/
  def main(args: Array[String]): Unit = {
    val A = Array(1, 3, 5, 4)
    val B = Array(1, 2, 3, 7)
    println(minSwap2(A,B))
  }

  def minSwap(A: Array[Int], B: Array[Int]): Int = {

    val swap = new Array[Int](A.length)
    val notSwap = new Array[Int](A.length)
    swap(0) = 1
    for (i <- 1 until A.length) {
      if (A(i - 1) >= B(i) || B(i - 1) >= A(i)) {
        swap(i) = swap(i - 1) + 1
        notSwap(i) = notSwap(i - 1)
      } else if (A(i - 1) >= A(i) || B(i - 1) >= B(i)) {
        swap(i) = notSwap(i - 1) + 1
        notSwap(i) = swap(i - 1)
      } else if (A(i - 1) < A(i) || B(i - 1) < B(i)) {
        val temp = math.min(swap(i - 1), notSwap(i - 1))
        notSwap(i) = temp
        swap(i) = temp + 1
      }
    }
     math.min(swap.last, notSwap.last)
  }


  def minSwap2(A: Array[Int], B: Array[Int]): Int = {

    var swap = 1
    var notSwap =0
    for (i <- 1 until A.length) {
      if (A(i - 1) >= B(i) || B(i - 1) >= A(i)) {
        swap+=1
      } else if (A(i - 1) >= A(i) || B(i - 1) >= B(i)) {
        val temp=notSwap
        notSwap = swap
        swap = temp + 1
      } else if (A(i - 1) < A(i) || B(i - 1) < B(i)) {
        val temp = math.min(swap, notSwap)
        notSwap= temp
        swap = temp + 1
      }
    }
    math.min(swap, notSwap)
  }
}
