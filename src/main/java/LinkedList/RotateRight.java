package LinkedList;

public class RotateRight {
    /**
     * Given a list, rotate the list to the right by k places, where k is non-negative.
     * <p>
     * For example:
     * Given 1->2->3->4->5->NULL and k = 2,
     * return 4->5->1->2->3->NULL.
     */
    public ListNode rotateRight(ListNode head, int k) {
        //find length
        //find length-n
        //current.next=null
        //current next is newHead
        //find tail
        //tail.next=head
        //return newHead

        //special cases: (n==0),(n>len, n=n%len), (n%len==0),(len==1)
        if (k == 0) return head;
        if (head == null) return null;

        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }

        k = k % len;
        if (k == 0) return head;
        k = len - k;

        ListNode newHead = null;
        int count = 0;
        cur = head;
        while (cur != null) {
            count++;

            if (count == k) {
                newHead = cur.next;
                cur.next = null;
                break;
            } else {
                cur = cur.next;
            }
        }

        cur = newHead;
        while (cur.next != null) {
            cur = cur.next;
        }

        cur.next = head;

        return newHead;
    }

    public static void main(String[] args) {
        ListNode n5 = new ListNode(5, null);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode n1 = new ListNode(1, n2);

        RotateRight rl = new RotateRight();
        ListNode head = rl.rotateRight(n1, 2);
        System.out.println("------------------------");
        //iterate and print the list
        ListNode current = head;
        while (current != null) {
            System.out.println(current.val);
            current = current.next;
        }
    }

}