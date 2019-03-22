package com.software.ttsl.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.software.ttsl.AccountDetailsActivity;
import com.software.ttsl.ContactDetailActivity;
import com.software.ttsl.DealDetailsActivity;
import com.software.ttsl.Dialog.TaskDialog;
import com.software.ttsl.LeadDetailActivity;
import com.software.ttsl.R;
import com.software.ttsl.Request.DealDataModel;
import com.software.ttsl.Request.TaskDataModel;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.TaskDetailActivity;
import com.software.ttsl.Utils.AlertDialogManager;
import com.software.ttsl.Utils.EmployConstantUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */



public class TaskRelatedFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener,TaskDialog.OnDialogSelectorListener {

    public static final String TAG = TaskRelatedFragment.class.getName();

    @BindView(R.id.layout_contact)
    LinearLayout linearLayoutContact;

    @BindView(R.id.tv_contact_name)
    TextView textViewContactName;

    @BindView(R.id.layout_account)
    LinearLayout linearLayoutAccount;

    @BindView(R.id.tv_account_name)
    TextView textViewAccountName;

    @BindView(R.id.checkBox_task)
    CheckBox checkBoxTask;

    @BindView(R.id.tv_task_subject)
    TextView textViewTaskSubject;

    @BindView(R.id.tv_task_priority)
    TextView textViewTaskPriority;

    @BindView(R.id.tv_task_status)
    TextView textViewTaskStatus;

    @BindView(R.id.tv_task_due_date)
    TextView textViewTaskDueDate;

    @BindView(R.id.tv_task_owner_image)
    TextView textViewTaskOwnerImage;

    @BindView(R.id.tv_contact)
    TextView textViewLabelContact;


    private long taskId;
    private DataBaseAdapter dataBaseAdapter;
    private TaskDataModel taskDataModel;
    private TaskDetailActivity taskDetailActivity;
    private long accountID,contactID,leadId;
    private boolean isLead= false;
    private boolean isFirst;

    public TaskRelatedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_releted, container, false);

        ButterKnife.bind(this,view);
        dataBaseAdapter = new DataBaseAdapter(getContext());
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            taskId = bundle.getLong(EmployConstantUtil.KEY_TASK_ID, 0);
        }


        taskDetailActivity = (TaskDetailActivity) getActivity();


        setClickListener();
        updateUI();


        return view;
    }

    private void setClickListener() {
        linearLayoutAccount.setOnClickListener(this);
        linearLayoutContact.setOnClickListener(this);
        checkBoxTask.setOnCheckedChangeListener(this);


    }

    /*@Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
*/
    public void  updateUI(){
        taskDataModel = taskDetailActivity.getTaskDataModel();
        setTaskInfo();
    }

    private void setTaskInfo() {
        checkBoxTask.setChecked(taskDataModel.isTaskCompleted());
        textViewTaskSubject.setText(taskDataModel.getTaskSubject());
        textViewTaskPriority.setText(taskDataModel.getTaskPriority());
        textViewTaskDueDate.setText(taskDataModel.getTaskDueDate());
        textViewTaskStatus.setText(taskDataModel.getTaskStatus());
        if(!taskDataModel.getTaskAccount().isEmpty()){
            linearLayoutAccount.setVisibility(View.VISIBLE);
            textViewAccountName.setText(taskDataModel.getTaskAccount());
            accountID = taskDataModel.getTaskAccountId();

        }if(!taskDataModel.getTaskContact().isEmpty()){
            linearLayoutContact.setVisibility(View.VISIBLE);
            textViewContactName.setText(taskDataModel.getTaskContact());
            contactID= taskDataModel.getTaskContactId();

        }if(!taskDataModel.getLeadName().isEmpty()){
            linearLayoutContact.setVisibility(View.VISIBLE);
            textViewContactName.setText(taskDataModel.getLeadName());
            leadId = taskDataModel.getLeadId();
            isLead = true;
            textViewLabelContact.setText("Lead");
        }
        if(taskDataModel.getTaskStatus().equals("Completed")){
            checkBoxTask.setChecked(true);


        }else {
            checkBoxTask.setChecked(false);
        }
    }





    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case  R.id.layout_account:
                if(dataBaseAdapter.getAccountById(accountID)!=null) {
                    startNewActivity(AccountDetailsActivity.class, accountID, EmployConstantUtil.KEY_ACCOUNT_ID);
                }else {
                    new AlertDialogManager().showAlertDialog(getContext(),"","This account has been Deleted",false);
                }
                break;

            case R.id.layout_contact:
                if(isLead){
                    startNewActivity(LeadDetailActivity.class,leadId,EmployConstantUtil.KEY_LEAD_ID);
                }else {
                    startNewActivity(ContactDetailActivity.class, contactID, EmployConstantUtil.KEY_CONTACT_ID);
                }
                break;

        }
    }


    private void startNewActivity(Class<?> cls,long id,String key) {
        Intent intent = new Intent(getContext(), cls);
        intent.putExtra(key,id);
        startActivityForResult(intent,1000);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if(!isChecked ){

            TaskDialog taskDialog= new TaskDialog();
            taskDialog.show(getFragmentManager(),"notificationDialog");

        }
        else {


            textViewTaskStatus.setText("Completed");
        }

    }


    @Override
    public void onSelectedOption(String selectedIndex) {
        textViewTaskStatus.setText(selectedIndex);
        dataBaseAdapter.updateTaskStatus(selectedIndex,taskId);

        if(selectedIndex.equals("Completed")){
            checkBoxTask.setChecked(true);

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode){
                case 1000:
                    Toast.makeText(getContext(),"task Releated",Toast.LENGTH_LONG).show();
            }
        }else {
            updateUI();
        }
    }
}
