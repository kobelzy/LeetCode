package easy

import scala.util.control._

/**
  * Created by Administrator on 2017/11/9.
  */
object No1_TwoSum {
    val loop = new Breaks

    def twoSum(nums: Array[Int], target: Int): Array[Int] = {
        val map = scala.collection.mutable.Map[Int, Int]()
        for (i <- nums.indices) {
            map(nums(i)) = i
        }
        val result = scala.collection.mutable.Buffer[Int]()
        loop.breakable {
            for (i <- nums.indices) {
                val complement = target - nums(i)
                if (map.contains(complement) && map(complement) != i) {
                    result += i
                    result += map(complement)
                    loop.break()
                }
            }
        }
        result.toArray
    }

    /**
      *这个是最慢的
      * @param nums
      * @param target
      * @return
      */
    def twoSum2(nums: Array[Int], target: Int): Array[Int] = {
        val result = new Array[Int](2)
        for (i <- nums.indices) {
            val sub = target - nums(i)
            if (nums.contains(sub) && nums.indexOf(sub) != i) {
                result(0) = i
                result(1) = nums.indexOf(sub)
            }
        }
        result
    }

    /**
      * 这个是最快的
      * @param nums
      * @param target
      * @return
      */
    def twoSum3(nums: Array[Int], target: Int): Array[Int] = {
        val map = scala.collection.mutable.Map[Int, Int]()
        for (i <- nums.indices) {
            if (map.contains(target - nums(i))) return Array(map(target - nums(i)), i)
            map += (nums(i) -> i)
        }
        Array[Int]()
    }


        def main(args: Array[String]): Unit = {
        println(twoSum(Array(3, 2, 4), 6).mkString(","))
    }
}
