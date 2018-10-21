package easy

import scala.collection.mutable.ListBuffer

object No206_ReverseLinkedList {
   class ListNode(var _x: Int = 0) {
       var next: ListNode = null
       var x: Int = _x
     }
  def reverseList(head: ListNode): ListNode = {
    var cur = head
    var lastNode: ListNode = null
    var nextNode: ListNode = null
    while (cur != null) {
      nextNode = cur.next
      cur.next = lastNode
      lastNode = cur
      cur = nextNode
    }
    lastNode
  }

  def main(args: Array[String]): Unit = {
    val list=ListBuffer[ListNode]()
    val node1=new ListNode(1)
    val node2=new ListNode(2)
    val node3=new ListNode(3)
node1.next=node2
    node2.next=node3
    list+=(node1,node2,node3)
    println(node2.next.x)//应该为1才对
    list.foreach(x=>print(x.x)+",")
    println("-=---")
    reverseList(node1)
    println(node2.next.x)//应该为1才对
    list.foreach(x=>print(x.x)+",")
list.reverse
  }
}
