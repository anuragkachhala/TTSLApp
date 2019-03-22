package com.software.ttsl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.software.ttsl.Interfacce.AlertDialogCallback;
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
import com.software.ttsl.Utils.SessionManager;
import com.software.ttsl.Utils.SharedPrefUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAccountActivity extends AppCompatActivity implements View.OnClickListener, AlertDialogCallback, Callback<String> {
    public static final String TAG = EditAccountActivity.class.getName();

    private static final int REQUEST_CODE = 1000;


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.et_account_owner)
    EditText editTextAccountOwner;

    @BindView(R.id.et_account_rating)
    EditText editTextAccountRating;

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
    EditText editTextAccountParent;

    @BindView(R.id.et_account_type)
    EditText editTextAccountType;

    @BindView(R.id.et_account_ownership)
    EditText editTextAccountOwnerShip;

    @BindView(R.id.et_account_industry)
    EditText editTextAccountIndustry;

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


    private AccountDataModel accountDataModel;
    private AlertDialogManager alertDialogManager;
    private DataBaseAdapter dataBaseAdapter;
    private SharedPrefUtil sharedPrefUtil;
    private String emptyAccountNameErrorMsg, showSmartView, showAllFields, errorTitle, titleAccountOwner, titleRating, titleAccountType, titleIndustry, titleAccounts, titleOwnerShip, errMsgInvalidPhoneNo, errMsgInvalidFax, errMsgInvalidURL, errMsgInvalidTwitterID, createdBy;
    private String[] itemList, ratingList, accountTypeList, ownershipList, industryList;
    private String title;
    private Intent intent;
    private long parentAccountId;
    private Long accountId;
    private long createdTime;

    private Boolean isSync;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        SessionManager.setContext(getApplicationContext());
        sessionManager = SessionManager.getInstance();
        SharedPrefUtil.setContext(getApplicationContext());
        sharedPrefUtil = SharedPrefUtil.getInstance();


        ButterKnife.bind(this);

        Intent intent = getIntent();
        accountId = intent.getLongExtra(EmployConstantUtil.KEY_ACCOUNT_ID, 0);
        Log.v(TAG, "edit account id " + accountId);

        alertDialogManager = new AlertDialogManager();
        dataBaseAdapter = new DataBaseAdapter(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        getAccountById();
        setClickListener();
        getStringResources();

    }

    private void getAccountById() {
        accountDataModel = dataBaseAdapter.getAccountById(accountId);
        setAccountDetails(accountDataModel);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        updateUI();
    }

    private void updateUI() {

    }


    private void getStringResources() {
        String panNo = "<font color='#ED5565'>*</font>" + getResources().getString(R.string.label_add_account_account_name);
        textViewAccountName.setText(Html.fromHtml(panNo));
        //textViewAccountName.setText(Html.fromHtml(getResources().getString(R.string.label_add_account_account_name)));
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
        getAccountType();

    }


    private void getAccountType() {
        String item = sharedPrefUtil.getAccountType();
        if (item == null) {
            DialogUtitlity.showLoading(EditAccountActivity.this);
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);
            final Call<List<AccountType>> response = apiService.getAccountType();
            response.enqueue(new Callback<List<AccountType>>() {
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
                                itemList = sharedPrefUtil.getAccountType().split(",");
                            } else {

                            }

                        }
                    }

                }

                @Override
                public void onFailure(Call<List<AccountType>> call, Throwable t) {
                    DialogUtitlity.hideLoading();

                }

            });
        } else {
            itemList = item.split(",");
        }

    }

    private void setClickListener() {
        editTextAccountOwner.setOnClickListener(this);
        editTextAccountRating.setOnClickListener(this);
        editTextAccountParent.setOnClickListener(this);
        editTextAccountType.setOnClickListener(this);
        editTextAccountOwnerShip.setOnClickListener(this);
        editTextAccountIndustry.setOnClickListener(this);
        textViewSmartView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.et_account_owner:
                itemList = new String[]{"Test"};
                title = titleAccountOwner;
                startNewActivity(title, itemList);
                break;
            case R.id.tv_smart_view:
                startSmartView();
                break;
            case R.id.et_account_type:
                title = titleAccountType;
                itemList = accountTypeList;
                startNewActivity(title, itemList);
                break;
            case R.id.et_account_rating:
                title = titleRating;
                startNewActivity(title, ratingList);
                break;
            case R.id.et_account_industry:
                title = titleIndustry;
                startNewActivity(title, industryList);
                break;
            case R.id.et_account_ownership:

                title = titleOwnerShip;
                startNewActivity(title, ownershipList);
                break;
            case R.id.et_account_parent:
                title = titleAccounts;
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

               /* if (setLeadData()) {
                    addLeadData();

                }*/
                if (checkValidation())
                    updateAccountData();

                break;
        }

        return super.onOptionsItemSelected(item);


    }


    private boolean checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        if (!isConnected) {

            return false;
        }

        return true;
    }

    private boolean checkValidation() {
        if (editTextAccountName.getText().toString().trim().isEmpty()) {
            showErrorDialog(emptyAccountNameErrorMsg);
            return false;
        }
        String phone = editTextAccountPhone.getText().toString().trim();
        if (!phone.isEmpty() && !EmployeeValidationUtil.validateMobile(phone)) {
            showErrorDialog(errMsgInvalidPhoneNo);
            return false;
        }
        String website = editTextAccountWebSite.getText().toString().trim();
        if (!website.isEmpty() && !EmployeeValidationUtil.validateUrl(website)) {
            showErrorDialog(errMsgInvalidURL);
            return false;
        }
        if (textViewSmartView.getText().toString().equals(showSmartView)) {
            String fax = editTextAccountFax.getText().toString().trim();
            {
                if (!fax.isEmpty() && !EmployeeValidationUtil.validateFax(fax)) {
                    showErrorDialog(errMsgInvalidFax);
                    return false;
                }

            }


        }


        return true;
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

    private void startNewActivity(String title, Class<?> cls) {
        intent = new Intent(this, cls);
        startActivityForResult(intent, 1000);

    }


    private void setAccountDetails(AccountDataModel accountDataModel) {
        editTextAccountOwner.setText(accountDataModel.getAccountOwner());
        editTextAccountRating.setText(accountDataModel.getRating());
        editTextAccountName.setText(accountDataModel.getAccountName());
        editTextAccountPhone.setText(accountDataModel.getPhone());
        editTextAccountSite.setText(accountDataModel.getAccountSite());
        editTextAccountFax.setText(accountDataModel.getFax());
        editTextAccountParent.setText(accountDataModel.getParentAccount());
        editTextAccountWebSite.setText(accountDataModel.getWebSite());
        editTextAccountNumber.setText(String.valueOf(accountDataModel.getAccountNumber()));
        editTextAccountTickerSymbol.setText(accountDataModel.getTickerSymbol());
        editTextAccountType.setText(accountDataModel.getAccountType());
        editTextAccountOwnerShip.setText(accountDataModel.getOwnerShip());
        editTextAccountIndustry.setText(accountDataModel.getIndustry());
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


    private void updateAccountData() {
        accountDataModel = new AccountDataModel();
        accountDataModel.setAccountOwner(checkSelectListItem(editTextAccountOwner.getText().toString().trim()));
        accountDataModel.setRating(checkSelectListItem(editTextAccountRating.getText().toString().trim()));
        accountDataModel.setAccountName(editTextAccountName.getText().toString().trim());
        accountDataModel.setPhone(editTextAccountPhone.getText().toString().trim());
        accountDataModel.setAccountSite(editTextAccountSite.getText().toString().trim());
        accountDataModel.setWebSite(editTextAccountWebSite.getText().toString().trim());
        accountDataModel.setFax(editTextAccountFax.getText().toString().trim());
        accountDataModel.setAccountNumber(checkIntegerIsNull(editTextAccountNumber.getText().toString().trim()));
        accountDataModel.setParentAccount(checkSelectListItem(editTextAccountParent.getText().toString().trim()));
        accountDataModel.setTickerSymbol(editTextAccountTickerSymbol.getText().toString().trim());
        accountDataModel.setAccountType(checkSelectListItem(editTextAccountType.getText().toString().trim()));
        accountDataModel.setOwnerShip(checkSelectListItem(editTextAccountOwnerShip.getText().toString().trim()));
        accountDataModel.setIndustry(checkSelectListItem(editTextAccountIndustry.getText().toString().trim()));
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
        accountDataModel.setCreatedTime(createdTime);
        accountDataModel.setModifyTime(DateAndTimeUtil.currentTimeStamp());
        dataBaseAdapter.openDataBase();

       /*if(result ==1){
           Toast.makeText(this,"updated",Toast.LENGTH_SHORT).show();
           Toast.makeText(this, "Lead Update Successfully", Toast.LENGTH_SHORT).show();
           startActivity(new Intent(EditAccountActivity.this, AccountListActivity.class));
           finish();

       }else {
           Toast.makeText(this,"update fail ",Toast.LENGTH_SHORT).show();
       }*/

        if (!isSync) {

            if (checkConnection()) {
                updateAccountDataToServer();
            } else {
                accountDataModel.setSync(isSync);
                dataBaseAdapter.updateAccount(accountDataModel, accountId);
                alertDialogManager.showAlertDialogMessage(this, "", "Account is Updated ", false, this);
            }


        } else {

            if (checkConnection()) {
                updateAccountDataToServer();
            } else {
                Toast.makeText(this, "Please check your Internet connection ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void updateAccountDataToServer() {

        DialogUtitlity.showLoading(EditAccountActivity.this);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> listCall = apiInterface.addAccount(accountDataModel, "");
        Log.e("addAccount", "inside addAccountData to server");
        listCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        DialogUtitlity.hideLoading();
        Log.e("addAccount", "inside UpdateAccountData to server");
        int statusCode = response.code();
        Log.e("addAccount", String.valueOf(statusCode));
        if (statusCode == 200) {
            if (response.body() instanceof String) {

                accountDataModel.setSync(isSync);

            }


        } else if (statusCode == 201) {
            accountDataModel.setSync(true);


        } else if (statusCode == 500) {


            accountDataModel.setSync(isSync);

        }

        int result = dataBaseAdapter.updateAccount(accountDataModel, accountId);
        alertDialogManager.showAlertDialogMessage(this, "", "Account is updated ", false, this);


    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

    }


    private void startNewActivity(String title, String[] itemList) {

        intent = new Intent(EditAccountActivity.this, CustomListActivity.class);
        intent.putExtra(EmployConstantUtil.ITEM_LIST, itemList);
        intent.putExtra(EmployConstantUtil.TITLE, title);
        startActivityForResult(intent, 1000);

    }

    private void startNewActivity(String title) {
        intent = new Intent(EditAccountActivity.this, AccountNameListActivity.class);
        startActivityForResult(intent, 1000);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == RESULT_OK) {
                String selectedItem = data.getStringExtra(EmployConstantUtil.SELECTED_ITEM);
                String title = data.getStringExtra(EmployConstantUtil.TITLE);
                if (title.equals(titleAccountOwner)) {
                    editTextAccountOwner.setText(selectedItem);
                } else if (title.equals(titleAccountType)) {
                    editTextAccountType.setText(selectedItem);
                } else if (title.equals(titleRating)) {
                    editTextAccountRating.setText(selectedItem);
                } else if (title.equals(titleIndustry)) {
                    editTextAccountIndustry.setText(selectedItem);

                } else if (title.equals(titleOwnerShip)) {
                    editTextAccountOwnerShip.setText(selectedItem);
                } else if (title.equals(titleAccounts)) {
                    parentAccountId = data.getLongExtra(EmployConstantUtil.KEY_ACCOUNT_ID, 0);
                    editTextAccountParent.setText(selectedItem);

                }
            }
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
            linearLayoutAccountParent.setVisibility(View.VISIBLE);
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
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void alertDialogCallbackOk() {
        startActivity(new Intent(EditAccountActivity.this, AccountListActivity.class));
        finish();
    }

    @Override
    public void alertDialogCallbackCancel() {

    }


}
