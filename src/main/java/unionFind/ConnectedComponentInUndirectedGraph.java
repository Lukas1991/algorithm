package unionFind;

import Graph.UndirectedGraphNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Find the number connected component in the undirected graph. Each node in the graph contains a label and a list of its neighbors.
 * Each connected component should sort by label.
 * Union Find
 */
public class ConnectedComponentInUndirectedGraph {

    Map<Integer, Integer> parentMap = new HashMap<>();

    public List<List<Integer>> connectedSet(List<UndirectedGraphNode> nodes) {
        List<List<Integer>> res = new ArrayList<>();
        HashSet<Integer> labels = new HashSet<>();
        init(nodes, labels);

        //union
        for (UndirectedGraphNode n : nodes) {
            for (UndirectedGraphNode nei : n.neighbors) {
                union(n.label, nei.label);
            }
        }

        //key is parent, value - other nodes in the same set
        Map<Integer, Set<Integer>> unionMap = new HashMap<>();
        for (int l : labels) {
            int p = find(l);
            Set<Integer> children = unionMap.getOrDefault(p, new HashSet<>());
            children.add(l);
            unionMap.put(p, children);
        }

        unionMap.values().forEach(set -> {
            List<Integer> list = new ArrayList<>(set);
            Collections.sort(list);
            res.add(list);
        });

        return res;
    }

    void init(List<UndirectedGraphNode> nodes, HashSet<Integer> labels) {
        for (UndirectedGraphNode n : nodes) {
            labels.add(n.label);
            for (UndirectedGraphNode nei : n.neighbors) {
                labels.add(nei.label);
            }
        }
        for (int l : labels) {
            parentMap.put(l, l);
        }
    }

    void union(int a, int b) {
        int roota = find(a);
        int rootb = find(b);
        if (roota != rootb) {
            parentMap.put(roota, rootb);
        }
    }

    int find(int a) {
        if (parentMap.get(a) == a) {
            return a;
        } else {
            int p = find(parentMap.get(a));
            parentMap.put(a, p);
            return p;
        }
    }
}
