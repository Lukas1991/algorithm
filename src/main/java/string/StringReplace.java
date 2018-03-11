package string;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two identical-sized string array A, B and a string S. All substrings A appearing in S are replaced by B.
 * (Notice: From left to right, it must be replaced if it can be replaced. If there are multiple alternatives, replace longer priorities. After the replacement of the characters can't be replaced again.)
 * Given A = ["ab","aba"], B = ["cc","ccc"], S = "ababa", return "cccba".
 */
public class StringReplace {

    public String stringReplace(String[] a, String[] b, String s) {
        StringBuilder builder = new StringBuilder(s);

        for (int i = 0; i < s.length(); i++) {
            int maxLen = -1;
            int index = -1;

            for (int j = 0; j < a.length; j++) {
                String sa = a[j];
                if (i + sa.length() <= s.length()) {
                    //此处超时
                    String sub = s.substring(i, i + sa.length());
                    if (sub.equals(sa)) {
                        if (sub.length() > maxLen) {
                            maxLen = sub.length();
                            index = j;
                        }
                    }
                }
            }

            if (maxLen != -1) {
                builder.replace(i, i + maxLen, b[index]);
                i += maxLen - 1;
            }
        }
        return builder.toString();
    }

    int mod = 10000007;

    /**
     * Rolling hash 方法优化substring equals
     */
    public String stringReplace2(String[] a, String[] b, String s) {
        int mxLen = -1;
        List<Integer> aHash = new ArrayList<>();
        //calculate hash of each string in a
        for (String sa : a) {
            int hash = 1;
            for (int i = 0; i < sa.length(); i++) {
                hash = (hash * 31 + (sa.charAt(i) - 'a')) % mod;
            }
            aHash.add(hash);
            mxLen = Math.max(mxLen, sa.length());
        }

        List<Integer> sHash = new ArrayList<>();
        sHash.add(1);
        int hash = 1;
        for (int i = 0; i < s.length(); i++) {
            hash = (hash * 31 + (s.charAt(i) - 'a')) % mod;
            sHash.add(hash);
        }
        mxLen = Math.max(mxLen, s.length());

        List<Integer> baseList = new ArrayList<>();
        baseList.add(1);
        int base = 1;
        for (int i = 0; i < mxLen; i++) {
            base = base * 31 % mod;
            baseList.add(base);
        }

        StringBuilder builder = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            int maxLen = -1;
            int index = -1;

            for (int j = 0; j < a.length; j++) {
                int len = a[j].length();
                int l = i + 1;
                int r = i + len;
                if (r > s.length()) {
                    continue;
                }

                int leftHash = (int) ((long) baseList.get(r - l + 1) * sHash.get(l - 1) % mod);
                int sHashValue = (sHash.get(r) - leftHash + mod) % mod;

                int aHashValue = (aHash.get(j) - baseList.get(len) + mod) % mod;
                if (sHashValue == aHashValue && len > maxLen) {
                    maxLen = len;
                    index = j;
                }

            }

            if (maxLen != -1) {
                builder.replace(i, i + maxLen, b[index]);
                i += maxLen - 1;
            }
        }
        return builder.toString();
    }
}
