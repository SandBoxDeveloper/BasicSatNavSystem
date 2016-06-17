package com.hulldiscover.zeus.basicsatnavsystem;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;

/**
 * Created by Zeus on 13/06/16.
 */
public class PathFinderTest {

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
    }


    // Find all paths from A - E
    // Expected size 5
    @Test
    public void testFindAllPathsFirstCase() throws Exception {
        // Prepare
        int expected = 5;

        // Find all possible paths
        List<List<String>> pathList = findAllPaths.getAllPaths(A,E);

        // Print paths
        for(List<String> pathNames : pathList) {
            System.out.println(pathNames);
        }

        // Assert
        Assert.assertEquals(expected, pathList.size());
    }

    // Find all paths from A - D
    // Expected size 3
    @Test
    public void testFindAllPathsSecondCase() throws Exception {
        // Prepare
        int expected = 3;

        // Find all possible paths
        List<List<String>> pathList = findAllPaths.getAllPaths(A,D);

        // Print paths
        for(List<String> pathNames : pathList) {
            System.out.println(pathNames);
        }

        // Assert
        Assert.assertEquals(expected, pathList.size());
    }

    // Find all paths from A - C
    // Expected size 3
    @Test
    public void testFindAllPathsThirdCase() throws Exception {

        // Prepare
        int expected = 4;

        // Find all possible paths
        List<List<String>> pathList = findAllPaths.getAllPaths(A,C);

        // Print paths
        for(List<String> pathNames : pathList) {
            System.out.println(pathNames);
        }

        // Assert
        Assert.assertEquals(expected, pathList.size());

    }

    // Find all paths from C - C (Cyclic path)
    // Expected size 9
    @Test
    public void findAllPaths() throws Exception {
        // Prepare
        int expected = 9;

        // Find all possible paths
        List<List<String>> pathList = findAllPaths.getAllPaths(C,C);

        // Print paths
        for(List<String> pathNames : pathList) {
            System.out.println(pathNames);
        }

        // Assert
        Assert.assertEquals(expected, pathList.size());
    }

    @Test
    public void testIsCyclicPath() throws Exception {
        // Prepare
        boolean expected = true;

        Graph.Vertex vertex = new Graph.Vertex(A);

        // Compute if path cyclic
        boolean outcome = directedGraph.isCyclicDirected(vertex);

        // Assert
        Assert.assertEquals(expected, outcome);
    }



}
