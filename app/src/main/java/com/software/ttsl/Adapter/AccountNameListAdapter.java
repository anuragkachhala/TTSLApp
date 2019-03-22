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
import com.software.ttsl.Utils.DateAndTimeUtil;

import java.util.List;

public class AccountNameListAdapter extends RecyclerView.Adapter<AccountNameListAdapter.AccountNameListViewHolder> {
    public static final String TAG = AccountNameListAdapter.class.getName();

    private Context context;
    private List<AccountDataModel> accountDataModelList;
    private ItemClickListener listener;


    public AccountNameListAdapter(Context context, List<AccountDataModel> accountDataModelList, ItemClickListener listener) {
        this.context = context;
        this.accountDataModelList = accountDataModelList;
        this.listener = listener;
    }

    @Override
    public AccountNameListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_account_name_list, parent, false);
        return new AccountNameListViewHolder(view);

    }

    @Override
    public void onBindViewHolder(AccountNameListViewHolder holder, int position) {
        AccountDataModel accountDataModel = accountDataModelList.get(position);
        holder.textViewAccountName.setText(DateAndTimeUtil.firstLatterUpper(accountDataModel.getAccountName()));



    }

    @Override
    public int getItemCount() {
        return accountDataModelList.size();
    }

    public class AccountNameListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
       TextView textViewAccountName;

        public AccountNameListViewHolder(View itemView) {
            super(itemView);

            textViewAccountName =(TextView)itemView.findViewById(R.id.tv_account_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition());
        }
    }
}
