package array;

import java.util.Arrays;

/**
 * 452. Minimum Number of Arrows to Burst Balloons
 */
public class FindMinArrowShots {

    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        if (points.length == 1) return 1;

        //sort by end
        Arrays.sort(points, (p1, p2) -> p1[1] - p2[1]);

        int count = 1;
        int lastshot = points[0][1];

        for (int i = 1; i < points.length; i++) {
            int[] cur = points[i];

            if (lastshot >= cur[0]) {
                continue;
            }
            //no overlap
            count++;
            lastshot = cur[1];
        }

        return count;
    }

    public static void main(String[] args) {
        FindMinArrowShots obj = new FindMinArrowShots();
        int[][] points = {{3, 9}, {7, 12}, {3, 8}, {6, 8}, {9, 10}, {2, 9}, {0, 9}, {3, 9}, {0, 6}, {2, 8}}; //2
        System.err.println(obj.findMinArrowShots(points));
    }
}
