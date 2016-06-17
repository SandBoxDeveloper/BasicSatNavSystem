package com.hulldiscover.zeus.basicsatnavsystem;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;

/**
 * Created by Zeus on 14/06/16.
 */
public class DijkstraShortestPathTest {

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

    // Path Finder
    PathFinder pathFinder;

    // Path Search
    BreadthFirstPaths findAllPaths;

    DijkstraShortestPath shortestPath;

    /**
     * Set up test environment
     * based on given specification.
     * */
    @Before
    public void setUp() {
        // Init Graph
        directedGraph = new Graph(GRAPH);

        // Init PathFinder
        pathFinder = new PathFinder(directedGraph, "A");

        // Init search
        findAllPaths = new BreadthFirstPaths(directedGraph);

        // Init search for shortest path
        shortestPath = new DijkstraShortestPath();
    }



    // There exist a route for A-E-D
    // Expected output FALSE
    @Test
    public void routeExistTestCase() {
        // Prepare
        List<String> routePath = new ArrayList<String>();

        // Route A-E-D
        routePath.add(A);
        routePath.add(E);
        routePath.add(D);

        String expected = "false";
        boolean outcome = findAllPaths.pathExist(routePath);

        assertFalse(expected, outcome);

        //shortestPath.computePath(directedGraph, A); // run Dijkstra
        //System.out.println("Distance to " + Z + ": " + Z.minDistance);
        List<String> path = shortestPath.getShortestPathTo(A);
    }



}
