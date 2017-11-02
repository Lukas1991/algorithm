package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiplyString {

    /**
     * 43. Multiply Strings
     * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.
     *
     * Better Solution:
     * The product of two numbers cannot exceed the sum of the two lengths. int[] arr = new int[len1 + len2];
     * num1[i] * num2[j] will be placed at indices [i + j, i + j + 1]
     */
    public String multiply(String num1, String num2) {
        if (isZero(num1) || isZero(num2)) {
            return "0";
        }
        int len1 = num1.length();
        int len2 = num2.length();

        int[] arr = new int[len1 + len2];

        for (int i = len2 - 1; i >= 0; i--) {
            for (int j = len1 - 1; j >= 0; j--) {
                int a2 = num2.charAt(i) - '0';
                int a1 = num1.charAt(j) - '0';

                int p1 = i + j;
                int p2 = i + j + 1;

                int res = a1 * a2 + arr[p2];

                arr[p2] = res % 10;
                arr[p1] = arr[p1] + res / 10;
            }
        }

        System.err.println(Arrays.toString(arr));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i == 0 && arr[i] == 0) {
                continue;
            }
            sb.append(arr[i]);
        }

        return sb.toString();
    }

    private boolean isZero(String str) {
        return str.length() == 1 && str.charAt(0) == '0';
    }

    /**
     * Same as what we do in math, multiply num1 by each num2[j], get the sum.
     * This way need more lines of code.
     */
    public String multiply2(String num1, String num2) {
        if (isZero(num1) || isZero(num2)) {
            return "0";
        }
        int len1 = num1.length();
        int len2 = num2.length();

        List<String> list = new ArrayList<>();
        int maxLen = 0;
        //multiply row by row
        for (int i = len2 - 1; i >= 0; i--) {
            int a2 = num2.charAt(i) - '0';
            StringBuilder sb = new StringBuilder();
            int zeroCount = len2 - 1 - i;
            while (zeroCount > 0) {
                sb.append("0");
                zeroCount--;
            }
            int carry = 0;
            for (int j = len1 - 1; j >= 0; j--) {
                int a1 = num1.charAt(j) - '0';
                int res = a1 * a2 + carry;
                sb.append(res % 10);
                carry = res / 10;
            }
            if (carry > 0) {
                sb.append(carry);
            }

            String reverse = sb.toString();
            list.add(reverse); //store string in reverse order
            maxLen = Math.max(maxLen, reverse.length());
        }

        //add list
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maxLen; i++) {
            int total = carry;
            for (String str : list) {
                if (i < str.length()) {
                    int a = str.charAt(i) - '0';
                    total += a;
                }
            }

            sb.append(total % 10);
            carry = total / 10;
        }

        if (carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        MultiplyString obj = new MultiplyString();
        System.err.println(obj.multiply("123", "45"));

        System.err.println(obj.multiply("999", "99"));
    }
}
