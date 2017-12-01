package Interview.twosigma;


class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;
    TreeLinkNode(int x) { val = x; }
}

public class PopulateNextRight {

    /**
     * 116. Populating Next Right Pointers in Each Node
     * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
     * level by level
     */
    public void connect1(TreeLinkNode root) {
        
        TreeLinkNode cur = root;  //first node of each level

        while (cur != null) {
            TreeLinkNode head = cur.left;  //next level head

            while (cur != null) { //cur to the end of the level
            		if (cur.left != null) {
            			cur.left.next = cur.right;
                    if (cur.next != null) {
                        cur.right.next = cur.next.left;
                    }
            		}
                
                cur = cur.next;
            }

            cur = head;
        }
    }

    /**
     * 117. Populating Next Right Pointers in Each Node II
     * What if the given tree could be any binary tree?
     * level by level
     */
    public void connect2(TreeLinkNode root) {
       
        TreeLinkNode cur = root; //current node of current level

        while (cur != null) {  //new level
            TreeLinkNode head = null; //next level head
            TreeLinkNode pre = null; //next level pre

            while (cur != null) {  //to the end of current level
                //left child
                if (cur.left != null) {
                    if (pre != null) {
                        pre.next = cur.left;
                    } else {
                        head = cur.left;
                    }

                    pre = cur.left;
                }

                //right child
                if (cur.right != null) {
                    if (pre != null) {
                        pre.next = cur.right;
                    } else {
                        head = cur.right;
                    }

                    pre = cur.right;
                }

                cur = cur.next;
            }

            cur = head;
        }
    }

}
