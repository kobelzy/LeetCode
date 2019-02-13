package medium

/**
  * Auther: Lzy
  * Description:
  * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
  *
  * 例如，给定三角形：
  *
  * [
  * [2],
  * [3,4],
  * [6,5,7],
  * [4,1,8,3]
  * ]
  * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
  *
  * 说明：
  *
  * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
  * Date Created by： 9:10 on 2019/1/18
  * Modified By：
  */

object No120_Triangle {
    def main(args: Array[String]): Unit = {
        val list=List(List(2),List(3,4),List(6,5,7),List(4,1,8,3))
        println(minimumTotal(list))
    }
    def minimumTotal(triangle: List[List[Int]]): Int = {
        val mini =triangle.last.toArray
        for(i<- triangle.size-2 to (0 , -1)){ //或者是 to 0 by -1
            for(j<- triangle(i).indices){
                mini(j)=triangle(i)(j)+math.min(mini(j),mini(j+1))
            }
        }
        mini(0)
    }
}
