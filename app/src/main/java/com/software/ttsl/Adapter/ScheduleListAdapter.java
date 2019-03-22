package com.software.ttsl.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.software.ttsl.R;
import com.software.ttsl.Response.SailingScheduleResponse;

import java.util.List;

public class ScheduleListAdapter extends RecyclerView.Adapter<ScheduleListAdapter.ScheduleListViewHolder> {

    Context context;
    List<SailingScheduleResponse> sailingScheduleList;


    public ScheduleListAdapter(Context context, List<SailingScheduleResponse> sailingScheduleList) {
        this.context = context;
        this.sailingScheduleList = sailingScheduleList;
    }

    @Override
    public ScheduleListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_sailing_schedule, parent, false);
        return new ScheduleListViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ScheduleListViewHolder holder, int position) {
        SailingScheduleResponse sailingSchedule = sailingScheduleList.get(position);
        holder.departureDateTextView.setText(sailingSchedule.getEtd());
        holder.arrivalDateTextView.setText(sailingSchedule.getEta());
        holder.vesselNameTextView.setText(sailingSchedule.getVesselName());
        holder.portOfLoadingTextView.setText(sailingSchedule.getPolCode());
        holder.portOfDischargeTextView.setText(sailingSchedule.getPodCode());

    }

    @Override
    public int getItemCount() {
        return sailingScheduleList.size();
    }

    public class ScheduleListViewHolder extends RecyclerView.ViewHolder{
        TextView departureDateTextView;
        TextView arrivalDateTextView;
        TextView vesselNameTextView;
        TextView portOfLoadingTextView;
        TextView portOfDischargeTextView;
        public ScheduleListViewHolder(View itemView) {
            super(itemView);
            departureDateTextView = (TextView)itemView.findViewById(R.id.tv_etd);
            arrivalDateTextView=(TextView)itemView.findViewById(R.id.tv_eta);
            vesselNameTextView=(TextView)itemView.findViewById(R.id.tv_vessel);
            portOfLoadingTextView=(TextView)itemView.findViewById(R.id.tv_pol);
            portOfDischargeTextView=(TextView)itemView.findViewById(R.id.tv_pod);
        }
    }
}
