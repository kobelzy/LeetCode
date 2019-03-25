package medium

/**
  * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
  *
  * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
  *
  * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
  *
  * 示例：
  *
  * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
  * 输出：7 -> 0 -> 8
  * 原因：342 + 465 = 807
  * Auther: Lzy
  * Description:
  * Date Created by： 9:31 on 2019/3/25
  * Modified By：
  */
class ListNode(var _x: Int = 0) {
  var next: ListNode = null
  var x: Int = _x
}

object No2_AddTwoNumbers {

  def main(args: Array[String]): Unit = {
    val l1 = new ListNode(2)
    l1.next = new ListNode(4)
    l1.next.next = new ListNode(3)

    val l2 = new ListNode(5)
    //        l2.next=new ListNode(6)
    //        l2.next.next=new ListNode(4)
    val result = addTwoNumbers(l1, l2)
    println(result.x)
    println(result.next.x)
    println(result.next.next.x)
  }

  def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {
    var value = 0
    var isIn = 0
    var node: ListNode = new ListNode(0)
    val head = node
    var currentL1 = l1
    var currentL2 = l2
    while (currentL1 != null || currentL2 != null) {
      value = (if (currentL1 == null) currentL2.x else if (currentL2 == null) currentL1.x else currentL1.x + currentL2.x) + isIn
      isIn = value / 10
      val newNode = new ListNode(value % 10)
      node.next = newNode
      node = newNode
      currentL1 = if (currentL1 != null) currentL1.next else currentL1
      currentL2 = if (currentL2 != null) currentL2.next else currentL2
    }
    if (isIn > 0) node.next = new ListNode(1)
    head.next
  }
}
