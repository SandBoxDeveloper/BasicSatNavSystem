package com.hulldiscover.zeus.basicsatnavsystem;

import java.util.List;
import java.util.ListIterator;

/**
 * Created by Zeus on 14/06/16.
 */

/* The class implements a method
 * to calculate the length of
 * a route in the graph.
 *
 * Method named getRouteLength does the calculation.
 */
public class RoutePathLength {

    public int getRouteLength(Graph directedGraph, List<String> path) {

        // Sum of distance in path
        int distanceBetweenTwoVertexPoints = 0;

        // Check if path is empty
        if (path.isEmpty()) { // if no paths exist

            noSuchRoute(); // Print "NO SUCH ROUTE"

        } else { // Else, find distance of path

            // Iterate through path
            ListIterator<String> iterator = path.listIterator();

            // Until there are no more vertex's
            // in path
            while (iterator.hasNext()) {
                // Get first and second vertex
                String source = iterator.next();
                String destination = iterator.next();

                /* Calculate the distance of path
                 *
                 *  1) Get the distance between two vertex points (source & destination)
                 *  2) Add distance to path-distance variable
                 *  3) Check if there exist more vertex points,
                 *     and continue if there exist.
                 *     Else, return path-distance.
                 */

                // 1) and 2)
                distanceBetweenTwoVertexPoints = directedGraph.getDistance(source, destination);

                // 3)
                while (iterator.hasPrevious() && iterator.hasNext()) {
                    String previousVertex = iterator.previous();
                    String commaFoundInPath = iterator.next(); // This is needed due to the "," comma punctuation found in path list
                    String nextVertex = iterator.next();

                    // Add to existing distance sum
                    distanceBetweenTwoVertexPoints += directedGraph.getDistance(previousVertex, nextVertex);
                }
            }
        }
        return distanceBetweenTwoVertexPoints;
    }

    private String noSuchRoute () {
        return "NO SUCH ROUTE";
    }

}
