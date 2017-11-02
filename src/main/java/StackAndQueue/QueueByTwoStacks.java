package StackAndQueue;

import java.util.Stack;

//232	Implement Queue using Stacks
public class QueueByTwoStacks<T> {

    /**
     * Question 3.5
     *
     * use s2 to reverse the order of the elements, when pop, pop from s2. we don't need to move everything from s1 to s2 every
     * time. Leave the old elements in s2, push the new element into s1. when s2 is empty, we'll then transfer all the elements from
     * s1 to s2
     */
    Stack<T> s1, s2;

    T front;

    public QueueByTwoStacks() {
        s1 = new Stack<T>();
        s2 = new Stack<T>();
    }

    public int size() {
        return s1.size() + s2.size();
    }

    //Time complexity : O(1)
    public void add(T value) {
        if (s1.isEmpty()) {
            front = value;
        }
        s1.push(value);
    }

    //Time complexity : O(1)
    public T peek() {
        if (s2.isEmpty()) {
            return front;
        } else {
            return s2.peek();
        }
    }

    //Time complexity: Amortized O(1), Worst-case O(n)
    public T remove() {
        if (s2.isEmpty()) {
            shiftStack();
        }

        return s2.pop();
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
