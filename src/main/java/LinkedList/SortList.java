package LinkedList;

public class SortList {

    /**
     * Merge Sort
     * 1. Break the list to two in the middle
     * 2. Recursively sort the two sub lists
     * 3. Merge the two sub lists
     * Time O(nlogn), constant space
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p1Pre = head;
        ListNode p1 = head, p2 = head;
        while (p2 != null && p2.next != null) {
            p1Pre = p1;
            p1 = p1.next;
            p2 = p2.next.next;
        }

        //p1 is mid;
        p1Pre.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(p1);

        return merge(left, right);
    }

    ListNode merge(ListNode p1, ListNode p2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                cur.next = p1;
                p1 = p1.next;
            } else {
                cur.next = p2;
                p2 = p2.next;
            }
            cur = cur.next;
        }

        while (p1 != null) {
            cur.next = p1;
            p1 = p1.next;
            cur = cur.next;
        }

        while (p2 != null) {
            cur.next = p2;
            p2 = p2.next;
            cur = cur.next;
        }

        return dummy.next;
    }
}
