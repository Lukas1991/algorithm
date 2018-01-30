package SegmentTree;

public class IntervalSum {

    SegmentTreeNode root;

    public IntervalSum(int[] A) {
        root = build(A, 0, A.length - 1);
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
        node.sum = node.left.sum + node.right.sum;
        return node;
    }

    /*
     * For query(start, end), return the sum from index start to index end in the given array.
     */
    public long query(int start, int end) {
        return query(root, start, end);
    }

    long query(SegmentTreeNode node, int start, int end) {
        if (start > end || node == null) {
            return 0;
        }

        if (start <= node.start && node.end <= end) {
            return node.sum;
        }

        int mid = (node.start + node.end) / 2;
        long leftSum = 0, rightSum = 0;

        if (start <= mid) {
            leftSum = query(node.left, start, end);
        }

        if (end > mid) {
            rightSum = query(node.right, start, end);
        }

        return leftSum + rightSum;
    }

    /*
     * For modify(index, value), modify the number in the given index to value
     */
    public void modify(int index, int value) {
        modify(root, index, value);
    }

    void modify(SegmentTreeNode node, int index, int value) {
        if (node.start == index && node.end == index) {
            node.sum = value;
            return;
        }

        int mid = (node.start + node.end) / 2;
        if (index <= mid) {
            modify(node.left, index, value);
        } else {
            modify(node.right, index, value);
        }

        node.sum = node.left.sum + node.right.sum;
    }

    class SegmentTreeNode {
        int start, end;
        long sum;
        SegmentTreeNode left, right;

        public SegmentTreeNode(int start, int end, long sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
        }
    }

    public static void main(String[] args) {
        IntervalSum obj = new IntervalSum(new int[] {1, 2, 7, 8, 5});
        System.err.println(obj.query(0, 2)); //10
        obj.modify(0, 4); //change A[0] from 1 to 4.
        System.err.println(obj.query(0, 1)); //6
        obj.modify(2, 1); //change A[2] from 7 to 1.
        System.err.println(obj.query(2, 4)); //14
    }
}
