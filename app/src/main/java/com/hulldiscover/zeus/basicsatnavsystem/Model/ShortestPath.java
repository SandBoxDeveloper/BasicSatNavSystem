package com.hulldiscover.zeus.basicsatnavsystem.Model;

import com.hulldiscover.zeus.basicsatnavsystem.Graph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Zeus on 18/06/16.
 */
public class ShortestPath {
    LinkedList<Graph.Vertex> shortestPath;
    public Map<Graph.Vertex, Integer> shortestPath(Graph graph, String source) {

        Graph.Vertex sourceVertex = graph.getV(source);

        Graph.Vertex b = new Graph.Vertex("B");
        Graph.Vertex empty = new Graph.Vertex("NULL");

        // Heap + map data structure
        BinaryMinHeap<Graph.Vertex> minHeap = new BinaryMinHeap<>();

        // Stores shortest distance from source to every vertex
        Map<Graph.Vertex, Integer> distance = new HashMap<>();

        // Stores parent of every vertex in shortest distance
        Map<Graph.Vertex, Graph.Vertex> parent = new HashMap<>();

        // Init all vertex with infinite distance from source vertex
        // Because we initially don't know the distance between them
        for (Graph.Vertex vertex : graph.verticesList()) {
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
            BinaryMinHeap<Graph.Vertex>.Node heapNode = minHeap.extractMinNode();
            Graph.Vertex current = heapNode.key;

            // Update shortest distance of current vertex from source vertex
            distance.put(current, heapNode.weight);

            //Graph.Edge[] edges = graph.edges();
            //Iterator<Graph.Vertex> iterator = graph.adjacentTo(current.name).iterator();

            //while (iterator.hasNext()) {
                //Graph.Vertex adjacent = iterator.next();

            //}
            // Iterate through all edges of current vertex
            for (Graph.Edge edge : current.getEdges()) {

                // Get the adjacent vertex
                Graph.Vertex adjacent = getVertexForEdge(graph, current, edge);

                // If heap does not contain adjacent vertex means adjacent vertex
                // already has shortest distance from source vertex
                if (!minHeap.containsData(adjacent)) {
                    continue;
                }

                // Add distance of current vertex to edge weight to get distance of adjacent
                // vertex from source

                int newDistance = distance.get(current) + edge.getDistance();
                //int newDistance = graph.getDistance(current.name, adjacent.name);

                // See if the above calculated distance is less than current
                // distance stored for adjacent vertex from source vertex
                if (minHeap.getWeight(adjacent) > newDistance) {
                    minHeap.decrease(adjacent, newDistance);
                    parent.put(adjacent, current);
                }

                graph.getV(adjacent.name);
            }
        }


        //String name = parent.get(destination).name;// will give value then continue until reach sourcevertex
        //shortestPath(graph, destination, sourceVertex, parent, new LinkedHashSet<Graph.Vertex>());
        Graph.Vertex destination = new Graph.Vertex("C");
        discover(destination, sourceVertex, parent, new LinkedHashSet<Graph.Vertex>());
        return distance;
    }

    private Graph.Vertex getVertexForEdge(Graph g, Graph.Vertex v, Graph.Edge e){
        Graph.Vertex v1 = g.getV(e.vertex1);
        Graph.Vertex v2 = g.getV(e.vertex2);
        return v1.equals(v) ? v2 : v1;
    }



    public void find(Graph.Vertex current, Graph.Vertex destination, Map<Graph.Vertex, Graph.Vertex> parent, LinkedHashSet<Graph.Vertex> path) {
        path.add(current);

        if(current == destination) {
            path.add(current);
            return;
        }

        for (Map.Entry<Graph.Vertex, Graph.Vertex> entry : parent.entrySet())
        {
            Graph.Vertex e = entry.getKey();
            Graph.Vertex v = entry.getValue();
            Graph.Vertex start = parent.get(current);

            if(!path.contains(start)) {
                path.add(start);
                find(start, destination, parent, path);
            }

            //System.out.println(entry.getKey() + "/" + entry.getValue());
        }

        /*for(int i = 0; i < parent.size(); i++) {
            Graph.Vertex v = parent.get(i);
            if(!path.contains(v)) {
                path.add(v);
                find(v, destination, parent, path);
            }
        }*/

        //path.remove(current);
    }

    // Get to C from A
    // Current = C
    // Source = A
    public void discover(Graph.Vertex current, Graph.Vertex source, Map<Graph.Vertex, Graph.Vertex> parent, LinkedHashSet<Graph.Vertex> path) {
        path.add(current);

        if(current == source) { // C == A
            path.add(current);
            // remove last node which will be null
            return;
        }

        /*for (Map.Entry<Graph.Vertex, Graph.Vertex> entry : parent.entrySet())
        {
            Graph.Vertex e = entry.getKey();
            Graph.Vertex v = entry.getValue();
            System.out.println("Key: " +e.name + ", " + "Value: " +v.name);

            //Graph.Vertex start = parent.get(current);
            //System.out.println("Entry " +start);

        }
        //Graph.Vertex b = new Graph.Vertex("B");
        System.out.println("Current " +parent.get(current).name);
        System.out.println("Size " +parent.size());*/

        // Current  = C
        // Source/Destination = A
        // Look in parent
        // Go to Key C from parent
        // Get value of Key C = B
        // Use value to look in parent again
        // Get value of Key B from parent = A
        // Get value of Key A from parent = null
        // stop when value = null

        //final Set<Map.Entry<Graph.Vertex, Graph.Vertex>> edges = parent.entrySet();

        //for (Map.Entry<Graph.Vertex, Graph.Vertex> v : edges)
        //{
            Graph.Vertex start = parent.get(current); // Get C, then B, then A -> to get null


            if(!path.contains(start)) { // B not in path, A not in path, null not in path
                path.add(start); // Add B to path, Add A to path, Add null to path
                discover(start, source, parent, path); // Update start to = B, and start method again
                                                        // Update start = A, and start again
                                                        // Update start = null, and start again
            }



        //}


        //Iterator<Graph.Vertex> iterator = path.iterator();
        System.out.println("Path");
        LinkedList<Graph.Vertex> list = new LinkedList<>(path);
        Iterator<Graph.Vertex> itr = list.descendingIterator();
        while(itr.hasNext()) {
            Graph.Vertex v = itr.next();
            // do something
            shortestPath = new LinkedList<>(path);
            shortestPath.add(v);
            System.out.println(v.name + "");
        }



    }

    public List<Graph.Vertex> getPath() {
        LinkedList<Graph.Vertex> shortest = new LinkedList<>();
        LinkedList<Graph.Vertex> list = new LinkedList<>(shortestPath);
        Iterator<Graph.Vertex> itr = list.descendingIterator();
        while(itr.hasNext()) {
            Graph.Vertex v = itr.next();
            // do something

            shortest.add(v);
        }
        return shortest;
    }


}
