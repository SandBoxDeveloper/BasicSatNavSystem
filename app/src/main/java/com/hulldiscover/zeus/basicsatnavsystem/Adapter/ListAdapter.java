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
public class ListAdapter extends ArrayAdapter {

    List<String> routes;
    private Context context;

    public ListAdapter(Context context, int resource, List<List<String>> route) {
        super(context, resource, route);
        this.context = context;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {

        View row;
        row = convertView;
        final ViewHolder viewHolder;

        if(row == null) {
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.listview_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.route = (TextView) row.findViewById(R.id.route_path);

            row.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder)row.getTag();
        }

        List<String> item = (List<String>)this.getItem(position);

        viewHolder.route.setText(context.getResources().getString(R.string.list_route_label) +item.toString());

        return row;
    }


    public class ViewHolder {
        private TextView route;

    }

}
