package dp;

import java.util.ArrayList;
import java.util.List;

public class DecodeWays {
    /**
     * Scan the String from end to start
     *
     * if leading zeros are not valid, dp[i] = 0;
     * if leading zeros are valid (002, 1001) dp[i] = dp[i+1]
     */
    public int numDecodings(String s) {
        final int n = s.length();
        if (n == 0) {
            return 0;
        }

        int[] dp = new int[n + 1];

        dp[n] = 1;
        dp[n - 1] = s.charAt(n - 1) == '0' ? 0 : 1;

        for (int i = n - 2; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                dp[i] = dp[i + 1];  //or dp[i] == 0;
            } else if (Integer.valueOf(s.substring(i, i + 2)) <= 26) {
                dp[i] = dp[i + 1] + dp[i + 2];
            } else {
                dp[i] = dp[i + 1];
            }
        }

        return dp[0];
    }


    public List<String> getNumDecodings(String s) {
        int n = s.length();
        if (n == 0) {
            return new ArrayList<>();
        }

        List<String> prepre = new ArrayList<>();
        prepre.add("");
        List<String> pre = new ArrayList<>();
        if (s.charAt(n - 1) == '0') {
            pre.add("");
        } else {
            pre.add(getChar(s.substring(n - 1, n)) + "");
        }


        for (int i = n - 2; i >= 0; i--) {
            List<String> current = new ArrayList<>();
            char c = s.charAt(i);
            if (c == '0') {
                current.addAll(pre);
            } else {
                for (String str : pre) {
                    current.add(getChar(s.substring(i, i + 1)) + str);
                }

                if (Integer.parseInt(s.substring(i, i + 2)) <= 26) {
                    for (String str : prepre) {
                        current.add(getChar(s.substring(i, i + 2)) + str);
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

    /**
     * 639. Decode Ways II
     * 1. The encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9
     * 2. The answer may be very large, you should return the output mod 10^9 + 7.
     *
     * current char can be 0, *, 1-2, 3-9
     * next(i+1) char can be *, 0-6, 7-9
     */
    public int numDecodings2(String s) {
        int M = 1000000007;
        int n = s.length();
        if (n == 0) {
            return 0;
        }

        char[] arr = s.toCharArray();

        long prepre = 1; //dp[i+2]
        long res;   //dp[i]
        if (arr[n - 1] == '*') {
            res = 9;
        } else if (arr[n - 1] == '0') {
            res = 0;
        } else {
            res = 1;
        }
        long pre = res; //dp[i+1]

        for (int i = n - 2; i >= 0; i--) {
            char c = arr[i];    //can be 0, *, 1, 2, 3-9
            char next = arr[i + 1];

            if (c == '0') {
                res = 0;
            } else if (c == '*') {
                res = pre * 9;

                if (next == '*') {
                    res += prepre * 15; //(11-19) (21-26) count = 15
                } else if (next - '0' <= 6) {
                    res += prepre * 2;
                } else {
                    res += prepre;
                }
                res = res % M;
            } else if (c == '1') {
                if (next == '*') {
                    res = pre + prepre * 9;//(11-19) count = 9
                } else {
                    res = pre + prepre;
                }
                res = res % M;
            } else if (c == '2') {
                if (next == '*') {
                    res = pre + prepre * 6; //(21-26) count = 6
                } else if (next - '0' <= 6) {
                    res = pre + prepre;
                } else {
                    res = pre;
                }
                res = res % M;
            } else { //c == 3-9
                res = pre;
            }

            prepre = pre;
            pre = res;
        }

        return (int) res;
    }


    public static void main(String[] args) {
        DecodeWays d = new DecodeWays();
        System.err.println(d.numDecodings("*"));   //9
        System.err.println(d.numDecodings("*1"));  //11
        System.err.println(d.numDecodings("*9"));  //10
        System.err.println(d.numDecodings("**"));   //96
        System.err.println(d.numDecodings("2"));  //1
        System.err.println(d.numDecodings("1*"));   //18
        System.err.println(d.numDecodings("2*"));   //15
        System.err.println(d.numDecodings("3*"));   //9

        System.err.println(d.numDecodings("*0"));   //2

//        System.err.println("002 - " + d.numDecodings2("002"));
//        System.err.println("1001 - " + d.numDecodings2("1001"));
//
//        System.err.println(d.getNumDecodings("002"));
//        System.err.println(d.getNumDecodings("1001"));
//        System.err.println(d.getNumDecodings("21001"));
    }
}
