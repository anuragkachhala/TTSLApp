package com.software.ttsl;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.software.ttsl.CustomView.CustomProgressBar;
import com.software.ttsl.Request.AccountDataModel;
import com.software.ttsl.Request.CallDataModel;
import com.software.ttsl.Request.DealDataModel;
import com.software.ttsl.Request.LeadDataModel;
import com.software.ttsl.Response.FormDropDown.DropDownDataModel;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.DialogUtitlity;
import com.software.ttsl.Utils.EmployConstantUtil;
import com.software.ttsl.Utils.SessionManager;
import com.software.ttsl.model.AddContactData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownloadDataActivity extends AppCompatActivity {

    private static final String TAG = DownloadDataActivity.class.getName();

    SessionManager sessionManager;

    private List<DropDownDataModel> dropDownDataModelList;


    @BindView(R.id.progress_bar_2)
    CustomProgressBar customProgressBar;

    private DataBaseAdapter dataBaseAdapter;

    private int progress = 0;

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 0) {
                if (progress < 100) {
                    progress++;
                    customProgressBar.setProgress(progress);

                    customProgressBar.setOnProgressListener(new CustomProgressBar.OnProgressListener() {

                        @Override
                        public void progressToComplete() {
                            // Here after the completion of the processing
                        }
                    });


                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_data);
        ButterKnife.bind(this);
        SessionManager.setContext(getApplicationContext());
        sessionManager = SessionManager.getInstance();
        dataBaseAdapter = new DataBaseAdapter(this);
        new Thread(new Runnable() {

            @Override
            public void run() {

                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(100);

                        mHandler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();



        getStatusDropDown();

       // getAllLead();


    }

    private void getStatusDropDown(){

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<DropDownDataModel>> listCall = apiInterface.getStatusDropDown("Bearer "+sessionManager.getAccessToken());
        Log.i(TAG, "inside getAll lead from server");
        listCall.enqueue(new Callback<List<DropDownDataModel>>() {
            @Override
            public void onResponse(Call<List<DropDownDataModel>> call, Response<List<DropDownDataModel>> response) {
                DialogUtitlity.hideLoading();
                Log.i(TAG, "inside getAll lead from server");
                int statusCode = response.code();
                Log.i(TAG, String.valueOf(statusCode));
                if (statusCode == 200) {
                    if (response.body() instanceof List) {
                        dropDownDataModelList= response.body();
                        dataBaseAdapter.setDropDown("Status",dropDownDataModelList);
                        //getAllAccount();
                        getIndustryDropDown();

                    }

                }

            }

            @Override
            public void onFailure(Call<List<DropDownDataModel>> call, Throwable t) {
                 DialogUtitlity.hideLoading();
            }
        });

    }



    private void getIndustryDropDown(){

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<DropDownDataModel>> listCall = apiInterface.getIndustryDropDown("Bearer "+sessionManager.getAccessToken());
        Log.i(TAG, "inside getAll industry from server");
        listCall.enqueue(new Callback<List<DropDownDataModel>>() {
            @Override
            public void onResponse(Call<List<DropDownDataModel>> call, Response<List<DropDownDataModel>> response) {
                DialogUtitlity.hideLoading();
                Log.i(TAG, "inside getAll industry from server");
                int statusCode = response.code();
                Log.i(TAG, String.valueOf(statusCode));
                if (statusCode == 200) {
                    if (response.body() instanceof List) {
                        dropDownDataModelList= response.body();
                        dataBaseAdapter.setDropDown(EmployConstantUtil.KEY_INDUSTRY,dropDownDataModelList);
                        //getAllAccount();

                    }

                }

            }

            @Override
            public void onFailure(Call<List<DropDownDataModel>> call, Throwable t) {
                DialogUtitlity.hideLoading();
            }
        });

    }


    private void getAllLead() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<LeadDataModel>> listCall = apiInterface.getLeadData(0);
        Log.i(TAG, "inside getAll lead from server");
        listCall.enqueue(new Callback<List<LeadDataModel>>() {
            @Override
            public void onResponse(Call<List<LeadDataModel>> call, Response<List<LeadDataModel>> response) {
                DialogUtitlity.hideLoading();
                Log.i(TAG, "inside getAll lead from server");
                int statusCode = response.code();
                Log.i(TAG, String.valueOf(statusCode));
                if (statusCode == 200) {
                    if (response.body() instanceof List) {
                        List<LeadDataModel> leadDataModelList = response.body();
                        dataBaseAdapter.setAllLead(leadDataModelList);
                        getAllAccount();

                    }

                }

            }

            @Override
            public void onFailure(Call<List<LeadDataModel>> call, Throwable t) {

            }
        });
    }


    private void getAllAccount() {
        DialogUtitlity.showLoading(DownloadDataActivity.this);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<AccountDataModel>> listCall = apiInterface.getAllAccount(0);
        Log.i(TAG, "inside getAll Account from server");
        listCall.enqueue(new Callback<List<AccountDataModel>>() {
            @Override
            public void onResponse(Call<List<AccountDataModel>> call, Response<List<AccountDataModel>> response) {
                DialogUtitlity.hideLoading();
                Log.i(TAG, "inside getAll Account from server");
                int statusCode = response.code();
                Log.i(TAG, String.valueOf(statusCode));
                if (statusCode == 200) {
                    if (response.body() instanceof List) {
                        List<AccountDataModel> accountDataModelList = response.body();
                        dataBaseAdapter.setAllAccounts(accountDataModelList);
                        getAllDeal();


                    }


                }

            }

            @Override
            public void onFailure(Call<List<AccountDataModel>> call, Throwable t) {

            }
        });


    }


    private void getAllDeal() {
        DialogUtitlity.showLoading(DownloadDataActivity.this);
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

                        getAllCall();


                    }
                }
            }

            @Override
            public void onFailure(Call<List<DealDataModel>> call, Throwable t) {

            }
        });
    }


    private void getAllContact() {
        DialogUtitlity.showLoading(DownloadDataActivity.this);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<AddContactData>> listCall = apiInterface.getAllContact(0);
        listCall.enqueue(new Callback<List<AddContactData>>() {
            @Override
            public void onResponse(Call<List<AddContactData>> call, Response<List<AddContactData>> response) {
                DialogUtitlity.hideLoading();
                Log.i(TAG, "inside getAll contact from server");
                int statusCode = response.code();
                Log.i(TAG, String.valueOf(statusCode));
                if (statusCode == 200) {
                    if (response.body() instanceof List) {
                        List<AddContactData> contactDataList = response.body();
                        dataBaseAdapter.setAllContacts(contactDataList);

                    }
                    startActivity(new Intent(DownloadDataActivity.this, EmployeeHomeActivity.class));
                }
                if (statusCode == 204) {
                    startActivity(new Intent(DownloadDataActivity.this, EmployeeHomeActivity.class));
                }
            }

            @Override
            public void onFailure(Call<List<AddContactData>> call, Throwable t) {

            }
        });
    }


    private void getAllCall() {
        DialogUtitlity.showLoading(DownloadDataActivity.this);
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
                        getAllContact();


                    }
                }
            }

            @Override
            public void onFailure(Call<List<CallDataModel>> call, Throwable t) {

            }
        });
    }



}
