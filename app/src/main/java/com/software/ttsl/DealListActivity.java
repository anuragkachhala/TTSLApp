package com.software.ttsl;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.software.ttsl.Fragment.AddDealFragment;
import com.software.ttsl.Request.DealDataModel;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.DialogUtitlity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DealListActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_CODE_ADD_DEAL = 1000;
    private static final String TAG = AccountListActivity.class.getName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    AddDealFragment addDealFragment;
    DataBaseAdapter dataBaseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_list);


        ButterKnife.bind(this);


        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dataBaseAdapter = new DataBaseAdapter(this);

        setClickListener();
        getAllDeal();
        if (savedInstanceState == null) {
            // Let's first dynamically add a fragment into a frame container
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.fragment_container, new AddDealFragment(), "ADD_DEAL").
                    commit();

        }
        // Now later we can lookup the fragment by tag
        addDealFragment = (AddDealFragment) getSupportFragmentManager().findFragmentByTag("ADD_DEAL");


    }

    private void getAllDeal() {
        DialogUtitlity.showLoading(DealListActivity.this);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<DealDataModel>> listCall = apiInterface.getAllDeal(0);
        listCall.enqueue(new Callback<List<DealDataModel>>() {
            @Override
            public void onResponse(Call<List<DealDataModel>> call, Response<List<DealDataModel>> response) {
                DialogUtitlity.hideLoading();
                Log.i(TAG, "inside getAll lead from server");
                int statusCode = response.code();
                Log.i(TAG, String.valueOf(statusCode));
                if (statusCode == 200) {
                    if (response.body() instanceof List) {
                        List<DealDataModel> dealDataModelList = response.body();
                        dataBaseAdapter.setAllDeal(dealDataModelList);


                    }
                }
            }

            @Override
            public void onFailure(Call<List<DealDataModel>> call, Throwable t) {
                DialogUtitlity.hideLoading();

            }
        });
    }

    private void setClickListener() {
        floatingActionButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        startActivityForResult(new Intent(DealListActivity.this, AddDealActivity.class), REQUEST_CODE_ADD_DEAL);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_ADD_DEAL:
                    //TODO get intent data
                    addDealFragment = (AddDealFragment) getSupportFragmentManager().findFragmentByTag("ADD_DEAL");
                    addDealFragment.onActivityResult(requestCode, resultCode, data);
                    break;
            }
        }
    }

    @Override
    public void onResume() {

        super.onResume();
        addDealFragment = (AddDealFragment) getSupportFragmentManager().findFragmentByTag("ADD_DEAL");
        addDealFragment.updateUI();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
