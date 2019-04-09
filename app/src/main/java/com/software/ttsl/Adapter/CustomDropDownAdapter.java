package com.software.ttsl.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.software.ttsl.R;
import com.software.ttsl.Response.FormDropDown.DropDownDataModel;

import java.util.List;

public class CustomDropDownAdapter extends ArrayAdapter<DropDownDataModel> {

    private Context context;
    private int resource;
    private List<DropDownDataModel> dropDownDataModelList;
    private RecyclerView.ViewHolder viewHolder;
    private DropDownDataModel dropDownDataModel = null;


    public CustomDropDownAdapter(@NonNull Context context, int resource, List<DropDownDataModel> dropDownDataModelList) {
        super(context, resource, dropDownDataModelList);
        this.context = context;
        this.resource = resource;
        this.dropDownDataModelList = dropDownDataModelList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_custom_list_view, parent, false);
        }
        // Lookup view for data population
        TextView listItem = (TextView) convertView.findViewById(R.id.tv_list_item);


        // Populate the data into the template view using the data object
        dropDownDataModel = dropDownDataModelList.get(position);
        if (dropDownDataModel != null) {
            listItem.setText(dropDownDataModel.getValue());
        }
        // Return the completed view to render on screen
        return convertView;
    }
}
