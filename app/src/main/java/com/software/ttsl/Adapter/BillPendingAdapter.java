package com.software.ttsl.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.software.ttsl.PendingInvoiceActivity;
import com.software.ttsl.R;
import com.software.ttsl.model.PendingInvoice;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BillPendingAdapter extends RecyclerView.Adapter<BillPendingAdapter.PendingBillViewHolder> {
    Context context;
    List<PendingInvoice> pendingInvoiceList;
    private BillPendingAdapterListener listener;

    public BillPendingAdapter(Context context, List<PendingInvoice> pendingInvoiceList,BillPendingAdapterListener listener) {
    this.context=context;
    this.pendingInvoiceList=pendingInvoiceList;
    this.listener=listener;
    }

    @Override
    public PendingBillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pending_invoice_row,parent,false);
        return new PendingBillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PendingBillViewHolder holder, int position) {
        PendingInvoice pendingInvoice =pendingInvoiceList.get(position);
        String duesMonthText ="Dues for  ";
        String dueMonth =pendingInvoice.getMounth();
        String total =duesMonthText+dueMonth;
        String rupeeSymbol = context.getResources().getString(R.string.Rs);
        SpannableString spannableString = new SpannableString(total);
        spannableString.setSpan(new ForegroundColorSpan(Color.BLACK),duesMonthText.length(),total.length(),0);
        holder.billAmount.setText( rupeeSymbol+ " "+String.valueOf(pendingInvoice.getAmount()));
        holder.billNumber.setText(pendingInvoice.getBillNumber());
        holder.dueDate.setText(pendingInvoice.getDueDate());
        holder.dueMonth.setText(spannableString);
        holder.isChecked.setChecked(pendingInvoice.getChecked());

        applyClickEvents(holder,position);



    }

    private void applyClickEvents(PendingBillViewHolder holder,final int position) {
        holder.isChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return pendingInvoiceList.size();
    }


    public class PendingBillViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.check)
        CheckBox isChecked;

        @BindView(R.id.tv_due_month)
        TextView dueMonth;

        @BindView(R.id.tv_due_date)
        TextView dueDate;

        @BindView(R.id.tv_bill_no)
        TextView billNumber;

        @BindView(R.id.tv_bill_amount)
        TextView billAmount;
        public PendingBillViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            /*isChecked =(CheckBox)itemView.findViewById(R.id.check);*/
            /*dueMonth=(TextView)itemView.findViewById(R.id.tv_due_month);
            dueDate=(TextView)itemView.findViewById(R.id.tv_due_date);
            billNumber=(TextView)itemView.findViewById(R.id.tv_bill_no);
            billAmount=(TextView)itemView.findViewById(R.id.tv_bill_amount);*/
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
                listener.onItemClicked(getAdapterPosition());

            }
    }

   public interface BillPendingAdapterListener
   {
       void onCheckBoxClicked(int position);

       void onItemClicked(int position);
   }

}
