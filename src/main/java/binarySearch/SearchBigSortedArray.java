package binarySearch;

public class SearchBigSortedArray {

    /**
     * http://www.jiuzhang.com/solutions/search-in-a-big-sorted-array/
     *
     * Given a big sorted array with positive integers sorted by ascending order. The array is so big so that you can not get the
     * length of the whole array directly, and you can only access the kth number by ArrayReader.get(k).
     * Find the first index of a target number. Your algorithm should be in O(log k), where k is the first index of the target number.
     * Return -1, if the number doesn't exist in the array.
     *
     * Time Complexity: O(log k), k is the first index of the given target number.
     *
     * 1. 找到end index的位置, end从1开始，exponential backoff, 两倍两倍上升, 倍增法
     * 2. 二分查找，start=0, end
     */
    public int searchBigSortedArray(ArrayReader reader, int target) {

        int count = 1; //第一个数
        while (reader.get(count - 1) < target) {
            count = count * 2;
        }

        int start = 0, end = count - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int val = reader.get(mid);

            if (val == target) {
                end = mid;
            } else if (val < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (reader.get(start) == target) {
            return start;
        }

        if (reader.get(end) == target) {
            return end;
        }

        return -1;
    }

    class ArrayReader {
        int get(int index) {
            return 0;
        }
    }

}
