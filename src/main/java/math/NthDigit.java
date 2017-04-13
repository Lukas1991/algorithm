package math;

public class NthDigit {

    public static int findNthDigit(int n) {
        int len = 1;
        long count = 9;
        int start = 1;

        while (n > len * count) {
            n -= len * count;
            len++;
            count *= 10;
            start *= 10;
        }

        int number = start + (n - 1) / len;
        String str = Integer.toString(number);
        char c = str.charAt((n - 1) % len);
        return Character.getNumericValue(c);
    }

    public static void main(String[] args) {
        findNthDigit(2147483647);
    }
}
