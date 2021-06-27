package list;

import java.util.BitSet;

/**
 * 面试题 02.01. 移除重复节点
 * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
 * 示例1:
 *  输入：[1, 2, 3, 3, 2, 1]
 *  输出：[1, 2, 3]
 * 示例2:
 *  输入：[1, 1, 1, 1, 2]
 *  输出：[1, 2]
 * 提示：
 * 链表长度在[0, 20000]范围内。
 * 链表元素在[0, 20000]范围内。
 * 进阶：
 * 如果不得使用临时缓冲区，该怎么解决？
 * @author: Patrick Star
 * @time: 2021/6/27 12:27
 */
public class IQ0201_RemoveDuplicateNode {
    public static void main(String[] args) {
        IQ0201_RemoveDuplicateNode t=new IQ0201_RemoveDuplicateNode();
        ListNode l1=new ListNode(1);
        ListNode l2=new ListNode(2);
        ListNode l3=new ListNode(1);
        ListNode l4=new ListNode(1);
        l1.next=l2;
        l2.next=l3;
        l3.next=l4;
        System.out.println(t.removeDuplicateNodes2(l1));
    }
    /**
     * 使用外部缓存
     *
     * @param head
     * @return
     */
    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        BitSet cache = new BitSet();
        cache.set(cur.val);
        while ( cur.next != null) {
            ListNode next = cur.next;
            if (!cache.get(next.val)) {
                cache.set(next.val);
                cur = cur.next;
            } else cur.next = cur.next.next;
        }
        return head;
    }

    public ListNode removeDuplicateNodes2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        while(cur!=null){
            ListNode tmp=cur;
            while(tmp.next!=null ){
                if(tmp.next.val==cur.val)tmp.next=tmp.next.next;
                //这里用else，不会导致tmp.next=null
                else tmp=tmp.next;
            }
            cur=cur.next;
        }
        return head;
    }
}
