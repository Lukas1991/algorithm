package twoPointer;

public class MinimumWindowSubstring {

    public String minWindow(String source, String target) {
        int min = Integer.MAX_VALUE;
        String minString = "";

        int[] thash = new int[256];
        int[] shash = new int[256];
        for (int i = 0; i < target.length(); i++) {
            thash[target.charAt(i)]++;
        }

        int j = 0;
        for (int i = 0; i < source.length(); i++) {
            while (j < source.length() && !containsAll(shash, thash)) {
                char c = source.charAt(j);
                shash[c]++;
                j++;
            }

            if (containsAll(shash, thash) && j - i <= min) {
                minString = source.substring(i, j);
                min = minString.length();
            }

            //remove i
            shash[source.charAt(i)]--;
        }

        return minString;
    }

    private boolean containsAll(int[] shash, int[] thash) {
        for (int i = 0; i < 256; i++) {
            if (shash[i] < thash[i]) {
                return false;
            }
        }
        return true;
    }

    public String minWindow2(String S, String T) {
        String result = "";

        int[] thash = new int[256];
        for (int i = 0; i < T.length(); i++) {
            thash[T.charAt(i)]++;
        }

        int[] shash = new int[256];
        int count = 0;
        int left = 0;
        int minSize = Integer.MAX_VALUE;

        for (int right = 0; right < S.length(); right++) {
            //T doesn't contain
            if (thash[S.charAt(right)] == 0) {
                continue;
            }

            if (shash[S.charAt(right)] < thash[S.charAt(right)]) {
                count++;
            }
            shash[S.charAt(right)]++;

            if (count == T.length()) {

                //move left until tmp[left c] == set[left c]
                while (thash[S.charAt(left)] == 0 || shash[S.charAt(left)] > thash[S.charAt(left)]) {
                    if (shash[S.charAt(left)] > thash[S.charAt(left)]) {
                        shash[S.charAt(left)]--;
                    }
                    left++;
                }

                int winSize = right - left + 1;
                if (winSize < minSize) {
                    result = S.substring(left, right + 1);
                    minSize = winSize;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        MinimumWindowSubstring min = new MinimumWindowSubstring();
        String s = "ADOBECODEBANC";
        String t = "ABC";

        System.err.println(min.minWindow("aa", "aa"));
    }
}
