package list;

/**
 * 25. K 个一组翻转链表
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 进阶：
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 * 示例 2：
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 * 示例 3：
 * 输入：head = [1,2,3,4,5], k = 1
 * 输出：[1,2,3,4,5]
 * 示例 4：
 * 输入：head = [1], k = 1
 * 输出：[1]
 * 提示：
 * 列表中节点的数量在范围 sz 内
 * 1 <= sz <= 5000
 * 0 <= Node.val <= 1000
 * 1 <= k <= sz
 * 通过次数197,519提交次数302,469
 */
public class No25_ReverseNodesInkGroup {
    public static void main(String[] args) {
        ListNode head=new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5)))));
        System.out.println(head);
        No25_ReverseNodesInkGroup t =new No25_ReverseNodesInkGroup();
        System.out.println(t.reverseKGroup(head,2));
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode preHead = new ListNode(-1, head), cur = preHead, memCur = null;
        while (cur != memCur) {
            memCur = cur;
            cur = reverse(cur, k);
        }
        return preHead.next;
    }


    /**
     * 旋转k长度的部分
     * @param head 传入上一组的尾部
     * @param k
     * @return
     */
    private ListNode reverse(ListNode head, int k) {
        //需要旋转的最后一个节点
        ListNode reversedEnd=head.next;
        ListNode end = head;
        for (int i = 0; i < k; i++) {
            if (end.next != null) end = end.next;
            else return head;
        }
        //正常旋转prev=0即可，但是这里因为后边可能有值，与其直接进行相连
        ListNode prev = end.next, cur = head.next;
        for (int i = 0; i < k; i++) {
            ListNode tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }
        //反转后的头部与前边的链表相连
        head.next = prev;
        //返回新结果的尾部；
        return reversedEnd;
    }
}
