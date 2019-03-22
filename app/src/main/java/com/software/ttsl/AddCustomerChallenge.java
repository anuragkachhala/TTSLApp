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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.software.ttsl.Interfacce.AlertDialogCallback;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.AlertDialogManager;
import com.software.ttsl.Utils.ConnectivityReceiver;
import com.software.ttsl.Utils.DateAndTimeUtil;
import com.software.ttsl.Utils.EmployConstantUtil;
import com.software.ttsl.Utils.EmployeeValidationUtil;
import com.software.ttsl.Utils.SessionManager;
import com.software.ttsl.Utils.SharedPrefUtil;
import com.software.ttsl.model.CustomerChallengeModel;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCustomerChallenge extends AppCompatActivity implements View.OnClickListener, AlertDialogCallback {

    public static final String TAG = AddCustomerChallenge.class.getName();
    private static final int REQUEST_CODE_CONTACT = 1000;
    private static final int REQUEST_CODE_PRIORITY = 2000;
    private static final int REQUEST_CODE_STATUS = 3000;
    private static final int REQUEST_CODE_ORIGIN = 4000;


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.et_customer)
    EditText editTextCustomer;

    @BindView(R.id.et_customer_contact)
    TextView textViewCustomerContact1;

    @BindView(R.id.et_customer_log_date)
    TextView textViewCustomerLogDate;

    @BindView(R.id.et_customer_priority)
    TextView textViewCustomerPriority;

    @BindView(R.id.et_customer_origin)
    TextView textViewCustomerOrigin;

    @BindView(R.id.et_customer_reason)
    EditText editTextCustomerReason;

    @BindView(R.id.et_customer_due_date)
    TextView textViewCustomerDueDate;

    @BindView(R.id.et_closed_on)
    TextView textViewCustomerClosedOn;

    @BindView(R.id.et_incharge)
    EditText editTextCustomerIncharge;

    @BindView(R.id.et_cc_mail_id)
    EditText editTextCCMailId;

    @BindView(R.id.et_status)
    TextView textViewCustomerStatus;

    @BindView(R.id.et_subject)
    EditText editTextSubject;

    @BindView(R.id.et_note)
    EditText editTextNote;

    @BindView(R.id.et_discription)
    EditText editTextDiscription;

    @BindView(R.id.et_internal_note)
    EditText editTextInternalNote;

    @BindView(R.id.et_customer_feedback)
    EditText editTextCustomerFeedBack;

    @BindView(R.id.iv_customer_log_date)
    ImageView imageViewCustomerLogDate;

    @BindView(R.id.iv_closed_on)
    ImageView imageViewCustomerClosedOn;

    @BindView(R.id.iv_customer_due_date)
    ImageView imageViewCustomerDueDate;

    @BindView(R.id.iv_customer_contact)
    ImageView imageViewCustomerContact;

    @BindView(R.id.tv_customer_contact)
    TextView textViewCustomerContact;

    @BindView(R.id.tv_customer)
    TextView textViewLabelCustomer;






    private AlertDialogManager alertDialogManager;
    private SharedPrefUtil sharedPrefUtil;
    private SessionManager sessionManager;
    private DataBaseAdapter dataBaseAdapter;
    private Intent intent;
    private String[] itemList, priorityList, originList, statusList;
    private String title;
    private CustomerChallengeModel customerChallengeModel;
    private String titleContact, titlePriority, createdBy, titleOrigin, titleStatus, emptyCustomerNameErrorMsg, emptyContactNameErrorMsg, errorTitle, msgValidEmailAddress, titleContactName, titleLeadName;
    private int mYear, mMonth, mDay;
    private long contactId, leadId, customerChallengeId,createdTime;
    private boolean isLead, isSync = false, editCustomerChallenge = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer_challenge);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        SessionManager.setContext(getApplicationContext());
        sessionManager = SessionManager.getInstance();
        alertDialogManager = new AlertDialogManager();
        dataBaseAdapter = new DataBaseAdapter(this);

        createdBy = sessionManager.getUserKeyId();
        SharedPrefUtil.setContext(getApplicationContext());
        sharedPrefUtil = SharedPrefUtil.getInstance();


        Intent intent = getIntent();

        customerChallengeId = intent.getLongExtra(EmployConstantUtil.KEY_CUSTOMER_CHALLENGE_ID, 0);
        if (customerChallengeId != 0) {
            editCustomerChallenge = true;

        }
        onClickListener();

        getStringResources();
        if (editCustomerChallenge) {
            getCustomerChallengeById();
            toolbar.setTitle(getResources().getString(R.string.title_edit_customer_challenge));
        }
    }

    private void getCustomerChallengeById() {
        customerChallengeModel = dataBaseAdapter.getCustomerChallengesById(customerChallengeId);
        setCustomerChallengeData();

    }

    private void setCustomerChallengeData() {
        editTextCustomer.setText(customerChallengeModel.getCustomer());
        if(customerChallengeModel.getContactName().isEmpty()){
            textViewCustomerContact1.setText(customerChallengeModel.getLeadName());
            leadId = customerChallengeModel.getLeadId();
            textViewCustomerContact.setText(titleLeadName);

        }else {
            textViewCustomerContact1.setText(customerChallengeModel.getContactName());
            contactId = customerChallengeModel.getContactId();
            textViewCustomerContact.setText(titleContactName);
        }
        textViewCustomerClosedOn.setText(DateAndTimeUtil.longToDate(customerChallengeModel.getCloseddate()));
        editTextCustomerIncharge.setText(customerChallengeModel.getCustomerInCharge());
        editTextCCMailId.setText(customerChallengeModel.getCcMailId());
        textViewCustomerDueDate.setText(DateAndTimeUtil.longToDate(customerChallengeModel.getCustomerDueDate()));
        editTextCustomerFeedBack.setText(customerChallengeModel.getCustomerFeedback());
        textViewCustomerLogDate.setText(DateAndTimeUtil.longToDate(customerChallengeModel.getCustomerLogDate()));
        textViewCustomerOrigin.setText(customerChallengeModel.getCustomerOrigin());
        textViewCustomerPriority.setText(customerChallengeModel.getCustomerPriority());
        editTextCustomerReason.setText(customerChallengeModel.getCustomerReason());
        textViewCustomerStatus.setText(customerChallengeModel.getStatus());
        editTextDiscription.setText(customerChallengeModel.getDescription());
        editTextInternalNote.setText(customerChallengeModel.getInternalNote());
        editTextNote.setText(customerChallengeModel.getNote());
        editTextSubject.setText(customerChallengeModel.getSubject());
        createdTime = customerChallengeModel.getCreatedTime();
        createdBy = customerChallengeModel.getCreatedBy();
        isSync = customerChallengeModel.isSync();

    }

    private void getStringResources() {
        textViewLabelCustomer.setText(Html.fromHtml(EmployConstantUtil.ASTERISK+ getResources().getString(R.string.label_customer)));
        textViewCustomerContact.setText(Html.fromHtml(EmployConstantUtil.ASTERISK+getResources().getString(R.string.label_customer_contact)));
        titleContact = getResources().getString(R.string.title_contact_list);
        titlePriority = getResources().getString(R.string.title_priority_list);
        titleOrigin = getResources().getString(R.string.title_origins_list);
        titleStatus = getResources().getString(R.string.title_status_list);
        titleContactName = getResources().getString(R.string.title_contact_list_activity);
        titleLeadName = getResources().getString(R.string.title_lead_list_activity);
        emptyContactNameErrorMsg = getResources().getString(R.string.msg_customer_contact_name);
        emptyCustomerNameErrorMsg = getResources().getString(R.string.msg_customer_name);
        errorTitle = getResources().getString(R.string.err_msg_title_dialog);
        msgValidEmailAddress = getResources().getString(R.string.msg_email_validation);
        statusList = getResources().getStringArray(R.array.list_customer_challenge_status);
        originList = getResources().getStringArray(R.array.list_customer_challeng_origin);
        priorityList = getResources().getStringArray(R.array.list_customer_challenge_priority);

    }

    private void onClickListener() {
        textViewCustomerLogDate.setOnClickListener(this);
        textViewCustomerPriority.setOnClickListener(this);
        textViewCustomerOrigin.setOnClickListener(this);
        textViewCustomerDueDate.setOnClickListener(this);
        textViewCustomerClosedOn.setOnClickListener(this);
        textViewCustomerStatus.setOnClickListener(this);
        textViewCustomerContact1.setOnClickListener(this);

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

               /* if (setLeadData()) {
                    addLeadData();

                }*/
                if (checkValidation())
                    addCustomerChallengeData();

                break;
        }

        return super.onOptionsItemSelected(item);


    }

    private String checkSelectListItem(String selectedItem, String label) {

        return selectedItem.equals(EmployConstantUtil.NOTING_SELECTED_VALUE) ? "" : textViewCustomerContact.getText().toString().trim().equals(label) ? selectedItem : "";
    }

    private void addCustomerChallengeData() {
        customerChallengeModel = new CustomerChallengeModel();
        if(!editCustomerChallenge){
            customerChallengeId =currentTimeStamp();
        }
        customerChallengeModel.setCustomerId(customerChallengeId);
        customerChallengeModel.setCustomer(editTextCustomer.getText().toString().trim());
        customerChallengeModel.setContactId(contactId);
        customerChallengeModel.setLeadId(leadId);
        customerChallengeModel.setCustomerLogDate(DateAndTimeUtil.DateToLong(textViewCustomerLogDate.getText().toString().trim()));
        customerChallengeModel.setCustomerDueDate(DateAndTimeUtil.DateToLong(textViewCustomerDueDate.getText().toString()));
        customerChallengeModel.setCloseddate(DateAndTimeUtil.DateToLong(textViewCustomerClosedOn.getText().toString()));
        customerChallengeModel.setContactName(checkSelectListItem(textViewCustomerContact1.getText().toString(), titleContactName));
        customerChallengeModel.setLeadName(checkSelectListItem(textViewCustomerContact1.getText().toString(), titleLeadName));
        customerChallengeModel.setCustomerPriority(checkSelectListItem(textViewCustomerPriority.getText().toString().trim()));
        customerChallengeModel.setCustomerOrigin(checkSelectListItem(textViewCustomerOrigin.getText().toString().trim()));
        customerChallengeModel.setCustomerReason(editTextCustomerReason.getText().toString().trim());
        customerChallengeModel.setCustomerInCharge(editTextCustomerIncharge.getText().toString().trim());
        customerChallengeModel.setCcMailId(editTextCCMailId.getText().toString().trim());
        customerChallengeModel.setSubject(editTextSubject.getText().toString().trim());
        customerChallengeModel.setStatus(checkSelectListItem(textViewCustomerStatus.getText().toString().trim()));
        customerChallengeModel.setNote(editTextNote.getText().toString().trim());
        customerChallengeModel.setDescription(editTextDiscription.getText().toString().trim());
        customerChallengeModel.setInternalNote(editTextInternalNote.getText().toString().trim());
        customerChallengeModel.setCustomerFeedback(editTextCustomerFeedBack.getText().toString().trim());
        customerChallengeModel.setCreatedTime(currentTimeStamp());
        customerChallengeModel.setModifyTime(currentTimeStamp());
        customerChallengeModel.setCreatedBy(createdBy);
        customerChallengeModel.setModifyBy(sessionManager.getUserKeyId());
        customerChallengeModel.setSync(isSync);


        if (editCustomerChallenge) {
            if (!isSync) {

                if (checkConnection()) {
                    addCustomerChallengeDataToServer();
                } else {

                    dataBaseAdapter.updateCustomerChallenge(customerChallengeModel,customerChallengeId);
                    alertDialogManager.showAlertDialogMessage(this, "", "Custoer is Updated ", false, this);
                }


            } else {

                if (checkConnection()) {
                    addCustomerChallengeDataToServer();
                } else {
                    Toast.makeText(this, "Please check your Internet connection ", Toast.LENGTH_SHORT).show();
                }
            }

        } else {

            if (checkConnection()) {
                addCustomerChallengeDataToServer();
            } else {

                customerChallengeModel.setSync(false);
                dataBaseAdapter.addCustomerChallenge(customerChallengeModel);
                alertDialogManager.showAlertDialogMessage(AddCustomerChallenge.this, "", "Task is created ", false, AddCustomerChallenge.this);


            }
        }
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

                int id = view.getId();
                switch (id) {
                    case R.id.et_customer_log_date:
                        textViewCustomerLogDate.setText(date);
                        break;
                    case R.id.et_customer_due_date:
                        textViewCustomerDueDate.setText(date);
                        break;
                    case R.id.et_closed_on:
                        textViewCustomerClosedOn.setText(date);
                        break;
                }
            }
        }, mYear, mMonth, mDay);

        datePickerDialog.show();


    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.et_customer_log_date:
                openCalender(textViewCustomerLogDate);
                break;
            case R.id.et_customer_priority:

                startNewActivity(titlePriority, priorityList, REQUEST_CODE_PRIORITY);
                break;
            case R.id.et_customer_origin:
                startNewActivity(titleOrigin, originList, REQUEST_CODE_ORIGIN);
                break;
            case R.id.et_customer_due_date:
                openCalender(textViewCustomerDueDate);
                break;
            case R.id.et_closed_on:
                openCalender(textViewCustomerClosedOn);
                break;
            case R.id.et_status:
                startNewActivity(titleStatus, statusList, REQUEST_CODE_STATUS);
                break;
            case R.id.et_customer_contact:
                startNewActivity(titleContact, ContactNameListActivity.class, REQUEST_CODE_CONTACT);
                break;

        }
    }

    private boolean checkValidation() {
        if (editTextCustomer.getText().toString().trim().isEmpty()) {
            showErrorDialog(emptyCustomerNameErrorMsg);
            return false;
        }
        if (textViewCustomerContact1.getText().toString().trim().isEmpty()) {
            showErrorDialog(emptyContactNameErrorMsg);
            return false;
        }
        if (!editTextCCMailId.getText().toString().trim().isEmpty()) {
            if (!EmployeeValidationUtil.validateEmail(editTextCCMailId.getText().toString().trim())) {
                showErrorDialog(msgValidEmailAddress);
                return false;
            } else {
                return true;
            }


        }

        return true;
    }

    private void addCustomerChallengeDataToServer() {
        //DialogUtitlity.showLoading(AddContactActivity.this);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        final Call<String> response = apiService.customerChallenge(customerChallengeModel, sessionManager.getUserKeyId());
        response.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //DialogUtitlity.hideLoading();
                int statusCode = response.code();
                switch (statusCode) {
                    case 201:
                        customerChallengeModel.setSync(true);
                        break;
                    case 400:
                        customerChallengeModel.setSync(false);
                        break;
                }


                if (!editCustomerChallenge) {
                    dataBaseAdapter.addCustomerChallenge(customerChallengeModel);
                    alertDialogManager.showAlertDialogMessage(AddCustomerChallenge.this, "", "CustomerChallenge is created ", false, AddCustomerChallenge.this);
                } else {
                    long result = dataBaseAdapter.updateCustomerChallenge(customerChallengeModel, customerChallengeId);
                    alertDialogManager.showAlertDialogMessage(AddCustomerChallenge.this, "", "CustomerChallenge is updated ", false, AddCustomerChallenge.this);


                }
              

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                //DialogUtitlity.hideLoading();
                Log.e(TAG, "NOT CREATED");
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();


            }
        });

    }

    public void showErrorDialog(String errMessage) {
        alertDialogManager.showAlertDialog(this, errorTitle, errMessage, false);

    }


    private void startNewActivity(String title, String[] itemList, int requestCode) {

        intent = new Intent(AddCustomerChallenge.this, CustomListActivity.class);
        intent.putExtra(EmployConstantUtil.ITEM_LIST, itemList);
        intent.putExtra(EmployConstantUtil.TITLE, title);
        startActivityForResult(intent, requestCode);

    }

    private void startNewActivity(String title, Class<?> cls, int requestCode) {
        intent = new Intent(AddCustomerChallenge.this, cls);
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


    private String checkSelectListItem(String selectedItem) {

        return selectedItem.equals(EmployConstantUtil.NOTING_SELECTED_VALUE) ? "" : selectedItem;
    }


    private long currentTimeStamp() {
        return System.currentTimeMillis() - 1000;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String selectedItem = "";
        if (data != null) {
            selectedItem = data.getStringExtra(EmployConstantUtil.SELECTED_ITEM);
        }



        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_CONTACT:
                    textViewCustomerContact1.setText(selectedItem);
                    isLead = data.getBooleanExtra(EmployConstantUtil.KEY_IS_LEAD, false);
                    if (isLead) {
                        leadId = data.getLongExtra(EmployConstantUtil.KEY_CONTACT_ID, 0);
                        textViewCustomerContact.setText(titleLeadName);
                        contactId = 0;
                    } else {
                        contactId = data.getLongExtra(EmployConstantUtil.KEY_CONTACT_ID, 0);
                        textViewCustomerContact.setText(titleContactName);
                        leadId = 0;
                    }
                    break;
                case REQUEST_CODE_ORIGIN:
                    textViewCustomerOrigin.setText(selectedItem);
                    break;
                case REQUEST_CODE_PRIORITY:
                    textViewCustomerPriority.setText(selectedItem);
                    break;
                case REQUEST_CODE_STATUS:
                    textViewCustomerStatus.setText(selectedItem);
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
