package com.software.ttsl.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.software.ttsl.R;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter<String> {

    private Context context;
    private int resource ;
    private String[] itemList;


    public CustomListAdapter(@NonNull Context context, int resource,String[] itemList) {
        super(context, resource, itemList);
        this.context = context;
        this.resource = resource;
        this.itemList = itemList;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        String item = itemList[position];
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_custom_list_view, parent, false);
        }
        // Lookup view for data population
        TextView listItem = (TextView) convertView.findViewById(R.id.tv_list_item);

        // Populate the data into the template view using the data object
        listItem.setText(item);
        // Return the completed view to render on screen
        return convertView;
    }


}

