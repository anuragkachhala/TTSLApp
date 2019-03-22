package com.software.ttsl;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.software.ttsl.Interfacce.AlertDialogCallback;
import com.software.ttsl.Request.EventDataModel;
import com.software.ttsl.Request.Participant;
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
import com.software.ttsl.Utils.SharedPrefUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditEventActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, AlertDialogCallback {

    private static final int REQUEST_CODE_EVENT_TITLE=1000;
    private static final int REQUEST_CODE_PARTICIPANTS=2000;
    private static final int REQUEST_CODE_CONTACT = 4000;
    private static final int REQUEST_CODE_ACCOUNT = 5000;

    public static final String TAG = EditEventActivity.class.getName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.et_event_title)
    EditText editTextEventTitle;

    @BindView(R.id.et_event_location)
    EditText editTextEventLocation;

    @BindView(R.id.et_event_from)
    EditText editTextEventFrom;

    @BindView(R.id.et_event_from_time)
    EditText editTextEventFromTime;

    @BindView(R.id.et_event_to_time)
    EditText editTextEventToTime;

    @BindView(R.id.et_event_to)
    EditText editTextEventTo;

    @BindView(R.id.et_event_host)
    EditText editTextEventHost;

    @BindView(R.id.et_event_participants)
    EditText editTextEventParticipants;

    @BindView(R.id.et_event_contact)
    EditText editTextEventContact;

    @BindView(R.id.et_event_account)
    EditText editTextEventAccount;

    @BindView(R.id.et_event_description)
    EditText editTextEventDescription;

    @BindView(R.id.switch_event_all_day)
    Switch aSwitchEventAllDay;

    @BindView(R.id.linear_layout_event_host)
    LinearLayout linearLayoutEventHost;

    @BindView(R.id.linear_layout_event_participants)
    LinearLayout linearLayoutEventParticipants;

    @BindView(R.id.linear_layout_event_contact)
    LinearLayout linearLayoutEventContact;

    @BindView(R.id.linear_layout_event_account)
    LinearLayout linearLayoutEventAccount;

    @BindView(R.id.linear_layout_description_information)
    LinearLayout linearLayoutDescription;

    @BindView(R.id.linear_layout_event_from_time)
    LinearLayout linearLayoutEventFromTime;

    @BindView(R.id.linear_layout_event_to_time)
    LinearLayout linearLayoutEventToTime;

    @BindView(R.id.tv_event_contact)
    TextView textViewLabelContact;



    @BindView(R.id.tv_smart_view)
    TextView textViewSmartView;

    private String errorTitle, showAllFields, showSmartView, titleContactName, titleAccountName, title,titleLeadName;
    private AlertDialogManager alertDialogManager;
    private DataBaseAdapter dataBaseAdapter;
    private EventDataModel eventDataModel;
    private Intent intent;
    private int mYear, mDay, mMonth, mHour, mMinute;
    private long accountId, contactId, eventId,leadId;
    private String[] itemList;
    private long createdTime;
    private String createdBy;
    private Boolean isSync,isLead;
    private List<Participant> participants;
    private SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);


        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        SessionManager.setContext(getApplicationContext());
        sessionManager = SessionManager.getInstance();

        alertDialogManager = new AlertDialogManager();
        dataBaseAdapter = new DataBaseAdapter(this);
        participants = new ArrayList<Participant>();


        Intent intent = getIntent();
        eventId = intent.getLongExtra(EmployConstantUtil.KEY_EVENT_ID, 0);
        Log.v(TAG, "edit event id " + eventId);

        setClickListener();
        setStringResource();
        getEventByID();

    }

    private void getEventByID() {
       eventDataModel = dataBaseAdapter.getEventByID(eventId);
        setEventDetails(eventDataModel);
    }

    private void setEventDetails(EventDataModel eventDataModel) {
        editTextEventTitle.setText(eventDataModel.getTitle());
        editTextEventLocation.setText(eventDataModel.getLocation());
        aSwitchEventAllDay.setChecked(eventDataModel.getAllDay());
        editTextEventFrom.setText(DateAndTimeUtil.longToDate(eventDataModel.getFromDate(), EmployConstantUtil.DATE_FORMAT));
        editTextEventTo.setText(DateAndTimeUtil.longToDate(eventDataModel.getToDate(), EmployConstantUtil.DATE_FORMAT));
        editTextEventContact.setText(checkSelectItem(eventDataModel));
        textViewLabelContact.setText(setSelectedLabel(eventDataModel));
        editTextEventFromTime.setText(DateAndTimeUtil.longToTime(eventDataModel.getFromTime()));
        editTextEventToTime.setText(DateAndTimeUtil.longToTime(eventDataModel.getToTime()));
        editTextEventHost.setText(eventDataModel.getHost());
        editTextEventParticipants.setText(String.valueOf(eventDataModel.getNoOfParticipants()));
        editTextEventDescription.setText(eventDataModel.getDescription());
        editTextEventAccount.setText(eventDataModel.getAccount());
        createdTime = eventDataModel.getCreatedTime();
        createdBy = eventDataModel.getCreatedBy();
        isSync = eventDataModel.isSync();
        if(eventDataModel.getAllDay()){
            linearLayoutEventFromTime.setVisibility(View.GONE);
            linearLayoutEventToTime.setVisibility(View.GONE);
        }



    }


    private String checkSelectItem(EventDataModel eventDataModel) {

        return eventDataModel.getContact().isEmpty() ? eventDataModel.getLeadName().isEmpty() ? "" : eventDataModel.getLeadName() : eventDataModel.getContact();
    }

    private String setSelectedLabel(EventDataModel eventDataModel) {
        return eventDataModel.getContact().isEmpty() ? eventDataModel.getLeadName().isEmpty() ? titleContactName: titleLeadName: titleContactName;

    }


    private void setStringResource() {
        errorTitle = getResources().getString(R.string.err_msg_title_dialog);
        showAllFields = getResources().getString(R.string.footer_show_all_fields);
        showSmartView = getResources().getString(R.string.footer_smart_view);
        titleContactName = getResources().getString(R.string.title_contact_list_activity);
        titleAccountName = getResources().getString(R.string.title_account_list_activity);
        titleLeadName = getResources().getString(R.string.title_lead_list_activity);

    }


    private void setClickListener() {
        editTextEventAccount.setOnClickListener(this);
        editTextEventContact.setOnClickListener(this);
        editTextEventHost.setOnClickListener(this);
        editTextEventParticipants.setOnClickListener(this);
        textViewSmartView.setOnClickListener(this);
        aSwitchEventAllDay.setOnCheckedChangeListener(this);
        editTextEventFromTime.setOnClickListener(this);
        editTextEventToTime.setOnClickListener(this);
        editTextEventFrom.setOnClickListener(this);
        editTextEventTo.setOnClickListener(this);


    }


    public void openCalender(final View view) {

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker dateView, int year, int monthOfYear, int dayOfMonth) {
                String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                if (view.getId() == R.id.et_event_from) {
                    editTextEventFrom.setText(DateAndTimeUtil.dateToString(date));
                } else {
                    editTextEventTo.setText(DateAndTimeUtil.dateToString(date));
                }


                //textViewTaskDueDate.setText(DateAndTimeUtil.dateToString(date));

            }
        }, mYear, mMonth, mDay);

        datePickerDialog.show();


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.et_event_account:
                startNewActivity(titleAccountName, AccountNameListActivity.class,REQUEST_CODE_CONTACT);
                break;
            case R.id.et_event_contact:
                startNewActivity(titleContactName, ContactNameListActivity.class,REQUEST_CODE_CONTACT);
                break;
            case R.id.et_event_participants:
                intent = new Intent(this, AddParticipantsActivity.class);
                if (editTextEventParticipants.getText().toString().isEmpty() || editTextEventParticipants.getText().toString().equals("0")) {
                    intent.putExtra("isList", false);
                    intent.putExtra(EmployConstantUtil.KEY_EVENT_ID, eventId);
                } else {
                    intent.putExtra("isList", true);
                    intent.putExtra(EmployConstantUtil.KEY_EVENT_ID, eventId);
                }

                startActivityForResult(intent, REQUEST_CODE_PARTICIPANTS);
                break;
            case R.id.tv_smart_view:
                startSmartView();
                break;
            case R.id.et_event_from_time:
                openTimePicker(editTextEventFromTime);
                break;
            case R.id.et_event_to_time:
                openTimePicker(editTextEventToTime);
                break;
            case R.id.et_event_from:
                openCalender(editTextEventFrom);
                break;
            case R.id.et_event_to:
                openCalender(editTextEventTo);
                break;
            case R.id.et_event_host:
                itemList = new String[]{"Test"};
                title = "Test";
                startNewActivity(title, itemList,REQUEST_CODE_EVENT_TITLE);
                break;
        }


    }

    private void startNewActivity(String title, String[] itemList,int requestCode) {

        intent = new Intent(this, CustomListActivity.class);
        intent.putExtra(EmployConstantUtil.ITEM_LIST, itemList);
        intent.putExtra(EmployConstantUtil.TITLE, title);
        startActivityForResult(intent, requestCode);

    }

    private void startNewActivity(String title, Class<?> cls,int requestCode) {
        intent = new Intent(EditEventActivity.this, cls);
        startActivityForResult(intent, requestCode);
    }

    private void startSmartView() {
        if (textViewSmartView.getText().toString().equals(showSmartView)) {
            textViewSmartView.setText(showAllFields);
            linearLayoutDescription.setVisibility(View.GONE);
            linearLayoutEventAccount.setVisibility(View.GONE);
            linearLayoutEventContact.setVisibility(View.GONE);
            linearLayoutEventHost.setVisibility(View.GONE);
            linearLayoutEventParticipants.setVisibility(View.GONE);


        } else {
            textViewSmartView.setText(showSmartView);
            linearLayoutDescription.setVisibility(View.VISIBLE);
            linearLayoutEventAccount.setVisibility(View.VISIBLE);
            linearLayoutEventContact.setVisibility(View.VISIBLE);
            linearLayoutEventHost.setVisibility(View.VISIBLE);
            linearLayoutEventParticipants.setVisibility(View.VISIBLE);


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_event, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.add_event:
                if (checkValidation())
                    updateEventData();

                break;
        }

        return super.onOptionsItemSelected(item);


    }

    private ArrayList<Participant> addParticipants() {
        return dataBaseAdapter.getAllParticipants(eventId);
    }

    private String checkSelectListItem(String selectedItem,String label) {

        return selectedItem.equals(EmployConstantUtil.NOTING_SELECTED_VALUE) ? "" : textViewLabelContact.getText().toString().equals(label)? selectedItem : "";
    }

    private void updateEventData() {
        eventDataModel = new EventDataModel();
        eventDataModel.setEventId(eventId);
        eventDataModel.setTitle(editTextEventTitle.getText().toString().trim());
        eventDataModel.setLocation(editTextEventLocation.getText().toString().trim());
        eventDataModel.setAllDay(aSwitchEventAllDay.isChecked());
        eventDataModel.setContact(checkSelectListItem(editTextEventContact.getText().toString().trim(),titleContactName));
        eventDataModel.setLeadName(checkSelectListItem(editTextEventContact.getText().toString().trim(),titleLeadName));
        eventDataModel.setAccount(editTextEventAccount.getText().toString().trim());
        eventDataModel.setAccountId(accountId);
        eventDataModel.setContactId(contactId);
        eventDataModel.setLeadId(leadId);
        eventDataModel.setHost(editTextEventHost.getText().toString());
        eventDataModel.setNoOfParticipants(checkIntegerIsNull(editTextEventParticipants.getText().toString()));
        eventDataModel.setCreatedBy(createdBy);
        eventDataModel.setModifyBy(sessionManager.getUserKeyId());
        eventDataModel.setParticipants(addParticipants());
        eventDataModel.setFromDate(DateAndTimeUtil.dateToLong(editTextEventFrom.getText().toString().trim(), EmployConstantUtil.DATE_FORMAT));
        eventDataModel.setToDate(DateAndTimeUtil.dateToLong(editTextEventTo.getText().toString().trim(), EmployConstantUtil.DATE_FORMAT));
        eventDataModel.setFromTime(DateAndTimeUtil.timeToLong(editTextEventFromTime.getText().toString().trim(), EmployConstantUtil.TIME_FORMAT));
        eventDataModel.setToTime(DateAndTimeUtil.timeToLong(editTextEventToTime.getText().toString(), EmployConstantUtil.TIME_FORMAT));
        eventDataModel.setCreatedTime(createdTime);
        eventDataModel.setModifyTime(DateAndTimeUtil.currentTimeStamp());
        eventDataModel.setDescription(editTextEventDescription.getText().toString());
        eventDataModel.setSync(isSync);
        dataBaseAdapter.openDataBase();
        if (!isSync) {

            if (checkConnection()) {
                updateEventDataToServer();
            } else {
                dataBaseAdapter.updateEvent(eventDataModel, eventId);
                alertDialogManager.showAlertDialogMessage(this, "", "Event is Updated ", false, this);
            }


        } else {

            if (checkConnection()) {
                updateEventDataToServer();
            } else {
                Toast.makeText(this, "Please check your Internet connection ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private ArrayList<Participant> updateParticipants() {
        return dataBaseAdapter.getAllParticipants(eventId);
    }

    private void updateEventDataToServer() {
        DialogUtitlity.showLoading(EditEventActivity.this);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> listCall = apiInterface.addEvent(eventDataModel,sessionManager.getUserKeyId());
        Log.e("addEvent", "inside updateEventDataToServer");
        listCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                DialogUtitlity.hideLoading();
                Log.e("addEvent", "inside updateEventDataToServer");
                int statusCode = response.code();
                Log.e("addEvent", String.valueOf(statusCode));

                if (statusCode == 200) {
                    if (response.body() instanceof String) {
                        eventDataModel.setSync(true);
                    }
                } else if (statusCode == 201) {
                    eventDataModel.setSync(true);
                } else if (statusCode == 500) {
                    eventDataModel.setSync(isSync);
                }

                int result = dataBaseAdapter.updateEvent(eventDataModel, eventId);
                alertDialogManager.showAlertDialogMessage(EditEventActivity.this, "", "Event is updated ", false, EditEventActivity.this);

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                DialogUtitlity.hideLoading();
                eventDataModel.setSync(isSync);
                int result = dataBaseAdapter.updateEvent(eventDataModel, eventId);
                alertDialogManager.showAlertDialogMessage(EditEventActivity.this, "", "Event is updated ", false, EditEventActivity.this);

            }
        });
    }

    private boolean checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        if (!isConnected) {

            return false;
        }

        return true;
    }


    private boolean checkValidation() {

        if (editTextEventTitle.getText().toString().trim().isEmpty()) {
            showErrorDialog("Title should not be empty");
            return false;
        }
        return true;
    }


    public void showErrorDialog(String errMessage) {
        alertDialogManager.showAlertDialog(this, errorTitle, errMessage, false);

    }

    private long checkIntegerIsNull(String value) {
        return value.isEmpty() ? 0 : Long.parseLong(value);
    }

    public void openTimePicker(final View view) {
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
                        if (view.getId() == R.id.et_event_from_time) {
                            editTextEventFromTime.setText(time);
                        } else {
                            editTextEventToTime.setText(time);
                        }
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();


    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

        if (isChecked) {
            linearLayoutEventFromTime.setVisibility(View.GONE);
            linearLayoutEventToTime.setVisibility(View.GONE);

        } else {
            linearLayoutEventFromTime.setVisibility(View.VISIBLE);
            linearLayoutEventToTime.setVisibility(View.VISIBLE);
        }

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String selectedItem = "";
        if(data!=null) {
            selectedItem  = data.getStringExtra(EmployConstantUtil.SELECTED_ITEM);
        }
        if(resultCode ==RESULT_OK)
        {
            switch (requestCode){
                case REQUEST_CODE_EVENT_TITLE:
                    editTextEventHost.setText(title);
                    break;
                case REQUEST_CODE_ACCOUNT:
                    editTextEventAccount.setText(selectedItem);
                    accountId = data.getLongExtra(EmployConstantUtil.KEY_ACCOUNT_ID, 0);
                    break;
                case REQUEST_CODE_CONTACT:
                    editTextEventContact.setText(selectedItem);
                    isLead = data.getBooleanExtra(EmployConstantUtil.KEY_IS_LEAD,false);
                    if(isLead){
                        leadId = data.getLongExtra(EmployConstantUtil.KEY_CONTACT_ID, 0);
                        textViewLabelContact.setText(titleLeadName);
                        contactId = 0;
                    }else {
                        contactId = data.getLongExtra(EmployConstantUtil.KEY_CONTACT_ID, 0);
                        textViewLabelContact.setText(titleContactName);
                        leadId = 0;
                    }
                    break;
                case REQUEST_CODE_PARTICIPANTS:
                    editTextEventParticipants.setText(String.valueOf(data.getIntExtra(EmployConstantUtil.KEY_PARTICIPANT_LIST_SIZE,0)));
                    break;
            }
        }

    }

    @Override
    public void alertDialogCallbackOk() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();

    }

    @Override
    public void alertDialogCallbackCancel() {

    }
}
