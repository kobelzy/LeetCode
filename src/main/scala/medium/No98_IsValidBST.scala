package medium


/**
  * Auther: lzy
  * Description:
  * Date Created by： 9:13 on 2018/11/16
  * Modified By：
  */

object No98_IsValidBST {
    def main(args: Array[String]): Unit = {
//        val root=new TreeNode(10)
//        println(isValidBST(root))
//        var a:Int=10
//        a=null
//        println(a)
    }
    class TreeNode(var _value: Int) {
        var value: Int = _value
        var left: Option[TreeNode] = None
        var right: Option[TreeNode] = None
    }

    def isValidBST(root: TreeNode): Boolean = {
    isValid(Some(root),None,None)
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

    def isValid(root: Option[TreeNode], min: Option[Int], max: Option[Int]): Boolean = {
        if (root == None) return true
        if (min != None && root.get.value <= min.get) return false
        if (max != None && root.get.value >= max.get) return false
        isValid(root.get.left, min,Some( root.get.value)) && isValid(root.get.right, Some(root.get.value), max)
    }
}
