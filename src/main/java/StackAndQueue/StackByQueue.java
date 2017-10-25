package StackAndQueue;

import java.util.LinkedList;
import java.util.Queue;

//225. Implement Stack using Queues
public class StackByQueue {

    Queue<Integer> q = new LinkedList<>();

    /** Initialize your data structure here. */
    public StackByQueue() {
    }

    /** Push element x onto stack. */
    /**
     * add x to end of queue, move all elements before x one by one, and add back to end of queue one by one
     * Time: O(n)
     */
    public void push(int x) {
        q.offer(x);
        int count = q.size();
        while (count > 1) {
            q.offer(q.poll());
            count--;
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return q.poll();
    }

    /** Get the top element. */
    public int top() {
        return q.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q.isEmpty();
    }
}
