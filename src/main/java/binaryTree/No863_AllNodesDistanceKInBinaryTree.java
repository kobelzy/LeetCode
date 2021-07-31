package binaryTree;


import java.util.*;

/**
 * 863. 二叉树中所有距离为 K 的结点
 * 给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。
 * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
 * 示例 1：
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 * 输出：[7,4,1]
 * 解释：
 * 所求结点为与目标结点（值为 5）距离为 2 的结点，
 * 值分别为 7，4，以及 1
 * 注意，输入的 "root" 和 "target" 实际上是树上的结点。
 * 上面的输入仅仅是对这些对象进行了序列化描述。
 * 提示：
 * 给定的树是非空的。
 * 树上的每个结点都具有唯一的值 0 <= node.val <= 500 。
 * 目标结点 target 是树上的结点。
 * 0 <= K <= 1000.
 */
public class No863_AllNodesDistanceKInBinaryTree {
    Map<TreeNode, TreeNode> map = new HashMap<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        bfs(root);
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();
        Set<TreeNode> visited = new HashSet<>();
        int distance = 0;
        queue.offer(target);
        visited.add(target);
        while (!queue.isEmpty() || distance < k) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (distance == k) ans.add(cur.val);
                if (cur.left != null && !visited.contains(cur.left)) {
                    queue.offer(cur.left);
                    visited.add(cur.left);
                }
                if (cur.right != null && !visited.contains(cur.right)) {
                    queue.offer(cur.right);
                    visited.add(cur.right);
                }
                if (map.containsKey(cur)) {
                    TreeNode parent = map.get(cur);
                    if (!visited.contains(parent)) {
                        queue.offer(parent);
                        visited.add(parent);
                    }
                }
            }
            distance++;
        }
        return ans;
    }

    public void bfs(TreeNode root) {
        if (root == null) return;
        if (root.right != null) {
            map.put(root.right, root);
            bfs(root.right);
        }
        if (root.left != null) {
            map.put(root.left, root);
            bfs(root.left);
        }
    }

}
