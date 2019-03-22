package com.software.ttsl.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.software.ttsl.Interface.ItemClickListener;
import com.software.ttsl.R;
import com.software.ttsl.model.ParticipantsData;

import java.util.ArrayList;

public class ParticipantsListAdapter extends RecyclerView.Adapter<ParticipantsListAdapter.ParticipantsListViewHolder> {

    private Context context;
    private ArrayList<ParticipantsData> participantsDataArrayList;
    private ItemClickListener listener;

    public ParticipantsListAdapter(Context context, ArrayList<ParticipantsData> participantsDataArrayList, ItemClickListener listener) {
        this.context = context;
        this.participantsDataArrayList = participantsDataArrayList;
        this.listener = listener;
    }

    @Override
    public ParticipantsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_participants_list,null,false);
        return new ParticipantsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ParticipantsListViewHolder holder, int position) {
        ParticipantsData participantsData = participantsDataArrayList.get(position);
        holder.textView.setText(participantsData.getParticipantsName());

    }

    @Override
    public int getItemCount() {
        return participantsDataArrayList.size();
    }

    public class ParticipantsListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView ;
        ImageView imageViewDelete;
        public ParticipantsListViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.tv_item);
            imageViewDelete=(ImageView)itemView.findViewById(R.id.iv_delete);
            imageViewDelete.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition());
        }
    }


}
