package StackAndQueue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

public class StackWithMedian {

    Stack<Integer> stack;
    PriorityQueue<Integer> maxHeap; //lower half
    PriorityQueue<Integer> minHeap; //higher half

    public StackWithMedian() {
        stack = new Stack<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    //logn
    public void push(int x) {
        stack.push(x);

        //add to heaps
        maxHeap.offer(x);
        minHeap.offer(maxHeap.poll());

        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    //O(n) because remove
    public int pop() {
        int a = stack.pop();

        //remove a from heap
        if (maxHeap.peek() >= a) {
            maxHeap.remove(a);
        } else if(minHeap.peek() <= a) {
            minHeap.remove(a);
        }

        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }

        return a;
    }

    //O(1)
    public double getMedian() {
        if (maxHeap.size() == minHeap.size()) {
            if (maxHeap.isEmpty()) {
                return 0.0;
            } else {
                return ((double) maxHeap.peek() + (double) minHeap.peek() ) / 2.0;
            }
        } else {
            return maxHeap.peek();
        }
    }
}
