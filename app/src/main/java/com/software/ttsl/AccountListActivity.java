package com.software.ttsl;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.software.ttsl.Fragment.AddAccountFragment;
import com.software.ttsl.Fragment.AddContactFragment;
import com.software.ttsl.Fragment.AddLeadFragment;
import com.software.ttsl.Request.AccountDataModel;
import com.software.ttsl.Request.LeadDataModel;
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

public class AccountListActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG= AccountListActivity.class.getName();

    public static final  int REQUEST_CODE_ADD_LEAD=1000;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    private AddAccountFragment addAccountFragment;
    private DataBaseAdapter dataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_list);

        ButterKnife.bind(this);


        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dataBaseAdapter = new DataBaseAdapter(this);
        setClickListener();

        if(dataBaseAdapter.getAllAccounts().size()==0) {
           // getAllAccount();
        }

        if (savedInstanceState == null) {
            // Let's first dynamically add a fragment into a frame container
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.fragment_container, new AddAccountFragment(), "ADD_ACCOUNT").
                    commit();

        }
        // Now later we can lookup the fragment by tag
        addAccountFragment = (AddAccountFragment) getSupportFragmentManager().findFragmentByTag("ADD_ACCOUNT");


    }

    private void getAllAccount(){
        DialogUtitlity.showLoading(AccountListActivity.this);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<AccountDataModel>> listCall = apiInterface.getAllAccount(0);
        Log.i(TAG, "inside getAll lead from server");
        listCall.enqueue(new Callback<List<AccountDataModel>>() {
            @Override
            public void onResponse(Call<List<AccountDataModel>> call, Response<List<AccountDataModel>> response) {
                DialogUtitlity.hideLoading();
                Log.i(TAG, "inside getAll lead from server");
                int statusCode = response.code();
                Log.i(TAG, String.valueOf(statusCode));
                if (statusCode == 200) {
                    if (response.body() instanceof List) {
                        List<AccountDataModel> accountDataModelList= response.body();
                        dataBaseAdapter.setAllAccounts(accountDataModelList);


                    }
                }
            }

            @Override
            public void onFailure(Call<List<AccountDataModel>> call, Throwable t) {

            }
        });
    }

    private void setClickListener() {
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startActivityForResult(new Intent(AccountListActivity.this, AddAccountActivity.class), REQUEST_CODE_ADD_LEAD);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_ADD_LEAD:
                    //TODO get intent data
                    addAccountFragment = (AddAccountFragment) getSupportFragmentManager().findFragmentByTag("ADD_ACCOUNT");
                    addAccountFragment.onActivityResult(requestCode, resultCode, data);
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
