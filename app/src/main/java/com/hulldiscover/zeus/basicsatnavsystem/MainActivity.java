package com.hulldiscover.zeus.basicsatnavsystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

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
        //int dis2 = directedGraph.getDistance2("A", "B", "C");
        System.out.println(dis);

        // Init PathFinder
        PathFinder pathFinder = new PathFinder(directedGraph, "A");
        boolean hasPath = pathFinder.hasPathTo("C");
        int distanceTo = pathFinder.distanceTo("A", "D");
        System.out.println("HasPath?: " +hasPath);
        System.out.println("Distance to: " +distanceTo);

        BreadthFirstPaths findAllPaths = new BreadthFirstPaths(directedGraph);
        List<List<String>> paths = new ArrayList<List<String>>();





        /*for (String v : directedGraph.vertices()) {
            System.out.print(v + ": ");
            for (Graph.Vertex w : directedGraph.setOfVerticesAdjacentTo(v)) {
                System.out.print(w.distance + " ");
            }
            System.out.println();
        }*/



        // ABC - expected 9
        List<String> path1 = new ArrayList<String>();
        path1.add("A"); path1.add("B"); path1.add("C");

        // AD - expected 5
        List<String> path2 = new ArrayList<String>();
        path2.add("A"); path2.add("D");

        // ADC - expected 13
        List<String> path3 = new ArrayList<String>();
        path3.add("A"); path3.add("D"); path3.add("C");

        // AEBCD - expected 21
        List<String> path4 = new ArrayList<String>();
        path4.add("A"); path4.add("E"); path4.add("B"); path4.add("C"); path4.add("D");

        paths.add(path1);
        paths.add(path2);
        paths.add(path3);
        paths.add(path4);

        RoutePathLength routePathLengthCalculation = new RoutePathLength();
        List<String> routePath = new ArrayList<String>();


        // act
        int routeLength = routePathLengthCalculation.getRouteLength(directedGraph, path1);
        System.out.println("Path1" + routeLength);

        List<List<String>> pathList = findAllPaths.getAllPaths("A","D");
        TreeMap<String, Integer> routeDistances = findAllPaths.routeDistances();
        TreeMap<String, List<String>> routeDis = findAllPaths.routeDis();
        for(List<String> pathNames : pathList) {
            System.out.println(pathNames);
        }
        for (String key : routeDistances.keySet()) {
            System.out.println(key + " output is " + routeDistances.get(key));
        }
        for (String key : routeDis.keySet()) {
            System.out.println(key + " output is " + routeDis.get(key));
        }


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
