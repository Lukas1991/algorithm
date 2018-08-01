package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;

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

    //n * logk
    public List<Integer> topKFrequentPQ(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((e1, e2) -> e1.getValue() - e2.getValue());

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (minHeap.size() < k) {
                minHeap.offer(entry);
            } else {
                if (minHeap.peek().getValue() < entry.getValue()) {
                    minHeap.poll();
                    minHeap.offer(entry);
                }
            }
        }

        List<Integer> res = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : minHeap) {
            res.add(entry.getKey());
        }

        return res;
    }

    public List<Integer> topKFrequent3(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        //key is frequency
        TreeMap<Integer, Set<Integer>> treemap = new TreeMap<>();

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int freq = entry.getValue();
            if (!treemap.containsKey(freq)) {
                treemap.put(freq, new HashSet<>());
            }

            treemap.get(freq).add(entry.getKey());
        }

        List<Integer> res = new ArrayList<>();

        while (res.size() < k && !treemap.isEmpty()) {
            Set<Integer> numbers = treemap.pollLastEntry().getValue();
            for (int n : numbers) {
                if (res.size() == k) {
                    break;
                } else {
                    res.add(n);
                }
            }
        }

        return res;
    }

    Map<Integer, Integer> map = new HashMap<>();
    //key is frequency
    TreeMap<Integer, Set<Integer>> treemap = new TreeMap<>();

    public List<Integer> topKFrequent4(int[] nums, int k) {
        for (int n : nums) {
            addNumber(n);
        }

        return getTopK(k);
    }

    //logM
    void addNumber(int n) {
        if (!map.containsKey(n)) {
            map.put(n, 1);
            treemap.putIfAbsent(1, new HashSet<>());
            treemap.get(1).add(n);
        } else {
            int preFreq = map.get(n);
            int newFreq = preFreq + 1;
            map.put(n, newFreq);
            treemap.get(preFreq).remove(n);
            treemap.putIfAbsent(newFreq, new HashSet<>());
            treemap.get(newFreq).add(n);
        }
    }

    List<Integer> getTopK(int k) {
        List<Integer> res = new ArrayList<>();

        while (res.size() < k && !treemap.isEmpty()) {
            Set<Integer> numbers = treemap.pollLastEntry().getValue();  //poll last takes O(logn)
            for (int n : numbers) {
                if (res.size() == k) {
                    break;
                } else {
                    res.add(n);
                }
            }
        }

        return res;
    }
}
