package string;

public class MinimumWindowSubstring {

    public String minWindow(String S, String T) {
        if (S.length() == 0) return "";
        String result = "";


        int[] set = new int[256];
        for (int i = 0; i < T.length(); i++) {
            set[T.charAt(i)]++;
        }

        int[] tmp = new int[256];
        int count = 0;
        int left = 0, right = 0, minSize = Integer.MAX_VALUE;

        for (right = 0; right < S.length(); right++) {
            //T doesn't contain
            if (set[S.charAt(right)] == 0) continue;

            if (tmp[S.charAt(right)] < set[S.charAt(right)]) {
                count++;
            }
            tmp[S.charAt(right)]++;

            if (count == T.length()) {

                while (set[S.charAt(left)] == 0 || tmp[S.charAt(left)] > set[S.charAt(left)]) {
                    if (tmp[S.charAt(left)] > set[S.charAt(left)]) {
                        tmp[S.charAt(left)]--;
                    }
                    left++;
                }

                int winSize = right - left + 1;
                if (winSize < minSize) {
                    result = S.substring(left, right+1);
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

        System.err.println(min.minWindow(s, t));
    }
}
