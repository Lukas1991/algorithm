package matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

 For example, given three people living at (0,0), (0,4), and (2,2):

 1 - 0 - 0 - 0 - 1
 |   |   |   |   |
 0 - 0 - 0 - 0 - 0
 |   |   |   |   |
 0 - 0 - 1 - 0 - 0
 The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.
 */
public class BestMeetingPoint {

    /**
     * 为了保证总长度最小，我们只要保证每条路径尽量不要重复就行了，
     * 比如[1,2,4],meeting point is 3, 1->2->3<-4这种一维的情况，如果起点是1，2和4，那2->3和1->2->3这两条路径就有重复了。
     * 为了尽量保证右边的点向左走，左边的点向右走，那我们就应该去这些点中间的点作为交点。
     * 由于是曼哈顿距离，我们可以分开计算横坐标和纵坐标，结果是一样的。
     * 所以我们算出各个横坐标到中点横坐标的距离，加上各个纵坐标到中点纵坐标的距离，就是结果了。
     */
    public static int minTotalDistance(int[][] grid) {
        int m = grid[0].length, n = grid.length;

        List<Integer> xPos = new ArrayList<>();
        List<Integer> yPos = new ArrayList<>();


        //get all the x and y positions
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (grid[i][j] == 1) {
                    xPos.add(j);
                    yPos.add(i);
                }
            }
        }

        //print(xPos, yPos);

        int sum = 0;

        //yPos is in order, don't need to sort. get distance from all Y to midY
        int midY = yPos.get(yPos.size() /2);
        for(int y : yPos) {
            sum += Math.abs(y - midY);
        }

        //sort xPos. get distance from all X to midX
        Collections.sort(xPos);
        int midX = xPos.get(xPos.size() /2);
        for (int x : xPos) {
            sum += Math.abs(x - midX);
        }

        return sum;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1,0,0,0,1},
                {0,0,0,0,0},
                {0,0,1,0,0}
        };
        int sum = minTotalDistance(grid);
        System.err.println(sum);
    }

    private static void print(List<Integer> xPos, List<Integer> yPos) {
        System.err.println("xPos: ");
        for(int x : xPos) {
            System.err.print(x + ",");
        }
        System.err.println();

        System.err.println("yPos: ");
        for(int y : yPos) {
            System.err.print(y + ",");
        }
        System.err.println();
    }
}
