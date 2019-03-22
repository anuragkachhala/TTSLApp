package com.software.ttsl;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.software.ttsl.Fragment.AddAccountFragment;
import com.software.ttsl.Fragment.AddSalesFragment;
import com.software.ttsl.Request.CallDataModel;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.DialogUtitlity;
import com.software.ttsl.Utils.EmployConstantUtil;
import com.software.ttsl.model.SalesBudgetModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSalesBudgetListActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = AddSalesBudgetListActivity.class.getName();

    public static final int REQUEST_CODE_ADD_SALES_BUDGET = 1000;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    private AddSalesFragment addSalesFragment;
    private DataBaseAdapter dataBaseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sales_budget_list);

        ButterKnife.bind(this);


        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dataBaseAdapter = new DataBaseAdapter(this);
        setClickListener();

        getSalesBudget();
        if (savedInstanceState == null) {
            // Let's first dynamically add a fragment into a frame container
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.fragment_container, new AddSalesFragment(), EmployConstantUtil.TAG_SALES).
                    commit();

        }
        // Now later we can lookup the fragment by tag
        addSalesFragment = (AddSalesFragment) getSupportFragmentManager().findFragmentByTag(EmployConstantUtil.TAG_SALES);


    }

    private void getSalesBudget(){
        DialogUtitlity.showLoading(AddSalesBudgetListActivity.this);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<SalesBudgetModel>> listCall = apiInterface.getAllSalesBudget(0);
        listCall.enqueue(new Callback<List<SalesBudgetModel>>() {
            @Override
            public void onResponse(Call<List<SalesBudgetModel>> call, Response<List<SalesBudgetModel>> response) {
                DialogUtitlity.hideLoading();
                Log.i(TAG, "inside getAll sales budget from server");
                int statusCode = response.code();
                Log.i(TAG, String.valueOf(statusCode));
                if (statusCode == 200) {
                    if (response.body() instanceof List) {
                        List<SalesBudgetModel> salesBudgetModelList= response.body();
                        dataBaseAdapter.setAllSalesBudget(salesBudgetModelList);


                    }
                }
            }

            @Override
            public void onFailure(Call<List<SalesBudgetModel>> call, Throwable t) {
                DialogUtitlity.hideLoading();
            }
        });

    }

    private void setClickListener() {
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_ADD_SALES_BUDGET:
                    //TODO get intent data
                    addSalesFragment = (AddSalesFragment) getSupportFragmentManager().findFragmentByTag(EmployConstantUtil.TAG_SALES);
                    addSalesFragment.onActivityResult(requestCode, resultCode, data);
                    break;

            }
        }
    }


    @Override
    public void onClick(View view) {
        startActivityForResult(new Intent(AddSalesBudgetListActivity.this, AddSalesBudgetActivity.class), REQUEST_CODE_ADD_SALES_BUDGET);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
