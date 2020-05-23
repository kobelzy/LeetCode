package easy;

import java.util.HashSet;
import java.util.Set;

/**
 * 141. 环形链表
 * 给定一个链表，判断链表中是否有环。
 * <p>
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * <p>
 * 示例 2：
 * <p>
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * <p>
 * 示例 3：
 * <p>
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 */
public class No141_LinkedListCycle {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 双指针
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {

        if(head==null||head.next==null) return false;
        ListNode fPointer = head;
        ListNode sPointer = head;
        while (sPointer.next != null) {
            fPointer = fPointer.next;
            sPointer = sPointer.next;
            if (sPointer.next != null) sPointer = sPointer.next;
            else return false;
            if (sPointer == fPointer) return true;
        }
        return false;
    }

    public boolean hasCycle2(ListNode head) {
        Set<ListNode> set=new HashSet<ListNode>();
        ListNode current=head;

        while(current!=null){
            if(set.contains(current)) return true;
            else set.add(current);
            current=current.next;
        }
        return false;
    }
    }
