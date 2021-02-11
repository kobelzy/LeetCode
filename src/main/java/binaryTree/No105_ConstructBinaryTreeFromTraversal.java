package binaryTree;


import java.util.HashMap;
import java.util.Map;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class No105_ConstructBinaryTreeFromTraversal {
    public static void main(String[] args) {
        int[] preOrder=new int[]{3,1,2};
        int[] inOrder=new int[]{1,3,2};
        int[] postOrder=new int[]{1,2,3};
//        int[] preOrder=new int[]{1,2};
//        int[] inOrder=new int[]{1,2};
        No105_ConstructBinaryTreeFromTraversal t=new No105_ConstructBinaryTreeFromTraversal();
//        System.out.println(t.constructBinaryTreeFromPreorderAndInorderTraversal(preOrder,inOrder));
        System.out.println(t.constructBinaryTreeFromInorderAndPostorderTraversal(inOrder,postOrder));

    }
    Map<Integer,Integer> indexMap=new HashMap<>();

    /**
     * 105. 从前序与中序遍历序列构造二叉树
     * 根据一棵树的前序遍历与中序遍历构造二叉树。
     *
     * 注意:
     * 你可以假设树中没有重复的元素。
     *
     * 例如，给出
     *
     * 前序遍历 preorder = [3,9,20,15,7]
     * 中序遍历 inorder = [9,3,15,20,7]
     * 返回如下的二叉树：
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     */
    public TreeNode constructBinaryTreeFromPreorderAndInorderTraversal(int[] preorder, int[] inorder) {
        int size=preorder.length;
        for (int i = 0; i < size; i++) {
            indexMap.put(inorder[i],i);
        }

      return   getChildTreeNode(preorder,0,size-1,inorder,0,size-1);
    }
    private TreeNode getChildTreeNode(int[] preorder,int preLeft,int preRight, int[] inorder,int inLeft,int inRight) {
        if(preLeft>preRight) return null;
        int rootVal=preorder[preLeft];
        TreeNode root=new TreeNode(rootVal);
        int inorderRootIndex=indexMap.get(rootVal);
        int leftTreeSize=inorderRootIndex-inLeft;//左子树的数量
        root.left=getChildTreeNode(preorder,preLeft+1,leftTreeSize+preLeft,inorder,inLeft,inorderRootIndex-1);
        root.right=getChildTreeNode(preorder,leftTreeSize+preLeft+1,preRight,inorder,inorderRootIndex+1,inRight);
        return root;
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     * 根据一棵树的中序遍历与后序遍历构造二叉树。
     *
     * 注意:
     * 你可以假设树中没有重复的元素。
     *
     * 例如，给出
     *
     * 中序遍历 inorder = [9,3,15,20,7]
     * 后序遍历 postorder = [9,15,7,20,3]
     * 返回如下的二叉树：
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * @param inorder
     * @param postorder
     * @return
     */
        public TreeNode constructBinaryTreeFromInorderAndPostorderTraversal(int[] inorder,int[] postorder){
        int size=inorder.length;
            for (int i = 0; i < size; i++) {
                indexMap.put(inorder[i],i);
            }
            return getTreeNode(postorder,0,size-1, inorder,0 ,size-1 );
        }

    private TreeNode getTreeNode(int[] postorder,int postLeft,int postRight, int[] inorder,int inLeft,int inRight) {
        if(postLeft>postRight) return null;
        int rootVal=postorder[postRight];
        TreeNode root=new TreeNode(rootVal);
        int inorderRootIndex=indexMap.get(rootVal);
        int leftTreeSize=inorderRootIndex-inLeft;//左子树的数量
        root.left=getTreeNode(postorder,postLeft,leftTreeSize+postLeft-1,inorder,inLeft,inorderRootIndex-1);
        root.right=getTreeNode(postorder,leftTreeSize+postLeft,postRight-1,inorder,inorderRootIndex+1,inRight);
        return root;
    }
}
