package com.hulldiscover.zeus.basicsatnavsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Zeus on 15/06/16.
 */
public class DirectedCycle {
    private boolean[] marked;        // marked[v] = has vertex v been marked?
    private int[] edgeTo;            // edgeTo[v] = predecessor vertex on path to v
    private boolean[] onStack;       // onStack[v] = is vertex on the stack?
    private Stack<String> cycle;    // directed cycle (or null if no such cycle)

    /**
     * Determines whether the digraph <tt>G</tt> has a directed cycle and, if so,
     * finds such a cycle.
     * @param graph the digraph
     */
    public DirectedCycle(Graph graph) {
        marked  = new boolean[graph.numberOfVertices()];
        onStack = new boolean[graph.numberOfVertices()];
        edgeTo  = new int[graph.numberOfVertices()];

        Iterable<String> vertices = graph.vertices();
        List<String> listV = new ArrayList<String>(graph.numberOfVertices());

        for(String ve : vertices) {
            listV.add(ve);
        }

        for (int v = 0; v < graph.numberOfVertices(); v++) {
            String vertex = listV.get(v);
            if (!marked[v] && cycle == null) dfs(graph, vertex);
        }
    }



    // check that algorithm computes either the topological order or finds a directed cycle
    private void dfs(Graph graph, String vertex) {

        // List of vertices
        Iterable<String> vertices = graph.vertices();
        List<String> listV = new ArrayList<String>(graph.numberOfVertices());
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
        List<String> adjacentList = graph.listOfVerticesAdjacentTo(vertex);

        for (int w = 0; w < graph.numberOfVertices(); w++) {

            // short circuit if directed cycle found
            if (cycle != null) return;

                //found new vertex, so recur
            else if (!marked[w]) {
                edgeTo[w] = v; // index of vertex found
                String ve = listV.get(w);
                //w = listV.indexOf(ve);
                dfs(graph, ve);
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
