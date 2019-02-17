package medium


object No24_SwapNodesinPairs {

  //  给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
  //  给定 1->2->3->4, 你应该返回 2->1->4->3.
  class ListNode(var _x: Int = 0) {
    var next: ListNode = null
    var x: Int = _x
  }

  def swapPairs(head: ListNode): ListNode = {
    var cur = head
    var nextNode: ListNode = null
    val headNode:ListNode=head.next
    while (cur != null) {
      nextNode = cur.next
      if (nextNode != null) {
        //当前节点指向下下个
        cur.next = nextNode.next
        nextNode.next = cur

        cur = nextNode.next
      } else {
        cur = nextNode
      }
    }
    if(headNode==null)head else headNode
  }
  def swapPairs2(head: ListNode): ListNode = {
    var lastNode:ListNode=new ListNode()
    lastNode.next=head
    while(lastNode.next!=null && lastNode.next.next !=null) {
      val curNode = lastNode.next
      val nextNode = curNode.next
      lastNode.next = nextNode
      nextNode.next = curNode
      curNode.next = nextNode.next
      lastNode = curNode
    }
    lastNode.next
  }
}
