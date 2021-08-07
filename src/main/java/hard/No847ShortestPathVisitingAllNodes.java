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
    public static void main(String[] args) {
        No847ShortestPathVisitingAllNodes t=new No847ShortestPathVisitingAllNodes();
        int[] test=new int[]{0,0};
        test[0]|=(1<<2);
//        System.out.println(Arrays.toString(test));
//        System.out.println(Integer.toBinaryString(test[0]));
//        int[][] graph=new int[][]{{1,2,3},{0},{0},{0}};
//        int[][] graph=new int[][]{{2,3,5,7},{2,3,7},{0,1},{0,1},{7},{0},{10},{9,10,0,1,4},{9},{7,8},{7,6}};
        int[][] graph=new int[][]{{1},{0,2,4},{1,3,4},{2},{1,2}};
        System.out.println(t.shortestPathLength(graph));
    }


    public int shortestPathLength(int[][] graph) {
        int n = graph.length;

        // 1.初始化队列及标记数组，存入起点
        // 三个属性分别为 idx, mask, dist,u 表示当前位于的节点编号；
        // mask 是一个长度为 nn 的二进制数，表示每一个节点是否经过。如果 \textit{mask}mask 的第 ii 位是 11，则表示节点 ii 已经过，否则表示节点 ii 未经过；
        //dist 表示到当前节点为止经过的路径长度。
        Queue<int[]> queue = new LinkedList<int[]>();
        boolean[][] vis = new boolean[n][1 << n]; // 节点编号及当前状态
        for (int i = 0; i < n; i++) {
            queue.offer(new int[]{i, 1 << i, 0}); // 存入起点，起始距离0，标记
            vis[i][1 << i] = true;
        }
        // 开始搜索
        while (!queue.isEmpty()) {
            int[] tuple = queue.poll(); // 弹出队头元素
            int idx = tuple[0], mask = tuple[1], dist = tuple[2];
            // 找到答案，返回结果
            if (mask == (1 << n) - 1) return dist;
            // 扩展
            for (int x : graph[idx]) {
                int next_mask = mask | (1 << x);
                if (!vis[x][next_mask]) {
                    queue.offer(new int[]{x, next_mask, dist + 1});
                    vis[x][next_mask] = true;
                }
            }
        }
        return 0;
    }


    /**
     * 压缩状态直接使用int值表示二进制
     * 解法有些问题，复杂度比较高时，会卡死
     * @param graph
     * @return
     */
    public int shortestPathLength2(int[][] graph) {
        int len = graph.length;
        Queue<int[]> queue = new LinkedList<>();
        //对每个节点为起始值，都建立一个boolean数组
        int[] visited = new int[len];
        for (int i = 0; i < len; i++) {
            queue.offer(new int[]{i, 1 << i, 0});
            visited[i] =(1 << i);//用n位二进制表示是否存在
        }
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int idx = cur[0], mask = cur[1], dist = cur[2];
            //mask的所有位都是1，表示都访问过了，可以返回
            if (mask == ((1 << len) - 1)) return dist;
            for (int next : graph[idx]) {
                //下一个需要访问的位置
                int nextMask = mask | (1 << next);
                //如果二进制中不存在该值，则说明没有访问过
                if ((visited[next] | nextMask) != visited[next]) {
                    queue.offer(new int[]{next, nextMask, dist + 1});
                    visited[next] |= (1 << next);
                }
            }
        }
        return 0;
    }



}
