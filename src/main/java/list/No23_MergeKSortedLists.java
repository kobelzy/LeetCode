package list;


import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * 示例 1：
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 * 输入：lists = [[]]
 * 输出：[]
 * 提示：
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 */

public class No23_MergeKSortedLists {
    /**
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 65.30%的用户
     * 内存消耗： 40.5 MB, 在所有 Java 提交中击败了55.43%的用户
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0) return null;
        ListNode preHead = new ListNode(-1), cur = preHead;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        for (ListNode list : lists) {
            if (list != null) pq.add(list);
        }
//        while (pq.peek() != null) { //这种方法也可以
        while (!pq.isEmpty()) {
            ListNode tmp = pq.poll();
            if (tmp.next != null) pq.add(tmp.next);
            cur.next = tmp;
            cur=cur.next;
        }
        return preHead.next;
    }
}