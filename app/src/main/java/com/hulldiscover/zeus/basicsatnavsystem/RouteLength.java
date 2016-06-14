package com.hulldiscover.zeus.basicsatnavsystem;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Zeus on 14/06/16.
 */
public class RouteLength {
    // private final TreeMap<String, Integer> routeDistances = new TreeMap<String, Integer>();
    //routeDistances.put("Route " + routeCount, dis);
   /* public TreeMap<String, Integer> routeDistances () {
        return routeDistances;
    }*/

    public RouteLength(Graph directedGraph, HashMap<String, Graph.Vertex> allRoadJunctions, List<String> path) {

        int total = 0;
        BreadthFirstPaths findAllPaths = new BreadthFirstPaths(directedGraph);

        //directedGraph.getDistance(s, d);


        /*for(int i = 0; i < path.size() - 1; i++) {
            String src = path.get(i);
            Graph.Vertex src_rj = allRoadJunctions.get(src);

            String target = path.get(i + 1);

            Graph.Edge roadToTarget = src_rj.get(target);
            List<List<String>> pathToDestination = findAllPaths.getAllPaths("C","C");

            if(pathToDestination.size() == 0) {
                noSuchRoute ();
            } else {
                directedGraph.
                RoadJunction tgt = pathToDestination.get(0)..getDestiny();
                total += roadToTarget.getDistanceToTarget();
                src_rj = tgt;
            }
        }*/

        //return total + "";

    }

    private String noSuchRoute () {
        return "NO SUCH ROUTE";
    }
}
