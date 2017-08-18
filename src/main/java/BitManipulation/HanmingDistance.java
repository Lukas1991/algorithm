package BitManipulation;

public class HanmingDistance {

    /**
     * For each bit position X=0-31, we count which integer in the array has that bitX=1
     * If n integers in array, k of them bitX=1, (n-k) of them bitX=0, then k*(n-k) hamming distance to the total
     */
    public int totalHammingDistance(int[] nums) {
        if (nums.length <=1)    return 0;
        int sum = 0;
        int n = nums.length;

        for (int i=0; i<32; i++) {
            int bitCount = 0;
            for (int j = 0; j<nums.length; j++) {
                int b = (nums[j]>>i) & 1;
                if (b == 1) {
                    bitCount++;
                }
            }
            sum += bitCount * (n - bitCount);
        }

        return sum;
    }
}
