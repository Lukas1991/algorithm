package string;

public class OneEditDistance {

    /**
     * There're 3 possibilities to satisfy one edit distance apart:
     *
     * 1) Replace 1 char:
          s: a B c
          t: a D c
     * 2) Delete 1 char from s:
          s: a D  b c
          t: a    b c
     * 3) Delete 1 char from t
          s: a   b c
          t: a D b c
     */
    public boolean isOneEditDistance(String s, String t) {

        for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (s.length() == t.length()) {
                    //replace i
                    return s.substring(i+1).equals(t.substring(i+1));
                } else if (s.length() > t.length()) {
                    //delete i from s
                    return s.substring(i+1).equals(t.substring(i));
                } else {
                    //delete i from t
                    return t.substring(i+1).equals(s.substring(i));
                }
            }
        }

        //All previous chars are the same, the only possibility is deleting the end char in the longer one of s and t
        return Math.abs(s.length() - t.length()) == 1;
    }
}
