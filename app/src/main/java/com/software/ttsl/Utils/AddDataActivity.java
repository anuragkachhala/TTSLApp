package com.software.ttsl.Utils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.software.ttsl.R;
import com.software.ttsl.Response.VesselListResponse;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.SearchVesselActivity;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.model.PortData;
import com.software.ttsl.model.SalesUtilityData;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDataActivity extends AppCompatActivity implements View.OnClickListener, Callback<List<VesselListResponse>> {


    private EditText mPortCode, mState, mCountry;
    private Button mAddData,mNextScreen;
    private PortData portData;

    SharedPrefUtil sharedPrefUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        SharedPrefUtil.setContext(this);
        mPortCode = (EditText) findViewById(R.id.et_port_code);
        mState = (EditText) findViewById(R.id.et_state);
        mCountry = (EditText) findViewById(R.id.et_country);
        mAddData = (Button) findViewById(R.id.btn_add_data);
        mNextScreen = (Button) findViewById(R.id.btn_next_screen);

        mAddData.setOnClickListener(this);
        mNextScreen.setOnClickListener(this);
        portData = new PortData();

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_add_data:
                // AddDataToDataBase();
                //AddBLList();
                //AddSailingScheduleData();
                //addVesselList();
                // addPendingInvoice();
                // getPortData();

                getLeadRating();

                break;

            case R.id.btn_next_screen:
                startActivity(new Intent(AddDataActivity.this, SearchVesselActivity.class));
                break;
        }

    }

    private void getLeadRating() {
        DialogUtitlity.showLoading(AddDataActivity.this);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<SalesUtilityData>> listCall = apiInterface.getLeadRating();
        listCall.enqueue(new Callback<List<SalesUtilityData>>() {
            @Override
            public void onResponse(Call<List<SalesUtilityData>> call, Response<List<SalesUtilityData>> response) {
                DialogUtitlity.hideLoading();
                int statusCode = response.code();
                if (statusCode == 200) {
                    if (response.body() instanceof List) {
                    List<SalesUtilityData> salesRatingList = response.body();
                        Gson gson = new Gson();
                        String rating = gson.toJson(salesRatingList);
                        sharedPrefUtil = SharedPrefUtil.getInstance();
                        sharedPrefUtil.setLeadData(SharedPrefUtil.KEY_RATING,rating);




                    }
                    }

            }

            @Override
            public void onFailure(Call<List<SalesUtilityData>> call, Throwable t) {

            }
        });

    }

    private void addVesselList() {
        DialogUtitlity.showLoading(AddDataActivity.this);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<VesselListResponse>> listCall = apiInterface.getVesselList();
        listCall.enqueue(this);

    }

    @Override
    public void onResponse(Call<List<VesselListResponse>> call, Response<List<VesselListResponse>> response) {
        DialogUtitlity.hideLoading();
        int statusCode = response.code();
        if (statusCode == 200) {
            if (response.body() instanceof List) {
                List<VesselListResponse> vesselListResponseList = response.body();
                DataBaseAdapter dataBaseAdapter = new DataBaseAdapter(this);
                dataBaseAdapter.openDataBase();
                dataBaseAdapter.addVessels(vesselListResponseList);

            }

        }

    }

    @Override
    public void onFailure(Call<List<VesselListResponse>> call, Throwable t) {
    }


   /* private void AddSailingScheduleData() {
        SailingScheduleRequest sailingScheduleRequest = new SailingScheduleRequest();
        sailingScheduleRequest.setVesselId("250");
        sailingScheduleRequest.setPodId("8195");
        sailingScheduleRequest.setPolId("8195");
        DialogUtitlity.showLoading(AddDataActivity.this);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<SailingScheduleResponse>> listCall = apiInterface.getSailingSchedule("2017-11-18","2017-11-18","2017-11-19","2017-11-19",sailingScheduleRequest);
        listCall.enqueue(this);

    }

    @Override
    public void onResponse(Call<List<SailingScheduleResponse>> call, Response<List<SailingScheduleResponse>> response) {

        DialogUtitlity.hideLoading();
        int statusCode = response.code();
        if(statusCode==200){
            if(response.body() instanceof  List){
                List<SailingScheduleResponse> billOfLadingList= response.body();
                DataBaseAdapter dataBaseAdapter = new DataBaseAdapter(this);
                dataBaseAdapter.openDataBase();
                *//*dataBaseAdapter.addBillOfLadingList(billOfLadingList);
     *//*
            }

        }


    }

    @Override
    public void onFailure(Call<List<SailingScheduleResponse>> call, Throwable t) {

    }
*/




    /*private void addPendingInvoice() {
        DialogUtitlity.showLoading(AddDataActivity.this);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<PendingInvoiceResponce>> listCall = apiInterface.getPendingInvoices();
        listCall.enqueue(this);
    }*/

   /* private void AddBLList(){
        DialogUtitlity.showLoading(AddDataActivity.this);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<BillListData>> listCall = apiInterface.getBLLists("C","65488");
        listCall.enqueue(this);

    }*/

   /* @Override
    public void onResponse(Call<List<BillListData>> call, Response<List<BillListData>> response) {
        DialogUtitlity.hideLoading();
        int statusCode = response.code();
        if(statusCode==200){
            if(response.body() instanceof  List){
                List<BillListData> billOfLadingList= response.body();
                DataBaseAdapter dataBaseAdapter = new DataBaseAdapter(this);
                dataBaseAdapter.openDataBase();
                dataBaseAdapter.addBillOfLadingList(billOfLadingList);

            }

        }

    }

    @Override
    public void onFailure(Call<List<BillListData>> call, Throwable t) {

    }
*/

    /*private void getPortData() {
        DialogUtitlity.showLoading((AddDataActivity.this));
        ApiInterface apiServices =ApiClient.getClient().create(ApiInterface.class);
        Call<Map<String,List<PortDataResponse>>> portDataResponse = apiServices.getPortData();
        portDataResponse.enqueue(this);
    }*/

    /*private void AddDataToDataBase() {

        DialogUtitlity.showLoading(AddDataActivity.this);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<TrackingNoConstraintResponse>> trackingNoConstraint = apiService.getTrackingNoConstraint();
        trackingNoConstraint.enqueue(this);


    }
*/
  /*  @Override
    public void onResponse(Call<List<PendingInvoiceResponce>> call, Response<List<PendingInvoiceResponce>> response) {
      DialogUtitlity.hideLoading();
      int statusCode = response.code();
      if(statusCode==200){
          if(response.body() instanceof  List){
              List<PendingInvoiceResponce> pendingInvoiceResponceList = response.body();
              DataBaseAdapter dataBaseAdapter = new DataBaseAdapter(this);
              dataBaseAdapter.openDataBase();
              dataBaseAdapter.addPendingInvoices(pendingInvoiceResponceList);

          }

        }

    }

    @Override
    public void onFailure(Call<List<PendingInvoiceResponce>> call, Throwable t) {

    }*/
    /*@Override
    public void onResponse(Call<Map<String, List<PortDataResponse>>> call, Response<Map<String, List<PortDataResponse>>> response) {
    DialogUtitlity.hideLoading();
    int statusCode = response.code();
    if(response.body() instanceof Map){
        Map<String, List<PortDataResponse>> listPortData = response.body();
        DataBaseAdapter dataBaseAdapter = new DataBaseAdapter(this);
        dataBaseAdapter.openDataBase();
        dataBaseAdapter.addPortData(listPortData);
        startActivity(new Intent(AddDataActivity.this,SelectPortActivity.class));
    }

    }

    @Override
    public void onFailure(Call<Map<String, List<PortDataResponse>>> call, Throwable t) {

    }*/

  /*  @Override
    public void onResponse(Call<List<TrackingNoConstraintResponse>> call, Response<List<TrackingNoConstraintResponse>> response) {
        DialogUtitlity.hideLoading();
        int statusCode = response.code();
        if(response.body() instanceof List){
            List<TrackingNoConstraintResponse> trackingNoConstraintResponseList = response.body();

            DataBaseAdapter dataBaseAdapter = new DataBaseAdapter(this);
            dataBaseAdapter.openDataBase();
            dataBaseAdapter.addTrackingNoConstraint(trackingNoConstraintResponseList);
        }

    }

    @Override
    public void onFailure(Call<List<TrackingNoConstraintResponse>> call, Throwable t) {

    }*/

   /* private void postDataToSQLite() {
        long rowId;
        DataBaseAdapter dataBaseAdapter = new DataBaseAdapter(this);
        dataBaseAdapter.openDataBase();
        portData.setPortCode(mPortCode.getText().toString().trim());
        portData.setCountry(mCountry.getText().toString().trim());
        portData.setState(mState.getText().toString().trim());
       rowId = dataBaseAdapter.addPort(portData);
        if(rowId ==-1){
            Toast.makeText(this, "sorry data is not added", Toast.LENGTH_SHORT)
                    .show();
            Log.e("AddDataActivity"," data is not added");
        }

        Toast.makeText(this, "Port added Successful!", Toast.LENGTH_SHORT)
                .show();

        Log.e("AddDataActivity","added data");
        }
*/


}

