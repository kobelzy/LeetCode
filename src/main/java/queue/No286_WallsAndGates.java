package queue;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 286. 墙与门
 * 你被给定一个 m × n 的二维网格，网格中有以下三种可能的初始化值：
 * -1 表示墙或是障碍物
 * 0 表示一扇门
 * INF 无限表示一个空的房间。然后，我们用 231 - 1 = 2147483647 代表 INF。你可以认为通往门的距离总是小于 2147483647 的。
 * 你要给每个空房间位上填上该房间到 最近 门的距离，如果无法到达门，则填 INF 即可。
 * <p>
 * 示例：
 * 给定二维网格：
 * INF  -1  0  INF
 * INF INF INF  -1
 * INF  -1 INF  -1
 * 0  -1 INF INF
 * 运行完你的函数后，该网格应该变成：
 * 3  -1   0   1
 * 2   2   1  -1
 * 1  -1   2  -1
 * 0  -1   3   4
 */
public class No286_WallsAndGates {
    int rows;
    int cols;

    public void wallsAndGates(int[][] rooms) {
        rows = rooms.length;
        cols = rooms[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (rooms[i][j] == 0) bfs(rooms, i, j);
            }
        }

    }

    private void bfs(int[][] rooms, int i, int j) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i * cols + j);
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int x = 0; x < size; x++) {
                int id = queue.poll();
                int row = id / cols;
                int col = id % cols;

                rooms[row][col] = Math.max(rooms[row][col], step);
                int down = (row + 1) * cols + col;
                int up = (row - 1) * cols + col;
                int right = row * cols + col + 1;
                int left = row * cols + col - 1;

                if ((row + 1) < rows && rooms[row + 1][col] > 0 && !visited.contains(down)) {
                    queue.offer(down);
                    visited.add(down);
                }

                if ((row - 1) >= 0 && rooms[row - 1][col] > 0 && !visited.contains(up)) {
                    queue.offer(up);
                    visited.add(up);
                }

                if ((col - 1) >= 0 && rooms[row][col - 1] > 0 && !visited.contains(left)) {
                    queue.offer(left);
                    visited.add(left);
                }

                if ((col + 1) < cols && rooms[row][col + 1] > 0 && !visited.contains(right)) {
                    queue.offer(right);
                    visited.add(right);
                }

            }
            step += 1;
        }
    }
}
