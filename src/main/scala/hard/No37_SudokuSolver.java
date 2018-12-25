package hard;/**
 * Auther: lzy
 * Description:
 * Date Created by： 8:55 on 2018/12/18
 * Modified By：
 */

import java.util.Arrays;

/**
 * @program: LeetCode
 * @description: 编写一个程序，通过已填充的空格来解决数独问题。
 * <p>
 * 一个数独的解法需遵循如下规则：
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 * @author: Lzy
 * @create: 2018-12-18 08:55
 **/
public class No37_SudokuSolver {
    int count = 0;

    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        No37_SudokuSolver a = new No37_SudokuSolver();
        a.solveSudoku(board);
        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }

    /***
     * 功能实现:解法1
     *
     * Author: Lzy
     * Date: 2018/12/20 9:21
     * Param: [board]
     * Return: void
     */
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        solve(board);
        System.out.println("总迭代次数：" + count);
    }

    public boolean solve(char[][] board) {
        //如果所有格子都不为空，那么就直接返回true
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                count++;
                if (board[i][j] == '.') {
                    //如果该格子为空，需要判断一下该格子填入1-9的值是否满足三定律；格子不为空，则直接跳出，继续下一个循环
                    for (char c = '1'; c <= '9'; c++) {
                        System.out.println(c);
                        //如果该格子填入c，是否满则三定律
                        if (isValid(board, i, j, c)) {
                            System.out.println("进去");

                            //满足，则填入c
                            board[i][j] = c;
                            //更新棋盘，继续递归，
                            if (solve(board)) {
                                return true;
                            } else {
                                board[i][j] = '.';
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /***
     * 功能实现:
     *如果该格子填入c，且满足三定律，则返回true，否则返回false
     * Date: 2018/12/18 9:22
     */
    public boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            //对col列的格子判断，不为空，且为c，那么返回false
            if (board[i][col] != '.' && board[i][col] == c) {
                return false;
            }
            //对row列的格子判断
            if (board[row][i] != '.' && board[row][i] == c) {
                return false;
            }
            //对该格子对应的九宫格进行判断，，不为空且为c则返回false
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] != '.' && board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) {
                return false;
            }
        }
        return true;
    }


    /***
     * 功能实现:
     *解法2：java递归回溯，速度快3倍
     * Author: Lzy
     * Date: 2018/12/20 9:22
     * Param: [board]
     * Return: void
     */
    public void solveSudoku2(char[][] board) {
        /**
         * 记录某行，某位数字是否已经被摆放
         */
        boolean[][] row = new boolean[9][10];
        /**
         * 记录某列，某位数字是否已经被摆放
         */
        boolean[][] col = new boolean[9][10];
        /**
         * 记录某 3x3 宫格内，某位数字是否已经被摆放
         */
        boolean[][] block = new boolean[9][10];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    row[i][num] = true;
                    col[j][num] = true;
                    // blockIndex = i / 3 * 3 + j / 3，取整
                    block[i / 3 * 3 + j / 3][num] = true;
                }
            }
        }
        dfs(board, row, col, block, 0, 0);
    }

    private boolean dfs(char[][] board, boolean[][] row, boolean[][] col, boolean[][] block, int i, int j) {
        // 找寻空位置
        while (board[i][j] != '.') {
            if (++j >= 9) {
                i++;
                j = 0;
            }
            if (i >= 9) {
                return true;
            }
        }
        for (int num = 1; num <= 9; num++) {
            int blockIndex = i / 3 * 3 + j / 3;
            if (!row[i][num] && !col[j][num] && !block[blockIndex][num]) {
                // 递归
                board[i][j] = (char) ('0' + num);
                row[i][num] = true;
                col[j][num] = true;
                block[blockIndex][num] = true;
                if (dfs(board, row, col, block, i, j)) {
                    return true;
                } else {
                    // 回溯
                    row[i][num] = false;
                    col[j][num] = false;
                    block[blockIndex][num] = false;
                    board[i][j] = '.';
                }
            }
        }
        return false;
    }

    private void printBoard(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
