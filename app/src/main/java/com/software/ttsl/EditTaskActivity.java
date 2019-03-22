package com.software.ttsl;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.software.ttsl.Interfacce.AlertDialogCallback;
import com.software.ttsl.Request.TaskDataModel;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.AlertDialogManager;
import com.software.ttsl.Utils.ConnectivityReceiver;
import com.software.ttsl.Utils.DateAndTimeUtil;
import com.software.ttsl.Utils.EmployConstantUtil;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditTaskActivity extends AppCompatActivity implements View.OnClickListener, Callback<String>, AlertDialogCallback {

    public static final String TAG = EditTaskActivity.class.getName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.linear_layout_task_owner)
    LinearLayout linearLayoutTaskOwner;

    @BindView(R.id.linear_layout_task_subject)
    LinearLayout linearLayoutTaskSubject;

    @BindView(R.id.linear_layout_task_due_date)
    LinearLayout linearLayoutTaskDueDate;

    @BindView(R.id.linear_layout_task_account)
    LinearLayout linearLayoutTaskAccount;

    @BindView(R.id.linear_layout_task_contact)
    LinearLayout linearLayoutTaskContact;

    @BindView(R.id.linear_layout_task_status)
    LinearLayout linearLayoutTaskStatus;

    @BindView(R.id.linear_layout_task_priority)
    LinearLayout linearLayoutTaskPriority;

    @BindView(R.id.linear_layout_task_notification_mail)
    LinearLayout linearLayoutTaskNotificationMail;

    @BindView(R.id.linear_layout_task_reminder)
    LinearLayout linearLayoutTaskReminder;

    @BindView(R.id.linear_layout_task_repeat)
    LinearLayout linearLayoutTaskRepeat;

    @BindView(R.id.linear_layout_task_description)
    LinearLayout linearLayoutTaskDiscription;

    @BindView(R.id.tv_header_discription)
    TextView textViewHeaderDescription;

    @BindView(R.id.et_task_owner)
    TextView textViewTaskOwner;

    @BindView(R.id.et_task_subject)
    TextView textViewTaskSubject;

    @BindView(R.id.et_task_due_date)
    TextView textViewTaskDueDate;

    @BindView(R.id.et_task_contact)
    TextView textViewTaskContact;

    @BindView(R.id.et_task_account)
    TextView textViewTaskAccount;

    @BindView(R.id.et_task_status)
    TextView textViewTaskStatus;

    @BindView(R.id.et_task_priority)
    TextView textViewTaskPriority;

    @BindView(R.id.et_task_notification_mail)
    TextView textViewTaskNotificationMail;

    @BindView(R.id.switch_task_notification_mail)
    Switch aSwitchTaskNotificationMail;

    @BindView(R.id.et_task_reminder)
    TextView textViewTaskReminder;

    @BindView(R.id.et_task_repeat)
    TextView textViewTaskRepeat;

    @BindView(R.id.et_task_description)
    EditText editTextTaskDescription;

    @BindView(R.id.tv_smart_view)
    TextView textViewSmartView;

    @BindView(R.id.tv_task_contact)
    TextView textViewLabelContact;


    private int mMonth, mYear, mDay;
    private Intent intent;
    private String[] itemList, taskSubjectList, taskPriority, taskStatus;
    private String titleTaskOwner, titleSubject, titleContact, titleAccount, errorTitle, showSmartView, showAllFields, titleStatus, titlePriority, errSubjectEmpty, titleLeads;
    private long accountId, contactId, taskId, leadId;
    private AlertDialogManager alertDialogManager;
    private TaskDataModel taskDataModel;
    private DataBaseAdapter dataBaseAdapter;
    private long taskCreatedTime;
    private Boolean isSync, isLead;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Intent intent = getIntent();
        taskId = intent.getLongExtra(EmployConstantUtil.KEY_TASK_ID, 0);
        i = intent.getIntExtra("A", 0);

        Log.v(TAG, "edit task id " + taskId);

        alertDialogManager = new AlertDialogManager();
        dataBaseAdapter = new DataBaseAdapter(this);
        setStringResource();

        getTaskById();
        setClickLister();


    }

    private void getTaskById() {
        taskDataModel = dataBaseAdapter.getTaskByID(taskId);
        setTaskDetails(taskDataModel);
    }

    private void setTaskDetails(TaskDataModel taskDataModel) {
        textViewTaskOwner.setText(taskDataModel.getTaskOwner());
        textViewTaskSubject.setText(taskDataModel.getTaskSubject());
        textViewTaskDueDate.setText(taskDataModel.getTaskDueDate());
        textViewTaskPriority.setText(taskDataModel.getTaskPriority());
        textViewTaskAccount.setText(taskDataModel.getTaskAccount());
        textViewTaskContact.setText(checkSelectItem(taskDataModel));
        textViewLabelContact.setText(setSelectedLabel(taskDataModel));
        textViewTaskStatus.setText(taskDataModel.getTaskContact());
        editTextTaskDescription.setText(taskDataModel.getTaskDescription());
        taskCreatedTime = taskDataModel.getTaskCreatedTime();
        isSync = taskDataModel.isSync();

    }


    private String checkSelectItem(TaskDataModel taskDataModel) {

        return taskDataModel.getTaskContact().isEmpty() ? taskDataModel.getLeadName().isEmpty() ? "" : taskDataModel.getLeadName() : taskDataModel.getTaskContact();
    }

    private String setSelectedLabel(TaskDataModel taskDataModel) {
        return taskDataModel.getTaskContact().isEmpty() ? taskDataModel.getLeadName().isEmpty() ? titleContact : titleLeads : titleContact;

    }


    private void setStringResource() {
        titleTaskOwner = getResources().getString(R.string.label_task_owner);
        titleSubject = getResources().getString(R.string.label_task_subject);
        titleContact = getResources().getString(R.string.title_task_contact);
        titleAccount = getResources().getString(R.string.title_task_account);
        titleStatus = getResources().getString(R.string.label_task_status);
        titlePriority = getResources().getString(R.string.label_task_priority);
        titleLeads = getResources().getString(R.string.title_task_lead);
        taskPriority = getResources().getStringArray(R.array.list_task_priority);
        taskStatus = getResources().getStringArray(R.array.list_task_status);
        taskSubjectList = getResources().getStringArray(R.array.list_task_subject);
        showAllFields = getResources().getString(R.string.footer_show_all_fields);
        showSmartView = getResources().getString(R.string.footer_smart_view);
        errorTitle = getResources().getString(R.string.err_msg_title_dialog);
        errSubjectEmpty = getResources().getString(R.string.err_subject_empty);
        textViewTaskPriority.setText(taskPriority[0]);
        textViewTaskStatus.setText(taskStatus[0]);
    }


    private void setClickLister() {
        textViewTaskOwner.setOnClickListener(this);
        textViewTaskSubject.setOnClickListener(this);
        textViewTaskDueDate.setOnClickListener(this);
        textViewTaskContact.setOnClickListener(this);
        textViewTaskAccount.setOnClickListener(this);
        textViewTaskStatus.setOnClickListener(this);
        textViewTaskPriority.setOnClickListener(this);
        textViewTaskNotificationMail.setOnClickListener(this);
        textViewTaskReminder.setOnClickListener(this);
        textViewTaskRepeat.setOnClickListener(this);
        textViewSmartView.setOnClickListener(this);

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

                textViewTaskDueDate.setText(DateAndTimeUtil.dateToString(date));

            }
        }, mYear, mMonth, mDay);

        datePickerDialog.show();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_task, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.add_task:
                if (checkValidation())
                    updateTaskData();

                break;
        }

        return super.onOptionsItemSelected(item);


    }

    private String checkSelectListItem(String selectedItem, String label) {

        return selectedItem.equals(EmployConstantUtil.NOTING_SELECTED_VALUE) ? "" : textViewLabelContact.getText().toString().equals(label) ? selectedItem : "";
    }

    private void updateTaskData() {
        taskDataModel = new TaskDataModel();
        taskDataModel.setTaskId(leadId);
        taskDataModel.setTaskOwner(checkSelectListItem(textViewTaskOwner.getText().toString()));
        taskDataModel.setTaskSubject(textViewTaskSubject.getText().toString());
        taskDataModel.setTaskPriority(checkSelectListItem(textViewTaskPriority.getText().toString()));
        taskDataModel.setTaskStatus(checkSelectListItem(textViewTaskStatus.getText().toString()));
        taskDataModel.setTaskContact(checkSelectListItem(textViewTaskContact.getText().toString(), titleContact));
        taskDataModel.setTaskAccount(checkSelectListItem(textViewTaskAccount.getText().toString()));
        taskDataModel.setLeadName(checkSelectListItem(textViewTaskContact.getText().toString(), titleLeads));
        taskDataModel.setTaskDueDate(textViewTaskDueDate.getText().toString());
        taskDataModel.setTaskAccountId(accountId);
        taskDataModel.setTaskContactId(contactId);
        taskDataModel.setLeadId(leadId);
        taskDataModel.setTaskCreatedBy("Test");
        taskDataModel.setTaskModifyBy("Test");
        taskDataModel.setTaskCreatedTime(DateAndTimeUtil.currentTimeStamp());
        taskDataModel.setTaskModifyTime(DateAndTimeUtil.currentTimeStamp());
        taskDataModel.setTaskDescription(editTextTaskDescription.getText().toString());
        dataBaseAdapter.openDataBase();


        /*if(result ==1){
            Toast.makeText(this,"updated",Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "task Update Successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(EditTaskActivity.this, TaskListActivity.class));
            finish();

        }else {
            Toast.makeText(this,"update fail ",Toast.LENGTH_SHORT).show();
        }*/

        /*Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();*/

        if (!isSync) {

            if (checkConnection()) {
                updateTaskDataToServer();
            } else {

                dataBaseAdapter.updateTask(taskDataModel, taskId);
                alertDialogManager.showAlertDialogMessage(this, "", "Task is Updated ", false, this);
            }


        } else {

            if (checkConnection()) {
                updateTaskDataToServer();
            } else {
                Toast.makeText(this, "Please check your Internet connection ", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void updateTaskDataToServer() {
        //DialogUtitlity.showLoading(EditContactActivity.this);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> listCall = apiInterface.addTask(taskDataModel);
        Log.e("addTask", "inside updateTaskDataToServer");
        listCall.enqueue(this);

    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        Log.e("addTask", "inside onResponse");
        int statusCode = response.code();
        Log.e("addTask", String.valueOf(statusCode));
        switch (statusCode) {
            case 200:
                if (response.body() instanceof String) {

                    taskDataModel.setSync(true);

                }
                break;
            case 201:
                taskDataModel.setSync(true);
                break;
            case 500:
                taskDataModel.setSync(isSync);
                break;
            default:
                taskDataModel.setSync(isSync);


        }
        long result = dataBaseAdapter.updateTask(taskDataModel, taskId);
        alertDialogManager.showAlertDialogMessage(this, "", "Task is updated ", false, this);

    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

    }

    private boolean checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        if (!isConnected) {

            return false;
        }

        return true;
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.et_task_due_date:
                openCalender();
                break;
            case R.id.tv_smart_view:
                startSmartView();
                break;
            case R.id.et_task_owner:
                itemList = new String[]{"Test"};
                startNewActivity(titleTaskOwner, itemList);
                break;
            case R.id.et_task_subject:
                startNewActivity(titleSubject, taskSubjectList);
                break;
            case R.id.et_task_priority:
                startNewActivity(titlePriority, taskPriority);
                break;
            case R.id.et_task_status:
                startNewActivity(titleStatus, taskStatus);
                break;
            case R.id.et_task_account:
                startNewActivity(titleAccount, AccountNameListActivity.class);
                break;
            case R.id.et_task_contact:
                startNewActivity(titleContact, ContactNameListActivity.class);
                break;

        }

    }

    private void startSmartView() {
        if (textViewSmartView.getText().toString().equals(showSmartView)) {
            linearLayoutTaskOwner.setVisibility(View.GONE);
            linearLayoutTaskContact.setVisibility(View.GONE);
            linearLayoutTaskAccount.setVisibility(View.GONE);
            linearLayoutTaskDiscription.setVisibility(View.GONE);
            textViewHeaderDescription.setVisibility(View.GONE);
            textViewSmartView.setText(showAllFields);

        } else {
            textViewSmartView.setText(showSmartView);
            linearLayoutTaskOwner.setVisibility(View.VISIBLE);
            linearLayoutTaskContact.setVisibility(View.VISIBLE);
            linearLayoutTaskAccount.setVisibility(View.VISIBLE);
            linearLayoutTaskDiscription.setVisibility(View.VISIBLE);
            textViewHeaderDescription.setVisibility(View.VISIBLE);

        }
    }


    private void startNewActivity(String title, String[] itemList) {

        intent = new Intent(EditTaskActivity.this, CustomListActivity.class);
        intent.putExtra(EmployConstantUtil.ITEM_LIST, itemList);
        intent.putExtra(EmployConstantUtil.TITLE, title);
        startActivityForResult(intent, 1000);

    }

    private void startNewActivity(String title, Class<?> cls) {
        intent = new Intent(EditTaskActivity.this, cls);
        startActivityForResult(intent, 1000);

    }


    private boolean checkValidation() {
        if (textViewTaskSubject.getText().toString().trim().isEmpty()) {
            showErrorDialog(errSubjectEmpty);
            return false;
        }
        return true;
    }

    public void showErrorDialog(String errMessage) {
        alertDialogManager.showAlertDialog(this, errorTitle, errMessage, false);

    }

    private String checkSelectListItem(String selectedItem) {

        return selectedItem.equals(EmployConstantUtil.NOTING_SELECTED_VALUE) ? "" : selectedItem;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == RESULT_OK) {
                String selectedItem = data.getStringExtra(EmployConstantUtil.SELECTED_ITEM);
                String title = data.getStringExtra(EmployConstantUtil.TITLE);
                if (title.equals(titleSubject)) {
                    textViewTaskSubject.setText(selectedItem);
                } else if (title.equals(titleStatus)) {
                    textViewTaskStatus.setText(selectedItem);
                } else if (title.equals(titlePriority)) {
                    textViewTaskPriority.setText(selectedItem);
                } else if (title.equals(titleTaskOwner)) {
                    textViewTaskOwner.setText(selectedItem);
                }
                if (title.equals(titleAccount)) {
                    textViewTaskAccount.setText(selectedItem);
                    accountId = data.getLongExtra(EmployConstantUtil.KEY_ACCOUNT_ID, 0);

                } else if (title.equals(titleContact)) {
                    textViewTaskContact.setText(selectedItem);
                    isLead = data.getBooleanExtra(EmployConstantUtil.KEY_IS_LEAD, false);
                    if (isLead) {
                        leadId = data.getLongExtra(EmployConstantUtil.KEY_CONTACT_ID, 0);
                        textViewLabelContact.setText(titleLeads);
                    } else {
                        contactId = data.getLongExtra(EmployConstantUtil.KEY_CONTACT_ID, 0);
                        textViewLabelContact.setText(titleContact);
                    }
                }
            }
        }
    }


    @Override
    public void alertDialogCallbackOk() {
        if (i == 1) {
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public void alertDialogCallbackCancel() {

    }
}
