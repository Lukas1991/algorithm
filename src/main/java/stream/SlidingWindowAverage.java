package stream;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 346. Moving Average from Data Stream
 */
public class SlidingWindowAverage {
    Queue<Integer> q;
    int size;
    double sum = 0.0;

    /** Initialize your data structure here. */
    public SlidingWindowAverage(int size) {
        q = new LinkedList<>();
        this.size = size;
    }

    public double next(int val) {
        if (q.size() == size) {
            int first = q.poll();
            sum -= first;
        }

        q.offer(val);
        sum += val;

        return sum / q.size();
    }
}
