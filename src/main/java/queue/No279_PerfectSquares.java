package queue;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 279. 完全平方数
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 * 示例 1：
 *
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 * 示例 2：
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 * 提示：
 * 1 <= n <= 104
 */
public class No279_PerfectSquares {
    /**
     * 击败80%，内存消耗更多
     * @param n
     * @return
     */
    public int numSquares(int n) {
        Queue<Integer> queue=new LinkedList<>();
        int step=0;
        queue.offer(n);
        while(!queue.isEmpty()){
            step+=1;
            int size=queue.size();
            for (int i = size-1; i >=0 ; i--) {
                int cur=queue.poll();
                int maxSqrt = (int) Math.sqrt(cur);
                if (maxSqrt * maxSqrt == cur) return step;
                for (int j = maxSqrt; j > 0; j--) queue.add(cur - j * j); // 倒序，先遍历大的数据，因为 1 节点永远满足
            }
        }
        return n;
    }


    /**
     * 稍微次一点的解法
     * 使用了visited避免走回头路
     * @param n
     * @return
     */
    public int numSquares2(int n) {
        Queue<Integer> queue=new LinkedList<>();
        Set<Integer> visited=new HashSet<>();
        int step=0;
        queue.offer(0);
        visited.add(0);
        while(!queue.isEmpty()){
            step+=1;
            int size=queue.size();
            for (int i = 0; i < size; i++) {
                int cur=queue.poll();
//                for (int j = 1; j*j+cur <= n; j++) {
//                    int next=j*j+cur;
//                    if(next==n) return step;
//                    if(!visited.contains(next)){
//                        visited.add(next);
//                        queue.offer(next);
//                    }
//                }

                int maxSqrt = (int) Math.sqrt(cur);
                if (maxSqrt * maxSqrt == cur) return step;
                for (int j = maxSqrt; j > 0; i--) queue.add(cur - i * i); // 倒序，先遍历大的数据，因为 1 节点永远满足
            }
        }
        return -1;
    }
}
