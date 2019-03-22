package com.software.ttsl;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.software.ttsl.Dialog.TaskDialog;
import com.software.ttsl.Fragment.TaskDetailFragment;
import com.software.ttsl.Fragment.TaskRelatedFragment;
import com.software.ttsl.Interfacce.AlertDialogCallback;
import com.software.ttsl.Request.AccountDataModel;
import com.software.ttsl.Request.TaskDataModel;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.AlertDialogManager;
import com.software.ttsl.Utils.ConnectivityReceiver;
import com.software.ttsl.Utils.EmployConstantUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.software.ttsl.TaskListActivity.REQUEST_CODE_ADD_TASK;

public class TaskDetailActivity extends AppCompatActivity implements AlertDialogCallback,TaskDialog.OnDialogSelectorListener, Callback<String> {

    public static final String TAG = TaskDetailActivity.class.getName();
    public static final int REQUEST_CODE_EDIT_TASK= 2000;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    Long taskId;
    private DataBaseAdapter dataBaseAdapter;
    private TaskDataModel taskDataModel;
    private String taskStatus;
    private boolean isTaskComplete;
    private boolean isSync;
    private int i;

    private  TaskRelatedFragment taskRelatedFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        taskId = intent.getLongExtra(EmployConstantUtil.KEY_TASK_ID, 0);
        taskStatus=intent.getStringExtra(EmployConstantUtil.TASK_STATUS);
        Log.i(TAG, "Task id in task detail activity  " + taskId);
        i = intent.getIntExtra("A",0);
        isTaskComplete=intent.getBooleanExtra(EmployConstantUtil.IS_TASK_COMPLETED,false);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataBaseAdapter = new DataBaseAdapter(this);
        taskDataModel = dataBaseAdapter.getTaskByID(taskId);
        taskDataModel.setTaskCompleted(isTaskComplete);
        taskDataModel.setTaskStatus(taskStatus);
        isSync  = taskDataModel.isSync();
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }



    public TaskDataModel getTaskDataModel(){

        return  taskDataModel;
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        taskDataModel = dataBaseAdapter.getTaskByID(taskId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_account_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_edit:
                Intent intent = new Intent(TaskDetailActivity.this, AddTaskActivity.class);
                intent.putExtra(EmployConstantUtil.KEY_TASK_ID, taskId);
                if(i == 0) {
                    Log.i(TAG, "send task id to edit task " + taskId);
                    startActivityForResult(intent,REQUEST_CODE_EDIT_TASK);
                }
                else {
                    intent.putExtra("A",i);
                    startActivityForResult(intent,1000);
                }
                break;

            case R.id.action_delete:
                DeleteTask();
                break;

        }

        return super.onOptionsItemSelected(item);
    }
    private boolean checkConnection() {



        boolean isConnected = ConnectivityReceiver.isConnected();
        if (!isConnected) {

            return false;
        }

        return true;
    }

    private void DeleteTask() {
  //      AlertDialogManager.showAlertDialogMessage(TaskDetailActivity.this, "", "Are you sure to delete task", false, this);
        if(!isSync){
            if (dataBaseAdapter.deleteTask(taskId)) {
                Toast.makeText(this, "Task has been Deleted   not sync", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(this, "Please try again later ", Toast.LENGTH_SHORT).show();
            }

        }else {

            if (checkConnection()) {
                AlertDialogManager.showAlertDialogMessage(this, "", "Are you sure to delete Task", false, this);
            } else {
                Toast.makeText(this, "Please check your Internet connection ", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void setupViewPager(ViewPager viewPager) {
        Bundle bundle = new Bundle();
        bundle.putLong(EmployConstantUtil.KEY_TASK_ID, taskId);
        taskRelatedFragment = new TaskRelatedFragment();
        taskRelatedFragment.setArguments(bundle);
        TaskDetailFragment taskDetailFragment = new TaskDetailFragment();
        taskDetailFragment.setArguments(bundle);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(taskRelatedFragment, "Related");
        adapter.addFragment(taskDetailFragment, "Detail");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void alertDialogCallbackOk() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        final Call<String> response = apiService.removeTask(taskId);
        response.enqueue(this);

    }

    @Override
    public void alertDialogCallbackCancel() {

    }

    @Override
    public void onSelectedOption(String selectedIndex) {
        taskRelatedFragment.onSelectedOption(selectedIndex);

    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        int statusCode = response.code();
        if (statusCode == 200) {
            if (dataBaseAdapter.deleteTask(taskId)) {
                Toast.makeText(this, "Task has been Deleted ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(this, "Please try again later ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        Log.d("testing", "" + t.getMessage());
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
         switch (requestCode) {
             case REQUEST_CODE_ADD_TASK:
                 /*setResult(RESULT_OK, data);
                 finish();*/
                 break;
             case REQUEST_CODE_EDIT_TASK:
                 taskDataModel = dataBaseAdapter.getTaskByID(taskId);
                 viewPager.setCurrentItem(0);
                 break;

         }
        }else if(requestCode==RESULT_CANCELED){

            viewPager.setCurrentItem(0);


        }
    }
}
