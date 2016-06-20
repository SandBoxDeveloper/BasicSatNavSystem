package com.hulldiscover.zeus.basicsatnavsystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

/**
 * Created by Zeus on 14/06/16.
 */
public class DijkstraShortestPath {
    // predecessor = predecessor vertex on shortest path from source vertex to destination vertex
    // distance = length of shortest path from source vertex to destination vertex

    private final TreeMap<String, String> previous = new TreeMap<String, String>();
    private final TreeMap<String, Integer> distance = new TreeMap<String, Integer>();
    DirectedGraph.Edge[] edges;

    public void computePath(DirectedGraph grap, DirectedGraph.Vertex source) {
        //source.minimumDistance = 0;
        // Put source of vertex on queue
        Queue<DirectedGraph.Vertex> queue = new PriorityQueue<DirectedGraph.Vertex>();
        // Enqueuing
        queue.add(source);

        // Repeated remove next vertex v from queue and insert
        // all its neighbors, provided they haven't yet been visited
        while(!queue.isEmpty()) {
            // Dequeuing
            DirectedGraph.Vertex vertex = queue.remove();

            // Visit each edge (path) exiting vertex
            for(DirectedGraph.Vertex neighbours : grap.adjacentTo(vertex.name)) {
                if (!distance.containsValue(neighbours)) {
                    queue.add(neighbours);
                    distance.put(neighbours.name, 1 + distance.get(vertex));
                    previous.put(neighbours.name, vertex.name);
                }

            }
        }
    }

    public List<String> getShortestPathTo(String target)
    {
        List<String> path = new ArrayList<String>();
        for (String vertex = target; vertex != null; vertex = vertex)
            path.add(vertex);

        Collections.reverse(path);
        return path;
    }

}
