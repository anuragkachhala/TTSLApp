package com.software.ttsl.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.software.ttsl.R;
import com.software.ttsl.model.DeviceContactData;

import java.util.List;

public class DeviceContactListAdapter extends RecyclerView.Adapter<DeviceContactListAdapter.DeviceContactViewHolder> {
    public static final String TAG = DeviceContactListAdapter.class.getName();

    private Context context;
    private List<DeviceContactData> contactDataList;

    public DeviceContactListAdapter(Context context, List<DeviceContactData> contactDataList) {
        this.context = context;
        this.contactDataList = contactDataList;
    }

    @Override
    public DeviceContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_selected_participants,null,false);
        return new DeviceContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DeviceContactViewHolder holder, int position) {
        DeviceContactData deviceContactData = contactDataList.get(position);
        holder.textView.setText(deviceContactData.getName());

    }

    @Override
    public int getItemCount() {
        return contactDataList.size();
    }

    public class DeviceContactViewHolder extends RecyclerView.ViewHolder {
        TextView textView ;
        CheckBox checkBox;
        RelativeLayout relativeLayout;
        public DeviceContactViewHolder(View itemView) {
            super(itemView);
            relativeLayout=(RelativeLayout)itemView.findViewById(R.id.layout_item);
            textView = (TextView)itemView.findViewById(R.id.tv_item);
            checkBox=(CheckBox)itemView.findViewById(R.id.checkBox);
        }
    }
}
