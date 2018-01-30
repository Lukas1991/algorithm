package SegmentTree;

import java.util.ArrayList;
import java.util.List;

public class CountOfSmallerNumber {

    SegmentTreeNode root;

    /**
     * Give you an integer array (index from 0 to n-1, where n is the size of this array, value from 0 to 10000) and an query list.
     * For each query, give you an integer, return the number of element in the array that are smaller than the given integer.
     * Example: For array [1,2,7,8,5], and queries [1,8,5], return [0,4,2]
     */
    public List<Integer> countOfSmallerNumber(int[] A, int[] queries) {
        root = build(0, 10000);//build a segmentTree, start is smallest value, end is the largest value
        for (int a : A) {
            modify(root, a, 1);//the number's node count + 1;
        }

        List<Integer> res = new ArrayList<>();
        for (int q : queries) {
            res.add(query(root, 0, q - 1));
        }

        return res;
    }

    /**
     * For each element Ai in the array, count the number of element before this element Ai is smaller than it, and return count number array.
     * Example: For array [1,2,7,8,5], return [0,1,2,3,2]
     * 一边query 一边 modify
     */
    public List<Integer> countOfSmallerNumberII(int[] A) {
        root = build(0, 10000);
        List<Integer> res = new ArrayList<>();
        for (int a : A) {
            res.add(query(root, 0, a - 1));
            modify(root, a, 1);
        }
        return res;
    }

    /**
     * 如果query的start，end可能超出root的范围，那么用if (start <= node.start && node.end <= end)来判断
     */
    int query(SegmentTreeNode node, int start, int end) {
        if (start > end || node == null) {
            return 0;
        }

        if (start <= node.start && node.end <= end) {
            return node.count;
        }

        int mid = (node.start + node.end) / 2;
        int leftCount = 0, rightCount = 0;

        if (start <= mid) {
            leftCount = query(node.left, start, end);
        }

        if (end > mid) {
            rightCount = query(node.right, start, end);
        }

        return leftCount + rightCount;
    }

    void modify(SegmentTreeNode node, int index, int value) {
        if (node.start == index && node.end == index) {
            node.count += value;
            return;
        }

        int mid = (node.start + node.end) / 2;
        if (index <= mid) {
            modify(node.left, index, value);
        } else {
            modify(node.right, index, value);
        }

        node.count = node.left.count + node.right.count;
    }

    public SegmentTreeNode build(int start, int end) {
        if (start > end) {
            return null;
        }
        SegmentTreeNode node = new SegmentTreeNode(start, end, 0);
        if (start == end) {
            return node;
        } else {
            int mid = (start + end) / 2;
            node.left = build(start, mid);
            node.right = build(mid + 1, end);
        }
        return node;
    }

    class SegmentTreeNode {
        public int start, end, count;
        public SegmentTreeNode left, right;

        public SegmentTreeNode(int start, int end, int count) {
            this.start = start;
            this.end = end;
            this.count = count;
        }
    }
}
