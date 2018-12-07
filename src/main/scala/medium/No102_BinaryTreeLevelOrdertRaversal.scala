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

    def main(args: Array[String]): Unit = {
        val root=new TreeNode(3)
        val node20=new TreeNode(20)
        node20.left=new TreeNode(15)
        node20.right=new TreeNode(7)
        root.left=new TreeNode(9)
        root.right=node20
        println(levelOrder(root))
    }
    /***
     * 功能实现:
     *BFS广度优先
      * 可变List728ms
     * Author: Lzy
     * Date: 2018/12/5 9:10
     * Param: [root]
     * Return: scala.collection.immutable.List<scala.collection.immutable.List<java.lang.Object>>
     */
    def levelOrder(root: TreeNode): List[List[Int]] = {
        if(root==null) return Nil
        val result=scala.collection.mutable.ListBuffer[List[Int]]()
//        var result=List[List[Int]]()
        val queue=scala.collection.mutable.Queue[TreeNode]()
        queue.enqueue(root)

        while (queue .nonEmpty){
            val size=queue.size
            val current_level=scala.collection.mutable.ListBuffer[Int]()
//            var current_level=List[Int]()

            for(i<- 0 until size){
                val node=queue.dequeue()
                current_level+=(node.value)
//                current_level=current_level:+(node.value)
                if(node.left!=null) queue.enqueue(node.left)
                if(node.right!=null) queue.enqueue(node.right)
            }
                result+=(current_level.toList)
//                result=result:+(current_level)
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

    def levelOrder4(root: TreeNode): List[List[Int]] = {
        var queue = collection.mutable.ListBuffer.empty[(TreeNode, Int)]
        if (null == root) {
            return List.empty
        }
        val result = collection.mutable.ListBuffer.empty[List[Int]]
        queue += ((root, 0))

        while(queue.nonEmpty) {
            val level = queue.head._2
            val (tuples, rest) = queue.partition(t => t._2 == level)
            result += tuples.map(t => t._1.value).toList
            queue = rest
            tuples.foreach { t =>
                if (t._1.left != null) {
                    queue += ((t._1.left, level + 1))
                }
                if (t._1.right != null) {
                    queue += ((t._1.right, level + 1))
                }
            }
        }

        result.toList

    }
}
