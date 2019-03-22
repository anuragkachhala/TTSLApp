package com.software.ttsl.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.software.ttsl.CustomView.CustomProgressBar;
import com.software.ttsl.R;
import com.software.ttsl.Request.DealDataModel;

import java.util.List;

public class DealListAdapter extends RecyclerView.Adapter<DealListAdapter.DealListViewHolder> {
    private Context context;
    private List<DealDataModel> dealDataModelList;

    public DealListAdapter(Context context, List<DealDataModel> dealDataModelList) {
        this.context = context;
        this.dealDataModelList = dealDataModelList;
    }

    @Override
    public DealListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_deal, parent, false);
        return new DealListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DealListViewHolder holder, int position) {
        DealDataModel dealDataModel = dealDataModelList.get(position);
        holder.textViewDealName.setText(dealDataModel.getDealName());
        holder.textViewDealClosingData.setText(dealDataModel.getClosingDate());
        holder.textViewDealStage.setText(dealDataModel.getStage());
        holder.customProgressBar.setProgress((int) (long) (dealDataModel.getProbability()));
        

    }

    @Override
    public int getItemCount() {
        return dealDataModelList.size();
    }

    public class DealListViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDealName;
        TextView textViewDealStage;
        TextView textViewDealClosingData;
        CustomProgressBar customProgressBar;
        public DealListViewHolder(View itemView) {
            super(itemView);
            textViewDealName=(TextView)itemView.findViewById(R.id.tv_deal_name);
            textViewDealStage=(TextView)itemView.findViewById(R.id.tv_deal_stage);
            textViewDealClosingData=(TextView)itemView.findViewById(R.id.tv_deal_closing_date);
            customProgressBar=(CustomProgressBar)itemView.findViewById(R.id.progress_bar);
        }
    }
}
