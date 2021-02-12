package list;

/**
 * 234. 回文链表
 * 请判断一个链表是否为回文链表。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * <p>
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class No234_PalindromeLinkedList {

    public static void main(String[] args) {
        ListNode n1=new ListNode(0);
        ListNode n2=new ListNode(0);
        n1.next=n2;
        No234_PalindromeLinkedList test =new No234_PalindromeLinkedList();
        System.out.println(test.isPalindrome(n1));
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        ListNode fast = head;
        ListNode slow = head;

        //该过程中，如果size为奇数，那么slow是中间元素，fast是结尾元素
        //size是偶数，那么slow是size/2，而fast是倒数第二个。
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        //该反转过程中，第一个元素.next依旧不变。所以。对于size=偶数，
        ListNode reserveHeadNode = reverse(slow.next);
        //反转后半部分队列
        ListNode headNode = head;
        ListNode lastNode = reserveHeadNode;
        boolean flag = true;
        while (flag && lastNode != null) {
            if (headNode.val != lastNode.val) flag = false;
            headNode = headNode.next;
            lastNode = lastNode.next;
        }
        slow.next = reverse(reserveHeadNode);
        return flag;
    }

    private ListNode reverse(ListNode node) {
        ListNode pre = null;
        ListNode cur = node;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        //最后一个元素的时候，cur为null；则需要返回pre
        return pre;
    }
}
