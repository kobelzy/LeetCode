package medium;

  class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }

public class No236_LowestCommonAncestorofaBinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        /*找1，3
                4
             /   \
            2     6
           / \    / \
          1 3    4   7
         */
        if(root == null || root ==p || root ==q){
            return root;
        }
        TreeNode left=lowestCommonAncestor(root.left,p,q);
        TreeNode right=lowestCommonAncestor(root.right,p,q);
        return left ==null ? right :right ==null ? left:root;
    }
}