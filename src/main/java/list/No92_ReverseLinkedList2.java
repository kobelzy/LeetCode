package list;

/**
 * 92. 反转链表 II
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * <p>
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 */
public class No92_ReverseLinkedList2 {
    public static void main(String[] args) {
       new No92_ReverseLinkedList2().run();
    }
    public void run(){
        ListNode l5=new ListNode(5);
        ListNode l4=new ListNode(4);
        ListNode l3=new ListNode(3);
        ListNode l2=new ListNode(2);
        ListNode l1=new ListNode(1);
        l1.next=l2;
        l2.next=l3;
        l3.next=l4;
        l4.next=l5;
//        System.out.println(reverseBetween(l1,2,4));
        System.out.println(reverseBetween(l4,1,2));
    }
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode preHead=new ListNode(-1);
        preHead.next=head;
        ListNode cur = preHead;
        //m、n的下标是从1开始的；
        for (int i = 1; i < m; i++) cur = cur.next;
        cur.next=reverse(cur.next,n-m+1);//包含尾部
        return preHead.next;
    }

    public ListNode reverse(ListNode head,int num){
        if(head==null) return null;
        ListNode cur=head;
        ListNode prev=null;
        for (int i = 0; i < num; i++) {
            ListNode tmp=cur.next;
            cur.next=prev;
            prev=cur;
            cur=tmp;
        }
        head.next=cur; //将剩余不需要反转的部分直接串联起来
        return prev;
    }
}
