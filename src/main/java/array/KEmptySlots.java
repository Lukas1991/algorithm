package array;

public class KEmptySlots {

    /**
     * 683. K Empty Slots
     * There is a garden with N slots. In each slot, there is a flower. The N flowers will bloom one by one in N days.
     * In each day, there will be exactly one flower blooming and it will be in the status of blooming since then.
     *
     * Given a array flowers consists of number from 1 to N. Each number in the array represents the place where the flower will open in that day.
     *
     * For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x, where i and x will be in the range from 1 to N.
     *
     * Also given an integer k, you need to output in which day there exists two flowers in the status of blooming, and also the number of flowers between them is k and these flowers are not blooming.
     *
     * If there isn't such day, output -1.
     * Example 1: flowers: [1,3,2], k: 1, Output: 2
     * Example 2: flowers: [1,2,3], k: 1, Output: -1
     */
    public int kEmptySlots(int[] flowers, int k) {
        int n = flowers.length;
        boolean[] bloom = new boolean[n + 1];

        for (int i = 0; i < n; i++) {
            int x = flowers[i];
            bloom[x] = true;

            int left = x - k - 1;
            int right = x + k + 1;

            //scan x左边的K个,必须都不开花
            if (left >= 1 && bloom[left]) {
                int countNotBloom = countNotBloom(bloom, left + 1, x - 1);
                if (countNotBloom == k) {
                    return i + 1;
                }
            }

            //scan x右边的K个,必须都不开花
            if (right <= n && bloom[right]) {
                int countNotBloom = countNotBloom(bloom, x + 1, right - 1);
                if (countNotBloom == k) {
                    return i + 1;
                }
            }
        }

        return -1;
    }

    private int countNotBloom(boolean[] bloom, int start, int end) {
        int count = 0;
        for (int j = start; j <= end; j++) {
            if (bloom[j]) {
                break;
            } else {
                count++;
            }
        }
        return count;
    }
}
