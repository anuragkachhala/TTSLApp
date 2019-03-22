package com.software.ttsl.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.software.ttsl.Interface.ItemClickListener;
import com.software.ttsl.R;
import com.software.ttsl.Request.TaskDataModel;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.TaskDetailActivity;
import com.software.ttsl.TaskListAdapter;
import com.software.ttsl.Utils.DateAndTimeUtil;
import com.software.ttsl.Utils.EmployConstantUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;
import static com.software.ttsl.TaskListActivity.REQUEST_CODE_ADD_TASK;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddTaskFragment extends Fragment implements ItemClickListener, TaskListAdapter.OnItemCheckListener {

    private static final String TAG = AddTaskFragment.class.getName();
    public static final  int REQUEST_CODE_DETAIL_TASK= 2000;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private long taskId;
    private DataBaseAdapter dataBaseAdapter;
    private List<TaskDataModel> taskList = new ArrayList<>();
    private TaskListAdapter taskListAdapter;
    private String createdTime,createdTimeChanged,taskStatus;
    private boolean isTaskCompleted;


    public AddTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        ButterKnife.bind(this, view);


        dataBaseAdapter = new DataBaseAdapter(getContext());
        taskListAdapter = new TaskListAdapter(getContext(), taskList, this,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(taskListAdapter);
        recyclerView.setHasFixedSize(true);

        updateUI();

        return view;
    }

    private void updateUI() {
        taskList.clear();
        taskList.addAll(dataBaseAdapter.getAllTask());

        if(!taskList.isEmpty()) {
            createdTime = DateAndTimeUtil.longToDate(taskList.get(0).getTaskCreatedTime());
            for (TaskDataModel taskDataModel: taskList) {

                createdTimeChanged = DateAndTimeUtil.longToDate(taskDataModel.getTaskCreatedTime());
                if(!createdTime.equals(createdTimeChanged)){
                    taskDataModel.setHeaderShow(true);
                    createdTime = createdTimeChanged;
                }

                Log.e(TAG, " "+taskDataModel.isHeaderShow());
            }

            taskList.get(0).setHeaderShow(true);

        }

        taskListAdapter.notifyDataSetChanged();



    }

    @Override
    public void onItemClick(int position) {
        taskId  = taskList.get(position).getTaskId();
        isTaskCompleted =taskList.get(position).isTaskCompleted();
        taskStatus= taskList.get(position).getTaskStatus();
        Log.i(TAG,"task id onItemClicked in AddTaskFragment"+taskId);
        openTaskDetailActivity();
    }

    private void openTaskDetailActivity() {
        Intent intent = new Intent(getContext(), TaskDetailActivity.class);
        intent.putExtra(EmployConstantUtil.KEY_TASK_ID,taskId);
        intent.putExtra(EmployConstantUtil.IS_TASK_COMPLETED,isTaskCompleted);
        intent.putExtra(EmployConstantUtil.TASK_STATUS,taskStatus);
        startActivityForResult(intent,REQUEST_CODE_DETAIL_TASK);


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_ADD_TASK:
                    //TODO get intent data
                    updateUI();
                    break;
                case REQUEST_CODE_DETAIL_TASK:
                    updateUI();
                    break;


            }
        }else {
            updateUI();
        }
    }


    @Override
    public void onItemCheck(int position) {
        taskList.get(position).setTaskCompleted(true);
        taskList.get(position).setTaskStatus("Completed");
        dataBaseAdapter.updateTaskStatus("Completed",taskId);
       // taskListAdapter.notifyDataSetChanged();

    }

    @Override
    public void onItemUncheck(int position) {
        taskList.get(position).setTaskCompleted(false);
        taskList.get(position).setTaskStatus("Not Started");
        dataBaseAdapter.updateTaskStatus("Not Started",taskId);
        //taskListAdapter.notifyDataSetChanged();

    }
}
