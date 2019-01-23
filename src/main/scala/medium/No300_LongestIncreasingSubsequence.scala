package medium


/**
  * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
  *
  * 示例:
  *
  * 输入: [10,9,2,5,3,7,101,18]
  * 输出: 4
  * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
  * 说明:
  *
  * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
  * 你算法的时间复杂度应该为 O(n2) 。
  * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
  *
  * Auther: Lzy
  * Date Created by： 8:53 on 2019/1/21
  */

object No300_LongestIncreasingSubsequence {
    /*动态规划。时间复杂度为O(n^2)。
    状态转移方程：dp[i] = max{dp[j]+1，dp[i]}, j<i&&a[j]<a[i].

    第一步：状态转移方程：dp[i] ：从头->i元素的最长子序列的长度。就是Max(dp[0],dp[1]....dp[i-1])+1  。dp(j)必须小于dp(i)

     */
    def lengthOfLIS(nums: Array[Int]): Int = {
        if (nums.isEmpty) 0
        else {
            var res = 1
            val dp = Array.fill(nums.length + 1)(1)
            for (i <- 1 until nums.length) {
                for (j <- 0 until i) if (nums(j) < nums(i)) dp(i) = math.max(dp(i), dp(j) + 1)
                res = math.max(res, dp(i))
            }
            res
        }
    }

    /**
      * 功能实现:二分法：O(NlogN)
      * Author: Lzy
      * Date: 2019/1/21 9:10
      * Param: [nums]
      * Return:
      */
    def lengthOfLIS2(nums: Array[Int]): Int = {
        import scala.util.control._
        import scala.collection.mutable.ArrayBuffer
        val loop = new Breaks
        val res = ArrayBuffer[Int]()
        if (nums.isEmpty) 0
        else {
            res += (nums(0))
            for (i <- nums.indices) {
                if (nums(i) > res.last) {
                    res += (nums(i))
                } else {
                    loop.breakable(
                        for (j <- res.indices) {
                            if (res(j) >= nums(i)) {
                                res(j) = nums(i);
                                loop.break()
                            }
                        }
                    )
                }
            }
            res.length
        }
    }

    /**
      * 功能实现:贪心法+二分查找
      * Author: Lzy
      * Date: 2019/1/21 9:30
      * Param: [nums]
      * Return:
      */
    def lengthOfLIS3(nums: Array[Int]): Int = {
    var len=nums.length
        if(len<=1) len
        else{
            val tail=Array.fill(len)(0)
            for(i<-1 until len){
                if(nums(i)<tail(0))tail(0)=nums(i)
                else if(nums(i)>tail(len)){
                tail(len)=nums(i)
                len  +=1
                }else tail(biSearch(tail,0,len,nums(i)))=nums(i)

            }
            len+1
        }

    }

    def biSearch(tail:Array[Int],low:Int,high:Int,target:Int)={
        var _low=low
        var _high=high
        while(_low<=_high){
            val mid=(_high+_low)/2
            if(tail(mid)==target) mid
            else if(tail(mid)>target)_high =mid -1
            else _low=mid+1
        }
         _low
    }
}
