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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
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
import com.software.ttsl.model.AddContactData;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditContactActivity extends AppCompatActivity implements View.OnClickListener, AlertDialogCallback, Callback<String> {

    private static final String TAG = EditContactActivity.class.getName();


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.layout_contact_image)
    RelativeLayout relativeLayoutContactImage;

    @BindView(R.id.tv_contact_owner)
    TextView textViewContactOwner;

    @BindView(R.id.tv_contact_lead_source)
    TextView textViewLeadSource;

    @BindView(R.id.et_contact_first_name)
    EditText editTextContactFistName;

    @BindView(R.id.et_contact_last_name)
    EditText editTextContactLastName;

    @BindView(R.id.tv_contact_account_name)
    TextView textViewContactAccountName;

    @BindView(R.id.et_contact_email)
    EditText editTextContactEmail;

    @BindView(R.id.et_contact_title)
    EditText editTextContactTitle;

    @BindView(R.id.et_contact_department)
    EditText editTextContactDeprtment;

    @BindView(R.id.et_contact_phone)
    EditText editTextContactPhone;

    @BindView(R.id.et_contact_home_phone)
    EditText getEditTextContactHomePhone;

    @BindView(R.id.et_contact_other_phone)
    EditText editTextContactOtherPhone;

    @BindView(R.id.et_contact_fax)
    EditText editTextContactFax;

    @BindView(R.id.et_contact_mobile)
    EditText editTextContactMobile;

    @BindView(R.id.tv_contact_date_of_birth)
    TextView textViewContactDOB;

    @BindView(R.id.et_contact_assistant)
    EditText editTextContactAssistant;

    @BindView(R.id.et_contact_assistant_phone)
    EditText editTextContactAssistantPhone;

    @BindView(R.id.et_contact_report_to)
    EditText editTextContactReportTo;

    @BindView(R.id.et_contact_skypeid)
    EditText editTextContactSkypeId;

    @BindView(R.id.tv_contact_solution)
    TextView textViewContactSolution;

    @BindView(R.id.et_contact_secondary_mail)
    EditText editTextSecondaryMail;

    @BindView(R.id.et_contact_twitter)
    EditText editTextContactTwitter;

    @BindView(R.id.et_contact_description)
    EditText editTextDiscription;

    @BindView(R.id.et_account_mail_street)
    EditText editTextMailStreet;

    @BindView(R.id.et_account_mail_address_city)
    EditText editTextMailCity;

    @BindView(R.id.et_account_mail_address_state)
    EditText editTextMailState;

    @BindView(R.id.et_account_mail_address_code)
    EditText editTextMailCode;

    @BindView(R.id.et_account_mail_address_country)
    EditText editTextMailCountry;

    @BindView(R.id.et_account_shipping_street)
    EditText editTextShippingStreet;

    @BindView(R.id.et_account_shipping_address_city)
    EditText editTextShippingCity;

    @BindView(R.id.et_account_shipping_address_state)
    EditText editTextShippingState;

    @BindView(R.id.et_account_shipping_address_code)
    EditText editTextShippingCode;

    @BindView(R.id.et_account_shipping_address_country)
    EditText editTextShippingCountry;


    @BindView(R.id.tv_smart_view)
    TextView textViewSmartView;


    @BindView(R.id.linear_layout_contact_owner)
    LinearLayout linearLayoutContactOwner;

    @BindView(R.id.linear_layout_contact_lead_source)
    LinearLayout linearLayoutContactLeadSource;

    @BindView(R.id.linear_layout_contact_title)
    LinearLayout linearLayoutContactTitle;

    @BindView(R.id.linear_layout_contact_department)
    LinearLayout linearLayoutContactDepartment;

    @BindView(R.id.linear_layout_contact_home_phone)
    LinearLayout linearLayoutContactHomePhone;

    @BindView(R.id.linear_layout_contact_other_phone)
    LinearLayout linearLayoutContactotherPhone;

    @BindView(R.id.linear_layout_contact_fax)
    LinearLayout linearLayoutContactFax;

    @BindView(R.id.switch_contact_email_opt)
    Switch aSwitchContactEmailOpt;

    @BindView(R.id.linear_layout_contact_mobile)
    LinearLayout linearLayoutContactMobile;

    @BindView(R.id.linear_layout_date_of_birth)
    LinearLayout linearLayoutContactDOB;

    @BindView(R.id.linear_layout_assistant)
    LinearLayout linearLayoutContactAssistant;

    @BindView(R.id.linear_layout_contact_assistant_phone)
    LinearLayout linearLayoutContactAssistantPhone;

    @BindView(R.id.linear_layout_contact_report_to)
    LinearLayout linearLayoutContactReportTo;

    @BindView(R.id.linear_layout_contact_skypeid)
    LinearLayout linearLayoutContactSkypeId;

    @BindView(R.id.linear_layout_contact_solution)
    LinearLayout linearLayoutContactSolution;

    @BindView(R.id.linear_layout_contact_secondary_mail)
    LinearLayout linearLayoutContactSecondaryMail;

    @BindView(R.id.linear_layout_contact_twitter)
    LinearLayout linearLayoutContactTwitter;

    @BindView(R.id.linear_layout_add_contact_description)
    LinearLayout linearLayoutContactDescription;

    @BindView(R.id.linear_layout_address_information)
    LinearLayout linearLayoutContactInformation;

    @BindView(R.id.linear_layout_contact_address)
    LinearLayout linearLayoutContactAddress;

    @BindView(R.id.linear_layout_account_mail_city)
    LinearLayout linearLayoutMailCity;

    @BindView(R.id.linear_layout_account_mail_state)
    LinearLayout linearLayoutMailState;

    @BindView(R.id.linear_layout_account_mail_code)
    LinearLayout linearLayoutMailCode;

    @BindView(R.id.linear_layout_account_mail_country)
    LinearLayout linearLayoutMailCountry;

    @BindView(R.id.linear_layout_account_shipping_address)
    LinearLayout linearLayoutShippingAddress;

    @BindView(R.id.linear_layout_account_shipping_city)
    LinearLayout linearLayoutShippingCity;

    @BindView(R.id.linear_layout_account_shipping_state)
    LinearLayout linearLayoutShippingState;

    @BindView(R.id.linear_layout_account_shipping_code)
    LinearLayout linearLayoutShippingCode;

    @BindView(R.id.linear_layout_account_shipping_country)
    LinearLayout linearLayoutShippingCountry;

    @BindView(R.id.tv_header_discription)
    TextView textViewHeaderDiscription;

    @BindView(R.id.tv_contact_last_name)
    TextView textViewContactLastName;


    private AddContactData addContactData;
    private AlertDialogManager alertDialogManager;
    private DataBaseAdapter dataBaseAdapter;
    private SessionManager sessionManager;
    private long contactID, accountID;

    private String title, titleContactOwner, titleLeadSource, titleAccounts, titleSolution, errorTitle, showAllFields, showSmartView, msgEmptyLastName, msgValidEmailAddress, errMsgInvalidPhoneNo, errMsgInvalidTwitterID, errMSgInvalidSktypeID, errMsgInvalidSecondaryMail, errMsgInvalidFax;
    private String[] itemList, leadSourceList, solutionList;
    private Intent intent;
    private int mYear, mMonth, mDay;
    private long createdTime;
    private String createdBy;
    private Boolean isSync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        ButterKnife.bind(this);

        alertDialogManager = new AlertDialogManager();
        SessionManager.setContext(getApplicationContext());
        sessionManager = SessionManager.getInstance();

        Intent intent = getIntent();
        contactID = intent.getLongExtra(EmployConstantUtil.KEY_CONTACT_ID, 0);
        Log.v(TAG, "edit account id " + contactID);


        dataBaseAdapter = new DataBaseAdapter(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        getContactById();
        getStringResources();
        setClickListener();


    }

    private void getContactById() {

        addContactData = dataBaseAdapter.getContactById(contactID);
        setContactDetails(addContactData);
    }


    private void getStringResources() {
        textViewContactLastName.setText(Html.fromHtml(getResources().getString(R.string.label_lead_last_name)));
        leadSourceList = getResources().getStringArray(R.array.lead_source);
        solutionList = getResources().getStringArray(R.array.lead_solution);
        titleLeadSource = getResources().getString(R.string.title_lead_source);
        titleSolution = getResources().getString(R.string.title_lead_solution);
        titleContactOwner = getResources().getString(R.string.label_contact_owner);
        titleAccounts = getResources().getString(R.string.title_parent_account);
        errorTitle = getResources().getString(R.string.err_msg_title_dialog);
        showAllFields = getResources().getString(R.string.footer_show_all_fields);
        showSmartView = getResources().getString(R.string.footer_smart_view);
        msgEmptyLastName = getResources().getString(R.string.err_msg_last_name_empty);
        msgValidEmailAddress = getResources().getString(R.string.msg_email_validation);
        errMsgInvalidPhoneNo = getResources().getString(R.string.err_msg_phone_no_not_valid);
        errMsgInvalidTwitterID = getResources().getString(R.string.err_msg_twitter_not_valid);
        errMSgInvalidSktypeID = getResources().getString(R.string.err_msg_skypeid_not_valid);
        errMsgInvalidSecondaryMail = getResources().getString(R.string.msg_secondary_email_validation);
        errMsgInvalidFax = getResources().getString(R.string.err_msg_fax_not_valid);

    }


    private void setClickListener() {
        textViewContactAccountName.setOnClickListener(this);
        textViewContactDOB.setOnClickListener(this);
        textViewContactOwner.setOnClickListener(this);
        textViewContactSolution.setOnClickListener(this);
        textViewLeadSource.setOnClickListener(this);
        textViewSmartView.setOnClickListener(this);
        relativeLayoutContactImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id) {
            case R.id.tv_smart_view:
                showSmartView();
                break;
            case R.id.tv_contact_owner:
                itemList = new String[]{"Test"};
                startNewActivity(titleContactOwner, itemList);
                break;

            case R.id.tv_contact_lead_source:
                startNewActivity(titleLeadSource, leadSourceList);
                break;
            case R.id.tv_contact_solution:
                startNewActivity(titleSolution, solutionList);
                break;
            case R.id.tv_contact_date_of_birth:
                openCalender();
                break;
            case R.id.tv_contact_account_name:
                startNewActivity(titleAccounts);
                break;

        }

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

                textViewContactDOB.setText(date);

            }
        }, mYear, mMonth, mDay);


        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();


    }


    private void showSmartView() {
        if (textViewSmartView.getText().toString().trim().equals(showSmartView)) {
            textViewSmartView.setText(showAllFields);
            linearLayoutContactOwner.setVisibility(View.GONE);
            linearLayoutContactLeadSource.setVisibility(View.GONE);
            linearLayoutContactTitle.setVisibility(View.GONE);
            linearLayoutContactDepartment.setVisibility(View.GONE);
            linearLayoutContactHomePhone.setVisibility(View.GONE);
            linearLayoutContactotherPhone.setVisibility(View.GONE);
            linearLayoutContactFax.setVisibility(View.GONE);
            linearLayoutContactMobile.setVisibility(View.GONE);
            linearLayoutContactDOB.setVisibility(View.GONE);
            linearLayoutContactAssistant.setVisibility(View.GONE);
            linearLayoutContactAssistantPhone.setVisibility(View.GONE);
            linearLayoutContactReportTo.setVisibility(View.GONE);
            linearLayoutContactSolution.setVisibility(View.GONE);
            linearLayoutContactDescription.setVisibility(View.GONE);
            linearLayoutContactInformation.setVisibility(View.GONE);
            linearLayoutContactSecondaryMail.setVisibility(View.GONE);
            linearLayoutContactSkypeId.setVisibility(View.GONE);
            linearLayoutContactTwitter.setVisibility(View.GONE);
            textViewHeaderDiscription.setVisibility(View.GONE);


        } else {

            textViewSmartView.setText(showSmartView);
            linearLayoutContactOwner.setVisibility(View.VISIBLE);
            linearLayoutContactLeadSource.setVisibility(View.VISIBLE);
            linearLayoutContactTitle.setVisibility(View.VISIBLE);
            linearLayoutContactDepartment.setVisibility(View.VISIBLE);
            linearLayoutContactHomePhone.setVisibility(View.VISIBLE);
            linearLayoutContactotherPhone.setVisibility(View.VISIBLE);
            linearLayoutContactFax.setVisibility(View.VISIBLE);
            linearLayoutContactMobile.setVisibility(View.VISIBLE);
            linearLayoutContactDOB.setVisibility(View.VISIBLE);
            linearLayoutContactAssistant.setVisibility(View.VISIBLE);
            linearLayoutContactAssistantPhone.setVisibility(View.VISIBLE);
            linearLayoutContactReportTo.setVisibility(View.VISIBLE);
            linearLayoutContactSolution.setVisibility(View.VISIBLE);
            linearLayoutContactDescription.setVisibility(View.VISIBLE);
            linearLayoutContactInformation.setVisibility(View.VISIBLE);
            linearLayoutContactSecondaryMail.setVisibility(View.VISIBLE);
            linearLayoutContactSkypeId.setVisibility(View.VISIBLE);
            linearLayoutContactTwitter.setVisibility(View.VISIBLE);
            textViewHeaderDiscription.setVisibility(View.VISIBLE);

        }
    }


    private void startNewActivity(String title, String[] itemList) {

        intent = new Intent(EditContactActivity.this, CustomListActivity.class);
        intent.putExtra(EmployConstantUtil.ITEM_LIST, itemList);
        intent.putExtra(EmployConstantUtil.TITLE, title);
        startActivityForResult(intent, 1000);


    }


    private void startNewActivity(String title) {
        intent = new Intent(EditContactActivity.this, AccountNameListActivity.class);
        startActivityForResult(intent, 1000);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == RESULT_OK) {
                String selectedItem = data.getStringExtra(EmployConstantUtil.SELECTED_ITEM);
                String title = data.getStringExtra(EmployConstantUtil.TITLE);
                if (title.equals(titleLeadSource)) {
                    textViewLeadSource.setText(selectedItem);
                } else if (title.equals(titleSolution)) {
                    textViewContactSolution.setText(selectedItem);
                } else if (title.equals(titleAccounts)) {
                    textViewContactAccountName.setText(selectedItem);
                    accountID = data.getLongExtra(EmployConstantUtil.KEY_ACCOUNT_ID, 0);
                }
                if (title.equals(titleContactOwner)) {
                    textViewContactOwner.setText(selectedItem);
                }

            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.add_contact:
                if (checkValidation()) {
                    updateContactData();
                }
                break;
        }

        return super.onOptionsItemSelected(item);


    }


    private boolean checkValidation() {
        String lastName = editTextContactLastName.getText().toString().trim();
        if (lastName.isEmpty()) {
            showErrorDialog(msgEmptyLastName);
            return false;
        }
        String email = editTextContactEmail.getText().toString().trim();
        if (!email.isEmpty() && !EmployeeValidationUtil.validateEmail(email)) {
            showErrorDialog(msgValidEmailAddress);
            return false;
        }
        String phone = editTextContactPhone.getText().toString().trim();
        if (!phone.isEmpty() && !EmployeeValidationUtil.validateMobile(phone)) {
            showErrorDialog(errMsgInvalidPhoneNo);
            return false;
        }

        if (textViewSmartView.getText().toString().equals(showSmartView)) {
            String homePhone = getEditTextContactHomePhone.getText().toString().trim();
            if (!homePhone.isEmpty() && !EmployeeValidationUtil.validateMobile(homePhone)) {
                showErrorDialog(errMsgInvalidPhoneNo);
                return false;

            }
            String otherPhone = editTextContactOtherPhone.getText().toString().trim();
            if (!otherPhone.isEmpty() && !EmployeeValidationUtil.validateMobile(otherPhone)) {
                showErrorDialog(errMsgInvalidPhoneNo);
                return false;
            }
            String fax = editTextContactFax.getText().toString().trim();
            if (!fax.isEmpty() && !EmployeeValidationUtil.validateFax(fax)) {
                showErrorDialog(errMsgInvalidFax);
                return false;
            }
            String mobile = editTextContactMobile.getText().toString().trim();
            if (!mobile.isEmpty() && !EmployeeValidationUtil.validateMobile(mobile)) {
                showErrorDialog(errMsgInvalidPhoneNo);
                return false;
            }
            String assPhone = editTextContactAssistantPhone.getText().toString().trim();
            if (!assPhone.isEmpty() && !EmployeeValidationUtil.validateMobile(assPhone)) {
                showErrorDialog(errMsgInvalidPhoneNo);
                return false;
            }
            String skypeId = editTextContactSkypeId.getText().toString().trim();
            if (!skypeId.isEmpty() && !EmployeeValidationUtil.validateUserName(skypeId)) {
                showErrorDialog(errMSgInvalidSktypeID);
                return false;
            }
            String twitterID = editTextContactTwitter.getText().toString().trim();
            if (!twitterID.isEmpty() && !EmployeeValidationUtil.validateUserName(twitterID)) {
                showErrorDialog(errMsgInvalidTwitterID);
                return false;
            }
        }
        return true;
    }

    public void showErrorDialog(String errMessage) {
        alertDialogManager.showAlertDialog(this, errorTitle, errMessage, false);

    }


    private void updateContactData() {
        addContactData = new AddContactData();
        addContactData.setContactOwner(checkSelectListItem(textViewContactOwner.getText().toString().trim()));
        addContactData.setLeadSource(checkSelectListItem(textViewLeadSource.getText().toString().trim()));
        addContactData.setFirstName(editTextContactFistName.getText().toString().trim());
        addContactData.setLastName(editTextContactLastName.getText().toString().trim());
        addContactData.setAccountName(checkSelectListItem(textViewContactAccountName.getText().toString().trim()));
        addContactData.setAccountId(accountID);
        addContactData.setEmail(editTextContactEmail.getText().toString().trim());
        addContactData.setTitle(editTextContactTitle.getText().toString().trim());
        addContactData.setDepartment(editTextContactDeprtment.getText().toString().trim());
        addContactData.setPhone(editTextContactPhone.getText().toString().trim());
        addContactData.setHomePhone(getEditTextContactHomePhone.getText().toString().trim());
        addContactData.setOtherPhone(editTextContactOtherPhone.getText().toString().trim());
        addContactData.setFax(editTextContactFax.getText().toString().trim());
        addContactData.setMobile(editTextContactMobile.getText().toString().trim());
        addContactData.setDateOfBirth(textViewContactDOB.getText().toString().trim());
        addContactData.setAssistant(editTextContactAssistant.getText().toString().trim());
        addContactData.setAsstPhone(editTextContactAssistantPhone.getText().toString().trim());
        addContactData.setReportsTo(editTextContactReportTo.getText().toString().trim());
        addContactData.setEmailOptOut(aSwitchContactEmailOpt.isChecked());
        addContactData.setSkypeId(editTextContactSkypeId.getText().toString().trim());
        addContactData.setSalutation(checkSelectListItem(textViewContactSolution.getText().toString().trim()));
        addContactData.setTwitter(editTextContactTwitter.getText().toString().trim());
        addContactData.setSecondaryEmail(editTextSecondaryMail.getText().toString().trim());
        addContactData.setDescription(editTextDiscription.getText().toString().trim());
        addContactData.setMailingAddressStreet(editTextMailStreet.getText().toString().trim());
        addContactData.setMailingAddressCity(editTextMailCity.getText().toString().trim());
        addContactData.setMailingAddressCountry(editTextMailCountry.getText().toString().trim());
        addContactData.setMailingAddressState(editTextMailState.getText().toString().trim());
        addContactData.setMailingAddressZip(editTextMailCode.getText().toString().trim());
        addContactData.setOtherAddressStreet(editTextShippingStreet.getText().toString().trim());
        addContactData.setOtherAddressCity(editTextShippingCity.getText().toString().trim());
        addContactData.setOtherAddressCountry(editTextShippingCountry.getText().toString().trim());
        addContactData.setOtherAddressState(editTextShippingState.getText().toString().trim());
        addContactData.setOtherAddressZip(editTextShippingCode.getText().toString().trim());
        addContactData.setCreatedDate(createdTime);
        addContactData.setModifyDate(DateAndTimeUtil.currentTimeStamp());
        addContactData.setCreatedBy(createdBy);
        addContactData.setModifyBy(sessionManager.getUserKeyId());
        dataBaseAdapter = new DataBaseAdapter(this);
        dataBaseAdapter.openDataBase();
        addContactData.setSync(isSync);


        /*if(result ==1){
            Toast.makeText(this, "Contact Update Successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(EditContactActivity.this, ContactListActivity.class));
            finish();

        }else {
            Toast.makeText(this,"update fail ",Toast.LENGTH_SHORT).show();
        }*/

        if (!isSync) {

            if (checkConnection()) {
                updateContactDataToServer();
            } else {
                dataBaseAdapter.updateContact(addContactData, contactID);
                alertDialogManager.showAlertDialogMessage(this, "", "Contact is Updated ", false, this);
            }


        } else {

            if (checkConnection()) {
                updateContactDataToServer();
            } else {
                Toast.makeText(this, "Please check your Internet connection ", Toast.LENGTH_SHORT).show();
            }
        }

        /*if (checkConnection()) {
            updateContactDataToServer();
        } else {
            addContactData.setSync(false);
            dataBaseAdapter.updateContact(addContactData, contactID);
            alertDialogManager.showAlertDialogMessage(this, "", "Contact is Updated ", false, this);
        }*/
    }

    private void updateContactDataToServer() {

        //     DialogUtitlity.showLoading(EditContactActivity.this);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> listCall = apiInterface.addContact(addContactData);
        Log.e("addContact", "inside updateContactDataToServer");
        listCall.enqueue(this);

    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        //     DialogUtitlity.hideLoading();
        Log.e("addContact", "inside onResponse");
        int statusCode = response.code();
        Log.e("addContact", String.valueOf(statusCode));
        if (statusCode == 200) {
            if (response.body() instanceof String) {

                addContactData.setSync(isSync);

            }


        } else if (statusCode == 201) {
            addContactData.setSync(true);


        } else if (statusCode == 500) {


            addContactData.setSync(isSync);

        }

        int result = dataBaseAdapter.updateContact(addContactData, contactID);
        alertDialogManager.showAlertDialogMessage(this, "", "Contact is updated ", false, this);


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


    private void setContactDetails(AddContactData addContactData) {
        textViewContactOwner.setText(addContactData.getContactOwner());
        textViewLeadSource.setText(addContactData.getLeadSource());
        editTextContactFistName.setText(addContactData.getFirstName());
        editTextContactLastName.setText(addContactData.getLastName());
        textViewContactAccountName.setText(addContactData.getAccountName());
        editTextContactEmail.setText(addContactData.getEmail());
        editTextContactTitle.setText(addContactData.getTitle());
        editTextContactDeprtment.setText(addContactData.getDepartment());
        editTextContactPhone.setText(addContactData.getPhone());
        getEditTextContactHomePhone.setText(addContactData.getHomePhone());
        editTextContactOtherPhone.setText(addContactData.getOtherPhone());
        editTextContactFax.setText(addContactData.getFax());
        editTextContactMobile.setText(addContactData.getFax());
        textViewContactDOB.setText(addContactData.getDateOfBirth());
        editTextContactAssistant.setText(addContactData.getAssistant());
        editTextContactAssistantPhone.setText(addContactData.getAsstPhone());
        editTextContactReportTo.setText(addContactData.getReportsTo());
        aSwitchContactEmailOpt.setChecked(addContactData.getEmailOptOut());
        editTextContactSkypeId.setText(addContactData.getSkypeId());
        textViewContactSolution.setText(addContactData.getSalutation());
        editTextSecondaryMail.setText(addContactData.getSecondaryEmail());
        editTextContactTwitter.setText(addContactData.getTwitter());
        editTextShippingStreet.setText(addContactData.getOtherAddressStreet());
        editTextShippingCity.setText(addContactData.getOtherAddressCity());
        editTextShippingState.setText(addContactData.getOtherAddressState());
        editTextShippingCode.setText(addContactData.getOtherAddressZip());
        editTextShippingCountry.setText(addContactData.getOtherAddressCountry());
        editTextMailCode.setText(addContactData.getMailingAddressZip());
        editTextMailCity.setText(addContactData.getMailingAddressCity());
        editTextMailState.setText(addContactData.getMailingAddressState());
        editTextMailStreet.setText(addContactData.getMailingAddressStreet());
        editTextMailCountry.setText(addContactData.getMailingAddressCountry());
        editTextDiscription.setText(addContactData.getDescription());
        createdBy = addContactData.getCreatedBy();
        createdTime = addContactData.getCreatedDate();
        isSync = addContactData.isSync();
        accountID = addContactData.getAccountId();


    }


    private String checkSelectListItem(String selectedItem) {

        return selectedItem.equals(EmployConstantUtil.NOTING_SELECTED_VALUE) ? "" : selectedItem;
    }

    @Override
    public void alertDialogCallbackOk() {
        startActivity(new Intent(EditContactActivity.this, ContactListActivity.class));
        finish();
    }

    @Override
    public void alertDialogCallbackCancel() {

    }


}
