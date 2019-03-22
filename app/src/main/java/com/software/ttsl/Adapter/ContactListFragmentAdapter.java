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
import com.software.ttsl.Utils.DateAndTimeUtil;
import com.software.ttsl.model.AddContactData;
import com.software.ttsl.model.AddLeadData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class ContactListFragmentAdapter extends RecyclerView.Adapter<ContactListFragmentAdapter.ContactListViewHolder> {

    public static final String TAG = LeadListFragmentAdapter.class.getName();

    Context context;
    List<AddContactData> addContactDataList;
    private ItemClickListener listener;

    public ContactListFragmentAdapter(Context context, List<AddContactData> addContactDataList, ItemClickListener listener) {
        this.context = context;
        this.addContactDataList = addContactDataList;
        this.listener = listener;
    }

    @Override
    public ContactListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_contact_list, parent, false);
        return new ContactListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactListViewHolder holder, int position) {
        AddContactData addContactData = addContactDataList.get(position);

        holder.textViewContactAccountName.setText(addContactData.getAccountName());
        holder.textViewContactName.setText(DateAndTimeUtil.firstLatterUpper((addContactData.getFirstName()) + " " + DateAndTimeUtil.firstLatterUpper(addContactData.getLastName())).trim());
        if (addContactData.isHeaderShow()) {

            Log.e(TAG, position + " " + addContactData.isHeaderShow());
            holder.textViewContactHeader.setVisibility(View.VISIBLE);
            holder.viewHeader.setVisibility(View.VISIBLE);
            holder.textViewContactHeader.setText(DateAndTimeUtil.longToDate(addContactData.getCreatedDate()));
        } else {
            Log.e(TAG, position + " " + addContactData.isHeaderShow());
            holder.textViewContactHeader.setVisibility(View.GONE);
            holder.viewHeader.setVisibility(View.GONE);


        }



    }

    private String longToDateConverter(long dateLong){
        Date date=new Date(dateLong);
        SimpleDateFormat df2 = new SimpleDateFormat("dd/mm/yyyy");
        return  df2.format(date);

    }

    @Override
    public int getItemCount() {
        return addContactDataList.size();
    }


    public class ContactListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView textViewContactHeader;
        View viewHeader;
        TextView textViewContactName;
        TextView textViewContactAccountName;


        public ContactListViewHolder(View itemView) {
            super(itemView);
            textViewContactHeader = (TextView) itemView.findViewById(R.id.tv_contact_header);
            viewHeader = (View) itemView.findViewById(R.id.view_header);
            textViewContactName = (TextView) itemView.findViewById(R.id.tv_contact_name);
            textViewContactAccountName = (TextView) itemView.findViewById(R.id.tv_contact_account_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition());
        }
    }

}
