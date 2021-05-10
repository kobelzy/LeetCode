package binaryTree;

import java.util.Stack;

/**
 * 872. 叶子相似的树
 * 请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
 * 举个例子，如上图所示，给定一棵叶值序列为 (6, 7, 4, 9, 8) 的树。
 * 如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
 * 如果给定的两个根结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。
 * 示例 1：
 * 输入：root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
 * 输出：true
 * 示例 2：
 * 输入：root1 = [1], root2 = [1]
 * 输出：true
 * 示例 3：
 * 输入：root1 = [1], root2 = [2]
 * 输出：false
 * 示例 4：
 * 输入：root1 = [1,2], root2 = [2,2]
 * 输出：true
 * 示例 5：
 * 输入：root1 = [1,2,3], root2 = [1,3,2]
 * 输出：false
 * 提示：
 * 给定的两棵树可能会有 1 到 200 个结点。
 * 给定的两棵树上的值介于 0 到 200 之间。
 * @description:
 * @author: Patrick Star
 * @time: 2021/5/10 20:50
 */
public class No872_LeafSimilarTrees {

    /**
     * 先序遍历迭代
     *
     * @param root1
     * @param root2
     * @return
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        return preOrder(root1).equals(preOrder(root2));
    }

    public String preOrder(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) return "";
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur.left == null && cur.right == null) sb.append(cur.val);
            if (cur.left != null) stack.push(cur.left);
            if (cur.right != null) stack.push(cur.right);
        }
        return sb.toString();
    }

    /*
    使用队列，按照队列顺序，将数据放到array中
     */

    /**
     * 深度优先，跑不过。。。。
     * @param root1
     * @param root2
     * @return
     */
    public boolean leafSimilar2(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        dfs(sb1, root1);
        dfs(sb2, root2);
        System.out.println(sb1.toString());
        System.out.println(sb2.toString());
        return sb1.toString().equals(sb2.toString());

    }

    private void dfs(StringBuilder sb, TreeNode root) {
        if (root.left == null && root.right == null) sb.append(root.val);
        else {
            if (root.left != null) dfs(sb, root.left);
            if (root.right != null) dfs(sb, root.right);
        }
    }


}



























