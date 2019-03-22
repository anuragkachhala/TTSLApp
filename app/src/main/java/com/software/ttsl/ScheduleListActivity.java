package com.software.ttsl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import com.software.ttsl.Adapter.ScheduleListAdapter;
import com.software.ttsl.Request.SailingScheduleRequest;
import com.software.ttsl.Response.SailingScheduleResponse;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Utils.AlertDialogManager;
import com.software.ttsl.Utils.DialogUtitlity;
import com.software.ttsl.model.SailingScheduleRequestDate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleListActivity extends AppCompatActivity implements Callback<List<SailingScheduleResponse>> {

    private static final String TAG  = ScheduleListActivity.class.getName();

    private static final String KEY_PARCEL_DATA= "parcel_data";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.tv_schedule_count)
    TextView  textViewScheduleCount;
    ScheduleListAdapter scheduleListAdapter ;

    SailingScheduleRequestDate sailingScheduleRequestDate;
    AlertDialogManager alertDialogManager = new AlertDialogManager();



    List<SailingScheduleResponse> sailingScheduleList =  new ArrayList<>();
    int totalSchedule ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_list);


        ButterKnife.bind(this);

        Intent intent = getIntent();
        sailingScheduleRequestDate =(SailingScheduleRequestDate)intent.getParcelableExtra(KEY_PARCEL_DATA);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setUpRecyclerView();
        AddSailingScheduleData();





    }

    private void setUpRecyclerView() {

        scheduleListAdapter= new ScheduleListAdapter(this,sailingScheduleList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(scheduleListAdapter);
        recyclerView.setHasFixedSize(true);


    }


    private void AddSailingScheduleData() {
        SailingScheduleRequest sailingScheduleRequest = new SailingScheduleRequest();
        sailingScheduleRequest.setVesselId(String.valueOf(sailingScheduleRequestDate.getVesselId()));
        sailingScheduleRequest.setPodId(String.valueOf(sailingScheduleRequestDate.getDischargePortId()));
        sailingScheduleRequest.setPolId(String.valueOf(sailingScheduleRequestDate.getLoadingPortId()));

        DialogUtitlity.showLoading(ScheduleListActivity.this);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<SailingScheduleResponse>> listCall = apiInterface.getSailingSchedule(sailingScheduleRequestDate.getFromETD(),sailingScheduleRequestDate.getToETD(),
                sailingScheduleRequestDate.getFromETA(),sailingScheduleRequestDate.getToETA(),sailingScheduleRequest);
        listCall.enqueue(this);

    }

    @Override
    public void onResponse(Call<List<SailingScheduleResponse>> call, Response<List<SailingScheduleResponse>> response) {
        DialogUtitlity.hideLoading();
        int statusCode = response.code();
        if(statusCode==200) {
            if (response.body() instanceof List) {
                List<SailingScheduleResponse> scheduleList = response.body();
                totalSchedule = scheduleList.size();
                textViewScheduleCount.setText(totalSchedule+ " SCHEDULE FOR");

                    sailingScheduleList.addAll(scheduleList);
                    scheduleListAdapter.notifyDataSetChanged();

            }
        }else if(statusCode==204){
            alertDialogManager.showAlertDialog(this,"Error","No schedule of selected date",true);

        }
    }

    @Override
    public void onFailure(Call<List<SailingScheduleResponse>> call, Throwable t) {
        DialogUtitlity.hideLoading();
        alertDialogManager.showAlertDialog(this,"Error",t.toString(),true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:

                super.onBackPressed();
                break;

        }

        return super.onOptionsItemSelected(item);



    }
}
