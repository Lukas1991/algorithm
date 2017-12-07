package LinkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 */

/**
 * use a hashtable and a double linked list, which enables quickly remove nodes. It takes constant time to add and remove nodes from the head or tail.
 *
 * Head is most recently used, tail is the least recently used
 *
 * get(key), if contains, remove this node from list, add it back to the head of the list
 * set(key,value) if contains, update old value, remove it from list, add back to head
 * 				if not contains, if in capacity, remove end, add it to head
 *
 */
public class LRUCache {

	class Node {
		int key;
		int value;
		Node pre;
		Node next;
		public Node(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}

	private int capacity;
	private Node head;
	private Node end;
	private Map<Integer, Node> map = new HashMap<>();

	public LRUCache(int capacity) {
		this.capacity = capacity;
	}

	public int get(int key) {
		if (map.containsKey(key)) {
			Node node = map.get(key);
			remove(node);
			addToHead(node);
			return node.value;
		}
		return -1;
	}

	public void set(int key, int value) {
		if (map.containsKey(key)) {
			Node node = map.get(key);
			node.value = value;
			remove(node);
			addToHead(node);
		} else {
			Node node = new Node(key, value);
			if (map.size() == capacity) {
				if (end == null) { //means capacity is 0
					return;
				} else {
					map.remove(end.key);
					remove(end);
				}
			}

			addToHead(node);
			map.put(key, node);
		}

	}

	private void remove(Node node) {
		Node preNode = node.pre;
		Node nextNode = node.next;

		if (preNode == null) {
			head = nextNode;
		} else {
			preNode.next = nextNode;
		}

		if (nextNode == null) {
			end = preNode;
		} else {
			nextNode.pre = preNode;
		}

		node.pre = null;
		node.next = null;
	}

	private void addToHead(Node node) {
		if (head == null) {
			head = node;
			end = node;
		} else {
			node.next = head;
			head.pre = node;
			head = node;
		}
	}

	public static void main(String[] args) {
		LRUCache LRU = new LRUCache(1);
		LRU.set(2,1);
		System.err.println(LRU.get(2));
		LRU.set(3,2);
		System.err.println(LRU.get(2));
		System.err.println(LRU.get(3));
	}
}
