package easy

/**
  * 101. 对称二叉树
  * 给定一个二叉树，检查它是否是镜像对称的。
  *
  * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
  * 1
  * / \
  * 2   2
  * / \ / \
  * 3  4 4  3
  * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
  *
  * 1
  * / \
  * 2   2
  * \   \
  * 3    3
  * 说明:
  * 如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
  */
object No101_SymmetricTree {

  class TreeNode(var _value: Int) {
    var value: Int = _value
    var left: TreeNode = null
    var right: TreeNode = null
  }

  def isSymmetric(root: TreeNode): Boolean = {
    run(root, root)
  }

  def run(left: TreeNode, right: TreeNode): Boolean = {
    if (left == null && right == null) {
      true
    } else if (left == null || right == null) {
      false
    } else {
      if (left.value == right.value) {
        run(left.left, right.right) && run(left.right, right.left)
      } else {
        false
      }
    }

}

  /**
    * 使用模式匹配，这个运行反而更快一些，且代码更清晰
    * @param left
    * @param right
    * @return
    */
  def run2(left: TreeNode, right: TreeNode): Boolean = {
    (left, right) match {
      case (null, null) => true
      case (_, null)|(null, _) => false
      case (_, _) =>
        if (left.value == right.value)
          run(left.left, right.right) && run(left.right, right.left)
        else false
    }
  }


}
