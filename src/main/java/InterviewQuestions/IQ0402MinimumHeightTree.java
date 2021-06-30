package InterviewQuestions;


/**
 * 面试题 04.02. 最小高度树
 * 给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树。
 * 示例:
 * 给定有序数组: [-10,-3,0,5,9],
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *           0
 *          / \
 *        -3   9
 *        /   /
 *      -10  5
 * @author: Patrick Star
 * @time: 2021/6/30 20:48
 */
public class IQ0402MinimumHeightTree {
    public static void main(String[] args) {
        IQ0402MinimumHeightTree t = new IQ0402MinimumHeightTree();
        System.out.println(t.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9}));
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (end < start) return null;
        if (end == start) return new TreeNode(nums[start]);
        int mid = (end + start) >>> 1;
        TreeNode cur = new TreeNode(nums[mid]);
        cur.left = sortedArrayToBST(nums, start, mid - 1);
        cur.right = sortedArrayToBST(nums, mid + 1, end);
        return cur;
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
