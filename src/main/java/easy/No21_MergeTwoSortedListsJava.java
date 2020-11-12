package easy;

/**
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 示例：
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "{val=" + val +
                ", next=" + next +
                '}';
    }
}

public class No21_MergeTwoSortedListsJava {


    public static void main(String[] args) {
        No21_MergeTwoSortedListsJava test = new No21_MergeTwoSortedListsJava();
        ListNode n11 = new ListNode(1);
        ListNode n12 = new ListNode(2);
        ListNode n14 = new ListNode(4);
        n11.next = n12;
        n12.next = n14;

        ListNode n21 = new ListNode(1);
        ListNode n23 = new ListNode(3);
        ListNode n24 = new ListNode(4);
        n21.next = n23;
        n23.next = n24;
        System.out.println(test.mergeTwoLists(n11, n21));
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode cur = new ListNode(0);
        ListNode head = cur;
        ListNode left = l1;
        ListNode right = l2;
        while (left != null || right != null) {
            if (left != null && right != null) {
                if (left.val <= right.val) {
                    cur.next = left;
                    left = left.next;
                } else {
                    cur.next = right;
                    right = right.next;
                }
            } else if (left == null) {
                cur.next = right;
                right = right.next;
            } else {
                cur.next = left;
                left = left.next;
                //current2==null
            }
            cur = cur.next;
        }
        return head.next;
    }

    /**
     * 速度更快！！！！！
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode preHead = new ListNode(0);
        ListNode cur = preHead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        cur.next = l1 == null ? l2 : l1;
        return preHead.next;
    }

}
