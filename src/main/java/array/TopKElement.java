package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKElement {

    /**
     * Given a non-empty array of integers, return the k most frequent elements.
     * For example,
     * Given [1,1,1,2,2,3] and k = 2, return [1,2].
     *
     * Note: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
     *
     * Use a bucket, O(n)
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();

        //<number, count> map
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        //array of list
        List<Integer>[] bucket = new List[nums.length + 1];
        map.forEach((key, v) -> {
            if (bucket[v] == null) {
                bucket[v] = new ArrayList<>();
            }

            bucket[v].add(key);
        });

        for (int i=bucket.length-1; i>=0; i--) {
            if (bucket[i] != null) {
                for(int n : bucket[i]) {
                    res.add(n);
                    if (res.size() == k) return res;
                }
            }
        }

        return res;
    }
}
