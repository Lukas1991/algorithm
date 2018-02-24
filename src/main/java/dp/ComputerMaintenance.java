package dp;

import java.util.HashMap;
import java.util.Map;
import tools.Point;

/**
 * A n*m matrix represents an array of computers, and give a list which represents the coordinate of the broken computer. Now we start with (0,0) and repair the computer.
 * There are some request:
 * 1. You have to fix all the broken computers in the current line to get to the next line.
 * 2. If you are going to the next line, the mechanic must first return to the far left or right of the line.
 * Find the minimum access distance.
 * After fixing the last computer, you need to return to the far left or right of the last line.
 */
public class ComputerMaintenance {

    public int maintenance(int n, int m, Point[] Badcomputers) {
        //key is x, value is [min, max] 每行坏电脑的min max位置
        Map<Integer, int[]> map = new HashMap<>();
        for (Point p : Badcomputers) {
            if (!map.containsKey(p.x)) {
                map.put(p.x, new int[] {p.y, p.y});
            } else {
                map.get(p.x)[0] = Math.min(p.y, map.get(p.x)[0]);
                map.get(p.x)[1] = Math.max(p.y, map.get(p.x)[1]);
            }
        }

        //preLeft: 上一次都修完之后返回到了左边
        //preRight: 上一次都修完之后返回到了右边
        //left: 两种走法，上一层左边出发，修完返回左边。或者上一层右边出发，修完也返回左边
        //right: 两种走法，都返回到右边。
        int preLeft, preRight, left, right;

        if (map.containsKey(0)) {
            int max = map.get(0)[1];
            preLeft = max * 2;
            preRight = m - 1;
        } else {
            preLeft = 0;
            preRight = m - 1;
        }

        for (int i = 1; i < n; i++) {
            int max;
            int min;
            if (map.containsKey(i)) {
                max = map.get(i)[1];
                min = map.get(i)[0];

                left = Math.min(preLeft + max * 2, preRight + m - 1) + 1;
                right = Math.min(preLeft + m - 1, preRight + (m - 1 - min) * 2) + 1;
            } else {
                left = Math.min(preLeft, preRight + m - 1) + 1;
                right = Math.min(preLeft + m - 1, preRight) + 1;
            }

            preLeft = left;
            preRight = right;
        }

        return Math.min(preLeft, preRight);
    }

    public static void main(String[] args) {
        ComputerMaintenance obj = new ComputerMaintenance();

        Point p1 = new Point(0, 3);
        Point p2 = new Point(1, 7);
        Point p3 = new Point(1, 2);

        Point[] Badcomputers = new Point[] {p1, p2, p3};
        System.err.println(obj.maintenance(3, 10, Badcomputers));
    }
}
