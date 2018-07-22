package SegmentTree;

public class SegmentTreeBuildQueryModify {

    public SegmentTreeNode build(int[] A) {
        return build(A, 0, A.length - 1);
    }

    SegmentTreeNode build(int[] A, int start, int end) {
        if (start > end) {
            return null;
        }

        SegmentTreeNode node = new SegmentTreeNode(start, end, A[start]);

        if (start == end) {
            return node;
        } else {
            int mid = (start + end) / 2;
            node.left = build(A, start, mid);
            node.right = build(A, mid + 1, end);

            node.max = Math.max(node.left.max, node.right.max);
            return node;
        }
    }

    //log(n)
    public void modify(SegmentTreeNode root, int index, int value) {
        if (root.start == index && root.end == index) {
            root.max = value;
            return;
        }

        int mid = (root.start + root.end) / 2;
        if (index <= mid) {
            modify(root.left, index, value);
        } else {
            modify(root.right, index, value);
        }

        root.max = Math.max(root.left.max, root.right.max);
    }

    //log(n)
    public int query(SegmentTreeNode root, int start, int end) {
        if (start > end || root == null) {
            return Integer.MIN_VALUE;
        }

        if (start <= root.start && root.end <= end) {
            return root.max;
        }

        int mid = (root.start + root.end) / 2;
        int max = Integer.MIN_VALUE;

        if (mid >= start) {// 如果查询区间和左边节点区间有交集
            max = Math.max(max, query(root.left, start, end));
        }

        if (end > mid) {// 如果查询区间和右边节点区间有交集
            max = Math.max(max, query(root.right, start, end));
        }

        return max;
    }

    class SegmentTreeNode {
        public int start, end, max;
        public SegmentTreeNode left, right;

        public SegmentTreeNode(int start, int end, int max) {
            this.start = start;
            this.end = end;
            this.max = max;
            this.left = this.right = null;
        }
    }
}
