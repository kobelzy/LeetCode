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
                
## 快慢指针技巧

    def removeElement(self, nums: List[int], val: int) -> int:
        slow = 0
        n = len(nums)
        for fast in range(n):
            if nums[fast] != val:
                nums[slow] = nums[fast]
                slow += 1
        return slow    