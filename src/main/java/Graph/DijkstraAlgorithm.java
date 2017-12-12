package Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class DijkstraAlgorithm {

    class Vertex {
        String name;
        List<Edge> outgoings = new LinkedList<>();
        int minDistance = Integer.MAX_VALUE;
        Vertex previous;

        public Vertex(String name) {
            this.name = name;
        }
    }

    class Edge {
        Vertex from;
        Vertex to;
        int weight;

        public Edge(Vertex from, Vertex to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
            from.outgoings.add(this);
        }
    }

    /**
     * For all vertices, set minDistance = max value. previous vertex = null
     * Source Vertex.minDistance = 0;
     *
     * unvisited vertices heap, visited vertices set
     * while heap is not empty, get the current min distance Vertex. for all outgoing edges, calculate distance. if new distance is smaller,
     * update min distance and set previous
     */
    public void computePaths(Vertex source) {
        source.minDistance = 0;
        //min distance heap
        PriorityQueue<Vertex> unvisited = new PriorityQueue<>((v1, v2) -> v1.minDistance - v2.minDistance);
        unvisited.offer(source);

        Set<Vertex> visited = new HashSet<>();

        while (!unvisited.isEmpty()) {
            Vertex current = unvisited.poll();
            visited.add(current);

            for (Edge edge : current.outgoings) {
                Vertex to = edge.to;
                if (!visited.contains(to)) {
                    int distance = current.minDistance + edge.weight;
                    if (distance < to.minDistance) {
                        unvisited.remove(to);
                        to.minDistance = distance;
                        to.previous = current;
                        unvisited.offer(to);
                    }
                }
            }
        }
    }

    public List<Vertex> getShortestPathTo(Vertex destination) {
        List<Vertex> path = new LinkedList<>();
        Vertex node = destination;
        while (node != null) {
            path.add(0, node);
            node = node.previous;
        }
        return path;
    }

    public List<Vertex> createGraph() {
        List<Vertex> vertices = new ArrayList<>();

        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");
        Vertex c = new Vertex("c");
        Vertex d = new Vertex("d");
        Vertex e = new Vertex("e");

        Edge ab = new Edge(a, b, 4);
        Edge ac = new Edge(a, c, 2);
        Edge cb = new Edge(c, b, 1);
        Edge bc = new Edge(b, c, 3);
        Edge bd = new Edge(b, d, 2);
        Edge be = new Edge(b, e, 3);
        Edge cd = new Edge(c, d, 4);
        Edge ce = new Edge(c, e, 5);
        Edge ed = new Edge(e, d, 1);

        vertices.add(a);
        vertices.add(b);
        vertices.add(c);
        vertices.add(d);
        vertices.add(e);

        return vertices;
    }

    public static void main(String[] args) {
        DijkstraAlgorithm obj = new DijkstraAlgorithm();
        List<Vertex> vertices = obj.createGraph();

        Vertex source = vertices.get(0);
        obj.computePaths(source);

        Vertex destination = vertices.get(3); //to D
        List<Vertex> path = obj.getShortestPathTo(destination);

        System.err.println("path to D");
        for (Vertex vertex : path) {
            System.err.println(vertex.name + " minDistance: " + vertex.minDistance);
        }


        Vertex destination2 = vertices.get(4); //to E
        List<Vertex> path2 = obj.getShortestPathTo(destination2);

        System.err.println("path to E");
        for (Vertex vertex : path2) {
            System.err.println(vertex.name + " minDistance: " + vertex.minDistance);
        }
    }
}
