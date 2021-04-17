package binaryTree;

import java.util.Stack;

/**
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 * 给定一棵二叉搜索树，请找出其中第k大的节点。
 * 示例 1:
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 4
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 4
 * 限制：
 * 1 ≤ k ≤ 二叉搜索树元素个数
 * 通过次数109,819提交次数146,638
 */
public class No_KthLargestNode {
    public int kthLargest(TreeNode root, int k) {
        int i=1;
        Stack<TreeNode> stack=new Stack<>();
        while(!stack.isEmpty() || root!=null){
            while(root!=null){
                stack.push(root);
                root=root.right;
            }
            if(!stack.isEmpty()){
                TreeNode cur=stack.pop();
                if(i++==k)return cur.val;
                root=cur.left;
            }
        }
        return -1;
    }
}
