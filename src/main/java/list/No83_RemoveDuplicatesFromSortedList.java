package list;

/**
 * 83. 删除排序链表中的重复元素
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * 示例 1:
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例 2:
 * <p>
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 */
public class No83_RemoveDuplicatesFromSortedList {
    public static void main(String[] args) {
        ListNode l5 = new ListNode(3);
        ListNode l4 = new ListNode(3);
        ListNode l3 = new ListNode(2);
        ListNode l2 = new ListNode(1);
        ListNode l1 = new ListNode(1);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        No83_RemoveDuplicatesFromSortedList t = new No83_RemoveDuplicatesFromSortedList();
        System.out.println(t.deleteDuplicates(l1));
    }

    /**
     * while (cur.next != null) 的实现
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;
            while (tmp != null && tmp.val == cur.val) {
                tmp = tmp.next;
            }
            cur.next = tmp;
            cur = cur.next;
        }
        return head;
    }

    /**
     * while (cur.next != null) 的实现
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) return null;
        ListNode cur = head;
        while (cur.next != null) {
            ListNode tmp = cur.next;
            while (tmp != null && tmp.val == cur.val) tmp = tmp.next;
            cur.next = tmp;
            if (tmp != null) cur = cur.next;
        }
        return head;
    }

    public ListNode deleteDuplicates3(ListNode head) {
        ListNode preHead=new ListNode(-1),cur=preHead;
        preHead.next=head;
        while (cur.next != null) {
            ListNode tmp = cur.next;
            while (tmp.next != null && tmp.val == tmp.next.val) tmp = tmp.next;
            cur.next = tmp;
            if (tmp != null) cur = cur.next;
        }
        return preHead.next;
    }
}
