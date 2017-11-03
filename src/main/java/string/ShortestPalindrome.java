package string;

public class ShortestPalindrome {

    //O(n^2)
    /**
     * 为了求最短panlindrome, i 从reverse 的头开始，如果一开始reverse.substring(0) == s.substring(0,n),那么string 本身就是palindrome.
     */
    public String shortestPalindrome(String s) {
        StringBuilder sb = new StringBuilder(s);
        String reverse = sb.reverse().toString();
        int n = s.length();

        for (int i = 0; i < n; i++) {
            if (s.substring(0, n - i).equals(reverse.substring(i))) {

                return reverse.substring(0, i) + s;
            }
        }

        return "";
    }
}
