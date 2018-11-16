 class TreeNode:
     def __init__(self, x):
         self.val = x
         self.left = None
         self.right = None

class Solution:
    def isValidBST(self, root):
        """
        :type root: TreeNode
        :rtype: bool
        """
        inorder=self.inorder(root)
        return inorder ==list(sorted(set(inorder)))

    def inorder(self,root):
        if root is None:
            return []
        return self.inorder(root.left)+[root.val]+self.inorder(root.right)