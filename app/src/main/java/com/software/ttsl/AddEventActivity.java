package com.software.ttsl;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import com.software.ttsl.Utils.EmployConstantUtil;
import com.software.ttsl.Utils.EmployeeValidationUtil;
import com.software.ttsl.Utils.SessionManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEventActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, AlertDialogCallback {
    public static final String TAG = AddEventActivity.class.getName();
    private static final int REQUEST_CODE_EVENT_TITLE = 1000;
    private static final int REQUEST_CODE_PARTICIPANTS = 2000;
    private static final int REQUEST_CODE_CONTACT = 4000;
    private static final int REQUEST_CODE_ACCOUNT = 5000;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_event_title)
    TextView textViewEventTitle;

    @BindView(R.id.et_event_title)
    EditText editTextEventTitle;

    @BindView(R.id.et_event_location)
    EditText editTextEventLocation;

    @BindView(R.id.et_event_from)
    TextView textViewEventFrom;

    @BindView(R.id.et_event_from_time)
    TextView textViewEventFromTime;

    @BindView(R.id.et_event_to_time)
    TextView textViewEventToTime;

    @BindView(R.id.et_event_to)
    TextView textViewEventTo;

    @BindView(R.id.et_event_host)
    TextView textViewEventHost;

    @BindView(R.id.et_event_participants)
    TextView textViewEventParticipants;

    @BindView(R.id.et_event_contact)
    TextView editTextEventContact;

    @BindView(R.id.et_event_account)
    TextView textViewEventAccount;

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

    @BindView(R.id.tv_error_event_title)
    TextView textViewErrorEventTitle;


    private String errorTitle, showAllFields, showSmartView, titleContactName, titleAccountName, title, titleLeadName, createdBy;
    private AlertDialogManager alertDialogManager;
    private DataBaseAdapter dataBaseAdapter;

    private EventDataModel eventDataModel;
    private Intent intent;
    private int mYear, mDay, mMonth, mHour, mMinute;
    private long accountId, contactId, eventId, leadId,createdTime;
    private String[] itemList;
    private List<Participant> participants;
    private boolean isLead = false;
    private boolean isList = false;
    private SessionManager sessionManager;
    private boolean editEvent = false,isSync =false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);





        alertDialogManager = new AlertDialogManager();
        dataBaseAdapter = new DataBaseAdapter(this);
        SessionManager.setContext(getApplicationContext());
        sessionManager = SessionManager.getInstance();
        createdBy = sessionManager.getUserKeyId();
        participants = new ArrayList<Participant>();


        setClickListener();
        setStringResource();




        Intent intent = getIntent();
        eventId = intent.getLongExtra(EmployConstantUtil.KEY_EVENT_ID, 0);
        Log.v(TAG, "edit event id " + eventId);
        if (eventId != 0) {
            editEvent = true;
        }


        if (editEvent) {
            getEventByID();
            toolbar.setTitle("Edit Event ");
        }


        /*eventId = DateAndTimeUtil.currentTimeStamp();*/








        setCurrentDate();
    }

    private void getEventByID() {
        eventDataModel = dataBaseAdapter.getEventByID(eventId);
        setEventDetails(eventDataModel);
    }




    private void setEventDetails(EventDataModel eventDataModel) {
        editTextEventTitle.setText(eventDataModel.getTitle());
        editTextEventLocation.setText(eventDataModel.getLocation());
        aSwitchEventAllDay.setChecked(eventDataModel.getAllDay());
        textViewEventFrom.setText(DateAndTimeUtil.longToDate(eventDataModel.getFromDate(), EmployConstantUtil.DATE_FORMAT));
        textViewEventTo.setText(DateAndTimeUtil.longToDate(eventDataModel.getToDate(), EmployConstantUtil.DATE_FORMAT));
        editTextEventContact.setText(checkSelectItem(eventDataModel));
        textViewLabelContact.setText(setSelectedLabel(eventDataModel));
        textViewEventFromTime.setText(DateAndTimeUtil.longToTime(eventDataModel.getFromTime()));
        textViewEventToTime.setText(DateAndTimeUtil.longToTime(eventDataModel.getToTime()));
        textViewEventHost.setText(eventDataModel.getHost());
        textViewEventParticipants.setText(String.valueOf(eventDataModel.getNoOfParticipants()));
        editTextEventDescription.setText(eventDataModel.getDescription());
        textViewEventAccount.setText(eventDataModel.getAccount());
        createdTime = eventDataModel.getCreatedTime();
        createdBy = eventDataModel.getCreatedBy();
        isSync = eventDataModel.isSync();
        if(eventDataModel.getAllDay()){
            linearLayoutEventFromTime.setVisibility(View.GONE);
            linearLayoutEventToTime.setVisibility(View.GONE);
        }

        accountId =  eventDataModel.getAccountId();

    }

    private String checkSelectItem(EventDataModel eventDataModel) {

        return eventDataModel.getContact().isEmpty() ? eventDataModel.getLeadName().isEmpty() ? "" : eventDataModel.getLeadName() : eventDataModel.getContact();
    }


    private String setSelectedLabel(EventDataModel eventDataModel) {
        return eventDataModel.getContact().isEmpty() ? eventDataModel.getLeadName().isEmpty() ? titleContactName: titleLeadName: titleContactName;

    }

    private void setCurrentDate() {
        textViewEventFrom.setText(DateAndTimeUtil.getCurrentDateUsingCalendar());
        textViewEventTo.setText(DateAndTimeUtil.getCurrentDateUsingCalendar());
        textViewEventFromTime.setText(DateAndTimeUtil.getCurrentTimeUsingCalendar());
        textViewEventToTime.setText(DateAndTimeUtil.getCurrentTimeUsingCalendar());
    }

    private void setStringResource() {
        textViewEventTitle.setText(Html.fromHtml(EmployConstantUtil.ASTERISK+ getResources().getString(R.string.label_event_title)));
        errorTitle = getResources().getString(R.string.err_msg_title_dialog);
        showAllFields = getResources().getString(R.string.footer_show_all_fields);
        showSmartView = getResources().getString(R.string.footer_smart_view);
        titleContactName = getResources().getString(R.string.title_contact_list_activity);
        titleLeadName = getResources().getString(R.string.title_lead_list_activity);
        titleAccountName = getResources().getString(R.string.title_account_list_activity);


    }

    private void setClickListener() {
        textViewEventAccount.setOnClickListener(this);
        editTextEventContact.setOnClickListener(this);
        textViewEventHost.setOnClickListener(this);
        textViewEventParticipants.setOnClickListener(this);
        textViewSmartView.setOnClickListener(this);
        aSwitchEventAllDay.setOnCheckedChangeListener(this);
        textViewEventFromTime.setOnClickListener(this);
        textViewEventToTime.setOnClickListener(this);
        textViewEventFrom.setOnClickListener(this);
        textViewEventTo.setOnClickListener(this);


    }


    public void openCalender(final View view, long date) {

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker dateView, int year, int monthOfYear, int dayOfMonth) {
                String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                if (view.getId() == R.id.et_event_from) {
                    textViewEventFrom.setText(DateAndTimeUtil.dateToString(date));
                } else {
                    textViewEventTo.setText(DateAndTimeUtil.dateToString(date));
                }


                //textViewTaskDueDate.setText(DateAndTimeUtil.dateToString(date));

            }
        }, mYear, mMonth, mDay);


        if (date != 0) {
            datePickerDialog.getDatePicker().setMinDate(date);
        }
        datePickerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        datePickerDialog.setTitle("");

        datePickerDialog.show();


    }


    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.et_event_account:
                startNewActivity(titleAccountName, AccountNameListActivity.class, REQUEST_CODE_ACCOUNT);
                break;
            case R.id.et_event_contact:
                startNewActivity(titleContactName, ContactNameListActivity.class, REQUEST_CODE_CONTACT);
                break;
            case R.id.et_event_participants:
                intent = new Intent(this, AddParticipantsActivity.class);
                if (textViewEventParticipants.getText().toString().isEmpty() || textViewEventParticipants.getText().toString().equals("0")) {
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
                openTimePicker(textViewEventFromTime);
                break;
            case R.id.et_event_to_time:
                openTimePicker(textViewEventToTime);
                break;
            case R.id.et_event_from:
                openCalender(textViewEventFrom, System.currentTimeMillis() - 1000);
                textViewEventTo.setText("");
                textViewEventToTime.setText("");
                break;
            case R.id.et_event_to:
                openCalender(textViewEventTo, DateAndTimeUtil.dateToLong(textViewEventFrom.getText().toString(), "E, MMM dd yyyy"));
                break;
            case R.id.et_event_host:
                itemList = new String[]{"Test"};
                title = "Test";
                startNewActivity(title, itemList, REQUEST_CODE_EVENT_TITLE);
                break;
        }


    }


    private void startNewActivity(String title, String[] itemList, int requestCode) {

        intent = new Intent(this, CustomListActivity.class);
        intent.putExtra(EmployConstantUtil.ITEM_LIST, itemList);
        intent.putExtra(EmployConstantUtil.TITLE, title);
        startActivityForResult(intent, requestCode);

    }

    private void startNewActivity(String title, Class<?> cls, int requestCode) {
        intent = new Intent(AddEventActivity.this, cls);
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
                    if (!editEvent) {
                        alertDialogManager.showAlertDialogMessage(this, "", "Are you sure to create a Event", false, this);
                    } else {
                        alertDialogManager.showAlertDialogMessage(this, "", "Are you sure to update a Event", false, this);
                    }

                break;
        }

        return super.onOptionsItemSelected(item);


    }

    private ArrayList<Participant> addParticipants() {
        return dataBaseAdapter.getAllParticipants(eventId);
    }

    private void addEventData() {
        eventDataModel = new EventDataModel();


        if (editEvent) {
            eventDataModel.setEventId(eventId);
        } else {
            eventDataModel.setEventId(DateAndTimeUtil.currentTimeStamp());
        }

        eventDataModel.setTitle(editTextEventTitle.getText().toString().trim());
        eventDataModel.setLocation(editTextEventLocation.getText().toString().trim());
        eventDataModel.setAllDay(aSwitchEventAllDay.isChecked());
        eventDataModel.setContact(checkSelectListItem(editTextEventContact.getText().toString().trim(), titleContactName));
        eventDataModel.setLeadName(checkSelectListItem(editTextEventContact.getText().toString().trim(), titleLeadName));
        eventDataModel.setAccount(textViewEventAccount.getText().toString().trim());
        eventDataModel.setAccountId(accountId);
        eventDataModel.setContactId(contactId);
        eventDataModel.setLeadId(leadId);
        eventDataModel.setHost(textViewEventHost.getText().toString());
        eventDataModel.setNoOfParticipants(checkIntegerIsNull(textViewEventParticipants.getText().toString()));
        eventDataModel.setCreatedBy(createdBy);
        eventDataModel.setModifyBy(sessionManager.getUserKeyId());
        eventDataModel.setParticipants(addParticipants());
        eventDataModel.setFromDate(DateAndTimeUtil.dateToLong(textViewEventFrom.getText().toString().trim(), EmployConstantUtil.DATE_FORMAT));
        eventDataModel.setToDate(DateAndTimeUtil.dateToLong(textViewEventTo.getText().toString().trim(), EmployConstantUtil.DATE_FORMAT));
        eventDataModel.setFromTime(DateAndTimeUtil.timeToLong(textViewEventFromTime.getText().toString().trim(), EmployConstantUtil.TIME_FORMAT));
        eventDataModel.setToTime(DateAndTimeUtil.timeToLong(textViewEventToTime.getText().toString(), EmployConstantUtil.TIME_FORMAT));
        eventDataModel.setCreatedTime(createdTime);
        eventDataModel.setModifyTime(DateAndTimeUtil.currentTimeStamp());
        eventDataModel.setDescription(editTextEventDescription.getText().toString());
        dataBaseAdapter.openDataBase();


        if (editEvent) {
            if (!isSync) {

                if (checkConnection()) {
                    addEventDataToServer();
                } else {
                    dataBaseAdapter.updateEvent(eventDataModel, eventId);
                    alertDialogManager.showAlertDialogMessage(this, "", "Event is Updated ", false, this);
                }


            } else {

                if (checkConnection()) {
                    addEventDataToServer();
                } else {
                    Toast.makeText(this, "Please check your Internet connection ", Toast.LENGTH_SHORT).show();
                }
            }

        } else {

            if (checkConnection()) {
                addEventDataToServer();
            } else {
                eventDataModel.setSync(false);
                dataBaseAdapter.addEvent(eventDataModel);
                //alertDialogManager.showAlertDialogMessage(AddEventActivity.this, "", "Event is created ", false, AddEventActivity.this);

              Intent intent = new Intent();
              setResult(RESULT_OK, intent);

              finish();
            }
        }



    }


    private void updateEvent(){
        if (!editEvent) {
            dataBaseAdapter.addEvent(eventDataModel);
            //alertDialogManager.showAlertDialogMessage(AddEventActivity.this, "", "Event is created ", false, AddEventActivity.this);;
            // alertDialogManager.showAlertDialogMessage(AddTaskActivity.this, "", "Task is created ", false, AddTaskActivity.this);
        } else {
            long result = dataBaseAdapter.updateEvent(eventDataModel, eventId);
            // alertDialogManager.showAlertDialogMessage(AddTaskActivity.this, "", "Task is updated ", false, AddTaskActivity.this);


        }


        Log.e(TAG, "NOT CREATED");
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }



    private String checkSelectListItem(String selectedItem, String label) {

        return selectedItem.equals(EmployConstantUtil.NOTING_SELECTED_VALUE) ? "" : textViewLabelContact.getText().toString().equals(label) ? selectedItem : "";
    }


    private void addEventDataToServer() {
        //DialogUtitlity.showLoading(AddContactActivity.this);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        final Call<String> response = apiService.addEvent(eventDataModel, sessionManager.getUserKeyId());
        response.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //DialogUtitlity.hideLoading();
                int statusCode = response.code();
                switch (statusCode) {
                    case 201:
                        eventDataModel.setSync(true);
                        break;
                    case 400:
                        eventDataModel.setSync(false);


                        break;
                    case 406:
                        Toast.makeText(AddEventActivity.this, "Event data not acceptable", Toast.LENGTH_SHORT).show();
                        break;
                }



                updateEvent();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                eventDataModel.setSync(false);
                updateEvent();


            }
        });

    }

    private boolean checkValidation() {
        if (textViewError != null) {
            textViewError.setVisibility(View.GONE);
        }
        if (editTextEventTitle.getText().toString().trim().isEmpty()) {
            showErrorMsg(textViewErrorEventTitle,getResources().getString(R.string.err_msg_event_title),editTextEventTitle);
            return false;
        }if(!EmployeeValidationUtil.validateName(editTextEventTitle.getText().toString().trim(),3)){
            showErrorMsg(textViewErrorEventTitle,getResources().getString(R.string.err_msg_title_char),editTextEventTitle);
            return false;
        }
        return true;
    }

    private TextView textViewError;
    private boolean showErrorMsg(TextView textView, String errorMessage, EditText editText) {
        editText.requestFocus();
        textView.setText(errorMessage);
        textView.setVisibility(View.VISIBLE);
        textViewError = textView;
        return false;
    }

    // Method to manually check connection status
    private boolean checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        if (!isConnected) {

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

    public void openTimePicker(final View view1) {
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

                        if (DateAndTimeUtil.dateToLong(textViewEventFrom.getText().toString(),"E, MMM dd yyyy")> DateAndTimeUtil.currentTimeStamp() || datetime.getTimeInMillis() >= c.getTimeInMillis() ) {
                            //it's after current
                            int hour = hourOfDay % 12;
                            if (view1.getId() == R.id.et_event_from_time) {
                                textViewEventFromTime.setText(String.format("%02d:%02d %s", hour == 0 ? 12 : hour,
                                        minute, hourOfDay < 12 ? "am" : "pm"));
                                //textViewEventFromTime.setText(time);
                            } else {
                                textViewEventToTime.setText(String.format("%02d:%02d %s", hour == 0 ? 12 : hour,
                                        minute, hourOfDay < 12 ? "am" : "pm"));
                                //textViewEventToTime.setText(time);
                            }
                        } else {
                            //it's before current'
                            Toast.makeText(getApplicationContext(), "Invalid Time", Toast.LENGTH_LONG).show();
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
        if (data != null) {
            selectedItem = data.getStringExtra(EmployConstantUtil.SELECTED_ITEM);
        }
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_EVENT_TITLE:
                    textViewEventHost.setText(title);
                    break;
                case REQUEST_CODE_ACCOUNT:
                    textViewEventAccount.setText(selectedItem);
                    accountId = data.getLongExtra(EmployConstantUtil.KEY_ACCOUNT_ID, 0);
                    break;
                case REQUEST_CODE_CONTACT:
                    editTextEventContact.setText(selectedItem);
                    isLead = data.getBooleanExtra(EmployConstantUtil.KEY_IS_LEAD, false);
                    if (isLead) {
                        leadId = data.getLongExtra(EmployConstantUtil.KEY_CONTACT_ID, 0);
                        textViewLabelContact.setText(titleLeadName);
                        contactId = 0;
                    } else {
                        contactId = data.getLongExtra(EmployConstantUtil.KEY_CONTACT_ID, 0);
                        textViewLabelContact.setText(titleContactName);
                        leadId = 0;
                    }
                    break;
                case REQUEST_CODE_PARTICIPANTS:
                    textViewEventParticipants.setText(String.valueOf(data.getIntExtra(EmployConstantUtil.KEY_PARTICIPANT_LIST_SIZE, 0)));
                    break;
            }
        }


    }

    @Override
    public void alertDialogCallbackOk() {
       addEventData();
    }

    @Override
    public void alertDialogCallbackCancel() {

    }
}
