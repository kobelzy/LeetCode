package list;

/**
 * 19. 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 进阶：你能尝试使用一趟扫描实现吗？
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 * 提示：
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 */
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

    /**
     * 这个更好理解一些
     * @param head
     * @param k
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head,int k){
        ListNode preHead=new ListNode(-1);
        preHead.next=head;
        ListNode slow=preHead,fast=preHead;
        for (int i = 0; i < k; i++) {//走过k个，
            fast=fast.next;
        }
        //pre,a,b,c,d,null
        while(fast.next!=null){//fast指向null,slow指向k的前一个
            fast=fast.next;
            slow=slow.next;
        }
        slow.next=slow.next.next;
        return preHead.next;
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
