package com.software.ttsl.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.software.ttsl.R;
import com.software.ttsl.model.GetQuotation;

import java.util.List;

public class GetQuotationAdapter extends RecyclerView.Adapter<GetQuotationAdapter.GetQuotationViewHolder> {
    List<GetQuotation> quotationList;
    Context context;
    GetQuotationAdapterListener listener;

    public GetQuotationAdapter(List<GetQuotation> quotationList, Context context, GetQuotationAdapterListener listener) {
        this.quotationList = quotationList;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public GetQuotationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                   .inflate(R.layout.get_quotation_row,parent,false);

        return new GetQuotationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GetQuotationViewHolder holder, int position) {
        GetQuotation getQuotation= quotationList.get(position);
        getQuotation.setShippingModeId(String.valueOf(position));
        holder.title.setText(getQuotation.getQuotationType());
        holder.discription.setText(getQuotation.getQuotationDiscription());
        holder.isChecked.setChecked(getQuotation.isChecked());
        holder.mShipModeId.setText(getQuotation.getShippingModeId());
        holder.mRow.setBackgroundColor(getQuotation.getBackgroundColor());

        applyClickEvents(holder,position);

    }

    private void applyClickEvents(GetQuotationViewHolder holder, final int position) {
        holder.isChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //view.setBackgroundColor(Color.parseColor("#E0E0E0"));
                listener.onItemClicked(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return quotationList.size();
    }

    public class GetQuotationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView discription;
        CheckBox isChecked;
        TextView mShipModeId;
        RelativeLayout mRow;

        public GetQuotationViewHolder(View itemView) {
            super(itemView);
            title =(TextView)itemView.findViewById(R.id.quotation_heading);
            discription=(TextView)itemView.findViewById(R.id.quotation_discription);
            isChecked=(CheckBox)itemView.findViewById(R.id.checkBox);
            mShipModeId=(TextView)itemView.findViewById(R.id.ship_mode_id);
            mRow=(RelativeLayout)itemView.findViewById(R.id.view_get_quotation_row);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClicked(getLayoutPosition());
        }
    }

    public interface GetQuotationAdapterListener
    {


        void onItemClicked(int position);

    }


}
