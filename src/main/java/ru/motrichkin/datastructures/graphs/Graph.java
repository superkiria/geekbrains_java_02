package ru.motrichkin.datastructures.graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Graph {

    public static int countPath(List<GraphEdge> list) {
        int result = 0;
        for (GraphEdge i : list) {
            result += i.getWeight();
        }
        return result;
    }

    private HashMap<Integer, GraphNode> nodes = new HashMap<>();

    private GraphNode getOrMakeNode(int a) {
        if (!nodes.containsKey(a)) {
            nodes.put(a, new GraphNode(a));
        }
        return nodes.get(a);
    }

    private GraphNode getNodeOrNull(int a) {
        return nodes.getOrDefault(a, null);
    }

    public void addEdge(int a, int b, int weight) {
        getOrMakeNode(a);
        getOrMakeNode(b);
        getNodeOrNull(a).addEdge(nodes.get(b), weight);
        getNodeOrNull(b).addEdge(nodes.get(a), weight);
    }

    public List<GraphEdge> findBestRoute(int a, int b) {
        if (a == b) {
            return new LinkedList<>();
        }
        if (nodes.containsKey(a) && nodes.containsKey(b)) {
            return nodes.get(a).findBestRoute(b);
        }
        return null;
    }

}
