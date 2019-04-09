package com.software.ttsl.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.software.ttsl.R;
import com.software.ttsl.Response.FormDropDown.DropDownDataModel;


import java.util.ArrayList;
import java.util.List;

public class LocationSearchAdapter extends RecyclerView.Adapter<LocationSearchAdapter.LocationSearchViewHolder> implements Filterable {


    private Context context;
    private List<DropDownDataModel> dropDownDataModelListFiltered;
    private List<DropDownDataModel> dropDownDataModelList;
    private LocationListAdapterListener locationListAdapterListener;

    public LocationSearchAdapter(Context context, List<DropDownDataModel> dropDownDataModelList,LocationListAdapterListener locationListAdapterListener) {
        this.context = context;
        this.dropDownDataModelListFiltered = dropDownDataModelList;
        this.dropDownDataModelList = dropDownDataModelList;
        this.locationListAdapterListener = locationListAdapterListener;
    }

    @Override
    public LocationSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_location_list, parent, false);
        return new LocationSearchViewHolder(itemView);
        
    }

    @Override
    public void onBindViewHolder(LocationSearchViewHolder holder, int position) {
        DropDownDataModel dropDownDataModel = dropDownDataModelListFiltered.get(position);

        holder.textViewLocation.setText(dropDownDataModel.getValue());
        holder.textViewLocationId.setText( String.valueOf(dropDownDataModel.getKey()));

    }

    @Override
    public int getItemCount() {
        return dropDownDataModelListFiltered.size();
    }

    public class LocationSearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewLocation;
        TextView textViewLocationId;

        public LocationSearchViewHolder(View itemView) {
            super(itemView);
        

            textViewLocation =(TextView)itemView.findViewById(R.id.tv_location_name);
            textViewLocationId=(TextView)itemView.findViewById(R.id.tv_location_id);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            DropDownDataModel dropDownDataModel = dropDownDataModelListFiltered.get(getAdapterPosition());
            locationListAdapterListener.onLocationSelected(dropDownDataModel.getKey(),dropDownDataModel.getValue());
        }
        
        
       
    }


    public interface LocationListAdapterListener{
        void onLocationSelected(String key,String value);
    }



    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    dropDownDataModelListFiltered= dropDownDataModelList;
                } else {
                    List<DropDownDataModel> filteredList = new ArrayList<>();
                    for (DropDownDataModel row : dropDownDataModelList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getValue().toLowerCase().contains(charString.toLowerCase()) ) {
                            filteredList.add(row);
                        }
                    }

                    dropDownDataModelListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = dropDownDataModelListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                dropDownDataModelListFiltered = (ArrayList<DropDownDataModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };

    }


    public void setList(List<DropDownDataModel> dropDownDataModelList){
        this.dropDownDataModelList = dropDownDataModelList;
        dropDownDataModelListFiltered = dropDownDataModelList;
    }

    ;
}
