package com.software.ttsl;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.software.ttsl.Request.CallDataModel;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.DialogUtitlity;
import com.software.ttsl.Utils.EmployConstantUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallListActivity extends AppCompatActivity implements View.OnClickListener {


    public static final int REQUEST_CODE_ADD_CALL = 1000;
    private static final String TAG = AccountListActivity.class.getName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;


    private AddCallFragment addCallFragment;
    private DataBaseAdapter dataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_list);

        ButterKnife.bind(this);


        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dataBaseAdapter = new DataBaseAdapter(this);

        setClickListener();
        if (dataBaseAdapter.getAllCall().size() == 0) {
            getAllCall();
        }


        if (savedInstanceState == null) {
            // Let's first dynamically add a fragment into a frame container
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.fragment_container, new AddCallFragment(), EmployConstantUtil.TAG_CALL).
                    commit();

        }
        // Now later we can lookup the fragment by tag
        addCallFragment = (AddCallFragment) getSupportFragmentManager().findFragmentByTag(EmployConstantUtil.TAG_CALL);


    }

    private void getAllCall() {
        DialogUtitlity.showLoading(CallListActivity.this);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<CallDataModel>> listCall = apiInterface.getAllCall(0);
        listCall.enqueue(new Callback<List<CallDataModel>>() {
            @Override
            public void onResponse(Call<List<CallDataModel>> call, Response<List<CallDataModel>> response) {
                DialogUtitlity.hideLoading();
                Log.i(TAG, "inside getAll call from server");
                int statusCode = response.code();
                Log.i(TAG, String.valueOf(statusCode));
                if (statusCode == 200) {
                    if (response.body() instanceof List) {
                        List<CallDataModel> callDataModels = response.body();
                        dataBaseAdapter.setAllCall(callDataModels);


                    }
                }
            }

            @Override
            public void onFailure(Call<List<CallDataModel>> call, Throwable t) {
                DialogUtitlity.hideLoading();


            }
        });
    }

    private void setClickListener() {
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startActivityForResult(new Intent(CallListActivity.this, AddCallActivity.class), REQUEST_CODE_ADD_CALL);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_ADD_CALL:
                    //TODO get intent data
                    addCallFragment = (AddCallFragment) getSupportFragmentManager().findFragmentByTag(EmployConstantUtil.TAG_CALL);
                    addCallFragment.onActivityResult(requestCode, resultCode, data);
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
