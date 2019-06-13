package easy

/**
  * 1025. 除数博弈
  * 爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
  *
  * 最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：
  *
  * 选出任一 x，满足 0 < x < N 且 N % x == 0 。
  * 用 N - x 替换黑板上的数字 N 。
  * 如果玩家无法执行这些操作，就会输掉游戏。
  *
  * 只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 false。假设两个玩家都以最佳状态参与游戏。
  *
  *  
  *
  * 示例 1：
  *
  * 输入：2
  * 输出：true
  * 解释：爱丽丝选择 1，鲍勃无法进行操作。
  * 示例 2：
  *
  * 输入：3
  * 输出：false
  * 解释：爱丽丝选择 1，鲍勃也选择 1，然后爱丽丝无法进行操作。
  *  
  *
  * 提示：
  *
  * 1 <= N <= 1000
  *
  * 来源：力扣（LeetCode）
  * 链接：https://leetcode-cn.com/problems/divisor-game
  * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
  */
object No1025 {
  def divisorGame(N: Int): Boolean = {



    false
  }

  /**
    * 最终结果应该是占到2的赢，占到1的输；
    *
    * 若当前为奇数，奇数的约数只能是奇数或者1，因此下一个一定是偶数；
    *
    * 若当前为偶数， 偶数的约数可以是奇数可以是偶数也可以是1，因此直接减1，则下一个是奇数；
    *
    * 因此，奇则输，偶则赢。直接:
    *
    * 作者：pandawakaka
    * 链接：https://leetcode-cn.com/problems/two-sum/solution/python3gui-na-fa-by-pandawakaka/
    * 来源：力扣（LeetCode）
    * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    *
    * @param N
    * @return
    */
  def divisorGame2(N: Int): Boolean = {
    (N&1)==0
  }
}
