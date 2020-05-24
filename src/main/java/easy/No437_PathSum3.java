package easy;

/**
 * 437. 路径总和 III
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 * <p>
 * 找出路径和等于给定数值的路径总数。
 * <p>
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * <p>
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 * <p>
 * 示例：
 * <p>
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 * <p>
 * 10
 * /  \
 * 5   -3
 * / \    \
 * 3   2   11
 * / \   \
 * 3  -2   1
 * <p>
 * 返回 3。和等于 8 的路径有:
 * <p>
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3.  -3 -> 11
 */
public class No437_PathSum3 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 递归解法：
     * 当前节点开始是否有联通的结果
     * 当前结果右节点的总结果数
     * 当前结果左节点的总结果数
     *
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        int rootSum = pathCount(root, sum);
        int leftSum = pathSum(root.left, sum);
        int rightSum = pathSum(root.right, sum);
        return rootSum+leftSum+rightSum;
    }

    public int pathCount(TreeNode root, int sum) {
        if (root == null) return 0;
        sum = sum - root.val;
        int count = sum == 0 ? 1 : 0;
        return count + pathCount(root.left, sum) + pathCount(root.right, sum);

    }

}
