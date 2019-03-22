package com.software.ttsl;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.software.ttsl.Interfacce.AlertDialogCallback;
import com.software.ttsl.Request.CallDataModel;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.AlertDialogManager;
import com.software.ttsl.Utils.ConnectivityReceiver;
import com.software.ttsl.Utils.DateAndTimeUtil;
import com.software.ttsl.Utils.DialogUtitlity;
import com.software.ttsl.Utils.EmployConstantUtil;
import com.software.ttsl.Utils.SessionManager;
import com.software.ttsl.Utils.SharedPrefUtil;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditCallDetailActivity extends AppCompatActivity implements View.OnClickListener, AlertDialogCallback, Callback<String> {


    private static final String TAG = EditCallDetailActivity.class.getName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.et_call_contact)
    EditText editTextCallContact;

    @BindView(R.id.et_call_subject)
    EditText editTextCallSubject;

    @BindView(R.id.et_call_purpose)
    EditText editTextCallPurpose;

    @BindView(R.id.et_call_account)
    EditText editTextCallAccount;

    @BindView(R.id.et_call_type1)
    EditText editTextCallType1;

    @BindView(R.id.et_call_type)
    EditText editTextCallType;

    @BindView(R.id.et_call_duration)
    EditText editTextCallDuration;

    @BindView(R.id.et_call_description)
    EditText editTextCallDescription;

    @BindView(R.id.et_call_result)
    EditText editTextCallResult;

    @BindView(R.id.et_call_start_date)
    EditText editTextCallStartDate;

    @BindView(R.id.et_call_start_time)
    EditText editTextCallStrartTime;

    @BindView(R.id.tv_call_subject)
    TextView textViewCallSubject;

    @BindView(R.id.tv_call_type1)
    TextView textViewCallType;

    @BindView(R.id.tv_call_start_date)
    TextView textViewCallStartDate;

    @BindView(R.id.tv_call_start_time)
    TextView textViewCallStartTime;

    @BindView(R.id.tv_call_duration)
    TextView textViewCallDuration;


    private String emptyAccountNameErrorMsg,emptyCallDuration, emptySubjectNameErrorMsg, emptyClosingDateErrorMsg, emptyStageErrorMsg, showSmartView, showAllFields, errorTitle, titleDealType, titleStage, titleDealOwner, titleAccountName, titleContactName, titleCallPurpose, titleCallType, titleCallType1;
    private SharedPrefUtil sharedPrefUtil;
    private AlertDialogManager alertDialogManager;
    private CallDataModel callDataModel;
    private DataBaseAdapter dataBaseAdapter;
    private Intent intent;
    private String[] itemList, callType, callType1, callPurpose;
    private String title,createdBy;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private long accountId, contactId,callId,createdTime;
    private Boolean isSync;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_call_detail);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SessionManager.setContext(getApplicationContext());

        alertDialogManager = new AlertDialogManager();
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intent = getIntent();
        callId = intent.getLongExtra(EmployConstantUtil.KEY_CALL_ID,0);

        dataBaseAdapter = new DataBaseAdapter(this);



        SharedPrefUtil.setContext(getApplicationContext());
        sharedPrefUtil = SharedPrefUtil.getInstance();

        setClickListener();

        getStringResources();

        getCallData();

    }

    private void getCallData() {
        callDataModel = dataBaseAdapter.getCallByID(callId);
        setCallData();
    }

    private void setCallData() {
        editTextCallType.setText(callDataModel.getType());
        editTextCallContact.setText(callDataModel.getContact());
        editTextCallAccount.setText(callDataModel.getAccount());
        editTextCallPurpose.setText(callDataModel.getCallPurpose());
        editTextCallSubject.setText(callDataModel.getSubject());
        editTextCallType1.setText(callDataModel.getCallType());
        editTextCallStartDate.setText(DateAndTimeUtil.longToDate(callDataModel.getCallStartDate(),EmployConstantUtil.DATE_FORMAT));
        editTextCallStrartTime.setText(DateAndTimeUtil.longToTime(callDataModel.getCallStartTime(),EmployConstantUtil.TIME_FORMAT));
        editTextCallDuration.setText(DateAndTimeUtil.longToTime(callDataModel.getCallDuration(),EmployConstantUtil.TIME_FORMAT_DURATION));
        editTextCallDescription.setText(callDataModel.getDescription());
        editTextCallResult.setText(callDataModel.getCallResult());
        contactId = callDataModel.getContactId();
        accountId = callDataModel.getAccountId();
        createdTime = callDataModel.getCreatedTime();
        createdBy = callDataModel.getCreatedBy();
        isSync = callDataModel.isSync();
    }

    private void getStringResources() {
        textViewCallType.setText(Html.fromHtml(EmployConstantUtil.ASTERISK+getResources().getString(R.string.label_call_type1)));
        textViewCallSubject.setText(Html.fromHtml(EmployConstantUtil.ASTERISK+getResources().getString(R.string.label_call_subject)));
        textViewCallDuration.setText(Html.fromHtml(EmployConstantUtil.ASTERISK+getResources().getString(R.string.label_call_duration)));
        textViewCallStartDate.setText(Html.fromHtml(EmployConstantUtil.ASTERISK+getResources().getString(R.string.label_call_start_date)));
        textViewCallStartTime.setText(Html.fromHtml(EmployConstantUtil.ASTERISK+getResources().getString(R.string.label_call_start_time)));
        errorTitle = getResources().getString(R.string.err_msg_title_dialog);
        showAllFields = getResources().getString(R.string.footer_show_all_fields);
        showSmartView = getResources().getString(R.string.footer_smart_view);
        titleContactName = getResources().getString(R.string.title_contact_list_activity);
        titleAccountName = getResources().getString(R.string.title_account_list_activity);
        emptySubjectNameErrorMsg= getResources().getString(R.string.msg_enter_subject);
        emptyCallDuration= getResources().getString(R.string.msg_empty_call_duration);
        titleCallPurpose= getResources().getString(R.string.label_call_purpose);
        callType = getResources().getStringArray(R.array.list_call_type);
        callType1 = getResources().getStringArray(R.array.list_call_type1);
        titleCallType1 = getResources().getString(R.string.label_call_type1);
        titleCallType = getResources().getString(R.string.label_call_type);
        callPurpose = getResources().getStringArray(R.array.list_call_purpose);


    }

    private void setClickListener() {
        editTextCallAccount.setOnClickListener(this);
        editTextCallContact.setOnClickListener(this);
        editTextCallDuration.setOnClickListener(this);
        editTextCallPurpose.setOnClickListener(this);
        editTextCallType.setOnClickListener(this);
        editTextCallType1.setOnClickListener(this);
        editTextCallStrartTime.setOnClickListener(this);
        editTextCallStartDate.setOnClickListener(this);

    }

    private String checkSelectListItem(String selectedItem) {

        return selectedItem.equals(EmployConstantUtil.NOTING_SELECTED_VALUE) ? "" : selectedItem;
    }



    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.et_call_type:
                startNewActivity(titleCallType, callType);
                break;
            case R.id.et_call_contact:
                startNewActivity(titleContactName, ContactNameListActivity.class);
                break;
            case R.id.et_call_account:
                startNewActivity(titleAccountName, AccountNameListActivity.class);
                break;
            case R.id.et_call_purpose:
                startNewActivity(titleCallPurpose, callPurpose);
                break;
            case R.id.et_call_type1:
                startNewActivity(titleCallType1, callType1);
                break;
            case R.id.et_call_duration:
                showCallDurationDialog();
                break;
            case R.id.et_call_start_date:
                openCalender();
                break;
            case R.id.et_call_start_time:
                openTimePicker();
                break;


        }


    }

    public void openTimePicker() {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        String time = hourOfDay + ":" + minute;

                        editTextCallStrartTime.setText(time);

                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();


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

                editTextCallStartDate.setText(DateAndTimeUtil.dateToString(date));


                //textViewTaskDueDate.setText(DateAndTimeUtil.dateToString(date));

            }
        }, mYear, mMonth, mDay);

        datePickerDialog.show();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_call, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.add_call:


                if (checkValidation())
                    addCallData();

                break;
        }

        return super.onOptionsItemSelected(item);


    }

    private void addCallData() {
        callDataModel = new CallDataModel();
        callDataModel.setType(editTextCallType.getText().toString());
        callDataModel.setSubject(editTextCallSubject.getText().toString());
        callDataModel.setAccount(editTextCallAccount.getText().toString());
        callDataModel.setAccountId(accountId);
        callDataModel.setContact(editTextCallContact.getText().toString());
        callDataModel.setContactId(contactId);
        callDataModel.setCallType(editTextCallType1.getText().toString());
        callDataModel.setDescription(editTextCallDescription.getText().toString());
        callDataModel.setCallResult(editTextCallResult.getText().toString());
        callDataModel.setCallPurpose(checkSelectListItem(editTextCallPurpose.getText().toString()));
        callDataModel.setCreatedBy(createdBy);
        callDataModel.setModifiedBy("TEST");
        callDataModel.setCreatedTime(createdTime);
        callDataModel.setModifiedTime(DateAndTimeUtil.currentTimeStamp());
        callDataModel.setCallStartDate(DateAndTimeUtil.dateToLong(editTextCallStartDate.getText().toString().trim(),EmployConstantUtil.DATE_FORMAT));
        callDataModel.setCallDuration(checkCallDuration(editTextCallDuration.getText().toString().trim()));
        callDataModel.setCallStartTime(DateAndTimeUtil.timeToLong(editTextCallStrartTime.getText().toString().trim(),EmployConstantUtil.TIME_FORMAT));
        callDataModel.setSync(isSync);
        dataBaseAdapter.openDataBase();

        if (!isSync) {

            if (checkConnection()) {
                updateCallDataToServer();
            } else {
                dataBaseAdapter.updateCall(callDataModel, callId);
                alertDialogManager.showAlertDialogMessage(this, "", "Call is Updated ", false, this);
            }


        } else {

            if (checkConnection()) {
                updateCallDataToServer();
            } else {
                Toast.makeText(this, "Please check your Internet connection ", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void updateCallDataToServer() {

        DialogUtitlity.showLoading(EditCallDetailActivity.this);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> listCall = apiInterface.addCall(callDataModel);
        Log.e("addCall", "inside updateCallDataToServer");
        listCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        DialogUtitlity.hideLoading();
        Log.e("addCall", "inside UpdateCallData to server");
        int statusCode = response.code();
        Log.e("addCall", String.valueOf(statusCode));
        if (statusCode == 200) {
            if (response.body() instanceof String) {

                callDataModel.setSync(true);

            }


        } else if (statusCode == 201) {
            callDataModel.setSync(true);


        } else if (statusCode == 500) {


            callDataModel.setSync(isSync);

        }

        int result = dataBaseAdapter.updateCall(callDataModel, callId);
        alertDialogManager.showAlertDialogMessage(this, "", "Call is updated ", false, this);

    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

    }

    private long checkCallDuration(String callDuration) {

        return callDuration.isEmpty() ? 0 : DateAndTimeUtil.dateToLong(callDuration,EmployConstantUtil.TIME_FORMAT_DURATION);
    }


    private boolean checkValidation() {
        if (editTextCallSubject.getText().toString().trim().isEmpty()) {
            alertDialogManager.showAlertDialog(this, errorTitle, emptySubjectNameErrorMsg, false);
            return false;
        }
        if(editTextCallDuration.getText().toString().trim().isEmpty()){
            alertDialogManager.showAlertDialog(this,errorTitle,emptyCallDuration,false);
            return false;
        }
        return true;
    }


    private void startNewActivity(String title, String[] itemList) {

        intent = new Intent(EditCallDetailActivity.this, CustomListActivity.class);
        intent.putExtra(EmployConstantUtil.ITEM_LIST, itemList);
        intent.putExtra(EmployConstantUtil.TITLE, title);
        startActivityForResult(intent, 1000);

    }

    private void startNewActivity(String title, Class<?> cls) {
        intent = new Intent(EditCallDetailActivity.this, cls);
        startActivityForResult(intent, 1000);
    }


    // Method to manually check connection status
    private boolean checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        if (!isConnected) {

            return false;
        }

        return true;
    }


    private void showCallDurationDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View callDurationDialog = inflater.inflate(R.layout.layout_call_duration, null);
        final NumberPicker noPickerHr = (NumberPicker) callDurationDialog.findViewById(R.id.number_picker_hr);
        final NumberPicker noPickerMin = (NumberPicker) callDurationDialog.findViewById(R.id.number_picker_min);
        final NumberPicker noPickerSec = (NumberPicker) callDurationDialog.findViewById(R.id.number_picker_sec);
        noPickerHr.setMaxValue(24);
        noPickerHr.setMinValue(0);

        noPickerHr.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        noPickerHr.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format("%02d", i);
            }
        });

        noPickerMin.setMaxValue(59);
        noPickerMin.setMinValue(0);
        noPickerMin.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        noPickerMin.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format(EmployConstantUtil.CALL_NUMBER_FORMAT, i);
            }
        });

        noPickerSec.setMaxValue(59);
        noPickerSec.setMinValue(10);
        noPickerSec.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        noPickerSec.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format(EmployConstantUtil.CALL_NUMBER_FORMAT, i);
            }
        });

        AlertDialog.Builder alert = new AlertDialog.Builder(this);


        // this is set the view from XML inside AlertDialog
        alert.setView(callDurationDialog);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setPositiveButton("SET", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                editTextCallDuration.setText(String.format(EmployConstantUtil.CALL_NUMBER_FORMAT, noPickerHr.getValue()) + ":" + String.format(EmployConstantUtil.CALL_NUMBER_FORMAT, noPickerMin.getValue()) + ":" + String.format(EmployConstantUtil.CALL_NUMBER_FORMAT, noPickerSec.getValue()));
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();


    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == RESULT_OK) {
                String selectedItem = data.getStringExtra(EmployConstantUtil.SELECTED_ITEM);
                String title = data.getStringExtra(EmployConstantUtil.TITLE);
                if (title.equals(titleAccountName)) {
                    editTextCallAccount.setText(selectedItem);
                    accountId = data.getLongExtra(EmployConstantUtil.KEY_ACCOUNT_ID, 0);
                } else if (title.equals(titleContactName)) {
                    editTextCallContact.setText(selectedItem);
                    contactId = data.getLongExtra(EmployConstantUtil.KEY_CONTACT_ID, 0);
                } else if (title.equals(titleCallType)) {
                    editTextCallType.setText(selectedItem);
                } else if (title.equals(titleCallType1)) {
                    editTextCallType1.setText(selectedItem);

                } else if (title.equals(titleCallPurpose)) {
                    editTextCallPurpose.setText(selectedItem);
                }
            }
        }
    }


    @Override
    public void alertDialogCallbackOk() {
        startActivity(new Intent(EditCallDetailActivity.this, CallListActivity.class));
        finish();
    }

    @Override
    public void alertDialogCallbackCancel() {

    }


}
