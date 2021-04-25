package list;

/**
 * 445. 两数相加 II
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * 进阶：
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 * 示例：
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7
 */
public class No445_AddTwoNumbersII {

    /**
     * 反转链表解法，违反了输入链表不能修改的规则
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode r1=reverse(l1);
    ListNode r2=reverse(l2);
    int flag=0;
    ListNode preHead=new ListNode(-1),cur=preHead;
    while(r1!=null || r2!=null || flag!=0){
        int sum=(r1==null ?0:r1.val)+(r2==null ?0:r2.val)+flag;
        flag=sum/10;
        cur.next= new ListNode(sum%10);
        cur=cur.next;
        r1=r1==null?null :r1.next;
        r2=r2==null?null:r2.next;
    }
    return reverse(preHead.next);
    }

    private ListNode reverse(ListNode head){
        ListNode cur=head,prev=null;
        while (cur != null) {
            ListNode next=cur.next;
            cur.next=prev;
            prev=cur;
            cur=next;
        }
        return prev;
    }
}
