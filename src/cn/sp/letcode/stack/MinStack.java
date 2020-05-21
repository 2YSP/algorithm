package cn.sp.letcode.stack;

import java.util.ArrayList;

/**
 * Created by 2YSP on 2019/4/28.
 */
public class MinStack {

    private ArrayList<Integer> theArray;

    private int topOfStack;


    /**
     * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
     * push(x) -- 将元素 x 推入栈中
     * pop() -- 删除栈顶的元素。
     * top() -- 获取栈顶元素。
     * getMin() -- 检索栈中的最小元素。
     */

    /** initialize your data structure here. */
    public MinStack() {
        theArray = new ArrayList();
        topOfStack = -1;
    }

    public void push(int x) {
        theArray.add(x);
        topOfStack++;
    }

    public void pop() {
        if (topOfStack == -1){
            throw new UnsupportedOperationException();
        }
        theArray.remove(topOfStack);
        topOfStack--;
    }

    public int top() {
        if (topOfStack == -1){
            throw new UnsupportedOperationException();
        }
        return  theArray.get(topOfStack);
    }

    public int getMin() {
        int min = theArray.get(0);
        for(int i=0;i<topOfStack+1;i++){
            if (min > theArray.get(i)){
                min = theArray.get(i);
            }
        }
        return min;
    }

    public static void main(String[] args) {
        MinStack obj = new MinStack();
         obj.push(2);
         obj.push(0 );
         obj.push(3);
         obj.push(0);
        int a = obj.getMin();
        obj.pop();
        int b = obj.getMin();
        obj.pop();
        int c =obj.getMin();
        obj.pop();
        int d = obj.getMin();
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);

    }
}
