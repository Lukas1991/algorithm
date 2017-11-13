package math;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PowerOfThree {

    private static final int MAX_POWER_OF_THREE = 1162261467;

    //Time is O(1), Space is O(1)

    /**
     * the possible power of 3 numbers are 3^0, 3^1, 3^2...
     * Since 3 is a prime number, we can calculate MAX_POWER_OF_THREE first, the only divisor of 3^19 are 3^0, 3^1, ..., 3^19
     * therefore, all we need to do is divide 3^19 by n. if the remainder is 0, then n is a divisor of 3^19, therefore n is a power of 3
     */
    public boolean isPower3(int num) {
        if (num <= 0) {
            return false;
        }

        //int maxExp = (int) (Math.log(Integer.MAX_VALUE) / Math.log(3)); //to calculate log MAX, base is 3
        //int maxPowerOfThree = (int) Math.pow(3, maxExp); //base, exponent 指数

        //System.err.println(maxExp + ", " + maxPowerOfThree);  //19, 1162261467

        return MAX_POWER_OF_THREE % num == 0;
    }

    //keep dividing n by 3 as long as the remainder is 0, and check the end result to be 1
    //[1, 3, 9]
    //Time O(log3 n) Space O(1)
    public boolean isPowerOfThreeLoop(int n) {
        if (n <= 0) {
            return false;
        }

        while (n % 3 == 0) {
            n = n / 3;
        }

        return n == 1;
    }

    public static void main(String[] args) {
        PowerOfThree obj = new PowerOfThree();

        System.out.println(obj.isPowerOfThreeLoop(0));
        System.out.println(obj.isPowerOfThreeLoop(1));
        System.out.println(obj.isPowerOfThreeLoop(3));

        System.out.println(obj.isPowerOfThreeLoop(10));
        System.out.println(obj.isPower3(Integer.MAX_VALUE));
    }

    @Test
    public void test() {
        assertFalse(isPower3(0));

        assertTrue(isPower3(1));
        assertTrue(isPower3(3));

        assertFalse(isPower3(10));
        assertFalse(isPower3(Integer.MAX_VALUE));
    }

}
