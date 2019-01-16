package easy

/** 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
  * *
  * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
  * *
  * 注意：给定 n 是一个正整数。
  * *
  * 示例 1：
  * *
  * 输入： 2
  * 输出： 2
  * 解释： 有两种方法可以爬到楼顶。
  *1.  1 阶 + 1 阶
  *2.  2 阶
  * 示例 2：
  * *
  * 输入： 3
  * 输出： 3
  * 解释： 有三种方法可以爬到楼顶。
  *1.  1 阶 + 1 阶 + 1 阶
  *2.  1 阶 + 2 阶
  *3.  2 阶 + 1 阶
  * Auther: Lzy
  * Description:
  * Date Created by： 9:19 on 2019/1/15
  * Modified By：
  */

object No70_ClimbingStairs {
    def main(args: Array[String]): Unit = {
//        for(i<-1 to 5) println(i)
        for(i<-5 to 1 by -1) println(i)
    }
    def climbStairs(n: Int): Int = {
        if(n<=2) n
        else {
            var oneStepBefore=2
            var twoStepBefore=1
            var allWays=0
            for(i <- 2 until n){
               allWays=oneStepBefore+twoStepBefore
                twoStepBefore=oneStepBefore
                oneStepBefore=allWays
            }
            allWays
        }
    }
}
