package com.hulldiscover.zeus.basicsatnavsystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Created by Zeus on 13/06/16.
 */
public class BreadthFirstPaths {

    private final TreeMap<String, Integer> distance = new TreeMap<String, Integer>();
    private final TreeMap<String, Integer> routeDistances = new TreeMap<String, Integer>();
    private final TreeMap<String, List<String>> routeDis = new TreeMap<String, List<String>>();
    private Stack<String> cycle;


    private final DirectedGraph directedGraph;

    /**
     * Takes in a directedGraph. This directedGraph should not be changed by the client
     */
    public BreadthFirstPaths(DirectedGraph directedGraph) {
        if (directedGraph == null) {
            throw new NullPointerException("The input directedGraph cannot be null.");
        }
        this.directedGraph = directedGraph;
    }

    private void validate(String source, String destination) {

        if (source == null) {
            throw new NullPointerException("The source: " + source + " cannot be  null.");
        }
        if (destination == null) {
            throw new NullPointerException("The destination: " + destination + " cannot be  null.");
        }
        //if (source.equals(destination)) {
            //throw new IllegalArgumentException("The source and destination: " + source + " cannot be the same.");
        //}
    }

    /**
     * Returns the list of paths, where path itself is a list of nodes.
     *
     * @param source      the source node
     * @param destination the destination node
     * @return List of all paths
     */
    public List<List<String>> getAllPaths(String source, String destination) {
        // Validate source vertex
        // and destination vertex
        validate(source, destination);

        // Set source vertex
        // default distance to 0
        distance.put(source, 0);

        List<List<String>> paths = new ArrayList<List<String>>();
        recursive(source, destination, paths, new LinkedHashSet<String>());

        // Calculate distances between
        // all paths found
        // between source and destination
        distanceTo(paths);

        return paths;
    }

    // this ignore's cycles.
    private void recursive(String current, String destination, List<List<String>> paths, LinkedHashSet<String> path) {
        path.add(current);

        //TODO: Change current.equals(destination) to check if node has been visited before (2 times limit for now, but should allow more)
        if (current.equals(destination)) {
            paths.add(new ArrayList<String>(path)); // add array on one found path for destination
            path.remove(current); // remove last node from path list
            return;
        }

        final Set<DirectedGraph.Vertex> edges = directedGraph.setOfVerticesAdjacentTo(current);

        for (DirectedGraph.Vertex v : edges) {
            if (!path.contains(v.name)) { // not in path, start recursion again and add to path
                v.compareTo(v);
                recursive(v.name, destination, paths, path);
            }
        }

        path.remove(current);


    }

    private final TreeMap<String, String> previous = new TreeMap<String, String>();

    public void computePath(DirectedGraph directedGraph, DirectedGraph.Vertex source) {
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
            for(DirectedGraph.Vertex neighbours : directedGraph.adjacentTo(vertex.name)) {
                if (!distance.containsValue(neighbours)) {
                    queue.add(neighbours);
                    distance.put(neighbours.name, 1 + distance.get(vertex));
                    previous.put(neighbours.name, vertex.name);
                }

            }
        }
    }



    private void distanceTo(List<List<String>> paths) {
        int distance = 0;
        int routeCount = 1;
        String startVertex = "";
        String endVertex = "";

        for (List<String> v : paths) {
            ListIterator<String> iterator = v.listIterator();

            while (iterator.hasNext()) {

                String a = iterator.next();
                startVertex = a; // source vertex
                String b = iterator.next();
                if(!iterator.hasNext()) {
                    endVertex = b; // destination vertex
                }
                System.out.println("Vertex 1: " +a);
                System.out.println("Vertex 2: " +b);

                distance = directedGraph.getDistance(a, b);
                System.out.println("Distance between route " +a+ " -> " +b+ " : " +distance);

                while (iterator.hasPrevious() == true && iterator.hasNext() == true) {
                    String previousVertex = iterator.previous();
                    String punctuation = iterator.next();
                    String nextVertex = iterator.next();
                    endVertex = nextVertex; // destination vertex
                    distance += directedGraph.getDistance(previousVertex, nextVertex);

                }

                routeDistances.put("Route " + routeCount, distance);
                routeDis.put("Route " + routeCount, v);
                routeCount++;
            }
            //System.out.println();
            //System.out.println("Total Route Distance: " +distance);
        }
        System.out.println("Total Route Distance: " +distance);

    }

    public boolean hasPathTo(String v) {
        return distance.containsKey(v);
    }

    public TreeMap<String, Integer> routeDistances () {
        return routeDistances;
    }

    public TreeMap<String, List<String>> routeDis () {
        return routeDis;
    }

    public boolean pathExist(List<String> path) {
        /**
         * 1) From list find the start (source) and end (destination)
         * 2) Find all possible routes
         * 3) Check if input path is one of the possible routes found in step 2.
         */
        // First element in list
        String source = path.get(0);
        String destination = null;
        if (!path.isEmpty()) {
            // Last element in list
            destination = path.get(path.size() - 1);
        }
        // Find all possible paths
        getAllPaths(source, destination);

        return routeDis.containsValue(path);
    }

    public List<String> shortestPath(String startVertex, String endVertex) {

        // run bfs on the directedGraph
        runBFS(startVertex);

        List<String> path = new ArrayList<>();
        // trace path back from end vertex to start
        DirectedGraph.Vertex end = directedGraph.getV(endVertex);
        while (end != null && end != directedGraph.getV(startVertex)) {
            path.add(end.name);
            //end = end.parent();
        }
        // if end is null, node not found
        if (end == null) {
            return null;
        }
        else {
            Collections.reverse(path);
        }
        return path;
    }

    private void runBFS(String startVertex) {

        // init the queue
        Queue<DirectedGraph.Vertex> queue = new LinkedList<>();
        DirectedGraph.Vertex start = directedGraph.getV(startVertex);
        queue.add(start);

        // explore the directedGraph
        /*while (!queue.isEmpty()) {
            DirectedGraph.Vertex first = queue.remove();
            first.setVisited(true);
            List<DirectedGraph.Vertex> edges = directedGraph.adjacentTo(first.name);
            for(String e : edges) {

                DirectedGraph.Vertex neighbour = e;
                if (!neighbour.isVisited()) {
                    neighbour.setParent(first);
                    queue.add(neighbour);
                }

            }
        }*/
    }


}
