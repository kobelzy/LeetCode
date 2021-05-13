# 二分查找
二分查找是一种非常高效的查找算法，高效到什么程度呢？我们来分析一下它的时间复杂度。

假设数据大小是 n，每次查找后数据都会缩小为原来的一半，也就是会除以 2。最坏情况下，直到查找区间被缩小为空，才停止。

被查找区间的大小变化为：

    n, n/2, n/4, n/8, ..., n/(2^k)

可以看出来，这是一个等比数列。其中 n/(2^k)=1 时，k 的值就是总共缩小的次数。而每一次缩小操作只涉及两个数据的大小比较，所以，经过了 k 次区间缩小操作，时间复杂度就是 O(k)。通过 n/(2^k)=1，我们可以求得 k=log2n，所以时间复杂度就是 O(logn)。

 1. 循环退出条件是 low <= high，而不是 low < high；
 2. mid 的取值，可以是 mid = (low + high) / 2，但是如果 low 和 high 比较大的话，low + high 可能会溢出，所以这里写为 mid = (low + high) >>> 1；
 3. low 和 high 的更新分别为 low = mid + 1、high = mid - 1。

## 1 标准解法


    int binarySearch(int[] nums, int target){
      if(nums == null || nums.length == 0)
        return -1;
    
      int left = 0, right = nums.length - 1;
      while(left <= right){
        // Prevent (left + right) overflow
        int mid = left + (right - left) / 2;
        if(nums[mid] == target){ return mid; }
        else if(nums[mid] < target) { left = mid + 1; }
        else { right = mid - 1; }
      }
    
      // End Condition: left > right
      return -1;
    }
    
题目：

 - 704.二分查找 
 - 33.搜索旋转排序数组
 - 69.x的平方根
 - 374.猜数字大小   



## 2 二分查找算法四种常见的变形问题

 - 查找第一个值等于给定值的元素
 - 查找最后一个值等于给定值的元素
 - 查找第一个大于等于给定值的元素
 - 查找最后一个小于等于给定值的元素

### 2.1 查找第一个值等于给定值的元素

    public static int search(int[] nums, int val) {
    int n = nums.length;
    int low = 0, high = n - 1;
    while (low <= high) {
        int mid = (low + high) >>> 1;
        if (nums[mid] < val) {
            low = mid + 1;
        } else if (nums[mid] > val) {
            high = mid - 1;
        } else {
            // 如果nums[mid]是第一个元素，或者nums[mid-1]不等于val
            // 说明nums[mid]就是第一个值为给定值的元素
            if (mid == 0 || nums[mid - 1] != val) {
                return mid;
            }
            high = mid - 1;
        }
    }
    return -1;
    }



### 2.2 查找最后一个值等于给定值的元素

     public static int search(int[] nums, int val) {
        int n = nums.length;
        int low = 0, high = n - 1;
        while (low <= high) {
           int mid = (low + high) >>> 1;
           if (nums[mid] < val) {
              low = mid + 1;
           } else if (nums[mid] > val) {
              high = mid - 1;
           } else {
           // 如果nums[mid]是最后一个元素，或者nums[mid+1]不等于val
           // 说明nums[mid]就是最后一个值为给定值的元素
              if (mid == n - 1 || nums[mid + 1] != val) {
                 return mid;
              }
              low = mid + 1;
           }
        }
        return -1;
     }

### 2.3 查找第一个大于等于给定值的元素

    public static int search(int[] nums, int val) {
       int low = 0, high = nums.length - 1;
       while (low <= high) {
       int mid = (low + high) >>> 1;
          if (nums[mid] < val) {
             low = mid + 1;
          } else {
          // 如果nums[mid]是第一个元素，或者nums[mid-1]小于val
          // 说明nums[mid]就是第一个大于等于给定值的元素
             if (mid == 0 || nums[mid - 1] < val) {
                return mid;
             }
             high = mid - 1;
          }
       }
       return -1;
    }


### 2.4 查找最后一个小于等于给定值的元素
   
    public static int search(int[] nums, int val) {
       int n = nums.length;
       int low = 0, high = n - 1;
          while (low <= high) {
          int mid = (low + high) >>> 1;
             if (nums[mid] > val) {
                high = mid - 1;
             } else {
                // 如果nums[mid]是最后一个元素，或者nums[mid+1]大于val
                // 说明nums[mid]就是最后一个小于等于给定值的元素
                if (mid == n - 1 || nums[mid + 1] > val) {
                return mid;
             }
             low = mid + 1;
             }
          }
       return -1;
    }
 


## 3 模板二 :用于查找需要访问数组中当前索引及其值直接右令居索引的元素或条件。

