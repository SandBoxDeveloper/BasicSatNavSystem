package com.hulldiscover.zeus.basicsatnavsystem.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.hulldiscover.zeus.basicsatnavsystem.Adapter.ListAdapter;
import com.hulldiscover.zeus.basicsatnavsystem.Adapter.ShortestPathListAdapter;
import com.hulldiscover.zeus.basicsatnavsystem.BreadthFirstPaths;
import com.hulldiscover.zeus.basicsatnavsystem.Graph;
import com.hulldiscover.zeus.basicsatnavsystem.Model.ShortestPath;
import com.hulldiscover.zeus.basicsatnavsystem.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Zeus on 04/04/16.
 */
public class Navigation extends AppCompatActivity implements Animation.AnimationListener{


    EditText start;
    EditText destination;
    Button searchRoutes;
    Button bestRoute;
    ImageButton reversePath;
    ImageButton transitMode;
    String startInput = "";
    String destinationInput = "";
    ListView listView;
    ListAdapter listAdapter;
    ShortestPathListAdapter shortestPathListAdapter;
    AnimationDrawable carAnimation;

    // Animation
    Animation animFadein;
    Animation animZoomin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setup and init directed graph
        final Graph directedGraph = new Graph(GRAPH);

        // Get a reference to the editText in the layout
        start = (EditText) findViewById(R.id.start);

        // Get the string array
        //String[] countries = getResources().getStringArray(R.array.destination_array);

        // Create the adapter and set it to the AutoCompleteTextView
        /*ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.activity_list_item, countries);
        start.setAdapter(adapter);*/

