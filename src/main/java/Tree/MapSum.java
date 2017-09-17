package Tree;

public class MapSum {
    private TrieNode root;

    public MapSum() {
        root = new TrieNode();
    }

    public void insert(String key, int val) {
        TrieNode node = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (node.keys[c - 'a'] == null) {
                node.keys[c - 'a'] = new TrieNode();
            }
            node = node.keys[c - 'a'];
        }
        node.isEnd = true;
        node.val = val;
    }

    public int sum(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (node.keys[c - 'a'] != null) {
                node = node.keys[c - 'a'];
            } else {
                return 0;
            }
        }
        return find(node);
    }

    private int find(TrieNode node) {
        if (node == null) {
            return 0;
        }
        TrieNode curr = node;
        int count = 0;
        if (curr.isEnd) {
            count += curr.val;
        }

        for (TrieNode c : node.keys) {
            if (c != null) {
                count += find(c);
            }
        }
        return count;
    }


    class TrieNode {
        boolean isEnd;
        int val;
        //26 children
        TrieNode[] keys;

        public TrieNode() {
            keys = new TrieNode[26];
        }
    }

    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        mapSum.insert("app", 2);
        System.err.println(mapSum.sum("app"));
    }
}
