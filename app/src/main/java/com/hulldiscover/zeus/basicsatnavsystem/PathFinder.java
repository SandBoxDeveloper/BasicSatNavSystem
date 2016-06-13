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
    // previous = previous vertex on shortest path from source vertex to destination vertex
    // distance = length of shortest path from source vertex to destination vertex

    private final TreeMap<String, String> previous = new TreeMap<String, String>();
    private final TreeMap<String, Integer> distance = new TreeMap<String, Integer>();
    Graph.Edge[] edges;

    public PathFinder (Graph graph, String source) {
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
            for(Graph.Vertex s : graph.setOfVerticesAdjacentTo(v)) {
                if(!distance.containsValue(s.name)) {
                    queue.remove(s);
                    distance.put(s.name, 1 + distance.get(v));
                    previous.put(s.name, v);
                }
            }
        }

        edges = graph.edges;

    }

    public boolean hasPathTo(String v) {
        return distance.containsKey(v);
    }

    public int distanceTo(String v) {
        if(!hasPathTo(v)) return Integer.MAX_VALUE;

        //for (Graph.Edge edge : edges) {


        //}

        return distance.get(v);
    }

    // The National Institute of Standards and Technology (NIST)
    // online Dictionary of Algorithms and Data Structures lists
    // this problem as "all simple paths" and recommends a depth-first search.

    public void findPath(Graph graph, String s, String d) {
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

        Set<Graph.Vertex> vertexSet = graph.setOfVerticesAdjacentTo(s);
        path.addLast(s);
        for(Graph.Vertex v : vertexSet) {
            if(v.equals(d)) {
                vertexOnPath.add(path.element());
                System.out.println(path);
            }
            else if (!path.contains(v)) {
                //findPath(graph, v.name, d);
            }
        }
        path.removeLast();


    }


}
