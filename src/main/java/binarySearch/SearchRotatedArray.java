package binarySearch;

/**
 * 33. Search in Rotated Sorted Array
 * 81. Search in Rotated Sorted Array II
 */
public class SearchRotatedArray {

	/**
     * 33. Search in Rotated Sorted Array
     * You may assume no duplicate exists in the array.
     * mid 跟A[last]比较，判断是左边还是右边
	 */
    public int search(int[] A, int target) {
        if (A == null || A.length == 0) {
            return -1;
        }

        int start = 0, end = A.length - 1;
        int last = A[A.length - 1];

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            //可写可不写
            // if (A[mid] == target) {
            //     return mid;
            // }

            if (A[mid] > last) {
                if (target >= A[start] && target <= A[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }

			} else {
                if (target >= A[mid] && target <= A[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
			}
		}

        if (A[start] == target) {
            return start;
        }

        if (A[end] == target) {
            return end;
        }

		return -1;
	}


	/**
     * What if duplicates are allowed? Would this affect the run-time complexity? How and why?
	 */
    // 这个问题在面试中不会让实现完整程序
    // 只需要举出能够最坏情况的数据是 [1,1,1,1... 1] 里有一个0即可。
    // 在这种情况下是无法使用二分法的，复杂度是O(n)
    // 因此写个for循环最坏也是O(n)，那就写个for循环就好了
    //  如果你觉得，不是每个情况都是最坏情况，你想用二分法解决不是最坏情况的情况，那你就写一个二分吧。
    //  反正面试考的不是你在这个题上会不会用二分法。这个题的考点是你想不想得到最坏情况。
    public boolean searchDuplicate(int[] nums, int target) {
        for (int n : nums) {
            if (n == target) {
                return true;
			}
		}

        return false;
	}

}
