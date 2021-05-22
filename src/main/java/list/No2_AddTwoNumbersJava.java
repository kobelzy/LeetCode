package list;

/**
 * 2.两数相加
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807

 */
public class No2_AddTwoNumbersJava {

    public ListNode addTwoNumbers2(ListNode l1,ListNode l2){
        int flag=0;
        ListNode preHead=new ListNode(-1),cur=preHead;
        while(l1!=null || l2 !=null || flag!=0){
            int sum=(l1==null ?0:l1.val)+(l2==null?0:l2.val)+flag;
            flag=sum/10;
            ListNode next=new ListNode(sum%10);
            cur.next=next;
            cur=cur.next;
            l1=l1==null?null:l1.next;
            l2=l2==null?null:l2.next;
        }
        return preHead.next;
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode preHead=new ListNode(0);
        ListNode cur=preHead;
        int flag=0;
        while(l1!=null || l2!=null){
            int v1= l1==null ?0 :l1.val;
            int v2= l2==null ?0 :l2.val;
            int sum=v1+v2+flag;
            if(sum>=10){
                flag=sum/10;
                sum=sum%10;
            } else{
                flag=0;
            }
//            l1 =l1==null ? null :l1.next;
//            l2= l2==null ? null :l2.next;
             if(l1!=null) l1=l1.next;
             if(l2!=null) l2=l2.next;
            cur.next=new ListNode(sum);
            cur=cur.next;
        }
        if(flag!=0) cur.next=new ListNode(flag);
        return preHead.next;
    }


}
