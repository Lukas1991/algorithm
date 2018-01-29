package heap;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * PriorityQueue implementation provides O(log(n)) time for the enqueing and dequeing methods (offer, poll);
 * O(n) for the remove(Object) and contains(Object) methods;
 *
 * O(n * k)
 */
public class MedianSlidingWindow {

    PriorityQueue<Integer> maxHeap; //lower half
    PriorityQueue<Integer> minHeap; //higher half

    //n⋅(log(k) + k) ~ n*k
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || nums.length < k) {
            return new double[0];
        }
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        minHeap = new PriorityQueue<>();

        LinkedList<Integer> deque = new LinkedList<>();
        double[] res = new double[nums.length - k + 1];

        for (int i = 0; i < k; i++) {
            addNum(nums[i]);
        }

        //i = k
        for (int i = k; i < nums.length; i++) {
            res[i-k] = findMedian();
            outQueue(nums[i-k]);
            addNum(nums[i]);
        }

        res[nums.length-k] = findMedian();
        return res;
    }

    //take O(k) time
    private void outQueue(int a) {
        if (maxHeap.peek() >= a) {
            maxHeap.remove(a);
        } else if(minHeap.peek() <= a) {
            minHeap.remove(a);
        }

        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public void addNum(int num) {
        maxHeap.offer(num);
        //move max to minHeap
        minHeap.offer(maxHeap.poll());

        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
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
