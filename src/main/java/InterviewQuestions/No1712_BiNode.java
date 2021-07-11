package InterviewQuestions;

import java.util.Stack;

/**
 * 面试题 17.12. BiNode
 * 二叉树数据结构TreeNode可用来表示单向链表（其中left置空，right为下一个链表节点）。实现一个方法，把二叉搜索树转换为单向链表，要求依然符合二叉搜索树的性质，转换操作应是原址的，也就是在原始的二叉搜索树上直接修改。
 * 返回转换后的单向链表的头节点。
 * 注意：本题相对原题稍作改动
 * 示例：
 * 输入： [4,2,5,1,3,null,6,0]
 * 输出： [0,null,1,null,2,null,3,null,4,null,5,null,6]
 * 提示：
 * 节点数量不会超过 100000。
 *
 * @author: Patrick Star
 * @time: 2021/7/11 20:02
 */
public class No1712_BiNode {
    public static void main(String[] args) {
        TreeNode l2 = new TreeNode(2);
        TreeNode l1 = new TreeNode(1);
        TreeNode l3 = new TreeNode(3);
        l2.left = l1;
        l2.right = l3;
        No1712_BiNode t = new No1712_BiNode();
        System.out.println(l2);
        System.out.println(t.convertBiNode(l2));
    }

    public TreeNode convertBiNode(TreeNode root) {
        if (root == null) return null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode head = new TreeNode(-1), pre = head;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();

            // pre.left = null; 这句不对，会导致环
            root.left = null;
            pre.right = root;
            pre = root;
            root = root.right;
        }
        return head.right;
    }

}
