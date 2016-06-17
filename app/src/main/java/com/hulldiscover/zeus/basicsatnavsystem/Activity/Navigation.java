package com.hulldiscover.zeus.basicsatnavsystem.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.hulldiscover.zeus.basicsatnavsystem.BreadthFirstPaths;
import com.hulldiscover.zeus.basicsatnavsystem.Graph;
import com.hulldiscover.zeus.basicsatnavsystem.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zeus on 04/04/16.
 */
public class Navigation extends AppCompatActivity {


    AutoCompleteTextView start;
    TextView destination;
    Button searchRoutes;
    ImageButton reversePath;
    String startInput = "";
    String destinationInput = "";
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setup and init directed graph
        final Graph directedGraph = new Graph(GRAPH);

        // Get a reference to the AutoCompleteTextView in the layout
        start = (AutoCompleteTextView) findViewById(R.id.start);

        // Get the string array
        String[] countries = getResources().getStringArray(R.array.destination_array);

        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.activity_list_item, countries);
        start.setAdapter(adapter);

        // Get reference of textViews in the layout
        destination = (TextView) findViewById(R.id.destination);


        // Inputs from screen, used later
        startInput = start.getText().toString().toUpperCase();
        destinationInput = destination.getText().toString().toUpperCase();

        // Get ListView object from xml
        //listView = (ListView) findViewById(R.id.route_list);

        // When search button is clicked
        // Find all possible paths between start and destination
        searchRoutes = (Button) findViewById(R.id.search_routes_btn);
        searchRoutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Init search
                BreadthFirstPaths findAllPaths = new BreadthFirstPaths(directedGraph);

                // Get inputs from screen
                String startInput = start.getText().toString().toUpperCase();
                String destinationInput = destination.getText().toString().toUpperCase();

                // Get list of paths between start and destination
                List<List<String>> pathList = findAllPaths.getAllPaths(startInput,destinationInput);


                // Display results
                List<String> l = new ArrayList<String>();

                for(List<String> pathNames : pathList) {
                    System.out.println(pathNames);
                    l.add(pathNames.toArray().toString());
                }

                //start.setText(pathList.toString());

                String[] myStringArray=
                        {"A","B","C","D","E","F","G","H","I","J","K"};

                ListAdapter a = new ListAdapter(Navigation.this, l);
                //listView.setAdapter(a);

                // Create ArrayAdapter using the planet list.
                //ArrayAdapter<String> adapter = new ArrayAdapter<String>(Navigation.this, R.layout.item_list, myStringArray);


                // Assign adapter to ListView
                //listView.setAdapter(adapter);
            }
        });

        // When item from list is selected
        // Display small message
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String  itemValue = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();
            }
        });*/

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



