package InterviewQuestions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 面试题 04.01. 节点间通路
 * 节点间通路。给定有向图，设计一个算法，找出两个节点之间是否存在一条路径。
 * 示例1:
 * 输入：n = 3, graph = [[0, 1], [0, 2], [1, 2], [1, 2]], start = 0, target = 2
 * 输出：true
 * 示例2:
 * 输入：n = 5, graph = [[0, 1], [0, 2], [0, 4], [0, 4], [0, 1], [1, 3], [1, 4], [1, 3], [2, 3], [3, 4]], start = 0, target = 4
 * 输出 true
 * 提示：
 * 节点数量n在[0, 1e5]范围内。
 * 节点编号大于等于 0 小于 n。
 * 图中可能存在自环和平行边。
 *
 * @author: Patrick Star
 * @time: 2021/6/28 21:26
 */
public class IQ0401_RouteBetweenNodes {
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        List<Integer>[] adj = new ArrayList[n];
        for (int[] edge : graph) {
            int from = edge[0];
            int to = edge[1];
            if (adj[from] == null) adj[from] = new ArrayList<>();
            adj[from].add(to);
        }
        return hasPath(n,adj,start,target);
    }

    private boolean hasPath(int n, List<Integer>[] adj, int start, int target) {
        Queue<Integer> queue=new LinkedList<>();
        queue.offer(start);
        boolean[] visited=new boolean[n];
        visited[start]=true;
        while(!queue.isEmpty()){
            int size=queue.size();
            for (int i = 0; i < size; i++) {
                int cur=queue.poll();
                List<Integer> nextList=adj[cur];
                if(nextList==null) continue;
                for(Integer next:nextList){
                    if(next==target) return true;
                    if(visited[next]) continue;
                    visited[next]=true;
                    queue.add(next);
                }
            }
        }
        return false;
    }
}
