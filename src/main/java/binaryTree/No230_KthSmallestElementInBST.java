package binaryTree;

import java.util.Stack;

/**
 * 230. 二叉搜索树中第K小的元素
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 * 示例 1：
 * 输入：root = [3,1,4,null,2], k = 1
 * 输出：1
 * 示例 2：
 * 输入：root = [5,3,6,2,4,null,null,1], k = 3
 * 输出：3
 * 提示：
 * 树中的节点数为 n 。
 * 1 <= k <= n <= 104
 * 0 <= Node.val <= 104
 */
public class No230_KthSmallestElementInBST {
    public int kthSmallest(TreeNode root, int k) {
        int i=1;
        Stack<TreeNode> stack=new Stack<>();
        while(!stack.isEmpty() || root!=null){
            while(root!=null){
                stack.push(root);
                root=root.left;
            }
            if(!stack.isEmpty()){
                TreeNode cur=stack.pop();
                if(i++==k)return cur.val;
                root=cur.right;
            }
        }
        return -1;
    }
}
