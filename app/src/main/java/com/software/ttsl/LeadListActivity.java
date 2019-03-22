package com.software.ttsl;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ajts.androidmads.library.SQLiteToExcel;
import com.software.ttsl.Fragment.AddLeadFragment;
import com.software.ttsl.Request.LeadDataModel;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Sql.DataBaseConstant;
import com.software.ttsl.Sql.DataBaseHelper;
import com.software.ttsl.Utils.DialogUtitlity;
import com.software.ttsl.Utils.EmployConstantUtil;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeadListActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = LeadListActivity.class.getName();

    public static final int ORDERBY = 100;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;
    AddLeadFragment addLeadFragment;

    private DataBaseAdapter dataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead_list);

        ButterKnife.bind(this);


        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dataBaseAdapter = new DataBaseAdapter(this);

        setClickListener();

        if(dataBaseAdapter.getAllLead().size()==0) {
            //getAllLead();
        }

        if (savedInstanceState == null) {
            // Let's first dynamically add a fragment into a frame container
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.fragment_container, new AddLeadFragment(), EmployConstantUtil.TAG_LEAD).
                    commit();

        }
        // Now later we can lookup the fragment by tag
        addLeadFragment = (AddLeadFragment) getSupportFragmentManager().findFragmentByTag(EmployConstantUtil.TAG_LEAD);


    }

    private void getAllLead() {
        DialogUtitlity.showLoading(LeadListActivity.this);
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


                    }
                }

            }

            @Override
            public void onFailure(Call<List<LeadDataModel>> call, Throwable t) {

            }
        });
    }

    private void setClickListener() {
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //  convertDatabaseToExcel();
        startActivityForResult(new Intent(LeadListActivity.this, AddLeadActivity.class), 1000);


    }


    public void convertDatabaseToExcel(){
        String directory_path = Environment.getExternalStorageDirectory().getPath() + "/Backup/";
        File file = new File(directory_path);
        if (!file.exists()) {
            file.mkdirs();
        }

        SQLiteToExcel sqliteToExcel = new SQLiteToExcel(getApplicationContext(), DataBaseConstant.DATABASE_NAME, directory_path);
        sqliteToExcel.exportAllTables("ttsl.xls", new SQLiteToExcel.ExportListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted(String filePath) {
               // Utils.showSnackBar(view, "Successfully Exported");
                Log.e(TAG,"Successfully Exported");

            }

            @Override
            public void onError(Exception e) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lead_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:

                onBackPressed();
                break;
            case R.id.add_task:

                startActivityForResult(new Intent(LeadListActivity.this, LeadSettingActivity.class), ORDERBY);

                break;
        }

        return super.onOptionsItemSelected(item);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1000:
                    //TODO get intent data
                    addLeadFragment = (AddLeadFragment) getSupportFragmentManager().findFragmentByTag(EmployConstantUtil.TAG_LEAD);
                    addLeadFragment.onActivityResult(requestCode, resultCode, data);
                    break;

                case ORDERBY:

                    addLeadFragment = (AddLeadFragment) getSupportFragmentManager().findFragmentByTag(EmployConstantUtil.TAG_LEAD);
                    addLeadFragment.onActivityResult(requestCode, resultCode, data);
                    break;
            }
        }

    }
}
