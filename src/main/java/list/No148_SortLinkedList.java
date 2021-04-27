package list;

/**
 * 148. 排序链表
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * 进阶：
 * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 * 示例 1：
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * 示例 2：
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * 示例 3：
 * 输入：head = []
 * 输出：[]
 */
public class No148_SortLinkedList {
    public static void main(String[] args) {
        No148_SortLinkedList t=new No148_SortLinkedList();
        ListNode l5=new ListNode(5);
        ListNode l4=new ListNode(4);
        ListNode l3=new ListNode(3);
        ListNode l2=new ListNode(2);
        ListNode l1=new ListNode(1);
        l2.next=l1;
        l3.next=l2;
        l4.next=l3;
        l5.next=l4;
//        System.out.println(reverseBetween(l1,2,4));
        System.out.println(t.sortList(l5));
    }
    /**
     * 使用归并方式进行排序
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if(head==null || head.next==null) {
            return head;
        }
        ListNode slow=head,fast=head;
        while(fast.next!=null && fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        ListNode midNode=slow.next;
        slow.next=null;
        ListNode l1=sortList(head);
        ListNode l2=sortList(midNode);
        ListNode dummy=new ListNode(0),cur=dummy;
        while(l1!=null && l2!=null){
            if(l1.val <=l2.val){
                cur.next=l1;
                l1=l1.next;
            }else{
                cur.next=l2;
                l2=l2.next;
            }
            cur=cur.next;
        }
        cur.next=l1==null?l2:l1;
        return dummy.next;
    }
}
