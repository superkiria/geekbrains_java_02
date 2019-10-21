package ru.motrichkin.datastructures_tests.graphs_tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.motrichkin.datastructures.graphs.Graph;

@RunWith(JUnit4.class)
public class GraphTests {

    @Test
    public void graphTest_00() {
        Graph graph = new Graph();
        graph.addEdge(1, 2, 1);
        graph.addEdge(2,3, 1);
        graph.addEdge(3,1, 1);
        graph.addEdge(3,4, 1);
        Assert.assertEquals(1, Graph.countPath(graph.findBestRoute(1, 2)));
        Assert.assertEquals(1, Graph.countPath(graph.findBestRoute(2, 3)));
        Assert.assertEquals(1, Graph.countPath(graph.findBestRoute(3, 1)));
        Assert.assertEquals(2, Graph.countPath(graph.findBestRoute(1, 4)));
    }

    @Test
    public void graphTest_01() {
        Graph graph = new Graph();
        Assert.assertNull(graph.findBestRoute(1, 2));
        graph.addEdge(1, 2, 1);
        Assert.assertNull(graph.findBestRoute(1, 3));
        Assert.assertNull(graph.findBestRoute(2, 3));
        Assert.assertEquals(1, Graph.countPath(graph.findBestRoute(1, 2)));
        graph.addEdge(1,3, 2);
        Assert.assertEquals(2, Graph.countPath(graph.findBestRoute(1, 3)));
        graph.addEdge(2,4, 5);
        graph.addEdge(3,4, 10);
        Assert.assertEquals(6, Graph.countPath(graph.findBestRoute(1, 4)));
        Assert.assertEquals(3, Graph.countPath(graph.findBestRoute(2, 3)));
        graph.addEdge(5, 6, 10);
        Assert.assertNull(graph.findBestRoute(1, 6));
        graph.addEdge(4, 5, 3);
        Assert.assertEquals(19, Graph.countPath(graph.findBestRoute(1, 6)));
        Assert.assertEquals(21, Graph.countPath(graph.findBestRoute(3, 6)));
        graph.addEdge(3, 7, 1);
        graph.addEdge(7, 8, 2);
        graph.addEdge(4, 8, 1);
        graph.addEdge(5, 8, 2);
        Assert.assertEquals(15, Graph.countPath(graph.findBestRoute(3, 6)));
        graph.addEdge(5, 5, 1);
        Assert.assertEquals(15, Graph.countPath(graph.findBestRoute(3, 6)));
        graph.addEdge(3, 4, 1);
        Assert.assertEquals(14, Graph.countPath(graph.findBestRoute(3, 6)));
        graph.addEdge(9, 10, 6);
        graph.addEdge(3, 10, 7);
        graph.addEdge(9, 1, 11);
        Assert.assertEquals(27, Graph.countPath(graph.findBestRoute(9, 6)));
        System.out.println(graph.findBestRoute(9, 6));
    }

}
