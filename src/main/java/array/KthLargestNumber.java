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
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int val : nums) {
            minHeap.offer(val);

            if(minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

    /**
     * Quick select algorithm, average time is O(n)
     * https://en.wikipedia.org/wiki/Quickselect
     * O(N) guaranteed running time + O(1) space
     */
    public int findKthLargest(int[] nums, int k) {
        shuffle(nums);
        //System.err.println("After shuffle" + Arrays.toString(nums));

        k = nums.length - k;
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int j = partition(nums, lo, hi);
            //System.err.println("After partition" + Arrays.toString(nums));
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
        for (int ind = 1; ind < a.length; ind++) {
            final int r = random.nextInt(ind + 1);
            swap(a, ind, r);
        }
    }
    
    private int partition(int[] nums, int start, int end) {
        int pivot = nums[end];
        int left = start - 1;
        for (int j = start; j < end; j++) {
            if (nums[j] < pivot) {
                left++;
                swap(nums, left, j);
            }
        }
        
        left++;
        swap(nums, left, end);
        return left;
    }

    private void swap(int[] a, int i, int j) {
        final int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        KthLargestNumber kthLargestNumber = new KthLargestNumber();
        int[] arr = {3,2,1,5,6,4};
        System.err.println(Arrays.toString(arr));
        kthLargestNumber.findKthLargest(arr, 2);
    }
}
