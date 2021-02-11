"""
31. 下一个排列
实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

必须原地修改，只允许使用额外常数空间。

以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
"""

from typing import List
class Solution:

    def nextPermutation(nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """

        def reverse(nums: List[int], start: int):
            i, j = start, len(nums) - 1
            while i < j:
                swap(nums, i, j)
                i += 1
                j -= 1

        def swap(nums: List[int], i: int, j: int):
            tmp = nums[i]
            nums[i] = nums[j]
            nums[j] = tmp
            
        i = len(nums) - 2
        while i >= 0 and nums[i + 1] <= nums[i]:
            i -= 1
        if i >= 0:
            j = len(nums) - 1
            while j >= 0 and nums[j] <= nums[i]:
                j -= 1
            swap(nums, i, j)
        reverse(nums, i + 1)
    if __name__ == '__main__':
        l=[1,2,3]
        nextPermutation(l)
        print(l)


