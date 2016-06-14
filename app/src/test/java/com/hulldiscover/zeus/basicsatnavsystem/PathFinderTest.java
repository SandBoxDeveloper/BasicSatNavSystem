package com.hulldiscover.zeus.basicsatnavsystem;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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





    /*
     * Does vertex B have path to C ?
     * Expected result should return true
     */
    @Test
    public void hasPathTo() throws Exception {
        assertTrue(findAllPaths.hasPathTo("C"));
    }

    /*
     * Distance of shortest path
     * Expected result should return 9
     */
    @Test
    public void distanceToAToD() throws Exception {
        assertEquals(5, pathFinder.distanceTo("A", "D"));
    }

    @Test
    public void distanceToAToC() throws Exception {
        assertEquals(9, pathFinder.distanceTo("A", "C"));
    }

    /*
     * Find all paths from A - E
     * Expected result
     */
    @Test
    public void findAllPathsAToE() throws Exception {
        List<List<String>> pathList = findAllPaths.getAllPaths("A","E");
        for(List<String> pathNames : pathList) {
            System.out.println(pathNames);
        }
    }

    // Find all routes A - D
    @Test
    public void findAllPathsAToD() throws Exception {
        //assertNotNull(findAllPaths());
        List<List<String>> pathList = findAllPaths.getAllPaths("A","D");
        for(List<String> pathNames : pathList) {
            System.out.println(pathNames);
        }
    }

    // Find all routes A - C
    @Test
    public void findAllPathsAToC() throws Exception {
        //assertNotNull(findAllPaths());
        List<List<String>> pathList = findAllPaths.getAllPaths("A","C");
        for(List<String> pathNames : pathList) {
            System.out.println(pathNames);
        }
    }

    @Test
    public void findAllPaths() throws Exception {
        //assertNotNull(findAllPaths());
        List<List<String>> pathList = findAllPaths.getAllPaths("C","C");
        for(List<String> pathNames : pathList) {
            System.out.println(pathNames);
        }
    }



}
