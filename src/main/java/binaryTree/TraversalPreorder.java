package binaryTree;

import other.Tree;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * 前中后序遍历
 *
 */
public class TraversalPreorder {
    /*---------------------------------------------前序遍历---------------------------------------------------*/

    /**
     * 前序遍历[优先掌握]
     * 需要先将数据push到队列，需要判断空值
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal0(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            res.add(cur.val);
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
        }
        return res;
    }

    /**
     * 前序遍历
     * 双端队列当作栈使用，不需要判断空值
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
//        Deque<TreeNode> stack = new LinkedList<TreeNode>(); //这是一种双端队列
        Stack<TreeNode> stack=new Stack<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                res.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop().right;
        }
        return res;
    }

    /**
     * 前序遍历，递归
     */
    List<Integer> recursionPreOderRes = new ArrayList<>();

    public List<Integer> preorderTrversalRecursion(TreeNode root) {
        if (root == null) return recursionPreOderRes;
        recursionPreOderRes.add(root.val);
        preorderTrversalRecursion(root.left);
        preorderTrversalRecursion(root.right);
        return recursionPreOderRes;
    }


    /*---------------------------------------------中序遍历---------------------------------------------------*/
    /**
     * 中序遍历：迭代2
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }

    List<Integer> recursionInOrderRes = new ArrayList<>();

    /**
     * 中序遍历：递归
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversalRecusion(TreeNode root) {
        if (root == null) return recursionInOrderRes;
        inorderTraversalRecusion(root.left);
        recursionInOrderRes.add(root.val);
        inorderTraversalRecusion(root.right);
        return recursionInOrderRes;
    }






    /*---------------------------------------------后序遍历---------------------------------------------------*/

    /**
     * 后序遍历1
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> res=new ArrayList<>();
        if(root==null) return res;
        Stack<TreeNode> stack=new Stack<>();
        while(!stack.isEmpty()|| root!=null){
            while(root!=null){
                res.add(root.val);
                stack.push(root);
                root=root.right;
            }
            root=stack.pop().left;
        }
        Collections.reverse(res);
        return res;
    }

    /**
     * 后序遍历2
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            TreeNode node = stack.pop();
            if (node.right == null || pre == node.right){
                result.add(node.val);
                pre = node;
            }
            else{
                stack.push(node);
                root = node.right;
            }
        }
        return result;
    }
}
