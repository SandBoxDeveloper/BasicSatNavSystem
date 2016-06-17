package com.hulldiscover.zeus.basicsatnavsystem.Calculator;

import com.hulldiscover.zeus.basicsatnavsystem.BreadthFirstPaths;
import com.hulldiscover.zeus.basicsatnavsystem.Graph;

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

    // Path Search
    BreadthFirstPaths findAllPaths;

    /**
     * This method gets the distance (length) of
     * a path.
     * @param directedGraph
     * @param path
     * @return the route distance
     */
    public String getRouteLength(Graph directedGraph, List<String> path) {

        // Sum of distance in path
        int distanceBetweenTwoVertexPoints = 0;

        /* Run check first before continuing
         *
         * 1) Check that path exist in graph
         * 2) If path exist, continue with route calculation.
         *    Else, return "NO SUCH ROUTE".
         */

        // Use FindPath class to check
        // Init search class
        findAllPaths = new BreadthFirstPaths(directedGraph);

        // 1) & 2)
        if(findAllPaths.pathExist(path) == true) { // find distance of path
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

            return tryParse(distanceBetweenTwoVertexPoints);
        }

        else { // no paths exist
            return "NO SUCH ROUTE"; // Print "NO SUCH ROUTE"
        }


    }

    /**
     * This method try to parse int input
     * to a string value.
     * If it cannot, it return null.
     * @param value
     * @return the string representation of int input OR
     *         null if if cannot convert int value.
     */
    public static String tryParse(int value) {
        try {
            return Integer.toString(value);
        } catch (Exception e) {
            return null;
        }
    }

}
