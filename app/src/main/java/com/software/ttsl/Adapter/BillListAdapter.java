package com.software.ttsl.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.software.ttsl.R;
import com.software.ttsl.model.BillListData;

import java.util.List;

public class BillListAdapter extends RecyclerView.Adapter<BillListAdapter.BillListViewHolder> {
      List<BillListData> dataList;
      Context context;
      BillListItemListener listener;

    public BillListAdapter(List<BillListData> dataList, Context context, BillListItemListener listener) {
        this.dataList = dataList;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public BillListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bill_list_row,parent,false);
        return new BillListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BillListViewHolder holder, int position) {
        BillListData billListData = dataList.get(position);
        holder.billNumber.setText(billListData.getBookingNumber());
        holder.billDate.setText(billListData.getBookingDate());
        holder.etd.setText(billListData.getEta());
        holder.eta.setText(billListData.getEta());


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class BillListViewHolder extends RecyclerView.ViewHolder {
        TextView billNumber,billDate,etd,eta;
        ImageView printBill;
        public BillListViewHolder(View itemView) {
            super(itemView);
            billNumber=(TextView)itemView.findViewById(R.id.billno);
            billDate=(TextView)itemView.findViewById(R.id.tv_bill_date);
            etd=(TextView)itemView.findViewById(R.id.tv_etd);
            eta=(TextView)itemView.findViewById(R.id.tv_eta);
            printBill=(ImageView)itemView.findViewById(R.id.print);
        }
    }

    public interface BillListItemListener{
        public void  onPrintClick(int position);
    }
}
