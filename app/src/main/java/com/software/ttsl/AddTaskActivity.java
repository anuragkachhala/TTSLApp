package com.software.ttsl;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
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
import com.software.ttsl.Utils.DialogUtitlity;
import com.software.ttsl.Utils.EmployConstantUtil;
import com.software.ttsl.Utils.SessionManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTaskActivity extends AppCompatActivity implements View.OnClickListener, AlertDialogCallback {

    public static final String TAG = AddTaskActivity.class.getName();
    private static final int REQUEST_CODE_TASK_OWNER = 1000;
    private static final int REQUEST_CODE_SUBJECT_LIST = 2000;
    private static final int REQUEST_CODE_PRIORITY = 3000;
    private static final int REQUEST_CODE_STATUS = 4000;
    private static final int REQUEST_CODE_CONTACT = 5000;
    private static final int REQUEST_CODE_ACCOUNT = 6000;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_task_subject)
    TextView textViewLabelTaskSubject;

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

    @BindView(R.id.tv_error_subject)
    TextView textViewErrorSubject;

    private long taskId, taskCreatedTime;
    private boolean isSync = false;
    private boolean isLead = false;
    private int mMonth, mYear, mDay;
    private Intent intent;
    private String[] itemList, taskSubjectList, taskPriority, taskStatus;
    private String titleTaskOwner, titleSubject,createdBy, titleContact, titleAccount, errorTitle, showSmartView, showAllFields, titleStatus, titlePriority, errSubjectEmpty, titleLeads;
    private long accountId, contactId, leadId;
    private AlertDialogManager alertDialogManager;
    private TaskDataModel taskDataModel;
    private DataBaseAdapter dataBaseAdapter;
    private boolean editTask = false;
    private SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        textViewTaskDueDate.setText(new SimpleDateFormat("E, MMM dd yyyy", Locale.getDefault()).format(new Date()));
        alertDialogManager = new AlertDialogManager();
        dataBaseAdapter = new DataBaseAdapter(this);
        SessionManager.setContext(getApplicationContext());
        sessionManager = SessionManager.getInstance();
        createdBy = sessionManager.getUserKeyId();

        Intent intent = getIntent();

        taskId = intent.getLongExtra(EmployConstantUtil.KEY_TASK_ID, 0);
        if (taskId != 0) {
            editTask = true;

        }

        Log.v(TAG, "edit task id " + taskId);

        setClickLister();

        setStringResource();
        if (editTask) {
            getTaskById();
            toolbar.setTitle(getResources().getString(R.string.title_edit_task_activity));
        }
    }

    private void setStringResource() {
        titleTaskOwner = getResources().getString(R.string.label_task_owner);
        titleSubject = getResources().getString(R.string.label_task_subject);
        textViewLabelTaskSubject.setText(Html.fromHtml(EmployConstantUtil.ASTERISK+titleSubject));
        titleAccount = getResources().getString(R.string.title_task_account);
        titleContact = getResources().getString(R.string.title_task_contact);
        titleLeads = getResources().getString(R.string.title_task_lead);
        titleStatus = getResources().getString(R.string.label_task_status);
        titlePriority = getResources().getString(R.string.label_task_priority);
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
        textViewTaskStatus.setText(taskDataModel.getTaskStatus());
        editTextTaskDescription.setText(taskDataModel.getTaskDescription());
        taskCreatedTime = taskDataModel.getTaskCreatedTime();
        leadId = taskDataModel.getLeadId();
        contactId = taskDataModel.getTaskContactId();
        accountId = taskDataModel.getTaskAccountId();
        createdBy = taskDataModel.getTaskCreatedBy();
        isSync = taskDataModel.isSync();


    }

    private String checkSelectItem(TaskDataModel taskDataModel) {

        return taskDataModel.getTaskContact().isEmpty() ? taskDataModel.getLeadName().isEmpty() ? "" : taskDataModel.getLeadName() : taskDataModel.getTaskContact();
    }

    private String setSelectedLabel(TaskDataModel taskDataModel) {
        return taskDataModel.getTaskContact().isEmpty() ? taskDataModel.getLeadName().isEmpty() ? titleContact : titleLeads : titleContact;

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


        datePickerDialog.getDatePicker().setMinDate(DateAndTimeUtil.currentTimeStamp());
        datePickerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        datePickerDialog.setTitle("");
        datePickerDialog.show();


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
                startNewActivity(titleTaskOwner, itemList, REQUEST_CODE_TASK_OWNER);
                break;
            case R.id.et_task_subject:
                startNewActivity(titleSubject, taskSubjectList, REQUEST_CODE_SUBJECT_LIST);
                break;
            case R.id.et_task_priority:
                startNewActivity(titlePriority, taskPriority, REQUEST_CODE_PRIORITY);
                break;
            case R.id.et_task_status:
                startNewActivity(titleStatus, taskStatus, REQUEST_CODE_STATUS);
                break;
            case R.id.et_task_account:
                startNewActivity(titleAccount, AccountNameListActivity.class, REQUEST_CODE_ACCOUNT);
                break;
            case R.id.et_task_contact:
                startNewActivity(titleContact, ContactNameListActivity.class, REQUEST_CODE_CONTACT);
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


    private void startNewActivity(String title, String[] itemList, int requestCode) {
        intent = new Intent(AddTaskActivity.this, CustomListActivity.class);
        intent.putExtra(EmployConstantUtil.ITEM_LIST, itemList);
        intent.putExtra(EmployConstantUtil.TITLE, title);
        startActivityForResult(intent, requestCode);

    }

    private void startNewActivity(String title, Class<?> cls, int requestCode) {
        intent = new Intent(AddTaskActivity.this, cls);
        startActivityForResult(intent, requestCode);

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
                    if (!editTask) {
                    alertDialogManager.showAlertDialogMessage(this, "", "Are you sure to create a Task ", false, this);
                } else {
                    alertDialogManager.showAlertDialogMessage(this, "", "Are you sure to update a Task ", false, this);
                }

                break;
        }

        return super.onOptionsItemSelected(item);


    }

    private void addTaskData() {
        taskDataModel = new TaskDataModel();
        if (editTask) {
            taskDataModel.setTaskId(taskId);
        } else {
            taskDataModel.setTaskId(DateAndTimeUtil.currentTimeStamp());
        }
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
        taskDataModel.setTaskCreatedBy(createdBy);
        taskDataModel.setTaskModifyBy(sessionManager.getUserKeyId());
        taskDataModel.setTaskCreatedTime(DateAndTimeUtil.currentTimeStamp());
        taskDataModel.setTaskModifyTime(DateAndTimeUtil.currentTimeStamp());
        taskDataModel.setTaskDescription(editTextTaskDescription.getText().toString());
        dataBaseAdapter.openDataBase();

        if (editTask) {
            if (!isSync) {

                if (checkConnection()) {
                    addTaskDataToServer();
                } else {

                    dataBaseAdapter.updateTask(taskDataModel, taskId);
                    //alertDialogManager.showAlertDialogMessage(this, "", "Task is Updated ", false, this);
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }


            } else {

                if (checkConnection()) {
                    addTaskDataToServer();
                } else {
                    Toast.makeText(this, "Please check your Internet connection ", Toast.LENGTH_SHORT).show();
                }
            }

        } else {

            if (checkConnection()) {
                addTaskDataToServer();
            } else {

                taskDataModel.setSync(false);
                dataBaseAdapter.addTask(taskDataModel);
                //alertDialogManager.showAlertDialogMessage(AddTaskActivity.this, "", "Task is created ", false, AddTaskActivity.this);

                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();

            }
        }
    }


    private void addTaskDataToServer() {
        DialogUtitlity.showLoading(AddTaskActivity.this);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        final Call<String> response = apiService.addTask(taskDataModel);
        response.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                DialogUtitlity.hideLoading();
                int statusCode = response.code();
                switch (statusCode) {
                    case 201:
                        taskDataModel.setSync(true);
                        break;
                    case 400:
                        taskDataModel.setSync(false);
                        break;
                }

                updateTask();

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            updateTask();

            }
        });
    }


    private void updateTask(){
        if (!editTask) {
            dataBaseAdapter.addTask(taskDataModel);
           // alertDialogManager.showAlertDialogMessage(AddTaskActivity.this, "", "Task is created ", false, AddTaskActivity.this);
        } else {
            long result = dataBaseAdapter.updateTask(taskDataModel, taskId);
           // alertDialogManager.showAlertDialogMessage(AddTaskActivity.this, "", "Task is updated ", false, AddTaskActivity.this);


        }


        Log.e(TAG, "NOT CREATED");
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    // Method to manually check connection status
    private boolean checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        if (!isConnected) {
            return false;
        }

        return true;
    }


    private boolean checkValidation() {
        if (textViewTaskSubject.getText().toString().trim().isEmpty()) {
            textViewErrorSubject.setVisibility(View.VISIBLE);
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


    private String checkSelectListItem(String selectedItem, String label) {

        return selectedItem.equals(EmployConstantUtil.NOTING_SELECTED_VALUE) ? "" : textViewLabelContact.getText().toString().equals(label) ? selectedItem : "";
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String selectedItem = data.getStringExtra(EmployConstantUtil.SELECTED_ITEM);
            switch (requestCode) {
                case REQUEST_CODE_TASK_OWNER:
                    textViewTaskOwner.setText(selectedItem);
                    break;
                case REQUEST_CODE_PRIORITY:
                    textViewTaskPriority.setText(selectedItem);
                    break;
                case REQUEST_CODE_STATUS:
                    textViewTaskStatus.setText(selectedItem);
                    break;
                case REQUEST_CODE_SUBJECT_LIST:
                    textViewTaskSubject.setText(selectedItem);
                    break;
                case REQUEST_CODE_ACCOUNT:
                    textViewTaskAccount.setText(selectedItem);
                    accountId = data.getLongExtra(EmployConstantUtil.KEY_ACCOUNT_ID, 0);
                    break;
                case REQUEST_CODE_CONTACT:
                    textViewTaskContact.setText(selectedItem);
                    isLead = data.getBooleanExtra(EmployConstantUtil.KEY_IS_LEAD, false);
                    if (isLead) {
                        leadId = data.getLongExtra(EmployConstantUtil.KEY_CONTACT_ID, 0);
                        textViewLabelContact.setText(titleLeads);

                    } else {
                        contactId = data.getLongExtra(EmployConstantUtil.KEY_CONTACT_ID, 0);
                        textViewLabelContact.setText(titleContact);

                    }
                    break;


            }
        }

    }

    @Override
    public void alertDialogCallbackOk() {
        addTaskData();
    }

    @Override
    public void alertDialogCallbackCancel() {

    }



}
