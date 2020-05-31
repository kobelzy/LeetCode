from typing import List

"""
11. 盛最多水的容器
给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

说明：你不能倾斜容器，且 n 的值至少为 2。
"""
class Solution:
    #暴力会超出最大时间
    def maxArea(height: List[int]) -> int:
        n = len(height)
        res = 0
        for i in range(0, n - 1):
            for j in range(i + 1, n):
                high = min(height[i], height[j])
                res = max(res, (j - i) * high)
        return res

    def maxArea2(height: List[int]) -> int:
        left, right = 0, len(height) - 1
        vol = (right - left) * min(height[left], height[right])
        while left < right :
            if height[left] < height[right]:
                left += 1
            else:
                right -= 1
            vol = max(vol, (right - left) * min(height[left], height[right]))
        return vol

    if __name__ == '__main__':
        print(maxArea2([1, 8, 6, 2, 5, 4, 8, 3, 7]))
