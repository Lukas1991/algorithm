package SortAndSearch;

import java.util.Arrays;
import java.util.Random;

/**
 * QuickSort is a Divide and Conquer algorithm. It picks an element as pivot and partitions the given array around the picked pivot.
 * There are many different versions of quickSort that pick pivot in different ways.
 *
 * Always pick first element as pivot.
 * Always pick last element as pivot (implemented below)
 * Pick a random element as pivot.
 * Pick median as pivot.
 *
 * The key process in quickSort is partition(). Target of partitions is, given an array and an element x of array as pivot, put x at
 * its correct position in sorted array and put all smaller elements (smaller than x) before x, and put all greater elements
 * (greater than x) after x. All this should be done in linear time.
 *
 * Time complexity of Quick Sort is O(nLogn) in average, O(n) best, O(n^2) worst
 *
 * Quick Sort is preferred over MergeSort for sorting Arrays, because 1) in-place sort, doesn't require extra storage. 2) Most practical implementations of Quick Sort use randomized version.
 */
public class QuickSort {

    public int[] quickSort(int arr[]) {
        return quickSort(arr, 0, arr.length - 1);
    }

    public int[] quickSort(int[] arr, int start, int end) {

        if (start < end) {
            //pivot is arr[end]
            //pivotIndex is the partitioning index, arr[pivotIndex] is now at the correct place
            int pivotIndex = partition(arr, start, end);

            quickSort(arr, start, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, end);
        }

        return arr;

    }

    private int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int left = start - 1;

        for (int j = start; j < end; j++) {
            if (arr[j] <= pivot) {
                left++;
                swap(arr, left, j);
            }
        }

        swap(arr, left + 1, end);
        return left + 1;
    }

    private void swap(int[] a, int i, int j) {
        final int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        QuickSort qs = new QuickSort();

        int[] arr = {5, 86, 69, 73, 69, 19, 34, 20, 74, 3};
        int[] sorted = qs.quickSort(arr);
        System.out.println(Arrays.toString(sorted));


        Random generator = new Random();

		int[] a = new int[10];
		for (int i = 0; i < a.length; i++) {
            a[i] = generator.nextInt(100);
        }

		System.out.println("Before sort: " + Arrays.toString(a));
        a = qs.quickSort(a);
        System.out.println("After sort: " + Arrays.toString(a));
    }

}
