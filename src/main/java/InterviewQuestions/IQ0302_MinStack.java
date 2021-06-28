package InterviewQuestions;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 面试题 03.02. 栈的最小值
 * 请设计一个栈，除了常规栈支持的pop与push函数以外，还支持min函数，该函数返回栈元素中的最小值。执行push、pop和min操作的时间复杂度必须为O(1)。
 * 示例：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 * @author: Patrick Star
 * @time: 2021/6/28 20:32
 */
public class IQ0302_MinStack {
    public static void main(String[] args) {
        Deque<Integer> xStack=new LinkedList<>();
        xStack.push(1);//加入到队列头部
        xStack.push(2);
        xStack.offer(3);//加入到队列尾部
        System.out.println(xStack);
        System.out.println(xStack.peek());
        System.out.println(xStack.poll());//出头
        System.out.println(xStack.pop());//出头
    }
        Deque<Integer> xStack;
        Deque<Integer> minStack;

        public IQ0302_MinStack() {
            xStack = new LinkedList<Integer>();
            minStack = new LinkedList<Integer>();
            minStack.push(Integer.MAX_VALUE);
        }

        public void push(int x) {
            xStack.push(x);
            minStack.push(Math.min(minStack.peek(), x));
        }

        public void pop() {
            xStack.pop();
            minStack.pop();
        }

        public int top() {
            return xStack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }

}
