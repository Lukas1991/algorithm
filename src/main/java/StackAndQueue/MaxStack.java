package StackAndQueue;

import java.util.Stack;

/**
 * only difference is when push, check x >= maxStack.peek()
 */
public class MaxStack {
    Stack<Integer> stack;
    Stack<Integer> maxStack;

    public MaxStack() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }

    public void push(int x) {
        if (maxStack.isEmpty() || x >= maxStack.peek()) {
            maxStack.push(x);
        }
        stack.push(x);
    }

    //Note, compare two Integer
    public void pop() {
        int pop = stack.pop();
        if (maxStack.peek() == pop) {
            maxStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMax() {
        return maxStack.peek();
    }

    public static void main(String[] args) {
        MaxStack obj = new MaxStack();
        obj.push(4);
        obj.push(2);
        System.err.println(obj.getMax());
        obj.push(14);
        System.err.println(obj.getMax());
        obj.pop();
        System.err.println(obj.getMax());
        obj.push(18);
        System.err.println(obj.getMax());
        obj.pop();
        System.err.println(obj.getMax());
        obj.pop();
        System.err.println(obj.getMax());
    }
}
