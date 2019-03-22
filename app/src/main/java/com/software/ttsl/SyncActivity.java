package com.software.ttsl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.software.ttsl.Request.AccountDataModel;
import com.software.ttsl.Request.CallDataModel;
import com.software.ttsl.Request.DealDataModel;
import com.software.ttsl.Request.EventDataModel;
import com.software.ttsl.Request.LeadDataModel;
import com.software.ttsl.Request.SyncAllDataModel;
import com.software.ttsl.Request.TaskDataModel;
import com.software.ttsl.Response.ImageResponse;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.ConnectivityReceiver;
import com.software.ttsl.Utils.DateAndTimeUtil;
import com.software.ttsl.Utils.EmployConstantUtil;
import com.software.ttsl.model.AddContactData;
import com.software.ttsl.model.CustomerChallengeModel;
import com.software.ttsl.model.SalesBudgetModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String LEAD_SYNC = "LEAD";
    public static final String ACCOUNT_SYNC = "ACCOUNT";
    public static final String CONTACT_SYNC = "CONTACT";
    public static final String TASK_SYNC = "TASK";
    public static final String EVENT_SYNC = "EVENT";
    public static final String DEAL_SYNC = "DEAL";
    public static final String CALL_SYNC = "CALL";
    public static final String CUSTOMER_SYNC = "CUSTOMER";
    public static final String SALES_SYNC = "SALES";
    private static  final String SYNC_ALL_MODULE = "SYNCALL";

    public static final String LEAD_SYNC_ALL = "LEAD_ALL";
    public static final String ACCOUNT_SYNC_ALL = "ACCOUNT_ALL";
    public static final String CONTACT_SYNC_ALL = "CONTACT_ALL";
    public static final String TASK_SYNC_ALL = "TASK_ALL";
    public static final String EVENT_SYNC_ALL = "EVENT_ALL";
    public static final String CALL_SYNC_ALL = "CALL_ALL";
    public static final String IMAGE_SYNC ="IMAGE";
    public static final String CUSTOMER_SYNC_ALL = "CUSTOMER_ALL";
    public static final String SALES_SYNC_ALL = "SALES_ALL";
    public String SYNC_ALL="";

    @BindView(R.id.toolbar_sync)
    Toolbar toolbar_sync;
    @BindView(R.id.leads)
    TextView leads;
    @BindView(R.id.account)
    TextView account;
    @BindView(R.id.contacts)
    TextView contacts;
    @BindView(R.id.task)
    TextView task;
    @BindView(R.id.event)
    TextView event;
    @BindView(R.id.deals)
    TextView deals;
    @BindView(R.id.call)
    TextView call;
    @BindView(R.id.customers)
    TextView customers;
    @BindView(R.id.sales)
    TextView sales;
    @BindView(R.id.leadsImage)
    ImageView leadsImage;
    @BindView(R.id.accountImage)
    ImageView accountImage;
    @BindView(R.id.contactsImage)
    ImageView contactsImage;
    @BindView(R.id.taskImage)
    ImageView taskImage;
    @BindView(R.id.eventImage)
    ImageView eventImage;
    @BindView(R.id.dealsImage)
    ImageView dealsImage;
    @BindView(R.id.callImage)
    ImageView callImage;
    @BindView(R.id.customerImage)
    ImageView customerImage;
    @BindView(R.id.salesImage)
    ImageView salesImage;

    @BindView(R.id.leads_container)
    LinearLayout leads_container;
    @BindView(R.id.account_container)
    LinearLayout account_container;
    @BindView(R.id.contacts_container)
    LinearLayout contacts_container;
    @BindView(R.id.task_container)
    LinearLayout task_container;
    @BindView(R.id.event_container)
    LinearLayout event_container;
    @BindView(R.id.deals_container)
    LinearLayout deals_container;
    @BindView(R.id.call_container)
    LinearLayout call_container;
    @BindView(R.id.customer_container)
    LinearLayout customer_container;
    @BindView(R.id.sales_container)
    LinearLayout sales_container;
    LinearLayout mLinearLayout = null;


    @BindView(R.id.syncAllImage)
    ImageView imageViewSyncAll;

    private String SYNC_MODULE = "";

    private String LEAD = "LEAD";
    private String ACCOUNT = "ACCOUNT";
    private String CONTACT = "CONTACT";
    private String TASK = "TASK";
    private String EVENT = "EVENT";
    private String DEAL = "DEAL";
    private String CALL = "CALL";
    private String CUSTOMER = "CUSTOMER";
    private String SALES = "SALES";


    private SyncAllDataModel syncAllDataModel;
    private DataBaseAdapter dataBaseAdapter;

    private List<LeadDataModel> leadDataModelList = new ArrayList<LeadDataModel>();
    private List<AccountDataModel> accountDataModelList = new ArrayList<AccountDataModel>();
    private List<AddContactData> contactDataModelList = new ArrayList<AddContactData>();
    private List<TaskDataModel> taskDataModelList = new ArrayList<TaskDataModel>();
    private List<EventDataModel> eventDataModelList = new ArrayList<EventDataModel>();
    private List<DealDataModel> dealDataModelList = new ArrayList<DealDataModel>();
    private List<CallDataModel> callDataModelList = new ArrayList<CallDataModel>();
    private List<CustomerChallengeModel> customerChallengeModelList = new ArrayList<CustomerChallengeModel>();
    private List<SalesBudgetModel> salesBudgetModelList = new ArrayList<SalesBudgetModel>();
    private List<ImageResponse> imageResponsesList = new ArrayList<>();

    private long leadID;
    private long accountID;
    private long contactID;
    private long taskID;
    private long eventID;
    private long dealID;
    private long callID;
    private long customerID;
    private long salesID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar_sync);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        dataBaseAdapter = new DataBaseAdapter(this);
        syncAllDataModel = new SyncAllDataModel();
        updateData();

    }

    @Override
    protected void onResume() {
        super.onResume();
        leadDataModelList.clear();
        accountDataModelList.clear();
        contactDataModelList.clear();
        taskDataModelList.clear();
        eventDataModelList.clear();
        dealDataModelList.clear();
        callDataModelList.clear();
        customerChallengeModelList.clear();
        salesBudgetModelList.clear();

        updateData();

    }

    private void updateData() {
        setClickListener();
        setUnSyncLead();
        setUnSyncAccount();
        setUnSyncContact();
        setUnSyncTask();
        setUnSyncEvent();
        setUnSyncDeal();
        setUnSyncCall();
        setUnSyncCustomer();
        setUnSyncSales();
        setUnSyncLeadImages();
    }

    private void setUnSyncLeadImages(){
        for (LeadDataModel leadDataModel :leadDataModelList){
            imageResponsesList.add(leadDataModel.getImageResponse());
        }
    }

    private void setUnSyncLead() {
        leads_container.removeAllViews();
        leadDataModelList.addAll(dataBaseAdapter.getAllUnSyncLead());
        //syncAllDataModel.setLead(leadDataModelList);
        for (LeadDataModel leadDataModel : leadDataModelList) {
            final View view = getLayoutInflater().inflate(R.layout.leads_sync_row, mLinearLayout, true);
            TextView textViewLeadCompanyName = view.findViewById(R.id.leads_company);
            TextView textViewFirstLastName = view.findViewById(R.id.leads_FirstLast);
            TextView leadCreatedDate = view.findViewById(R.id.leadCreatedDate);
            textViewLeadCompanyName.setText(leadDataModel.getCompany());
            textViewFirstLastName.setText((leadDataModel.getFirstName() + leadDataModel.getLastName()).trim());
            leadCreatedDate.setText(DateAndTimeUtil.longToDate(leadDataModel.getCreatedDate()));

            leads_container.addView(view);
            view.setTag(leadDataModel.getLeadId());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    leadID = (long) view.getTag();
                    Log.d("test", "ID: " + view.getTag());
                    Intent leadIntent = new Intent(SyncActivity.this, LeadDetailActivity.class);
                    leadIntent.putExtra(EmployConstantUtil.KEY_LEAD_ID, leadID);
                    Log.d("test", "TAGView: " + leadID);
                    startActivityForResult(leadIntent, 1000);
                }
            });
        }
    }

    private void setUnSyncAccount() {
        account_container.removeAllViews();
        accountDataModelList.addAll(dataBaseAdapter.getAllUnSyncAccounts());
        //syncAllDataModel.setAccount(accountDataModelList);
        for (AccountDataModel accountDataModel : accountDataModelList) {
            final View view = getLayoutInflater().inflate(R.layout.account_sync_row, mLinearLayout, true);
            TextView textViewAccountName = view.findViewById(R.id.account_name);
            TextView accountCreatedDate = view.findViewById(R.id.accountCreatedDate);
            textViewAccountName.setText(accountDataModel.getAccountName().substring(0, 1).toUpperCase());
            accountCreatedDate.setText(DateAndTimeUtil.longToDate(accountDataModel.getCreatedTime()));

            account_container.addView(view);
            view.setTag(accountDataModel.getId());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    accountID = (long) view.getTag();
                    Log.d("test", "ID: " + view.getTag());
                    Intent accountIntent = new Intent(SyncActivity.this, AccountDetailsActivity.class);
                    accountIntent.putExtra(EmployConstantUtil.KEY_ACCOUNT_ID, accountID);
                    startActivityForResult(accountIntent, 1000);
                }
            });
        }
    }

    private void setUnSyncContact() {
        contacts_container.removeAllViews();
        contactDataModelList.addAll(dataBaseAdapter.getAllUnSyncContact());
        //syncAllDataModel.setContact(contactDataModelList);
        for (AddContactData addContactData : contactDataModelList) {
            final View view = getLayoutInflater().inflate(R.layout.contacts_sync_row, mLinearLayout, true);
            TextView textViewContactAccountName = view.findViewById(R.id.textViewContactAccountName);
            TextView textViewContactName = view.findViewById(R.id.textViewContactName);
            TextView contactCreatedDate = view.findViewById(R.id.ContactCreatedDate);
            textViewContactAccountName.setText(DateAndTimeUtil.firstLatterUpper((addContactData.getFirstName()) + " " + DateAndTimeUtil.firstLatterUpper(addContactData.getLastName())).trim());
            textViewContactName.setText(addContactData.getAccountName());
            contactCreatedDate.setText(DateAndTimeUtil.longToDate(addContactData.getCreatedDate()));

            contacts_container.addView(view);
            view.setTag(addContactData.getContactId());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contactID = (long) view.getTag();
                    Log.d("test", "ID: " + view.getTag());
                    Intent contactIntent = new Intent(SyncActivity.this, ContactDetailActivity.class);
                    contactIntent.putExtra(EmployConstantUtil.KEY_CONTACT_ID, contactID);
                    startActivityForResult(contactIntent, 1000);
                }
            });
        }
    }

    private void setUnSyncTask() {
        task_container.removeAllViews();
        taskDataModelList.addAll(dataBaseAdapter.getAllUnSyncTask());
        //syncAllDataModel.setTask(taskDataModelList);
        for (TaskDataModel taskDataModel : taskDataModelList) {
            final View view = getLayoutInflater().inflate(R.layout.task_sync_row, mLinearLayout, true);
            TextView taskSubject = view.findViewById(R.id.taskSubject);
            TextView taskCreatedDate = view.findViewById(R.id.taskCreatedDate);
            taskSubject.setText(taskDataModel.getTaskSubject());
            taskCreatedDate.setText(DateAndTimeUtil.longToDate(taskDataModel.getTaskCreatedTime()));

            task_container.addView(view);
            view.setTag(taskDataModel.getTaskId());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    taskID = (long) view.getTag();
                    Log.d("test", "ID: " + view.getTag());
                    Intent taskIntent = new Intent(SyncActivity.this, TaskDetailActivity.class);
                    taskIntent.putExtra(EmployConstantUtil.KEY_TASK_ID, taskID);
                    startActivityForResult(taskIntent, 1000);
                }
            });
        }
    }

    private void setUnSyncEvent() {
        event_container.removeAllViews();
        eventDataModelList.addAll(dataBaseAdapter.getAllUnSyncEvents());
        //syncAllDataModel.setEvent(eventDataModelList);
        for (EventDataModel eventDataModel : eventDataModelList) {
            final View view = getLayoutInflater().inflate(R.layout.event_sync_row, mLinearLayout, true);
            TextView textViewTitle = view.findViewById(R.id.textViewTitle);
            TextView event_sync = view.findViewById(R.id.event_sync);
            textViewTitle.setText(eventDataModel.getTitle());
            event_sync.setText(DateAndTimeUtil.longToDate(eventDataModel.getCreatedTime()));

            event_container.addView(view);
            view.setTag(eventDataModel.getEventId());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eventID = (long) view.getTag();
                    Log.d("test", "ID: " + view.getTag());
                    Intent eventIntent = new Intent(SyncActivity.this, EventDetailActivity.class);
                    eventIntent.putExtra(EmployConstantUtil.KEY_EVENT_ID, eventID);
                    startActivityForResult(eventIntent, 1000);
                }
            });
        }
    }

    private void setUnSyncDeal() {
        deals_container.removeAllViews();
        dealDataModelList.addAll(dataBaseAdapter.getAllUnSyncDeals());
        //syncAllDataModel.setDeal(dealDataModelList);
        for (DealDataModel dealDataModel : dealDataModelList) {

            final View view = getLayoutInflater().inflate(R.layout.deals_sync_row, mLinearLayout, true);
            TextView textViewDealName = view.findViewById(R.id.textViewDealName);
            TextView textViewDealDate = view.findViewById(R.id.textViewDealDate);
            textViewDealName.setText(dealDataModel.getDealName());
            textViewDealDate.setText(DateAndTimeUtil.longToDate(dealDataModel.getCreatedTime()));

            deals_container.addView(view);
            view.setTag(dealDataModel.getDealId());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dealID = (long) view.getTag();
                    Log.d("test", "ID: " + view.getTag());
                    Intent dealIntent = new Intent(SyncActivity.this, DealDetailsActivity.class);
                    dealIntent.putExtra(EmployConstantUtil.KEY_DEAL_ID, dealID);
                    startActivityForResult(dealIntent, 1000);
                }
            });
        }
    }

    private void setUnSyncCall() {
        call_container.removeAllViews();
        //syncAllDataModel.setCall(callDataModelList);
        callDataModelList.addAll(dataBaseAdapter.getAllUnSyncCall());
        for (CallDataModel callDataModel : callDataModelList) {
            final View view = getLayoutInflater().inflate(R.layout.call_sync_row, mLinearLayout, true);
            TextView textViewCallSubject = view.findViewById(R.id.textViewCallSubject);
            TextView textViewCallTime = view.findViewById(R.id.call_sync_date);
            textViewCallSubject.setText(callDataModel.getSubject());
            textViewCallTime.setText(DateAndTimeUtil.longToDate(callDataModel.getCallStartDate()));

            call_container.addView(view);
            view.setTag(callDataModel.getCallId());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callID = (long) view.getTag();
                    Log.d("test", "ID: " + view.getTag());
                    Intent callIntent = new Intent(SyncActivity.this, CallDetailActivity.class);
                    callIntent.putExtra(EmployConstantUtil.KEY_CALL_ID, callID);
                    startActivityForResult(callIntent, 1000);
                }
            });
        }
    }

    private void setUnSyncCustomer() {
        customer_container.removeAllViews();
        //syncAllDataModel.setCustomer(customerChallengeModelList);
        customerChallengeModelList.addAll(dataBaseAdapter.getAllUnSyncCustomerChallenges());
        for (CustomerChallengeModel customerChallengeModel : customerChallengeModelList) {
            final View view = getLayoutInflater().inflate(R.layout.customer_sync_row, mLinearLayout, true);
            TextView textViewCustomerName = view.findViewById(R.id.textViewCustomerName);
            TextView CustomerCreatedTime = view.findViewById(R.id.CustomerCreatedTime);
            textViewCustomerName.setText(customerChallengeModel.getCustomer());
            CustomerCreatedTime.setText(DateAndTimeUtil.longToDate(customerChallengeModel.getCreatedTime()));


            customer_container.addView(view);
            view.setTag(customerChallengeModel.getCustomerId());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customerID = (long) view.getTag();
                    Log.d("test", "ID: " + view.getTag());
                    Intent customerIntent = new Intent(SyncActivity.this, CustomerChallengesDetailActivity.class);
                    customerIntent.putExtra(EmployConstantUtil.KEY_CUSTOMER_CHALLENGE_ID, customerID);
                    startActivityForResult(customerIntent, 1000);
                }
            });
        }
    }

    private void setUnSyncSales() {
        sales_container.removeAllViews();
        //syncAllDataModel.setSales(salesBudgetModelList);
        salesBudgetModelList.addAll(dataBaseAdapter.getAllUnSyncSalesBudget());
        for (SalesBudgetModel salesBudgetModel : salesBudgetModelList) {
            final View view = getLayoutInflater().inflate(R.layout.sales_sync_row, mLinearLayout, true);
            TextView textViewSalesYear = view.findViewById(R.id.textViewSalesYear);
            TextView SalesCreatedTime = view.findViewById(R.id.SalesCreatedTime);
            textViewSalesYear.setText(salesBudgetModel.getSalesYear());
            SalesCreatedTime.setText(DateAndTimeUtil.longToDate(salesBudgetModel.getSalesCreatedTime()));

            sales_container.addView(view);
            view.setTag(salesBudgetModel.getSalesId());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    salesID = (long) view.getTag();
                    Log.d("test", "ID: " + view.getTag());
                    Intent salesIntent = new Intent(SyncActivity.this, SalesDetailActivity.class);
                    salesIntent.putExtra(EmployConstantUtil.KEY_SALES_BUDGET_ID, salesID);
                    startActivityForResult(salesIntent, 1000);
                }
            });
        }
    }

    private void setClickListener() {
        leadsImage.setOnClickListener(this);
        accountImage.setOnClickListener(this);
        contactsImage.setOnClickListener(this);
        taskImage.setOnClickListener(this);
        eventImage.setOnClickListener(this);
        dealsImage.setOnClickListener(this);
        callImage.setOnClickListener(this);
        customerImage.setOnClickListener(this);
        salesImage.setOnClickListener(this);
        imageViewSyncAll.setOnClickListener(this);
    }

    private void syncDataToServer() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        final Call<String> response = apiService.syncDetail(syncAllDataModel);
        response.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                int statusCode = response.code();
                switch (statusCode) {
                    case 201:
                        dataBaseAdapter.closeDataBase();
                        switch (SYNC_MODULE) {
                            case LEAD_SYNC:
                                leads_container.removeAllViews();
                                dataBaseAdapter.syncAllData(syncAllDataModel, LEAD);
                                SYNC_MODULE = IMAGE_SYNC;
                                syncAllDataModel = new SyncAllDataModel();
                                syncAllDataModel.setImageResponses(imageResponsesList);
                                syncDataToServer();
                                break;
                            case ACCOUNT_SYNC:
                                account_container.removeAllViews();
                                dataBaseAdapter.syncAllData(syncAllDataModel, ACCOUNT);
                                break;
                            case CONTACT_SYNC:
                                contacts_container.removeAllViews();
                                dataBaseAdapter.syncAllData(syncAllDataModel, CONTACT);
                                break;
                            case TASK_SYNC:
                                task_container.removeAllViews();
                                dataBaseAdapter.syncAllData(syncAllDataModel, TASK);
                                break;
                            case EVENT_SYNC:
                                event_container.removeAllViews();
                                dataBaseAdapter.syncAllData(syncAllDataModel, EVENT);
                                break;
                            case DEAL_SYNC:
                                deals_container.removeAllViews();
                                dataBaseAdapter.syncAllData(syncAllDataModel, DEAL);
                                break;
                            case CALL_SYNC:
                                call_container.removeAllViews();
                                dataBaseAdapter.syncAllData(syncAllDataModel, CALL);
                                break;
                            case CUSTOMER_SYNC:
                                customer_container.removeAllViews();
                                dataBaseAdapter.syncAllData(syncAllDataModel, CUSTOMER);
                                break;
                            case SALES_SYNC:
                                sales_container.removeAllViews();
                                dataBaseAdapter.syncAllData(syncAllDataModel, SALES);
                                break;
                            case SYNC_ALL_MODULE:
                                switch (SYNC_ALL){
                                    case LEAD_SYNC_ALL:
                                        leads_container.removeAllViews();
                                        dataBaseAdapter.syncAllData(syncAllDataModel, LEAD);
                                        setModuleList(ACCOUNT_SYNC);
                                        syncDataToServer();
                                        break;
                                    case ACCOUNT_SYNC_ALL:
                                        account_container.removeAllViews();
                                        dataBaseAdapter.syncAllData(syncAllDataModel, ACCOUNT);
                                        setModuleList(CONTACT_SYNC);
                                        syncDataToServer();
                                        break;
                                    case CONTACT_SYNC_ALL:

                                        contacts_container.removeAllViews();
                                        dataBaseAdapter.syncAllData(syncAllDataModel, CONTACT);
                                        setModuleList(ACCOUNT_SYNC);
                                        syncDataToServer();
                                        break;




                                }

                        }
                        break;
                    case 400:
                        break;
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

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

    @Override
    public void onClick(View v) {
        int id = v.getId();

        syncAllDataModel = new SyncAllDataModel();

        switch (id) {
            case R.id.leadsImage:

                SYNC_MODULE = LEAD_SYNC;
                if (checkConnection()) {
                    syncAllDataModel.setLead(leadDataModelList);
                    setModuleList(LEAD_SYNC);
                    syncDataToServer();
                    Toast.makeText(SyncActivity.this, "Syncing", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SyncActivity.this, "Please check your Internet connection", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.accountImage:
                SYNC_MODULE = ACCOUNT_SYNC;
                if (checkConnection()) {
                    syncAllDataModel.setAccount(accountDataModelList);
                    setModuleList(ACCOUNT_SYNC);
                    syncDataToServer();
                    Toast.makeText(SyncActivity.this, "Syncing", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SyncActivity.this, "Please check your Internet connection", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.contactsImage:
                SYNC_MODULE = CONTACT_SYNC;
                if (checkConnection()) {
                    setModuleList(CONTACT_SYNC);
                    syncAllDataModel.setContact(contactDataModelList);
                    syncDataToServer();
                    Toast.makeText(SyncActivity.this, "Syncing", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SyncActivity.this, "Please check your Internet connection", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.taskImage:
                SYNC_MODULE = TASK_SYNC;
                if (checkConnection()) {
                    setModuleList(TASK_SYNC);
                    //syncAllDataModel.setTask(taskDataModelList);
                    syncDataToServer();
                    Toast.makeText(SyncActivity.this, "Syncing", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SyncActivity.this, "Please check your Internet connection", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.eventImage:
                SYNC_MODULE = EVENT_SYNC;
                if (checkConnection()) {
                    setModuleList(EVENT_SYNC);
                    //syncAllDataModel.setEvent(eventDataModelList);
                    syncDataToServer();
                    Toast.makeText(SyncActivity.this, "Syncing", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SyncActivity.this, "Please check your Internet connection", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.dealsImage:
                SYNC_MODULE = DEAL_SYNC;
                if (checkConnection()) {
                    setModuleList(DEAL_SYNC);
                    //syncAllDataModel.setDeal(dealDataModelList);
                    syncDataToServer();
                    Toast.makeText(SyncActivity.this, "Syncing", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SyncActivity.this, "Please check your Internet connection", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.callImage:
                SYNC_MODULE = CALL_SYNC;
                if (checkConnection()) {
                    //syncAllDataModel.setCall(callDataModelList);
                    setModuleList(CALL_SYNC);
                    syncDataToServer();
                    Toast.makeText(SyncActivity.this, "Syncing", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SyncActivity.this, "Please check your Internet connection", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.customerImage:
                SYNC_MODULE = CUSTOMER_SYNC;
                if (checkConnection()) {
                    syncAllDataModel.setCustomer(customerChallengeModelList);
                    syncDataToServer();
                    Toast.makeText(SyncActivity.this, "Syncing", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SyncActivity.this, "Please check your Internet connection", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.salesImage:
                SYNC_MODULE = SALES_SYNC;
                if (checkConnection()) {
                    syncAllDataModel.setSales(salesBudgetModelList);
                    syncDataToServer();
                    Toast.makeText(SyncActivity.this, "Syncing", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SyncActivity.this, "Please check your Internet connection", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.syncAllImage:
                SYNC_MODULE = SYNC_ALL_MODULE;
                if (checkConnection()) {
                    //syncAllDataModel.setLead(leadDataModelList);
                    setModuleList(LEAD_SYNC);
                    syncDataToServer();
                    Toast.makeText(SyncActivity.this, "Syncing", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SyncActivity.this, "Please check your Internet connection", Toast.LENGTH_SHORT).show();
                }
        }


    }

    private void setModuleList(String module) {
        switch (module) {
            case LEAD_SYNC:
                SYNC_ALL = LEAD_SYNC_ALL;
                syncAllDataModel.setLead(leadDataModelList);
                break;
            case ACCOUNT_SYNC:
                SYNC_ALL = ACCOUNT_SYNC_ALL;
                syncAllDataModel.setAccount(accountDataModelList);
                break;
            case CONTACT_SYNC:
                SYNC_ALL = CONTACT_SYNC_ALL;
                syncAllDataModel.setContact(contactDataModelList);
                break;
            case DEAL_SYNC:
                SYNC_ALL = DEAL_SYNC;
                syncAllDataModel.setDeal(dealDataModelList);
                break;
            case CALL_SYNC:
                SYNC_ALL = CALL_SYNC_ALL;
                syncAllDataModel.setCall(callDataModelList);
                break;
            case EVENT_SYNC:
                SYNC_ALL =EVENT_SYNC_ALL;
                syncAllDataModel.setEvent(eventDataModelList);
                break;
            case TASK_SYNC:
                SYNC_ALL = TASK_SYNC_ALL;
                syncAllDataModel.setTask(taskDataModelList);
                break;

        }
    }
}
