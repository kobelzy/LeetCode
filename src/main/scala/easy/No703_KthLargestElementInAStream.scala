package easy

import java.util.PriorityQueue

/**
  * Auther: Lzy
  * Description:
  * Date Created by： 14:08 on 2019/2/13
  * Modified By：
  */

object No703_KthLargestElementInAStream {
    def main(args: Array[String]): Unit = {
        val k = 3
        val nums = Array(4, 5, 8, 2)
        var obj = new KthLargest2(k, nums)
        println(obj.add(3))
        println(obj.add(5))
        println(obj.add(10))
        println(obj.add(9))
        println(obj.add(4))
    }
}

class KthLargest(_k: Int, _nums: Array[Int]) {
    final val q = new PriorityQueue[Int](_k)
    for (n <- _nums) add(n)

    def add(`val`: Int): Int = {
        if (q.size < _k) {
            q.add(`val`)
        } else if (q.peek() < `val`) {
            q.poll()
            q.add(`val`)
        }
        q.peek()
    }

}

/**
  * 功能实现:使用scala的优先队列实现
  * 有问题没实现
  * Author: Lzy
  * Date: 2019/2/13 14:51
  * Param:
  * Return:
  */
class KthLargest2(_k: Int, _nums: Array[Int]) {
    final val q = scala.collection.mutable.PriorityQueue[Int](_k)
    for (n <- _nums) add(n)

    def add(`val`: Int): Int = {
        if (q.size < _k) {
            q.enqueue(`val`)
        } else if (q.last < `val`) {
            q.dropRight(1)
            q.enqueue(`val`)
        }
        q.last
    }

}