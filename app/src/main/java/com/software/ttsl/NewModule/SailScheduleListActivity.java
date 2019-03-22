package com.software.ttsl.NewModule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.software.ttsl.R;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Utils.AlertDialogManager;
import com.software.ttsl.Utils.DialogUtitlity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.software.ttsl.NewModule.SailScheduleFragment.DISCHARGE_PORT;
import static com.software.ttsl.NewModule.SailScheduleFragment.FROM_ETA;
import static com.software.ttsl.NewModule.SailScheduleFragment.FROM_ETD;
import static com.software.ttsl.NewModule.SailScheduleFragment.LOADING_PORT;
import static com.software.ttsl.NewModule.SailScheduleFragment.SELECTED_FROM_PORT;
import static com.software.ttsl.NewModule.SailScheduleFragment.SELECTED_TO_PORT;
import static com.software.ttsl.NewModule.SailScheduleFragment.TO_ETA;
import static com.software.ttsl.NewModule.SailScheduleFragment.TO_ETD;

public class SailScheduleListActivity extends AppCompatActivity implements Callback<List<SailScheduleResponse>> {

    public static final String TAG = SailScheduleListActivity.class.getName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.layout_port)
    RelativeLayout relativeLayoutLayoutPort;

    @BindView(R.id.tv_schedule_count)
    TextView textViewScheduleCount;

    @BindView(R.id.tv_from_port)
    TextView textViewFromPort;

    @BindView(R.id.tv_to_port)
    TextView textViewToPort;

    private Intent intent;
    private SailScheduleListAdapter sailScheduleListAdapter;
    private List<SailScheduleResponse> sailScheduleResponseList = new ArrayList<>();
    private String portOfLoading, portOfDischarge, fromETD, toETD, fromETA, toETA;
    private int totalSchedule;
    private AlertDialogManager alertDialogManager = new AlertDialogManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sail_schedule_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        intent = getIntent();
        portOfLoading = intent.getStringExtra(SELECTED_FROM_PORT);
        portOfDischarge = intent.getStringExtra(SELECTED_TO_PORT);
        fromETD = intent.getStringExtra(FROM_ETD);
        toETD = intent.getStringExtra(TO_ETD);
        fromETA = intent.getStringExtra(FROM_ETA);
        toETA = intent.getStringExtra(TO_ETA);
        textViewFromPort.setText(intent.getStringExtra(LOADING_PORT));
        textViewToPort.setText(intent.getStringExtra(DISCHARGE_PORT));

        Log.e(TAG, portOfLoading + " " + portOfDischarge + " " + fromETD + " " + fromETA + " " + toETD + " " + toETA);

        setRecyclerView();
        AddSailingScheduleData();

    }

    private void setRecyclerView() {

        sailScheduleListAdapter = new SailScheduleListAdapter(this, sailScheduleResponseList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(sailScheduleListAdapter);
        recyclerView.setHasFixedSize(true);


    }

    private void AddSailingScheduleData() {
        SailScheduleRequest sailScheduleRequest = new SailScheduleRequest();
        sailScheduleRequest.setPortOfDischarge(/*"LOS ANGLES"*/ portOfDischarge);
        sailScheduleRequest.setPortOfLoading(/*"CHENNAI"*/ portOfLoading);
        DialogUtitlity.showLoading(SailScheduleListActivity.this);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<SailScheduleResponse>> listCall = apiInterface.getSailSchedules(fromETD,toETD,
                 fromETA,toETA/*"06.12.18", "31.12.18", "05.12.18", "10.12.18"*/, sailScheduleRequest);
        listCall.enqueue(this);
    }


    @Override
    public void onResponse(Call<List<SailScheduleResponse>> call, Response<List<SailScheduleResponse>> response) {
        DialogUtitlity.hideLoading();
        int statusCode = response.code();
        if (statusCode == 200) {
            if (response.body() instanceof List) {
                relativeLayoutLayoutPort.setVisibility(View.VISIBLE);
                List<SailScheduleResponse> sailScheduleList = response.body();
                totalSchedule = sailScheduleList.size();
                textViewScheduleCount.setText(totalSchedule + " SCHEDULE FOR");
                sailScheduleResponseList.clear();
                sailScheduleResponseList.addAll(sailScheduleList);
                sailScheduleListAdapter.notifyDataSetChanged();

            }
        } else if (statusCode == 204) {
            alertDialogManager.showAlertDialog(this, "Error", "No schedule of selected date", true);

        }
    }

    @Override
    public void onFailure(Call<List<SailScheduleResponse>> call, Throwable t) {
        DialogUtitlity.hideLoading();
        alertDialogManager.showAlertDialog(this, "Error", t.toString(), true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}
