package queue;

/**
 * 622. 设计循环队列
 * 设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
 * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
 * 你的实现应该支持如下操作：
 * MyCircularQueue(k): 构造器，设置队列长度为 k 。
 * Front: 从队首获取元素。如果队列为空，返回 -1 。
 * Rear: 获取队尾元素。如果队列为空，返回 -1 。
 * enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
 * deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
 * isEmpty(): 检查循环队列是否为空。
 * isFull(): 检查循环队列是否已满。
 * 示例：
 * MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
 * circularQueue.enQueue(1);  // 返回 true
 * circularQueue.enQueue(2);  // 返回 true
 * circularQueue.enQueue(3);  // 返回 true
 * circularQueue.enQueue(4);  // 返回 false，队列已满
 * circularQueue.Rear();  // 返回 3
 * circularQueue.isFull();  // 返回 true
 * circularQueue.deQueue();  // 返回 true
 * circularQueue.enQueue(4);  // 返回 true
 * circularQueue.Rear();  // 返回 4
 */
public class No622_DesignCircularQueue {
    /**
     * 通过这个公式，可以计算得出尾部的索引
     * tailIndex=(headIndex+count−1)modcapacity
     */
    int[] data;
    int headPointer;
    int count;
    int capacity;

    public No622_DesignCircularQueue(int k) {
        data = new int[k];
        headPointer = 0;
        count = 0;
        capacity = k;
    }

    /**
     * 向循环队列插入一个元素。如果成功插入则返回真。
     *
     * @param value
     * @return
     */
    public boolean enQueue(int value) {
        if (count == capacity) return false;
        int dataIndex = (headPointer + count) % capacity; //tail的索引
        this.data[dataIndex]=value;
        count+=1;
        return true;
    }

    /**
     * 从循环队列中删除头一个元素。如果成功删除则返回真。
     * @return
     */
    public boolean deQueue() {
    if(count==0) return false;
    this.headPointer=(this.headPointer + 1) % this.capacity;//指针向后移动一位
    count-=1;
    return true;
    }

    /**
     * 从队首获取元素。如果队列为空，返回 -1 。
     *
     * @return
     */
    public int Front() {
        if(count==0) return -1;
        return data[headPointer];
    }

    /**
     * Rear: 获取队尾元素。如果队列为空，返回 -1 。
     * @return
     */
    public int Rear() {
        if(count==0) return -1;
        int tailIndex=(this.headPointer + this.count - 1) % this.capacity;//由count推算已有的数据，必须-1
        return this.data[tailIndex];
    }

    public boolean isEmpty() {
        return count==0;
    }

    public boolean isFull() {
        return this.count==this.capacity;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
