package string;

import java.util.ArrayList;
import java.util.List;

public class SearchPattern {

    /**
     * Search all pattern in txt, return the occurrences of start index.
     * compare each substring's hash with pattern's hash, if hash same, then compare each char
     *
     * @param pat pattern like "TEST"
     * @param txt text like "THIS IS A TEST TEXT - TEST"
     * @param q, a prime number
     * @return list of indexes
     */
    public static List<Integer> search(String pat, String txt, int q) {
        int pSize = pat.length();
        int txtSize = txt.length();

        int pHash = 0;
        int txtHash = 0;
        int highestBase = 1;

        for (int i = 0; i < pSize; i++) {
            pHash = (pHash * 256 + pat.charAt(i)) % q;
            txtHash = (txtHash * 256 + txt.charAt(i)) % q;
            if (i > 0) {
                highestBase = (highestBase * 256) % q;
            }
        }

        List<Integer> res = new ArrayList<>();
        if (pHash == txtHash && checkChars(pat, txt, 0, pSize)) {
            res.add(0);
        }

        for (int i = pSize; i < txtSize; i++) {
            char toRemove = txt.charAt(i - pSize);
            char toAdd = txt.charAt(i);

            txtHash = ((txtHash - toRemove * highestBase) * 256 + toAdd) % q;

            //we might get negative value of txtHash, convert it to positive
            if (txtHash < 0) {
                txtHash += q;
            }
            if (pHash == txtHash && checkChars(pat, txt, i - pSize + 1, i)) {
                res.add(i - pSize + 1);
            }
        }

        return res;
    }

    private static boolean checkChars(String pat, String txt, int start, int end) {
        int k = 0;
        for (int i = start; i <= end; i++) {
            if (pat.charAt(k) != txt.charAt(i)) {
                return false;
            } else {
                k++;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String txt = "THIS IS A TEST TEXT - TEST";
        String pattern = "TEST";
        List<Integer> res = search(pattern, txt, 101);
        for (int i : res) {
            System.err.println(txt.substring(i, i + pattern.length()));
        }
    }

}
