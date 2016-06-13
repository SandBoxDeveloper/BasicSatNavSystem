package com.hulldiscover.zeus.basicsatnavsystem;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Zeus on 12/06/16.
 */
public class Graph {
    // graph
    private final Map<String, Vertex> graph; // mapping of vertex names to Vertex objects, built from a set of Edges
    // number of edges
    private int edgesCount;
    Edge[] edges;

    /**
     * In a directed graph,
     * an edge is an ordered pair of nodes.
     * These are the lines that connect to
     * edges.
     *
     * This class represents one edge
     * in a graph.
     *
     * The class is used to construct a graph.
     */
    public static class Edge {
        // Member variables
        public final String vertex1;
        public final String vertex2;
        public final int distance;


        /* Constructor
         * Creates an edge in a graph
         *
         * @param vertex1 the first vertex in graph
         * @param vertex2 the first vertex in graph
         * @param distance the distance between vertex1
         *        and vertex 2 in graph.
         */
        public Edge(String vertex1, String vertex2, int distance) {
            // Init member variables
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.distance = distance;
        }






    }

    /**
     * In a directed graph,
     * is represented by a circle with a label.
     *
     * This class represents one vertex
     * in a graph.
     *
     * The class is used to construct a graph.
     */
    public static class Vertex implements Comparable<Vertex> {
        // Member variables
        public final String name;
        public int distance = Integer.MAX_VALUE; // MAX_VALUE assumed to be infinity
        public Vertex previous = null;
        public final Map<Vertex, Integer> neighbours = new HashMap<>();

        /* Constructor
         * Creates an vertex in a graph
         *
         * @param name the name of the vertex
         */
        public Vertex(String name) {
            // Init name member variable
            this.name = name;
        }

        /*
         * Print path of vertex
         * @param null
         * @return void
         */
        private void printPath() {
            if (this == this.previous) {
                System.out.printf("%s", this.name);
            } else if (this.previous == null) {
                System.out.printf("%s(unreached)", this.name);
            } else {
                this.previous.printPath();
                System.out.printf(" -> %s(%d)", this.name, this.distance);
            }
        }

        /* Method compares the distances
         * between two vertices.
         *
         * @param other the vertex to compare with
         * @return the distance between two vertices
         * @see int
         */
        public int compareTo(Vertex other) {
            return Integer.compare(distance, other.distance);
        }
    }

    /**
     * This class constructs a graph
     * based off a set of edges.
     *
     * @param edges an array of edges
     */
    public Graph(Edge[] edges) {
        // Init graph to length of edges array
        graph = new HashMap<>(edges.length);
        edgesCount = edges.length;
        this.edges = edges;

        //one pass to find all vertices
        /* Conditional statement with
         * ternary operator support
         * looping through all edges in the graph
         * to find all vertices.
         */
        for (Edge e : edges) {
            // if graph (HashMap) doesn't contain vertex1
            if (!graph.containsKey(e.vertex1)) {
                graph.put(e.vertex1, new Vertex(e.vertex1));
            }
            // if graph (HashMap) doesn't contain vertex2
            if (!graph.containsKey(e.vertex2)) {
                graph.put(e.vertex2, new Vertex(e.vertex2));
            }
        }

        //another pass to set neighbouring vertices
        /* Conditional statement with
         * ternary operator support
         */
        for (Edge e : edges) {
            // neighbours of vertices
            graph.get(e.vertex1).neighbours.put(graph.get(e.vertex2), e.distance);
            //graph.get(e.v2).neighbours.put(graph.get(e.v1), e.dist); // also do this for an undirected graph
        }
    }

    /**
     * Method throw an exception if
     * parameter is not a vertex
     * in graph.
     *
     * @param vertex the vertex
     */
    private void validateVertex(String vertex) {
        if (!graphHasVertex(vertex)) {
            throw new IllegalArgumentException(vertex + " is not a vertex");
        }
    }

    /**
     * Method returns the
     * vertices in graph.
     *
     * @return the set of vertices in this graph
     */
    public Iterable<String> vertices () {
        return graph.keySet();
    }

    /**
     * Returns the iterator that travels the vertexs of a graph.
     *
     * @return an iterator that travels the vertexs of a graph.
     */
    public Iterator<String> iterator() {
        return graph.keySet().iterator();
    }

