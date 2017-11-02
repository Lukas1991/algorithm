package math;

/**
 * Power of Two Power of Three Power of Four
 */
public class Power {

    /**
     * O(logn) Note: n=0, n<0, n = Integer.MIN_VALUE
     */
    public double myPow(double x, int n) {
        if (n == 0) return 1.0;

        if (n == 1) return x;

        //MIN is -2147483648, MAX is 2147483647, MAX has 1 less than abs(MIN)
        if (n == Integer.MIN_VALUE) return myPow(1 / x, Integer.MAX_VALUE) * (1 / x);

        if (n < 0) return myPow(1 / x, -n);

        if (n % 2 == 0) {
            return myPow(x * x, n / 2);
        } else {
            return myPow(x * x, n / 2) * x;
        }
    }

    //the regex checks if the string starts with 1, and followed by zero or more 0
    public static boolean isPowerOfTwo(int n) {
        return Integer.toBinaryString(n).matches("10*");
    }

    public static boolean isPowerOfThree(int num) {
        return num > 0 && Integer.toString(num, 3).matches("10*");
    }

    public static boolean isPowerOfFour(int num) {
        return num > 0 && Integer.toString(num, 4).matches("10*");
    }

    public boolean isPowerOfThreeLoop(int n) {
        if (n <= 0) {
            return false;
        }
        while (n != 1) {
            if (n % 3 == 0) {
                n = n / 3;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * the maximum value of n that is also a power of three is 1162261467 Therefore, the possible values of n where we should return
     * true are 3^0, 3^1 ... 3^19​, Since 3 is a prime number, the only divisors of 3^19​ are 3^0, 3^1 ... 3^19​, therefore all we
     * need to do is divide 3^19​​ by n. A remainder of 0 means n is a divisor of 3^19​, and therefore a power of three.
     */
    public boolean isPowerOfThreeFast(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

    public static int getMaxNumberPowerOfThree() {
        int i = 0;
        int max = 1;
        int maxThree = Integer.MAX_VALUE / 3;
        while (max < maxThree) {
            max = 3 * max;
            i++;
        }
        return max;
    }


    public static void main(String[] args) {
        //System.err.println(getMaxNumberPowerOfThree());  //1162261467

        //System.err.println(isPowerOfTwo(4));
        //System.err.println(isPowerOfTwo(6));

        int a = -2147483648;

        System.err.println(-a);

        System.err.println(Integer.MIN_VALUE);
        System.err.println(Integer.MAX_VALUE);
    }
}
