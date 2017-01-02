package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ComputerNetwork {

    /**
     * The element in the network array, [1, 2, 5]: 1 is from node, 2 is to node, 5 is the distance bewteen them.
     * find the shortest distance from node 1 to node n in the given network.
     * @param n
     * @param network
     * @return
     */
    int computerNetwork(int n, int[][] network) {
        if (network.length == 0) return 0;
        if (n ==1) return 0;
        Map<Integer, Set<Integer>> neighborMap = new HashMap<>();
        Map<String, Integer> disMap = new HashMap<>();

        getMap(network, neighborMap, disMap);

        List<Integer> distances = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(1);

        getDistance(1, n, path, 0, distances, neighborMap, disMap);
        int min = Integer.MAX_VALUE;

        for (int dis : distances) {
            if (dis < min) {
                min = dis;
            }
        }

        return min;
    }

    private void getDistance(int start, int target, List<Integer> path, int pathSum, List<Integer> distances,
                             Map<Integer, Set<Integer>> neighborMap, Map<String, Integer> disMap) {

        Set<Integer> neighbors = neighborMap.get(start);
        for(int n : neighbors) {
            if (!path.contains(n)) {
                int dis = disMap.get(start + "-" + n);
                if (n == target) {
                    distances.add(pathSum + dis);
                } else {
                    path.add(n);
                    getDistance(n, target, path, pathSum + dis, distances, neighborMap, disMap);
                    path.remove(path.size() - 1);
                }
            }
        }
    }

    private void getMap(int[][] network, Map<Integer, Set<Integer>> neighborMap, Map<String, Integer> disMap) {

        for (int i=0; i<network.length; i++) {
            int[] entry = network[i];
            int from = entry[0];
            int to = entry[1];
            int dis = entry[2];

            if(!neighborMap.containsKey(from)) {
                neighborMap.put(from, new HashSet<>());
            }
            neighborMap.get(from).add(to);

            if(!neighborMap.containsKey(to)) {
                neighborMap.put(to, new HashSet<>());
            }
            neighborMap.get(to).add(from);

            disMap.put(from + "-" + to, dis);
            disMap.put(to + "-" + from, dis);
        }
    }

    public static void main(String[] args) {
        ComputerNetwork solution = new ComputerNetwork();
        int[][] network = {{1, 4, 200},
            {1, 2, 5},
            {1, 3, 10},
            {2, 3, 4},
            {2, 4, 150},
            {3, 4, 100}};

        int shortestDistance = solution.computerNetwork(4, network);
        System.out.println(shortestDistance);
    }
}
