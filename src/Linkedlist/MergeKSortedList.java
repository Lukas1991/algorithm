package Linkedlist;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedList {
	/**
	 * Merge k sorted linked lists and return it as one sorted list. Analyze and
	 * describe its complexity.
	 * 
	 * @param args
	 */

	public ListNode mergeKLists(ArrayList<ListNode> lists) {
		if (lists == null || lists.size() == 0) {
			return null;
		}

		ListNode head = new ListNode(0);
		ListNode current = head;
		// accending order:
		PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(lists.size(),
				new Comparator<ListNode>() {

					@Override
					public int compare(ListNode o1, ListNode o2) {
						if (o1.val > o2.val)
							return 1;
						else if (o1.val < o2.val)
							return -1;
						else
							return 0;
					}

				});
		// QUEUE doesn't allow null
		for (ListNode list : lists) {
			if (list != null)
				pq.offer(list);
		}

		while (pq.size() > 0) {
			// get smallest ListNode in the queue
			ListNode temp = pq.poll();
			current.next = temp;
			current = current.next;
			if (temp.next != null) {
				pq.offer(temp.next);
			}
		}

		current.next = null;
		return head.next;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
