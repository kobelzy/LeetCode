package medium


/**
  * 验证是否为二叉树
  * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
  *
  * 假设一个二叉搜索树具有如下特征：
  *
  * 节点的左子树只包含小于当前节点的数。
  * 节点的右子树只包含大于当前节点的数。
  * 所有左子树和右子树自身必须也是二叉搜索树。
  * 示例 1:
  *
  * 输入:
  * 2
  * / \
  * 1   3
  * 输出: true
  * 示例 2:
  *
  * 输入:
  * 5
  * / \
  * 1   4
  * / \
  * 3   6
  * 输出: false
  * 解释: 输入为: [5,1,4,null,null,3,6]。
  * 根节点的值为 5 ，但是其右子节点值为 4 。
  * Auther: lzy
  * Description:
  * Date Created by： 9:13 on 2018/11/16
  * Modified By：
  */

object No98_IsValidBST {
    def main(args: Array[String]): Unit = {
        val root=No98_IsValidBST.TreeNode(5)
        val n4=new TreeNode(4)
        root.left=new TreeNode(1)
        root.right=n4
        n4.left=new TreeNode(3)
        n4.right=new TreeNode(6)
        println(No98_IsValidBST.isValidBST(root))
        println(root)
    }
     case class TreeNode(var value: Int,var left: TreeNode = null,var right: TreeNode = null)

    def isValidBST(root: TreeNode): Boolean = {
    isValid(root,None,None)
    }

    def isValid(root: TreeNode, min: Option[Int], max:Option[Int]): Boolean = {
        if (root == null) return true
        if (min.isDefined && root.value <= min.get) return false
        if (max.isDefined && root.value >= max.get) return false
        isValid(root.left, min,Some(root.value)) && isValid(root.right,Some(root.value), max)
    }
}
