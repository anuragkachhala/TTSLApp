package com.software.ttsl.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.software.ttsl.Interface.ItemClickListener;
import com.software.ttsl.R;
import com.software.ttsl.Request.LeadDataModel;
import com.software.ttsl.Utils.DateAndTimeUtil;
import com.software.ttsl.model.AddLeadData;
import com.software.ttsl.model.OrderByData;

import java.util.List;

public class LeadListFragmentAdapter extends RecyclerView.Adapter<LeadListFragmentAdapter.LeadListViewHolder> {

    public static final String TAG = LeadListFragmentAdapter.class.getName();

    Context context;
    List<LeadDataModel> leadDataModelList;
    private ItemClickListener listener;

    public LeadListFragmentAdapter(Context context, List<LeadDataModel> leadDataModelList,ItemClickListener listener) {
        this.context = context;
        this.leadDataModelList = leadDataModelList;
        this.listener =listener;


        int i =0;
        for (LeadDataModel leadDataModel: leadDataModelList){
            Log.e(TAG, i + "   " + leadDataModel.isHeaderShow() + " ");
            i++;
        }

    }

    @Override
    public LeadListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_lead_list,parent,false);
        return new LeadListViewHolder(view);

    }

    @Override
    public void onBindViewHolder(LeadListViewHolder holder, int position) {

        LeadDataModel leadDataModel= leadDataModelList.get(position);

            holder.textViewLeadName.setText(leadDataModel.getCompany());
            holder.textViewLeadEmail.setText((leadDataModel.getFirstName() +leadDataModel.getLastName()).trim() );
             holder.textViewLeadImage.setText(leadDataModel.getLeadOwner().substring(0,2).toUpperCase());
            if(leadDataModel.isHeaderShow()) {

                Log.e(TAG,position+ " "+leadDataModel.isHeaderShow());
                holder.textViewLeadHeader.setVisibility(View.VISIBLE);
                holder.viewHeader.setVisibility(View.VISIBLE);
                holder.textViewLeadHeader.setText(DateAndTimeUtil.longToDate(leadDataModel.getCreatedDate()));
            }else {
                Log.e(TAG,position+ " "+leadDataModel.isHeaderShow());
                holder.textViewLeadHeader.setVisibility(View.GONE);
                holder.viewHeader.setVisibility(View.GONE);


            }

    }

    @Override
    public int getItemCount() {
        return leadDataModelList.size();
    }

    public class LeadListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewLeadHeader;
        View viewHeader;
        TextView textViewLeadName;
        TextView textViewLeadEmail;
        TextView textViewLeadImage;
        public LeadListViewHolder(View itemView) {
            super(itemView);
            textViewLeadHeader =(TextView)itemView.findViewById(R.id.tv_lead_header);
            viewHeader =(View) itemView.findViewById(R.id.view_header);
            textViewLeadName =(TextView)itemView.findViewById(R.id.tv_lead_name);
            textViewLeadEmail=(TextView)itemView.findViewById(R.id.tv_lead_mail_id);
            textViewLeadImage=(TextView)itemView.findViewById(R.id.tv_lead_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition());
        }
    }
}
