package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubarraySum {

    /**
     * Given an integer array, find a subarray where the sum of numbers is zero. Your code should return the index of the first number and the index of the last number.
     * Given [-3, 1, 2, -3, 4], return [0, 2] or [1, 3].
     *
     * sum(i,j) = sum(j) - sum(i-1) = 0
     * sum(j) = sum(i-1)
     * 找到之前的sum = 当前sum，用map
     */
    public List<Integer> subarraySum(int[] nums) {
        //key is sum, value is index
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        List<Integer> res = new ArrayList<>();

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (map.containsKey(sum)) {
                res.add(map.get(sum) + 1);
                res.add(i);
                return res;
            } else {
                map.put(sum, i);
            }
        }

        return res;
    }

    /**
     * Given an integer array, find a subarray where the sum of numbers is in a given interval. Your code should return the number of possible answers. (The element in the array should be positive)
     * Given [1,2,3,4] and interval = [1,3], return 4. The possible answers are:
     * [0, 0], [0, 1], [1, 1], [2, 2]
     *
     * sum(i,j) = sum(j) - sum(i-1)
     * start <= sum(j) - sum(i-1) <= end
     * left = sum(j) - end <= sum(i-1) <= sum(j) - start = right
     * seach for a range in previous sums [left, right]
     */
    public int subarraySumII(int[] A, int start, int end) {
        int n = A.length;
        int[] sum = new int[n];
        sum[0] = A[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + A[i];
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (sum[i] >= start && sum[i] <= end) {
                count++;
            }

            int left = sum[i] - end;
            int right = sum[i] - start;

            count += findFirstIndex(sum, n, right + 1) - findFirstIndex(sum, n, left);
        }

        return count;
    }

    //first index of sum >= value
    int findFirstIndex(int[] sum, int len, int value) {
        if (sum[len - 1] < value) {
            return len;
        }

        int start = 0, end = len - 1;
        int ans = 0;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (sum[mid] >= value) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (sum[start] >= value) {
            return start;
        }
        if (sum[end] >= value) {
            return end;
        } else {
            return -1;
        }
    }
}
