package com.hulldiscover.zeus.basicsatnavsystem;

import com.hulldiscover.zeus.basicsatnavsystem.Production.DijkstraFindShortestPath;
import com.hulldiscover.zeus.basicsatnavsystem.Production.BreadthFirstFindAllPaths;
import com.hulldiscover.zeus.basicsatnavsystem.Production.DirectedGraph;

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

    // DirectedGraph Edges
    private static final DirectedGraph.Edge[] GRAPH = {
            new DirectedGraph.Edge(A, B, 5),
            new DirectedGraph.Edge(B, C, 4),
            new DirectedGraph.Edge(C, D, 7),
            new DirectedGraph.Edge(D, C, 8),
            new DirectedGraph.Edge(D, E, 6),
            new DirectedGraph.Edge(A, D, 5),
            new DirectedGraph.Edge(C, E, 2),
            new DirectedGraph.Edge(E, B, 3),
            new DirectedGraph.Edge(A, E, 7),
    };

    // DirectedGraph
    DirectedGraph directedDirectedGraph;

    // Path Search
    BreadthFirstFindAllPaths findAllPaths;

    // Shortest Path Search
    DijkstraFindShortestPath searchForShortestPath;


    /**
     * Set up test environment
     * based on given specification.
     * */
    @Before
    public void setUp() {
        // Init DirectedGraph
        directedDirectedGraph = new DirectedGraph(GRAPH);

        // Init search
        findAllPaths = new BreadthFirstFindAllPaths(directedDirectedGraph);

        // Init shortest path search
        searchForShortestPath = new DijkstraFindShortestPath();
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

        DirectedGraph.Vertex C = new DirectedGraph.Vertex("C");

        // Find shortest route
        Map<DirectedGraph.Vertex,Integer> discoveredRoute = searchForShortestPath.shortestPath(directedDirectedGraph, startPoint, destination);

        // Get the shortest path as list
        List<DirectedGraph.Vertex> path = searchForShortestPath.getPath();

        // Create new list to store String representation
        // of vertex points found in shortest path
        ArrayList<String> shortestPath = new ArrayList<String>();

        // Add vertex points in path
        // as a String to new list
        for(DirectedGraph.Vertex vertex : path) {
            shortestPath.add(vertex.name); // string representation
        }

        // Calculate distance
        actualDistance += discoveredRoute.get(C);
        System.out.println("Distance is " +actualDistance);

        /*for (Map.Entry<DirectedGraph.Vertex, Integer> route : discoveredRoute.entrySet()) {
            DirectedGraph.Vertex key = route.getKey();
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
        Map<DirectedGraph.Vertex,Integer> discoveredRoute = searchForShortestPath.shortestPath(directedDirectedGraph, startPoint, destination);

        // Get the shortest path as list
        List<DirectedGraph.Vertex> path = searchForShortestPath.getPath();

        // Create new list to store String representation
        // of vertex points found in shortest path
        ArrayList<String> shortestPath = new ArrayList<String>();

        // Add vertex points in path
        // as a String to new list
        for(DirectedGraph.Vertex vertex : path) {
            shortestPath.add(vertex.name); // string representation
        }

        // Assert
        Assert.assertEquals(expected, shortestPath);
    }



}
