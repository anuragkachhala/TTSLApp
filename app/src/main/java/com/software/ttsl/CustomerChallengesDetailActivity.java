package com.software.ttsl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
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
import com.software.ttsl.Utils.SessionManager;
import com.software.ttsl.model.CustomerChallengeModel;


import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

public class CustomerChallengesDetailActivity extends AppCompatActivity implements AlertDialogCallback, Callback<String> {


    public static final String TAG = SalesDetailActivity.class.getName();
    private static final int REQUEST_EDIT_CUSTOMER_CHALLENGE =1000;


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_add_customer)
    TextView textViewAddCustomer;

    @BindView(R.id.tv_customer_contact)
    TextView textViewCustomerContact;

    @BindView(R.id.tv_customer_log_date)
    TextView textViewCustomerLogDate;

    @BindView(R.id.tv_customer_priority)
    TextView textViewCustomerPriority;

    @BindView(R.id.tv_customer_origin)
    TextView textViewCustomerOrigin;

    @BindView(R.id.tv_customer_reason)
    TextView textViewCustomerReason;

    @BindView(R.id.tv_customer_due_date)
    TextView textViewCustomerDueDate;

    @BindView(R.id.tv_closed_on)
    TextView textViewClosedOn;

    @BindView(R.id.tv_customer_incharge)
    TextView textViewCustomerIncharge;

    @BindView(R.id.tv_cc_mail_id)
    TextView textViewCCMailId;

    @BindView(R.id.tv_customer_status)
    TextView textViewCustomerStatus;

    @BindView(R.id.tv_customer_subject)
    TextView textViewCustomerSubject;

    @BindView(R.id.tv_customer_note)
    TextView textViewCustomerNote;

    @BindView(R.id.tv_customer_description)
    TextView textViewCustomerDescription;

    @BindView(R.id.tv_internal_note)
    TextView textViewInternalNote;

    @BindView(R.id.tv_customer_feedback)
    TextView textViewCustomerFeedBack;

    @BindView(R.id.tv_customer_created_by)
    TextView textViewCustomerCreatedBY;

    @BindView(R.id.tv_customer_modify_by)
    TextView textViewModifyBY;

    @BindView(R.id.tv_customer_created_time)
    TextView textViewCreatedTime;

    @BindView(R.id.tv_customer_modify_time)
    TextView textViewModifyTime;

    @BindView(R.id.linear_layout_customer_priority)
    LinearLayout linearLayoutCustomerPriority;

    @BindView(R.id.linear_layout_customer_origin)
    LinearLayout linearLayoutCustomerOrigin;

    @BindView(R.id.linear_layout_customer_reason)
    LinearLayout linearLayoutCustomerReason;

    @BindView(R.id.linear_layout_incharge)
    LinearLayout linearLayoutIncharge;

    @BindView(R.id.linear_layout_cc_mail_id)
    LinearLayout linearLayoutCCMailId;

    @BindView(R.id.linear_layout_status)
    LinearLayout linearLayoutStatus;

    @BindView(R.id.linear_layout_created_by)
    LinearLayout linearLayoutCreatedBY;

    @BindView(R.id.linear_layout_customer_subject)
    LinearLayout linearLayoutCustomerSubject;

    @BindView(R.id.linear_layout_customer_note)
    LinearLayout linearLayoutCustomerNote;

    @BindView(R.id.linear_layout_customer_description)
    LinearLayout linearLayoutCustomerDescription;

    @BindView(R.id.linear_layout_internal_note)
    LinearLayout linearLayoutInternalNote;

    @BindView(R.id.linear_layout_customer_feedback)
    LinearLayout linearLayoutCustomerFeedback;

    @BindView(R.id.linear_layout_customer_contact)
    LinearLayout linearLayoutCustomerContact;

    @BindView(R.id.tv_smart_view)
    TextView textViewSmartView;

    @BindView(R.id.tv_contact_label)
    TextView textViewCustomerChallengeLabel;

    private DataBaseAdapter dataBaseAdapter;
    private Long customerChallengeId;
    private SessionManager sessionManager;
    private String titleContact,titleLead;
    private CustomerChallengeModel customerChallengeModel;
    private long contactId,leadId;
    private AlertDialogManager alertDialogManager;
    private boolean isSync,isLead= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_challenges_detail);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        sessionManager  = SessionManager.getInstance();


        alertDialogManager = new AlertDialogManager();

        Intent intent = getIntent();
        customerChallengeId = intent.getLongExtra(EmployConstantUtil.KEY_CUSTOMER_CHALLENGE_ID, 0);

        dataBaseAdapter = new DataBaseAdapter(this);
        customerChallengeModel = dataBaseAdapter.getCustomerChallengesById(customerChallengeId);
        isSync = customerChallengeModel.isSync();


        textViewSmartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smartView();
            }
        });


        setResource();

        updateUI();

        linearLayoutCustomerContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLead) {

                    if (dataBaseAdapter.getLeadByID(leadId) != null) {
                        startNewActivity(LeadDetailActivity.class, leadId, EmployConstantUtil.KEY_LEAD_ID);
                    } else {
                        alertDialogManager.showAlertDialog(CustomerChallengesDetailActivity.this, "", "Sorry ! this Lead has been deleted", false);

                    }
                } else {
                    if (dataBaseAdapter.getContactById(contactId) != null) {
                        startNewActivity(ContactDetailActivity.class, contactId, EmployConstantUtil.KEY_CONTACT_ID);
                    } else {
                        alertDialogManager.showAlertDialog(CustomerChallengesDetailActivity.this, "", "Sorry ! this contact has been deleted", false);

                    }
                }
            }
        });



    }


    private void startNewActivity(Class<?> cls, long id, String key) {
        Intent intent = new Intent(this, cls);
        intent.putExtra(key, id);
        startActivity(intent);
    }

    private void startNewActivityForResult(Class<?> cls, long id, String key,int requestCode) {
        Intent intent = new Intent(this, cls);
        intent.putExtra(key, id);
        startActivityForResult(intent,requestCode);
    }




    private void setResource(){
        titleContact = getResources().getString(R.string.title_task_contact);
        titleLead = getResources().getString(R.string.title_task_lead);
    }

    private boolean checkConnection() {

        boolean isConnected = ConnectivityReceiver.isConnected();
        if (!isConnected) {

            return false;
        }

        return true;
    }

    private void smartView() {
        if (textViewSmartView.getText().toString().equals(getResources().getString(R.string.footer_show_all_fields))) {
            textViewSmartView.setText(getResources().getString(R.string.footer_smart_view));
            linearLayoutCustomerPriority.setVisibility(View.VISIBLE);
            linearLayoutCustomerOrigin.setVisibility(View.VISIBLE);
            linearLayoutCustomerReason.setVisibility(View.VISIBLE);
            linearLayoutIncharge.setVisibility(View.VISIBLE);
            linearLayoutCCMailId.setVisibility(View.VISIBLE);
            linearLayoutStatus.setVisibility(View.VISIBLE);
            linearLayoutCustomerSubject.setVisibility(View.VISIBLE);
            textViewCustomerNote.setVisibility(View.VISIBLE);
            linearLayoutCustomerDescription.setVisibility(View.VISIBLE);
            linearLayoutInternalNote.setVisibility(View.VISIBLE);
            linearLayoutCustomerFeedback.setVisibility(View.VISIBLE);

        } else {
            textViewSmartView.setText(getResources().getString(R.string.footer_show_all_fields));
            setVisibilityGone();
            setCustomerChallenge();
        }
    }

    private void setVisibilityGone() {

        linearLayoutCustomerPriority.setVisibility(View.GONE);
        linearLayoutCustomerOrigin.setVisibility(View.GONE);
        linearLayoutCustomerReason.setVisibility(View.GONE);
        linearLayoutIncharge.setVisibility(View.GONE);
        linearLayoutCCMailId.setVisibility(View.GONE);
        linearLayoutStatus.setVisibility(View.GONE);
        linearLayoutCustomerSubject.setVisibility(View.GONE);
        linearLayoutCustomerNote.setVisibility(View.GONE);
        linearLayoutCustomerDescription.setVisibility(View.GONE);
        linearLayoutInternalNote.setVisibility(View.GONE);
        linearLayoutCustomerFeedback.setVisibility(View.GONE);
    }

    private void updateUI() {
        getCustomerChallengeById();
        setCustomerChallenge();
    }

    private void setCustomerChallenge() {
        textViewAddCustomer.setText(customerChallengeModel.getCustomer());
        contactId = customerChallengeModel.getContactId();
        leadId  = customerChallengeModel.getLeadId();

        if(!customerChallengeModel.getContactName().isEmpty()){

            textViewCustomerContact.setText(customerChallengeModel.getContactName());
            textViewCustomerChallengeLabel.setText(titleContact);
        }else {
            isLead = true;
            textViewCustomerContact.setText(customerChallengeModel.getLeadName());
            textViewCustomerChallengeLabel.setText(titleLead);

        }
        textViewCustomerLogDate.setText(DateAndTimeUtil.longToDate(customerChallengeModel.getCustomerLogDate()));
        if (!customerChallengeModel.getCustomerPriority().isEmpty()) {
            linearLayoutCustomerPriority.setVisibility(View.VISIBLE);
            textViewCustomerPriority.setText(customerChallengeModel.getCustomerPriority());
        }
        if (!customerChallengeModel.getCustomerOrigin().isEmpty()) {
            linearLayoutCustomerOrigin.setVisibility(View.VISIBLE);
            textViewCustomerOrigin.setText(customerChallengeModel.getCustomerOrigin());
        }
        if (!customerChallengeModel.getCustomerInCharge().isEmpty()) {
            linearLayoutCustomerReason.setVisibility(View.VISIBLE);
            textViewCustomerReason.setText(customerChallengeModel.getCustomerReason());

        }
        textViewCustomerDueDate.setText(DateAndTimeUtil.longToDate(customerChallengeModel.getCustomerDueDate()));
        textViewClosedOn.setText(DateAndTimeUtil.longToDate(customerChallengeModel.getCloseddate()));
        if (!customerChallengeModel.getInternalNote().isEmpty()) {
            linearLayoutInternalNote.setVisibility(View.VISIBLE);
            textViewInternalNote.setText(customerChallengeModel.getInternalNote());
        }
        if (!customerChallengeModel.getCustomerInCharge().isEmpty()) {
            linearLayoutIncharge.setVisibility(View.VISIBLE);
            textViewCustomerIncharge.setText(customerChallengeModel.getCustomerInCharge());
        }
        if (!customerChallengeModel.getCcMailId().isEmpty()) {
            linearLayoutCCMailId.setVisibility(View.VISIBLE);
            textViewCCMailId.setText(customerChallengeModel.getCcMailId());
        }
        if (!customerChallengeModel.getStatus().isEmpty()) {
            linearLayoutStatus.setVisibility(View.VISIBLE);
            textViewCustomerStatus.setText(customerChallengeModel.getStatus());
        }
        if (!customerChallengeModel.getSubject().isEmpty()) {
            linearLayoutCustomerSubject.setVisibility(View.VISIBLE);
            textViewCustomerSubject.setText(customerChallengeModel.getSubject());
        }
        if (!customerChallengeModel.getNote().isEmpty()) {
            linearLayoutCustomerNote.setVisibility(View.VISIBLE);
            textViewCustomerNote.setText(customerChallengeModel.getNote());
        }
        if (!customerChallengeModel.getDescription().isEmpty()) {
            linearLayoutCustomerDescription.setVisibility(View.VISIBLE);
            textViewCustomerDescription.setText(customerChallengeModel.getDescription());
        }
        if (!customerChallengeModel.getCustomerFeedback().isEmpty()) {
            linearLayoutCustomerFeedback.setVisibility(View.VISIBLE);
            textViewCustomerFeedBack.setText(customerChallengeModel.getCustomerFeedback());
        }
        textViewCustomerCreatedBY.setText(customerChallengeModel.getCreatedBy());
        textViewCreatedTime.setText(DateAndTimeUtil.longToDate(customerChallengeModel.getCreatedTime()));
        textViewModifyBY.setText(customerChallengeModel.getModifyBy());
        textViewModifyTime.setText(DateAndTimeUtil.longToDate(customerChallengeModel.getModifyTime()));


    }


    private void getCustomerChallengeById() {

        customerChallengeModel = dataBaseAdapter.getCustomerChallengesById(customerChallengeId);
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sales_budget, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_edit:
                startNewActivityForResult(AddCustomerChallenge.class,customerChallengeId,EmployConstantUtil.KEY_CUSTOMER_CHALLENGE_ID,REQUEST_EDIT_CUSTOMER_CHALLENGE);
                Log.e(TAG,"send customer  id to edit deal"+customerChallengeId);
                break;
            case R.id.action_delete:
                deleteCustomerChallenges();
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteCustomerChallenges() {
        if (!isSync) {
            if (dataBaseAdapter.deleteCustomerChallenges(customerChallengeId)) {
                Toast.makeText(this, "Customer account has been Deleted   not sync", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(this, "Please try again later ", Toast.LENGTH_SHORT).show();
            }

        } else {

            if (checkConnection()) {
                AlertDialogManager.showAlertDialogMessage(this, "", "Are you sure to delete Account", false, this);
            } else {
                Toast.makeText(this, "Please check your Internet connection ", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void alertDialogCallbackOk() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        final Call<String> response = apiService.removeCustChallenge(customerChallengeId,sessionManager.getUserKeyId());
        response.enqueue(this);
    }

    @Override
    public void alertDialogCallbackCancel() {

    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        int statusCode = response.code();
        if (statusCode == 200) {
            if (dataBaseAdapter.deleteCustomerChallenges(customerChallengeId)) {
                Toast.makeText(this, "Customer Account has been Deleted ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(this, "Please try again later ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        Log.d("testing", "" + t.getMessage());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case REQUEST_EDIT_CUSTOMER_CHALLENGE:
                    updateUI();
            }
        }
    }
}
