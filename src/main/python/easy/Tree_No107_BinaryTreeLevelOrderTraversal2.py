"""107. 二叉树的层次遍历 II
给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）

例如：
给定二叉树 [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回其自底向上的层次遍历为：

[
  [15,7],
  [9,20],
  [3]
]
"""
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None
from typing import List
class Solution:
    def levelOrderBottom( root: TreeNode) -> List[List[int]]:
        #从上往下遍历，每一层从左到右，每一层插入到第0个元素
        nodes=[root]
        res=[]
        while nodes:
            node_val=(val.val for val in nodes)
            res.insert(0,node_val)
            next_nodes=[]
            cur_val=[]
            for node in nodes:
                if node:
                    cur_val.append(node.val)
                    next_nodes.extend([node.left,node.right])
            if cur_val:
                res.insert(0,cur_val)
            nodes=next_nodes
        return res
    if __name__ == '__main__':
        root=TreeNode(3)
        second9=TreeNode(9)
        second20=TreeNode(20)
        root.left=second9
        root.right=second20
        second20.left=TreeNode(15)
        second20.right=TreeNode(7)
        res=levelOrderBottom(root)
        for node in res:
            print(node)


