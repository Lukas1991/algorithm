package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 350. Intersection of Two Arrays II
 * What if the given array is already sorted? How would you optimize your algorithm? - use two pointers
 *
 * What if nums1's size is small compared to nums2's size? Which algorithm is better? - use Map. if sort, have to sort the larger array.
 *
 * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 * If only nums2 cannot fit in memory, put all elements of nums1 into a HashMap, read chunks of array that fit into the memory, and record the intersections.
 * If both nums1 and nums2 are so huge that neither fit into the memory, sort them individually (external sort),
 * then read 2 elements from each array at a time in memory, record intersections.
 */
public class IntersectionOfTwoArrays {

    //Time O(n), Space O(n)
    public int[] intersectHashMap(int[] nums1, int[] nums2) {
        //number, count
        Map<Integer, Integer> map = new HashMap<>();

        for (int i : nums1) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i : nums2) {
            if (map.containsKey(i)) {
                if (map.get(i) > 1) {
                    map.put(i, map.get(i) - 1);
                } else {
                    map.remove(i);
                }
                list.add(i);
            }
        }

        return list.stream().mapToInt(a -> a).toArray();
    }


    //Time O(mlogm) + O(nlogn), no additional space
    public int[] intersectSort(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> list = new ArrayList<>();

        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                list.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        return list.stream().mapToInt(a -> a).toArray();
    }

}
