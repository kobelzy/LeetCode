package InterviewQuestions;

import java.util.Stack;

/**
 * 面试题 03.05. 栈排序
 * 栈排序。 编写程序，对栈进行排序使最小元素位于栈顶。最多只能使用一个其他的临时栈存放数据，但不得将元素复制到别的数据结构（如数组）中。该栈支持如下操作：push、pop、peek 和 isEmpty。当栈为空时，peek 返回 -1。
 * 示例1:
 *  输入：
 * ["SortedStack", "push", "push", "peek", "pop", "peek"]
 * [[], [1], [2], [], [], []]
 *  输出：
 * [null,null,null,1,null,2]
 * 示例2:
 *  输入：
 * ["SortedStack", "pop", "pop", "push", "pop", "isEmpty"]
 * [[], [], [], [1], [], []]
 *  输出：
 * [null,null,null,null,null,true]
 * 说明:
 * 栈中的元素数目在[0, 5000]范围内。
 * @author: Patrick Star
 * @time: 2021/6/30 20:09
 */
public class IQ0305_SortOfStacks {

    /*
     * 维护两个栈，两个栈的数据整体有序：
     * minStack - 1，2，3，4，5
     * maxStack - 100,99,98
     * 从maxStack pop到minStack后，minStack依旧从小到大有序，
     * 反过来则maxStack始终从打到小有序
     * */
    Stack<Integer> minStack = new Stack<>();
    Stack<Integer> maxStack = new Stack<>();

    public IQ0305_SortOfStacks() {

    }

    public void push(int val) {
        int maxValue = maxStack.isEmpty() ? Integer.MAX_VALUE : maxStack.peek();
        int minValue = minStack.isEmpty() ? Integer.MIN_VALUE : minStack.peek();
        while (true) {

            if (val < minValue) {
                maxStack.push(minStack.pop());
                minValue = minStack.isEmpty() ? Integer.MIN_VALUE : minStack.peek();
                ;
            } else if (val > maxValue) {
                minStack.push(maxStack.pop());
                maxValue = maxStack.isEmpty() ? Integer.MAX_VALUE : maxStack.peek();
            } else {
                maxStack.push(val);
                break;
            }
        }
    }

    public void pop() {
        while (!minStack.isEmpty()) maxStack.push(minStack.pop());
        if (!maxStack.isEmpty()) maxStack.pop();
    }

    public int peek() {
        while (!minStack.isEmpty()) maxStack.push(minStack.pop());
        return maxStack.isEmpty() ? -1 : maxStack.peek();
    }

    public boolean isEmpty() {
        return minStack.isEmpty() && maxStack.isEmpty();
    }
}
