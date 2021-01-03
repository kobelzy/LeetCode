package easy;

import java.util.Stack;

/**
 * 538. 把二叉搜索树转换为累加树
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 * 例如：
 * 输入: 原始二叉搜索树:
 *               5
 *             /   \
 *            2     13
 *
 * 输出: 转换为累加树:
 *              18
 *             /   \
 *           20     13
 */
public class No538_ConvertBSTToGreaterTree {
    public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }

    int sum=0;
    public TreeNode convertBST(TreeNode root) {
        /** 特性是：左节点比root小，右节点比跟节点大
         * 总之，必须先遍历右子树，相加到当前节点，再遍历左子树
         * */
        if(root==null) return null;
    convertBST(root.right);
    sum+=root.val;
    root.val=sum;
    convertBST(root.left);
    return root;
    }

    /**
     * 利用栈做迭代
     * 栈中有未遍历节点或者node不为空，就将其右节点压入栈
     * 确保了先走右子树，相当于降序遍历；
     * 之后访问栈顶节点，再考虑其左节点。
     * 当stack为空，且左节点也为null时候，则遍历结束。
     * @param root
     * @return
     */
    public TreeNode convertBST2(TreeNode root) {
        int sum=0;
        TreeNode node =root;
        Stack<TreeNode> stack=new Stack<TreeNode>();
        while(!stack.isEmpty() || node!=null){
            while(node!=null){
                stack.add(node);
                node=node.right;
            }
            node=stack.pop();
            sum+=node.val;
            node.val=sum;
            node=node.left;
        }
        return root;
    }
    }
