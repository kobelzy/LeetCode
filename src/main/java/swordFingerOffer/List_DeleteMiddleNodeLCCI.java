package swordFingerOffer;

public class List_DeleteMiddleNodeLCCI {
    public class ListNode {
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
    public static void main(String[] args) {
        new List_DeleteMiddleNodeLCCI().run();
    }
    public void run(){
        ListNode l6=new ListNode(6);
        ListNode l5=new ListNode(5);
        ListNode l4=new ListNode(4);
        ListNode l3=new ListNode(3);
        ListNode l2=new ListNode(2);
        ListNode l1=new ListNode(1);
        l1.next=l2;
        l2.next=l3;
        l3.next=l4;
        l4.next=l5;
        l5.next=l6;
        deleteNode(l1);
        System.out.println(l1);
    }

    //TODO 未解决
    public void deleteNode(ListNode node) {
        if(node==null || node.next==null ||node.next.next==null) return ;
        ListNode preHead=new ListNode(-1);
        preHead.next=node;
        ListNode fast=preHead,slow=preHead;
        while(fast!=null){
            fast=fast.next;
            slow=slow.next;
            if(fast!=null) fast=fast.next;
        }
        slow.val=slow.next.val;
        slow.next=slow.next.next;
    }
}
