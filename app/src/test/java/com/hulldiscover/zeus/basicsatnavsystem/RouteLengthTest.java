package com.hulldiscover.zeus.basicsatnavsystem;

import com.hulldiscover.zeus.basicsatnavsystem.Production.RoutePathLength;
import com.hulldiscover.zeus.basicsatnavsystem.Production.BreadthFirstFindAllPaths;
import com.hulldiscover.zeus.basicsatnavsystem.Production.DirectedGraph;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zeus on 14/06/16.
 */
public class RouteLengthTest {

    // Vertex (node) points
    public static final String A = "A";
    public static final String B = "B";
    public static final String C = "C";
    public static final String D = "D";
    public static final String E = "E";

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

    /**
     * Set up test environment
     * based on given specification.
     *
     * Test Data:
     * AB5, BC4, CD7, DC8, DE6, AD5, CE2, EB3, AE7
     * */
    @Before
    public void setUp() {
        // Init DirectedGraph
        directedDirectedGraph = new DirectedGraph(GRAPH);

        // Init search
        findAllPaths = new BreadthFirstFindAllPaths(directedDirectedGraph);
    }

     /* 1. Distance for route A-B-C. Expected output 9
            2. Distance for route A-D. Expected output 5
            3. Distance for route A-D-C. Expected output 13
            4. Distance for route A-E-B-C-D. Expected output 21
            5. Distance for route A-E-D. Expected output NO SUCH ROUTE
    6. The number of routes starting at C and ending at C with a maximum of 3
    junctions. In the sample data below, there are two such routes: C-D-C (2
                                                                           junctions) and C-E-B-C (3 junctions). Expected output 2*/

    // Distance for route A-B-C
    // Expected output 9
    @Test
    public void testRouteDistanceCalculatorFirstCase() {
        // Prepare
        RoutePathLength routePathLengthCalculation = new RoutePathLength();
        List<String> routePath = new ArrayList<String>();
        // Route A-B-C
        routePath.add(A);
        routePath.add(B);
        routePath.add(C);

        String expected = "9";

        // Calculate route path distance
        String routeLength = routePathLengthCalculation.getRouteLength(directedDirectedGraph, routePath);

        // Assert
        Assert.assertEquals(expected, routeLength);
    }

    // Distance for route A-D
    // Expected output 5
    @Test
    public void testRouteDistanceCalculatorSecondCase() {
        // Prepare
        RoutePathLength routePathLengthCalculation = new RoutePathLength();
        List<String> routePath = new ArrayList<String>();
        // Route A-D
        routePath.add(A);
        routePath.add(D);

        String expected = "5";

        // Calculate route path distance
        String routeLength = routePathLengthCalculation.getRouteLength(directedDirectedGraph, routePath);

        // Assert
        Assert.assertEquals(expected, routeLength);
    }

    // Distance for route A-D-C
    // Expected output 13
    @Test
    public void testRouteDistanceCalculatorThirdCase() {
        // Prepare
        RoutePathLength routePathLengthCalculation = new RoutePathLength();
        List<String> routePath = new ArrayList<String>();
        // Route A-D-C
        routePath.add(A);
        routePath.add(D);
        routePath.add(C);

        String expected = "13";

        // Calculate route path distance
        String routeLength = routePathLengthCalculation.getRouteLength(directedDirectedGraph, routePath);

        // Assert
        Assert.assertEquals(expected, routeLength);
    }

    // Distance for route A-E-B-C-D
    // Expected output 21
    @Test
    public void testRouteDistanceCalculatorFourthCase() {
        // Prepare
        RoutePathLength routePathLengthCalculation = new RoutePathLength();
        List<String> routePath = new ArrayList<String>();
        // Route A-E-B-C-D
        routePath.add(A);
        routePath.add(E);
        routePath.add(B);
        routePath.add(C);
        routePath.add(D);

        String expected = "21";

        // Calculate route path distance
        String routeLength = routePathLengthCalculation.getRouteLength(directedDirectedGraph, routePath);

        // Assert
        Assert.assertEquals(expected, routeLength);
    }

    // Distance for route A-E-D
    // Expected output NO SUCH ROUTE
    @Test
    public void testRouteDistanceCalculatorFifthCase() {
        // Prepare
        RoutePathLength routePathLengthCalculation = new RoutePathLength();
        List<String> routePath = new ArrayList<String>();
        // Route A-E-D
        routePath.add(A);
        routePath.add(E);
        routePath.add(D);

        String expected = "NO SUCH ROUTE";

        // Calculate route path distance
        String routeLength = routePathLengthCalculation.getRouteLength(directedDirectedGraph, routePath);

        // Assert
        Assert.assertEquals(expected, routeLength);
    }

    // Distance for route A-C
    // Expected output 9
    @Test
    public void testRouteDistanceCalculatorSixthCase() {
        // Prepare
        RoutePathLength routePathLengthCalculation = new RoutePathLength();
        List<String> routePath = new ArrayList<String>();
        // Route A-C
        routePath.add(A);
        routePath.add(B);
        routePath.add(C);

        String expected = "9";

        // Calculate route path distance
        String routeLength = routePathLengthCalculation.getRouteLength(directedDirectedGraph, routePath);

        // Assert
        Assert.assertEquals(expected, routeLength);
    }

    // Distance for route B-B
    // Expected output 9
    /*@Test
    public void testRouteDistanceCalculatorSeventhCase() {
        // Prepare
        RoutePathLength routePathLengthCalculation = new RoutePathLength();
        List<String> routePath = new ArrayList<String>();
        // Route B-B
        routePath.add(B);
        routePath.add(B);

        String expected = "9";

        // Calculate route path distance
        String routeLength = routePathLengthCalculation.getRouteLength(directedDirectedGraph, routePath);

        // Assert
        Assert.assertEquals(expected, routeLength);
    }*/







}
