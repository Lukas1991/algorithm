package array;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class KthLargestNumber {

    /**
     * O(N lg K) running time + O(K) memory
     * use a min heap that will store the K-th largest values
     */
    public int findKthLargestHeap(int[] nums, int k) {

        final PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int val : nums) {
            pq.offer(val);

            if(pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    /**
     * O(N) guaranteed running time + O(1) space
     */
    public int findKthLargest(int[] nums, int k) {
        shuffle(nums);
        System.err.println("After shuffle" + Arrays.toString(nums));

        k = nums.length - k;
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            final int j = partition(nums, lo, hi);
            System.err.println("After partition" + Arrays.toString(nums));
            if(j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                break;
            }
        }
        return nums[k];
    }

    private void shuffle(int a[]) {
        final Random random = new Random();
        for(int ind = 1; ind < a.length; ind++) {
            final int r = random.nextInt(ind + 1);
            exch(a, ind, r);
        }
    }

    private int partition(int[] a, int lo, int hi) {

        int i = lo;
        int j = hi + 1;
        while(true) {
            while(i < hi && less(a[++i], a[lo]));
            while(j > lo && less(a[lo], a[--j]));
            if(i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private void exch(int[] a, int i, int j) {
        final int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private boolean less(int v, int w) {
        return v < w;
    }

    public static void main(String[] args) {
        KthLargestNumber kthLargestNumber = new KthLargestNumber();
        int[] arr = {3,2,1,5,6,4};
        System.err.println(Arrays.toString(arr));
        kthLargestNumber.findKthLargest(arr, 2);
    }
}
