package binaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 112. 路径总和
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例: 
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 *
 */
public class No112_PathSum {

    /**
     * 迭代
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
    if(root==null) return false;
    Queue<TreeNode> treeQ=new LinkedList<>();
    Queue<Integer> valQ=new LinkedList<>();
    treeQ.offer(root);
    valQ.offer(root.val);
    while(!treeQ.isEmpty()){
        TreeNode curNode=treeQ.poll();
        int curVal=valQ.poll();
        if(curNode.left==null && curNode.right==null){
            if(curVal==sum) return true;
            continue;
        }
        if(curNode.left!=null){
            treeQ.offer(curNode.left);
            valQ.offer(curNode.left.val+curVal);
        }
        if(curNode.right!=null){
            treeQ.offer(curNode.left);
            valQ.offer(curNode.right.val+curVal);
        }
    }
    return false;
    }


    /**
     * 递归
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum2(TreeNode root,int sum){
        if(root==null) return false;
        if(root.left==null && root.right==null) return sum==root.val;
        return hasPathSum2(root.left,sum-root.val) ||
                hasPathSum2(root.right,sum-root.val);
    }
}
