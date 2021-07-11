package InterviewQuestions;

import java.util.*;

/**
 * 面试题 04.12. 求和路径
 * 给定一棵二叉树，其中每个节点都含有一个整数数值(该值或正或负)。设计一个算法，打印节点数值总和等于某个给定值的所有路径的数量。注意，路径不一定非得从二叉树的根节点或叶节点开始或结束，但是其方向必须向下(只能从父节点指向子节点方向)。
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \    / \
 * 7    2  5   1
 * 返回:
 * 解释：和为 22 的路径有：[5,4,11,2], [5,8,4,5], [4,11,7]
 * 提示：
 * 节点总数 <= 10000
 * @author: Patrick Star
 * @time: 2021/7/11 14:13
 */
public class IQ0412_PathsWithSum {

    public int pathSum(TreeNode root, int sum) {
        // key是前缀和, value是大小为key的前缀和出现的次数
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        // 前缀和为0的一条路径
        prefixSumCount.put(0, 1);
        // 前缀和的递归回溯思路
        return recursionPathSum(root, prefixSumCount, sum, 0);
    }

    /**
     * 前缀和的递归回溯思路
     * 从当前节点反推到根节点(反推比较好理解，正向其实也只有一条)，有且仅有一条路径，因为这是一棵树
     * 如果此前有和为currSum-target,而当前的和又为currSum,两者的差就肯定为target了
     * 所以前缀和对于当前路径来说是唯一的，当前记录的前缀和，在回溯结束，回到本层时去除，保证其不影响其他分支的结果
     *
     * @param node           树节点
     * @param prefixSumCount 前缀和Map
     * @param target         目标值
     * @param currSum        当前路径和
     * @return 满足题意的解
     */
    private int recursionPathSum(TreeNode node, Map<Integer, Integer> prefixSumCount, int target, int currSum) {
        // 1.递归终止条件
        if (node == null) return 0;

        // 2.本层要做的事情
        int res = 0;
        // 当前路径上的和
        currSum += node.val;

        //---核心代码
        // 看看root到当前节点这条路上是否存在节点前缀和加target为currSum的路径
        // 当前节点->root节点反推，有且仅有一条路径，如果此前有和为currSum-target,而当前的和又为currSum,两者的差就肯定为target了
        // currSum-target相当于找路径的起点，起点的sum+target=currSum，当前点到起点的距离就是target
        res += prefixSumCount.getOrDefault(currSum - target, 0);
        // 更新路径上当前节点前缀和的个数
        prefixSumCount.put(currSum, prefixSumCount.getOrDefault(currSum, 0) + 1);
        //---核心代码

        // 3.进入下一层
        res += recursionPathSum(node.left, prefixSumCount, target, currSum);
        res += recursionPathSum(node.right, prefixSumCount, target, currSum);

        // 4.回到本层，恢复状态，去除当前节点的前缀和数量
        prefixSumCount.put(currSum, prefixSumCount.get(currSum) - 1);
        return res;
    }


    /**
     * 广度优先遍历
     * 使用一个辅助Queue来保存路径上的和
     * 执行用时：14 ms, 在所有 Java 提交中击败了5.56%的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了96.72%的用户
     *
     * @param root
     * @param sum
     * @return
     */
    public int pathSum2(TreeNode root, int sum) {
        Queue<TreeNode> treeQ = new LinkedList<>();
        Queue<List<Integer>> valQ = new LinkedList<>();
        List<Integer> rootValList = new ArrayList<>();
        if (root != null) {
            treeQ.offer(root);
            rootValList.add(root.val);
        }
        valQ.offer(rootValList);
        int res = 0;
        while (!treeQ.isEmpty()) {
            int size = treeQ.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = treeQ.poll();
                List<Integer> curValList = valQ.poll();
                for (Integer curVal : curValList) {
                    if (curVal == sum) res++;
                }
                /**
                 * 先通过增加一个0值，之后修改为0+val；
                 * 可以保证顺着路径下来，索引0代表整个路径和；索引1代表没有root的和....最后一个节点只有当前值的和。
                 */
                curValList.add(0);
                if (cur.left != null) {
                    List<Integer> newList = new ArrayList<>(curValList.size());
                    for (int j = 0; j < curValList.size(); j++) {
                        newList.add(curValList.get(j) + cur.left.val);
                    }
                    treeQ.offer(cur.left);
                    valQ.offer(newList);
                }
                if (cur.right != null) {
                    for (int j = 0; j < curValList.size(); j++) {
                        curValList.set(j, curValList.get(j) + cur.right.val);
                    }
                    treeQ.offer(cur.right);
                    valQ.offer(curValList);
                }
            }
        }
        return res;
    }
}
