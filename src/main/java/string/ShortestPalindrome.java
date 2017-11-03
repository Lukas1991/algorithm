package string;

public class ShortestPalindrome {

    //O(n^2)
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
