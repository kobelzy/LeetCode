package list;

/**
 * 83. 删除排序链表中的重复元素
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * 示例 1:
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例 2:
 *
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 */
public class No83_RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur=head;
        while(cur!=null){
            ListNode tmp=cur.next;
            while(tmp!=null && tmp.val==cur.val){
                tmp=tmp.next;
            }
            cur.next=tmp;
            cur=cur.next;
        }
        return head;
    }
}
