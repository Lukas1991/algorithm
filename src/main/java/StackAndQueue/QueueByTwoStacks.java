package StackAndQueue;

import java.util.Stack;

public class QueueByTwoStacks<T> {

    /**
     * Question 3.5
     *
     * use s2 to reverse the order of the elements, when pop, pop from s2. we don't need to move everything from s1 to s2 every
     * time. Leave the old elements in s2, push the new element into s1. when s2 is empty, we'll then transfer all the elements from
     * s1 to s2
     */
    Stack<T> s1, s2;

    public QueueByTwoStacks() {
        s1 = new Stack<T>();
        s2 = new Stack<T>();
    }

    public int size() {
        return s1.size() + s2.size();
    }

    public void add(T value) {
        s1.push(value);
    }

    public T peek() {
        if (!s2.isEmpty()) {
            return s2.peek();
        } else {
            shiftStack();
            return s2.peek();
        }
    }

    public T remove() {
        if (!s2.isEmpty()) {
            return s2.pop();
        } else {
            shiftStack();
            return s2.pop();
        }
    }

    //transfer all in s1 to s2
    private void shiftStack() {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
    }

    public static void main(String[] args) {
        QueueByTwoStacks<Integer> test = new QueueByTwoStacks();
        test.add(1);
        test.add(2);
        test.add(3);
        System.out.println(test.remove());
        test.add(4);
        System.out.println(test.remove());
        System.out.println(test.remove());
        System.out.println(test.remove());

    }

}
