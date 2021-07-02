package InterviewQuestions;

import java.util.*;

/**
 * 面试题 04.03. 特定深度节点链表
 * 给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。返回一个包含所有深度的链表的数组。
 * 示例：
 * 输入：[1,2,3,4,5,null,7,8]
 *
 *         1
 *        /  \
 *       2    3
 *      / \    \
 *     4   5    7
 *    /
 *   8
 *
 * 输出：[[1],[2,3],[4,5,7],[8]]
 * @author: Patrick Star
 * @time: 2021/7/1 20:38
 */
public class IQ0403_ListOfDepth {

    /**
     * ArrayList代替可变数组的解法，不需要求深度
     *
     * @param tree
     * @return
     */
    public ListNode[] listOfDepth(TreeNode tree) {
        if (tree == null) return new ListNode[0];
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(tree);
        int depth = 0;
        List<ListNode> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            ListNode curLevelPreHead = new ListNode(-1), curListNode = curLevelPreHead;
            for (int i = 0; i < size; i++) {
                TreeNode curTreeNode = queue.poll();
                curListNode.next = new ListNode(curTreeNode.val);
                curListNode = curListNode.next;
                if (curTreeNode.left != null) queue.offer(curTreeNode.left);
                if (curTreeNode.right != null) queue.offer(curTreeNode.right);
            }
            res.add(curLevelPreHead.next);
        }
        //注意这个写法
        return res.toArray(new ListNode[0]);
    }

    /**
     * 使用深度优先获取最大深度；
     *
     * @param tree
     * @return
     */
    public ListNode[] listOfDepth2(TreeNode tree) {
        if (tree == null) return new ListNode[0];
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(tree);
        int depth = 0;
        ListNode[] res = new ListNode[getMaxDepth(tree)];
        while (!queue.isEmpty()) {
            int size = queue.size();
            ListNode curLevelPreHead = new ListNode(-1), curListNode = curLevelPreHead;
            for (int i = 0; i < size; i++) {
                TreeNode curTreeNode = queue.poll();
                curListNode.next = new ListNode(curTreeNode.val);
                curListNode = curListNode.next;
                if (curTreeNode.left != null) queue.offer(curTreeNode.left);
                if (curTreeNode.right != null) queue.offer(curTreeNode.right);
            }
            res[depth++] = curLevelPreHead.next;
        }
        return res;
    }

    private int getMaxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(getMaxDepth(root.left), getMaxDepth(root.right)) + 1;
    }

    private int getMinDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 0;
        return Math.min(getMinDepth(root.left), getMinDepth(root.right)) + 1;
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    ListNode(int x, ListNode next) {
        this.val = x;
        this.next = next;
    }

    @Override
    public String toString() {
        return "{val=" + val + ", next=" + next + '}';
    }
}