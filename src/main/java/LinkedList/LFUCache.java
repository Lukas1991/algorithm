package LinkedList;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {

    class KeyNode {
        int key;
        int value;
        int freq;
        KeyNode pre;
        KeyNode next;
    }

    class FreqNode {
        int freq;
        FreqNode pre;
        FreqNode next;
        KeyNode first;
        KeyNode last;
    }
    private int capacity;
    private Map<Integer, KeyNode> valueMap = new HashMap<>();
    private Map<Integer, FreqNode> freqMap = new HashMap<>(); //key is freq
    private FreqNode head = null;

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (valueMap.containsKey(key)) {
            KeyNode old = valueMap.get(key);
            increaseFreq(old);
            return old.value;
        } else {
            return -1;
        }
    }

    public void set(int key, int value) {
        if (capacity == 0) {
            return;
        }

        if (valueMap.containsKey(key)) {
            KeyNode old = valueMap.get(key);
            old.value = value;
            increaseFreq(old);
        } else {
            KeyNode newKeyNode = new KeyNode();
            newKeyNode.key = key;
            newKeyNode.value = value;
            newKeyNode.freq = 1;

            if (valueMap.size() == capacity) {
                //remove min freq keyNode

                KeyNode toRemove = head.last;
                KeyNode toRemovePre = toRemove.pre;
                if (toRemovePre != null) {
                    toRemovePre.next = null;
                    head.last = toRemovePre;
                } else {
                    //this freq Node is empty, remove it
                    FreqNode freqNode = freqMap.get(toRemove.freq);
                    deleteFreqNode(freqNode);
                }

                valueMap.remove(toRemove.key);
            }

            valueMap.put(key, newKeyNode);

            //update freq=1 freqNode
            if (freqMap.containsKey(1)) {
                //insert to first
                FreqNode freq1 = freqMap.get(1);
                insertKeyNodeToFirstFreqNode(freq1, newKeyNode);
            } else {
                addFreq1Node(newKeyNode);
            }
        }
    }

    private void insertKeyNodeToFirstFreqNode(FreqNode freqNode, KeyNode newKeyNode) {
        KeyNode firstKeyNode = freqNode.first;
        firstKeyNode.pre = newKeyNode;

        newKeyNode.next = firstKeyNode;

        freqNode.first = newKeyNode;
    }

    private boolean removeKeyNodeFromFreqNode(FreqNode freqNode, KeyNode keyNode) {
        //find keyNode from old freqNode, remove keyNode
        //if after remove, freqNode is empty, delete freqNode, and remove from freqMap
        KeyNode preNode = keyNode.pre;
        KeyNode nextNode = keyNode.next;
        keyNode.pre = null;
        keyNode.next = null;

        if (preNode != null) {
            preNode.next = nextNode;
        } else {
            freqNode.first = nextNode;
        }

        if (nextNode != null) {
            nextNode.pre = preNode;
        } else {
            freqNode.last = preNode;
        }

        if (freqNode.first == null) {
            deleteFreqNode(freqNode);
            return true;
        }
        return false;
    }

    private void deleteFreqNode(FreqNode freqNode) {
        FreqNode pre = freqNode.pre;
        FreqNode next = freqNode.next;
        if (pre == null) {
            head = next;
        } else {
            pre.next = next;
        }

        if (next != null) {
            next.pre = pre;
        }

        freqMap.remove(freqNode.freq);
    }

    private void addFreq1Node(KeyNode keyNode) {
        FreqNode freq1 = new FreqNode();
        freq1.freq = 1;
        freq1.first = keyNode;
        freq1.last = keyNode;

        FreqNode preHead = head;

        freq1.next = preHead;

        if (preHead != null) {
            preHead.pre = freq1;
        }

        head = freq1;

        freqMap.put(1, freq1);
    }

    private void increaseFreq(KeyNode keyNode) {
        int oldFreq = keyNode.freq;
        int newFreq = oldFreq + 1;
        keyNode.freq = newFreq;

        FreqNode freqNode = freqMap.get(oldFreq);
        FreqNode nextFreqNode = freqNode.next;
        FreqNode preFreqNode = freqNode.pre;

        //find keyNode from old freqNode, remove keyNode
        //if after remove, freqNode is empty, delete freqNode, and remove from freqMap
        boolean isFreqNodeRemoved = removeKeyNodeFromFreqNode(freqNode, keyNode);

        //add keyNode to freq+1 FreqNode
        if (nextFreqNode != null && nextFreqNode.freq == newFreq) {
            //insert keyNode as first
            insertKeyNodeToFirstFreqNode(nextFreqNode, keyNode);
        } else {
            FreqNode newFreqNode = new FreqNode();
            newFreqNode.freq = newFreq;
            newFreqNode.first = keyNode;
            newFreqNode.last = keyNode;
            freqMap.put(newFreq, newFreqNode);

            //insert newFreqNode before nextFreqNode
            newFreqNode.next = nextFreqNode;
            if (nextFreqNode != null) {
                nextFreqNode.pre = newFreqNode;
            }

            //deceide newFreqNode.pre
            if (!isFreqNodeRemoved) {
                newFreqNode.pre = freqNode;
                freqNode.next = newFreqNode;
            } else {
                //head maybe nextFreqNode
                if (head == nextFreqNode) {
                    head = newFreqNode;
                } else {
                    newFreqNode.pre = preFreqNode;
                    preFreqNode.next = newFreqNode;
                }
            }
        }
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.set(1, 1);
        cache.set(2, 2);
        System.err.println(cache.get(1)); // returns 1
        cache.set(3, 3);    // evicts key 2
        System.err.println(cache.get(2));       // returns -1 (not found)
        System.err.println(cache.get(3));       // returns 3.
        cache.set(4, 4);    // evicts key 1.
        System.err.println(cache.get(1));      // returns -1 (not found)
        System.err.println(cache.get(3));     // returns 3
        System.err.println(cache.get(4));       // returns 4
    }

}
