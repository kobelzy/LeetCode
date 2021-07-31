package binaryTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 987. 二叉树的垂序遍历
 * 给你二叉树的根结点 root ，请你设计算法计算二叉树的 垂序遍历 序列。
 * 对位于 (row, col) 的每个结点而言，其左右子结点分别位于 (row + 1, col - 1) 和 (row + 1, col + 1) 。树的根结点位于 (0, 0) 。
 * 二叉树的 垂序遍历 从最左边的列开始直到最右边的列结束，按列索引每一列上的所有结点，形成一个按出现位置从上到下排序的有序列表。如果同行同列上有多个结点，则按结点的值从小到大进行排序。
 * 返回二叉树的 垂序遍历 序列。
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[9],[3,15],[20],[7]]
 * 解释：
 * 列 -1 ：只有结点 9 在此列中。
 * 列  0 ：只有结点 3 和 15 在此列中，按从上到下顺序。
 * 列  1 ：只有结点 20 在此列中。
 * 列  2 ：只有结点 7 在此列中。
 * 示例 2：
 * 输入：root = [1,2,3,4,5,6,7]
 * 输出：[[4],[2],[1,5,6],[3],[7]]
 * 解释：
 * 列 -2 ：只有结点 4 在此列中。
 * 列 -1 ：只有结点 2 在此列中。
 * 列  0 ：结点 1 、5 和 6 都在此列中。
 *           1 在上面，所以它出现在前面。
 *           5 和 6 位置都是 (2, 0) ，所以按值从小到大排序，5 在 6 的前面。
 * 列  1 ：只有结点 3 在此列中。
 * 列  2 ：只有结点 7 在此列中。
 * 示例 3：
 * 输入：root = [1,2,3,4,6,5,7]
 * 输出：[[4],[2],[1,5,6],[3],[7]]
 * 解释：
 * 这个示例实际上与示例 2 完全相同，只是结点 5 和 6 在树中的位置发生了交换。
 * 因为 5 和 6 的位置仍然相同，所以答案保持不变，仍然按值从小到大排序。
 * 提示：
 * 树中结点数目总数在范围 [1, 1000] 内
 * 0 <= Node.val <= 1000
 */
public class No987_VerticalOrderTraversalOfBinaryTree {

    public static void main(String[] args) {
        No987_VerticalOrderTraversalOfBinaryTree t = new No987_VerticalOrderTraversalOfBinaryTree();

    }

    //[列，值]
//    PriorityQueue<int[]> pq=new PriorityQueue<>((v1,v2)-> {
//        int res = v1[0] - v2[0];
//        if(res==0) res=v1[1]-v2[1];
//        if(res==0) res=v1[2]-v2[2];
//        return res;
//    });
    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt((int[] v) -> v[0]).thenComparingInt(v -> v[1]).thenComparingInt(v -> v[2]));

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        int[] info = {0, 0, root.val};
        pq.offer(info);
        bfs(root, info);
        while (!pq.isEmpty()) {
            List<Integer> curCol = new ArrayList<>();
            int[] cur = pq.poll();
            curCol.add(cur[2]);
            while (!pq.isEmpty() && cur[0] == pq.peek()[0]) {
                curCol.add(pq.poll()[2]);
            }
            res.add(curCol);
        }
        return res;
    }

    private void bfs(TreeNode root, int[] info) {
        if (root.left != null) {
            int[] newInfo = {info[0] - 1, info[1] + 1, root.left.val};
            pq.offer(newInfo);
            bfs(root.left, newInfo);
        }
        if (root.right != null) {
            int[] newInfo = {info[0] + 1, info[1] + 1, root.right.val};
            pq.offer(newInfo);
            bfs(root.right, newInfo);
        }
    }


}
