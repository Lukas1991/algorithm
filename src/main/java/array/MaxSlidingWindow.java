package array;

import java.util.Arrays;
import java.util.LinkedList;


public class MaxSlidingWindow {
    /**
     * Deque, Queue 's first is always the max number in the window
     * when inQueue, remove from last, if smaller than num. Then offer()
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || nums.length < k) {
            return new int[0];
        }

        LinkedList<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];

        for (int i = 0; i < k; i++) {
            inQueue(deque, nums[i]);
        }

        //i = k
        for (int i = k; i < nums.length; i++) {
            res[i-k] = deque.peekFirst();
            outQueue(deque, nums[i-k]);
            inQueue(deque, nums[i]);
        }

        if (!deque.isEmpty()) {
            res[nums.length-k] = deque.peekFirst();
        }

        return res;
    }

    private void inQueue(LinkedList<Integer> deque, int a) {
        while(!deque.isEmpty() && deque.peekLast() < a) {
            deque.removeLast();
        }

        deque.addLast(a);
        //printQueue(deque);
    }

    private void outQueue(LinkedList<Integer> deque, int a) {
        if (!deque.isEmpty() && deque.peekFirst() == a) {
            deque.removeFirst();
        }
    }

    private void printQueue(LinkedList<Integer> queue) {
        StringBuilder sb = new StringBuilder();
        sb.append("Queue: ");
        for(int i=0; i<queue.size(); i++) {
            sb.append(queue.get(i) + ", ");
        }
        System.err.println(sb.toString());
    }

    public static void main(String[] args) {
        MaxSlidingWindow window = new MaxSlidingWindow();
        //int[] res = window.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        int[] res = window.maxSlidingWindow(new int[]{1,3,1,2,0,5}, 3);
        System.err.println("Result: " + Arrays.toString(res));
    }
}
