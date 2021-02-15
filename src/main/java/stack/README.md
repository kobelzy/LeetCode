# 栈

在 LIFO 数据结构中，将首先处理添加到队列中的最新元素。

与队列不同，栈是一个 LIFO 数据结构。

    stack.push(cur) //入栈
    stack.pop() //出栈
    stack.peek() //查看栈顶元素
    
    
## DFS 深度优先遍历
    
与 BFS 类似，深度优先搜索（DFS）是用于 在树/图中遍历/搜索 的另一种重要算法。

我们可以用 DFS 进行 前序遍历，中序遍历 和 后序遍历。在这三个遍历顺序中有一个共同的特性：除非我们到达最深的结点，否则我们永远不会回溯 。

这也是 DFS 和 BFS 之间最大的区别，BFS永远不会深入探索，除非它已经在当前层级访问了所有结点。

通常，使用递归实现 DFS。栈在递归中起着重要的作用。与 BFS 类似，`深度优先搜索`（DFS）也可用于查找从根结点到目标结点的路径。
DFS更早访问的结点可能不是更靠近根结点的结点。因此，在 DFS 中找到的第一条路径可能不是最短路径。


    /*
     * Return true if there is a path from cur to target.
     * 没有调用栈？起始递归调用就是使用了栈，Java中使用虚拟机栈
     */
    boolean DFS(Node cur, Node target, Set<Node> visited) {
        return true if cur is target;
        for (next : each neighbor of cur) {
            if (next is not in visited) {
                add next to visted;
                return true if DFS(next, target, visited) == true;
            }
        }
        return false;
    }


⚠️ 使用递归的方式，有个缺点，就是调用栈太深的话，就可能会导致栈溢出，那么可以显式的使用栈来计算：


    boolean DFS(int root, int target) {
        Set<Node> visited;
        Stack<Node> s;
        add root to s;
        while (s is not empty) {
            Node cur = the top element in s;
            return true if cur is target;
            for (Node next : the neighbors of cur) {
                if (next is not in visited) {
                    add next to s;
                    add next to visited;
                }
            }
            remove cur from s;
        }
        return false;
    }

## DFS网格遍历代码模版

链接：https://leetcode-cn.com/problems/number-of-islands/solution/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/

    void dfs(int[][] grid, int r, int c) {
        // 判断 base case
        if (!inArea(grid, r, c)) {
            return;
        }
        // 如果这个格子不是岛屿，直接返回
        if (grid[r][c] != 1) {
            return;
        }
        grid[r][c] = 2; // 将格子标记为「已遍历过」
        
        // 访问上、下、左、右四个相邻结点
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }
    
    // 判断坐标 (r, c) 是否在网格中
    boolean inArea(int[][] grid, int r, int c) {
        return 0 <= r && r < grid.length 
                && 0 <= c && c < grid[0].length;
    }


如何避免这样的重复遍历呢？答案是标记已经遍历过的格子。以岛屿问题为例，我们需要在所有值为 1 的陆地格子上做 DFS 遍历。每走过一个陆地格子，就把格子的值改为 2，这样当我们遇到 2 的时候，就知道这是遍历过的格子了。也就是说，每个格子可能取三个值：

 - 0 —— 海洋格子
 - 1 —— 陆地格子（未遍历过）
 - 2 —— 陆地格子（已遍历过）

这种方法看似很巧妙，但实际上有很大隐患，因为这样我们就无法区分「海洋格子」和「已遍历过的陆地格子」了。如果题目更复杂一点，这很容易出 bug。

对应题目：

 - 200. 岛屿数量 （Easy）
 - 463. 岛屿的周长 （Easy）
 - 695. 岛屿的最大面积 （Medium）
 - 827. 最大人工岛 （Hard）