package swordFingerOffer;


/**
 * 面试题 02.02. 返回倒数第 k 个节点
 * 实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
 *
 * 注意：本题相对原题稍作改动
 *
 * 示例：
 *
 * 输入： 1->2->3->4->5 和 k = 2
 * 输出： 4
 */
public class List_KthNodeFromEndOfListLCCI {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public int kthToLast(ListNode head, int k) {
        ListNode fast = head, slow = head;
        for (int i = 0; i < k; i++) fast = fast.next;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow.val;
    }
}
