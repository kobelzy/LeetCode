package medium;

/**
 * 328. 奇偶链表
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 *
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * 示例 2:
 *
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 * 说明:
 *
 * 应当保持奇数节点和偶数节点的相对顺序。
 * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 */
public class No328_OddEvenLinkedList {

    public class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

 public void run(){
        ListNode l5=new ListNode(5);
        ListNode l4=new ListNode(4,l5);
        ListNode l3=new ListNode(3,l4);
        ListNode l2=new ListNode(2,l3);
        ListNode l1=new ListNode(1,l2);
     System.out.println(l1);
     System.out.println(oddEvenList(l1));

 }
    public static void main(String[] args) {
        No328_OddEvenLinkedList t=new No328_OddEvenLinkedList();
        t.run();
    }
        public ListNode oddEvenList(ListNode head) {
            ListNode odd=new ListNode(-1),oddHead=odd;
            ListNode even=new ListNode(-1),evenHead=even;
            ListNode current=head;
            boolean isOdd=true;
            while(current!=null){
                if(isOdd){
                    odd.next=current;
                    odd=odd.next;
                }else{
                    even.next=current;
                    even=even.next;
                }
                isOdd=!isOdd;
                current=current.next;
            }
            even.next=null;
            odd.next=evenHead.next;
            return oddHead.next;
        }

    /**
     * 官方答案
     * @param head
     * @return
     */
    public ListNode oddEvenList2(ListNode head) {
        if (head == null) return null;
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;

    }
}
