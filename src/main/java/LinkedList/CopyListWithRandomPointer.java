package LinkedList;

public class CopyListWithRandomPointer {

    /**
     * Space O(1)
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null)   return null;

        //1. copy each node, and link each to the old node
        RandomListNode cur = head;
        while (cur != null) {
            RandomListNode copy = new RandomListNode(cur.label);

            RandomListNode next = cur.next;
            cur.next = copy;
            copy.next = next;

            cur = next;
        }

        //2. copy random
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }

            cur = cur.next.next;
        }

        //3. detach the link
        cur = head;
        RandomListNode newHead = cur.next;

        while (cur != null) {
            RandomListNode next = cur.next;
            if (next != null) {
                cur.next = next.next;
            }

            cur = next;
        }

        return newHead;
    }

    class RandomListNode {
        int label;
        RandomListNode next, random;
        RandomListNode(int x) { this.label = x; }
    };
}
