package medium;
class ListNode {
    int val;
    ListNode next;
    public ListNode() {}
    public ListNode(int val) { this.val = val; }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public String toString() {
        return "[v=" + val +", next="+ next +']';
    }
}
public class No19_RemoveNthNodeFromEndOfListJava {


    /**
     * 双指针，指针1正向走n个，剩下的count-n的长度，就是倒数第n个
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //删除节点的情况下，引入一个head前节点，更方便
        //同时也可以用该节点向后循环，进行删除
        ListNode headPre=new ListNode(0,head);
        ListNode tmp=headPre;
        ListNode current=head;
        for (int i = 0; i < n; i++) {
            current=current.next;
        }
        while(current!=null){
            current=current.next;
            tmp=tmp.next;
        }
        tmp.next=tmp.next.next;
        return headPre.next;

    }

    public static void main(String[] args) {
//    ListNode tail=new ListNode(5);
//    ListNode tail2=new ListNode(4,tail);
//    ListNode tail3=new ListNode(3,tail2);
    ListNode tail4=new ListNode(2);
    ListNode head=new ListNode(1,tail4);
//    ListNode head=new ListNode(1);
        System.out.println(head);

        System.out.println(new No19_RemoveNthNodeFromEndOfListJava().removeNthFromEnd(head,2));
    }
}
