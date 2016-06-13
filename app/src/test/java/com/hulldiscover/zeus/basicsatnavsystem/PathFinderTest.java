package com.hulldiscover.zeus.basicsatnavsystem;

import org.junit.Test;

import java.util.ArrayList;
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
    List<List<String>> paths = new ArrayList<List<String>>();




    /*
     * Does vertex B have path to C ?
     * Expected result should return true
     */
    @Test
    public void hasPathTo() throws Exception {
        assertTrue(pathFinder.hasPathTo("C"));
    }

    /*
     * Distance of shortest path
     * Expected result should return 9
     */
    @Test
    public void distanceTo() throws Exception {
        assertEquals(9, pathFinder.distanceTo("C"));
    }

    /*
     * Find all paths from A - E
     * Expected result
     */
    @Test
    public void findAllPaths() throws Exception {
        //assertNotNull(findAllPaths());
    }
}
