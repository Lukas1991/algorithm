package Tree;

import java.util.HashMap;
import java.util.Map;

public class MapSum {

    Map<String, Integer> map;
    private TrieNode root;

    public MapSum() {
        root = new TrieNode();
        map = new HashMap<>();
    }

    //O(K), k is key's length
    public void insert(String key, int val) {
        int diff = val - map.getOrDefault(key, 0);
        map.put(key, val);

        TrieNode node = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);

            if (!node.children.containsKey(c)) {
                node.children.put(c, new TrieNode());
            }

            node = node.children.get(c);
            node.sum += diff;
        }
    }

    //O(K), k is prefix's length
    public int sum(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);

            if (node.children.containsKey(c)) {
                node = node.children.get(c);
            } else {
                return 0;
            }
        }
        return node.sum;
    }

    private class TrieNode {
        Map<Character, TrieNode> children;
        int sum;

        public TrieNode() {
            children = new HashMap<>();
        }
    }

    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        mapSum.insert("app", 2);
        System.err.println(mapSum.sum("app"));
    }
}
