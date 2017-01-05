package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DecodeWays {
    /**
     * scan the String from end to start
     * if leading zeros are valid (002, 1001) dp[i] = dp[i+1]
     * if not valid, dp[i] = 0;
     */
    public int numDecodings2(String s) {
        final int n = s.length();
        if (n == 0) {
            return 0;
        }

        int[] dp = new int[n + 1];

        dp[n] = 1;
        dp[n-1] = s.charAt(n-1) == '0' ? 0 : 1;

        for (int i = n-2; i>=0; i--) {
            if (s.charAt(i) == '0') {
                dp[i] = dp[i + 1];  //or dp[i] == 0;
                continue;
            }

            if (Integer.valueOf(s.substring(i,  i+2)) <= 26) {
                dp[i] = dp[i+1] + dp[i+2];
            } else {
                dp[i] = dp[i+1];
            }
        }

        System.err.println("dp[] : " + Arrays.toString(dp));
        return dp[0];
    }


    public List<String> getNumDecodings (String s) {
        int n = s.length();
        if (n == 0) return new ArrayList<>();

        List<String> prepre = new ArrayList<>();
        prepre.add("");
        List<String> pre = new ArrayList<>();
        if (s.charAt(n-1) == '0') {
            pre.add("");
        } else {
            pre.add(getChar(s.substring(n-1, n)) + "");
        }


        for (int i = n-2; i>=0; i--) {
            List<String> current = new ArrayList<>();
            char c = s.charAt(i);
            if (c == '0') {
                current.addAll(pre);
            } else {
                for (String str : pre) {
                    current.add(getChar(s.substring(i, i+1)) + str);
                }

                if (Integer.parseInt(s.substring(i, i+2)) <= 26) {
                    for (String str : prepre) {
                        current.add(getChar(s.substring(i, i+2)) + str);
                    }
                }
            }

            prepre = pre;
            pre = current;
        }

        return pre;
    }

    private char getChar(String str) {
        int number = Integer.parseInt(str);
        char c = (char) ('A' + number - 1);
        return c;
    }

    public static void main(String[] args) {
        DecodeWays d = new DecodeWays();
        System.err.println("002 - " + d.numDecodings2("002"));
        System.err.println("1001 - " + d.numDecodings2("1001"));

        System.err.println(d.getNumDecodings("002"));
        System.err.println(d.getNumDecodings("1001"));
        System.err.println(d.getNumDecodings("21001"));
    }
}
