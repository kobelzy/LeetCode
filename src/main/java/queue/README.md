# 队列

## 基于队列的BFS

> 主要用于解决遍历或找出最短路径的问题
>
1. 结点的处理顺序是什么？

在第一轮中，我们处理根结点。在第二轮中，我们处理根结点旁边的结点；在第三轮中，我们处理距根结点两步的结点；等等等等。

与树的层序遍历类似，越是接近根结点的结点将越早地遍历。

如果在第 k 轮中将结点 X 添加到队列中，则根结点与 X 之间的最短路径的长度恰好是 k。也就是说，第一次找到目标结点时，你已经处于最短路径中。

2. 队列的入队和出队顺序是什么？

如上面的动画所示，我们首先将根结点排入队列。然后在每一轮中，我们逐个处理已经在队列中的结点，并将所有邻居添加到队列中。值得注意的是，新添加的节点不会立即遍历，而是在下一轮中处理。

结点的处理顺序与它们添加到队列的顺序是完全相同的顺序，即先进先出（FIFO）。这就是我们在 BFS 中使用队列的原因。


模版

    int BUF(Node root,Node target){
        Queue<Node> queue;
        int step=0;
        Set<Node> visited;//可选
        visited=add(root);//可选
        queue.offer(root);第一层加入队列
        while(!queue.isEmpty()){
            int size=queue.size();
            for(int i=0;i<size;i++){
                Node cur=queue.poll();
                if(cur==target)return step;
                if(visited.contains(root)) continue; //可选
                //将相邻节点加入queue，如果是树的话，是left和right
                for (Node next : the neighbors of cur) {
                    if (!visited.contains(next)) {
                        queue.offer(next);
                        visited.add(next);//可选
                    }
                }
            }
            step+=1;
        }
        return -1;
    }
 
 有两种情况不需要visited来防止重复处理：
  - 完全确定没有循环，例如，在树遍历中；
  - 确实希望多次将结点添加到队列中。


