package math;

public class DivideTwoInteger {

    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return dividend >= 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }

        if (dividend == 0) {
            return 0;
        }

        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        boolean isNegative = dividend > 0 ^ divisor > 0;

        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);
        int result = 0;
        while (a >= b) {
            long sum = b; //b*1, b*2, b*4, b*8...
            int multiply = 1; //1, 2, 4, 8 ...
            while (a >= sum + sum) {
                sum = sum + sum;
                multiply = multiply + multiply;
            }

            a = a - sum;
            result = result + multiply;
        }
        return isNegative ? -result : result;
    }
}
