# 二叉树

二叉树分类
完全二叉树：每一层紧凑靠左排列
满二叉树：是一种特殊的完全二叉树，每层都是是满的，像一个稳定的三角形：
二叉搜索树：

## 1 二叉树递归

    void	traverse(TreeNode	root)	{
        //	root	需要做什么？在这做。
        //	其他的不⽤root操⼼，抛给框架	
        traverse(root.left);	
        traverse(root.right); } 

二叉搜索树递归

    void BST(TreeNode root,	int	target)	{
        if(root.val == target)//找到⽬标，做点什么
        if(root.val	<target) BST(root.right,	target);	
        if(root.val>target)	BST(root.left,	target); 
    } 