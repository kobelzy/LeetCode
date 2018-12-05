package medium

import scala.collection.mutable.ListBuffer

/**
  * Auther: lzy
  * Description:
  * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
  *
  * 例如:
  * 给定二叉树: [3,9,20,null,null,15,7],
  *
  * 3
  * / \
  * 9  20
  * /  \
  * 15   7
  * 返回其层次遍历结果：
  *
  * [
  * [3],
  * [9,20],
  * [15,7]
  * ]
  * Date Created by： 9:09 on 2018/12/5
  * Modified By：
  */

object No102_BinaryTreeLevelOrdertRaversal {
     class TreeNode(var _value: Int) {
         var value: Int = _value
         var left: TreeNode = null
         var right: TreeNode = null
     }
//    def levelOrder(self,root):
//        if not root: return []
//        result=[]
//        queue=collections.deque()
//        queue.append(root)
//        #visited=set(root)
//        while queue:
//                level_size=len(queue)
//                current_level=[]
//                for _ in range(level_size):
//                        node=queue.popleft()
//                        current_elvel.append(node.val)
//                        if node.left: queue.append(node.left)
//                        if node.right:queue.append(node.right)
//                result.append(curent_level)
    /***
     * 功能实现:
     *BFS广度优先
     * Author: Lzy
     * Date: 2018/12/5 9:10
     * Param: [root]
     * Return: scala.collection.immutable.List<scala.collection.immutable.List<java.lang.Object>>
     */
    def levelOrder(root: TreeNode): List[List[Int]] = {
        if(root==null) return Nil
        val result=ListBuffer[List[Int]]()
        val queue=scala.collection.mutable.Queue[TreeNode]()
        queue.enqueue(root)

        while (!queue .isEmpty){
        val level_size=queue.size
            val current_level=ListBuffer[Int]()

            for (i<- queue.indices){
                val node=queue.dequeue()
                current_level+=(node.value)
                if(node.left!=null) queue.enqueue(node.left)
                if(node.right!=null) queue.enqueue(node.left)
            result+=(current_level.toList)

            }

        }
        result.toList
    }

/***
 * 功能实现:
 *DFS深度优先
 * Author: Lzy
 * Date: 2018/12/5 9:12
 * Param: [root]
 * Return: scala.collection.immutable.List<scala.collection.immutable.List<java.lang.Object>>
 */
    def levelOrder2(root: TreeNode): List[List[Int]] = {
        Nil
    }

    def levelOrder3(root: TreeNode): List[List[Int]] = {
        Nil

    }
}
