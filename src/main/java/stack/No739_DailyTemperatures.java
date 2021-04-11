package stack;

import java.util.Stack;

/**
 * 739. 每日温度
 * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 */
public class No739_DailyTemperatures {

    /**
     * 单调栈
     *是O(n)的复杂度
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        Stack<Integer> stack = new Stack<>();//只用来存放索引
        for (int i = T.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && T[stack.peek()] <= T[i]) stack.pop();// 只留下栈中比当前值大的，
            res[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return res;
    }

    /**
     * 暴力解法
     *
     * @param T
     * @return
     */
    public int[] dailyTemperatures2(int[] T) {
        int[] res = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            for (int j = i; j < T.length; j++) {//这里不用担心啊j越界，因为超出就不会继续循环了
                if (T[j] > T[i]) {
                    res[i] = j - i;
                    break;
                }
            }
        }
        return res;
    }
}
