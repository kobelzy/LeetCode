package list;

/**
 * 24. 两两交换链表中的节点
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 示例 1：
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 示例 2：
 * 输入：head = []
 * 输出：[]
 * 示例 3：
 * 输入：head = [1]
 * 输出：[1]
 * 提示：
 * 链表中节点的数目在范围 [0, 100] 内
 * 0 <= Node.val <= 100
 *
 * 进阶：你能在不修改链表节点值的情况下解决这个问题吗?（也就是说，仅修改节点本身。）
 */
public class No24_SwapNodesInPairs {
    public static void main(String[] args) {
        ListNode l5=new ListNode(5);
        ListNode l4=new ListNode(4);
        ListNode l3=new ListNode(3);
        ListNode l2=new ListNode(2);
        ListNode l1=new ListNode(1);
        l1.next=l2;
        l2.next=l3;
        l3.next=l4;
        l4.next=l5;
        No24_SwapNodesInPairs t=new No24_SwapNodesInPairs();
        System.out.println(t.swapPairs(l1));
    }

    /**
     * 使用cur+prev来替换
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode preHead = new ListNode(-1), cur = head,pre=preHead;
        preHead.next = head;
        while(cur!=null && cur.next!=null){
            ListNode tmp=cur.next;
            cur.next=tmp.next;
            tmp.next=cur;
            pre.next=tmp;
            pre=cur;
            cur=cur.next;
        }
    return preHead.next;
    }

    /**
     * 使用cur.next+cur.next.next替换
     * @param head
     * @return
     */
    public ListNode swapPairs2(ListNode head) {
        if(head==null || head.next==null) return head;
        ListNode preHead=new ListNode(-1),cur=preHead;
        preHead.next=head;
        while(cur.next!=null && cur.next.next!=null){
            ListNode next1=cur.next;
            ListNode next2=next1.next;
            ListNode next3=next2.next;
            cur.next=next2;
            next2.next=next1;
            next1.next=next3;
            cur=next1;
        }
        return preHead.next;
    }
}
