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
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(lists.length, (o1, o2) -> o1.val - o2.val);

        // QUEUE doesn't allow null
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
            }
        }

        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;

        while (!minHeap.isEmpty()) {
            // get smallest ListNode in the queue
            ListNode temp = minHeap.poll();
            current.next = temp;

            if (temp.next != null) {
                minHeap.offer(temp.next);
            }
            current = current.next;
        }

        return dummyHead.next;
    }

}