package com.hulldiscover.zeus.basicsatnavsystem;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

    Graph directedGraph = new Graph(GRAPH);

    /*
     * Is the directed graph setup successful?
     */
    @Test
    public void setupgraph_isCorrect() throws Exception {
        assertNotNull(directedGraph);
    }

    /*
     * Number of vertices in graph
     * Expected count is 6
     */
    @Test
    public void numberOfVertices() throws Exception {
        int verticesCount = directedGraph.numberOfVertices();
        assertEquals(5, verticesCount);
    }

    /*
     * Number of Edges in graph
     * Expected count is 9
     */
    @Test
    public void numberOfEdges() throws Exception {
        int edgesCount = directedGraph.numberOfEdges();
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
        assertNotNull(directedGraph.vertices());
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
        assertTrue(directedGraph.isAdjacentTo("A", "B"));
    }

    @Test
    public void getDistanceBetweenPaths() throws Exception {
        assertEquals(5, directedGraph.getDistance("A", "D"));
    }



    /*Distance for route A-B-C. Expected output 9
            2. Distance for route A-D. Expected output 5
            3. Distance for route A-D-C. Expected output 13
            4. Distance for route A-E-B-C-D. Expected output 21*/

    @Test
    public void distanceFromABC() throws Exception {
        Graph directedGraph = new Graph(GRAPH);
        assertEquals(9, directedGraph.getDistance2("A", "B", "C"));
    }
}
