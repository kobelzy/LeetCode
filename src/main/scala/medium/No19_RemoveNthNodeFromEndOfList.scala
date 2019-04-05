package medium

/**
  * 19. 删除链表的倒数第N个节点
  * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
  *
  * 示例：
  *
  * 给定一个链表: 1->2->3->4->5, 和 n = 2.
  *
  * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
  * 说明：
  *
  * 给定的 n 保证是有效的。
  *
  * 进阶：
  *
  * 你能尝试使用一趟扫描实现吗？
  */
object No19_RemoveNthNodeFromEndOfList {

  class ListNode(var _x: Int = 0) {
    var next: ListNode = null
    var x: Int = _x
  }

  def main(args: Array[String]): Unit = {
val h1=new ListNode(1)
val h2=new ListNode(2)
val h3=new ListNode(3)
val h4=new ListNode(4)
val h5=new ListNode(5)
    h1.next=h2
    h2.next=h3
    h3.next=h4
    h4.next=h5
     val head=removeNthFromEnd(h1,1)
    var currentNode=head
    while(currentNode!=null){
      println(currentNode.x)
      currentNode=currentNode.next
    }
  }

  def removeNthFromEnd(head: ListNode, n: Int): ListNode = {
    val len = length(head)
    var index = 0
    val dropIndex=len-n-1
    println(len)
    var currentNode = head
    if(len<n) return head
    if(len==n) return head.next
    while (currentNode.next != null && index != dropIndex) {
      println("循环:"+index)
      index +=1
      currentNode = currentNode.next
    }
    println("currentNode:"+currentNode.x)
    //a->b->c
    if(currentNode.next!=null)currentNode.next = currentNode.next.next
//    currentNode.next.next = null
    head
  }

  def length(head: ListNode) = {
    var len = 0
    var currentNode = head
    while (currentNode != null) {
      len +=1
      currentNode = currentNode.next
    }
    len
  }

  /**
    *提交的最优答案
    * @param head
    * @param n
    * @return
    */
  def removeNthFromEndOptimal(head: ListNode, n: Int): ListNode ={
    var a,b=head
    for(i<-0 until n) a=a.next
    a match{
      case null=>head.next
      case _=>{
        while(a.next!=null){
          a=a.next
          b=b.next
        }
        b.next=b.next.next
        head
      }
    }
  }
}
