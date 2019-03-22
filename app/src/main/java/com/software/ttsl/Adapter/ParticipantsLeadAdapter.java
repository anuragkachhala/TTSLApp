package com.software.ttsl.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.software.ttsl.Interface.ParticipantsAdapterListener;
import com.software.ttsl.R;
import com.software.ttsl.model.ParticipantsData;

import java.util.ArrayList;

public class ParticipantsLeadAdapter extends RecyclerView.Adapter<ParticipantsLeadAdapter.ParticipantsLeadViewHolder> {
    public static final String TAG = ParticipantsLeadAdapter.class.getName();
    private Context context;
    private ArrayList<ParticipantsData> participantsDataList;
    private ParticipantsAdapterListener listener;

    public ParticipantsLeadAdapter(Context context, ArrayList<ParticipantsData> participantsDataList, ParticipantsAdapterListener listener) {
        this.context = context;
        this.participantsDataList = participantsDataList;
        this.listener = listener;
    }

    @Override
    public ParticipantsLeadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_selected_participants,null,false);

      return new ParticipantsLeadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ParticipantsLeadViewHolder holder, int position) {
        ParticipantsData participantsData =  participantsDataList.get(position);
        holder.textView.setText(participantsData.getParticipantsName());
        if(participantsData.isChecked()) {
            holder.checkBox.setChecked(true);
        }


    }

    @Override
    public int getItemCount() {
        return participantsDataList.size();
    }

    public class ParticipantsLeadViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView ;
        CheckBox checkBox;
        RelativeLayout relativeLayout;
        public ParticipantsLeadViewHolder(View itemView) {
            super(itemView);

            relativeLayout=(RelativeLayout)itemView.findViewById(R.id.layout_item);
            textView = (TextView)itemView.findViewById(R.id.tv_item);
            checkBox=(CheckBox)itemView.findViewById(R.id.checkBox);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClicked(getAdapterPosition());
        }
    }
}
