package heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KSmallestPair {

	/**
	 * 373. Find K Pairs with Smallest Sums
	 * The run time complexity is O(kLogk) since que.size <= k and we do at most k loop.
	 */
	public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> res = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0) return res;
        
        PriorityQueue<Pair> min = new PriorityQueue<>((p1, p2) -> p1.sum - p2.sum); //max size is K
        
        for (int j = 0; j<nums2.length && j<k; j++) {
            min.offer(new Pair(nums1[0] + nums2[j], 0, j));
        }
        
        while (res.size() < k && !min.isEmpty()) {
            Pair p = min.poll();
            res.add(new int[]{nums1[p.i], nums2[p.j]});
            
            if(p.i + 1 < nums1.length) {
                Pair newp = new Pair(nums1[p.i + 1] + nums2[p.j], p.i + 1, p.j);
                min.offer(newp);
            }
        }
        
        return res;
    }
    
    class Pair {
        int sum;
        int i; //nums1 index
        int j; //nums2 index
        Pair(int sum, int i, int j) {
            this.sum = sum;
            this.i = i;
            this.j = j;
        }
    }
}
