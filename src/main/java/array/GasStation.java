package array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class GasStation {

    /**
     * 1) if total of gas[] >= total of cost[], then there exists a start index to complete the circle.
     * 2) if A can not reach C in a the sequence of A-->B-->C, then B can not make it either. use C as a new start
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {

        if (gas.length == 0 || cost.length == 0 || gas.length != cost.length) {
            return -1;
        }
        int start = 0, total = 0, sum = 0;
        for (int i = 0; i < gas.length; i++) {
            sum += gas[i] - cost[i];
            if (sum < 0) {
                sum = 0;
                start = i + 1;
            }
            total += gas[i] - cost[i];

        }

        if (total >= 0) return start;
        else return -1;

    }

    //O(n^2)
    public static int canCompleteCircuit2(int[] gas, int[] cost) {

        if (gas.length == 0 || cost.length == 0 || gas.length != cost.length) {
            return -1;
        }

        for (int i = 0; i < gas.length; i++) {
            int left = gas[i];
            boolean flag = true;
            int j = i;
            while (flag) {
                left = left - cost[j % gas.length];
                if (left >= 0) {//can travel to the next
                    j++;
                    int newIndex = j % gas.length;
                    left = left + gas[newIndex];
                    if (newIndex == i) {
                        return i;
                        //flag = false;
                    }

                } else {
                    flag = false;
                }
            }


        }

        return -1;
    }

    /**
     * 一辆公交车，有一定的油，要到d miles 之外的目的地。起点终点之间有一些加油站(GasStation)，加油站这个类有两个变量，1，到目的地的距离，2，有多少油。
     * 问公交车最少要停多少次来加油 才能到目的地。公交车耗油是 1 加仑／迈
     * g = 10 gallon,
     * d = 20 miles,
     * list of GasStation,
     * gasStations = [[15, 1], [14,10], [12,12]]
     * minimum stop = 1, 停在最后一个加油站加油就行。
     * <p>
     * BFS
     */
    public int minSteps(int g, int d, int[][] gasStations) {
        if (g >= d) {
            return 0;
        }

        //sort gasStations by distance DESC
        Arrays.sort(gasStations, (a1, a2) -> a2[0] - a1[0]);

        Node root = new Node(g, -1);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);


        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Node node = queue.poll();

                for (int k = node.index + 1; k < gasStations.length; k++) {
                    int dis;
                    if (node.index >= 0) {
                        dis = gasStations[node.index][0] - gasStations[k][0];
                    } else {
                        dis = d - gasStations[k][0];
                    }

                    if (node.gallon - dis >= 0) {
                        //能走到
                        int newgallon = node.gallon - dis + gasStations[k][1];
                        if (newgallon >= gasStations[k][0]) {
                            return step;
                        }
                        Node newnode = new Node(newgallon, k);
                        queue.offer(newnode);
                    }
                }
            }
        }

        return -1;
    }

    class Node {
        int gallon;//在station加完油之后，剩余多少油
        int index; //某个station

        public Node(int gallon, int index) {
            this.gallon = gallon;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        GasStation obj = new GasStation();
        int[][] gasStations = {{15, 1}, {14, 10}, {12, 12}};
        System.err.println(obj.minSteps(10, 20, gasStations));

        gasStations = new int[][]{{18, 4}, {12, 1}, {10, 8}, {4, 2}};
        System.err.println(obj.minSteps(6, 20, gasStations));
    }

}
