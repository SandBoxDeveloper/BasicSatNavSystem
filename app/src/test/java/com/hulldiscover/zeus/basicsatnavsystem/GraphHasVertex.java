package com.hulldiscover.zeus.basicsatnavsystem;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Zeus on 12/06/16.
 */
public class GraphHasVertex {
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

    Graph directedGraph = new Graph(GRAPH);

    /*
     * Does the graph have this
     * associated vertex ?
     *
     * Test should return true
     * for all vertex values.
     */

    // Vertex A
    @Test
    public void grapHasVertexA() throws Exception {
        assertTrue(directedGraph.graphHasVertex("A"));
    }

    // Vertex B
    @Test
    public void grapHasVertexB() throws Exception {
        assertTrue(directedGraph.graphHasVertex("B"));
    }

    // Vertex C
    @Test
    public void grapHasVertexC() throws Exception {
        assertTrue(directedGraph.graphHasVertex("C"));
    }

    // Vertex D
    @Test
    public void grapHasVertexD() throws Exception {
        assertTrue(directedGraph.graphHasVertex("D"));
    }

    // Vertex E
    @Test
    public void grapHasVertexE() throws Exception {
        assertTrue(directedGraph.graphHasVertex("E"));
    }

    // Vertex F - Doesn't exist
    // Test should return false
    @Test
    public void grapHasVertexF() throws Exception {
        assertFalse(directedGraph.graphHasVertex("F"));
    }

    //TODO: Test ValidateVertex Method that is set as private

}
