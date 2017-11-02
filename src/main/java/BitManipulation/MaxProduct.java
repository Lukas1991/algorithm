package BitManipulation;

public class MaxProduct {

    /**
     * 318. Maximum Product of Word Lengths.
     * Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters.
     * You may assume that each word will contain only lower case letters. If no such two words exist, return 0.
     *
     * Example 1: Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
     * Return 16
     * The two words can be "abcw", "xtfn".
     */
    public int maxProduct(String[] words) {
        int max = 0;
        int n = words.length;
        int[] value = new int[n];

        for (int i = 0; i < n; i++) {
            value[i] = 0;
            for (int k = 0; k < words[i].length(); k++) {
                char c = words[i].charAt(k);
                value[i] |= 1 << (c - 'a');
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((value[i] & value[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }

        return max;
    }
}
