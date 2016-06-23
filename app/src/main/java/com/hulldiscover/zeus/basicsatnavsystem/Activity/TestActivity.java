package com.hulldiscover.zeus.basicsatnavsystem.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hulldiscover.zeus.basicsatnavsystem.Production.BreadthFirstFindAllPaths;
import com.hulldiscover.zeus.basicsatnavsystem.Production.RoutePathLength;
import com.hulldiscover.zeus.basicsatnavsystem.Production.DirectedGraph;
import com.hulldiscover.zeus.basicsatnavsystem.Production.DijkstraFindShortestPath;
import com.hulldiscover.zeus.basicsatnavsystem.TestCode.TestPathFinder;
import com.hulldiscover.zeus.basicsatnavsystem.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Note: The code implemented below is used only
 *       to test the functionality of the app.
 *
 *       Method and classes used here, have been
 *       used to test functionality only.
 *
 *       The actual application runs off
 *       ActivityNavigate.java
 *
 *       Refer to that, to see how the application
 *       runs.
 *
 */
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Test Code Implement Below
         */

        // setup and init directed graph
        DirectedGraph directedDirectedGraph = new DirectedGraph(GRAPH);

        // get the number of vertices in graph
        int verticesSize = directedDirectedGraph.numberOfVertices();

        // get the number of edges in graph
        int edgesSize = directedDirectedGraph.numberOfEdges();

        // print count of vertices and edges
        System.out.println("Number of vertices: " +verticesSize);
        System.out.println("Number of edges: " +edgesSize);

        // setup and init shortest path class
        DijkstraFindShortestPath dsp = new DijkstraFindShortestPath();

        // crate a new vertex called "A" - FOR TESTING
        DirectedGraph.Vertex sourceVertex = directedDirectedGraph.getV("A");

        // print new vertex A - name
        System.out.println("Vertex: " +sourceVertex.name);
        // print new vertex A - distance
        System.out.println("Vrtex Distance: " +sourceVertex.distance);
        // print new vertex A - neighbours
        System.out.println("Vertex neighbours : " +sourceVertex.neighbours.keySet());

        // find the shortest path between vertex A and vertex D
        Map<DirectedGraph.Vertex,Integer> distance = dsp.shortestPath(directedDirectedGraph, "A", "D");
        // get the Map key set
        Set keys = distance.keySet();
        // iterate through ket-set to grab the distance value
        for (Iterator i = keys.iterator(); i.hasNext();)
        {
            DirectedGraph.Vertex key = (DirectedGraph.Vertex) i.next();
            int value =  distance.get(key); // distance value
            System.out.println("Distance: " +key.name+ " : " +value);
        }
        //System.out.print("Distance: " +distance.);*/

        // create new vertex A and vertex D - FOR TESTING
        DirectedGraph.Vertex a = new DirectedGraph.Vertex("A");
        DirectedGraph.Vertex d = new DirectedGraph.Vertex("D");
        directedDirectedGraph.pathString(a,d);

        // using dijkstra find the shortest distance to vertex C
        directedDirectedGraph.dijkstra("C");


        //directedDirectedGraph.printPath("C");
        //directedDirectedGraph.printAllPaths();

        /*DirectedGraph.Vertex v = new DirectedGraph.Vertex("A");
        boolean isCycle = directedDirectedGraph.isCyclicDirected(v);
        boolean hasSelfLoop = directedDirectedGraph.hasSelfLoop("C");
        System.out.println("Self Loop " +hasSelfLoop);
        System.out.println("Cycle" +isCycle);

        TestDirectedCycle finder = new TestDirectedCycle(directedDirectedGraph);
        if (finder.hasCycle()) {
            System.out.println("Directed cycle: ");
            for (String vo : finder.cycle()) {
                System.out.println(vo + " ");
            }
            System.out.println();
        }

        else {
            System.out.println("No directed cycle");
        }*/
        System.out.println();


        // print adjacent vertices
        directedDirectedGraph.printVerticesAdjacentTo("A");
        directedDirectedGraph.printVerticesAdjacentTo("B");
        directedDirectedGraph.printVerticesAdjacentTo("E");

        // find out is vertices are adjacent to each other
        boolean is = directedDirectedGraph.isAdjacentTo("A", "B");
        // print result
        System.out.println(is);

        // get the distance between two vertices
        int dis = directedDirectedGraph.getDistance("A", "D");
        // print distance
        System.out.println(dis);

        // print path to vertex C
        directedDirectedGraph.printPath("C");

        // setup and init TestPathFinder
        TestPathFinder testPathFinder = new TestPathFinder(directedDirectedGraph, "A");
        // find if there exist a path to vertex C
        boolean hasPath = testPathFinder.hasPathTo("C");
        // find the distance between vertex A and vertex D
        int distanceTo = testPathFinder.distanceTo("A", "D");
        // print results
        System.out.println("HasPath?: " +hasPath);
        System.out.println("Distance to: " +distanceTo);

        // using breadthfirst algorithm, find all paths in directed graph
        // based on inputs inserted later on in program
        BreadthFirstFindAllPaths findAllPaths = new BreadthFirstFindAllPaths(directedDirectedGraph);
        List<List<String>> paths = new ArrayList<List<String>>();


        // TEST PATHS
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

        // calculate the distance of routes
        RoutePathLength routePathLengthCalculation = new RoutePathLength();
        List<String> routePath = new ArrayList<String>();


        // get the distance of path 1
        String routeLength = routePathLengthCalculation.getRouteLength(directedDirectedGraph, path1);
        // print result
        System.out.println("Path1" + routeLength);

        // get all paths between vertex A and vertex D
        List<List<String>> pathList = findAllPaths.getAllPaths("A","D");
        TreeMap<String, Integer> routeDistances = findAllPaths.routeDistances();
        TreeMap<String, List<String>> routeDis = findAllPaths.routeDis();
        // print results
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

    /**
     * TEST DATA
     * Method to setup graph
     * with its associated edges.
     */
    private static final DirectedGraph.Edge[] GRAPH = {
            new DirectedGraph.Edge("A", "B", 5),
            new DirectedGraph.Edge("B", "C", 4),
            new DirectedGraph.Edge("C", "D", 7),
            new DirectedGraph.Edge("D", "C", 8),
            new DirectedGraph.Edge("D", "E", 6),
            new DirectedGraph.Edge("A", "D", 5),
            new DirectedGraph.Edge("C", "E", 2),
            new DirectedGraph.Edge("E", "B", 3),
            new DirectedGraph.Edge("A", "E", 7),
    };

}
