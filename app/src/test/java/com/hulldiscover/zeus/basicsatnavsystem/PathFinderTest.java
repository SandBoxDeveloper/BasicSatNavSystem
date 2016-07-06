package com.hulldiscover.zeus.basicsatnavsystem;

import com.hulldiscover.zeus.basicsatnavsystem.Production.CycleInDirectedGraph;
import com.hulldiscover.zeus.basicsatnavsystem.Production.BreadthFirstFindAllPaths;
import com.hulldiscover.zeus.basicsatnavsystem.Production.DirectedGraph;
import com.hulldiscover.zeus.basicsatnavsystem.TestCode.TestPathFinder;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
    DirectedGraph directedGraph;

    // Path Finder
    TestPathFinder testPathFinder;

    // Path Search
    BreadthFirstFindAllPaths findAllPaths;

    // Cycle in Graph
    CycleInDirectedGraph cycleInDirectedGraph;

    /**
     * Set up test environment
     * based on given specification.
     * */
    @Before
    public void setUp() {
        // Init DirectedGraph
        directedGraph = new DirectedGraph(GRAPH);

        // Init TestPathFinder
        testPathFinder = new TestPathFinder(directedGraph, "A");

        // Init search
        findAllPaths = new BreadthFirstFindAllPaths(directedGraph);

        // Init cycle detection
        cycleInDirectedGraph = new CycleInDirectedGraph();
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
    /*@Test
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
    }*/

    @Test
    public void testIsCyclicPath() throws Exception {
        // Prepare
        boolean expected = true;

        DirectedGraph.Vertex vertex = new DirectedGraph.Vertex(A);

        // Compute if path cyclic
        boolean outcome = directedGraph.isCyclicDirected(vertex);

        // Assert
        Assert.assertEquals(expected, outcome);
    }

    @Test
    public void hasCycle() {
        // Prepare
        boolean expected = true;
        boolean outcome;

        // Determine if graph has cycle
        outcome = cycleInDirectedGraph.hasCycle(directedGraph);

        // Set of cycles in graph
        Set<Set<DirectedGraph.Vertex>> cycles = cycleInDirectedGraph.returnGreySet();



        for(Set<DirectedGraph.Vertex> set : cycles) {
            // create an iterator
            Iterator iterator = set.iterator();
            //DirectedGraph.Vertex v = iterator.next();

            for(DirectedGraph.Vertex cycle : set) {
                System.out.println("Value: "+cycle.name + " ");
            }
            // check values
            /*while (iterator.hasNext()){
                System.out.println("Value: "+iterator.next() + " ");
            }*/
        }




        // Assert
        Assert.assertEquals(expected, outcome);
    }



}
