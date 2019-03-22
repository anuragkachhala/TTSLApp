package com.software.ttsl.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.software.ttsl.CustomView.CustomProgressBar;
import com.software.ttsl.Interface.ItemClickListener;
import com.software.ttsl.R;
import com.software.ttsl.Request.DealDataModel;

import java.util.ArrayList;

public class DealListFragmentAdapter extends RecyclerView.Adapter<DealListFragmentAdapter.DealListFragmentViewHolder> {

   public static final String TAG= DealListFragmentAdapter.class.getName();
   Context context;
   ArrayList<DealDataModel> dealDataModelList;
    private ItemClickListener listener;

    public DealListFragmentAdapter(Context context, ArrayList<DealDataModel> dealDataModelList, ItemClickListener listener) {
        this.context = context;
        this.dealDataModelList = dealDataModelList;
        this.listener = listener;
    }

    @Override
    public DealListFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_deal_list, parent, false);
        return new  DealListFragmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DealListFragmentViewHolder holder, int position) {
        DealDataModel dealDataModel = dealDataModelList.get(position);

        holder.textViewDealName.setText(dealDataModel.getDealName());
        holder.textViewDealOwner.setText(dealDataModel.getDealOwner());
        holder.textViewDealStage.setText(dealDataModel.getStage());
        holder.textViewDealAmount.setText(String.valueOf(dealDataModel.getAmount()));
        holder.customProgressBar.setProgress((int) (long) (dealDataModel.getProbability()));
        if(dealDataModel.isHeaderShow()){
            Log.e(TAG, position + " " + dealDataModel.isHeaderShow());
            holder.textViewDealHeader.setVisibility(View.VISIBLE);
            holder.viewHeader.setVisibility(View.VISIBLE);
            holder.textViewDealHeader.setText(dealDataModel.getClosingDate());

        }else {

            Log.e(TAG, position + " " + dealDataModel.isHeaderShow());
            holder.textViewDealHeader.setVisibility(View.GONE);
            holder.viewHeader.setVisibility(View.GONE);

        }

    }

    @Override
    public int getItemCount() {
        return dealDataModelList.size();
    }

    public class DealListFragmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewDealHeader;
        View viewHeader;
        //TextView textViewDealImage;
        TextView textViewDealName;
        TextView textViewDealAmount;
        TextView textViewDealStage;
        TextView textViewDealOwner;
        TextView getTextViewDealName;
        CustomProgressBar customProgressBar;
        public DealListFragmentViewHolder(View itemView) {
            super(itemView);
            textViewDealHeader =(TextView)itemView.findViewById(R.id.tv_deal_header);
            viewHeader=(View)itemView.findViewById(R.id.view_header);
            //textViewDealImage=(TextView)itemView.findViewById(R.id.tv_deal_image);
            textViewDealName=(TextView)itemView.findViewById(R.id.tv_deal_name);
            textViewDealStage=(TextView)itemView.findViewById(R.id.tv_deal_stage);
            textViewDealOwner=(TextView)itemView.findViewById(R.id.tv_deal_owner);
            textViewDealAmount=(TextView)itemView.findViewById(R.id.tv_deal_amount);
            customProgressBar=(CustomProgressBar)itemView.findViewById(R.id.progress_bar);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition());
        }
    }
}