    /**
     * In graph theory, an adjacent vertex
     * of a vertex v in a graph is a vertex
     * that is connected to v by an edge.
     *
     * This method prints
     * the neighbour to a vertex
     *
     * @param  vertex the vertex
     */
    public void printVerticesAdjacentTo(String vertex) {
        // first validate this input is
        // an vertex in graph
        validateVertex(vertex);

        // Set of keys from HashMap
        // that contain the neighbours to
        // @param vertex
        Set<Vertex> vertexSet = graph.get(vertex).neighbours.keySet();

        // Loop through set
        // and print values
        for (Vertex vertex1 : vertexSet) {
            System.out.println(vertex + " is Adjacent to: " + vertex1.name);
        }
    }

    /**
     * Returns the set of vertices adjacent to vertex in graph.
     *
     * @param  vertex the vertex
     * @return the set of vertices adjacent to vertex <tt>v</tt> in this graph
     * @throws IllegalArgumentException if <tt>v</tt> is not a vertex in this graph
     */
    public Set<Vertex> setOfVerticesAdjacentTo(String vertex) {
        validateVertex(vertex);
        return graph.get(vertex).neighbours.keySet();
    }

    /**
     * Method check if vertex is adjacent
     * to another vertex.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return true of false whether vertex is adjacent to
     */
    public boolean isAdjacentTo(String vertex1, String vertex2) {
        boolean isAdjacent = false;
        // Set that contains
        // keys of @param vertex1
        // neighbours
        Set<Vertex> vertexSet = graph.get(vertex1).neighbours.keySet();

        // Check vertex has neighbour
        // of @param vertex2
        for(Vertex vertex : vertexSet) {
            if(vertex.name.equals(vertex2)) {
                isAdjacent = true;
                return isAdjacent;
            }
        }

        return isAdjacent;
    }

    /**
     *  Method returns true if
     * input @param String v is
     * a vertex in graph.
     *
     * @param  vertex the vertex
     * @return <tt>true</tt> if <tt>vertex</tt> is a vertex in graph,
     *         <tt>false</tt> otherwise
     */
    public boolean graphHasVertex(String vertex) {
        return graph.containsKey(vertex);
    }

    /**
     *  Method returns the
     * number of vertices graph.
     *
     * @return the number of vertices in graph
     * @see int
     */
    public int numberOfVertices() {
        return graph.size();
    }

    /**
     *  Method returns the
     * number of edges graph.
     *
     * @return the number of edges in graph
     * @see int
     */
    public int numberOfEdges() {
        return edgesCount;
    }

    /** Prints a path from the source to the specified vertex */
    public void printPath(String endName) {
        if (!graph.containsKey(endName)) {
            System.err.printf("Graph doesn't contain end vertex \"%s\"\n", endName);
            return;
        }

        graph.get(endName).printPath();
        System.out.println();
    }

    public void printAllPaths() {
        for (Vertex v : graph.values()) {
            v.printPath();
            System.out.println();
        }
    }

    public int getDistance(String startName, String endName) {
        int distance = 0;
        /*if(graph.containsKey(startName)) {
             distance = graph.get(startName).distance;
        } else {
            System.err.printf("Graph doesn't contain vertex");
        }*/

        for (Edge edge : edges) {
            if (edge.vertex1.equals(startName)
                    && edge.vertex2.equals(endName)) {
                distance = edge.distance;
            }
            if (edge.vertex1.equals(startName)
                    && edge.vertex2.equals(endName)) {
                distance = edge.distance;
            }
        }
        return distance;
    }

    public int getDistance2(String startName, String middleName, String endName) {
        int distance = 0;
        for (Edge edge : edges) {
            if (edge.vertex1.equals(startName)
                    && edge.vertex2.equals(middleName)
                    && edge.vertex1.equals(endName)) {
                distance = edge.distance;
            }
            if (edge.vertex1.equals(startName)
                    && edge.vertex2.equals(middleName)
                    && edge.vertex1.equals(endName)) {
                distance = edge.distance;
            }
            //throw new RuntimeException("Should not happen");
        }
        return distance;
    }

    //TODO: Class called PathTO using BreadthFirstSearch Algorithim
    // PathFinder(Graph G, String s) constructor
    // int distanceTo(String v) length of shortest path of s to v in G
    // Interable<String> pathTo(String v) shortest path from s to v in G

}
