package cn.sp.letcode.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by 2YSP on 2019/4/29.
 */
public class MyQueue {

    private Stack<Integer> stack;

    /** Initialize your data structure here. */
    public MyQueue() {
        stack = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {

        int len = stack.size();
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<len;i++){
            list.add(stack.pop());
        }
        stack.push(x);
        for(int j=len-1;j>= 0;j--){
            stack.push(list.get(j));
        }
        System.out.printf("");
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return stack.pop();
    }

    /** Get the front element. */
    public int peek() {
        return stack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack.empty();
    }

    public static void main(String[] args) {
        MyQueue queue  = new MyQueue();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println(queue.peek());
//        System.out.println(queue.pop());
//        System.out.println(queue.empty());
    }
}
