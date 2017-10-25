package Tree;


class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;
    TreeLinkNode(int x) { val = x; }
}

public class PopulateNextRight {

    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }

        TreeLinkNode left = root.left;
        TreeLinkNode right = root.right;

        if (root.val == 7) {
            int b = 1;
        }
        TreeLinkNode next = null;
        TreeLinkNode curr = root.next;
        while (curr != null) {
            if (curr.left != null) {
                next = curr.left;
                break;
            }

            if (curr.right != null) {
                next = curr.right;
                break;
            }

            curr = curr.next;
        }


        if (left != null) {
            if (right != null) {
                left.next = right;
            } else {
                left.next = next;
            }
        }

        if (right != null) {
            right.next = next;
        }

        connect(left);
        connect(right);
    }

    public static void main(String[] args) {
        PopulateNextRight obj = new PopulateNextRight();
        TreeLinkNode root = new TreeLinkNode(0);
        TreeLinkNode n1 = new TreeLinkNode(1);
        TreeLinkNode n3 = new TreeLinkNode(3);

        root.left = n1;
        root.right = n3;

        TreeLinkNode n0 = new TreeLinkNode(0);
        TreeLinkNode n7 = new TreeLinkNode(7);
        n1.left = n0;
        n1.right = n7;

        TreeLinkNode n9 = new TreeLinkNode(9);
        TreeLinkNode n11 = new TreeLinkNode(1);
        n3.left = n9;
        n3.right = n11;

        n0.left = new TreeLinkNode(2);
        n7.left = new TreeLinkNode(1);
        n7.right = new TreeLinkNode(0);

        n11.left = new TreeLinkNode(8);
        n11.right = new TreeLinkNode(8);




        obj.connect(root);
        int a = 0;
    }
}
