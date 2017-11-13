package Interview.twosigma;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Find Median from Data Stream
 * PriorityQueue implementation provides O(log(n)) time for the enqueing and dequeing methods (offer, poll, remove() and  add);
 * linear time for the remove(Object) and contains(Object) methods;
 * and constant time for the retrieval methods (peek,  element, and size).
 */
public class MedianFinder {

    //The size of two heaps differs at most 1. maxHeap size always >= minHeap size
    PriorityQueue<Integer> maxHeap; //lower half
    PriorityQueue<Integer> minHeap; //higher half

    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    //time complexity: O(log(n))
    public void addNum(int num) {
        maxHeap.offer(num);
        //move max to minHeap
        minHeap.offer(maxHeap.poll());

        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    //time complexity: O(1)
    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            if (maxHeap.isEmpty()) {
                return 0.0;
            } else {
                return (maxHeap.peek() + minHeap.peek()) / 2.0;
            }
        } else {
            return maxHeap.peek();
        }
    }

    public static void main(String[] args) {
        MedianFinder finder = new MedianFinder();
        int[] numbers = {2,3,4};

        for (int num : numbers) {
            finder.addNum(num);
        }

        double result = finder.findMedian();
        System.out.println(result);
    }
}
