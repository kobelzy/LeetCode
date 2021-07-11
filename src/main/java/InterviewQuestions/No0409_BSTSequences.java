package InterviewQuestions;

import java.util.*;

/**
 * 面试题 04.09. 二叉搜索树序列
 * 从左向右遍历一个数组，通过不断将其中的元素插入树中可以逐步地生成一棵二叉搜索树。给定一个由不同节点组成的二叉搜索树，输出所有可能生成此树的数组。
 * 示例：
 * 给定如下二叉树
 *         2
 *        / \
 *       1   3
 * 返回：
 * [
 *    [2,1,3],
 *    [2,3,1]
 * ]
 * 通过次数5,824提交次数12,244
 * @author: Patrick Star
 * @time: 2021/7/11 17:27
 */
public class No0409_BSTSequences {
    public static void main(String[] args) {
        List<Integer> res=new ArrayList<>();
        res.add(1);
        res.add(2);
        res.add(3);
        res.add(4);
        List<Integer> res2=new ArrayList<>(res);
        res.remove(0);
        System.out.println(res);
        System.out.println(res2);
        res=new ArrayList<>(res2);
        System.out.println(res);
    }

    private List<List<Integer>> ress;
    public List<List<Integer>> BSTSequences(TreeNode root){
        this.ress=new ArrayList<>();
        //path用于保存当前遍历的所有数据
        List<Integer> path=new ArrayList<>();
        if(root==null){
            this.ress.add(path);
            return this.ress;
        }

        path.add(root.val);
        //content用于保存当前待遍历的TreeNode
        List<TreeNode> content=new LinkedList<>();
        if(root.left!=null) content.add(root.left);
        if(root.right!=null) content.add(root.right);
        dfs(content,path);
        return this.ress;
    }

    private void dfs(List<TreeNode> content,List<Integer> path){
        if(content.isEmpty()){
            //没有待遍历对象，将当前路径合并到结果中
            this.ress.add(new ArrayList<>(path));
            return;
        }
        //用于保存待提交对象
        List<TreeNode> temp=new ArrayList<>(content);
        for (int i = 0; i < content.size(); i++) {
            TreeNode node=content.get(i);
            path.add(node.val);
            content.remove(i);
            if(node.left !=null) content.add(node.left);
            if(node.right!=null) content.add(node.right);
            dfs(content,path);
            path.remove(path.size()-1);
            content=new ArrayList<>(temp);
        }
    }

    /**
     * 解法2
     */
    List<List<Integer>> resList = new LinkedList<>();
    LinkedList<Integer> res = new LinkedList<>();

    public List<List<Integer>> BSTSequences2(TreeNode root) {
        if (root == null) {
            resList.add(res);
            return resList;
        }
        Set<TreeNode> curLevel=new HashSet<>();
        curLevel.add(root);
        dfs(curLevel);
        return resList;
    }

    private void dfs(Set<TreeNode> curLevel) {
        if (curLevel.size() != 0) {
            resList.add(new LinkedList<>(res));
            return;
        }
        Set<TreeNode> nextLevel = new HashSet<>(curLevel);
        for (TreeNode t : curLevel) {
            res.add(t.val);
            nextLevel.remove(t);
            if (t.left != null) nextLevel.add(t.left);
            if (t.right != null) nextLevel.add(t.right);
            dfs(nextLevel);
            if (t.left != null) nextLevel.remove(t.left);
            if (t.right != null) nextLevel.remove(t.right);
            nextLevel.add(t);
            res.removeLast();
        }
    }

}
