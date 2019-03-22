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
import android.widget.TextView;

import com.software.ttsl.R;
import com.software.ttsl.Request.EventDataModel;
import com.software.ttsl.Utils.DateAndTimeUtil;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    public static final String TAG = EventAdapter.class.getName();

    private Context context;
    private List<EventDataModel> dataModelList;

    public EventAdapter(Context context, List<EventDataModel> dataModelList) {
        this.context = context;
        this.dataModelList = dataModelList;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_event, parent, false);

        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        EventDataModel eventDataModel = dataModelList.get(position);
        holder.textViewTitle.setText(eventDataModel.getTitle());
        holder.textViewEventDate.setText(DateAndTimeUtil.longToDate(eventDataModel.getFromDate()));
        holder.imageView.setImageDrawable(setVectorForPreLollipop(R.drawable.ic_event_time,context));
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
        return dataModelList.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textViewTitle;
        TextView textViewEventDate;

        public EventViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.iv_calender);
            textViewTitle=(TextView)itemView.findViewById(R.id.tv_event_title);
            textViewEventDate=(TextView)itemView.findViewById(R.id.tv_event_date);

        }
    }
}
