package com.hulldiscover.zeus.basicsatnavsystem.TestCode;

import com.hulldiscover.zeus.basicsatnavsystem.Production.DirectedGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Zeus on 15/06/16.
 *
 * This is an attempt to implement detection of a cycle
 * in a directed graph.
 *
 * It is an experimental class not used for production
 * and will implemented into the final production code
 * in the future during code-clean up and refactoring.
 */
public class TestDirectedCycle {
    private boolean[] marked;        // marked[v] = has vertex v been marked?
    private int[] edgeTo;            // edgeTo[v] = predecessor vertex on path to v
    private boolean[] onStack;       // onStack[v] = is vertex on the stack?
    private Stack<String> cycle;    // directed cycle (or null if no such cycle)

    /**
     * Determines whether the digraph <tt>G</tt> has a directed cycle and, if so,
     * finds such a cycle.
     * @param directedGraph the digraph
     */
    public TestDirectedCycle(DirectedGraph directedGraph) {
        marked  = new boolean[directedGraph.numberOfVertices()];
        onStack = new boolean[directedGraph.numberOfVertices()];
        edgeTo  = new int[directedGraph.numberOfVertices()];

        Iterable<String> vertices = directedGraph.vertices();
        List<String> listV = new ArrayList<String>(directedGraph.numberOfVertices());

        for(String ve : vertices) {
            listV.add(ve);
        }

        for (int v = 0; v < directedGraph.numberOfVertices(); v++) {
            String vertex = listV.get(v);
            if (!marked[v] && cycle == null) depthFirstSearch(directedGraph, vertex);
        }
    }



    /**
     * Method to check that algorithm computes
     * either the topological order or finds a directed cycle.
     */
    private void depthFirstSearch(DirectedGraph directedGraph, String vertex) {

        // List of vertices
        Iterable<String> vertices = directedGraph.vertices();
        List<String> listV = new ArrayList<String>(directedGraph.numberOfVertices());
        int v = 0;

        for(String ve : vertices) {
            listV.add(ve);
            System.out.println(ve);
        }

        if (listV.contains(vertex))
        {
            v = listV.indexOf(vertex);
        }


        onStack[v] = true; // vertex positioned at v is now on stack
        marked[v] = true; // vertex positioned at v has now been marked

        // List of vertex adjacent
        List<String> adjacentList = directedGraph.listOfVerticesAdjacentTo(vertex);

        for (int w = 0; w < directedGraph.numberOfVertices(); w++) {

            // short circuit if directed cycle found
            if (cycle != null) return;

                //found new vertex, so recur
            else if (!marked[w]) {
                edgeTo[w] = v; // index of vertex found
                String ve = listV.get(w);
                //w = listV.indexOf(ve);
                depthFirstSearch(directedGraph, ve);
            }



            // trace back directed cycle
            else if (onStack[w]) {
                cycle = new Stack<String>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(listV.get(x));
                }
                cycle.push(listV.get(w));
                cycle.push(listV.get(v));
                //assert check();
            }
        }
        onStack[v] = false;
    }

    /**
     * Does the digraph have a directed cycle?
     * @return <tt>true</tt> if the digraph has a directed cycle, <tt>false</tt> otherwise
     */
    public boolean hasCycle() {
        return cycle != null;
    }

    /**
     * Returns a directed cycle if the digraph has a directed cycle, and <tt>null</tt> otherwise.
     * @return a directed cycle (as an iterable) if the digraph has a directed cycle,
     *    and <tt>null</tt> otherwise
     */
    public Iterable<String> cycle() {
        return cycle;
    }


}