通过nums[mid]不能直接得到结果，可能是其中一个解的情况下，使用该方法。

关键属性

 - 一种实现二分查找的高级方法。
 - 查找条件需要访问元素的直接右邻居。
 - 使用元素的右邻居来确定是否满足条件，并决定是向左还是向右。
 - 保证查找空间在每一步中至少有 2 个元素。
 - 需要进行后处理。 当你剩下 1 个元素时，循环 / 递归结束。 需要评估剩余元素是否符合条件。


区分语法

 - 初始条件：left = 0, right = length
 - 终止：left == right
 - 向左查找：right = mid
 - 向右查找：left = mid+1
 
 代码
 
    int binarySearch(int[] nums, int target){
      if(nums == null || nums.length == 0)
        return -1;
    
      int left = 0, right = nums.length;
      while(left < right){
        // Prevent (left + right) overflow
        int mid = left + (right - left) / 2;
        if(nums[mid] == target){ return mid; }
        else if(nums[mid] < target) { left = mid + 1; }
        else { right = mid; }
      }
    
      // Post-processing:
      // End Condition: left == right
      if(left != nums.length && nums[left] == target) return left;
      return -1;
    }
    
    
题目：
 - 278.第一个错误的版本   
 - 153.寻找旋转排序数组中的最小值
 - 162.寻找峰值
 
## 4 模板三 用于搜索需要访问当前索引及其在数组中的直接左右邻居索引的元素
 
关键属性

 - 实现二分查找的另一种方法。
 - 搜索条件需要访问元素的直接左右邻居。
 - 使用元素的邻居来确定它是向右还是向左。
 - 保证查找空间在每个步骤中至少有 3 个元素。
 - 需要进行后处理。当剩下 2 个元素时，循环 / 递归结束。需要评估其余元素是否符合条件。


区分语法

 - 初始条件：left = 0, right = length-1
 - 终止：left + 1 == right
 - 向左查找：right = mid
 - 向右查找：left = mid

代码：
 
    int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
    
        int left = 0, right = nums.length - 1;
        while (left + 1 < right){
            // Prevent (left + right) overflow
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
    
        // Post-processing:
        // End Condition: left + 1 == right
        if(nums[left] == target) return left;
        if(nums[right] == target) return right;
        return -1;
    } 
    
题目：

 - 34. 在排序数组中查找元素的第一个和最后一个位置    
 
 
 
## 四、总结

 | 形式 | 结论与建议 |
 |  :----: | :----: |
 | while (left <= right) | 简单问题用，在循环体里能找到答案以后退出。 |
 | while (left < right) | 复杂问题用，把答案留到退出循环以后，再判断。是解决二分问题的利器，尤其在边界问题用，这种方式考虑细节最少，但是需要一定练习才能灵活运用。 |
 | while (left + 1 < right) | 不建议，本质上和 while (left <= right) 写法一样，盲目套这个所谓的最无脑模板，反而学不会二分。 |
 
 
 while(left <= right) 与 while(left < right) 写法的区别
 这一部分也来自评论区网友的提问，我的理解如下：
 
 首先抓住它们最主要的特征：
 
 while(left <= right) 在退出循环的时候 left = right + 1，即 right 在左，left 在右；
 while(left < right) 在退出循环的时候，有 left == right 成立。
 我的经验是 left <= right 用在简单的二分问题中，如果题目要我们找的数的性质很简单，可以用这种写法，在循环体里找到了就退出。
 
 在一些复杂问题中，例如找一些边界的值（就比如当前这个问题），用 while(left < right) 其实是更简单的，把要找的数留到最后，在退出循环以后做判断。我觉得最重要的原因是退出循环以后有 left == right 成立，这种思考问题的方式不容易出错。
 
 while(left < right) 写法难点在于理解：初学的时候很难理解出现死循环的原因。特别是很难理解分支的取法决定中间数的取法。不过通过练习和调试，把这一关过了，相信解决一些难度较大的额额分查找问题就相对容易了。建议大家尝试使用 while(left < right) 的方式去解决一些较困难的问题。
 while (left + 1 < right) 的写法
 一些资料上号称这是最无脑的写法，但是我有一些不同的意见：
 
 首先 while (left + 1 < right) 这种写法就很奇怪；
 其次，这种写法在设置边界的时候屏蔽了细节，全部写成 left = mid 和 right = mid，没有加 1 减 1，这一点虽然号称是优点，但我觉得是缺点，加 1 减 1 完全可以分析出来，屏蔽这个细节不能算是优点；
 退出循环的时候必须针对 left 和 right 编写逻辑，这是一个负担，增加了出错的可能；
 如果拿这种写法做当前这道题（LeetCode 第 34 题），就会发现代码很不好写。
 