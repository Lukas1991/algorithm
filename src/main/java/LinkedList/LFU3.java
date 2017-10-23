package LinkedList;

import java.util.HashMap;
import java.util.LinkedList;

public class LFU3 {
    class Node {
        int freq = 0;
        LinkedList<Integer> keys = new LinkedList<>();
        Node pre = null, next = null;
        public Node(int freq) {
            this.freq = freq;
        }
    }

    private Node head = null; //least frequency node
    private int cap = 0;
    //key, value
    private HashMap<Integer, Integer> valueMap = null;
    //key, the Frequency Node it belongs to
    private HashMap<Integer, Node> nodeMap = null;

    public LFU3(int capacity) {
        this.cap = capacity;
        valueMap = new HashMap<>();
        nodeMap = new HashMap<>();
    }

    public int get(int key) {
        if (valueMap.containsKey(key)) {
            increaseFreq(key);
            return valueMap.get(key);
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (this.cap == 0)  return;
        if (valueMap.containsKey(key)) {
            increaseFreq(key);
        } else {
            if (valueMap.size() == this.cap) {
                removeLFU();
            }

            addToHead(key);
        }
        valueMap.put(key, value);
    }

    private void increaseFreq(int key) {
        Node f = nodeMap.get(key);
        int index = f.keys.indexOf(key);
        f.keys.remove(index);

        int newf = f.freq + 1;
        Node next = f.next;
        if (next == null) {
            Node newnode = new Node(newf);
            f.next = newnode;
            newnode.pre = f;
            next = f.next;
        } else if (next.freq > newf) {
            Node newnode = new Node(newf);
            newnode.pre = f;
            newnode.next = next;

            next.pre = newnode;
            f.next = newnode;

            next = newnode;
        }

        next.keys.add(key);
        nodeMap.put(key, next);
        if (f.keys.size() == 0) {
            remove(f);
        }
    }

    private void removeLFU() {
        if (head == null)   return;
        int lfu = head.keys.getFirst();
        head.keys.removeFirst();

        if (head.keys.size() == 0) {
            remove(head);
        }

        nodeMap.remove(lfu);
        valueMap.remove(lfu);
    }

    private void remove(Node node) {
        if (node.pre == null) {
            head = node.next;
        } else {
            node.pre.next = node.next;
        }
        if (node.next != null) {
            node.next.pre = node.pre;
        }
    }

    private void addToHead(int key) {
        //set freq = 1
        if (head != null) {
            if (head.freq == 1) {
                head.keys.add(key);
            } else {
                Node f1 = new Node(1);
                f1.keys.add(key);
                f1.next = head;
                head.pre = f1;
                head = f1;
            }
        } else {
            head = new Node(1);
            head.keys.add(key);
        }

        nodeMap.put(key, head);
    }

    public static void main(String[] args) {
        LFU3 cache = new LFU3(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.err.println(cache.get(1)); // returns 1
        cache.put(3, 3);    // evicts key 2
        System.err.println(cache.get(2));       // returns -1 (not found)
        System.err.println(cache.get(3));       // returns 3.
        cache.put(4, 4);    // evicts key 1.
        System.err.println(cache.get(1));      // returns -1 (not found)
        System.err.println(cache.get(3));     // returns 3
        System.err.println(cache.get(4));       // returns 4
    }
}
