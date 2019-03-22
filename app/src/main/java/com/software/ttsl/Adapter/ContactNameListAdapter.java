package com.software.ttsl.Adapter;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.software.ttsl.Interface.ItemClickListener;
import com.software.ttsl.R;
import com.software.ttsl.Utils.DateAndTimeUtil;
import com.software.ttsl.model.AddContactData;
import java.util.List;



public class ContactNameListAdapter extends RecyclerView.Adapter<ContactNameListAdapter.ContactNameListViewHolder> {
    public static final String TAG = AccountNameListAdapter.class.getName();

    private Context context;
    private List<AddContactData> addContactDataList;
    private ItemClickListener listener;


    public ContactNameListAdapter (Context context, List<AddContactData> addContactDataList, ItemClickListener listener) {
        this.context = context;
        this.addContactDataList = addContactDataList;
        this.listener = listener;
    }

    @Override
    public ContactNameListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_account_name_list, parent, false);
        return new ContactNameListViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ContactNameListViewHolder holder, int position) {
        AddContactData addContactData= addContactDataList.get(position);
        holder.textViewAccountName.setText((DateAndTimeUtil.firstLatterUpper(addContactDataList.get(position).getFirstName())+ " " +
                DateAndTimeUtil.firstLatterUpper(addContactDataList.get(position).getLastName())).trim());


    }

    @Override
    public int getItemCount() {
        return addContactDataList.size();
    }

    public class ContactNameListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewAccountName;

        public ContactNameListViewHolder(View itemView) {
            super(itemView);

            textViewAccountName = (TextView) itemView.findViewById(R.id.tv_account_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition());
        }
    }
}