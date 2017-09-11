package array;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {

    /**
     * 228. Summary Ranges.
     * Input: [0,1,2,4,5,7]
     * Output: ["0->2","4->5","7"]
     *
     * Input: [0,2,3,4,6,8,9]
     * Output: ["0","2->4","6","8->9"]
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }

        int startIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] != 1) {
                //i-1 to start
                res.add(convert(nums, startIndex, i - 1));
                startIndex = i;
            }
        }

        //add last
        res.add(convert(nums, startIndex, nums.length - 1));

        return res;
    }

    private String convert(int[] nums, int s, int e) {
        if (s == e) {
            return nums[s] + "";
        } else {
            return nums[s] + "->" + nums[e];
        }
    }
}
