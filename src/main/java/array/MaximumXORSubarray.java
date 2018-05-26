package array;

/**
 * Given an array of integers. find the maximum XOR subarray value in given array.
 * <p>
 * What's the XOR: https://en.wikipedia.org/wiki/Exclusive_or
 */
public class MaximumXORSubarray {

    class TrieNode {
        public int label;
        public TrieNode[] children;

        TrieNode(int label) {
            this.label = label;
            this.children = new TrieNode[2];
        }
    }

    /**
     * Using Trie data structure:
     * 1 saving the xor prefix into Trie.
     * 2 for each xor prefix, iterate from the root of Trie to find the current maximum subarray xor value.
     */
    public int maxXorSubarray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = 0;
        int pre_xor = 0;
        TrieNode root = new TrieNode(0);
        //根据j同学的提示，这里应该insert 0做初始化。
        insert(root, 0);
        for (int i = 0; i < nums.length; i++) {
            pre_xor ^= nums[i];
            insert(root, pre_xor);
            max = Math.max(max, searchAndFindMax(root, pre_xor));
        }
        return max;
    }

    private void insert(TrieNode root, int num) {
        TrieNode cur = root;
        for (int i = 31; i >= 0; i--) {
            int bit = num >> i & 1;
            if (cur.children[bit] == null) {
                cur.children[bit] = new TrieNode(bit);
            }
            cur = cur.children[bit];
        }
    }

    private int searchAndFindMax(TrieNode root, int num) {
        TrieNode cur = root;
        int res = 0;
        for (int i = 31; i >= 0; i--) {
            int bit = num >> i & 1;
            if (cur.children[1 - bit] != null) {
                res |= 1 << i;
                cur = cur.children[1 - bit];
            } else {
                cur = cur.children[bit];
            }
        }
        return res;
    }

}
