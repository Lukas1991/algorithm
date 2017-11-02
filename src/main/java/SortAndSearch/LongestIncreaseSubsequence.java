package SortAndSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 300. Longest Increasing Subsequence
 */
public class LongestIncreaseSubsequence {

    /**
     * Time complexity : O(n^2)
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;

        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            int maxJ = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxJ = Math.max(dp[j], maxJ);
                }
            }
            dp[i] = maxJ + 1;
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    /**
     * DP + Binary Search Time complexity : O(nlog(n))
     */
    public int lengthOfLISBinarySearch(int[] nums) {
        int[] dp = new int[nums.length]; //tmp array to hold incresing subsequence
        int len = 0;//to mark the tmp array's actural length
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num); //find index to insert num to tmp array
            if (i < 0) {
                i = -(i + 1);
            }

            dp[i] = num;
            if (i == len) { //if insert num at the end of valid tmp array
                len++;
            }
        }
        return len;
    }

    /**
     * Return the Longest Increasing Subsequence
     */
    public static List<Integer> increasing(int[] a) {
        List<Integer>[] resArray = new ArrayList[a.length];

        for (int i = 0; i < a.length; i++) {
            increasing(a, resArray, i);
        }

        System.out.println("All Increasing Lists: " + Arrays.toString(resArray));

        //find max_size's index
        int maxSizeIndex = 0;
        int maxSize = 0;
        for (int j = 0; j < resArray.length; j++) {
            if (resArray[j].size() > maxSize) {
                maxSize = resArray[j].size();
                maxSizeIndex = j;
            }
        }

        System.out.println("max length is:" + maxSize);
        return resArray[maxSizeIndex];
    }

    public static void increasing(int[] a, List<Integer>[] solutions, int index) {
        //System.out.println("index is: " + index);

        List<Integer> best_seq = null;
        for (int i = 0; i < index; i++) {
            if (a[i] < a[index]) {
                best_seq = seqMaxLength(best_seq, solutions[i]);
                //System.out.println(best_seq);
            }
        }

        //System.out.println("best seq: " + best_seq);
        ArrayList<Integer> newSeq = new ArrayList<>();
        if (best_seq != null) {
            newSeq.addAll(best_seq);
        }
        newSeq.add(a[index]);

        solutions[index] = newSeq;
        //System.out.println("new_seq: " + newSeq);
    }

    public static List<Integer> seqMaxLength(List<Integer> s1, List<Integer> s2) {
        if (s1 == null) {
            return s2;
        }
        if (s2 == null) {
            return s1;
        }
        return s1.size() > s2.size() ? s1 : s2;
    }

    public static void main(String[] args) {
        int[] a = {0, 7, 3, 5, 9, 4, 8};

        List<Integer> result = increasing(a);
        System.out.println("result: " + result);
    }

}
