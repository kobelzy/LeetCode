"""
581. 最短无序连续子数组
给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。

你找到的子数组应是最短的，请输出它的长度。

示例 1:

输入: [2, 6, 4, 8, 10, 9, 15]
输出: 5
解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
说明 :

输入的数组长度范围在 [1, 10,000]。
输入的数组可能包含重复元素 ，所以升序的意思是<=。

"""
from typing import List


class Solution:
    def findUnsortedSubarray(nums: List[int]) -> int:
        """
        先找到不递增的min；和递减得到的最小元素
        之后在不连续串内与这些边界值比较，寻找出更极端的边界值

        :param nums:
        :return:
        """
        n=len(nums)
        min = 2147483647
        max = -2147483648
        flag = False

        for i in range(1, n):
            if nums[i] < nums[i - 1]:
                flag = True
            if flag and min > nums[i]:
                min = nums[i]
        if not flag:
            return 0
        flag = False
        for i in range(n - 2, -1, -1):
            if nums[i] > nums[i + 1]:
                flag = True
            if flag and nums[i] > max:
                max = nums[i]

        _from = 0
        for i in range(0, n):
            if min < nums[i]:
                _from = i
                break

        _end = len(nums) - 1
        for i in range(len(nums) - 1, -1, -1):
            if max > nums[i]:
                _end = i
                break
        print(min, max)
        print(_from, _end)

        return 0 if _end - _from < 0 else _end - _from + 1

    if __name__ == '__main__':
        print(findUnsortedSubarray(nums=[1, 3, 2, 4, 5]))
        print(findUnsortedSubarray(nums=[1, 2, 3, 4]))
