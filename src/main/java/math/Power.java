package math;

/**
 * x^N, Power of Two, Is Power of N
 */
public class Power {

    /**
     * O(logn) Note: n=0, n<0, n = Integer.MIN_VALUE
     */
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1.0;
        }

        if (n == 1) {
            return x;
        }

        //MIN is -2147483648, MAX is 2147483647, MAX has 1 less than abs(MIN)
        if (n == Integer.MIN_VALUE) {
            return myPow(1 / x, Integer.MAX_VALUE) * (1 / x);
        }

        if (n < 0) {
            return myPow(1 / x, -n);
        }

        if (n % 2 == 0) {
            return myPow(x * x, n / 2);
        } else {
            return myPow(x * x, n / 2) * x;
        }
    }

    public static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    /**
     * Implement a Boolean function - bool f(long x , long y). The function should return true if the first value x is a power of
     * the second value y. Inputs should be non-negative integral values.
     *
     * powerofn(9, 3) == True
     * powerofn(8,2) == True
     * powerofn(5,2) == False
     *
     */
    public static boolean powerofn(long x, long y) {
        if (x <= 0L) {
            return false;
        }
        if (x == 1L) {
            return true;
        }

        long tmp = y;
        while (tmp < x) {
            tmp *= y;
            if (tmp == x) {
                return true;
            }

        }

        return false;
    }


    public static void main(String[] args) {

        System.err.println(powerofn(9, 3));
        System.err.println(powerofn(8, 2));
        System.err.println(powerofn(5, 2));

        System.err.println(powerofn(1, 2));
    }
}
