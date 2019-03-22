package com.software.ttsl.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.software.ttsl.Adapter.SalesBudgetFragmentAdapter;
import com.software.ttsl.Interface.ItemClickListener;
import com.software.ttsl.R;
import com.software.ttsl.SalesDetailActivity;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.EmployConstantUtil;
import com.software.ttsl.model.SalesBudgetModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddSalesFragment extends Fragment implements ItemClickListener {

    private final static String TAG = AddSalesFragment.class.getName();


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private long salesId;

    private DataBaseAdapter dataBaseAdapter;
    private SalesBudgetFragmentAdapter salesBudgetFragmentAdapter;
    private List<SalesBudgetModel> salesBudgetModelsList= new ArrayList<>();
    private List<SalesBudgetModel> list;
    private String year;
    private String yearChanged;



    public AddSalesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_sales, container, false);
        ButterKnife.bind(this,view);

        dataBaseAdapter = new DataBaseAdapter(getContext());
        salesBudgetFragmentAdapter= new SalesBudgetFragmentAdapter(getContext(),salesBudgetModelsList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(salesBudgetFragmentAdapter);
        recyclerView.setHasFixedSize(true);

        updateUI();

        return  view;
    }

    private void updateUI() {

        salesBudgetModelsList.clear();
        list = dataBaseAdapter.getAllSalesBudget();
        salesBudgetModelsList.addAll(list);
        if(!list.isEmpty()) {
            year = salesBudgetModelsList.get(0).getSalesYear();
            for (SalesBudgetModel salesBudgetModel: salesBudgetModelsList) {
                yearChanged = salesBudgetModel.getSalesYear();
                if (!year.equals(yearChanged)) {
                    salesBudgetModel.setHeader(true);
                    year = yearChanged;
                }
            }

            salesBudgetModelsList.get(0).setHeader(true);

        }

        salesBudgetFragmentAdapter.notifyDataSetChanged();


    }

    @Override
    public void onItemClick(int position) {
        salesId = salesBudgetModelsList.get(position).getSalesId();
        Log.e(TAG,"sales budget id IN add sales FRAGMENT  "+salesId);
        OpenSalesDetailActivity();
    }

    private void OpenSalesDetailActivity() {
        Intent intent = new Intent(getContext(), SalesDetailActivity.class);
        intent.putExtra(EmployConstantUtil.KEY_SALES_BUDGET_ID,salesId);
        startActivityForResult(intent,1000);


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode ==RESULT_OK){
            switch (requestCode){
                case 1000:
                    //TODO get intent data
                    updateUI();
                    salesBudgetFragmentAdapter.notifyDataSetChanged();
                    break;





            }
        }
    }

}
