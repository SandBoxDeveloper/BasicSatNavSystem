# BasicSatNavSystem

Android (Java implementation) application of a simple route navigation system to guide a driver around a network of one way streets in a small town. 
After starting the application on your phone, you can input a start and destination point then click the buttons to find routes. 

The app can:
- Get the distance of a route 
- Find the shortest routes between two points 
- Find all possible routes between two points

NOTE: THE APPLICATION ONLY ACCEPTS CHARACTERS - 'A' 'B' 'C' 'D' 'E' AS INPUTS.
 
# Video 

<a href="https://youtu.be/B_ayAbobFAs" target="_blank"><img src="https://github.com/SandBoxDeveloper/BasicSatNavSystem/blob/master/Screen%20Shot%202016-06-20%20at%2010.33.00.png?raw=true" 
alt="Screen Shot" width="640" height="380" border="10" /></a>

# How to Test

JUnit tests are located under src/test/java.

Example test:

 ```
 // Distance for route A-B-C
 // Expected output 9
 @Test
    public void testRouteDistanceCalculatorFirstCase() {
        // Prepare
        RoutePathLength routePathLengthCalculation = new RoutePathLength();
        List<String> routePath = new ArrayList<String>();
        // Route A-B-C
        routePath.add(A);
        routePath.add(B);
        routePath.add(C);

        String expected = "9";

        // Calculate route path distance
        String routeLength = routePathLengthCalculation.getRouteLength(directedDirectedGraph, routePath);

        // Assert
        Assert.assertEquals(expected, routeLength);
    }
  ```
  
  See the test suite com.hulldiscover.zeus.basicsatnavsystem.UnitTestSuite to run all test at once.
  
> Test cases that pass:
  
 1. Distance for route A-B-C. Expected output 9
 2. Distance for route A-D. Expected output 5
 3. Distance for route A-D-C. Expected output 13
 4. Distance for route A-E-B-C-D. Expected output 21
 5. Distance for route A-E-D. Expected output NO SUCH ROUTE
 
 8. The length of the shortest route (in terms of distance to travel) from A
 to C. Expected output 9

  
# Pre-requisites

* Android SDK v23
* Android Build Tools v23.0.3
* Android Support Repository

# Getting Started

This sample uses the Gradle build system. To build this project, use the "gradlew build" command or use "Import Project" in Android Studio.
  
# References

* https://www.youtube.com/user/tusharroy2525
