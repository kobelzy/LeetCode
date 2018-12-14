package hard

import java.util.Arrays
import scala.collection.mutable.ListBuffer

/**
  * Auther: lzy
  * Description:
  * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
  * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
  *
  * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
  *
  * 示例:
  *
  * 输入: 4
  * 输出: [
  * [".Q..",  // 解法 1
  * "...Q",
  * "Q...",
  * "..Q."],
  *
  * ["..Q.",  // 解法 2
  * "Q...",
  * "...Q",
  * ".Q.."]
  * ]
  * 解释: 4 皇后问题存在两个不同的解法。
  * Date Created by： 9:14 on 2018/12/13
  * Modified By：
  */

object No51_NQueens {
//一个已经存在的结论是，pei斜线上，x+y都是一个固定值。na斜线上，x-y都是一个固定值
    def main(args: Array[String]): Unit = {
        //        val row=new Array[Char](10)
//        Arrays.fill(row,'.')
        println(solveNQueens(4))
    }

    def solveNQueens(n: Int): List[List[String]] = {
        val res = new ListBuffer[List[String]]()
        helper(0,new Array[Boolean](n),new Array[Boolean](2*n),new Array[Boolean](2*n),new Array[String](n),res)
        res.toList
    }

    /***
     * 功能实现:
     *
     * Author: Lzy
     * Date: 2018/12/14 9:01
     * Param: [r, cols, d1, d2, board, res]
      * r:棋盘深度
      *
      *
      * board：代表棋盘，每一个深度用一个String表示
     * Return: void
     */
    def helper(r: Int, cols: Array[Boolean], d1: Array[Boolean], d2: Array[Boolean], board: Array[String], res: ListBuffer[List[String]]):Unit = {

        if (r == board.size) {
            res += (board.toList)
        } else {
            for (c <- board.indices) {
                //坐标值（c,r）相当于（x,y）坐标

//                (y-x)，na斜线对应的固定值,范围是0-》2*board.size-1
                val id1 = r - c + board.size
                //(x+y),pie斜线对应的固定值，0-》2*board.size-1
                val id2 = 2 * board.size - r - c - 1
                //如果当前位置没有棋子 && na斜线没有被占用  && pie斜线没有被占用
                if (!cols(c) && !d1(id1) && !d2(id2)) {
                    val row = Array.fill(board.size)('.')

                    //为棋子被占用的棋局赋值
                    row(c)='Q'
                    board(r)=new String(row)
                    cols(c)=true
                    d1(id1)=true
                    d2(id2)=true

                    //迭代下一层
                    helper(r+1,cols,d1,d2,board,res)
                    cols(c)=false
                    d1(id1)=false
                    d2(id2)=false
                }
            }
        }


    }

}
