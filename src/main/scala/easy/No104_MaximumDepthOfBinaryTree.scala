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
      val queue=scala.collection.mutable.Queue[Array[TreeNode]]()
      var depth=0
      queue.enqueue(Array(root))
      while(queue.nonEmpty){
        val node_arr=queue.dequeue()
        depth+=1
        val sonNodes=node_arr.flatMap(node=>{
          (Try(node.left).toOption, Try(node.right).toOption) match {
            case (Some(left),Some(right)) =>Array(left,right)
            case (None,Some(right))=>Array(right)
            case (Some(left),None)=>Array(left)
            case _=>Array[TreeNode]()
          }
        })
        queue.enqueue(sonNodes)
      }
      depth
    }
  }
}
