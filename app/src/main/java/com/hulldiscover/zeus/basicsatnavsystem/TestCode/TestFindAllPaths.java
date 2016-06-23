package com.hulldiscover.zeus.basicsatnavsystem.TestCode;

import com.hulldiscover.zeus.basicsatnavsystem.Production.DirectedGraph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by Zeus on 13/06/16.
 *
 * This is an attempt to implement depth-first search.
 *
 * It is an experimental class not used for production
 * and will be removed in the future during code-clean up.
 *
 * ========================================================
 *
 * Class to
 * find all paths
 * in graph.
 *
 * Using DFS
 * The depth-first search will find all non-cyclical
 * paths between two nodes. This algorithm should be very
 * fast and scale to large graphs.
 */
public class TestFindAllPaths<Vertex> {
    // Stack Access = O(n)
    private Stack<String> path = new Stack<String>(); // current path
    private Set<String> vertexOnPath = new HashSet<String>(); // set of vertices on path

    public TestFindAllPaths(DirectedGraph directedGraph, String source, String t) {
        // Call to enumerate method
        enumerate(directedGraph, source, t);
    }

    private void enumerate(DirectedGraph directedGraph, String vertex, String t) {
        // add vertex to
        // current path from source
        path.add(vertex);
        vertexOnPath.add(vertex);


        // Found path from source to t -
        // currently prints in reverse
        // order because of stack
        if(vertex.equals(t)) {
            System.out.println(path);
        }
        else {
            // Consider all neighbors
            // that would continue path
            // with repeating a node
            for(DirectedGraph.Vertex vertexName : directedGraph.setOfVerticesAdjacentTo(vertex)) {
                if(!vertexOnPath.contains(vertexName)) { // if vertex not on path
                    // continue search
                    //enumerate(directedGraph, vertex, t);
                }
            }
        }

        // Once search for path
        // is done exploring from vertex
        // remove vertex from path

        path.pop(); // return the element on the top of the stack, removing it in the process
        vertexOnPath.remove(vertex); // remove vertex from path

    }

}
