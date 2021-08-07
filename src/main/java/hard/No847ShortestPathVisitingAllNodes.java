package hard;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 847. 访问所有节点的最短路径
 * 存在一个由 n 个节点组成的无向连通图，图中的节点按从 0 到 n - 1 编号。
 * 给你一个数组 graph 表示这个图。其中，graph[i] 是一个列表，由所有与节点 i 直接相连的节点组成。
 * 返回能够访问所有节点的最短路径的长度。你可以在任一节点开始和停止，也可以多次重访节点，并且可以重用边。
 * 示例 1：
 * 输入：graph = [[1,2,3],[0],[0],[0]]
 * 输出：4
 * 解释：一种可能的路径为 [1,0,2,0,3]
 * 示例 2：
 * 输入：graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
 * 输出：4
 * 解释：一种可能的路径为 [0,1,4,2,3]
 * 提示：
 * n == graph.length
 * 1 <= n <= 12
 * 0 <= graph[i].length < n
 * graph[i] 不包含 i
 * 如果 graph[a] 包含 b ，那么 graph[b] 也包含 a
 * 输入的图总是连通图
 */
public class No847ShortestPathVisitingAllNodes {
    public int shortestPathLength(int[][] graph) {
        int len = graph.length;
        Queue<int[]> queue = new LinkedList<>();
        //对每个节点为起始值，都建立一个boolean数组
        int[] visited = new int[len];
        for (int i = 0; i < len; i++) {
            queue.offer(new int[]{i, 1 << i, 0});
            visited[i] |= (1 << i);
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int idx = cur[0], mask = cur[1], dist = cur[2];
            if (mask == ((1 << len) - 1)) return dist;
            for (int val : graph[idx]) {
                int nextMask = mask | (1 << val);

                if ((visited[val] & nextMask) != visited[val]) {
                    queue.offer(new int[]{val, nextMask, dist + 1});
                    visited[val] |= (1 << val);
                }
            }
        }
        return 0;
    }

    public int shortestPathLength2(int[][] graph) {
        int len = graph.length;
        Queue<int[]> queue = new LinkedList<>();
        //对每个节点为起始值，都建立一个boolean数组
        boolean[][] visited = new boolean[len][1 << len];
        for (int i = 0; i < len; i++) {
            queue.offer(new int[]{i, 1 << i, 0});
            visited[i][1 << i] = true;
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int idx = cur[0], mask = cur[1], dist = cur[2];
            if (mask == ((1 << len) - 1)) return dist;
            for (int val : graph[idx]) {
                int nextMask = mask | (1 << val);
                if (!visited[idx][nextMask]) {
                    queue.offer(new int[]{val, nextMask, dist + 1});
                    visited[val][nextMask] = true;
                }
            }
        }
        return 0;
    }
}
