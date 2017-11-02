package combination;

import java.util.HashMap;
import java.util.Map;

public class NumberOfBoomerangs {

    /**
     * Find the number of boomerangs.
     * a "boomerang" is a tuple of points (i, j, k) such that the distance between i and j equals the distance between i and k
     * (the order of the tuple matters).
     * @param points
     * @return
     */
    /**
     * The order matters: Permutation, P(n, 2) = n(n-1) The order doesn't matter: Combination, C(n,2) = n(n-1)/2
     *
     * Permutation formula: You have n items and want to find the number of ways k items can be ordered: P(n,k) = n! /(n-k)!
     *
     * Combination formula: or the number of ways to combine k items from a set of n: C(n,k) = n!/(n-k)! * k!
     */
    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            //populate distance map
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                } else {
                    int d = getDis(points[i], points[j]);
                    map.put(d, map.getOrDefault(d, 0) + 1);
                }
            }

            res += map.values().stream()
                    .filter(a -> a > 1)
                    .mapToInt(a -> a*(a-1))
                    .sum();
           
            map.clear();
        }

        return res;
    }

    private int getDis(int[] x, int[] y) {
        int d1 = x[0] - y[0];
        int d2 = x[1] - y[1];
        return d1 * d1 + d2 * d2;
    }
}
