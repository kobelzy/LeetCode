package medium


/**
  * Auther: lzy
  * Description:
  * Date Created by： 9:13 on 2018/11/16
  * Modified By：
  */

object No98_IsValidBST {

    class TreeNode(var _value: Int) {
        var value: Int = _value
        var left: TreeNode = null
        var right: TreeNode = null
    }

    def isValidBST(root: TreeNode): Boolean = {
        isValid(root,root.left.value,root.right.value)


    }
//    def helper(root:TreeNode)={
//        if(root.value==null){
//            true
//        }
//        if(!helper(root.left)){
//            false
//        }
//        if()
//
//
//            true
//    }

    def isValid(root: TreeNode, min: Int, max: Int): Boolean = {
        if (root == null) return true
        if (min != null && root.value <= min) return false
        if (max != null && root.value >= max) return false
        isValid(root.left, min, root.value) && isValid(root.right, root.value, max)
    }
}
