package swordFingerOffer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 剑指 Offer 37. 序列化二叉树  * 297题目相同
 * 请实现两个函数，分别用来序列化和反序列化二叉树。
 * 你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * 提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * 示例：
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 *
 *
 * @description:
 * @author: Patrick Star
 * @time: 2021/6/30 21:11
 */
public class Offer37_SerliazeBinaryTree {
    public static void main(String[] args) {
        String[] split="1,2,3,".split(",");
        List<String> list=new LinkedList<>(Arrays.asList(split));
        System.out.println(list);

    }
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb =new StringBuilder();
        runSerialize(root,sb);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] split=data.split(",");
        List<String> list=new LinkedList<>(Arrays.asList(split));
        return runDeserialize(list);
    }

    private void runSerialize(TreeNode root,StringBuilder sb){
        if(root==null){
            sb.append("null,");
        }else{
            sb.append(root.val+",");
            runSerialize(root.left,sb);
            runSerialize(root.right,sb);
        }
    }

    private TreeNode runDeserialize(List<String> data){
        if(data.get(0).equals("null")){
            data.remove(0);
            return null;
        }
        TreeNode root=new TreeNode(Integer.parseInt(data.get(0)));
        data.remove(0);
        root.left=runDeserialize(data);
        root.right=runDeserialize(data);
        return root;
    }
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

    TreeNode(int val, TreeNode left, TreeNode right) {        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "{" + "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}