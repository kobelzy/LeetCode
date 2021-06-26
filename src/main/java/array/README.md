# 数组

对于数组，计算机会在内存中为其申请一段 连续 的空间，并且会记下索引为 0 处的内存地址
查找元素的时间复杂度为 O(N)


## 双指针技巧
需要从两端向中间迭代数组时，可以考虑使用：
    
    def reverseString(self, s):
            i, j = 0, len(s) - 1
            while i < j:
                s[i], s[j] = s[j], s[i]
                i += 1
                j -= 1

题目：

 - 167. 两数之和 II - 输入有序数组
## 快慢指针技巧

    def removeElement(self, nums: List[int], val: int) -> int:
        slow = 0
        n = len(nums)
        for fast in range(n):
            if nums[fast] != val:
                nums[slow] = nums[fast]
                slow += 1
        return slow    

题目：

 - 209. 长度最小的子数组


快慢指针可以用于求区间值这样的场景，外层循环使用一个for循环作为快指针

内部使用一个全局的slow当作慢指针，每次根据条件选择是否进行slow+=1；