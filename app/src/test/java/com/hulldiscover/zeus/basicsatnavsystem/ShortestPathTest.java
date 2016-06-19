package com.hulldiscover.zeus.basicsatnavsystem;

import com.hulldiscover.zeus.basicsatnavsystem.Model.ShortestPath;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Zeus on 14/06/16.
 */
public class ShortestPathTest {

    // Test Data
    // Vertex (node) points
    public static final String A = "A";
    public static final String B = "B";
    public static final String C = "C";
    public static final String D = "D";
    public static final String E = "E";

    // Graph Edges
    private static final Graph.Edge[] GRAPH = {
            new Graph.Edge(A, B, 5),
            new Graph.Edge(B, C, 4),
            new Graph.Edge(C, D, 7),
            new Graph.Edge(D, C, 8),
            new Graph.Edge(D, E, 6),
            new Graph.Edge(A, D, 5),
            new Graph.Edge(C, E, 2),
            new Graph.Edge(E, B, 3),
            new Graph.Edge(A, E, 7),
    };

    // Graph
    Graph directedGraph;

    // Path Search
    BreadthFirstPaths findAllPaths;

    // Shortest Path Search
    ShortestPath searchForShortestPath;


    /**
     * Set up test environment
     * based on given specification.
     * */
    @Before
    public void setUp() {
        // Init Graph
        directedGraph = new Graph(GRAPH);

        // Init search
        findAllPaths = new BreadthFirstPaths(directedGraph);

        // Init shortest path search
        searchForShortestPath = new ShortestPath();
    }



    // The length of the shortest
    // route (in terms of distance to travel)
    // from A to C.
    // Expected output 9
    @Test
    public void testShortestPathFirstCase() {

        // Prepare
        ArrayList<String> expected = new ArrayList<>();
        expected.add("A");
        expected.add("B");
        expected.add("C");

        int expectedLength = 9;
        int actualDistance = 0;

        String startPoint = "A";
        String destination = "C";

        Graph.Vertex C = new Graph.Vertex("C");

        // Find shortest route
        Map<Graph.Vertex,Integer> discoveredRoute = searchForShortestPath.shortestPath(directedGraph, startPoint, destination);

        // Get the shortest path as list
        List<Graph.Vertex> path = searchForShortestPath.getPath();

        // Create new list to store String representation
        // of vertex points found in shortest path
        ArrayList<String> shortestPath = new ArrayList<String>();

        // Add vertex points in path
        // as a String to new list
        for(Graph.Vertex vertex : path) {
            shortestPath.add(vertex.name); // string representation
        }

        // Calculate distance
        actualDistance += discoveredRoute.get(C);
        System.out.println("Distance is " +actualDistance);

        /*for (Map.Entry<Graph.Vertex, Integer> route : discoveredRoute.entrySet()) {
            Graph.Vertex key = route.getKey();
            Integer value = route.getValue();
            System.out.println(key.name + " : "  +value);
        }*/

        // Assert
        Assert.assertEquals(expectedLength, actualDistance);

    }

    // The route of the shortest
    // path from A to C.
    // Expected [A, B, C]
    @Test
    public void testShortestPathSecondCase() {

        // Prepare
        ArrayList<String> expected = new ArrayList<>();

        expected.add("A");
        expected.add("B");
        expected.add("C");

        String startPoint = "A";
        String destination = "C";

        // Find shortest route
        Map<Graph.Vertex,Integer> discoveredRoute = searchForShortestPath.shortestPath(directedGraph, startPoint, destination);

        // Get the shortest path as list
        List<Graph.Vertex> path = searchForShortestPath.getPath();

        // Create new list to store String representation
        // of vertex points found in shortest path
        ArrayList<String> shortestPath = new ArrayList<String>();

        // Add vertex points in path
        // as a String to new list
        for(Graph.Vertex vertex : path) {
            shortestPath.add(vertex.name); // string representation
        }

        // Assert
        Assert.assertEquals(expected, shortestPath);
    }



}
