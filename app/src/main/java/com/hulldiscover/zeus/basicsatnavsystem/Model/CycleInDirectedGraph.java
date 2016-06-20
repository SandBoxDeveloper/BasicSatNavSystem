package com.hulldiscover.zeus.basicsatnavsystem.Model;

import com.hulldiscover.zeus.basicsatnavsystem.DirectedGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Zeus on 19/06/16.
 * Using DFS, whilst maintaining three sets.
 *
 * White set has all the vertices that have not been visited at all
 * Grey set has all the vertices that are currently being visited
 * Black set has all the vertices that have totally been visited (all its children have also been visited)
 *
 * If you encounter a vertex in a grey set, it means you have encountered a cycle.
 * Why? Because when a vertex is in a grey set, it means in neighbours, or verticies in that hiaracy
 * is still being explored and that vertex found a path back to a vertex in the grey set. Hence indicating
 * the presence of a cycle.
 */
public class CycleInDirectedGraph {

    Set<Set<DirectedGraph.Vertex>> cycleInGraph = new HashSet<>();
    Map<DirectedGraph.Vertex, DirectedGraph.Vertex> introducedBy = new HashMap<DirectedGraph.Vertex, DirectedGraph.Vertex>();
    List<DirectedGraph.Vertex> previous = new ArrayList<>();

    /**
     * Main function to find cycle in graph
     *
     * @param graph
     * @return true cycle is found.<pp>false has not been found.</pp>
     */
    public boolean hasCycle(DirectedGraph graph) {
        // Init sets
        Set<DirectedGraph.Vertex> whiteSet = new HashSet<>();
        Set<DirectedGraph.Vertex> greySet = new HashSet<>();
        Set<DirectedGraph.Vertex> blackSet = new HashSet<>();
        previous.add(null);

        // Add all vertices to white-set
        for(DirectedGraph.Vertex vertex : graph.verticesList()) {
            whiteSet.add(vertex);
        }

        // Take any random vertex from the white-set
        while(whiteSet.size() > 0) {
            DirectedGraph.Vertex current = whiteSet.iterator().next();
            DirectedGraph.Vertex prev = previous.get(previous.size() - 1);
            // Do Depth First Search
            if(depthFirstSearch(current, prev, whiteSet, greySet, blackSet)) {
                return true; // Cycle Found
            }
        }
        return false; // No Cycle Found
    }

    /**
     * Depth-First-Search function to explore
     * Vertices in graph.
     *
     * @param current  vertex
     * @param whiteSet set contains all un-visited vertices
     * @param greySet  set contains currently visited vertices
     * @param blackSet set contains vertices that have been visited,
     *                 with its neighbours as well
     */
    private boolean depthFirstSearch(DirectedGraph.Vertex current, DirectedGraph.Vertex pre, Set<DirectedGraph.Vertex> whiteSet, Set<DirectedGraph.Vertex> greySet, Set<DirectedGraph.Vertex> blackSet) {
        // Move current from white-set to grey-set
        // Then explore current
        moveVertexFrom(current, whiteSet, greySet);

        // Get neighbours of current
        for(DirectedGraph.Vertex neighbour : current.getAdjacentVertexes()){

            introducedBy.put(current, pre);
            previous.add(current);

            // If found in black-set
            // It is already been explored
            if(blackSet.contains(neighbour)){
                continue; // continue process
            }

            // If found in grey-set
            // Then cycle has been found
            if(greySet.contains(neighbour)) {
                return true; // exit process
            }

            // Continuing process
            if(depthFirstSearch(neighbour, pre, whiteSet, greySet, blackSet)) {
                return true;
            }
        }

        // When current has no more neighbours
        // Move 'current' vertex from grey-set to black-set
        moveVertexFrom(current, greySet, blackSet);
        //cycleInGraph.add(introducedBy);
        return false; // No Cycle Found
    }

    /**
     * Move vertex from one set to defined set
     *
     * @param current vertex to move
     * @param sourceSet set that contains vertex
     * @param destinationSet set to move vertex to
     */
    private void moveVertexFrom (DirectedGraph.Vertex current,
                                 Set<DirectedGraph.Vertex> sourceSet,
                                 Set<DirectedGraph.Vertex> destinationSet) {

        sourceSet.remove(current);
        destinationSet.add(current);
    }

    public Set<Set<DirectedGraph.Vertex>> returnGreySet () {
        return cycleInGraph;
    }
}
