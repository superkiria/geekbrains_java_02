package ru.motrichkin.datastructures.graphs;

import static ru.motrichkin.datastructures.graphs.Graph.countPath;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class GraphNode {

    private Integer number;
    private LinkedList<GraphEdge> edges = new LinkedList<>();

    public GraphNode(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GraphNode) {
            return number.equals(((GraphNode) obj).getNumber());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }

    public void addEdge(GraphNode node, int weight) {
        edges.add(new GraphEdge(this, node, weight));
    }

    private List<GraphEdge> findBestRoute(GraphEdge edgeFrom, int target, Set<Integer> visitedNodes) {
        HashSet<Integer> newVisitedNodes = new HashSet<>(visitedNodes);
        newVisitedNodes.add(number);
        LinkedList<List<GraphEdge>> possibleRoutes = new LinkedList<>();
        for (GraphEdge edge : edges) {
            if (!newVisitedNodes.contains(edge.getTo().getNumber())) {
                if (edge.getTo().getNumber().equals(target)) {
                    LinkedList<GraphEdge> list = new LinkedList<>();
                    list.add(edge);
                    possibleRoutes.add(list);
                    break;
                }
                List<GraphEdge> route = edge.getTo().findBestRoute(edge, target, newVisitedNodes);
                if (route != null) {
                    possibleRoutes.add(edge.getTo().findBestRoute(edge, target, newVisitedNodes));
                }
            }
        }
        if (possibleRoutes.size() == 0) {
            return null;
        }
        List<GraphEdge> bestRoute = possibleRoutes.getFirst();
        int bestRouteWeight = countPath(bestRoute);
        for (List<GraphEdge> route : possibleRoutes) {
            if (bestRouteWeight > countPath(route)) {
                bestRoute = route;
                bestRouteWeight = countPath(route);
            }
        }
        if (edgeFrom != null) {
            bestRoute.add(edgeFrom);
        }
        return bestRoute;
    }

    public List<GraphEdge> findBestRoute(int target) {
        return findBestRoute(null, target, new HashSet<>());
    }

    final public Integer getNumber() {
        return number;
    }

}
