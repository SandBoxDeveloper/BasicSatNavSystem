package com.hulldiscover.zeus.basicsatnavsystem;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Zeus on 12/06/16.
 */
public class DirectedGraphUnitTest {

    private static final DirectedGraph.Edge[] GRAPH = {
            new DirectedGraph.Edge("A", "B", 5),
            new DirectedGraph.Edge("B", "C", 4),
            new DirectedGraph.Edge("C", "D", 7),
            new DirectedGraph.Edge("D", "C", 8),
            new DirectedGraph.Edge("D", "E", 6),
            new DirectedGraph.Edge("A", "D", 5),
            new DirectedGraph.Edge("C", "E", 2),
            new DirectedGraph.Edge("E", "B", 3),
            new DirectedGraph.Edge("A", "E", 7),
    };

    DirectedGraph directedDirectedGraph = new DirectedGraph(GRAPH);

    /*
     * Is the directed graph setup successful?
     */
    @Test
    public void setupgraph_isCorrect() throws Exception {
        assertNotNull(directedDirectedGraph);
    }

    /*
     * Number of vertices in graph
     * Expected count is 6
     */
    @Test
    public void numberOfVertices() throws Exception {
        int verticesCount = directedDirectedGraph.numberOfVertices();
        assertEquals(5, verticesCount);
    }

    /*
     * Number of Edges in graph
     * Expected count is 9
     */
    @Test
    public void numberOfEdges() throws Exception {
        int edgesCount = directedDirectedGraph.numberOfEdges();
        assertEquals(9, edgesCount);
    }

    /*
     * Does the graph return it's
     * associated vertices ?
     *
     * It should not be null.
     */
    @Test
    public void returnVertices() throws Exception {
        assertNotNull(directedDirectedGraph.vertices());
    }

    /*
     * Check adjacent vertex.
     * Is vertex A adjacent to
     * vertex B ?
     *
     * It should return true.
     */
    @Test
    public void isAdjacentTo() throws Exception {
        assertTrue(directedDirectedGraph.isAdjacentTo("A", "B"));
    }

    @Test
    public void getDistanceBetweenPaths() throws Exception {
        assertEquals(5, directedDirectedGraph.getDistance("A", "D"));
    }




}
