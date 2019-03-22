package com.software.ttsl;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.software.ttsl.Interfacce.AlertDialogCallback;
import com.software.ttsl.Interfacce.AlertDialogCallback1;
import com.software.ttsl.Request.DealDataModel;
import com.software.ttsl.Response.DealStage;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDealActivity extends AppCompatActivity implements View.OnClickListener, Callback<List<DealStage>>, AlertDialogCallback, AlertDialogCallback1 {

    public static final String TAG = AddDealActivity.class.getName();

    private static final int REQUEST_CODE_DEAL_ACCOUNT_NAME = 1000;
    private static final int REQUEST_CODE_DEAL_CONTACT_NAME = 2000;
    private static final int REQUEST_CODE_DEAL_OWNER = 3000;
    private static final int REQUEST_CODE_DEAL_STAGE = 4000;
    private static final int REQUEST_CODE_DEAL_TYPE = 5000;
    private static final int REQUEST_CODE_DEAL_LEAD_SOURCE = 6000;
    private static final int REQUEST_CODE_DEAL_CAMPAIGN_SOURCE = 7000;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.linear_layout_deal_owner)
    LinearLayout linearLayoutDealOwner;

    @BindView(R.id.linear_layout_deal_type)
    LinearLayout linearLayoutDealType;

    @BindView(R.id.linear_layout_deal_probability)
    LinearLayout linearLayoutDealProbability;

    @BindView(R.id.linear_layout_deal_next_step)
    LinearLayout linearLayoutDealNextStep;

    @BindView(R.id.linear_layout_lead_source)
    LinearLayout linearLayoutLeadSource;

    @BindView(R.id.linear_layout_deal_campaign_source)
    LinearLayout linearLayoutDealCampaignSource;

    @BindView(R.id.linear_layout_contact_name)
    LinearLayout linearLayoutContactName;

    @BindView(R.id.linear_layout_description_information)
    LinearLayout linearLayoutDescriptionInformation;

    @BindView(R.id.et_deal_owner)
    EditText editTextDealOwner;

    @BindView(R.id.et_deal_amount)
    EditText editTextDealAmount;

    @BindView(R.id.et_deal_name)
    EditText editTextDealName;

    @BindView(R.id.et_deal_closing_date)
    EditText editTextClosingDate;

    @BindView(R.id.et_account_name)
    EditText editTextAccountName;

    @BindView(R.id.et_deal_stage)
    EditText editTextDealStage;

    @BindView(R.id.et_deal_type)
    EditText editTextDealType;

    @BindView(R.id.et_deal_probability)
    EditText editTextDealProbability;

    @BindView(R.id.et_next_step)
    EditText editTextNextStep;

    @BindView(R.id.et_deal_ex_revenue)
    EditText editTextDealExRevenue;

    @BindView(R.id.et_lead_source)
    EditText editTextLeadSource;

    @BindView(R.id.et_deal_campaign_source)
    EditText editTextDealCampaignSource;

    @BindView(R.id.et_contact_name)
    EditText editTextContactName;

    @BindView(R.id.et_deal_description)
    EditText editTextDealDescription;

    @BindView(R.id.iv_deal_owner)
    ImageView imageViewDealOwner;

    @BindView(R.id.iv_deal_closing_date)
    ImageView imageViewDealClosingDate;

    @BindView(R.id.iv_account_name)
    ImageView imageViewAccountName;

    @BindView(R.id.iv_deal_stage)
    ImageView imageViewDealStage;

    @BindView(R.id.iv_deal_type)
    ImageView imageViewDealType;

    @BindView(R.id.iv_lead_source)
    ImageView imageViewLeadSource;

    @BindView(R.id.iv_deal_campaign_source)
    ImageView imageViewDealCampaignSource;

    @BindView(R.id.iv_contact_name)
    ImageView imageViewContactName;

    @BindView(R.id.tv_smart_view)
    TextView textViewSmartView;

    @BindView(R.id.tv_contact_name)
    TextView textViewLabelContact;


    @BindView(R.id.tv_deal_name)
    TextView textViewDealName;

    @BindView(R.id.tv_deal_closing_date)
    TextView textViewDealClosingDate;

    @BindView(R.id.tv_account_name)
    TextView textViewDealAccountName;

    @BindView(R.id.tv_deal_stage)
    TextView textViewDealStage;

    @BindView(R.id.tv_error_deal_name)
    TextView textViewErrorDealName;

    @BindView(R.id.tv_error_deal_closing_date)
    TextView textViewErrorDealClosingDate;

    @BindView(R.id.tv_error_deal_account_name)
    TextView textViewErrorDealAccountName;

    @BindView(R.id.tv_error_deal_stage)
    TextView textViewErrorDealStage;

    private TextView textViewError;
    private String createdBy, emptyAccountNameErrorMsg, emptyDealNameErrorMsg, emptyClosingDateErrorMsg, emptyStageErrorMsg, showSmartView, showAllFields, errorTitle, titleDealType, titleStage, titleDealOwner, titleAccountName, titleContactName, titleCampaignSource, titleLeadSource;
    private SharedPrefUtil sharedPrefUtil;
    private AlertDialogManager alertDialogManager;
    private DealDataModel dealDataModel;
    private DataBaseAdapter dataBaseAdapter;
    private SessionManager sessionManager;
    private Intent intent;
    private String[] itemList, leadSourceList, dealList;
    private String title;
    private String stagesList[];
    private int mYear, mMonth, mDay;
    private long accountId, contactId, dealId, createdTime;
    private Boolean isSync = false, editDeal = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_deal);

        ButterKnife.bind(this);

        alertDialogManager = new AlertDialogManager();
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SessionManager.setContext(getApplicationContext());
        sessionManager = SessionManager.getInstance();

        createdBy = sessionManager.getUserKeyId();

        editTextDealExRevenue.setFilters(new InputFilter[]{new MoneyValueFilter(editTextDealExRevenue)});
        editTextDealExRevenue.addTextChangedListener(new MoneyTextWatcher(editTextDealExRevenue));
        editTextDealAmount.setFilters(new InputFilter[]{new MoneyValueFilter(editTextDealAmount)});
        editTextDealAmount.addTextChangedListener(new MoneyTextWatcher(editTextDealAmount));
        editTextDealProbability.setFilters(new InputFilter[]{new MoneyValueFilter(editTextDealProbability,2,4)});

            setProbability();


        dataBaseAdapter = new DataBaseAdapter(this);

        SharedPrefUtil.setContext(getApplicationContext());
        sharedPrefUtil = SharedPrefUtil.getInstance();

        Intent intent = getIntent();

        dealId = intent.getLongExtra(EmployConstantUtil.KEY_DEAL_ID, 0);
        if (dealId != 0) {
            editDeal = true;

        }


        setClickListener();

        getStringResources();

        getDealState();




        if (editDeal) {
            getDealById();
            toolbar.setTitle(getResources().getString(R.string.title_edit_deal_activity));
        }


    }





    private void getDealById() {
        dealDataModel = dataBaseAdapter.getDealById(dealId);
        setDealDetails(dealDataModel);
    }


    private void setDealDetails(DealDataModel dealDataModel) {

        editTextDealOwner.setText(dealDataModel.getDealOwner());
        editTextDealAmount.setText(String.valueOf(dealDataModel.getAmount()));
        editTextAccountName.setText(dealDataModel.getAccountName());
        editTextDealName.setText(dealDataModel.getDealName());
        editTextClosingDate.setText(dealDataModel.getClosingDate());
        editTextDealCampaignSource.setText(dealDataModel.getCampaignSource());
        editTextDealStage.setText(dealDataModel.getStage());
        editTextNextStep.setText(dealDataModel.getNextStep());
        editTextContactName.setText(dealDataModel.getContactName());
        editTextDealDescription.setText(dealDataModel.getDescription());
        editTextDealExRevenue.setText(dealDataModel.getExpectedRevenue());
        editTextDealProbability.setText(String.valueOf(dealDataModel.getProbability()));
        editTextDealExRevenue.setText(dealDataModel.getExpectedRevenue());
        editTextDealType.setText(dealDataModel.getType());
        editTextLeadSource.setText(dealDataModel.getLeadSource());
        createdTime = dealDataModel.getCreatedTime();
        createdBy = dealDataModel.getCreatedBy();
        isSync = dealDataModel.isSync();


    }


    private String checkSelectListItem(String selectedItem) {

        return selectedItem.equals(EmployConstantUtil.NOTING_SELECTED_VALUE) ? "" : selectedItem;
    }


    private void getDealState() {
        String item = sharedPrefUtil.getDealStage();
        if (item == null) {
          //  DialogUtitlity.showLoading(AddDealActivity.this);
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);
            final Call<List<DealStage>> response = apiService.getDealStage();
            response.enqueue(this);
        } else {
            stagesList = item.split(",");
        }

    }


    private void setClickListener() {

        editTextDealOwner.setOnClickListener(this);
        editTextAccountName.setOnClickListener(this);
        editTextDealStage.setOnClickListener(this);
        editTextDealType.setOnClickListener(this);
        editTextClosingDate.setOnClickListener(this);
        editTextLeadSource.setOnClickListener(this);
        editTextDealCampaignSource.setOnClickListener(this);
        editTextContactName.setOnClickListener(this);
        textViewSmartView.setOnClickListener(this);
    }

    private void getStringResources() {
        titleContact = getResources().getString(R.string.title_task_contact);
        titleLeads = getResources().getString(R.string.title_task_lead);
        textViewDealName.setText(Html.fromHtml(EmployConstantUtil.ASTERISK+getResources().getString(R.string.label_deal_name)));
        textViewDealClosingDate.setText(Html.fromHtml(EmployConstantUtil.ASTERISK+getResources().getString(R.string.label_deal_closing_date)));
        textViewDealAccountName.setText(Html.fromHtml(EmployConstantUtil.ASTERISK+getResources().getString(R.string.label_deal_account_name)));
        textViewDealStage.setText(Html.fromHtml(EmployConstantUtil.ASTERISK+getResources().getString(R.string.label_deal_stage)));
        emptyAccountNameErrorMsg = getResources().getString(R.string.msg_deal_account_name);
        emptyClosingDateErrorMsg = getResources().getString(R.string.msg_deal_closing_date);
        emptyDealNameErrorMsg = getResources().getString(R.string.msg_deal_name_empty);
        emptyStageErrorMsg = getResources().getString(R.string.msg_deal_stage);
        errorTitle = getResources().getString(R.string.err_msg_title_dialog);
        showAllFields = getResources().getString(R.string.footer_show_all_fields);
        showSmartView = getResources().getString(R.string.footer_smart_view);
        titleDealOwner = getResources().getString(R.string.label_deal_owner);
        titleCampaignSource = getResources().getString(R.string.title_campaign_source);
        titleDealType = getResources().getString(R.string.title_type);
        titleContactName = getResources().getString(R.string.title_contact_list_activity);
        titleAccountName = getResources().getString(R.string.title_account_list_activity);
        titleStage = getResources().getString(R.string.title_stage);
        titleLeadSource = getResources().getString(R.string.title_lead_source);
        leadSourceList = getResources().getStringArray(R.array.lead_source);
        dealList = getResources().getStringArray(R.array.list_deal_type);


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.tv_smart_view:
                startSmartView();
                break;
            case R.id.et_deal_owner:
                selectedItem = "Test";
                startNewActivity(titleDealOwner, new String[]{"Test"}, REQUEST_CODE_DEAL_OWNER);
                break;
            case R.id.et_deal_stage:
                selectedItem = editTextDealStage.getText().toString().trim();
                startNewActivity(titleStage, stagesList, REQUEST_CODE_DEAL_STAGE);
                break;
            case R.id.et_lead_source:
                selectedItem = editTextLeadSource.getText().toString().trim();
                startNewActivity(titleLeadSource, leadSourceList, REQUEST_CODE_DEAL_LEAD_SOURCE);
                break;
            case R.id.et_deal_type:
                selectedItem = editTextDealType.getText().toString().trim();
                startNewActivity(titleDealType, dealList, REQUEST_CODE_DEAL_TYPE);
                break;
            case R.id.et_deal_closing_date:
                openCalender();
                break;
            case R.id.et_deal_campaign_source:
                title = titleCampaignSource;
                startNewActivity(titleCampaignSource, new String[]{}, REQUEST_CODE_DEAL_CAMPAIGN_SOURCE);
                break;

            case R.id.et_account_name:
                startNewActivity(titleAccountName, AccountNameListActivity.class, REQUEST_CODE_DEAL_ACCOUNT_NAME);
                break;
            case R.id.et_contact_name:
                startNewActivity(titleContactName, ContactNameListActivity.class, REQUEST_CODE_DEAL_CONTACT_NAME);
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

                editTextClosingDate.setText(DateAndTimeUtil.dateToString(date));

            }
        }, mYear, mMonth, mDay);

        datePickerDialog.getDatePicker().setMinDate(DateAndTimeUtil.currentTimeStamp());
        datePickerDialog.show();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_deal, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.add_deal:

               /* if (setLeadData()) {
                    addLeadData();

                }*/
                if (checkValidation())
                    if (!editDeal) {
                        alertDialogManager.showAlertDialogMessage(this, "", "Are you sure to create a Deal", false, this);
                    } else {
                        alertDialogManager.showAlertDialogMessage(this, "", "Are you sure to update a Deal ", false, this);
                    }

                break;
        }

        return super.onOptionsItemSelected(item);


    }

    private void addDealData() {
        dealDataModel = new DealDataModel();
        if (editDeal) {
            dealDataModel.setDealId(dealId);
        } else {
            createdTime = DateAndTimeUtil.currentTimeStamp();
            dealId = System.currentTimeMillis() - 1000;
        }
        dealDataModel.setDealId(dealId);
        dealDataModel.setDealOwner(editTextDealOwner.getText().toString().trim());
        dealDataModel.setAccountName(editTextAccountName.getText().toString().trim());
        dealDataModel.setAmount(checkIntegerIsNull(editTextDealAmount.getText().toString().trim()));
        dealDataModel.setDealName(editTextDealName.getText().toString().trim());
        dealDataModel.setClosingDate(editTextClosingDate.getText().toString().trim());
        dealDataModel.setAccountName(editTextAccountName.getText().toString().trim());
        dealDataModel.setStage(editTextDealStage.getText().toString().trim());
        dealDataModel.setType(checkSelectListItem(editTextDealType.getText().toString().trim()));
        dealDataModel.setAccountId(accountId);
        dealDataModel.setContactId(contactId);
        dealDataModel.setProbability(checkIntegerIsNull(editTextDealProbability.getText().toString().trim()));
        dealDataModel.setNextStep(editTextNextStep.getText().toString().trim());
        dealDataModel.setExpectedRevenue(editTextDealExRevenue.getText().toString().trim());
        dealDataModel.setLeadSource(checkSelectListItem(editTextLeadSource.getText().toString().trim()));
        dealDataModel.setCampaignSource(editTextDealCampaignSource.getText().toString().trim());
        dealDataModel.setContactName(editTextContactName.getText().toString().trim());
        dealDataModel.setDescription(editTextDealDescription.getText().toString().trim());
        dealDataModel.setModifiedBy(sessionManager.getUserKeyId());
        dealDataModel.setCreatedBy(createdBy);
        dealDataModel.setExpectedRevenue(editTextDealExRevenue.getText().toString().trim());
        dealDataModel.setModifiedTime(DateAndTimeUtil.currentTimeStamp());
        dealDataModel.setCreatedTime(createdTime);

        if (editDeal) {

            if (!isSync) {

                if (checkConnection()) {
                    addDealDataToServer(dealDataModel);
                } else {
                    dataBaseAdapter.updateDealByID(dealDataModel, dealId);
                    //alertDialogManager.showAlertDialogMessage1(this, "", "Deal is Updated ", false, this);
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();

                }


            } else {

                if (checkConnection()) {
                    addDealDataToServer(dealDataModel);
                } else {
                    Toast.makeText(this, "Please check your Internet connection ", Toast.LENGTH_SHORT).show();
                }
            }
        } else {

            if (checkConnection()) {
                addDealDataToServer(dealDataModel);
            } else {
                dealDataModel.setSync(false);
                dataBaseAdapter.addDeal(dealDataModel);
               // alertDialogManager.showAlertDialogMessage1(AddDealActivity.this, "", "Deal is created ", false, AddDealActivity.this);


                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();

            }
        }


    }

    private void addDealDataToServer(final DealDataModel dealDataModel) {
        // DialogUtitlity.showLoading(this);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        final Call<String> response = apiService.addDeal(dealDataModel,sessionManager.getUserKeyId());
        response.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //          DialogUtitlity.hideLoading();
                int statusCode = response.code();
                Log.e("addDeal", String.valueOf(statusCode));
                switch (statusCode) {
                    case 201:
                        dealDataModel.setSync(true);
                        break;
                    case 400:
                        dealDataModel.setSync(false);
                        break;
                }

                if (!editDeal) {
                    dataBaseAdapter.addDeal(dealDataModel);
                    alertDialogManager.showAlertDialogMessage1(AddDealActivity.this, "", "Deal is created ", false, AddDealActivity.this);
                } else {
                    dealDataModel.setSync(true);
                    int result = dataBaseAdapter.updateDealByID(dealDataModel, dealId);
                    alertDialogManager.showAlertDialogMessage1(AddDealActivity.this, "", "Deal is updated ", false, AddDealActivity.this);
                }

            }


            @Override
            public void onFailure(Call<String> call, Throwable t) {
                DialogUtitlity.hideLoading();
                Log.e("addDeal", "fail");
              /*  Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();*/

            }
        });
    }


    private void updateDeal(){
        if (!editDeal) {
            dataBaseAdapter.addDeal(dealDataModel);
           // alertDialogManager.showAlertDialogMessage1(AddDealActivity.this, "", "Deal is created ", false, AddDealActivity.this);
        } else {
            dealDataModel.setSync(true);
            int result = dataBaseAdapter.updateDealByID(dealDataModel, dealId);
            //alertDialogManager.showAlertDialogMessage1(AddDealActivity.this, "", "Deal is updated ", false, AddDealActivity.this);
        }

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();

    }


    private long checkIntegerIsNull(String value) {
        return value.isEmpty() ? 0 : Long.parseLong(value);
    }




    private boolean checkValidation() {

        if(textViewError!=null){
            textViewError.setVisibility(View.GONE);
        }

        if (editTextDealName.getText().toString().trim().isEmpty()) {
            showErrorMsg(textViewErrorDealName,emptyDealNameErrorMsg,editTextDealName);
            return false;
        }
        /*if (!EmployeeValidationUtil.validateName(editTextDealName.getText().toString().trim(),3)) {
            showErrorMsg(textViewErrorDealName,getResources().getString(R.string.err_msg_deal_name_char),editTextDealName);
            return false;
        }*/


        if (editTextClosingDate.getText().toString().trim().isEmpty()) {
             showErrorMsg(textViewErrorDealClosingDate,emptyClosingDateErrorMsg,editTextClosingDate);
            return false;
        }
        if (editTextAccountName.getText().toString().trim().isEmpty()) {
            showErrorMsg(textViewErrorDealAccountName,emptyAccountNameErrorMsg,editTextAccountName);
            return false;
        }

        if (editTextDealStage.getText().toString().trim().isEmpty() || editTextDealStage.getText().toString().trim().equals("-None-")) {
            showErrorMsg(textViewErrorDealStage,emptyStageErrorMsg,editTextDealStage);
            return false;
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



    private void startSmartView() {
        if (textViewSmartView.getText().toString().equals(showSmartView)) {
            textViewSmartView.setText(showAllFields);
            linearLayoutDealOwner.setVisibility(View.GONE);
            linearLayoutLeadSource.setVisibility(View.GONE);
            linearLayoutDescriptionInformation.setVisibility(View.GONE);
            linearLayoutDealType.setVisibility(View.GONE);
            linearLayoutContactName.setVisibility(View.GONE);
            linearLayoutDealProbability.setVisibility(View.GONE);
            linearLayoutDealCampaignSource.setVisibility(View.GONE);
            linearLayoutDealNextStep.setVisibility(View.GONE);

        } else {
            textViewSmartView.setText(showSmartView);
            linearLayoutDealOwner.setVisibility(View.VISIBLE);
            linearLayoutLeadSource.setVisibility(View.VISIBLE);
            linearLayoutDescriptionInformation.setVisibility(View.VISIBLE);
            linearLayoutDealType.setVisibility(View.VISIBLE);
            linearLayoutContactName.setVisibility(View.VISIBLE);
            linearLayoutDealProbability.setVisibility(View.VISIBLE);
            linearLayoutDealCampaignSource.setVisibility(View.VISIBLE);
            linearLayoutDealNextStep.setVisibility(View.VISIBLE);
        }
    }


    private void startNewActivity(String title, String[] itemList, int requestCode) {

        intent = new Intent(AddDealActivity.this, CustomListActivity.class);
        intent.putExtra(EmployConstantUtil.ITEM_LIST, itemList);
        intent.putExtra(EmployConstantUtil.TITLE, title);
        startActivityForResult(intent, requestCode);

    }

    private void startNewActivity(String title, Class<?> cls, int requestCode) {
        intent = new Intent(AddDealActivity.this, cls);
        startActivityForResult(intent, requestCode);
    }

    private boolean isLead = false;
    private long leadId;
    private String titleLeads,titleContact;
    String selectedItem ;
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            selectedItem = data.getStringExtra(EmployConstantUtil.SELECTED_ITEM);
            accountId = data.getLongExtra(EmployConstantUtil.KEY_ACCOUNT_ID, 0);
            contactId = data.getLongExtra(EmployConstantUtil.KEY_CONTACT_ID, 0);
        }

        if(resultCode ==RESULT_OK){

        switch (requestCode) {
            case REQUEST_CODE_DEAL_ACCOUNT_NAME:
                editTextAccountName.setText(selectedItem);

                break;
            case REQUEST_CODE_DEAL_CONTACT_NAME:
                editTextContactName.setText(selectedItem);
                isLead = data.getBooleanExtra(EmployConstantUtil.KEY_IS_LEAD, false);
                if (isLead) {
                    leadId = data.getLongExtra(EmployConstantUtil.KEY_CONTACT_ID, 0);
                    textViewLabelContact.setText(titleLeads);

                } else {
                    contactId = data.getLongExtra(EmployConstantUtil.KEY_CONTACT_ID, 0);
                    textViewLabelContact.setText(titleContact);

                }
                break;
            case REQUEST_CODE_DEAL_OWNER:
                editTextDealOwner.setText(selectedItem);
                break;
            case REQUEST_CODE_DEAL_STAGE:
                editTextDealStage.setText(selectedItem);
                break;
            case REQUEST_CODE_DEAL_TYPE:
                editTextDealType.setText(selectedItem);
                break;
            case REQUEST_CODE_DEAL_LEAD_SOURCE:
                editTextLeadSource.setText(selectedItem);
                break;


        }
        }

    }


    // Method to manually check connection status
    private boolean checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        if (!isConnected) {

            return false;
        }

        return true;
    }


    @Override
    public void onResponse(Call<List<DealStage>> call, Response<List<DealStage>> response) {
        //DialogUtitlity.hideLoading();
        int statusCode = response.code();
        if (statusCode == 200) {
            if (response.body() instanceof List) {
                List<DealStage> dealStageList = response.body();
                if (!dealStageList.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    for (DealStage dealStage : dealStageList) {
                        sb.append(dealStage.getStage()).append(",");
                    }

                    sharedPrefUtil.setDealStage(sb.toString());
                    itemList = sharedPrefUtil.getDealStage().split(",");
                } else {

                }

            }
        }

    }


    @Override
    public void onFailure(Call<List<DealStage>> call, Throwable t) {
        // DialogUtitlity.hideLoading();
        Log.e("addDeal", "fail");
        //AddLeadActivity have to chanage this when image is  added lead is fail

    }

    @Override
    public void alertDialogCallbackOk() {
        addDealData();

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

    @Override
    protected void onStop() {
        super.onStop();
        DialogUtitlity.hideLoading();
    }

    Pattern pattern;

    private void setProbability(){
        editTextDealProbability.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                if (editTextDealProbability.getText().toString().matches("^0") || editTextDealProbability.getText().toString().equals(".")) {
                    editTextDealProbability.setText("");
                }

                Log.e("Probability", charSequence.toString());
               /* if(!strEnteredVal.equals("")){
                    int num=Integer.parseInt(strEnteredVal);
                    if(num<=100){
                        editTextDealProbability.setText(""+num);
                    }else{
                        editTextDealProbability.setText("100");
                    }
                }*/
            }

            @Override
            public void afterTextChanged(Editable s) {

                try {
                    Log.d("Percentage", "input: " + s);
                    if (Integer.parseInt(s.toString()) > 100)
                        s.replace(0, s.length(), "100");
                } catch (NumberFormatException nfe) {

                }
            }




        });

    }


    }
