package binaryTree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 297. 二叉树的序列化与反序列化
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * <p>
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 * 示例 2：
 * <p>
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：root = [1]
 * 输出：[1]
 * 示例 4：
 * <p>
 * 输入：root = [1,2]
 * 输出：[1,2]
 * 提示：
 * 树中结点数在范围 [0, 104] 内
 * -1000 <= Node.val <= 1000
 */
public class No297_SerializeAndDeserializeBinaryTree {
    public static void main(String[] args) {
        No297_SerializeAndDeserializeBinaryTree t = new No297_SerializeAndDeserializeBinaryTree();
        String line="1,2,3,None,None,4,5";
        System.out.println(t.deserialize(line));
    }


    /**
     * 基于先序遍历的序列化与反序列化
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        rSerialize(root, sb);
        return sb.toString();
    }

    private StringBuilder rSerialize(TreeNode root, StringBuilder sb) {
        if (root == null) sb.append("None,");
        else {
            sb.append(root.val + ",");
            rSerialize(root.left, sb);
            rSerialize(root.right, sb);
        }
        return sb;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] split = data.split(",");
        List<String> list = new LinkedList<>(Arrays.asList(split));
        return rDeserialize(list);
    }

    public TreeNode rDeserialize(List<String> data) {
        if (data.get(0).equals("None")) {
            data.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(data.get(0)));
        data.remove(0);
        root.left = rDeserialize(data);
        root.right = rDeserialize(data);
        return root;
    }
}
