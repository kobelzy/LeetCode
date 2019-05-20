package easy

/**21. 合并两个有序链表
  * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
  *
  * 示例：
  *
  * 输入：1->2->4, 1->3->4
  * 输出：1->1->2->3->4->4
  */
object No21_MergeTwoSortedLists {
  case class ListNode(var _x: Int = 0) {
     var next: ListNode = null
     var x: Int = _x
   }

  def main(args: Array[String]): Unit = {
    val n11=new ListNode(1)
    val n12=new ListNode(2)
    val n14=new ListNode(4)
    n11.next=n12
    n12.next=n14

    val n21=new ListNode(1)
    val n23=new ListNode(3)
    val n24=new ListNode(4)
    n21.next=n23
    n23.next=n24
    println(mergeTwoLists(n11,n21))
  }
  def mergeTwoLists(l1: ListNode, l2: ListNode): ListNode = {
    var cur=new ListNode(0)
    val head=cur
    var c1=l1
    var c2=l2
    while(c1.next!=null || c2.next!=null){
      if(c1.next!=null && c2.next!=null){
        if(c1.x>=c2.x){
          cur.next=c1.next
          c1=c1.next
        }else{
          cur.next=c2.next
          c2=c2.next
        }
      }else if(c1.next !=null){
        cur.next=c1.next
        c1=c1.next
      }else{
        cur.next=c2.next
        c2=c2.next
      }

      cur=cur.next
    }
    head.next
  }
}
