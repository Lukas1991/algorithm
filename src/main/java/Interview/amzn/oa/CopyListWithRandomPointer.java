package Interview.amzn.oa;

import Interview.amzn.oa.model.RandomListNode;

/**
 * https://yeqiuquan.blogspot.com/2017/03/lintcode-105-copy-list-with-random.html
 * 
 * @author dacai
 *
 */
public class CopyListWithRandomPointer {

	public RandomListNode copyRandomList(RandomListNode head) {
		// write your code here

		RandomListNode iter;
		RandomListNode next;
		iter = head;

		while (iter != null) {
			next = iter.next;
			RandomListNode copy = new RandomListNode(iter.label);
			iter.next = copy;
			copy.next = next;
			iter = next;
		}

		iter = head;
		while (iter != null) {
			if (iter.random != null) {
				iter.next.random = iter.random.next;
			}
			iter = iter.next.next;
		}

		RandomListNode dummy = new RandomListNode(0);
		iter = head;
		RandomListNode copyIter;
		RandomListNode copy;
		copyIter = dummy;
		while (iter != null) {
			next = iter.next.next;

			copy = iter.next;
			copyIter.next = copy;
			copyIter = copy;

			iter = next;
		}

		return dummy.next;
	}

}
