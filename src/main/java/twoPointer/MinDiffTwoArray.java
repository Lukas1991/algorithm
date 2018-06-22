package twoPointer;

import java.util.Arrays;
import org.junit.Test;

public class MinDiffTwoArray {

    int minDiff(int[] a, int[] b) {
        int min = Integer.MAX_VALUE;

        Arrays.sort(a);
        Arrays.sort(b);

        int i = 0;
        int j = 0;
        while (i < a.length && j < b.length) {
            int diff = Math.abs(a[i] - b[j]);
            min = Math.min(min, diff);
            if (a[i] < b[j]) {
                i++;
            } else if (a[i] > b[j]) {
                j++;
            } else {
                break;
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    @Test
    public void test1() {
        int[] a = {5, 4, 6};
        int[] b = {6, 7};

        System.err.println(minDiff(a, b));
    }


    @Test
    public void test2() {
        int[] a = {};
        int[] b = {-2, 8,};

        System.err.println(minDiff(a, b));
    }

}
