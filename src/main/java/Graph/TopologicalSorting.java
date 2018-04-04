package Graph;

import java.util.*;

/**
 * Given an directed graph, a topological order of the graph nodes is defined as follow:
 * <p>
 * For each directed edge A -> B in graph, A must before B in the order list.
 * The first node in the order can be any node in the graph with no nodes direct to it.
 * <p>
 * Find any topological order for the given graph.
 */
public class TopologicalSorting {

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        //map count how many parents a node has
        Map<DirectedGraphNode, Integer> map = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode nei : node.neighbors) {
                map.put(nei, map.getOrDefault(nei, 0) + 1);
            }
        }

        //put no incoming edge(no parent) nodes into queue
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        for (DirectedGraphNode node : graph) {
            if (!map.containsKey(node)) {
                queue.offer(node);
            }
        }

        ArrayList<DirectedGraphNode> res = new ArrayList<>();

        while (!queue.isEmpty()) {
            DirectedGraphNode node = queue.poll();
            res.add(node);

            for (DirectedGraphNode nei : node.neighbors) {
                int count = map.get(nei);
                count--;
                if (count == 0) {
                    queue.offer(nei);
                }
                map.put(nei, count);
            }
        }

        return res;
    }
}
