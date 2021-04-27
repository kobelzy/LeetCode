package list;

/**
 * 143. 重排链表
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 示例 1:
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 * 通过次数93,008提交次数154,403
 */
public class No143_ReorderList {
    public static void main(String[] args) {
        ListNode l5 = new ListNode(5);
        ListNode l4 = new ListNode(4);
        ListNode l3 = new ListNode(3);
        ListNode l2 = new ListNode(2);
        ListNode l1 = new ListNode(1);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
//        l4.next = l5;
        No143_ReorderList t = new No143_ReorderList();
        t.reorderList(l1);
        System.out.println(l1);
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode slow = head, fast = head;
        //如果是奇数节点，slow在中心节点
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode reverseNode = reverse(slow.next);
        slow.next = null;
        ListNode preHead = new ListNode(-1), cur = preHead;
        while (head != null && reverseNode != null) {
            ListNode tmp = head.next;
            cur.next = head;
            cur = cur.next;
            head = tmp;
            ListNode tmpReverse = reverseNode.next;
            cur.next = reverseNode;
            cur = cur.next;
            reverseNode = tmpReverse;
        }
        cur.next = head;
    }

    private ListNode reverse(ListNode head) {
        ListNode cur = head, prev = null;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }
        return prev;
    }
}
