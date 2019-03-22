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
import com.software.ttsl.Response.VesselListResponse;
import com.software.ttsl.model.PortData;

import java.util.List;

public class VesselListAdapter extends RecyclerView.Adapter<VesselListAdapter.VesselListViewHolder> implements Filterable {
    Context context;
    List<VesselListResponse> vesselList;
    List<VesselListResponse> vesselListFiltered;
    VesselListAdapterListener vesselListAdapterListener;

    public VesselListAdapter(Context context, List<VesselListResponse> vesselList, VesselListAdapterListener vesselListAdapterListener) {
        this.context = context;
        this.vesselList = vesselList;
        this.vesselListFiltered = vesselList;
        this.vesselListAdapterListener = vesselListAdapterListener;
    }

    @Override
    public VesselListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_vessel_list, parent, false);
        return new VesselListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VesselListViewHolder holder, int position) {
        VesselListResponse vessel  =  vesselListFiltered.get(position);
        holder.textViewVessel.setText(vessel.getVesselName());
        holder.textViewVesselId.setText( String.valueOf(vessel.getVesselId()));
    }

    @Override
    public int getItemCount() {
        return vesselListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public class VesselListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewVessel;
        TextView textViewVesselId;

        public VesselListViewHolder(View itemView) {
            super(itemView);

            textViewVessel =(TextView)itemView.findViewById(R.id.tv_vessel_name);
            textViewVesselId=(TextView)itemView.findViewById(R.id.tv_vessel_id);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            vesselListAdapterListener.onVesselSelected(getAdapterPosition());
        }
    }

    public interface VesselListAdapterListener{
        void onVesselSelected(int position);
    }
}
