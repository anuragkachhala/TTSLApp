package com.software.ttsl.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.software.ttsl.Interface.ItemClickListener;
import com.software.ttsl.R;
import com.software.ttsl.Request.EventDataModel;
import com.software.ttsl.Utils.DateAndTimeUtil;

import java.util.ArrayList;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventListViewHolder> {
    private static final String TAG = EventListAdapter.class.getName();

    private Context context;
    private ArrayList<EventDataModel> eventList;
    private ItemClickListener listener;

    public EventListAdapter(Context context, ArrayList<EventDataModel> eventList, ItemClickListener listener) {
        this.context = context;
        this.eventList = eventList;
        this.listener = listener;
    }

    @Override
    public EventListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_event_list, parent, false);

        return new EventListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventListViewHolder holder, int position) {
        EventDataModel eventDataModel = eventList.get(position);
        if (eventDataModel.getAllDay()) {
            if (!DateAndTimeUtil.longToDate(eventDataModel.getFromDate()).equals(DateAndTimeUtil.longToDate(eventDataModel.getToDate()))) {

                holder.textViewFromDate.setText(DateAndTimeUtil.longToDate(eventDataModel.getFromDate()));
                holder.textViewToDate.setText(DateAndTimeUtil.longToDate(eventDataModel.getToDate()));
                //holder.imageViewCalender.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_multidate_cal));
                holder.imageViewCalender.setImageDrawable(setVectorForPreLollipop(R.drawable.ic_multidate_cal, context));


            } else {

                holder.textViewFromDate.setText(DateAndTimeUtil.longToDate(eventDataModel.getFromDate()));
                holder.textViewTo.setVisibility(View.GONE);
                holder.textViewToDate.setText("All Day");
                holder.imageViewCalender.setImageDrawable(setVectorForPreLollipop(R.drawable.ic_event, context));

            }

        } else {
            holder.textViewFromDate.setText(DateAndTimeUtil.longToDate(eventDataModel.getFromDate()));
            holder.textViewTo.setVisibility(View.GONE);
            holder.textViewToDate.setText(DateAndTimeUtil.longToTime(eventDataModel.getFromTime()));

            //holder.imageViewCalender.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_event_time));
            holder.imageViewCalender.setImageDrawable(setVectorForPreLollipop(R.drawable.ic_event_time, context));
        }

        holder.textViewTitle.setText(eventDataModel.getTitle());


    }


    public Drawable setVectorForPreLollipop(int resourceId, Context activity) {
        Drawable icon;
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            icon = VectorDrawableCompat.create(activity.getResources(), resourceId, activity.getTheme());
        } else {
            icon = activity.getResources().getDrawable(resourceId, activity.getTheme());
        }

        return icon;
    }


    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class EventListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewFromDate;
        TextView textViewToDate;
        TextView textViewTo;
        TextView textViewTitle;
        ImageView imageViewCalender;
        LinearLayout layout;
        View relative_layout;

        public EventListViewHolder(View itemView) {
            super(itemView);
            textViewFromDate = (TextView) itemView.findViewById(R.id.tv_from_date);
            textViewToDate = (TextView) itemView.findViewById(R.id.tv_to_date);
            textViewTo = (TextView) itemView.findViewById(R.id.tv_to);
            imageViewCalender = (ImageView) itemView.findViewById(R.id.iv_calender);
            textViewTitle = (TextView) itemView.findViewById(R.id.tv_title);
            layout = (LinearLayout) itemView.findViewById(R.id.layout);
            relative_layout = itemView.findViewById(R.id.relative_layout);
            textViewTitle.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition());
        }
    }
}
