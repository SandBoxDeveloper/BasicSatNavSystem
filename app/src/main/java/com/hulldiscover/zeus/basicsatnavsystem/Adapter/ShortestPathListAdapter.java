package com.hulldiscover.zeus.basicsatnavsystem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hulldiscover.zeus.basicsatnavsystem.R;

import java.util.List;

/**
 * Created by Zeus on 07/05/16.
 */
public class ShortestPathListAdapter extends ArrayAdapter {

    List<String> routes;
    private Context context;

    public ShortestPathListAdapter(Context context, int resource, List<List<String>> route) {
        super(context, resource, route);
        this.context = context;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {

        View row;
        row = convertView;

        // Check if an existing view is being reused, otherwise inflate the view
        final ViewHolder viewHolder; // view lookup cache stored in tag
        if(row == null) {
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.listview_item, parent, false);
            viewHolder = new ViewHolder();

            // Lookup view for data population
            viewHolder.route = (TextView) row.findViewById(R.id.route_path);

            row.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder)row.getTag();
        }

        // Get the data item for this position
        List<String> item = (List<String>)this.getItem(position);

        // Populate the data into the template view using the data object
        viewHolder.route.setText(context.getResources().getString(R.string.list_route_label) +item.toString());

        // Return the completed view to render on screen
        return row;
    }


    public class ViewHolder {
        private TextView route;

    }

}
