package com.software.ttsl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.InputFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.software.ttsl.Interfacce.AlertDialogCallback;
import com.software.ttsl.Interfacce.AlertDialogCallback1;
import com.software.ttsl.Request.AccountDataModel;
import com.software.ttsl.Response.AccountType;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.AlertDialogManager;
import com.software.ttsl.Utils.ConnectivityReceiver;
import com.software.ttsl.Utils.DateAndTimeUtil;
import com.software.ttsl.Utils.DialogUtitlity;
import com.software.ttsl.Utils.EmployConstantUtil;
import com.software.ttsl.Utils.EmployeeValidationUtil;
import com.software.ttsl.Utils.MoneyTextWatcher;
import com.software.ttsl.Utils.MoneyValueFilter;
import com.software.ttsl.Utils.SessionManager;
import com.software.ttsl.Utils.SharedPrefUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAccountActivity extends AppCompatActivity implements View.OnClickListener, Callback<List<AccountType>>, AlertDialogCallback, AlertDialogCallback1 {

    public static final String PARENT_ACCOUNT = "ParentAccount";
    private static final String TAG = AddAccountActivity.class.getName();

    private static final int REQUEST_CODE_ACCOUNT_OWNER = 1000;
    private static final int REQUEST_CODE_ACCOUNT_TYPE = 2000;
    private static final int REQUEST_CODE_ACCOUNT_RATING = 3000;
    private static final int REQUEST_CODE_ACCOUNT_INDUSTRY = 4000;
    private static final int REQUEST_CODE_ACCOUNT_OWNERSHIP = 5000;
    private static final int REQUEST_CODE_ACCOUNT_PARENT = 6000;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.et_account_owner)
    TextView textViewAccountOwner;

    @BindView(R.id.et_account_rating)
    TextView textViewAccountRating;

    @BindView(R.id.et_account_name)
    EditText editTextAccountName;


    @BindView(R.id.et_account_phone)
    EditText editTextAccountPhone;


    @BindView(R.id.et_account_site)
    EditText editTextAccountSite;

    @BindView(R.id.et_account_fax)
    EditText editTextAccountFax;

    @BindView(R.id.et_account_website)
    EditText editTextAccountWebSite;

    @BindView(R.id.et_account_number)
    EditText editTextAccountNumber;

    @BindView(R.id.et_account_ticker_symbol)
    EditText editTextAccountTickerSymbol;

    @BindView(R.id.et_account_employee)
    EditText editTextAccountEmployee;

    @BindView(R.id.et_account_revenue)
    EditText editTextAccountRevenue;

    @BindView(R.id.et_account_parent)
    TextView textViewAccountParent;

    @BindView(R.id.et_account_type)
    TextView textViewAccountType;

    @BindView(R.id.et_account_ownership)
    TextView textViewAccountOwnerShip;

    @BindView(R.id.et_account_industry)
    TextView textViewAccountIndustry;

    @BindView(R.id.et_account_sic_code)
    EditText editTextAccountSicCode;

    @BindView(R.id.et_account_bill_street)
    EditText editTextBillAddressStreet;

    @BindView(R.id.et_account_bill_address_code)
    EditText editTextBillAddressCode;

    @BindView(R.id.et_account_bill_address_country)
    EditText editTextBillAddressCountry;

    @BindView(R.id.et_account_bill_address_state)
    EditText editTextBillAddressState;

    @BindView(R.id.et_account_bill_address_city)
    EditText editTextBillAddressCity;

    @BindView(R.id.et_account_shipping_street)
    EditText editTextAccountShippingStreet;

    @BindView(R.id.et_account_shipping_address_code)
    EditText editTextAccountShippingCode;

    @BindView(R.id.et_account_shipping_address_state)
    EditText editTextAccountShippingState;

    @BindView(R.id.et_account_shipping_address_city)
    EditText editTextAccountShippingCity;

    @BindView(R.id.et_account_shipping_address_country)
    EditText editTextAccountShippingCountry;

    @BindView(R.id.et_account_description)
    EditText editTextAccountDescription;

    @BindView(R.id.linear_layout_account_owner)
    LinearLayout linearLayoutAccountOwner;

    @BindView(R.id.linear_layout_account_rating)
    LinearLayout linearLayoutAccountRating;

    @BindView(R.id.linear_layout_account_site)
    LinearLayout linearLayoutAccountSite;

    @BindView(R.id.linear_layout_account_fax)
    LinearLayout linearLayoutAccountFax;

    @BindView(R.id.linear_layout_account_parent)
    LinearLayout linearLayoutAccountParent;
    @BindView(R.id.linear_layout_account_number)
    LinearLayout linearLayoutAccountNumber;

    @BindView(R.id.linear_layout_account_ticker_symbol)
    LinearLayout linearLayoutAccountTickerSymbol;

    @BindView(R.id.linear_layout_account_type)
    LinearLayout linearLayoutAccountType;

    @BindView(R.id.linear_layout_account_ownership)
    LinearLayout linearLayoutAccountOwnerShip;

    @BindView(R.id.linear_layout_account_industry)
    LinearLayout linearLayoutAccountIndustry;

    @BindView(R.id.linear_layout_account_employeee)
    LinearLayout linearLayoutAccountEmployee;
    @BindView(R.id.linear_layout_account_revenue)
    LinearLayout linearLayoutAccountRevenue;

    @BindView(R.id.linear_layout_account_sic_code)
    LinearLayout linearLayoutAccountSicCode;

    @BindView(R.id.linear_layout_address_information)
    LinearLayout linearLayoutAccountAddressInformation;

    @BindView(R.id.linear_layout_description_information)
    LinearLayout linearLayoutAccountDiscriptionInfo;

    @BindView(R.id.tv_smart_view)
    TextView textViewSmartView;

    @BindView(R.id.tv_account_name)
    TextView textViewAccountName;

    @BindView(R.id.tv_error_account_name)
    TextView textViewErrorAccountName;

    @BindView(R.id.tv_error_phone)
    TextView textViewErrorPhone;

    @BindView(R.id.tv_error_account_site)
    TextView textViewErrorAccountSite;

    @BindView(R.id.tv_error_fax)
    TextView textViewErrorFax;

    @BindView(R.id.tv_error_website)
    TextView textViewErrorWebsite;

    @BindView(R.id.tv_error_account_number)
            TextView textViewErrorAccountNumber;

    @BindView(R.id.tv_error_account_sic_code)
            TextView textViewErrorSicCode;


    SessionManager sessionManager;
    String selectedItem;
    private AccountDataModel accountDataModel;
    private TextView textViewError;
    private AlertDialogManager alertDialogManager;
    private DataBaseAdapter dataBaseAdapter;
    private SharedPrefUtil sharedPrefUtil;
    private String emptyAccountNameErrorMsg, showSmartView, showAllFields, errorTitle, titleAccountOwner, titleRating, titleAccountType, titleIndustry, titleAccounts, titleOwnerShip, errMsgInvalidPhoneNo, errMsgInvalidFax, errMsgInvalidURL, errMsgInvalidTwitterID, createdBy;
    private String[] itemList, ratingList, accountTypeList, ownershipList, industryList;
    private String title;
    private Intent intent;
    private long parentAccountId, accountId, createdTime;
    private boolean firstParent = true, editAccount = false;
    private boolean isSync = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);

        SessionManager.setContext(getApplicationContext());
        sessionManager = SessionManager.getInstance();

        ButterKnife.bind(this);

        editTextAccountRevenue.setFilters(new InputFilter[]{new MoneyValueFilter(editTextAccountRevenue)});
        editTextAccountRevenue.addTextChangedListener(new MoneyTextWatcher(editTextAccountRevenue));
        editTextAccountEmployee.addTextChangedListener(new MoneyTextWatcher(editTextAccountEmployee));

        alertDialogManager = new AlertDialogManager();
        dataBaseAdapter = new DataBaseAdapter(this);
        SharedPrefUtil.setContext(getApplicationContext());
        sharedPrefUtil = SharedPrefUtil.getInstance();

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        createdBy = sessionManager.getUserKeyId();
        Intent intent = getIntent();
        firstParent = intent.getBooleanExtra(PARENT_ACCOUNT, true);
        if (!firstParent) {
            linearLayoutAccountParent.setVisibility(View.GONE);
        }

        accountId = intent.getLongExtra(EmployConstantUtil.KEY_ACCOUNT_ID, 0);
        if (accountId != 0) {
            editAccount = true;
        }


        setClickListener();
        getStringResources();
        getAccountType();
        if (editAccount) {
            getAccountByID();
            toolbar.setTitle("Edit Account");
        }


    }

    private void getAccountByID() {
        accountDataModel = dataBaseAdapter.getAccountById(accountId);
        setAccountDetails(accountDataModel);
    }

    private void
      setAccountDetails(AccountDataModel accountDataModel) {
        textViewAccountOwner.setText(accountDataModel.getAccountOwner());
        textViewAccountRating.setText(accountDataModel.getRating());
        editTextAccountName.setText(accountDataModel.getAccountName());
        editTextAccountPhone.setText(accountDataModel.getPhone());
        editTextAccountSite.setText(accountDataModel.getAccountSite());
        editTextAccountFax.setText(accountDataModel.getFax());
        textViewAccountParent.setText(accountDataModel.getParentAccount());
        editTextAccountWebSite.setText(accountDataModel.getWebSite());
        if(accountDataModel.getAccountNumber()!=0) {
            editTextAccountNumber.setText(accountDataModel.getAccountNumber() + "");
        }
        editTextAccountTickerSymbol.setText(accountDataModel.getTickerSymbol());
        textViewAccountType.setText(accountDataModel.getAccountType());
        textViewAccountOwnerShip.setText(accountDataModel.getOwnerShip());
        textViewAccountIndustry.setText(accountDataModel.getIndustry());
        editTextAccountEmployee.setText(accountDataModel.getEmployees());
        editTextAccountRevenue.setText(accountDataModel.getAnnualRevenue());
        editTextAccountSicCode.setText(accountDataModel.getSicCode());
        editTextBillAddressStreet.setText(accountDataModel.getBillingAddressStreet());
        editTextBillAddressCity.setText(accountDataModel.getBillingAddressCity());
        editTextBillAddressCode.setText(accountDataModel.getBillingAddressCode());
        editTextBillAddressState.setText(accountDataModel.getBillingAddressState());
        editTextBillAddressCountry.setText(accountDataModel.getBillingAddressCountry());
        editTextAccountShippingStreet.setText(accountDataModel.getShippingAddressStreet());
        editTextAccountShippingCity.setText(accountDataModel.getShippingAddressCity());
        editTextAccountShippingCode.setText(accountDataModel.getShippingAddressCode());
        editTextAccountShippingCountry.setText(accountDataModel.getShippingAddressCountry());
        editTextAccountShippingState.setText(accountDataModel.getShippingAddressState());
        editTextAccountDescription.setText(accountDataModel.getDescription());
        createdBy = accountDataModel.getCreatedBy();
        createdTime = accountDataModel.getCreatedTime();
        isSync = accountDataModel.isSync();
        if (accountDataModel.getParentAccountId() != 0) {
            parentAccountId = accountDataModel.getParentAccountId();
        }
    }

    private void getAccountType() {
        String item = sharedPrefUtil.getAccountType();
        if (item == null) {
            DialogUtitlity.showLoading(AddAccountActivity.this);
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);
            final Call<List<AccountType>> response = apiService.getAccountType();
            response.enqueue(this);
        } else {
            accountTypeList = item.split(",");
        }

    }

    private void getStringResources() {
        textViewAccountName.setText(Html.fromHtml(EmployConstantUtil.ASTERISK + getResources().getString(R.string.label_add_account_account_name)));
        emptyAccountNameErrorMsg = getResources().getString(R.string.msg_account_name_empty);
        errorTitle = getResources().getString(R.string.err_msg_title_dialog);
        showAllFields = getResources().getString(R.string.footer_show_all_fields);
        showSmartView = getResources().getString(R.string.footer_smart_view);
        titleAccountOwner = getResources().getString(R.string.title_account_owner);
        titleRating = getResources().getString(R.string.label_add_account_rating);
        titleIndustry = getResources().getString(R.string.label_add_account_industry);
        titleAccountType = getResources().getString(R.string.label_add_account_account_type);
        titleAccounts = getResources().getString(R.string.title_parent_account);
        titleOwnerShip = getResources().getString(R.string.label_add_account_owner_ship);
        errMsgInvalidPhoneNo = getResources().getString(R.string.err_msg_phone_no_not_valid);
        errMsgInvalidURL = getResources().getString(R.string.err_msg_url_not_valid);
        ratingList = getResources().getStringArray(R.array.lead_rating);
        industryList = getResources().getStringArray(R.array.lead_industry);
        ownershipList = getResources().getStringArray(R.array.list_owner_ship);
        errMsgInvalidFax = getResources().getString(R.string.err_msg_fax_not_valid);


    }

    private void setClickListener() {
        textViewAccountOwner.setOnClickListener(this);
        textViewAccountRating.setOnClickListener(this);
        textViewAccountParent.setOnClickListener(this);
        textViewAccountType.setOnClickListener(this);
        textViewAccountOwnerShip.setOnClickListener(this);
        textViewAccountIndustry.setOnClickListener(this);
        textViewSmartView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.et_account_owner:
                itemList = new String[]{"Test"};
                title = titleAccountOwner;
                startNewActivity(title, itemList, REQUEST_CODE_ACCOUNT_OWNER);
                break;
            case R.id.tv_smart_view:
                startSmartView();
                break;
            case R.id.et_account_type:
                title = titleAccountType;
                itemList = accountTypeList;
                selectedItem= textViewAccountType.getText().toString().trim();
                startNewActivity(title, itemList, REQUEST_CODE_ACCOUNT_TYPE);
                break;
            case R.id.et_account_rating:
                title = titleRating;
                selectedItem = textViewAccountRating.getText().toString().trim();
                startNewActivity(title, ratingList, REQUEST_CODE_ACCOUNT_RATING);
                break;
            case R.id.et_account_industry:
                title = titleIndustry;
                selectedItem = textViewAccountIndustry.getText().toString().trim();
                startNewActivity(title, industryList, REQUEST_CODE_ACCOUNT_INDUSTRY);
                break;
            case R.id.et_account_ownership:
                title = titleOwnerShip;
                selectedItem = textViewAccountOwnerShip.getText().toString().trim();
                startNewActivity(title, ownershipList, REQUEST_CODE_ACCOUNT_OWNERSHIP);
                break;
            case R.id.et_account_parent:
                title = titleAccounts;
                selectedItem = textViewAccountParent.getText().toString().trim();
                startNewActivity(title, AccountNameListActivity.class);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.add_account:
                if (checkValidation())
                    if (!editAccount) {
                        alertDialogManager.showAlertDialogMessage(this, "", "Are you sure to create a Account", false, this);
                    } else {
                        alertDialogManager.showAlertDialogMessage(this, "", "Are you sure to update a Account", false, this);
                    }

                break;
        }

        return super.onOptionsItemSelected(item);


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
        if (textViewError != null) {
            textViewError.setVisibility(View.GONE);
        }
        if (editTextAccountName.getText().toString().trim().isEmpty()) {
            showErrorMsg(textViewErrorAccountName, emptyAccountNameErrorMsg, editTextAccountName);
            //showErrorDialog(emptyAccountNameErrorMsg);
            return false;
        }
        if (!EmployeeValidationUtil.validateName(editTextAccountName.getText().toString().trim(), 3)) {
            showErrorMsg(textViewErrorAccountName, getResources().getString(R.string.err_msg_account_name_char), editTextAccountName);
            //showErrorDialog(emptyAccountNameErrorMsg);
            return false;
        }
        String phone = editTextAccountPhone.getText().toString().trim();
        if (!phone.isEmpty() && !EmployeeValidationUtil.validateMobile(phone)) {
            showErrorMsg(textViewErrorPhone, errMsgInvalidPhoneNo, editTextAccountPhone);
            //showErrorDialog(errMsgInvalidPhoneNo);
            return false;
        }
        String website = editTextAccountWebSite.getText().toString().trim();
        if (!website.isEmpty() && !EmployeeValidationUtil.validateUrl(website)) {
            showErrorMsg(textViewErrorWebsite, errMsgInvalidURL, editTextAccountWebSite);
            //showErrorDialog(errMsgInvalidURL);
            return false;
        }
        if (textViewSmartView.getText().toString().equals(showSmartView)) {
            String fax = editTextAccountFax.getText().toString().trim();
            String accountNumber = editTextAccountNumber.getText().toString().trim();

                if (!fax.isEmpty() && !EmployeeValidationUtil.validateFax(fax)) {
                    showErrorMsg(textViewErrorFax, errMsgInvalidFax, editTextAccountFax);
                    //showErrorDialog(errMsgInvalidFax);
                    return false;
                }

            if (!accountNumber.isEmpty() && !EmployeeValidationUtil.validateName(accountNumber,16)) {
                showErrorMsg(textViewErrorAccountNumber, getResources().getString(R.string.err_msg_account_number), editTextAccountNumber);
                //showErrorDialog(errMsgInvalidFax);
                return false;
            }



            String sicCode = editTextAccountSicCode.getText().toString().trim();
            if (!sicCode.isEmpty() && !EmployeeValidationUtil.validateName(sicCode,4)) {
                showErrorMsg(textViewErrorSicCode, getResources().getString(R.string.err_msg_sic_code), editTextAccountSicCode);
                //showErrorDialog(errMsgInvalidFax);
                return false;
            }




        }


        return true;
    }

    private boolean showErrorMsg(TextView textView, String errorMessage, EditText editText) {
        editText.requestFocus();
        textView.setText(errorMessage);
        textView.setVisibility(View.VISIBLE);
        textViewError = textView;
        return false;
    }

    private void addAccountData() {
        accountDataModel = new AccountDataModel();
        if (editAccount) {
            accountDataModel.setId(accountId);

        } else {

            accountDataModel.setId(DateAndTimeUtil.currentTimeStamp());
            createdTime = DateAndTimeUtil.currentTimeStamp();

        }
        accountDataModel.setCreatedTime(createdTime);
        accountDataModel.setAccountOwner(checkSelectListItem(textViewAccountOwner.getText().toString().trim()));
        accountDataModel.setRating(checkSelectListItem(textViewAccountRating.getText().toString().trim()));
        accountDataModel.setAccountName(editTextAccountName.getText().toString().trim());
        accountDataModel.setPhone(editTextAccountPhone.getText().toString().trim());
        accountDataModel.setAccountSite(editTextAccountSite.getText().toString().trim());
        accountDataModel.setWebSite(editTextAccountWebSite.getText().toString().trim());
        accountDataModel.setFax(editTextAccountFax.getText().toString().trim());
        accountDataModel.setAccountNumber(checkIntegerIsNull(editTextAccountNumber.getText().toString().trim()));
        accountDataModel.setParentAccount(checkSelectListItem(textViewAccountParent.getText().toString().trim()));
        accountDataModel.setTickerSymbol(editTextAccountTickerSymbol.getText().toString().trim());
        accountDataModel.setAccountType(checkSelectListItem(textViewAccountType.getText().toString().trim()));
        accountDataModel.setOwnerShip(checkSelectListItem(textViewAccountOwnerShip.getText().toString().trim()));
        accountDataModel.setIndustry(checkSelectListItem(textViewAccountIndustry.getText().toString().trim()));
        accountDataModel.setEmployees(editTextAccountEmployee.getText().toString().trim());
        accountDataModel.setAnnualRevenue(editTextAccountRevenue.getText().toString().trim());
        accountDataModel.setSicCode(editTextAccountSicCode.getText().toString().trim());
        accountDataModel.setBillingAddressStreet(editTextBillAddressStreet.getText().toString().trim());
        accountDataModel.setBillingAddressCity(editTextBillAddressCity.getText().toString().trim());
        accountDataModel.setBillingAddressState(editTextBillAddressState.getText().toString().trim());
        accountDataModel.setBillingAddressCode(editTextBillAddressCode.getText().toString().trim());
        accountDataModel.setBillingAddressCountry(editTextBillAddressCountry.getText().toString().trim());
        accountDataModel.setShippingAddressStreet(editTextAccountShippingStreet.getText().toString().trim());
        accountDataModel.setShippingAddressCity(editTextAccountShippingCity.getText().toString().trim());
        accountDataModel.setShippingAddressCode(editTextAccountShippingCode.getText().toString().trim());
        accountDataModel.setShippingAddressCountry(editTextAccountShippingCountry.getText().toString().trim());
        accountDataModel.setShippingAddressState(editTextAccountShippingState.getText().toString().trim());
        accountDataModel.setDescription(editTextAccountDescription.getText().toString().trim());
        accountDataModel.setParentAccountId(parentAccountId);
        accountDataModel.setCreatedBy(createdBy);
        accountDataModel.setModifyBy(sessionManager.getUserKeyId());
        accountDataModel.setModifyTime(DateAndTimeUtil.currentTimeStamp());
        accountDataModel.setSync(isSync);
        dataBaseAdapter.openDataBase();


        if (editAccount) {
            if (!isSync) {

                if (checkConnection()) {
                    addAccountDataToServer();
                } else {
                    accountDataModel.setSync(isSync);
                    dataBaseAdapter.updateAccount(accountDataModel, accountId);
                    alertDialogManager.showAlertDialogMessage1(this, "", "Account is Updated ", false, this);
                }


            } else {

                if (checkConnection()) {
                    addAccountDataToServer();
                } else {
                    Toast.makeText(this, "Please check your Internet connection ", Toast.LENGTH_SHORT).show();
                }
            }

        } else {

            if (checkConnection()) {
                addAccountDataToServer();
            } else {
                //    isSync = false;
                dataBaseAdapter.addAccount(accountDataModel);
                alertDialogManager.showAlertDialogMessage1(this, "", "Account is created ", false, this);

            }
        }

    }

    public void showErrorDialog(String errMessage) {
        alertDialogManager.showAlertDialog(this, errorTitle, errMessage, false);

    }

    private long checkIntegerIsNull(String value) {

        return value.isEmpty() ? 0 : Long.parseLong(value);
    }

    private String checkSelectListItem(String selectedItem) {

        return selectedItem.equals(EmployConstantUtil.NOTING_SELECTED_VALUE) ? "" : selectedItem;
    }

    private void addAccountDataToServer() {
        DialogUtitlity.showLoading(AddAccountActivity.this);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        final Call<String> response = apiService.addAccount(accountDataModel, "admin");
        response.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                DialogUtitlity.hideLoading();
                switch (response.code()) {
                    case 201:
                        accountDataModel.setSync(true);


                        break;
                    case 400:
                        accountDataModel.setSync(false);
                        break;
                }

                    updateAccount();


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                DialogUtitlity.hideLoading();
                updateAccount();
            }
        });

    }


    private void updateAccount(){
        if (!editAccount) {

            dataBaseAdapter.addAccount(accountDataModel);
            alertDialogManager.showAlertDialogMessage1(AddAccountActivity.this, "", "Account is created ", false, AddAccountActivity.this);
        } else {

            accountDataModel.setSync(true);
            dataBaseAdapter.updateAccount(accountDataModel, accountId);
            alertDialogManager.showAlertDialogMessage1(AddAccountActivity.this, "", "Account is updated", false, AddAccountActivity.this);
        }

        Log.e(TAG, "NOT CREATED");
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();

    }

    private void startNewActivity(String title, String[] itemList, int requestCode) {

        intent = new Intent(AddAccountActivity.this, CustomListActivity.class);
        intent.putExtra(EmployConstantUtil.ITEM_LIST, itemList);
        intent.putExtra(EmployConstantUtil.TITLE, title);
        startActivityForResult(intent, requestCode);

    }

    private void startNewActivity(String title, Class<?> cls) {
        intent = new Intent(AddAccountActivity.this, cls);
        startActivityForResult(intent, REQUEST_CODE_ACCOUNT_PARENT);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            selectedItem = data.getStringExtra(EmployConstantUtil.SELECTED_ITEM);
            parentAccountId = data.getLongExtra(EmployConstantUtil.KEY_ACCOUNT_ID, 0);
            Log.e(TAG, "Add Account "+ parentAccountId);
        }
        switch (requestCode) {
            case REQUEST_CODE_ACCOUNT_OWNER:
                textViewAccountOwner.setText(selectedItem);
                break;
            case REQUEST_CODE_ACCOUNT_INDUSTRY:
                textViewAccountIndustry.setText(selectedItem);
                break;
            case REQUEST_CODE_ACCOUNT_OWNERSHIP:
                textViewAccountOwnerShip.setText(selectedItem);
                break;
            case REQUEST_CODE_ACCOUNT_TYPE:
                textViewAccountType.setText(selectedItem);
                break;
            case REQUEST_CODE_ACCOUNT_RATING:
                textViewAccountRating.setText(selectedItem);
                break;
            case REQUEST_CODE_ACCOUNT_PARENT:
                textViewAccountParent.setText(DateAndTimeUtil.firstLatterUpper(selectedItem));
                break;


        }

    }


    private void startSmartView() {
        if (textViewSmartView.getText().toString().equals(showSmartView)) {
            textViewSmartView.setText(showAllFields);
            linearLayoutAccountOwner.setVisibility(View.GONE);
            linearLayoutAccountRating.setVisibility(View.GONE);
            linearLayoutAccountSite.setVisibility(View.GONE);
            linearLayoutAccountFax.setVisibility(View.GONE);
            linearLayoutAccountParent.setVisibility(View.GONE);
            linearLayoutAccountNumber.setVisibility(View.GONE);
            linearLayoutAccountTickerSymbol.setVisibility(View.GONE);
            linearLayoutAccountType.setVisibility(View.GONE);
            linearLayoutAccountOwnerShip.setVisibility(View.GONE);
            linearLayoutAccountIndustry.setVisibility(View.GONE);
            linearLayoutAccountEmployee.setVisibility(View.GONE);
            linearLayoutAccountRevenue.setVisibility(View.GONE);
            linearLayoutAccountSicCode.setVisibility(View.GONE);
            linearLayoutAccountAddressInformation.setVisibility(View.GONE);
            linearLayoutAccountDiscriptionInfo.setVisibility(View.GONE);
        } else {
            linearLayoutAccountOwner.setVisibility(View.VISIBLE);
            linearLayoutAccountRating.setVisibility(View.VISIBLE);
            linearLayoutAccountSite.setVisibility(View.VISIBLE);
            linearLayoutAccountFax.setVisibility(View.VISIBLE);
            if (!firstParent) {
                linearLayoutAccountParent.setVisibility(View.VISIBLE);
            }

            linearLayoutAccountNumber.setVisibility(View.VISIBLE);
            linearLayoutAccountTickerSymbol.setVisibility(View.VISIBLE);

            linearLayoutAccountType.setVisibility(View.VISIBLE);
            linearLayoutAccountOwnerShip.setVisibility(View.VISIBLE);
            linearLayoutAccountIndustry.setVisibility(View.VISIBLE);
            linearLayoutAccountEmployee.setVisibility(View.VISIBLE);
            linearLayoutAccountRevenue.setVisibility(View.VISIBLE);
            linearLayoutAccountSicCode.setVisibility(View.VISIBLE);
            linearLayoutAccountAddressInformation.setVisibility(View.VISIBLE);
            linearLayoutAccountDiscriptionInfo.setVisibility(View.VISIBLE);
            textViewSmartView.setText(showSmartView);
        }
    }

    @Override
    public void onResponse(Call<List<AccountType>> call, Response<List<AccountType>> response) {
        DialogUtitlity.hideLoading();
        int statusCode = response.code();
        if (statusCode == 200) {
            if (response.body() instanceof List) {
                List<AccountType> scheduleList = response.body();
                if (!scheduleList.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    for (AccountType accountType : scheduleList) {
                        sb.append(accountType.getAccTypeList()).append(",");

                    }

                    sharedPrefUtil.setAccountType(sb.toString());
                    accountTypeList = sharedPrefUtil.getAccountType().split(",");
                } else {

                }

            }
        }

    }

    @Override
    public void onFailure(Call<List<AccountType>> call, Throwable t) {
        DialogUtitlity.hideLoading();

    }

    @Override
    public void alertDialogCallbackOk() {

        addAccountData();


    }

    @Override
    public void alertDialogCallbackCancel() {

    }

    @Override
    public void alertDialogCallbackOk1() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}








