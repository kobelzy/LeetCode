package InterviewQuestions;

import java.util.Stack;

/**
 * 面试题 03.04. 化栈为队
 * 实现一个MyQueue类，该类用两个栈来实现一个队列。
 * 示例：
 * MyQueue queue = new MyQueue();
 * queue.push(1);
 * queue.push(2);
 * queue.peek();  // 返回 1
 * queue.pop();   // 返回 1
 * queue.empty(); // 返回 false
 * 说明：
 * 你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size 和 is empty 操作是合法的。
 * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 * 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
 * @author: Patrick Star
 * @time: 2021/6/28 21:15
 */
public class IQ0304_ImplementQueueUsingStacks {
    public static void main(String[] args) {
        IQ0304_ImplementQueueUsingStacks queue = new IQ0304_ImplementQueueUsingStacks();

        queue.push(1);
        queue.push(2);
        System.out.println(queue.peek());  // 返回 1
        System.out.println(queue.pop());   // 返回 1
        System.out.println(queue.empty()); // 返回 false

    }
    Stack<Integer> stack1=new Stack<>();
    Stack<Integer> stack2=new Stack<>();
    /** Initialize your data structure here. */
    public IQ0304_ImplementQueueUsingStacks() {

    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        stack1.push(x);
    }

    private void move(){
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
    }
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        move();
        return stack2.pop();
    }

    /** Get the front element. */
    public int peek() {
        move();
        int value=stack2.pop();
        stack2.push(value);
        return value;
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
