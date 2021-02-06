package easy;

/**
 * 删除链表中等于给定值 val 的所有节点。
 *
 * 示例:
 *
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 */
public class No203_RemoveLinkedListElements {
     class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }
    public ListNode removeElements(ListNode head, int val) {
        ListNode preHead =new ListNode(0);
        preHead.next=head;
        ListNode current=preHead;
        while(current !=null){
            ListNode tmp=current.next;
            while(tmp!=null && tmp.val==val){
                tmp=tmp.next;
                current.next=tmp;
            }
            current=tmp;
        }
        return preHead.next;
    }
}
