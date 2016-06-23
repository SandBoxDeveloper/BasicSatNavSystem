package com.hulldiscover.zeus.basicsatnavsystem.Production;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Zeus on 18/06/16.
 *
 * Find single source shortest path using Dijkstra's algorithm
 *
 * Space complexity - O(E + V)
 * Time complexity - O(ElogV)
 *
 * References
 * https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
 *
 */
public class DijkstraFindShortestPath {
    LinkedList<DirectedGraph.Vertex> shortestPath; // Shortest path between two vertex

    /**
     * Main function to find shortest path
     * between two vertex.
     *
     * @param
     *          directedGraph the graph
     * @param
     *          from stating vertex
     * @param
     *          to destination vertex
     * @return
     *          map of how far vertex is from source
     */
    public Map<DirectedGraph.Vertex, Integer> shortestPath(DirectedGraph directedGraph, String from, String to) {

        DirectedGraph.Vertex sourceVertex = directedGraph.getV(from);
        DirectedGraph.Vertex destination = directedGraph.getV(to);

        DirectedGraph.Vertex empty = new DirectedGraph.Vertex("NULL");

        // Heap + map data structure
        BinaryMinHeap<DirectedGraph.Vertex> minHeap = new BinaryMinHeap<>();

        // Stores shortest distance from source to every vertex
        Map<DirectedGraph.Vertex, Integer> distance = new HashMap<>();

        // Stores parent of every vertex in shortest distance
        Map<DirectedGraph.Vertex, DirectedGraph.Vertex> parent = new HashMap<>();

        // Init all vertex with infinite distance from source vertex
        // Because we initially don't know the distance between them
        for (DirectedGraph.Vertex vertex : directedGraph.verticesList()) {
            minHeap.add(Integer.MAX_VALUE, vertex);
        }

        // Set distance of source vertex to 0
        minHeap.decrease(sourceVertex, 0);

        // Put it in map
        distance.put(sourceVertex, 0);

        // Source vertex parent is null
        parent.put(sourceVertex, empty);

        // Iterate until heap is empty
        while (!minHeap.empty()) {
            // Get the min value heap node which has vertex and distance of that vertex from source vertex.
            BinaryMinHeap<DirectedGraph.Vertex>.Node heapNode = minHeap.extractMinNode();
            DirectedGraph.Vertex current = heapNode.key;

            // Update shortest distance of current vertex from source vertex
            distance.put(current, heapNode.weight);

            // Iterate through all edges of current vertex
            for (DirectedGraph.Edge edge : current.getEdges()) {

                // Get the adjacent vertex
                DirectedGraph.Vertex adjacent = getVertexForEdge(directedGraph, current, edge);

                // If heap does not contain adjacent vertex means adjacent vertex
                // already has shortest distance from source vertex
                if (!minHeap.containsData(adjacent)) {
                    continue;
                }

                // Add distance of current vertex to edge weight to get distance of adjacent
                // vertex from source

                int newDistance = distance.get(current) + edge.getDistance();
                //int newDistance = directedGraph.getDistance(current.name, adjacent.name);

                // See if the above calculated distance is less than current
                // distance stored for adjacent vertex from source vertex
                if (minHeap.getWeight(adjacent) > newDistance) {
                    minHeap.decrease(adjacent, newDistance);
                    parent.put(adjacent, current);
                }

                directedGraph.getV(adjacent.name);
            }
        }

        //DirectedGraph.Vertex destination = new DirectedGraph.Vertex("C");
        discover(destination, sourceVertex, parent, new LinkedHashSet<DirectedGraph.Vertex>());
        return distance;
    }

    /**
     * Method retrieves vertex point from edge.
     *
     * @param g
     *          graph
     * @param v
     *          vertex
     * @param e
     *          edge
     * @return
     *          the vertex from an edge in graph
     */
    private DirectedGraph.Vertex getVertexForEdge(DirectedGraph g, DirectedGraph.Vertex v, DirectedGraph.Edge e){
        DirectedGraph.Vertex v1 = g.getV(e.vertex1);
        DirectedGraph.Vertex v2 = g.getV(e.vertex2);
        return v1.equals(v) ? v2 : v1;
    }


    /**
     * Function to find the shortest path.
     *
     * @param current
     *                  vertex
     * @param source
     *                  vertex as starting point
     * @param parent
     *                  map
     * @param path
     *                  linkedHashSet that contains
     *                  vertices in shortest path
     */
    public void discover(DirectedGraph.Vertex current, DirectedGraph.Vertex source, Map<DirectedGraph.Vertex, DirectedGraph.Vertex> parent, LinkedHashSet<DirectedGraph.Vertex> path) {
        path.add(current);

        // Exit statement
        if (current == source) { // C == A
            path.add(current);
            // Add shortest path
            // for later reference
            addShortestPath(path);
            return;
        }

        /* Example
         * Current  = C
         * Source/Destination = A
         * Look in parent
         * Go to Key C from parent
         * Get value of Key C = B
         * Use value to look in parent again
         * Get value of Key B from parent = A
         * Get value of Key A from parent = null
         * stop when value = null
         */

        DirectedGraph.Vertex start = parent.get(current); // Get C, then B, then A -> to get null


        if (!path.contains(start)) { // B not in path, A not in path, null not in path
            path.add(start); // Add B to path, Add A to path, Add null to path
            discover(start, source, parent, path); // Update start to = B, and start method again
            // Update start = A, and start again
            // Update start = null, and start again
        }


    }

    /**
     * Function to add to a list
     * what has been found as
     * the shorted path.
     *
     * @param path the of the shortest route
     *             between two vertex
     */
    public void addShortestPath(LinkedHashSet<DirectedGraph.Vertex> path) {
        LinkedList<DirectedGraph.Vertex> list = new LinkedList<>(path);
        Iterator<DirectedGraph.Vertex> itr = list.descendingIterator();
        shortestPath = new LinkedList<>();
        // Iterate through shortest path
        // in descending order
        while (itr.hasNext()) {
            DirectedGraph.Vertex v = itr.next();
            shortestPath.add(v);
            System.out.println(v.name + "");
        }
    }

    /**
     * Function to return the shortest path found.
     *
     * @return list of the shortest path
     */
    public List<DirectedGraph.Vertex> getPath() {
        LinkedList<DirectedGraph.Vertex> shortest = new LinkedList<>();
        LinkedList<DirectedGraph.Vertex> list = new LinkedList<>(shortestPath);
        // Iterate through shortest path
        // in descending order
        Iterator<DirectedGraph.Vertex> itr = list.descendingIterator();
        while(itr.hasNext()) {
            DirectedGraph.Vertex v = itr.next();
            shortest.add(v);
        }
        return shortestPath;
    }


}
