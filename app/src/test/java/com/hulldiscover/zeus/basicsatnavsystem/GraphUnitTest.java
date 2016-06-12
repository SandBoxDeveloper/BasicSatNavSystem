package com.hulldiscover.zeus.basicsatnavsystem;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Zeus on 12/06/16.
 */
public class GraphUnitTest {

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

    @Test
    public void setupgraph_isCorrect() throws Exception {
        Graph directedGraph = new Graph(GRAPH);
        assertNotNull(directedGraph);
    }


    /*Distance for route A-B-C. Expected output 9
            2. Distance for route A-D. Expected output 5
            3. Distance for route A-D-C. Expected output 13
            4. Distance for route A-E-B-C-D. Expected output 21*/

    @Test
    public void distanceFromABC() throws Exception {
        Graph directedGraph = new Graph(GRAPH);
        assertEquals(5, directedGraph.getDistance("A", "D"));
    }
}
