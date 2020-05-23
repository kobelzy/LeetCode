package easy;


import java.util.Stack;

/**
 * 155. 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 * 示例:
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 * 提示：
 * pop、top 和 getMin 操作总是在 非空栈 上调用。
 */
public class No155_MinStack {
    private Stack<Integer> data;
    private Stack<Integer> helper;

    /**
     * initialize your data structure here.
     */
    public No155_MinStack() {
        data = new Stack<Integer>();
        helper = new Stack<Integer>();
    }

    public void push(int x) {
        data.add(x);
        //该操作保证了出栈时候，对应的位置可以获取到对应阶段的最小值
        if (helper.isEmpty() || helper.peek() >= x) helper.add(x);
        else helper.add(helper.peek());
    }

    /**注意push2 和 pop2方法，保证了不会放入对应步骤中多余的元素*/
    public void push2(int x) {
        data.add(x);
        //该操作保证了出栈时候，对应的位置可以获取到对应阶段的最小值
        if (helper.isEmpty() || helper.peek() >= x) helper.add(x);
    }

    public void pop() {
        data.pop();
        helper.pop();
    }

    public void pop2() {
        int pop = data.pop();
        if (helper.peek() == pop) helper.pop();
    }

    public int top() {
        return data.peek();
    }

    public int getMin() {
        return helper.peek();
    }
}
