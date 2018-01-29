package heap;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * TreeSet maintain the order,
 * guaranteed log(n) time cost for Add, Remove, Contains
 * can use methods: set.first(), set.pollFirst(), set.last(), set.pollLast()
 *
 * O(n * logk)
 */
public class MedianSlidingWindowTreeSet {

    TreeSet<Node> minheap = new TreeSet<>((n1, n2) -> {
        if (n1.val == n2.val) {
            return n1.index - n2.index;
        } else {
            return n1.val - n2.val;
        }
    });

    TreeSet<Node> maxheap = new TreeSet<>((n1, n2) -> {
        if (n1.val == n2.val) {
            return n2.index - n1.index;
        } else {
            return n2.val - n1.val;
        }
    });

    class Node {
        int index;
        int val;

        public Node(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }

    public List<Integer> medianSlidingWindow(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        if (k == 0) {
            return res;
        }
        for (int i = 0; i < k - 1; i++) {
            add(i, nums[i]);
        }

        for (int i = k - 1; i < nums.length; i++) {
            add(i, nums[i]);
            res.add(getMedian());
            remove(i - k + 1, nums[i - k + 1]);
        }
        return res;
    }

    void add(int index, int a) {
        Node node = new Node(index, a);
        maxheap.add(node);
        minheap.add(maxheap.pollFirst());

        if (minheap.size() > maxheap.size()) {
            maxheap.add(minheap.pollFirst());
        }
    }

    void remove(int index, int a) {
        Node node = new Node(index, a);
        if (maxheap.contains(node)) {
            maxheap.remove(node);
        } else {
            minheap.remove(node);
        }

        if (minheap.size() > maxheap.size()) {
            maxheap.add(minheap.pollFirst());
        }
    }

    int getMedian() {
        return maxheap.first().val;
    }
}


