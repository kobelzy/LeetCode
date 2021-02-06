package medium;

/**
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 示例 2:
 *
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class No61_RotateList {
    public class ListNode {
        int val;
         ListNode next;
        ListNode(int x) { val = x; }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }


    public static void main(String[] args) {
       new No61_RotateList().run();
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
        System.out.println(rotateRight(l1,2));
    }
    public ListNode rotateRight(ListNode head,int k){
    if(head==null)return null;
    if(head.next==null) return head;

    ListNode oldTail=head;
    ListNode newTail=head;
    int n;
    for(n=1;oldTail.next!=null ;n++){
        oldTail=oldTail.next;
    }

    oldTail.next=head;

    for(int i=0;i<n- k%n-1;i++){
        newTail=newTail.next;
    }
    ListNode newHead=newTail.next;
    newTail.next=null;
    return newHead;

    }
}
