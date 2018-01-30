package SegmentTree;

/**
 * There are n cities on an axis, numbers from 0 ~ n - 1. John intends to do business in these n cities, He is interested in Armani's shipment.
 * Each city has a price for these goods prices [i]. For city x, John can buy the goods from the city numbered from x - k to x + k, and sell them to city x.
 * We want to know how much John can earn at most in each city?
 *
 * Given prices = [1, 3, 2, 1, 5], k = 2, return [0, 2, 1, 0, 4]
 *
 * 在index= 1的city, 左右K个范围内min Price = 1, 最多赚price[i] - min
 *
 * Segment tree 求区间最小.
 *
 * O(n*logn)
 * 如果用sliding window(size = 2k+1), min heap的方法， 需要往heap里加一个数，减一个数，O(nk)
 */
public class JohnBusiness {

    SegmentTreeNode root;

    public int[] business(int[] A, int k) {
        root = build(A, 0, A.length - 1);
        int[] res = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            int start = i - k;
            int end = i + k;
            //query 区间[x - k, x + k]
            res[i] = A[i] - query(root, start, end);
        }

        return res;
    }

    public int query(SegmentTreeNode root, int start, int end) {
        if (start > end || root == null) {
            return Integer.MAX_VALUE;
        }

        if (start <= root.start && root.end <= end) {
            return root.min;
        }

        int mid = (root.start + root.end) / 2;
        int min = Integer.MAX_VALUE;

        if (mid >= start) {
            min = Math.min(min, query(root.left, start, end));
        }

        if (end > mid) {
            min = Math.min(min, query(root.right, start, end));
        }

        return min;
    }

    SegmentTreeNode build(int[] A, int i, int j) {
        if (i > j) {
            return null;
        }
        SegmentTreeNode node = new SegmentTreeNode(i, j, A[i]);
        if (i == j) {
            return node;
        }

        int mid = (i + j) / 2;
        node.left = build(A, i, mid);
        node.right = build(A, mid + 1, j);
        node.min = Math.min(node.left.min, node.right.min);
        return node;
    }

    class SegmentTreeNode {
        public int start, end, min;
        public SegmentTreeNode left, right;

        public SegmentTreeNode(int start, int end, int min) {
            this.start = start;
            this.end = end;
            this.min = min;
        }
    }
}
