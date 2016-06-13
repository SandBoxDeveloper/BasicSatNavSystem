package com.hulldiscover.zeus.basicsatnavsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setup and init directed graph
        Graph directedGraph = new Graph(GRAPH);
        int verticesSize = directedGraph.numberOfVertices();
        int edgesSize = directedGraph.numberOfEdges();
        System.out.println("Number of vertices: " +verticesSize);
        System.out.println("Number of edges: " +edgesSize);


        directedGraph.printVerticesAdjacentTo("A");
        directedGraph.printVerticesAdjacentTo("B");
        directedGraph.printVerticesAdjacentTo("E");

        boolean is = directedGraph.isAdjacentTo("A", "B");
        System.out.println(is);

        int dis = directedGraph.getDistance("A", "D");
        int dis2 = directedGraph.getDistance2("A", "B", "C");
        System.out.println(dis2);


        /*for (String v : directedGraph.vertices()) {
            System.out.print(v + ": ");
            for (Graph.Vertex w : directedGraph.setOfVerticesAdjacentTo(v)) {
                System.out.print(w.distance + " ");
            }
            System.out.println();
        }*/



    }

    /*
     * TEST DATA
     * Method to setup graph
     * with its associated edges.
     */
    private static final Graph.Edge[] GRAPH = {
            new Graph.Edge("A", "B", 5),
            new Graph.Edge("B", "C", 4),
            new Graph.Edge("C", "D", 7),
            new Graph.Edge("D", "C", 8),
            new Graph.Edge("D", "E", 6),
            new Graph.Edge("A", "D", 5),
            new Graph.Edge("C", "E", 2),
            new Graph.Edge("E", "B", 3),
            new Graph.Edge("A", "E", 7),
    };

}
