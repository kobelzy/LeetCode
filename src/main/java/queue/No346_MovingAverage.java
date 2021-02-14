package queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 346. 数据流中的移动平均值
 * 给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算其所有整数的移动平均值。
 *
 * 示例:
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 */
public class No346_MovingAverage {

    public static void main(String[] args) {
        No346_MovingAverage m = new No346_MovingAverage(3);
        System.out.println(m.next(1));
        System.out.println(m.next(10));
        System.out.println(m.next(3));
        System.out.println(m.next(5));
    }
    int count=0;
    int windowSum=0;
    int size=0;
    Queue<Integer> queue=new LinkedList<>();
    public No346_MovingAverage(int windowNum ){
        this.size=windowNum;
    }
    public double next(int val){
        count+=1;
        queue.offer(val);
        int tail=count>size ? queue.poll(): 0;
        windowSum=windowSum-tail+val;
        return windowSum*(1.0)/ Math.min(size,count);
    }
}
