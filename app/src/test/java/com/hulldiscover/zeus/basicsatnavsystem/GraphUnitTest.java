package com.hulldiscover.zeus.basicsatnavsystem;

import org.junit.Test;

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
}
