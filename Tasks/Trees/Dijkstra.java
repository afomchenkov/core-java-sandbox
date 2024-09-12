package Tasks.Trees;

import java.util.*;

// Given a source vertex s from a set of vertices V in a weighted digraph (directed graph)
// where all its edge weights w(u, v) are non-negative, find the shortest path weights d(s, v)
// from source s for all vertices v present in the graph.
//
// Vertex 	Minimum Cost 	Route
// A —> B 	   4 	         A —> E —> B
// A —> C 	   6 	         A —> E —> B —> C
// A —> D 	   5 	         A —> E —> D
// A —> E 	   3 	         A —> E
//
//
// Dijkstra’s Algorithm is an algorithm for finding the shortest paths between nodes in a graph. 
// For a given source node in the graph, the algorithm finds the shortest path between that node 
// and every other node. It can also be used for finding the shortest paths from a single node to 
// a single destination node by stopping the algorithm once the fastest route to the destination 
// node has been determined.
//
// > Dijkstra's pseudocode:
//
// function Dijkstra(Graph, source) {
//     dist[source] = 0 // Initialization
//     create vertex set Q
//
//     for each vertex v in Graph {
//         if v != source {
//             dist[v] = INFINITY // Unknown distance from source to v
//             prev[v] = UNDEFINED // Predecessor of v
//         }
//         Q.add_with_priority(v, dist[v])
//     }
//     while Q is not empty {
//         u = Q.extract_min() // Remove minimum
//         for each neighbor v of u that is still in Q {
//             alt = dist[u] + length(u, v)
//             if alt < dist[v] {
//                 dist[v] = alt
//                 prev[v] = u
//                 Q.decrease_priority(v, alt)
//             }
//         }
//     }
//     return dist[], prev[]
// }
class Edge {

    int source, dest, weight;

    public Edge(int source, int dest, int weight) {
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }
}

class Node {

    int vertex, weight;

    public Node(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}

class Graph {

    List<List<Edge>> adjList = null;

    Graph(List<Edge> edges, int n) {
        adjList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (Edge edge : edges) {
            adjList.get(edge.source).add(edge);
        }
    }
}

public class Dijkstra {

    private static void getRoute(int[] prev, int i, List<Integer> route) {
        if (i >= 0) {
            getRoute(prev, prev[i], route);
            route.add(i);
        }
    }

    public static void findShortestPaths(Graph graph, int source, int n) {
        PriorityQueue<Node> minHeap;
        minHeap = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight));
        minHeap.add(new Node(source, 0));

        // set initial distance from the source to `v` as infinity
        List<Integer> dist;
        dist = new ArrayList<>(Collections.nCopies(n, Integer.MAX_VALUE));
        dist.set(source, 0);

        // track vertices for which minimum cost is already found
        boolean[] done = new boolean[n];
        done[source] = true;

        // stores predecessor of a vertex
        int[] prev = new int[n];
        prev[source] = -1;

        while (!minHeap.isEmpty()) {
            Node node = minHeap.poll();
            int u = node.vertex;

            for (Edge edge : graph.adjList.get(u)) {
                int v = edge.dest;
                int weight = edge.weight;

                // Relaxation step
                if (!done[v] && (dist.get(u) + weight) < dist.get(v)) {
                    dist.set(v, dist.get(u) + weight);
                    prev[v] = u;
                    minHeap.add(new Node(v, dist.get(v)));
                }
            }

            // mark vertex `u` as done so it will not get picked up again
            done[u] = true;
        }

        List<Integer> route = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (i != source && dist.get(i) != Integer.MAX_VALUE) {
                getRoute(prev, i, route);
                System.out.printf("Path (%d —> %d): Minimum cost = %d, Route = %s\n",
                        source, i, dist.get(i), route);
                route.clear();
            }
        }
    }

    public static void main(String[] args) {
        // initialize edges as per the above diagram
        // (u, v, w) represent edge from vertex `u` to vertex `v` having weight `w`
        List<Edge> edges = Arrays.asList(
                new Edge(0, 1, 10), new Edge(0, 4, 3), new Edge(1, 2, 2),
                new Edge(1, 4, 4), new Edge(2, 3, 9), new Edge(3, 2, 7),
                new Edge(4, 1, 1), new Edge(4, 2, 8), new Edge(4, 3, 2)
        );

        // total number of nodes in the graph (labelled from 0 to 4)
        int n = 5;

        // construct graph
        Graph graph = new Graph(edges, n);

        // run the Dijkstra’s algorithm from every node
        for (int source = 0; source < n; source++) {
            findShortestPaths(graph, source, n);
        }
    }

    // Path (0 —> 1): Minimum cost = 4, Route = [0, 4, 1]
    // Path (0 —> 2): Minimum cost = 6, Route = [0, 4, 1, 2]
    // Path (0 —> 3): Minimum cost = 5, Route = [0, 4, 3]
    // Path (0 —> 4): Minimum cost = 3, Route = [0, 4]
    // Path (1 —> 2): Minimum cost = 2, Route = [1, 2]
    // Path (1 —> 3): Minimum cost = 6, Route = [1, 4, 3]
    // Path (1 —> 4): Minimum cost = 4, Route = [1, 4]
    // Path (2 —> 3): Minimum cost = 9, Route = [2, 3]
    // Path (3 —> 2): Minimum cost = 7, Route = [3, 2]
    // Path (4 —> 1): Minimum cost = 1, Route = [4, 1]
    // Path (4 —> 2): Minimum cost = 3, Route = [4, 1, 2]
    // Path (4 —> 3): Minimum cost = 2, Route = [4, 3] 
}
