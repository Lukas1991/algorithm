package binarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindPeakElement {

    /**
     * Binary Search, Time complexity : O(log​2(n))
     */
    public int findPeakElementBest(int[] nums) {
        int l = 0;
        int r = nums.length - 1;

        while (l < r) {
            int mid = (l + r) / 2;

            if (nums[mid] > nums[mid + 1]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

    /**
     * Linear Scan, Time complexity : O(n)
     */
    public int findPeakElement(int[] nums) {

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }

        return nums.length - 1;
    }

    /**
     * O(m + n)
     */
    public List<Integer> findPeakII(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        return find(1, n - 2, 1, m - 2, A);
    }

    public List<Integer> find(int x1, int x2, int y1, int y2,
                              int[][] A) {

        int mid1 = x1 + (x2 - x1) / 2;
        int mid2 = y1 + (y2 - y1) / 2;

        int x = mid1, y = mid2;
        int max = A[mid1][mid2];
        for (int i = y1; i <= y2; ++i) {
            if (A[mid1][i] > max) {
                max = A[mid1][i];
                x = mid1;
                y = i;
            }
        }

        for (int i = x1; i <= x2; ++i) {
            if (A[i][mid2] > max) {
                max = A[i][mid2];
                x = i;
                y = mid2;
            }
        }

        boolean isPeak = true;
        if (A[x - 1][y] > A[x][y]) {
            isPeak = false;
            x -= 1;
        } else if (A[x + 1][y] > A[x][y]) {
            isPeak = false;
            x += 1;
        } else if (A[x][y - 1] > A[x][y]) {
            isPeak = false;
            y -= 1;
        } else if (A[x][y + 1] > A[x][y]) {
            isPeak = false;
            y += 1;
        }

        if (isPeak) {
            return new ArrayList<Integer>(Arrays.asList(x, y));
        }

        if (x >= x1 && x < mid1 && y >= y1 && y < mid2) {
            return find(x1, mid1 - 1, y1, mid2 - 1, A);
        }

        if (x >= 1 && x < mid1 && y > mid2 && y <= y2) {
            return find(x1, mid1 - 1, mid2 + 1, y2, A);
        }

        if (x > mid1 && x <= x2 && y >= y1 && y < mid2) {
            return find(mid1 + 1, x2, y1, mid2 - 1, A);
        }

        if (x >= mid1 && x <= x2 && y > mid2 && y <= y2) {
            return find(mid1 + 1, x2, mid2 + 1, y2, A);
        }

        return new ArrayList<Integer>(Arrays.asList(-1, -1));

    }


    public static void main(String[] args) {
        int[][] A = {
            {1, 2, 3, 6, 5},
            {16, 41, 19, 22, 6},
            {15, 17, 21, 22, 7},
            {14, 20, 24, 23, 10},
            {13, 14, 11, 10, 9}
        };

        FindPeakElement obj = new FindPeakElement();
        obj.findPeakII(A);
    }
}
