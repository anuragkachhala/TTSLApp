package com.software.ttsl;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

import com.software.ttsl.Fragment.AddEventFragment;
import com.software.ttsl.Request.EventDataModel;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.DialogUtitlity;
import com.software.ttsl.Utils.EmployConstantUtil;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventListActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = EventListActivity.class.getName();

    public static final int REQUEST_CODE_ADD_EVENT = 1000;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    private AddEventFragment addEventFragment;

    private int mYear, mMonth, mDay;
    private DataBaseAdapter dataBaseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        ButterKnife.bind(this);


        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dataBaseAdapter = new DataBaseAdapter(this);

        setClickListener();
        if (dataBaseAdapter.getAllEvents().size() == 0) {
            getAllEvent();
        }





        if (savedInstanceState == null) {
            // Let's first dynamically add a fragment into a frame container
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.fragment_container, new AddEventFragment(), EmployConstantUtil.TAG_EVENT).
                    commit();

        }
        // Now later we can lookup the fragment by tag
        addEventFragment = (AddEventFragment) getSupportFragmentManager().findFragmentByTag(EmployConstantUtil.TAG_EVENT);


    }

    private void getAllEvent() {
        DialogUtitlity.showLoading(EventListActivity.this);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<EventDataModel>> listCall = apiInterface.getAllEvent(0);
        listCall.enqueue(new Callback<List<EventDataModel>>() {
            @Override
            public void onResponse(Call<List<EventDataModel>> call, Response<List<EventDataModel>> response) {
                DialogUtitlity.hideLoading();
                Log.i(TAG, "inside getAll lead from server");
                int statusCode = response.code();
                Log.i(TAG, String.valueOf(statusCode));
                if (statusCode == 200) {
                    if (response.body() instanceof List) {
                        List<EventDataModel> eventDataModelList = response.body();
                        dataBaseAdapter.setAllEvent(eventDataModelList);


                    }
                }
            }

            @Override
            public void onFailure(Call<List<EventDataModel>> call, Throwable t) {
                DialogUtitlity.hideLoading();
            }
        });
    }

    /* @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.menu_event_list, menu);
         return true;
     }

 */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_date:

               /* if (setLeadData()) {
                    addLeadData();

                }*/
                openCalender();

                break;
        }

        return super.onOptionsItemSelected(item);


    }


    public void openCalender() {

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker dateView, int year, int monthOfYear, int dayOfMonth) {
                String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;


            }
        }, mYear, mMonth, mDay);

        datePickerDialog.show();


    }


    private void setClickListener() {
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startActivityForResult(new Intent(this, AddEventActivity.class), REQUEST_CODE_ADD_EVENT);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_ADD_EVENT:
                    //TODO get intent data
                    addEventFragment = (AddEventFragment) getSupportFragmentManager().findFragmentByTag(EmployConstantUtil.TAG_EVENT);
                    addEventFragment.onActivityResult(requestCode, resultCode, data);
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
