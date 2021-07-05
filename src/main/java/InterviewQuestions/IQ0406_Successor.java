package InterviewQuestions;

import java.util.Stack;

/**
 * 面试题 04.06. 后继者
 * 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
 * 如果指定节点没有对应的“下一个”节点，则返回null。
 * 示例 1:
 * 输入: root = [2,1,3], p = 1
 *   2
 *  / \
 * 1   3
 * 输出: 2
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,null,1], p = 6
 *       5
 *      / \
 *     3   6
 *    / \
 *   2   4
 *  /
 * 1
 * 输出: null
 * @author: Patrick Star
 * @time: 2021/7/5 21:12
 */
public class IQ0406_Successor {
    public static void main(String[] args) {
        TreeNode l1 = new TreeNode(2);
        TreeNode l2 = new TreeNode(1);
        TreeNode l3 = new TreeNode(3);
        l1.left = l2;
        l1.right = l3;
        IQ0406_Successor t = new IQ0406_Successor();
        System.out.println(t.inorderSuccessor(l1, l2));

    }

    /**
     * 二叉搜索树的特性
     *
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        //第一步，找到节点的前置节点，因为中序遍历是左中右
        //左子树的父节点就是它的中序遍历下一个节点
        //右子树的子节点才是它的下一个节点
        TreeNode pre = null;
        while (root != p) {
            if (root.val < p.val) {
                root = root.right;
            } else {
                //注意这里
                pre = root;
                root = root.left;
            }
        }
        //如果右子树不存在，则用它的前一个节点
        if (root.right == null) return pre;

        root = root.right;
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    /**
     * 普通二叉树的迭代解法
     *
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        if (root == null) return null;
        boolean flag = false;
        //中序，左中右
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (flag) return root;
            if (root == p) flag = true;
            root = root.right;
        }
        return null;
    }
}
