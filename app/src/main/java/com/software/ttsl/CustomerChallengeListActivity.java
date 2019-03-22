package com.software.ttsl;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.software.ttsl.Fragment.CustomerChallengeFragment;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.DialogUtitlity;
import com.software.ttsl.model.CustomerChallengeModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerChallengeListActivity extends AppCompatActivity implements View.OnClickListener {


    public static final String TAG = CustomerChallengeListActivity.class.getName();

    public static final int REQUEST_CODE_CUSTOMER_CHALLENGE = 1000;


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    CustomerChallengeFragment customerChallengeListFragment;
    DataBaseAdapter dataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_challange_list);
        ButterKnife.bind(this);


        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dataBaseAdapter = new DataBaseAdapter(this);
        if (dataBaseAdapter.getAllCustomerChallenges().size() == 0) {
            //getAllCustomerChallenge();
        }

        setClickListener();

        if (savedInstanceState == null) {
            // Let's first dynamically add a fragment into a frame container
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.fragment_container, new CustomerChallengeFragment(), "ADD_CUSTOMER_CHALLENGE").
                    commit();

        }
        // Now later we can lookup the fragment by tag
        customerChallengeListFragment = (CustomerChallengeFragment) getSupportFragmentManager().findFragmentByTag("ADD_CUSTOMER_CHALLENGE");


    }

    private void getAllCustomerChallenge() {
        DialogUtitlity.showLoading(CustomerChallengeListActivity.this);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<CustomerChallengeModel>> listCall = apiInterface.getAllCustomer(0);
        listCall.enqueue(new Callback<List<CustomerChallengeModel>>() {
            @Override
            public void onResponse(Call<List<CustomerChallengeModel>> call, Response<List<CustomerChallengeModel>> response) {
                DialogUtitlity.hideLoading();
                Log.i(TAG, "inside getAll customer challenge from server");
                int statusCode = response.code();
                Log.i(TAG, String.valueOf(statusCode));
                if (statusCode == 200) {
                    if (response.body() instanceof List) {
                        List<CustomerChallengeModel> customerChallengeModelList = response.body();
                        dataBaseAdapter.setAllCustomerChallenge(customerChallengeModelList);


                    }
                }
            }

            @Override
            public void onFailure(Call<List<CustomerChallengeModel>> call, Throwable t) {

            }
        });
    }

    private void setClickListener() {
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startActivityForResult(new Intent(CustomerChallengeListActivity.this, AddCustomerChallenge.class), REQUEST_CODE_CUSTOMER_CHALLENGE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_CUSTOMER_CHALLENGE:
                    //TODO get intent data
                    customerChallengeListFragment = (CustomerChallengeFragment) getSupportFragmentManager().findFragmentByTag("ADD_CUSTOMER_CHALLENGE");
                    customerChallengeListFragment.onActivityResult(requestCode, resultCode, data);
                    break;
            }
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
