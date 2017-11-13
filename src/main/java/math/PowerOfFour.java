package math;

public class PowerOfFour {

    /**
     * 4^0 = 1, 4^1 = 100, 4^2 = 10000.
     *
     * For power of 4, We can check whether it is power of 2 first, and then check whether the bit 1 is at the odd position.
     *
     * We can use "n&(n-1) == 0" to determine if n is power of 2.
     *
     * And use "num & 0x55555555 == num" to check if "1" is located at the odd position.
     *
     * 5 is 0101, make the even position bit to 0.
     */
    public boolean isPowerOfFour(int num) {
        return num > 0 && (num & (num-1)) == 0 && (num & 0x55555555) == num;
    }

    public boolean isPowerOfFour2(int num) {
        return num > 0 && (num & (num-1)) == 0 && (num & 0x55555555) != 0;
    }

    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n-1)) == 0;
    }

    public static void main(String[] args){
        int[] nums = {0, 1, 5, 8, 9, 16, 48, 64, 196};
        PowerOfFour obj = new PowerOfFour();

        for(int num: nums){
            System.out.println(num + " is Power4? " + obj.isPowerOfFour(num));
        }
    }
}
