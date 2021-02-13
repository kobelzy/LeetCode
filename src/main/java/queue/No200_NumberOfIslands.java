package queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 * 示例 1：
 * 输入：grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * 输出：1
 * 示例 2：
 *
 * 输入：grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * 输出：3
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 */
public class No200_NumberOfIslands {

    /**
     * 基于queue的BFS解法，效率不高，空间利用率不错
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int rowNum = grid.length;
        int columnNum = grid[0].length;
        int islands = 0;
        for (int r = 0; r < rowNum; r++) {
            for (int c = 0; c < columnNum; c++) {
                //从左上到右下
                if (grid[r][c] == '1') {
                    grid[r][c] = '0';
                    islands += 1;
                    Queue<Integer> queue = new LinkedList<>();
                    queue.offer(r * columnNum + c);
                    while (!queue.isEmpty()) {
                        int id = queue.poll();
                        int curRow = id / columnNum;
                        int curColumn = id % columnNum;
                        if (curRow - 1 >= 0 && grid[curRow - 1][curColumn] == '1') {
                            queue.offer((curRow - 1) * columnNum + curColumn);
                            grid[curRow - 1][curColumn] = '0';
                        }
                        if (curRow + 1 < rowNum && grid[curRow + 1][curColumn] == '1') {
                            queue.offer((curRow + 1) * columnNum + curColumn);
                            grid[curRow + 1][curColumn] = '0';
                        }
                        if (curColumn - 1 >= 0 && grid[curRow][curColumn - 1] == '1') {
                            queue.offer(curRow * columnNum + curColumn - 1);
                            grid[curRow][curColumn - 1] = '0';
                        }
                        if (curColumn + 1 < columnNum && grid[curRow][curColumn + 1] == '1') {
                            queue.offer(curRow * columnNum + curColumn + 1);
                            grid[curRow][curColumn + 1] = '0';
                        }
                    }
                }
            }
        }
        return islands;
    }
}