        // Get reference of editText in the layout
        destination = (EditText) findViewById(R.id.destination);
        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.route_listview);

        // Inputs from screen, used later
        startInput = start.getText().toString().toUpperCase();
        destinationInput = destination.getText().toString().toUpperCase();

        // When search button is clicked
        // Find all possible paths between start and destination
        searchRoutes = (Button) findViewById(R.id.search_routes_btn);
        searchRoutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validate inputs
                if (!validate()) {
                    return; // return is input invalid
                }

                // Init search
                BreadthFirstPaths findAllPaths = new BreadthFirstPaths(directedGraph);

                // Get inputs from screen
                String startInput = start.getText().toString().toUpperCase();
                String destinationInput = destination.getText().toString().toUpperCase();

                // Get list of paths between start and destination
                List<List<String>> pathList = findAllPaths.getAllPaths(startInput,destinationInput);


                // Display results
                // 1) Loop through all results from search
                // 2) Add them to a list
                // 3) Path list into adapter
                ArrayList<List<String>> paths = new ArrayList<List<String>>();

                // Add results to list
                for(List<String> pathNames : pathList) {
                    System.out.println(pathNames);
                    paths.add(pathNames);
                }

                // Create ArrayAdapter using the planet list
                listAdapter = new ListAdapter(Navigation.this, R.layout.listview_item, paths);

                // Assign adapter to ListView
                listView.setAdapter(listAdapter);

            }
        });



        // Have to control if adapter is null,
        // not the list view, as the adapter holds the values.
        // Tell the list view which view to display when the list is empty
        if(listAdapter == null) {
            listView.setEmptyView(findViewById(R.id.emptyList));
        }


        // When item from list is selected
        // Display small message
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                List<String> itemValue = (List<String>) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();
            }
        });



        // When reverse path button is clicked
        // Switch start and destination points
        reversePath = (ImageButton) findViewById(R.id.reverse_path);
        reversePath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Swap start and destination values
                String tempStart = start.getText().toString().toUpperCase();
                String tempDestination = destination.getText().toString().toUpperCase();

                start.setText(tempDestination);
                destination.setText(tempStart);
            }
        });



        // load the animation
        animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move);
        animZoomin = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_in);

        // set animation listener
        animFadein.setAnimationListener(this);
        animZoomin.setAnimationListener(this);

        // button click event
        transitMode = (ImageButton) findViewById(R.id.transport_icon);
        transitMode.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                transitMode.setVisibility(View.VISIBLE);

                // start the animation
                transitMode.startAnimation(animFadein);

                final AlertDialog.Builder builder = new AlertDialog.Builder(Navigation.this);
                // Set the dialog title
                builder.setTitle("Pick transit");
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                builder.setSingleChoiceItems(getResources().getStringArray(R.array.transit_mode), -1,
                        new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selection) {
                        // The 'selection' argument contains the index position
                        // of the selected item
                        switch (selection) {
                            case 0:
                                // Car first option selected
                                Intent transitOptionCar = new Intent(Navigation.this, Activity_TransitOptionCar.class);
                                dialog.dismiss();
                                startActivity(transitOptionCar);
                                break;
                            case 1:
                                // Public transport when 2nd  option selected
                                Intent transitOptionPublicTransport = new Intent(Navigation.this, Activity_TransitOptionPublicTransport.class);
                                dialog.dismiss();
                                startActivity(transitOptionPublicTransport);
                                break;
                            case 2:
                                // Walk 3rd option selected
                                Intent transitOptionWalk = new Intent(Navigation.this, Activity_TransitOptionWalk.class);
                                dialog.dismiss();
                                startActivity(transitOptionWalk);
                                break;
                            case 3:
                                // Cycle option selected
                                Intent transitOptionCycle = new Intent(Navigation.this, Activity_TransitOptionCycle.class);
                                dialog.dismiss();
                                startActivity(transitOptionCycle);
                                break;
                            case 4:
                                // Spaceship option selected
                                Intent transitOptionSpaceship = new Intent(Navigation.this, Activity_TransitOptionSpaceship.class);
                                dialog.dismiss();
                                startActivity(transitOptionSpaceship);
                                break;
                        }
                    }

                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });









        bestRoute = (Button) findViewById(R.id.best_route_btn);
        bestRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get inputs from screen
                String startInput = start.getText().toString().toUpperCase();
                String destinationInput = destination.getText().toString().toUpperCase();

                // Validate inputs
                if (!validate()) {
                    return; // return is input invalid
                }

                // Init search
                ShortestPath searchForShortestPath = new ShortestPath();
                Map<Graph.Vertex,Integer> discoveredRoute = searchForShortestPath.shortestPath(directedGraph, startInput);

                // Get list of paths between start and destination
                List<Graph.Vertex> path = searchForShortestPath.getPath();



                // Display results
                // 1) Loop through all results from search
                // 2) Add them to a list
                // 3) Path list into adapter
                ArrayList<String> shortestPath = new ArrayList<String>();

                // Add results to list
                for(Graph.Vertex vertex : path) {
                    shortestPath.add(vertex.name); // string representation
                }

                ArrayList<List<String>> paths = new ArrayList<List<String>>();
                paths.add(shortestPath);

                // Create ArrayAdapter using the planet list
                shortestPathListAdapter = new ShortestPathListAdapter(Navigation.this, R.layout.listview_item, paths);

                // Assign adapter to ListView
                listView.setAdapter(shortestPathListAdapter);



            }
        });





    }

    public boolean validate() {
        boolean valid = true;

        String startInputText = start.getText().toString();
        String destinationInputText = destination.getText().toString();

        if (startInputText.isEmpty()) {
            start.setError("Insert starting point");
            valid = false;
        } else {
            start.setError(null);
        }

        if (destinationInputText.isEmpty()) {
            destination.setError("Insert destination");
            valid = false;
        } else {
            destination.setError(null);
        }

        if (startInputText.length() > 2 || destinationInputText.length() > 2) {
            destination.setError("Single letters only");
            valid = false;
        } else {
            destination.setError(null);
        }

        return valid;
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        // Take any action after completing the animation

        // check for fade in animation
        if (animation == animFadein) {
            Toast.makeText(getApplicationContext(), "Animation Stopped",
                    Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAnimationStart(Animation animation) {
        // TODO Auto-generated method stub

    }



    /**
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



