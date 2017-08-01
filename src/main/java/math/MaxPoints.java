package math;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Slope of two points is (y1 - y2)/(x1 - x2) Case 1: x1=x2, slope is infinite Case 2: y1=y2, slope is 0 Case 3: we cannot use
 * double as slope because of percision, we use Irreducible fraction (最简分数)
 *
 * The greatest common divisor (gcd 最大公约数) is used for reducing fractions to be in lowest terms.
 */
public class MaxPoints {

    public int maxPoints(Point[] points) {
        if (points.length <= 2) {
            return points.length;
        }

        int maxPoints = 0;

        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            int sameCount = 0;
            int infiniteCount = 0;
            Map<String, Integer> map = new HashMap<>();

            for (int j = i + 1; j < points.length; j++) {
                Point p1 = points[j];
                if (p1.x == p.x && p1.y == p.y) {
                    sameCount++;
                } else if (p1.x == p.x) {
                    infiniteCount++;
                } else {
                    String slope;
                    if (p1.y == p.y) {
                        slope = "0";
                    } else {
                        int diffY = p1.y - p.y;
                        int diffX = p1.x - p.x;
                        String sign = getSign(diffY, diffX);

                        diffY = Math.abs(diffY);
                        diffX = Math.abs(diffX);

                        int gcd = gcd(diffY, diffX);

                        diffY = diffY / gcd;
                        diffX = diffX / gcd;

                        slope = sign + diffY + "/" + diffX;
                    }

                    map.put(slope, map.getOrDefault(slope, 0) + 1);
                }
            }

            int max = map.values().stream().max(Comparator.naturalOrder()).orElse(0);
            max = Math.max(max, infiniteCount);
            max = max + sameCount + 1;

            maxPoints = Math.max(maxPoints, max);
        }

        return maxPoints;
    }

    private String getSign(int a, int b) {
        if ((a > 0 && b < 0) || (a < 0 && b > 0)) {
            return "-";
        } else {
            return "+";
        }
    }

    //greatest common divisor, e.g. gcd(12,8) = 4, gcd(5,2)=1
    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    public static void main(String[] args) {
        MaxPoints obj = new MaxPoints();
        Point[] points = {
            p(0, 0),
            p(2, 2),
            p(-1, -1)
        };

//        Point[] points = {
//            p(0,0),
//            p(94911151, 94911150),
//            p(94911152, 94911151)
//        };

        int res = obj.maxPoints(points);
        System.err.println(res);

        System.err.println(obj.gcd(2, 5)); //1
        System.err.println(obj.gcd(2, 5)); //1
    }

    public static Point p(int x, int y) {
        return new Point(x, y);
    }
}
