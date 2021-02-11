package binaryTree;


import scala.actors.threadpool.Arrays;

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
public class No105_ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {
        int[] preOrder=new int[]{3,1,2};
        int[] inOrder=new int[]{1,3,2};
//        int[] preOrder=new int[]{1,2};
//        int[] inOrder=new int[]{1,2};
        No105_ConstructBinaryTreeFromPreorderAndInorderTraversal t=new No105_ConstructBinaryTreeFromPreorderAndInorderTraversal();
        System.out.println(t.buildTree(preOrder,inOrder));

    }
    Map<Integer,Integer> indexMap=new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
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


    private TreeNode getChildTreeNode(int[] preOrder, int[] inOrder) {
        if (preOrder.length==0) return null;

        TreeNode root = new TreeNode(preOrder[0]);
        int inOrderRootIndex =indexOfArray(inOrder,root.val);//[0-rootIndex]是左子树，rootIndex+1-length是右子树的
        if (inOrderRootIndex != 0) {
            int preOderEndIndex = indexOfArray(preOrder,inOrder[inOrderRootIndex - 1]); //可能出现-1的情况
            root.left = getChildTreeNode(Arrays.copyOfRange(preOrder,1, preOderEndIndex + 1), Arrays.copyOfRange(inOrder,0, inOrderRootIndex));
            root.right = getChildTreeNode(Arrays.copyOfRange(preOrder,preOderEndIndex + 1, preOrder.length),Arrays.copyOfRange( inOrder,inOrderRootIndex + 1, inOrder.length));
        }else{
            root.left = getChildTreeNode(Arrays.copyOfRange(preOrder,1, preOrder.length), Arrays.copyOfRange(inOrder,0, inOrder.length));

        }
        return root;
    }
    private int indexOfArray(int[] data,int target){
        for (int i = 0; i < data.length; i++) {
            if(data[i]==target) return i;
        }
        System.out.println(Arrays.toString(data)+","+target);
        return -1;
    }
}
