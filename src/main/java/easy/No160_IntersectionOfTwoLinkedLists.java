package easy;

import java.util.HashSet;
import java.util.Set;

/**
 * 160. 相交链表
 * 编写一个程序，找到两个单链表相交的起始节点。
 * 如下面的两个链表：
 * <p>
 * 在节点 c1 开始相交。
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 * <p>
 * 示例 2：
 * <p>
 * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Reference of the node with value = 2
 * 输入解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 *  
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 解释：这两个链表不相交，因此返回 null。
 *  
 * <p>
 * 注意：
 * <p>
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 */
public class No160_IntersectionOfTwoLinkedLists {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 走完当前链表后指向另一个链表的头部，原因是链表走过两个链表的长度是固定的，
     * A和B不重合部分，相加起来是一个固定值，
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    ListNode curA=headA;
    ListNode curB=headB;
    //如果不相交，最终会走为null
    while(curA!=curB){
        curA= curA==null ?headB :curA.next;
        curB= curB==null ?headA :curB.next;
    }
    return curA;
    }

    /**
     * 使用hash
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        Set<ListNode> set=new HashSet<>();
        while(headA!=null){
            set.add(headA);
            headA=headA.next;
        }
        while(headB!=null){
            if(set.contains(headB)) return headB;
            headB=headB.next;
        }
        return null;
    }


        public static void main(String[] args) {
        ListNode a4=new ListNode(4);
        ListNode a1=new ListNode(1);



        ListNode b5=new ListNode(5);
        ListNode b0=new ListNode(0);
        ListNode b1=new ListNode(1);

        ListNode n8=new ListNode(8);
        ListNode n4=new ListNode(4);
        ListNode n5=new ListNode(5);
        a4.next=a1;
        a1.next=a1;
        a1.next=n8;
        n8.next=n4;
        n4.next=n5;

        b5.next=b0;
        b0.next=b1;
        b1.next=n8;
        No160_IntersectionOfTwoLinkedLists test=new No160_IntersectionOfTwoLinkedLists();
        ListNode intersectionNode = test.getIntersectionNode(a4, b5);
        System.out.println(intersectionNode.val);

    }

}
