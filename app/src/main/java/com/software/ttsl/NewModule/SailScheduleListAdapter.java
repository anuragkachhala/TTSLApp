package com.software.ttsl.NewModule;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.software.ttsl.R;

import java.util.List;

public class SailScheduleListAdapter extends RecyclerView.Adapter<SailScheduleListAdapter.SailScheduleViewHolder> {
    private static final String TAG = SailScheduleListAdapter.class.getName();

    private Context context;
    private List<SailScheduleResponse> sailScheduleResponseList;

    public SailScheduleListAdapter(Context context, List<SailScheduleResponse> sailScheduleResponseList) {
        this.context = context;
        this.sailScheduleResponseList = sailScheduleResponseList;
    }

    @Override
    public SailScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_sail_schedule_list, parent, false);
        return new SailScheduleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SailScheduleViewHolder holder, int position) {
        SailScheduleResponse sailScheduleResponse = sailScheduleResponseList.get(position);
        holder.textViewDepartureDate.setText(sailScheduleResponse.getFromEtd());
        holder.textViewArrivalDate.setText(sailScheduleResponse.getFromEta());
        holder.textViewVOY.setText(sailScheduleResponse.getVoy());
        holder.textViewService.setText(sailScheduleResponse.getService());
        holder.textViewSector.setText(sailScheduleResponse.getSector());
        holder.textViewVessel.setText(sailScheduleResponse.getVessel());


    }

    @Override
    public int getItemCount() {
        return sailScheduleResponseList.size();
    }

    public class SailScheduleViewHolder extends RecyclerView.ViewHolder{

        TextView textViewDepartureDate;
        TextView textViewArrivalDate;
        TextView textViewVessel;
        TextView textViewSector;
        TextView textViewService;
        TextView textViewVOY;

        public SailScheduleViewHolder(View itemView) {
            super(itemView);

            textViewDepartureDate =(TextView)itemView.findViewById(R.id.tv_etd);
            textViewArrivalDate =(TextView)itemView.findViewById(R.id.tv_eta);
            textViewVessel =(TextView)itemView.findViewById(R.id.tv_vessel);
            textViewSector =(TextView)itemView.findViewById(R.id.tv_sector);
            textViewService =(TextView)itemView.findViewById(R.id.tv_services);
            textViewVOY =(TextView)itemView.findViewById(R.id.tv_voy);

        }

    }
}
