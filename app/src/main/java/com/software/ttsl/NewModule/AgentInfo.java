package com.software.ttsl.NewModule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.software.ttsl.R;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Utils.AlertDialogManager;
import com.software.ttsl.Utils.DialogUtitlity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.software.ttsl.NewModule.AgentInfoFragment.PORT_NAME;

public class AgentInfo extends AppCompatActivity implements Callback<List<AgentInfoResponse>> {
    public static final String TAG = AgentInfo.class.getName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.scrollView)
    ScrollView scrollView;


    @BindView(R.id.tv_message)
    TextView textViewMsg;

    @BindView(R.id.tv_agent_info)
    TextView textViewAgentInfo;

    @BindView(R.id.tv_agent_destination_charges)
    TextView textViewAgentDestCharge;

    @BindView(R.id.tv_agent_destination_charges1)
    TextView textViewAgentDestCharge1;

    @BindView(R.id.tv_agent_note)
    TextView textViewAgentNote;
    private Intent intent;
    private String portName;
    private AlertDialogManager alertDialogManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_info);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        alertDialogManager  = new AlertDialogManager();
        intent = getIntent();
        portName = intent.getStringExtra(PORT_NAME);
        addAgentInfo();
    }


    private void addAgentInfo() {
        DialogUtitlity.showLoading(AgentInfo.this);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<AgentInfoResponse>> listCall = apiInterface.getAgentInformation(portName);
        listCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<AgentInfoResponse>> call, Response<List<AgentInfoResponse>> response) {
        DialogUtitlity.hideLoading();
        int statusCode = response.code();
        if (statusCode == 200) {
            if (response.body() instanceof List) {
                scrollView.setVisibility(View.VISIBLE);
                textViewMsg.setVisibility(View.GONE);
                List<AgentInfoResponse> agentInfoResponsesList = response.body();
                AgentInfoResponse agentInfoResponse = agentInfoResponsesList.get(0);
                textViewAgentInfo.setText(agentInfoResponse.getAgentAddress().trim());
                if (agentInfoResponse.getLclDetail() != null) {
                    textViewAgentDestCharge.setText(agentInfoResponse.getLclDetail().trim());
                }
                if (agentInfoResponse.getLalDetail1() != null) {
                    textViewAgentDestCharge1.setText(agentInfoResponse.getLalDetail1().trim());
                }
                if (agentInfoResponse.getNotes() != null) {
                    textViewAgentNote.setText(agentInfoResponse.getNotes().trim());
                }
            }
        } else if (statusCode == 204) {
            //alertDialogManager.showAlertDialog(this, "Error", "No Information available", true);
            textViewMsg.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onFailure(Call<List<AgentInfoResponse>> call, Throwable t) {
        Log.e(TAG, t.toString());
        textViewMsg.setVisibility(View.VISIBLE);
        DialogUtitlity.hideLoading();

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
