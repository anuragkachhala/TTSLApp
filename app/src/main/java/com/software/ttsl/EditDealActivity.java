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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.software.ttsl.Interfacce.AlertDialogCallback;
import com.software.ttsl.Request.DealDataModel;
import com.software.ttsl.Response.DealStage;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.AlertDialogManager;
import com.software.ttsl.Utils.ConnectivityReceiver;
import com.software.ttsl.Utils.DialogUtitlity;
import com.software.ttsl.Utils.EmployConstantUtil;
import com.software.ttsl.Utils.SessionManager;
import com.software.ttsl.Utils.SharedPrefUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditDealActivity extends AppCompatActivity implements View.OnClickListener, AlertDialogCallback, Callback<List<DealStage>> {

    public static final String TAG = EditDealActivity.class.getName();

    private static final int REQUEST_CODE_DEAL_ACCOUNT_NAME = 1000;
    private static final int REQUEST_CODE_DEAL_CONTACT_NAME = 2000;
    private static final int REQUEST_CODE_DEAL_OWNER = 3000;
    private static final int REQUEST_CODE_DEAL_STAGE  = 4000;
    private static final int REQUEST_CODE_DEAL_TYPE = 5000;
    private static final int REQUEST_CODE_DEAL_LEAD_SOURCE = 6000;
    private static final int REQUEST_CODE_DEAL_CAMPAIGN_SOURCE= 7000;

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

    @BindView(R.id.tv_deal_name)
    TextView textViewDealName;

    @BindView(R.id.tv_deal_closing_date)
    TextView textViewDealClosingDate;

    @BindView(R.id.tv_account_name)
    TextView textViewDealAccountName;

    @BindView(R.id.tv_deal_stage)
    TextView textViewDealStage;
    Long createdTime;
    private String emptyAccountNameErrorMsg, titleDealType, emptyDealNameErrorMsg, emptyClosingDateErrorMsg, emptyStageErrorMsg, showSmartView, showAllFields, errorTitle, titleType, titleStage, titleDealOwner, titleAccountName, titleContactName, titleCampaignSource, titleLeadSource;
    private SharedPrefUtil sharedPrefUtil;
    private AlertDialogManager alertDialogManager;
    private DealDataModel dealDataModel;
    private DataBaseAdapter dataBaseAdapter;
    private SessionManager sessionManager;
    private Intent intent;
    private String itemList[];
    private String title;
    private String[] stagesList, leadSourceList, dealList;
    private int mYear, mMonth, mDay;
    private Long dealId;
    private String createdBy;
    private long accountID, contactId;
    private Boolean isSync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_deal);

        ButterKnife.bind(this);

        SessionManager.setContext(getApplicationContext());
        sessionManager = SessionManager.getInstance();
        Intent intent = getIntent();
        dealId = intent.getLongExtra(EmployConstantUtil.KEY_DEAL_ID, 0);
        Log.e(TAG, "edit account id " + dealId);

        alertDialogManager = new AlertDialogManager();
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SharedPrefUtil.setContext(getApplicationContext());
        dataBaseAdapter = new DataBaseAdapter(this);


        sharedPrefUtil = SharedPrefUtil.getInstance();

        getDealById();

        setClickListener();

        getStringResources();

        getDealState();


    }

    private void getDealById() {
        dealDataModel = dataBaseAdapter.getDealById(dealId);
        setAccountDetails(dealDataModel);
    }

    private void setAccountDetails(DealDataModel dealDataModel) {
        textViewDealName.setText(Html.fromHtml(getResources().getString(R.string.label_deal_name)));
        textViewDealAccountName.setText(Html.fromHtml(getResources().getString(R.string.label_deal_closing_date)));
        textViewDealClosingDate.setText(Html.fromHtml(getResources().getString(R.string.label_deal_account_name)));
        textViewDealStage.setText(Html.fromHtml(getResources().getString(R.string.label_deal_stage)));
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


    private void getDealState() {
        String item = sharedPrefUtil.getAccountType();
        if (item == null) {
            DialogUtitlity.showLoading(EditDealActivity.this);
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
                startNewActivity(titleDealOwner, new String[]{"Test"},REQUEST_CODE_DEAL_OWNER);
                break;
            case R.id.et_deal_stage:
                startNewActivity(titleStage, stagesList,REQUEST_CODE_DEAL_STAGE);
                break;
            case R.id.et_lead_source:
                startNewActivity(titleLeadSource, leadSourceList,REQUEST_CODE_DEAL_LEAD_SOURCE);
                break;
            case R.id.et_deal_type:
                startNewActivity(titleDealType, dealList,REQUEST_CODE_DEAL_TYPE);
                break;
            case R.id.et_deal_closing_date:
                openCalender();
                break;
            case R.id.et_deal_campaign_source:
                title = titleCampaignSource;
                startNewActivity(titleCampaignSource, new String[]{},REQUEST_CODE_DEAL_CAMPAIGN_SOURCE);
                break;

            case R.id.et_account_name:
                startNewActivity(titleAccountName, AccountNameListActivity.class,REQUEST_CODE_DEAL_ACCOUNT_NAME);
                break;
            case R.id.et_contact_name:
                startNewActivity(titleContactName, ContactNameListActivity.class,REQUEST_CODE_DEAL_CONTACT_NAME);
                break;


        }

    }


    private void startNewActivity(String title, Class<?> cls,int requestCode) {
        intent = new Intent(this, cls);
        startActivityForResult(intent, requestCode);
    }

  /*  private void openCalender() {
        editTextDealCampaignSource.setText("campaign");
        editTextAccountName.setText("myAccount");
        editTextClosingDate.setText("10/12/2018");
        editTextContactName.setText("my contact");

    }*/


    public void openCalender() {

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker dateView, int year, int monthOfYear, int dayOfMonth) {
                String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;

                editTextClosingDate.setText(date);
                editTextDealCampaignSource.setText("campaign");
                editTextAccountName.setText("myAccount");
                editTextContactName.setText("my contact");
            }
        }, mYear, mMonth, mDay);

        datePickerDialog.show();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_deal, menu);
        return true;
    }


    public long dateToLong(String date) {

        long milliseconds = 0;
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date d = f.parse(date);
            milliseconds = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return milliseconds;

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
                    addDealData();

                break;
        }

        return super.onOptionsItemSelected(item);


    }

    private void addDealData() {
        dealDataModel = new DealDataModel();
        dealDataModel.setDealOwner(editTextDealOwner.getText().toString().trim());
        dealDataModel.setAccountName(editTextAccountName.getText().toString().trim());
        dealDataModel.setAmount(checkIntegerIsNull(editTextDealAmount.getText().toString().trim()));
        dealDataModel.setDealName(editTextDealName.getText().toString().trim());
        dealDataModel.setClosingDate(editTextClosingDate.getText().toString().trim());
        dealDataModel.setAccountName(editTextAccountName.getText().toString().trim());
        dealDataModel.setStage(editTextDealStage.getText().toString().trim());
        dealDataModel.setType(checkSelectListItem(editTextDealType.getText().toString().trim()));
        dealDataModel.setAccountId(accountID);
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
        dealDataModel.setModifiedTime(currentTimeStamp());
        dealDataModel.setCreatedTime(createdTime);
        dealDataModel.setSync(isSync);
        dataBaseAdapter.openDataBase();

        if (!isSync) {

            if (checkConnection()) {
                updateDealDataToServer();
            } else {
                dataBaseAdapter.updateDealByID(dealDataModel, dealId);
                alertDialogManager.showAlertDialogMessage(this, "", "Deal is Updated ", false, this);
            }


        } else {

            if (checkConnection()) {
                updateDealDataToServer();
            } else {
                Toast.makeText(this, "Please check your Internet connection ", Toast.LENGTH_SHORT).show();
            }
        }


    }

    private void updateDealDataToServer() {
        DialogUtitlity.showLoading(EditDealActivity.this);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> listCall = apiInterface.addDeal(dealDataModel,"");
        Log.e("addDeal", "inside updateDealDataToServer");
        listCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                DialogUtitlity.hideLoading();
                Log.e("addDeal", "inside updateDealDataToServer");
                int statusCode = response.code();
                Log.e("addDeal", String.valueOf(statusCode));

                if (statusCode == 200) {
                    if (response.body() instanceof String) {
                        dealDataModel.setSync(true);
                    }
                } else if (statusCode == 201) {
                    dealDataModel.setSync(true);
                } else if (statusCode == 500) {
                    dealDataModel.setSync(isSync);
                }

                int result = dataBaseAdapter.updateDealByID(dealDataModel, dealId);
                alertDialogManager.showAlertDialogMessage(EditDealActivity.this, "", "Deal is updated ", false, EditDealActivity.this);

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private long checkIntegerIsNull(String value) {
        return value.isEmpty() ? 0 : Long.parseLong(value);
    }

    private String checkSelectListItem(String selectedItem) {

        return selectedItem.equals(EmployConstantUtil.NOTING_SELECTED_VALUE) ? "" : selectedItem;
    }

    private long currentTimeStamp() {
        return System.currentTimeMillis() - 1000;
    }

    private String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-dd-MM");
        Date now = new Date();
        String today = simpleDateFormat1.format(now);

        return today;
    }


    private boolean checkValidation() {

        if (editTextAccountName.getText().toString().trim().isEmpty()) {
            alertDialogManager.showAlertDialog(this, errorTitle, emptyAccountNameErrorMsg, false);
            return false;
        }
        if (editTextClosingDate.getText().toString().trim().isEmpty()) {
            alertDialogManager.showAlertDialog(this, errorTitle, emptyClosingDateErrorMsg, false);
            return false;
        }
        if (editTextDealName.getText().toString().trim().isEmpty()) {
            alertDialogManager.showAlertDialog(this, errorTitle, emptyDealNameErrorMsg, false);
            return false;
        }
        if (editTextDealStage.getText().toString().trim().isEmpty()) {
            alertDialogManager.showAlertDialog(this, errorTitle, emptyDealNameErrorMsg, false);
            return false;
        }
        if (editTextDealStage.getText().toString().trim().equals("-None-")) {
            alertDialogManager.showAlertDialog(this, errorTitle, emptyStageErrorMsg, false);
            return false;
        }
        return true;


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


    private void startNewActivity(String title, String[] itemList,int requestCode) {

        intent = new Intent(EditDealActivity.this, CustomListActivity.class);
        intent.putExtra(EmployConstantUtil.ITEM_LIST, itemList);
        intent.putExtra(EmployConstantUtil.TITLE, title);
        startActivityForResult(intent, requestCode);

    }

    private void startNewActivity(String title) {
        intent = new Intent(EditDealActivity.this, AccountNameListActivity.class);
        startActivityForResult(intent, 1000);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000) {
            if (resultCode == RESULT_OK) {
                String selectedItem = data.getStringExtra(EmployConstantUtil.SELECTED_ITEM);
                String title = data.getStringExtra(EmployConstantUtil.TITLE);
                if (title.equals(titleAccountName)) {
                    editTextAccountName.setText(selectedItem);
                    accountID = data.getLongExtra(EmployConstantUtil.KEY_ACCOUNT_ID, 0);

                } else if (title.equals(titleContactName)) {
                    editTextContactName.setText(selectedItem);
                    contactId = data.getLongExtra(EmployConstantUtil.KEY_CONTACT_ID, 0);
                } else if (title.equals(titleDealOwner)) {
                    editTextDealOwner.setText(selectedItem);
                } else if (title.equals(titleStage)) {
                    editTextDealStage.setText(selectedItem);

                } else if (title.equals(titleDealType)) {
                    editTextDealType.setText(selectedItem);
                } else if (title.equals(titleLeadSource)) {
                    editTextLeadSource.setText(selectedItem);
                }
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
        DialogUtitlity.hideLoading();
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
                    itemList = sharedPrefUtil.getAccountType().split(",");
                } else {

                }

            }
        }

    }


    @Override
    public void onFailure(Call<List<DealStage>> call, Throwable t) {
        DialogUtitlity.hideLoading();

    }

    @Override
    public void alertDialogCallbackOk() {
        startActivity(new Intent(EditDealActivity.this, DealListActivity.class));
        finish();
    }

    @Override
    public void alertDialogCallbackCancel() {

    }

}
