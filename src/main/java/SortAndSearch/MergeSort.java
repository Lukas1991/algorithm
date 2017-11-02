package SortAndSearch;

import java.util.Arrays;
import java.util.Random;


public class MergeSort {


    /**
     * Merge sort is a Divide and Conquer algorithm. it devides input array into 2 halves, calls itself mergeSort for the first half and the second half, and then merges the two sorted halves.
     * Time complexity of Merge Sort is O(nLogn) in all 3 cases (worst, average and best) as merge sort always divides the array in two halves and take linear time to merge two halves.
     * Extra Space: O(n)
     * Merge Sort is useful for sorting linked lists in O(nLogn) time. Merge sort accesses data sequentially and the need of random access is low.
     * Unlike array, in linked list, we can insert items in the middle in O(1) extra space and O(1) time. Therefore merge operation of merge sort can be implemented without extra space for linked lists.
     * merge sort is generally considered better when data is huge and stored in external storage.
     */
    public static int[] mergeSort(int[] arr) {
        int n = arr.length;
        if (n <= 1) {
            return arr;
        }

        int leftSize = n / 2;
        int rightSize = n - n / 2;

        //create tmp arrays
        int[] L = new int[leftSize];
        int[] R = new int[rightSize];

        //copy data to tmp arrays
        for (int i = 0; i < leftSize; i++) {
            L[i] = arr[i];
        }

        for (int i = 0; i < rightSize; i++) {
            R[i] = arr[n / 2 + i];
        }

        L = mergeSort(L);
        R = mergeSort(R);

        return merge(L, R);
    }

    public static int[] merge(int[] L, int[] R) {
        int l1 = L.length, l2 = R.length;
        int[] temp = new int[l1 + l2];
        int i = 0, j = 0, k = 0;

        while (i < l1 && j < l2) {
            if (L[i] < R[j]) {
                temp[k] = L[i];
                i++;
                k++;
            } else {
                temp[k] = R[j];
                j++;
                k++;
            }
        }

        while (i < l1) {
            temp[k] = L[i];
            i++;
            k++;
        }
        while (j < l2) {
            temp[k] = R[j];
            j++;
            k++;
        }
        return temp;
    }

    public static void main(String[] args) {
        Random generator = new Random();

        int[] a = new int[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = generator.nextInt(100);
        }

        System.out.println(Arrays.toString(a));

        int[] newa = mergeSort(a);
        System.out.println(Arrays.toString(newa));

    }

}
