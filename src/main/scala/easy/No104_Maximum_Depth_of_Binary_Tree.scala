package easy

/**
  * Auther: lzy
  * Description:
  * 给定一个二叉树，找出其最大深度。
  *
  * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
  *
  * 说明: 叶子节点是指没有子节点的节点。
  *
  * 示例：
  * 给定二叉树 [3,9,20,null,null,15,7]，
  *
  * 3
  * / \
  * 9  20
  * /  \
  * 15   7
  * 返回它的最大深度 3 。
  * Date Created by： 9:11 on 2018/12/10
  * Modified By：
  */

object No104_Maximum_Depth_of_Binary_Tree {

    class TreeNode(var _value: Int) {
        var value: Int = _value
        var left: TreeNode = null
        var right: TreeNode = null
    }

    /** *
      * 功能实现:DFS
      *
      * Author: Lzy
      * Date: 2018/12/10 9:12
      * Param: [root]
      * Return: int
      */
    def maxDepth(root: TreeNode): Int = {
        if (root == null) 0
        else 1 + math.max(maxDepth(root.left), maxDepth(root.right))

        root match {
            case null => 0
            case _ => math.max(maxDepth(root.left), maxDepth(root.right)) + 1
        }
    }


    /** *
      * 功能实现:最小深度
      *
      * Author: Lzy
      * Date: 2018/12/10 9:12
      * Param: [root]
      * Return: int
      */
    def minDepth(root: TreeNode): Int = {
        root match {
            case null =>0
            case _=>{
                val left=minDepth(root.left)
                val right=minDepth(root.right)
                //left+right+1不管哪个为0都可以直接使用，不然还需要单独判断
                if(left ==0 || right ==0) left+right+1 else math.min(left,right)+1
            }
        }
    }
}
