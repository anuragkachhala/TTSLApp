package com.software.ttsl.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.software.ttsl.Interface.ItemClickListener;
import com.software.ttsl.R;
import com.software.ttsl.model.CustomerChallengeModel;

import java.util.Date;
import java.util.List;

public class CustomerChallengeAdapter extends RecyclerView.Adapter<CustomerChallengeAdapter.CustomerChallengeViewHolder> {


    public static final String TAG = CustomerChallengeAdapter.class.getName();
    private List<CustomerChallengeModel> customerChallengeList;
    private Context context;
    private ItemClickListener listener;


    public CustomerChallengeAdapter(List<CustomerChallengeModel> customerChallengeList, Context context, ItemClickListener listener) {
        this.customerChallengeList = customerChallengeList;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public CustomerChallengeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_customer_challenge_list, parent, false);
        return new CustomerChallengeViewHolder(view);


    }

    @Override
    public void onBindViewHolder(CustomerChallengeViewHolder holder, int position) {
        CustomerChallengeModel customerChallengeModel = customerChallengeList.get(position);

        holder.textViewCustomerName.setText(customerChallengeModel.getCustomer());
        if (customerChallengeModel.getContactName().equals("")) {
            holder.textViewCustomerContact.setText(customerChallengeModel.getLeadName());
        } else {
            holder.textViewCustomerContact.setText(customerChallengeModel.getContactName());
        }
        if (customerChallengeModel.getCustomer().length() == 2 || customerChallengeModel.getCustomer().length() > 2) {
            holder.textViewCustomerImage.setText(customerChallengeModel.getCustomer().substring(0, 2).toUpperCase());
        } else {
            holder.textViewCustomerImage.setText(customerChallengeModel.getCustomer().substring(0, 1).toUpperCase());
        }
        if (customerChallengeModel.getHeadershow()) {

            Log.e(TAG, position + " " + customerChallengeModel.getHeadershow());
            holder.textViewCustomerHeader.setVisibility(View.VISIBLE);
            holder.viewHeader.setVisibility(View.VISIBLE);
            holder.textViewCustomerHeader.setText(longToDate(customerChallengeModel.getCreatedTime()));
        } else {
            Log.e(TAG, position + " " + customerChallengeModel.getHeadershow());
            holder.textViewCustomerHeader.setVisibility(View.GONE);
            holder.viewHeader.setVisibility(View.VISIBLE);


        }

    }

    private String longToDate(long date) {

        // or you already have long value of date, use this instead of milliseconds variable.
        return DateFormat.format("dd-MM-yyyy", new Date(date)).toString();
    }

    @Override
    public int getItemCount() {
        return customerChallengeList.size();
    }

    public class CustomerChallengeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewCustomerHeader;
        View viewHeader;
        TextView textViewCustomerName;
        TextView textViewCustomerContact;
        TextView textViewCustomerImage;

        public CustomerChallengeViewHolder(View itemView) {
            super(itemView);
            textViewCustomerHeader = (TextView) itemView.findViewById(R.id.tv_customer_header);
            textViewCustomerContact = (TextView) itemView.findViewById(R.id.tv_customer_contact);
            viewHeader = (View) itemView.findViewById(R.id.view_header);
            textViewCustomerName = (TextView) itemView.findViewById(R.id.tv_customer_name);
            textViewCustomerImage = (TextView) itemView.findViewById(R.id.tv_customer_image);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition());
        }
    }
}
