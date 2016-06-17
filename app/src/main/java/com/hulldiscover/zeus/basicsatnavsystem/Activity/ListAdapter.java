package com.hulldiscover.zeus.basicsatnavsystem.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hulldiscover.zeus.basicsatnavsystem.R;

import java.util.List;

/**
 * Created by Zeus on 01/04/16.
 */
public class ListAdapter extends ArrayAdapter<String> {

    protected Context mContext;
    protected List<String> routes;

    // constructor
    public ListAdapter(Context context, List<String> routesList) {
        super(context, R.layout.item_list, routesList);
        // getters and setters
        mContext = context;
        routes = routesList;
    }

    // init ViewHolder
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        // if there are no items to be displayed, then create views
        if(convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.activity_main, null);

            holder = new ViewHolder();

            holder.route = (TextView) convertView
                    .findViewById(R.id.destination);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        // get position of the row that has been clicked
        //Events eventsObject = mEvents.get(position);


        // title
        holder.route.setText(routes.toString()); // set text from what was retrived from parse




        return convertView;
    }

    // static class
    public static class ViewHolder {
        TextView route;
    }
}
