package hard

import java.util.Comparator

/**
  * Auther: Lzy
  * Description:
  * Date Created by： 16:11 on 2019/2/13
  * Modified By：
  */

object No239_SlidingWindowMaximum {
    def main(args: Array[String]): Unit = {
        val nums = Array[Int](1, 3, -1, -3, 5, 3, 6, 7)
        val a = new No239_SlidingWindowMaximumJ
        val result = a.maxSlidingWindow(nums, 3)
        System.out.println(result.mkString(","))
    }
    def maxSlidingWindow(nums: Array[Int], k: Int): Array[Int] = {
        if (nums.isEmpty) {
            Array.empty[Int]
        } else {
            val result = new Array[Int](nums.length - k + 1)
            val p = new java.util.PriorityQueue[Int](k,new Comparator[Int]() {
                def compare(i1: Int, i2: Int): Int = i2 - i1
            })
            for (i <- 0 until k) {
                p.add(nums(i));
            }
            result(0) = p.peek()
            for (i <- k until nums.length) {
                p.remove(nums(i - k))
                p.add(nums(i))
                result(i - k + 1) = p.peek();
            }
            result
        }
    }
}
