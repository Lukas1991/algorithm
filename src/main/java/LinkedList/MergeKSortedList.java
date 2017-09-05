package LinkedList;

import java.util.PriorityQueue;

public class MergeKSortedList {

    /**
     * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        // accending order:
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, (o1, o2) -> o1.val - o2.val);

        // QUEUE doesn't allow null
        for (ListNode list : lists) {
            if (list != null) {
                pq.offer(list);
            }
        }

        ListNode head = new ListNode(0);
        ListNode current = head;

        while (!pq.isEmpty()) {
            // get smallest ListNode in the queue
            ListNode temp = pq.poll();
            current.next = temp;
            current = current.next;
            if (temp.next != null) {
                pq.offer(temp.next);
            }
        }

        return head.next;
    }

}