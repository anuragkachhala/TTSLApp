package com.software.ttsl;

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

import com.software.ttsl.Interface.ItemClickListener;
import com.software.ttsl.Request.CallDataModel;
import com.software.ttsl.Utils.DateAndTimeUtil;
import com.software.ttsl.Utils.EmployConstantUtil;

import java.util.ArrayList;

public class CallFragmentAdapter extends RecyclerView.Adapter<CallFragmentAdapter.CallFragmentViewHolder> {
    public static final String TAG = CallFragmentAdapter.class.getName();
    private Context context;
    private ArrayList<CallDataModel> callDataModels;
    private ItemClickListener listener;

    public CallFragmentAdapter(Context context, ArrayList<CallDataModel> callDataModels, ItemClickListener listener) {
        this.context = context;
        this.callDataModels = callDataModels;
        this.listener = listener;
    }

    @Override
    public CallFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_call_list, parent, false);
        return new CallFragmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CallFragmentViewHolder holder, int position) {
        CallDataModel callDataModel = callDataModels.get(position);
        if (callDataModel.isHeaderShow()) {
            holder.textViewCallHeader.setVisibility(View.VISIBLE);
            holder.viewHeader.setVisibility(View.VISIBLE);

        } else {
            holder.textViewCallHeader.setVisibility(View.GONE);
        }

        holder.textViewCallHeader.setText(DateAndTimeUtil.longToDate(callDataModel.getCallStartDate(), EmployConstantUtil.DATE_FORMAT));
        holder.textViewCallSubject.setText(callDataModel.getSubject());
        holder.textViewCallTime.setText(DateAndTimeUtil.longToTime(callDataModel.getCallStartTime()));
        holder.textViewCallDuration.setText(checkCallDuration(callDataModel.getCallDuration()));
        if (!callDataModel.getContact().isEmpty()) {
            holder.textViewCallContact.setText(callDataModel.getContact());
            holder.imageViewCallDot.setVisibility(View.VISIBLE);
        }
        if (callDataModel.getCallType().equals("Inbound"))
        {
            holder.imageViewCallType.setImageResource(R.drawable.cmd_phone_incoming);
    }else {
            holder.imageViewCallType.setImageResource(R.drawable.cmd_phone_outgoing);
        }
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


    private String checkCallDuration(long callDuration) {

        return callDuration ==0 ? "--:--" : DateAndTimeUtil.longToTime(callDuration,EmployConstantUtil.TIME_FORMAT_DURATION);
    }




    @Override
    public int getItemCount() {
        return callDataModels.size();
    }

    public class CallFragmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewCallHeader;
        View viewHeader;
        ImageView imageViewCallType;
        TextView textViewCallSubject;
        TextView textViewCallContact;
        TextView textViewCallTime;
        TextView textViewCallDuration;
        ImageView imageViewCallDot;
        public CallFragmentViewHolder(View itemView) {
            super(itemView);
            textViewCallHeader= (TextView)itemView.findViewById(R.id.tv_call_header);
            viewHeader=(View)itemView.findViewById(R.id.view_header);
            imageViewCallType=(ImageView)itemView.findViewById(R.id.iv_call_type);
            textViewCallSubject=(TextView) itemView.findViewById(R.id.tv_call_subject);
            textViewCallContact =(TextView)itemView.findViewById(R.id.tv_call_contact);
            textViewCallDuration=(TextView)itemView.findViewById(R.id.tv_call_duration);
            textViewCallTime=(TextView)itemView.findViewById(R.id.tv_call_time);
            imageViewCallDot=(ImageView)itemView.findViewById(R.id.iv_call_dot);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition());
        }
    }
}
