package medium;


import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
 *
 * 说明：不允许修改给定的链表。
 *
 * 进阶：
 *
 * 你是否可以使用 O(1) 空间解决此题？
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 示例 2：
 *
 *
 *
 * 输入：head = [1,2], pos = 0
 * 输出：返回索引为 0 的链表节点
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * 示例 3：
 *
 *
 *
 * 输入：head = [1], pos = -1
 * 输出：返回 null
 * 解释：链表中没有环。
 *  
 *
 * 提示：
 *
 * 链表中节点的数目范围在范围 [0, 104] 内
 * -105 <= Node.val <= 105
 * pos 的值为 -1 或者链表中的一个有效索引
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class No142_LinkedListCycleII {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 使用HashSet
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode current = head;
        Set<ListNode> map = new HashSet<ListNode>();
        map.add(current);
        while (current.next != null) {
            current = current.next;
            if (map.contains(current)) return current;
            else map.add(current);
        }
        return null;
    }


    /**
     * 快慢指针
     * 推理：
     * 快指针走2n，慢指针走n
     * 环外的节点数为a；慢节点在环内走了b，剩下的半截环长尾c，则快指针走过的是：
     * a+(b+c)*n+b => a+(n+1)b+nc
     * 快指针点走过的路程是慢指针的2倍，则有：
     * 2(a+b)=a+(n+1)b+nc =>
     * a=(n-1)b+nc=(n-1)(b+c)+c
     * 而b+c是环总长，
     * 那么在快慢指针碰上之后，慢节点已经走过了b的长度，让慢节点继续走n-1圈的距离就是a，也就是环开始的节点
     * 所以同时启动两个节点从b，头部开始走，最终相交的点就是橡胶垫
     */
    public ListNode detectCycle2(ListNode head) {
        if(head==null ||head.next==null) return null;

        ListNode fast=head;
        ListNode slow=head;
        while(fast!=null){
            slow=slow.next;
            fast=fast.next;
            if(fast.next==null) return null;
            else fast=fast.next;
            if(slow==fast){
                ListNode tmp=head;
                while(tmp!=slow){
                    tmp=tmp.next;
                    slow=slow.next;
                }
                return tmp;
            }
        }

        return null;
    }
}