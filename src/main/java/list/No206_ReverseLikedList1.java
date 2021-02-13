package list;

/**
 * 206. 反转链表
 * 反转一个单链表。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */
public class No206_ReverseLikedList1 {
    /** ⚠️：
     * while(cur!=null)最终遍历到cur=null ，即last的后一位
     * while(cur.next!=null)最终遍历到cur=last
     * */


    /**
     * 从第二个节点开始
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head.next;
        ListNode last = head;
        last.next = null;
        while (cur.next != null) {
            ListNode tmp = cur;
            cur = tmp.next;
            tmp.next = last;
            last = tmp;
        }
        cur.next = last;
        return cur;
    }


    /**
     * 从第一个节点开始
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {

        ListNode current = head;
        ListNode last = null;

        while (current != null) {
            ListNode tmp = current.next;
            current.next = last;
            last = current;
            current = tmp;
        }
        return last;
    }

}
