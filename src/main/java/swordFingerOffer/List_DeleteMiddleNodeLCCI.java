package swordFingerOffer;

/**
 * 面试题 02.03. 删除中间节点
 * 实现一种算法，删除单向链表中间的某个节点（即不是第一个或最后一个节点），假定你只能访问该节点。
 * 示例：
 *
 * 输入：单向链表a->b->c->d->e->f中的节点c
 * 结果：不返回任何数据，但该链表变为a->b->d->e->f
 */
     class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
public class List_DeleteMiddleNodeLCCI {
    public static void main(String[] args) {
        new List_DeleteMiddleNodeLCCI().run();
    }
    public void run(){
//        ListNode l6=new ListNode(6);
//        ListNode l5=new ListNode(5);
        ListNode l4=new ListNode(9);
        ListNode l3=new ListNode(1);
        ListNode l2=new ListNode(5);
        ListNode l1=new ListNode(4);
        l1.next=l2;
        l2.next=l3;
        l3.next=l4;
//        l4.next=l5;
//        l5.next=l6;
        deleteNode(l1);
        System.out.println(l1);
    }

    //TODO 未解决
    public void deleteNode(ListNode node) {

        node.val=node.next.val;
        node.next=node.next.next;
    }
}
