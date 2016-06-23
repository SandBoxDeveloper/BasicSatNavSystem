package com.hulldiscover.zeus.basicsatnavsystem;

import com.hulldiscover.zeus.basicsatnavsystem.Production.BreadthFirstFindAllPaths;
import com.hulldiscover.zeus.basicsatnavsystem.Production.DirectedGraph;
import com.hulldiscover.zeus.basicsatnavsystem.TestCode.TestDijkstraShortestPath;
import com.hulldiscover.zeus.basicsatnavsystem.TestCode.TestPathFinder;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;

/**
 * Created by Zeus on 14/06/16.
 */
public class DijkstraDijkstraFindShortestPathTest {

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

    // Path Finder
    TestPathFinder testPathFinder;

    // Path Search
    BreadthFirstFindAllPaths findAllPaths;

    TestDijkstraShortestPath shortestPath;

    /**
     * Set up test environment
     * based on given specification.
     * */
    @Before
    public void setUp() {
        // Init DirectedGraph
        directedDirectedGraph = new DirectedGraph(GRAPH);

        // Init TestPathFinder
        testPathFinder = new TestPathFinder(directedDirectedGraph, "A");

        // Init search
        findAllPaths = new BreadthFirstFindAllPaths(directedDirectedGraph);

        // Init search for shortest path
        shortestPath = new TestDijkstraShortestPath();
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
