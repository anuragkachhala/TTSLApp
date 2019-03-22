package com.software.ttsl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.software.ttsl.Adapter.BillPendingAdapter;
import com.software.ttsl.Interface.ItemClickListener;

import java.util.List;

public class ParticipantSelectListAdapter extends RecyclerView.Adapter<ParticipantSelectListAdapter.ParticipantSelectListViewHolder> {
    public static final String TAG = ParticipantSelectListAdapter.class.getName();
    private Context context;
    private List<String> stringList;
    private ParticipantsAdapterListener listener;

    public ParticipantSelectListAdapter(Context context, List<String> stringList, ParticipantsAdapterListener listener) {
        this.context = context;
        this.stringList = stringList;
        this.listener = listener;
    }

    @Override
    public ParticipantSelectListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_selected_participants,null,false);

        return new ParticipantSelectListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ParticipantSelectListViewHolder holder, int position) {
        String item  = stringList.get(position);
        holder.textView.setText(item);

        applyClickEvents(holder,position);

    }

    private void applyClickEvents(ParticipantSelectListViewHolder holder, final int position) {
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public class  ParticipantSelectListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView ;
        CheckBox checkBox;
        RelativeLayout relativeLayout;
        public ParticipantSelectListViewHolder(View itemView) {
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

    public interface ParticipantsAdapterListener
    {
        void onCheckBoxClicked(int position);

        void onItemClicked(int position);
    }
}
