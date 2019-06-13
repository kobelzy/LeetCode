package easy

/**
  * 198. 打家劫舍
  * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
  *
  * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
  *
  * 示例 1:
  *
  * 输入: [1,2,3,1]
  * 输出: 4
  * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
  *      偷窃到的最高金额 = 1 + 3 = 4 。
  * 示例 2:
  *
  * 输入: [2,7,9,3,1]
  * 输出: 12
  * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
  *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
  *
  * 来源：力扣（LeetCode）
  * 链接：https://leetcode-cn.com/problems/house-robber
  * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
  */
object No198 {
  /**
    * 很明显，这是一道动态规划的题目
    * 每一步都可以依赖前2户，或前3户的最佳
    * 是否依赖之前的状态？
    * 当前户+前两户
    * 当前沪+前三户
    * 上一已经计算过了
    * */
  def main(args: Array[String]): Unit = {
    val nums=Array(2,7,9,3,1)
println(rob(nums))
  }
  def rob(nums: Array[Int]): Int = {
    val len=nums.length
    if(len==0) return 0
    if(len==1) return nums.head
    if(len==2) return nums.max
    val dp=new Array[Int](len)
    dp(0)=nums(0)
    dp(1)=math.max(nums(0),nums(1))
    dp(2)=math.max(nums(0)+nums(2),nums(1))
    for(i<-3 until len){
      dp(i)=math.max(dp(i-2)+nums(i),dp(i-3)+nums(i))
    }

    dp.max
  }
}
