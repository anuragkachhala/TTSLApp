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

public class EditCustomerChallengeActivity extends AppCompatActivity implements View.OnClickListener, AlertDialogCallback, Callback<String> {


    public static final String TAG = EditCustomerChallengeActivity.class.getName();
    public long createdTime;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_customer)
    EditText editTextCustomer;
    @BindView(R.id.et_customer_contact)
    EditText editTextCustomerContact;
    @BindView(R.id.et_customer_log_date)
    EditText editTextCustomerLogDate;
    @BindView(R.id.et_customer_priority)
    EditText editTextCustomerPriority;
    @BindView(R.id.et_customer_origin)
    EditText editTextCustomerOrigin;
    @BindView(R.id.et_customer_reason)
    EditText editTextCustomerReason;
    @BindView(R.id.et_customer_due_date)
    EditText editTextCustomerDueDate;
    @BindView(R.id.et_closed_on)
    EditText editTextCustomerClosedOn;
    @BindView(R.id.et_incharge)
    EditText editTextCustomerIncharge;
    @BindView(R.id.et_cc_mail_id)
    EditText editTextCCMailId;
    @BindView(R.id.et_status)
    EditText editTextCustomerStatus;
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

    private AlertDialogManager alertDialogManager;
    private SharedPrefUtil sharedPrefUtil;
    private DataBaseAdapter dataBaseAdapter;
    private Intent intent;
    private String[] itemList, statusList, priorityList, originList;
    private String title;
    private CustomerChallengeModel customerChallengeModel;
    private String titleContact, titlePriority, titleOrigin, titleStatus, emptyCustomerNameErrorMsg, emptyContactNameErrorMsg, errorTitle, msgValidEmailAddress,titleContactName,titleLeadName;
    private int mYear, mMonth, mDay;
    private long contactId,leadId;
    private long customerChallengeId;
    private String createdBy;
    private Boolean isSync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_customer_challenge);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        SessionManager.setContext(getApplicationContext());

        alertDialogManager = new AlertDialogManager();
        dataBaseAdapter = new DataBaseAdapter(this);

        SharedPrefUtil.setContext(getApplicationContext());
        sharedPrefUtil = SharedPrefUtil.getInstance();


        Intent intent = getIntent();
        customerChallengeId = intent.getLongExtra(EmployConstantUtil.KEY_CUSTOMER_CHALLENGE_ID, 0);
        customerChallengeModel = dataBaseAdapter.getCustomerChallengesById(customerChallengeId);

        onClickListener();

        getStringResources();
        setCustomerChallengeData();
    }

    private void setCustomerChallengeData() {
        editTextCustomer.setText(customerChallengeModel.getCustomer());
        if(customerChallengeModel.getContactName().isEmpty()){
            editTextCustomerContact.setText(customerChallengeModel.getLeadName());
            leadId = customerChallengeModel.getLeadId();
            textViewCustomerContact.setText(titleLeadName);

        }else {
            editTextCustomerContact.setText(customerChallengeModel.getContactName());
            contactId = customerChallengeModel.getContactId();
            textViewCustomerContact.setText(titleContactName);
        }
        editTextCustomerClosedOn.setText(DateAndTimeUtil.longToDate(customerChallengeModel.getCloseddate()));
        editTextCustomerIncharge.setText(customerChallengeModel.getCustomerInCharge());
        editTextCCMailId.setText(customerChallengeModel.getCcMailId());
        editTextCustomerDueDate.setText(DateAndTimeUtil.longToDate(customerChallengeModel.getCustomerDueDate()));
        editTextCustomerFeedBack.setText(customerChallengeModel.getCustomerFeedback());
        editTextCustomerLogDate.setText(DateAndTimeUtil.longToDate(customerChallengeModel.getCustomerLogDate()));
        editTextCustomerOrigin.setText(customerChallengeModel.getCustomerOrigin());
        editTextCustomerPriority.setText(customerChallengeModel.getCustomerPriority());
        editTextCustomerReason.setText(customerChallengeModel.getCustomerReason());
        editTextCustomerStatus.setText(customerChallengeModel.getStatus());
        editTextDiscription.setText(customerChallengeModel.getDescription());
        editTextInternalNote.setText(customerChallengeModel.getInternalNote());
        editTextNote.setText(customerChallengeModel.getNote());
        editTextSubject.setText(customerChallengeModel.getSubject());
        createdTime = customerChallengeModel.getCreatedTime();
        createdBy = customerChallengeModel.getCreatedBy();
        isSync = customerChallengeModel.isSync();

    }


    private void getStringResources() {
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
        editTextCustomerLogDate.setOnClickListener(this);
        editTextCustomerPriority.setOnClickListener(this);
        editTextCustomerOrigin.setOnClickListener(this);
        editTextCustomerDueDate.setOnClickListener(this);
        editTextCustomerClosedOn.setOnClickListener(this);
        editTextCustomerStatus.setOnClickListener(this);
        editTextCustomerContact.setOnClickListener(this);

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

    private void addCustomerChallengeData() {
        customerChallengeModel = new CustomerChallengeModel();
        customerChallengeModel.setCustomer(editTextCustomer.getText().toString().trim());
        customerChallengeModel.setCustomerId(customerChallengeId);
        customerChallengeModel.setContactId(contactId);
        customerChallengeModel.setContactName(editTextCustomerContact.getText().toString().trim());
        customerChallengeModel.setCustomerLogDate(DateAndTimeUtil.DateToLong(editTextCustomerLogDate.getText().toString().trim()));
        customerChallengeModel.setCustomerDueDate(DateAndTimeUtil.DateToLong(editTextCustomerDueDate.getText().toString()));
        customerChallengeModel.setCloseddate(DateAndTimeUtil.DateToLong(editTextCustomerClosedOn.getText().toString()));
        customerChallengeModel.setCustomerPriority(checkSelectListItem(editTextCustomerPriority.getText().toString().trim()));
        customerChallengeModel.setCustomerOrigin(checkSelectListItem(editTextCustomerOrigin.getText().toString().trim()));
        customerChallengeModel.setCustomerReason(editTextCustomerReason.getText().toString().trim());
        customerChallengeModel.setCustomerInCharge(editTextCustomerIncharge.getText().toString().trim());
        customerChallengeModel.setCcMailId(editTextCCMailId.getText().toString().trim());
        customerChallengeModel.setSubject(editTextSubject.getText().toString().trim());
        customerChallengeModel.setStatus(checkSelectListItem(editTextCustomerStatus.getText().toString().trim()));
        customerChallengeModel.setNote(editTextNote.getText().toString().trim());
        customerChallengeModel.setDescription(editTextDiscription.getText().toString().trim());
        customerChallengeModel.setInternalNote(editTextInternalNote.getText().toString().trim());
        customerChallengeModel.setCustomerFeedback(editTextCustomerFeedBack.getText().toString().trim());
        customerChallengeModel.setCreatedTime(createdTime);
        customerChallengeModel.setModifyTime(currentTimeStamp());
        customerChallengeModel.setCreatedBy(createdBy);
        customerChallengeModel.setModifyBy("TEST");
        customerChallengeModel.setSync(isSync);


        /*int result= dataBaseAdapter.updateCustomerChallenge(customerChallengeModel,customerChallengeId);
        Log.v(TAG,"send customer challenge id to update  " +customerChallengeId);

        if(result==1){
            Toast.makeText(this,"Customer Challenge Updated ",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,CustomerChallengeListActivity.class));
            finish();
        }*/


        if (!isSync) {

            if (checkConnection()) {
                updateCustomerChallengeDataToServer();
            } else {
                dataBaseAdapter.updateCustomerChallenge(customerChallengeModel, customerChallengeId);
                alertDialogManager.showAlertDialogMessage(this, "", "Customer Account is Updated ", false, this);
            }


        } else {

            if (checkConnection()) {
                updateCustomerChallengeDataToServer();
            } else {
                Toast.makeText(this, "Please check your Internet connection ", Toast.LENGTH_SHORT).show();
            }
        }


    }

    private void updateCustomerChallengeDataToServer() {
        //     DialogUtitlity.showLoading(EditContactActivity.this);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> listCall = apiInterface.customerChallenge(customerChallengeModel,SessionManager.getInstance().getUserKeyId());
        Log.e("addCustomer", "inside updateCustomerChallengeDataToServer");
        listCall.enqueue(this);
    }


    private String checkSelectListItem(String selectedItem) {

        return selectedItem.equals(EmployConstantUtil.NOTING_SELECTED_VALUE) ? "" : selectedItem;
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
                        editTextCustomerLogDate.setText(date);
                        break;
                    case R.id.et_customer_due_date:
                        editTextCustomerDueDate.setText(date);
                        break;
                    case R.id.et_closed_on:
                        editTextCustomerClosedOn.setText(date);
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
                openCalender(editTextCustomerLogDate);
                break;
            case R.id.et_customer_priority:

                startNewActivity(titlePriority, priorityList);
                break;
            case R.id.et_customer_origin:
                startNewActivity(titleOrigin, originList);
                break;
            case R.id.et_customer_due_date:
                openCalender(editTextCustomerDueDate);
                break;
            case R.id.et_closed_on:
                openCalender(editTextCustomerClosedOn);
                break;
            case R.id.et_status:
                startNewActivity(titleStatus, statusList);
                break;
            case R.id.et_customer_contact:
                startNewActivity(titleContact, ContactNameListActivity.class);
                break;

        }
    }

    private boolean checkValidation() {
        if (editTextCustomer.getText().toString().trim().isEmpty()) {
            alertDialogManager.showAlertDialog(this, errorTitle, emptyCustomerNameErrorMsg, false);
            return false;
        }
        if (editTextCustomerContact.getText().toString().trim().isEmpty()) {
            alertDialogManager.showAlertDialog(this, errorTitle, emptyContactNameErrorMsg, false);
            return false;
        }
        if (!editTextCCMailId.getText().toString().trim().isEmpty()) {
            if (!EmployeeValidationUtil.validateEmail(editTextCCMailId.getText().toString().trim())) {
                alertDialogManager.showAlertDialog(this, errorTitle, msgValidEmailAddress, false);
                return false;
            } else {
                return true;
            }


        }

        return true;
    }


    private void startNewActivity(String title, String[] itemList) {

        intent = new Intent(EditCustomerChallengeActivity.this, CustomListActivity.class);
        intent.putExtra(EmployConstantUtil.ITEM_LIST, itemList);
        intent.putExtra(EmployConstantUtil.TITLE, title);
        startActivityForResult(intent, 1000);

    }

    private void startNewActivity(String title, Class<?> cls) {
        intent = new Intent(EditCustomerChallengeActivity.this, cls);
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


    private long currentTimeStamp() {
        return System.currentTimeMillis() - 1000;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == RESULT_OK) {
                String selectedItem = data.getStringExtra(EmployConstantUtil.SELECTED_ITEM);
                String title = data.getStringExtra(EmployConstantUtil.TITLE);
                if (title.equals(titleContact)) {
                    contactId = data.getLongExtra(EmployConstantUtil.KEY_CONTACT_ID, 0);
                    editTextCustomerContact.setText(selectedItem);

                } else if (title.equals(titlePriority)) {
                    editTextCustomerPriority.setText(selectedItem);
                } else if (title.equals(titleStatus)) {
                    editTextCustomerStatus.setText(selectedItem);

                } else if (title.equals(titleOrigin)) {
                    editTextCustomerOrigin.setText(selectedItem);
                }
            }
        }
    }

    @Override
    public void alertDialogCallbackOk() {
        startActivity(new Intent(EditCustomerChallengeActivity.this, CustomerChallengeListActivity.class));
        finish();
    }

    @Override
    public void alertDialogCallbackCancel() {

    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        //     DialogUtitlity.hideLoading();
        Log.e("addCustomer", "inside onResponse");
        int statusCode = response.code();
        Log.e("addCustomer", String.valueOf(statusCode));
        if (statusCode == 200) {
            if (response.body() instanceof String) {

                customerChallengeModel.setSync(isSync);

            }


        } else if (statusCode == 201) {
            customerChallengeModel.setSync(true);


        } else if (statusCode == 500) {


            customerChallengeModel.setSync(isSync);

        }

        long result = dataBaseAdapter.updateCustomerChallenge(customerChallengeModel, customerChallengeId);
        alertDialogManager.showAlertDialogMessage(this, "", "Customer is updated ", false, this);

    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

    }
}
