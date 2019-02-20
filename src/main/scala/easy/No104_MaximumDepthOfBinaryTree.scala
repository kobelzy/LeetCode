package easy

import scala.util.Try

object No104_MaximumDepthOfBinaryTree {

  class TreeNode(var _value: Int) {
    var value: Int = _value
    var left: TreeNode = null
    var right: TreeNode = null
  }

  /*
  BFS广度优先
   */
  def maxDepth(root: TreeNode): Int = {
    if (root == null) {
       0
    } else {
      val queue=scala.collection.mutable.Queue[TreeNode]()
      var depth=0
      queue.enqueue(root)
      while(queue.nonEmpty){
        depth+=1
        for(i<- queue.indices){
          val son=queue.dequeue()
      if(son.left!=null)queue.enqueue(son.left)
          if(son.right!=null)queue.enqueue(son.right)
        }
      }
      depth
    }
  }

  /** *
    * 功能实现:DFS
    *
    * Author: Lzy
    * Date: 2018/12/10 9:12
    * Param: [root]
    * Return: int
    */
  def maxDepth2(root: TreeNode): Int = {
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
  def minDepth3(root: TreeNode): Int = {
    root match {
      case null =>0
      case _=>{
        val left=minDepth3(root.left)
        val right=minDepth3(root.right)
        //left+right+1不管哪个为0都可以直接使用，不然还需要单独判断
        if(left ==0 || right ==0) left+right+1 else math.min(left,right)+1
      }
    }
  }
}
