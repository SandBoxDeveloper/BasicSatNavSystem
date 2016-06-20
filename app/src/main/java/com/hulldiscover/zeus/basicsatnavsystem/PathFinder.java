package com.hulldiscover.zeus.basicsatnavsystem;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by Zeus on 13/06/16.
 */
public class PathFinder {
    // predecessor = predecessor vertex on shortest path from source vertex to destination vertex
    // distance = length of shortest path from source vertex to destination vertex

    private final TreeMap<String, String> previous = new TreeMap<String, String>();
    private final TreeMap<String, Integer> distance = new TreeMap<String, Integer>();
    DirectedGraph.Edge[] edges;

    public PathFinder (DirectedGraph directedGraph, String source) {
        // Put source of vertex on queue
        Queue<String> queue = new PriorityQueue<String>();
        // Enqueuing
        queue.add(source);
        distance.put(source, 0);

        // Repeated remove next vertex v from queue and insert
        // all its neighbors, provided they haven't yet been visited
        while(!queue.isEmpty()) {
            // Dequeuing
            String v = queue.remove();
            // Loop through vertex neighbours
            for(DirectedGraph.Vertex s : directedGraph.setOfVerticesAdjacentTo(v)) {
                if(!distance.containsValue(s.name)) {
                    queue.remove(s);
                    distance.put(s.name, 1 + distance.get(v));
                    previous.put(s.name, v);
                }
            }
        }

        edges = directedGraph.edges;

    }

    public boolean hasPathTo(String v) {
        return distance.containsKey(v);
    }

    public int distanceTo(String v, String d) {
        if(!hasPathTo(v)) return Integer.MAX_VALUE;

        int dis = 0;
        for (DirectedGraph.Edge edge : edges) {
            if (edge.vertex1.equals(v)
                    && edge.vertex2.equals(d)) {
                dis = edge.distance;
            }
            if (edge.vertex1.equals(v)
                    && edge.vertex2.equals(d)) {
                dis = edge.distance;
            }
        }

        return dis;
        //return distance.get(v);
    }

    // The National Institute of Standards and Technology (NIST)
    // online Dictionary of Algorithms and Data Structures lists
    // this problem as "all simple paths" and recommends a depth-first search.

    public void findPath(DirectedGraph directedGraph, String s, String d) {
       LinkedList<String> path = new LinkedList<String>(); // current path
        LinkedList<String> vertexOnPath = new LinkedList<String>(); // set of vertices on path
        /*Add [c] to tail end of list [p]
        6     For each Vertex [v] adjacent to [c]
        7         If [v] is equal to [d] then
        8             Save list [p] in [x]
        9         Else If [v] is not in list [p]
        10             PathFind([v])
        11     Next For
        12     Remove tail from [p]*/

        Set<DirectedGraph.Vertex> vertexSet = directedGraph.setOfVerticesAdjacentTo(s);
        path.addLast(s);
        for(DirectedGraph.Vertex v : vertexSet) {
            if(v.equals(d)) {
                vertexOnPath.add(path.element());
                System.out.println(path);
            }
            else if (!path.contains(v)) {
                //findPath(directedGraph, v.name, d);
            }
        }
        path.removeLast();


    }


}
