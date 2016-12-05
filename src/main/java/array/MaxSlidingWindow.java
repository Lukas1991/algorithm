package array;

import java.util.Arrays;
import java.util.LinkedList;


public class MaxSlidingWindow {
    /**
     * Queue 's first is always the max number in the window
     * when inQueue, remove from last, if smaller than num. Then offer()
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int[] res = new int[nums.length - k + 1];
        LinkedList<Integer> queue = new LinkedList<>();

        for (int i=0; i<k-1; i++) {
            inQuene(queue, nums[i]);
        }

        //k=3, i=2
        for (int i=k-1; i<nums.length; i++) {
            inQuene(queue, nums[i]);
            res[i-k+1] = queue.peek();
            outQuene(queue, nums[i-k+1]);
        }

        return res;
    }

    private void inQuene(LinkedList<Integer> queue, int num) {
        while (!queue.isEmpty() && queue.peekLast() < num) {
            queue.pollLast();
        }
        queue.offer(num);
        //printQueue(queue);
    }

    private void outQuene(LinkedList<Integer> queue, int num) {
        if (queue.peekFirst() == num) {
            queue.poll();
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
        int[] res = window.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        System.err.println("Result: " + Arrays.toString(res));
    }
}
