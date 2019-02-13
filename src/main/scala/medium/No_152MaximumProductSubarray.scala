package medium

/**
  * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
  *
  * 示例 1:
  *
  * 输入: [2,3,-2,4]
  * 输出: 6
  * 解释: 子数组 [2,3] 有最大乘积 6。
  * 示例 2:
  *
  * 输入: [-2,0,-1]
  * 输出: 0
  * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
  * Date Created by： 9:23 on 2019/1/18
  */

object No_152MaximumProductSubarray {
    def main(args: Array[String]): Unit = {
        val nums = Array(2, 3, -2, 4)
        println(maxProduct(nums))
    }

    def maxProduct(nums: Array[Int]): Int = {
        if (nums.isEmpty) {
            0
        } else {
//            val dp = Array.fill[Int](2, 2)(nums(0))
            val dp = Array.ofDim[Int](2, 2)
            var res = nums(0)
            dp(0)(1) = nums(0)
            dp(0)(0) = nums(0)
            for (i <- 1 until nums.length) {
                val (x, y) = (i % 2, (i-1) % 2)
                dp(x)(0) = math.max(math.max(dp(y)(0) * nums(i), dp(y)(1) * nums(i)), nums(i))
                dp(x)(1) = math.min(math.min(dp(y)(0) * nums(i), dp(y)(1) * nums(i)), nums(i))
                res = math.max(res, dp(x)(0))
            }
            res
        }
    }

    def maxProduct2(nums: Array[Int]): Int = {
        if (nums.isEmpty)
            return 0

        var res = nums(0)
        var max = nums(0)
        var min = nums(0)
        for (i <- 1 until nums.length) {
            val n = nums(i)
            val tem = (n * max).min(n * min).min(n)
            max = (n * max).max(n * min).max(n)
            if (tem < 0) {
                min = tem
            } else {
                min = max
            }

            res = res.max(max).max(min)
        }
        res
    }
}
