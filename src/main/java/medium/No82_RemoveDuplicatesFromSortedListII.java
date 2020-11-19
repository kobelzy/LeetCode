package medium;

/**
 * 82. 删除排序链表中的重复元素 II
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 示例 2:
 * <p>
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 */
public class No82_RemoveDuplicatesFromSortedListII {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode preHead = new ListNode(-1), cur = preHead;
        preHead.next = head;

        while (cur != null) {
            ListNode tmp = cur.next;
            while (tmp != null && tmp.val == cur.val) {
                tmp = tmp.next;
            }
            cur.next = tmp;
            cur = cur.next;
        }
        return preHead;
    }

}
