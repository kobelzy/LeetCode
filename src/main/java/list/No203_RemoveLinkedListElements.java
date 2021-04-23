package list;

/**
 * 203. 移除链表元素
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 * 示例 1：
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * 输出：[1,2,3,4,5]
 * 示例 2：
 * 输入：head = [], val = 1
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [7,7,7,7], val = 7
 * 输出：[]
 * 提示：
 * 列表中的节点在范围 [0, 104] 内
 * 1 <= Node.val <= 50
 * 0 <= k <= 50
 */
public class No203_RemoveLinkedListElements {
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
        No203_RemoveLinkedListElements t=new No203_RemoveLinkedListElements();
        t.removeElements(l1,2);
    }
    public ListNode removeElements(ListNode head, int val) {
        ListNode preHead=new ListNode(-1),cur=preHead;
        preHead.next=head;
        while(cur!=null){
            ListNode tmp=cur.next;
            while(tmp!=null && tmp.val==val) tmp=tmp.next;
            cur.next=tmp;
            cur=cur.next;
        }
        return preHead.next;
    }

}
