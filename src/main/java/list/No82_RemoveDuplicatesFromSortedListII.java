package list;

/**
 * 82. 删除排序链表中的重复元素 II
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 * 示例 1:
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 示例 2:
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 * TODO 需要好好看看
 */
public class No82_RemoveDuplicatesFromSortedListII {

    public static void main(String[] args) {
        new No82_RemoveDuplicatesFromSortedListII().run();
    }
    public void run(){
        ListNode l5=new ListNode(3);
        ListNode l4=new ListNode(3);
        ListNode l3=new ListNode(2);
        ListNode l2=new ListNode(2);
        ListNode l1=new ListNode(1);
        l1.next=l2;
        l2.next=l3;
        l3.next=l4;
        l4.next=l5;
        System.out.println(deleteDuplicates(l1));
    }
    public ListNode deleteDuplicates(ListNode head) {
        ListNode preHead = new ListNode(0), cur = preHead;
        preHead.next = head;

        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) { //1,2,2
                ListNode temp = cur.next;
                while ( temp.next != null && temp.val == temp.next.val) {
                    temp = temp.next;
                }
                cur.next = temp.next;
            } else cur = cur.next;
        }
        return preHead.next;
    }


}
