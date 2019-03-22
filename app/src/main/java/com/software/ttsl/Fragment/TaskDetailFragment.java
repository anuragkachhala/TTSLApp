package com.software.ttsl.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.software.ttsl.AccountDetailsActivity;
import com.software.ttsl.R;
import com.software.ttsl.Request.AccountDataModel;
import com.software.ttsl.Request.TaskDataModel;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.TaskDetailActivity;
import com.software.ttsl.Utils.DateAndTimeUtil;
import com.software.ttsl.Utils.EmployConstantUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskDetailFragment extends Fragment {
    
    public static final String TAG = TaskDetailFragment.class.getName();


    @BindView(R.id.tv_task_owner)
    TextView textViewTaskOwner;
    
    @BindView(R.id.tv_task_subject)
    TextView textViewTaskSubject;
    
    @BindView(R.id.tv_task_due_date)
    TextView textViewDueDate;
    
    @BindView(R.id.tv_task_contact)
    TextView textViewContact;
    
    @BindView(R.id.tv_task_account)
    TextView textViewTaskAccount;
    
    @BindView(R.id.tv_task_status)
    TextView textViewTaskStatus;
    
    @BindView(R.id.tv_task_priority)
    TextView textViewPriority;
    
    @BindView(R.id.tv_task_created_by)
    TextView textViewCreatedBy;
    
    @BindView(R.id.tv_task_modified_by)
    TextView textViewModifiedBy;
    
    @BindView(R.id.tv_task_created_time)
    TextView textViewCreatedTime;
    
    @BindView(R.id.tv_task_modified_time)
    TextView textViewModifiedTime;
    
    @BindView(R.id.tv_task_description)
    TextView textViewDescription;
    
    @BindView(R.id.linear_layout_task_due_date)
    LinearLayout linearLayoutDueDate;
    
    @BindView(R.id.linear_layout_task_contact)
    LinearLayout linearLayoutTaskContact;
    
    @BindView(R.id.linear_layout_task_account)
    LinearLayout linearLayoutTaskAccount;
    
    @BindView(R.id.layout_task_description)
    LinearLayout linearLayoutTaskDescription;

    @BindView(R.id.tv_smart_view)
    TextView textViewSmartView;

    @BindView(R.id.tv_task_contact_label)
    TextView textViewTaskContactLabel;


    private DataBaseAdapter dataBaseAdapter;
    private Long taskId;
    private TaskDataModel taskDataModel;
    private TaskDetailActivity taskDetailActivity;



    public TaskDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_task_detail, container, false);
        ButterKnife.bind(this,view);

        dataBaseAdapter = new DataBaseAdapter(getContext());
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            taskId = bundle.getLong(EmployConstantUtil.KEY_TASK_ID, 0);
        }


        textViewSmartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smartView();
            }
        });


        getTask();
        setTaskInfo();
        return view;
    }

    private void setTaskInfo() {
        textViewTaskOwner.setText(taskDataModel.getTaskOwner());
        textViewTaskSubject.setText(taskDataModel.getTaskSubject());
        textViewTaskStatus.setText(taskDataModel.getTaskStatus());
        textViewPriority.setText(taskDataModel.getTaskPriority());
        textViewCreatedBy.setText(taskDataModel.getTaskCreatedBy());
        textViewModifiedBy.setText(taskDataModel.getTaskModifyBy());
        textViewCreatedTime.setText(DateAndTimeUtil.longToDate(taskDataModel.getTaskCreatedTime()));
        textViewModifiedTime.setText(DateAndTimeUtil.longToDate(taskDataModel.getTaskModifyTime()));
        textViewDueDate.setText(taskDataModel.getTaskDueDate());
        if(!taskDataModel.getTaskDescription().isEmpty()){
            linearLayoutTaskDescription.setVisibility(View.VISIBLE);
            textViewDescription.setText(taskDataModel.getTaskDescription());
        }if(!taskDataModel.getTaskContact().isEmpty()){
            linearLayoutTaskContact.setVisibility(View.VISIBLE);
            textViewTaskContactLabel.setText("Contacts");
            textViewContact.setText(taskDataModel.getTaskContact());

        }
        if(!taskDataModel.getLeadName().isEmpty()){
            linearLayoutTaskContact.setVisibility(View.VISIBLE);
            textViewTaskContactLabel.setText("Leads");
            textViewContact.setText(taskDataModel.getLeadName());

        }
        if(!taskDataModel.getTaskAccount().isEmpty()){
            linearLayoutTaskAccount.setVisibility(View.VISIBLE);
            textViewTaskAccount.setText(taskDataModel.getTaskAccount());
        }
    }

    private void getTask() {
        taskDetailActivity = (TaskDetailActivity) getActivity();
        taskDataModel = taskDetailActivity.getTaskDataModel();
    }

    @Override
    public void onResume() {
        super.onResume();
        getTask();
        setTaskInfo();

    }

    private void smartView() {
        if (textViewSmartView.getText().toString().equals(getResources().getString(R.string.footer_show_all_fields))) {
            textViewSmartView.setText(getResources().getString(R.string.footer_smart_view));
            linearLayoutDueDate.setVisibility(View.VISIBLE);
            linearLayoutTaskAccount.setVisibility(View.VISIBLE);
            linearLayoutTaskContact.setVisibility(View.VISIBLE);
            linearLayoutTaskDescription.setVisibility(View.VISIBLE);
        }

            else {
            textViewSmartView.setText(getResources().getString(R.string.footer_show_all_fields));
            setVisibilityGone();
            setTaskInfo();
        }
    }

    private void setVisibilityGone() {
        linearLayoutDueDate.setVisibility(View.GONE);
        linearLayoutTaskAccount.setVisibility(View.GONE);
        linearLayoutTaskContact.setVisibility(View.GONE);
        linearLayoutTaskDescription.setVisibility(View.GONE);
    }

}
