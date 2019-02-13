package easy

/**
  * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
  *
  * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
  *
  * 注意你不能在买入股票前卖出股票。
  *
  * 示例 1:
  *
  * 输入: [7,1,5,3,6,4]
  * 输出: 5
  * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
  * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
  * 示例 2:
  *
  * 输入: [7,6,4,3,1]
  * 输出: 0
  * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
  * Date Created by： 9:49 on 2019/1/18
  */

object No121_BestTimetoBuyandSellStock {
    def maxProfit(prices: Array[Int]): Int = {
        if (prices.isEmpty) {
            0
        } else {
            var res = 0
            val profit = Array.ofDim[Int](prices.length, 3)
            profit(0)(1) = -prices(0)
            for (i <- 1 until prices.length) {
                profit(i)(0) = profit(i - 1)(0)
                profit(i)(1) = math.max(profit(i - 1)(1), profit(i - 1)(0) - prices(i))
                profit(i)(2) = profit(i - 1)(1) + prices(i)
                res = Array(res, profit(i)(0), profit(i)(1), profit(i)(2)).max
            }
            res
        }
    }
}
