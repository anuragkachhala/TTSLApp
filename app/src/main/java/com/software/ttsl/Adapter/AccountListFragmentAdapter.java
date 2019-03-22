package com.software.ttsl.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.software.ttsl.Interface.ItemClickListener;
import com.software.ttsl.R;
import com.software.ttsl.Request.AccountDataModel;
import com.software.ttsl.model.AddContactData;

import java.util.List;

public class AccountListFragmentAdapter extends RecyclerView.Adapter<AccountListFragmentAdapter.AccountListViewHolder> {

    public static final String TAG = AccountListFragmentAdapter.class.getName();

    private Context context;
    private List<AccountDataModel> addContactDataList;
    private ItemClickListener listener;
    private String firstLatter;
    private String firstLatterChanged;

    public AccountListFragmentAdapter(Context context, List<AccountDataModel> addContactDataList, ItemClickListener listener) {
        this.context = context;
        this.addContactDataList = addContactDataList;
        this.listener = listener;
    }

    @Override
    public AccountListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_account_list, parent, false);
        return new AccountListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AccountListViewHolder holder, int position) {
        AccountDataModel accountDataModel = addContactDataList.get(position);
        firstLatter = accountDataModel.getAccountName().substring(0,1).toUpperCase();
        if(accountDataModel.isHeaderShow())
        {
            holder.textViewAccountHeader.setText(firstLatter);
            holder.viewHeader.setVisibility(View.VISIBLE);
            holder.textViewAccountName.setText(accountDataModel.getAccountName());
            holder.textViewAccountHeader.setVisibility(View.VISIBLE);
        }else {
            holder.textViewAccountHeader.setVisibility(View.GONE);
            holder.textViewAccountName.setText(accountDataModel.getAccountName());
        }
    }

    @Override
    public int getItemCount() {
        return addContactDataList.size();
    }

    public class AccountListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewAccountHeader;
        View viewHeader;
        TextView textViewAccountName;

        public AccountListViewHolder(View itemView) {
            super(itemView);
            textViewAccountHeader = (TextView) itemView.findViewById(R.id.tv_account_header);
            viewHeader = (View) itemView.findViewById(R.id.view_header);
            textViewAccountName = (TextView) itemView.findViewById(R.id.tv_account_name);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {

            listener.onItemClick(getAdapterPosition());

        }
    }
}
