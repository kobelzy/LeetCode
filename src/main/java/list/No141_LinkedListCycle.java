package list;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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


    /**
     * 快慢指针指针
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {

        if(head==null||head.next==null) return false;
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast.next != null) fast = fast.next;
            else return false;
            if (fast == slow) return true;
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
