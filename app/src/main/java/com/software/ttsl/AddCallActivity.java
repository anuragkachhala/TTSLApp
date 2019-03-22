package com.software.ttsl;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

import com.software.ttsl.Fragment.DatePickerFragment;
import com.software.ttsl.Interfacce.AlertDialogCallback;
import com.software.ttsl.Request.CallDataModel;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.AlertDialogManager;
import com.software.ttsl.Utils.ConnectivityReceiver;
import com.software.ttsl.Utils.DateAndTimeUtil;
import com.software.ttsl.Utils.EmployConstantUtil;
import com.software.ttsl.Utils.SessionManager;
import com.software.ttsl.Utils.SharedPrefUtil;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCallActivity extends AppCompatActivity implements View.OnClickListener, AlertDialogCallback  {

    public static final int DATE_PICKER_FRAGMENT = 1;
    private static final String TAG = AddCallActivity.class.getName();
    private static final int REQUEST_CODE_TYPE = 1000;
    private static final int REQUEST_CODE_CALL_PURPOSE = 2000;
    private static final int REQUEST_CODE_CALL_TYPE = 3000;
    private static final int REQUEST_CODE_CONTACT = 4000;
    private static final int REQUEST_CODE_ACCOUNT = 5000;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_call_contact)
    TextView textViewCallContact;
    @BindView(R.id.et_call_subject)
    EditText editTextCallSubject;
    @BindView(R.id.et_call_purpose)
    TextView textViewCallPurpose;
    @BindView(R.id.et_call_account)
    TextView textViewCallAccount;
    @BindView(R.id.et_call_type1)
    TextView textViewCallType1;
    @BindView(R.id.et_call_type)
    TextView TextViewCallType;
    @BindView(R.id.et_call_duration)
    TextView textViewCallDuration;
    @BindView(R.id.et_call_description)
    EditText editTextCallDescription;
    @BindView(R.id.et_call_result)
    EditText editTextCallResult;
    @BindView(R.id.et_call_start_date)
    TextView textViewCallStartDate;
    @BindView(R.id.et_call_start_time)
    TextView textViewCallStrartTime;
    @BindView(R.id.tv_call_subject)
    TextView textViewCallSubject;
    @BindView(R.id.tv_call_type1)
    TextView textViewCallType;
    @BindView(R.id.tv_call_start_date)
    TextView textViewCallStartDateLabel;
    @BindView(R.id.tv_call_start_time)
    TextView textViewCallStartTimeLabel;
    @BindView(R.id.tv_call_duration)
    TextView textViewCallDurationLabel;
    @BindView(R.id.tv_call_contact)
    TextView textViewCallContactLabel;
    @BindView(R.id.tv_error_subject)
    TextView textViewErrorSubject;
    @BindView(R.id.tv_error_call_duration)
    TextView textViewErrorCallDuration;
    TextView textViewError;
    private String emptyAccountNameErrorMsg, createdBy, emptyCallDuration, emptySubjectNameErrorMsg, emptyClosingDateErrorMsg, emptyStageErrorMsg, showSmartView, showAllFields, errorTitle, titleDealType, titleStage, titleDealOwner, titleAccountName, titleCallPurpose, titleCallType, titleCallType1, titleContactName, titleLeadName;
    private SharedPrefUtil sharedPrefUtil;
    private AlertDialogManager alertDialogManager;
    private CallDataModel callDataModel;
    private DataBaseAdapter dataBaseAdapter;
    private Intent intent;
    private String[] itemList, callType, callType1, callPurpose;
    private String title;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private long accountId, contactId, leadId, callId, createdTime;
    private boolean isLead, editCall = false, isSync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_call);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SessionManager.setContext(getApplicationContext());


        alertDialogManager = new AlertDialogManager();
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        dataBaseAdapter = new DataBaseAdapter(this);
        getStringResources();

        Intent intent = getIntent();
        callId = intent.getLongExtra(EmployConstantUtil.KEY_CALL_ID, 0);
        if (callId != 0) {
            editCall = true;
            getCallData();
            toolbar.setTitle("Edit Call");
        }

        Log.v(TAG, "edit call id " + callId);


        SharedPrefUtil.setContext(getApplicationContext());
        sharedPrefUtil = SharedPrefUtil.getInstance();

        setClickListener();


        setCurrentDate();


    }

    private void getCallData() {
        callDataModel = dataBaseAdapter.getCallByID(callId);
        setCallData();
    }

    private void setCallData() {
        TextViewCallType.setText(callDataModel.getType());
        textViewCallContact.setText(checkSelectItem(callDataModel));
        textViewCallContactLabel.setText(setSelectedLabel(callDataModel));
        textViewCallAccount.setText(callDataModel.getAccount());
        textViewCallPurpose.setText(callDataModel.getCallPurpose());
        editTextCallSubject.setText(callDataModel.getSubject());
        textViewCallType1.setText(callDataModel.getCallType());
        textViewCallStartDate.setText(DateAndTimeUtil.longToDate(callDataModel.getCallStartDate(), EmployConstantUtil.DATE_FORMAT));
        textViewCallStrartTime.setText(DateAndTimeUtil.longToTime(callDataModel.getCallStartTime(), EmployConstantUtil.TIME_FORMAT));
        textViewCallDuration.setText(DateAndTimeUtil.longToTime(callDataModel.getCallDuration(), EmployConstantUtil.TIME_FORMAT_DURATION));
        editTextCallDescription.setText(callDataModel.getDescription());
        editTextCallResult.setText(callDataModel.getCallResult());
        contactId = callDataModel.getContactId();
        accountId = callDataModel.getAccountId();
        createdTime = callDataModel.getCreatedTime();
        createdBy = callDataModel.getCreatedBy();
        isSync = callDataModel.isSync();
    }

    private String checkSelectItem(CallDataModel callDataModel) {

        return callDataModel.getContact().isEmpty() ? callDataModel.getContactLeadName().isEmpty() ? "" : callDataModel.getContactLeadName() : callDataModel.getContact();
    }

    private String setSelectedLabel(CallDataModel callDataModel) {
        return callDataModel.getContact().isEmpty() ? callDataModel.getContactLeadName().isEmpty() ? titleContactName : titleLeadName : titleContactName;

    }

    private void setCurrentDate() {
        textViewCallStartDate.setText(DateAndTimeUtil.getCurrentDateUsingCalendar());
       // textViewCallStrartTime.setText(DateAndTimeUtil.getCurrentTimeUsingCalendar());
        textViewCallType1.setText(callType1[0]);
        TextViewCallType.setText(callType[0]);

    }

    private void getStringResources() {
        textViewCallType.setText(Html.fromHtml(EmployConstantUtil.ASTERISK + getResources().getString(R.string.label_call_type1)));
        textViewCallSubject.setText(Html.fromHtml(EmployConstantUtil.ASTERISK + getResources().getString(R.string.label_call_subject)));
        textViewCallDurationLabel.setText(Html.fromHtml(EmployConstantUtil.ASTERISK + getResources().getString(R.string.label_call_duration)));
        textViewCallStartDateLabel.setText(Html.fromHtml(EmployConstantUtil.ASTERISK + getResources().getString(R.string.label_call_start_date)));
        textViewCallStartTimeLabel.setText(Html.fromHtml(EmployConstantUtil.ASTERISK + getResources().getString(R.string.label_call_start_time)));
        errorTitle = getResources().getString(R.string.err_msg_title_dialog);
        showAllFields = getResources().getString(R.string.footer_show_all_fields);
        showSmartView = getResources().getString(R.string.footer_smart_view);
        titleContactName = getResources().getString(R.string.title_contact_list_activity);
        titleAccountName = getResources().getString(R.string.title_account_list_activity);
        titleLeadName = getResources().getString(R.string.title_lead_list_activity);
        emptySubjectNameErrorMsg = getResources().getString(R.string.msg_enter_subject);
        emptyCallDuration = getResources().getString(R.string.msg_empty_call_duration);
        titleCallPurpose = getResources().getString(R.string.label_call_purpose);
        callType = getResources().getStringArray(R.array.list_call_type);
        callType1 = getResources().getStringArray(R.array.list_call_type1);
        titleCallType1 = getResources().getString(R.string.label_call_type1);
        titleCallType = getResources().getString(R.string.label_call_type);
        callPurpose = getResources().getStringArray(R.array.list_call_purpose);


    }

    private void setClickListener() {
        textViewCallAccount.setOnClickListener(this);
        textViewCallContact.setOnClickListener(this);
        textViewCallDuration.setOnClickListener(this);
        textViewCallPurpose.setOnClickListener(this);
        TextViewCallType.setOnClickListener(this);
        textViewCallType1.setOnClickListener(this);
        textViewCallStrartTime.setOnClickListener(this);
        textViewCallStartDate.setOnClickListener(this);

    }

    private String checkSelectListItem(String selectedItem) {

        return selectedItem.equals(EmployConstantUtil.NOTING_SELECTED_VALUE) ? "" : selectedItem;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.et_call_type:
                startNewActivity(titleCallType, callType, REQUEST_CODE_TYPE);
                break;
            case R.id.et_call_contact:
                startNewActivity(titleContactName, ContactNameListActivity.class, REQUEST_CODE_CONTACT);
                break;
            case R.id.et_call_account:
                startNewActivity(titleAccountName, AccountNameListActivity.class, REQUEST_CODE_ACCOUNT);
                break;
            case R.id.et_call_purpose:
                startNewActivity(titleCallPurpose, callPurpose, REQUEST_CODE_CALL_PURPOSE);
                break;
            case R.id.et_call_type1:
                startNewActivity(titleCallType1, callType1, REQUEST_CODE_CALL_TYPE);
                break;
            case R.id.et_call_duration:
                showCallDurationDialog();
                break;
            case R.id.et_call_start_date:
               textViewCallStrartTime.setText("");
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

                        Calendar datetime = Calendar.getInstance();
                        datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        datetime.set(Calendar.MINUTE, minute);

                        if (DateAndTimeUtil.dateToLong(textViewCallStartDate.getText().toString(),"E, MMM dd yyyy")> DateAndTimeUtil.currentTimeStamp() || datetime.getTimeInMillis() >= c.getTimeInMillis() ) {
                            //it's after current
                            int hour = hourOfDay % 12;
                            textViewCallStrartTime.setText(String.format("%02d:%02d %s", hour == 0 ? 12 : hour,
                                    minute, hourOfDay < 12 ? "am" : "pm"));
                        } else {
                            //it's before current'
                            Toast.makeText(getApplicationContext(), "Invalid Time", Toast.LENGTH_LONG).show();
                        }



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

                textViewCallStartDate.setText(DateAndTimeUtil.dateToString(date));
                //textViewTaskDueDate.setText(DateAndTimeUtil.dateToString(date));
            }
        }, mYear, mMonth, mDay);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
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
                    if (!editCall) {
                        alertDialogManager.showAlertDialogMessage(this, "", "Are you sure to create a Call", false, this);
                    } else {
                        alertDialogManager.showAlertDialogMessage(this, "", "Are you sure to update a Call", false, this);
                    }

                break;



        }

        return super.onOptionsItemSelected(item);


    }

    private void addCallData() {
        callDataModel = new CallDataModel();
        if (editCall) {
            callDataModel.setCallId(callId);
        } else {
            callDataModel.setCallId(DateAndTimeUtil.currentTimeStamp());
        }

        callDataModel.setType(TextViewCallType.getText().toString());
        callDataModel.setSubject(editTextCallSubject.getText().toString());
        callDataModel.setAccount(textViewCallAccount.getText().toString());
        callDataModel.setAccountId(accountId);
        callDataModel.setContact(checkSelectListItem(textViewCallContact.getText().toString(), titleContactName));
        callDataModel.setContactId(contactId);
        callDataModel.setCallType(textViewCallType1.getText().toString());
        callDataModel.setLeadId(leadId);
        callDataModel.setContactLeadName(checkSelectListItem(textViewCallContact.getText().toString(), titleLeadName));
        callDataModel.setDescription(editTextCallDescription.getText().toString());
        callDataModel.setCallResult(editTextCallResult.getText().toString());
        callDataModel.setCallPurpose(checkSelectListItem(textViewCallPurpose.getText().toString()));
        callDataModel.setCreatedBy("TEST");
        callDataModel.setModifiedBy("TEST");
        callDataModel.setCreatedTime(DateAndTimeUtil.currentTimeStamp());
        callDataModel.setModifiedTime(DateAndTimeUtil.currentTimeStamp());
        callDataModel.setCallStartDate(DateAndTimeUtil.dateToLong(textViewCallStartDate.getText().toString().trim(), EmployConstantUtil.DATE_FORMAT));
        callDataModel.setCallDuration(checkCallDuration(textViewCallDuration.getText().toString().trim()));
        callDataModel.setCallStartTime(DateAndTimeUtil.timeToLong(textViewCallStrartTime.getText().toString().trim(), EmployConstantUtil.TIME_FORMAT));
        dataBaseAdapter.openDataBase();

        if (editCall) {

            if (!isSync) {

                if (checkConnection()) {
                    addCallDataToServer();
                } else {
                    dataBaseAdapter.updateCall(callDataModel, callId);
                   // alertDialogManager.showAlertDialogMessage(this, "", "Call is Updated ", false, this);
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }


            } else {

                if (checkConnection()) {
                    addCallDataToServer();
                } else {
                    Toast.makeText(this, "Please check your Internet connection ", Toast.LENGTH_SHORT).show();
                }
            }

        } else {

            if (checkConnection()) {
                addCallDataToServer();
            } else {
                callDataModel.setSync(false);
                dataBaseAdapter.addCall(callDataModel);
               // alertDialogManager.showAlertDialogMessage(AddCallActivity.this, "", "Call is created ", false, AddCallActivity.this);
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        }


        /*Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();*/


    }

    private String checkSelectListItem(String selectedItem, String label) {

        return selectedItem.equals(EmployConstantUtil.NOTING_SELECTED_VALUE) ? "" : textViewCallContactLabel.getText().toString().trim().equals(label) ? selectedItem : "";
    }

    private long checkCallDuration(String callDuration) {

        return callDuration.isEmpty() ? 0 : DateAndTimeUtil.dateToLong(callDuration, EmployConstantUtil.TIME_FORMAT_DURATION);
    }


    private void addCallDataToServer() {
//        DialogUtitlity.showLoading(AddCallActivity.this);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        final Call<String> response = apiService.addCall(callDataModel);
        response.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //DialogUtitlity.hideLoading();
                int statusCode = response.code();
                switch (statusCode) {
                    case 201:
                        callDataModel.setSync(true);


                        break;
                    case 400:
                        callDataModel.setSync(false);
                        break;
                }


               updateCallActivity();


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                //DialogUtitlity.hideLoading();
                Log.e(TAG, "NOT CREATED");
                updateCallActivity();

            }
        });

    }


    private void updateCallActivity(){
        if (!editCall) {
            dataBaseAdapter.addCall(callDataModel);
            alertDialogManager.showAlertDialogMessage(AddCallActivity.this, "", "Call is created ", false, AddCallActivity.this);
        } else {
            callDataModel.setSync(true);
            int result = dataBaseAdapter.updateCall(callDataModel, callId);
            alertDialogManager.showAlertDialogMessage(AddCallActivity.this, "", "Call is updated ", false, AddCallActivity.this);
        }

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();


    }

    private boolean checkValidation() {
        if (textViewError != null) {
            textViewError.setVisibility(View.GONE);
        }
        if (editTextCallSubject.getText().toString().trim().isEmpty()) {

            showErrorMsg(textViewErrorSubject, emptySubjectNameErrorMsg, editTextCallSubject);
            return false;
        }
        if (textViewCallDuration.getText().toString().trim().isEmpty()) {
            showErrorMsg(textViewErrorCallDuration, emptyCallDuration, null);
            return false;
        }
        return true;
    }


    private boolean showErrorMsg(TextView textView, String errorMessage, EditText editText) {
        if (editText != null) {
            editText.requestFocus();
        }
        textView.setText(errorMessage);
        textView.setVisibility(View.VISIBLE);
        textViewError = textView;
        return false;
    }


    private void startNewActivity(String title, String[] itemList, int requestCode) {

        intent = new Intent(AddCallActivity.this, CustomListActivity.class);
        intent.putExtra(EmployConstantUtil.ITEM_LIST, itemList);
        intent.putExtra(EmployConstantUtil.TITLE, title);
        startActivityForResult(intent, requestCode);

    }

    private void startNewActivity(String title, Class<?> cls, int requestCode) {
        intent = new Intent(AddCallActivity.this, cls);
        startActivityForResult(intent, requestCode);
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

                textViewCallDuration.setText(String.format(EmployConstantUtil.CALL_NUMBER_FORMAT, noPickerHr.getValue()) + ":" + String.format(EmployConstantUtil.CALL_NUMBER_FORMAT, noPickerMin.getValue()) + ":" + String.format(EmployConstantUtil.CALL_NUMBER_FORMAT, noPickerSec.getValue()));
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();


    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String selectedItem = "";
        if (data != null) {
            selectedItem = data.getStringExtra(EmployConstantUtil.SELECTED_ITEM);
        }
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_TYPE:
                    TextViewCallType.setText(selectedItem);
                    break;
                case REQUEST_CODE_CALL_TYPE:
                    textViewCallType1.setText(selectedItem);
                    break;
                case REQUEST_CODE_CALL_PURPOSE:
                    textViewCallPurpose.setText(selectedItem);
                    break;
                case REQUEST_CODE_ACCOUNT:
                    textViewCallAccount.setText(selectedItem);
                    accountId = data.getLongExtra(EmployConstantUtil.KEY_ACCOUNT_ID, 0);
                    break;
                case REQUEST_CODE_CONTACT:
                    textViewCallContact.setText(selectedItem);
                    isLead = data.getBooleanExtra(EmployConstantUtil.KEY_IS_LEAD, false);
                    if (isLead) {
                        leadId = data.getLongExtra(EmployConstantUtil.KEY_CONTACT_ID, 0);
                        textViewCallContactLabel.setText(titleLeadName);
                        contactId = 0;
                    } else {
                        contactId = data.getLongExtra(EmployConstantUtil.KEY_CONTACT_ID, 0);
                        textViewCallContactLabel.setText(titleContactName);
                        leadId = 0;
                    }
                    break;


            }
        }

    }

    @Override
    public void alertDialogCallbackOk() {
       addCallData();
    }

    @Override
    public void alertDialogCallbackCancel() {

    }


}
