package StackAndQueue;

import java.util.Stack;

public class MinStack {
    Stack<Integer> stack;
    Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public static void main(String[] args) {
        System.err.println(2 < new Integer(3)); //true
        System.err.println(3 == new Integer(3)); //true
        System.err.println(new Integer(3) == new Integer(3)); //false

        MinStack obj = new MinStack();
        obj.push(512);
        obj.push(-1024);
        obj.push(-1024);
        obj.push(512);

        obj.pop();
        System.err.println(obj.getMin());
        obj.pop();
        System.err.println(obj.getMin());
        obj.pop();
        System.err.println(obj.getMin());
    }

    //Note: when minStack is empty, x <= minStack.peek
    public void push(int x) {
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
        stack.push(x);
    }

    //Note, compare two Integer
    public void pop() {
        if (stack.peek().intValue() == minStack.peek().intValue()) {
            minStack.pop();
        }
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
