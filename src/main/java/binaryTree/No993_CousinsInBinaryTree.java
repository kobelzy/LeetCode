package binaryTree;

import java.util.*;

/**
 * 993. 二叉树的堂兄弟节点
 * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
 * 如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。
 * 我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。
 * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。
 * 示例 1：
 * 输入：root = [1,2,3,4], x = 4, y = 3
 * 输出：false
 * 示例 2：
 * 输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
 * 输出：true
 * 示例 3：
 * 输入：root = [1,2,3,null,4], x = 2, y = 3
 * 输出：false
 * 提示：
 * 二叉树的节点数介于 2 到 100 之间。
 * 每个节点的值都是唯一的、范围为 1 到 100 的整数。
 * @author: Patrick Star
 * @time: 2021/5/17 20:57
 */
public class No993_CousinsInBinaryTree {




    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        Set<Integer> values = new HashSet<>(2);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                int initValuesSize = values.size();
                if (cur.left != null) {
                    queue.offer(cur.left);
                    if (cur.left.val == x || cur.left.val == y) values.add(cur.left.val);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                    if (cur.right.val == x || cur.right.val == y) values.add(cur.right.val);
                }
                //在一个节点加入了两个子节点的值，则是亲兄弟，直接返回
                if (values.size() == initValuesSize + 2) return false;
            }
            if (values.size() == 2) return true;
            values.clear();
        }
        return false;
    }
}
