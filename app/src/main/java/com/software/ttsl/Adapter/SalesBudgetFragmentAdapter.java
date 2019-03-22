package com.software.ttsl.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.software.ttsl.Interface.ItemClickListener;
import com.software.ttsl.R;
import com.software.ttsl.model.SalesBudgetModel;

import java.util.List;

public class SalesBudgetFragmentAdapter extends RecyclerView.Adapter<SalesBudgetFragmentAdapter.SaleBudgetFramendViewHolder> {
    private static final String TAG = SalesBudgetFragmentAdapter.class.getName();

    private Context context;
    private List<SalesBudgetModel> salesBudgetModelList;
    private ItemClickListener listener;
    private String year;
    private String yearChanged;


    public SalesBudgetFragmentAdapter(Context context, List<SalesBudgetModel> salesBudgetModelList, ItemClickListener listener) {
        this.context = context;
        this.salesBudgetModelList = salesBudgetModelList;
        this.listener = listener;
    }

    @Override
    public SaleBudgetFramendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_sale_budget, parent, false);
        return new SaleBudgetFramendViewHolder(view);
    }


    @Override
    public void onBindViewHolder(SaleBudgetFramendViewHolder holder, int position) {
        SalesBudgetModel salesBudgetModel = salesBudgetModelList.get(position);
        year = salesBudgetModel.getSalesYear();
        if (salesBudgetModel.isHeader()) {
            holder.textViewSalesBudegetHeader.setText(year);
           // holder.viewHeader.setVisibility(View.VISIBLE);
           // holder.textViewSalesSector.setText(salesBudgetModel.getSalesSector());
           // holder.textViewSalesman.setText(salesBudgetModel.getSalesman());
            holder.textViewSalesBudegetHeader.setVisibility(View.VISIBLE);
        } else {
            holder.textViewSalesBudegetHeader.setVisibility(View.GONE);
            //holder.textViewSalesSector.setText(salesBudgetModel.getSalesSector());
            //holder.textViewSalesman.setText(salesBudgetModel.getSalesman());


        }

    }
        @Override
        public int getItemCount () {
            return salesBudgetModelList.size();
        }

        public class SaleBudgetFramendViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView textViewSalesBudegetHeader;
            View viewHeader;
            TextView textViewSalesSector;
            TextView textViewSalesman;

            public SaleBudgetFramendViewHolder(View itemView) {
                super(itemView);
                textViewSalesBudegetHeader = (TextView) itemView.findViewById(R.id.tv_sales_budget_header);
                viewHeader = (View) itemView.findViewById(R.id.view_header);
                textViewSalesman = (TextView) itemView.findViewById(R.id.tv_budget_sector);
                textViewSalesSector = (TextView) itemView.findViewById(R.id.tv_budget_salesman);
                itemView.setOnClickListener(this);

            }

            @Override
            public void onClick(View view) {
                listener.onItemClick(getAdapterPosition());
            }
        }
    }
