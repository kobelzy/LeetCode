# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


"""
100. 相同的树
给定两个二叉树，编写一个函数来检验它们是否相同。

如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。

示例 1:

输入:       1         1
          / \       / \
         2   3     2   3

        [1,2,3],   [1,2,3]

输出: true
示例 2:

输入:      1          1
          /           \
         2             2

        [1,2],     [1,null,2]

输出: false
示例 3:

输入:       1         1
          / \       / \
         2   1     1   2

        [1,2,1],   [1,1,2]

输出: false
"""


class Solution(object):

    def isSameTree( p: TreeNode, q: TreeNode) -> bool:
        def func(p: TreeNode, q: TreeNode) -> bool:
            # p和q都是none则结束；p和q值不同也结束
            if not p and not q:  # q和p都是None
                return True
            if p is None or q is None:
                return False
            if p.val != q.val:
                return False
            return func(p.left, q.left) and func(p.right, q.right)
        return func(p,q)

    def isSameTree2( p: TreeNode, q: TreeNode) -> bool:
        from collections import deque
        """
        第二种方法：迭代
        """
        def check(p,q):
            if not p and not q:  # q和p都是None
                return True
            if p is None or q is None:
                return False
            if p.val != q.val:
                return False
        deq=deque([(p,q)])
        while deq:
            p,q=deq.popleft()
            if not check(p,q):
                return False
            if p:
                deq.append((p.left,q.left))
                deq.append((p.right,q.right))
        return True
    if __name__ == '__main__':
        ln1 = TreeNode(1)
        ln2 = TreeNode(2)
        ln3 = TreeNode(3)
        ln1.left = ln2
        ln1.right = ln3

        rn1 = TreeNode(1)
        rn2 = TreeNode(2)
        rn3 = TreeNode(3)
        rn1.left = rn2
        rn1.right = rn3

        res = isSameTree2(ln1, rn1)
        print(res)
