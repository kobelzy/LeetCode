package hard

/**
  * Auther: lzy
  * Description:
  * Date Created by： 9:25 on 2018/12/20
  * Modified By：
  */

object No37_SudokuSolverS {
    def main(args: Array[String]): Unit = {
        val board = Array(Array('5', '3', '.', '.', '7', '.', '.', '.', '.'), Array('6', '.', '.', '1', '9', '5', '.', '.', '.'), Array('.', '9', '8', '.', '.', '.', '.', '6', '.'), Array('8', '.', '.', '.', '6', '.', '.', '.', '3'), Array('4', '.', '.', '8', '.', '3', '.', '.', '1'), Array('7', '.', '.', '.', '2', '.', '.', '.', '6'), Array('.', '6', '.', '.', '.', '.', '2', '8', '.'), Array('.', '.', '.', '4', '1', '9', '.', '.', '5'), Array('.', '.', '.', '.', '8', '.', '.', '7', '9'));

    }

    def solveSudoku(board: Array[Array[Char]]): Unit = {
        val row: Array[Array[Boolean]] = Array.ofDim[Boolean](9, 10)
        val col = Array.ofDim[Boolean](9, 10)
        val block = Array.ofDim[Boolean](9, 10)

        for (i <- 0 until 9; j <- 0 until 9) {
            if (board(i)(j) != '.') {
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
        return false
    }
}
