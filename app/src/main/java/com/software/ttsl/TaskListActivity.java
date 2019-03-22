package com.software.ttsl;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.software.ttsl.Fragment.AddTaskFragment;
import com.software.ttsl.Request.TaskDataModel;
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

public class TaskListActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_CODE_ADD_TASK = 1000;
    private static final String TAG = TaskListActivity.class.getName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    private AddTaskFragment addTaskFragment;
    private DataBaseAdapter dataBaseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dataBaseAdapter = new DataBaseAdapter(this);

        setClickListener();
        if (dataBaseAdapter.getAllTask().size() == 0) {
          //  getAllTask();

        }

        if (savedInstanceState == null) {
            // Let's first dynamically add a fragment into a frame container
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.fragment_container, new AddTaskFragment(), EmployConstantUtil.TAG_TASK).
                    commit();

        }
        // Now later we can lookup the fragment by tag
        addTaskFragment = (AddTaskFragment) getSupportFragmentManager().findFragmentByTag(EmployConstantUtil.TAG_TASK);

    }

    private void setClickListener() {
        floatingActionButton.setOnClickListener(this);
    }


    private void getAllTask() {
        DialogUtitlity.showLoading(TaskListActivity.this);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<TaskDataModel>> listCall = apiInterface.getAllTask(-1);
        Log.i(TAG, "inside getAll task from server");
        listCall.enqueue(new Callback<List<TaskDataModel>>() {
            @Override
            public void onResponse(Call<List<TaskDataModel>> call, Response<List<TaskDataModel>> response) {
                DialogUtitlity.hideLoading();
                Log.i(TAG, "inside getAll task from server");
                int statusCode = response.code();
                Log.i(TAG, String.valueOf(statusCode));
                if (statusCode == 200) {
                    if (response.body() instanceof List) {
                        List<TaskDataModel> taskDataModelList = response.body();
                        dataBaseAdapter.setAllTask(taskDataModelList);


                    }
                }

            }

            @Override
            public void onFailure(Call<List<TaskDataModel>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.fab:
                startActivityForResult(new Intent(TaskListActivity.this, AddTaskActivity.class), REQUEST_CODE_ADD_TASK);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_ADD_TASK:
                    //TODO get intent data
                    addTaskFragment = (AddTaskFragment) getSupportFragmentManager().findFragmentByTag(EmployConstantUtil.TAG_TASK);
                    addTaskFragment.onActivityResult(requestCode, resultCode, data);
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
