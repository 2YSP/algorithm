package cn.sp.letcode.stack;

/**
 * Created by 2YSP on 2019/4/29.
 */
public class MyStack {

    private int[] theArray;

    private int currentSize;

    private int font;

    private int back;

    /** Initialize your data structure here. */
    public MyStack() {
        theArray = new int[20];
    }

    /** Push element x onto stack. */
    public void push(int x) {
        if (currentSize == 0){
            theArray[back] = x;
            ++currentSize;
        }else {
            ++currentSize;
            ++back;
            if (back > theArray.length -1){
                back = back - theArray.length;
            }
            theArray[back] = x;

        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
//        int ele = theArray[font];
//        ++font;
//        if (font > theArray.length -1){
//            font = font - theArray.length;
//        }
//        --currentSize;
        int ele = theArray[back];
        --back;
        if (back < 0){
            back = back + theArray.length;
        }
        --currentSize;
        return ele;
    }

    /** Get the top element. */
    public int top() {
        return theArray[back];
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return currentSize == 0;
    }
}
