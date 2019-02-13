package hard

import java.util.Comparator

import scala.collection.mutable.ArrayBuffer

/**
  * Auther: Lzy
  * Description:
  * Date Created by： 16:11 on 2019/2/13
  * Modified By：
  */

object No239_SlidingWindowMaximum {
  def main(args: Array[String]): Unit = {
    val nums = Array[Int](1, 3, -1, -3, 5, 3, 6, 7)
    val result = maxSlidingWindow2(nums, 3)
    System.out.println(result.mkString(","))
  }

  def maxSlidingWindow(nums: Array[Int], k: Int): Array[Int] = {
    if (nums.isEmpty) {
      Array.empty[Int]
    } else {
      val result = new Array[Int](nums.length - k + 1)
      val p = new java.util.PriorityQueue[Int](k, new Comparator[Int]() {
        def compare(i1: Int, i2: Int): Int = i2 - i1
      })
      for (i <- 0 until k) {
        p.add(nums(i))
      }
      result(0) = p.peek()
      for (i <- k until nums.length) {
        p.remove(nums(i - k))
        p.add(nums(i))
        result(i - k + 1) = p.peek()
      }
      result
    }
  }


  def maxSlidingWindow2(nums: Array[Int], k: Int): Array[Int] = {
    if (nums.isEmpty) {
      Array.emptyIntArray
    } else {
      val window = scala.collection.mutable.ListBuffer[Int]()
      val res = ArrayBuffer[Int]()
      for (i <- nums.indices) {
        //如果window的头部已经超过了窗口，则将其删除
        if (i >= k && window.head <= i - k) window.remove(0)
        //如果窗口的最后一个索引对应的值没有即将加入的大，则将其删除。因为在新值存在期间无论如何都不可能有该值的出头之日。
        //循环，直到删除掉小于该值的左右索引。这样同时确保了window第一个值永远是最大的
        while (window.nonEmpty && nums(window.last) <= nums(i)) window.remove(window.size-1)
        window += i
        //在第一个窗口打满开始，每次将窗口内的最大值添加到结果集
        if (i >= k - 1) res += nums(window.head)
      }
        res.toArray
    }
  }
}
