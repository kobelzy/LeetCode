package hard

/**
  * 37 解数独
  * 一个数独的解法需遵循如下规则：
  *
  * 数字 1-9 在每一行只能出现一次。
  * 数字 1-9 在每一列只能出现一次。
  * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
  * 空白格用 '.' 表示。
  *
  *
  *
  * 一个数独。
  *
  *
  *
  * 答案被标成红色。
  *
  * Note:
  *
  * 给定的数独序列只包含数字 1-9 和字符 '.' 。
  * 你可以假设给定的数独只有唯一解。
  * 给定数独永远是 9x9 形式的。
  * Auther: lzy
  * Description:
  * Date Created by： 9:25 on 2018/12/20
  * Modified By：
  */

object No37_SudokuSolver {
    def main(args: Array[String]): Unit = {
//
        //     val board = Array(Array('5', '3', '.', '.', '7', '.', '.', '.', '.'), Array('6', '.', '.', '1', '9', '5', '.', '.', '.'), Array('.', '9', '8', '.', '.', '.', '.', '6', '.'), Array('8', '.', '.', '.', '6', '.', '.', '.', '3'), Array('4', '.', '.', '8', '.', '3', '.', '.', '1'), Array('7', '.', '.', '.', '2', '.', '.', '.', '6'), Array('.', '6', '.', '.', '.', '.', '2', '8', '.'), Array('.', '.', '.', '4', '1', '9', '.', '.', '5'), Array('.', '.', '.', '.', '8', '.', '.', '7', '9'));
        println('i'-'0')
        println('5'.toInt)
    }

    def solveSudoku(board: Array[Array[Char]]): Unit = {
        //行的二维数组,i是第几行，j是该值下标对应的数组是否被占用
        val row: Array[Array[Boolean]] = Array.ofDim[Boolean](9, 10)
        //列的二维数组
        val col = Array.ofDim[Boolean](9, 10)
        //九宫格的二维数组
        val block = Array.ofDim[Boolean](9, 10)

        for (i <- 0 until 9; j <- 0 until 9) {
            if (board(i)(j) != '.') {
                //该区域已有的数值，-'0'是为了转换为数值
                val num = board(i)(j) - '0'
                row(i)(num) = true
                row(j)(num) = true
                block(i / 3 * 3 + j / 3)(num) = true
            }
        }
        dfs(board, row, col, block, 0, 0)
    }

    def dfs(board: Array[Array[Char]], row: Array[Array[Boolean]], col: Array[Array[Boolean]], block: Array[Array[Boolean]], i: Int, j: Int): Boolean = {
        var index_i = i
        var index_j = j
        while (board(i)(j) != '.') {
            index_j += 1
            if (index_j >= 9) {
                index_i += 1
                index_j = 0
            }
            if (index_i >= 9) return true
        }
        for (num <- 1 to 9) {
            val blockIndex = i / 3 * 3 + j / 3
            if (!row(i)(num) && !col(j)(num) && !block(blockIndex)(num)) {
                board(i)(j) = ('0' + num).toChar
                row(i)(num) = true
                col(j)(num) = true
                block(blockIndex)(num) = true
                if (dfs(board, row, col, block, i, j)) {
                    return true
                } else {
                    row(i)(num) = false
                    col(j)(num) = false
                    block(blockIndex)(num) = false
                    board(i)(j) = '.'
                }
            }
        }
         false
    }
}
