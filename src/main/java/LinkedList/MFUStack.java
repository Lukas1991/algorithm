package LinkedList;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class MFUStack {
    class Node {
        int freq = 0;
        LinkedList<Integer> keys = new LinkedList<>();
        Node pre = null, next = null;
        public Node(int freq) {
            this.freq = freq;
        }
    }

    private Node head = null; //least frequency node
    private Node tail = null; //most frequency node
    private int cap = 0;
    //key, the Frequency Node it belongs to
    private HashMap<Integer, Node> nodeMap = null;
    Stack<Integer> stack = new Stack<>();

    public MFUStack(int capacity) {
        this.cap = capacity;
        nodeMap = new HashMap<>();
    }

    public void push(int key) {
        if (nodeMap.containsKey(key)) {
            increaseFreq(key);
        } else {
            if (stack.size() == this.cap) {
                removeLFU();
            }

            addToHead(key);
        }
        stack.push(key);
    }

    public int getMFU() {
        if (tail == null) {
            return -1;
        } else {
            return tail.keys.getLast();
        }
    }

    public int peek() {
        int key = stack.peek();
        increaseFreq(key);
        return key;
    }

    public void pop() {
        int key = stack.pop();

        Node f = nodeMap.get(key);
        int index = f.keys.indexOf(key);
        f.keys.remove(index);
        nodeMap.remove(key);

        if (f.keys.size() == 0) {
            remove(f);
        }
        //decreaseFreq(key);
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
        if (next.next == null) {
            tail = next;
        }
    }

    private void decreaseFreq(int key) {
        //remove if freq=0
        Node f = nodeMap.get(key);
        int index = f.keys.indexOf(key);
        f.keys.remove(index);

        int newf = f.freq - 1;
        if (newf == 0) {
            nodeMap.remove(key);
        } else {
            Node pre = f.pre;
            if (pre == null) {
                Node newnode = new Node(newf);
                f.pre = newnode;
                newnode.next = f;
                pre = f.pre;
            } else if (pre.freq < newf) {
                Node newnode = new Node(newf);
                newnode.next = f;
                newnode.pre = pre;

                f.pre = newnode;
                pre.next = newnode;

                pre = newnode;
            }

            pre.keys.add(key);
            nodeMap.put(key, pre);
        }

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
    }

    private void remove(Node node) {
        if (node.pre == null) {
            head = node.next;
        } else {
            node.pre.next = node.next;
        }
        if (node.next != null) {
            node.next.pre = node.pre;
        } else {
            tail = node.pre;
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
            tail = head;
        }

        nodeMap.put(key, head);
    }

    public static void main(String[] args) {
        MFUStack stack = new MFUStack(4);
        stack.push(1);
        stack.push(2);

        System.err.println(stack.peek()); // returns 2
        System.err.println(stack.getMFU()); //2

        stack.push(3);
        System.err.println(stack.peek()); // returns 3
        System.err.println(stack.getMFU()); //3

        stack.pop(); //remove 3
        System.err.println(stack.getMFU()); //2

        stack.pop(); //remove 2
        System.err.println(stack.getMFU()); //1
    }
}
