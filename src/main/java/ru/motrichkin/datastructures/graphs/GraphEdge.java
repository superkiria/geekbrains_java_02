package ru.motrichkin.datastructures.graphs;

public class GraphEdge {

    private final GraphNode from;
    private final GraphNode to;
    private final int weight;

    GraphEdge(GraphNode from, GraphNode to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    GraphNode getFrom() {
        return from;
    }

    GraphNode getTo() {
        return to;
    }

    int getWeight() {
        return weight;
    }

}
