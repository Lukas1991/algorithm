package string;

import java.util.HashMap;
import java.util.Map;

/**
 * 273. Integer to English Words
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
 *
 * For example,
 * 123 -> "One Hundred Twenty Three"
 * 12345 -> "Twelve Thousand Three Hundred Forty Five"
 * 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 */
public class IntegerToEnglishWords {

    private Map<Integer, String> map;

    public String numberToWords(int num) {
        map = getMap();
        if (num == 0) {
            return map.get(0);
        }

        String[] arr = new String[] {"Billion", "Million", "Thousand", ""};
        StringBuilder sb = new StringBuilder();

        int base = 1000000000;
        for (int i = 0; i < arr.length; i++) {
            if (num >= base) {
                sb.append(less(num / base))
                    .append(" ")
                    .append(arr[i]);

                num = num % base;
                if (num == 0) {
                    return sb.toString().trim();
                } else {
                    sb.append(" ");
                }
            }

            base = base / 1000;

        }

        return sb.toString();
    }

    //n less than 1000
    private String less(int n) {
        StringBuilder sb = new StringBuilder();
        if (n >= 100) {
            sb.append(map.get(n / 100)).append(" ").append("Hundred");
            n = n % 100;
            if (n == 0) {
                return sb.toString();
            } else {
                sb.append(" ");
            }
        }

        if (map.containsKey(n)) {
            sb.append(map.get(n));
        } else {
            //n>20, like 25
            sb.append(map.get(n / 10 * 10)).append(" ").append(map.get(n % 10));
        }

        return sb.toString();
    }

    private Map<Integer, String> getMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "Zero");
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        map.put(5, "Five");
        map.put(6, "Six");
        map.put(7, "Seven");
        map.put(8, "Eight");
        map.put(9, "Nine");
        map.put(10, "Ten");

        map.put(11, "Eleven");
        map.put(12, "Twelve");
        map.put(13, "Thirteen");
        map.put(14, "Fourteen");
        map.put(15, "Fifteen");
        map.put(16, "Sixteen");
        map.put(17, "Seventeen");
        map.put(18, "Eighteen");
        map.put(19, "Nineteen");

        map.put(20, "Twenty");
        map.put(30, "Thirty");
        map.put(40, "Forty");
        map.put(50, "Fifty");
        map.put(60, "Sixty");
        map.put(70, "Seventy");
        map.put(80, "Eighty");
        map.put(90, "Ninety");
        return map;
    }

    public static void main(String[] args) {
        IntegerToEnglishWords obj = new IntegerToEnglishWords();
        System.err.println(obj.numberToWords(123));
        //System.err.println(obj.numberToWords(2147483647));
    }
}
