package com.software.ttsl.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.software.ttsl.R;
import com.software.ttsl.model.PortData;
import java.util.ArrayList;
import java.util.List;

public class PortDataAdapter extends RecyclerView.Adapter<PortDataAdapter.PortDataViewHolder> implements Filterable {

    private Context context;
    private List<PortData> portDataList;
    private List<PortData> portDataListFiltered;
    private PortAdapterListener listener;
    private String mSearchText;


    public PortDataAdapter(Context context, List<PortData> portDataList, PortAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.portDataList= portDataList;
        this.portDataListFiltered = portDataList;
    }
    @NonNull
    @Override
    public PortDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_port_data, parent, false);
        return new PortDataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PortDataViewHolder holder, int position) {
           PortData portData = portDataListFiltered.get(position);
           holder.portData.setText(portData.getPortCode()+" , " +portData.getState()+" , "+
           portData.getCountry());
    
    }

    @Override
    public int getItemCount() {
        return  portDataListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    portDataListFiltered= portDataList;
                } else {
                    List<PortData> filteredList = new ArrayList<>();
                    for (PortData row : portDataList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match

                        String prefixString = charString.toString().toLowerCase();
                        mSearchText = prefixString;
                        if (row.getPortCode().toLowerCase().contains(charString.toLowerCase()) || row.getPortCode().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    portDataListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = portDataListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                portDataListFiltered= (ArrayList<PortData>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public  class PortDataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView portData;

        public PortDataViewHolder(View itemView) {
            super(itemView);
            portData =(TextView)itemView.findViewById(R.id.tv_port_data);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onPortSelected(getAdapterPosition());
        }
    }

    public interface PortAdapterListener {
        void onPortSelected(int position);

    }

    public void notifyData(List<PortData> myList) {
        Log.d("notifyData ", myList.size() + "");
        this.portDataList = myList;
        this.portDataListFiltered =myList;
        notifyDataSetChanged();
    }
    
    
}
