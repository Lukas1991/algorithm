package Graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class CloneGraph {

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }

        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);

        Queue<UndirectedGraphNode> q = new LinkedList<>();
        q.offer(node);

        Map<Integer, UndirectedGraphNode> map = new HashMap<>();
        map.put(node.label, clone);

        while (!q.isEmpty()) {
            UndirectedGraphNode n1 = q.poll();
            UndirectedGraphNode clone1 = map.get(n1.label);

            for (UndirectedGraphNode n : n1.neighbors) {
                if (!map.containsKey(n.label)) {
                    map.put(n.label, new UndirectedGraphNode(n.label));
                    q.offer(n);
                }

                clone1.neighbors.add(map.get(n.label));
            }
        }

        return clone;
    }

}
