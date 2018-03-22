package Interview.dropbox;

//Space complexity: O(2n)
public class PhoneDirectorySegmentTree {

    int max;
    Node root;

    public PhoneDirectorySegmentTree(int maxNumbers) {
        root = buildTree(0, maxNumbers - 1);
        max = maxNumbers;
    }

    /**
     * Provide a number which is not assigned to anyone.
     *
     * @return - Return an available number. Return -1 if none is available.
     * log(N)
     */
    public int get() {
        int num = findAvai(root);
        if (num == -1) {
            return -1;
        } else {
            modify(root, num, false);
            return num;
        }
    }

    int findAvai(Node node) {
        if (node == null || !node.hasAvailable) {
            return -1;
        }

        if (node.left == null && node.right == null) {
            return node.start;
        } else {
            if (node.left.hasAvailable) {
                return findAvai(node.left);
            } else {
                return findAvai(node.right);
            }
        }
    }

    void modify(Node node, int index, boolean value) {
        if (node.start == index && node.end == index) {
            node.hasAvailable = value;
            return;
        }

        int mid = (node.start + node.end) / 2;
        if (index <= mid) {
            modify(node.left, index, value);
        } else {
            modify(node.right, index, value);
        }

        node.hasAvailable = node.left.hasAvailable || node.right.hasAvailable;
    }

    /**
     * Check if a number is available or not.
     */
    public boolean check(int number) {
        if (number < 0 || number >= max) {
            return false;
        }

        return query(root, number, number);
    }

    /**
     * Recycle or release a number.
     */
    //number 超出范围时
    public void release(int number) {
        if (number < 0 || number >= max) {
            return;
        }
        modify(root, number, true);
    }

    Node buildTree(int start, int end) {
        if (start > end) {
            return null;
        }

        Node node = new Node(start, end);
        if (start == end) {
            return node;
        }

        int mid = (start + end) / 2;
        node.left = buildTree(start, mid);
        node.right = buildTree(mid + 1, end);
        return node;
    }

    boolean query(Node node, int start, int end) {
        if (start > end || root == null) {
            return false;
        }

        if (start <= node.start && node.end <= end) {
            return node.hasAvailable;
        }

        int mid = (node.start + node.end) / 2;

        boolean hasAvai = false;
        if (mid >= start) {
            hasAvai |= query(node.left, start, end);
        }

        if (hasAvai) {
            return true;
        }

        if (mid < end) {
            hasAvai |= query(node.right, start, end);
        }

        return hasAvai;
    }

    class Node {
        int start;
        int end;
        Node left;
        Node right;
        boolean hasAvailable = true;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        PhoneDirectorySegmentTree obj = new PhoneDirectorySegmentTree(3);
        System.err.println(obj.get());

        System.err.println(obj.get());

        System.err.println(obj.check(2));  //true
        System.err.println(obj.get());
        System.err.println(obj.check(2));  //false
        obj.release(2);
        System.err.println(obj.check(2));  //true

    }
}
