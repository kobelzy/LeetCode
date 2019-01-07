package other

object trees {
  def main (args: Array[String] ): Unit = {
    val tree = Node(1,
      Node(2, Leaf(4), null),
      Node(3, Leaf(5), null))
  println(tree)
  }
}

trait Tree[+T] {
  /**
    * 深度优先遍历
    */
  def dfs(func: T => Unit) {
    this match {
      case Empty                    =>
      case Node(value, left, right) => func(value); left.dfs(func); right.dfs(func)
    }
  }
}
/**
  * 空树
  */
case object Empty extends Tree[Nothing]()
/**
  * 节点, 单个节点是一棵树
  */
case class Node[T](value: T, left: Tree[T], right: Tree[T]) extends Tree[T]
/**
  * 叶子，只是为了创建更方便
  */
//case class Leaf[T](override val value: T) extends Node[T](value, Empty, Empty)
case class Leaf[T](value: T)  extends Tree[T]{
  Node(value,null,null)

}

