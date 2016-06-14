package com.hulldiscover.zeus.basicsatnavsystem;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Zeus on 13/06/16.
 */
public class PathFinderTest {
    // Test Data
    private static final Graph.Edge[] GRAPH = {
            new Graph.Edge("A", "B", 5),
            new Graph.Edge("B", "C", 4),
            new Graph.Edge("C", "D", 7),
            new Graph.Edge("D", "C", 8),
            new Graph.Edge("D", "E", 6),
            new Graph.Edge("A", "D", 5),
            new Graph.Edge("C", "E", 2),
            new Graph.Edge("E", "B", 3),
            new Graph.Edge("A", "E", 7),
    };

    // Init Graph
    Graph directedGraph = new Graph(GRAPH);
    // Init PathFinder
    PathFinder pathFinder = new PathFinder(directedGraph, "A");

    BreadthFirstPaths findAllPaths = new BreadthFirstPaths(directedGraph);



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
