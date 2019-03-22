package com.software.ttsl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddCallActivity1 extends AppCompatActivity implements View.OnClickListener {



    private static final String ITEM_LIST ="string-array";
    private static final String TITLE ="toolBar_title";
    private static final String SELECTED_ITEM="selected_item";
    private static final int REQUEST_CODE= 1000;


    public String[] itemList;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.layout_call_type)
    RelativeLayout relativeLayoutCallType;
    @BindView(R.id.layout_call_date)
    RelativeLayout relativeLayoutCallDate;
    @BindView(R.id.layout_planned_date)
    RelativeLayout relativeLayoutPlannedDate;
    @BindView(R.id.layout_call_purpose)
    RelativeLayout relativeLayoutCallPurpose;
    @BindView(R.id.layout_mode)
    RelativeLayout relativeLayoutMode;
    @BindView(R.id.layout_followup_action)
    RelativeLayout relativeLayoutFollowupAction;
    @BindView(R.id.layout_followup_date)
    RelativeLayout relativeLayoutFollowupDate;
    @BindView(R.id.layout_status)
    RelativeLayout relativeLayoutFollowupStatus;
    @BindView(R.id.layout_followup_by)
    RelativeLayout relativeLayoutFollowupBy;
    @BindView(R.id.layout_designation)
    RelativeLayout relativeLayoutDesignation;
    @BindView(R.id.et_call_date)
    EditText editTextCallDate;
    @BindView(R.id.et_customer)
    EditText editTextCustomer;
    @BindView(R.id.et_salesman)
    EditText editTextSalesman;
    @BindView(R.id.et_sales_territory)
    EditText editTextSalesTerritory;
    @BindView(R.id.et_planned_date)
    EditText editTextPlannedDate;
    @BindView(R.id.et_cs_person)
    EditText editTextCSPerson;
    @BindView(R.id.et_call_type)
    EditText editTextCallType;
    @BindView(R.id.et_call_purpose)
    EditText editTextCallPurpose;
    @BindView(R.id.et_mode)
    EditText editTextMode;
    @BindView(R.id.et_discription)
    EditText editTextDiscription;
    @BindView(R.id.et_followup_action)
    EditText editTextFollowupAction;
    @BindView(R.id.et_followup_date)
    EditText editTextFollowupDate;
    @BindView(R.id.et_followup_status)
    EditText editTextFollowupStatus;
    @BindView(R.id.et_followup_by)
    EditText editTextFollowupBy;
    @BindView(R.id.et_name)
    EditText editTextFollowupName;
    @BindView(R.id.et_designation)
    EditText editTextDesignation;
    @BindView(R.id.et_email)
    EditText editTextEmail;
    private Intent intent;
    private String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_call1);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setClickListener();

    }

    private void setClickListener() {
        relativeLayoutCallDate.setOnClickListener(this);
        relativeLayoutPlannedDate.setOnClickListener(this);
        relativeLayoutCallType.setOnClickListener(this);
        relativeLayoutCallPurpose.setOnClickListener(this);
        relativeLayoutMode.setOnClickListener(this);
        relativeLayoutFollowupAction.setOnClickListener(this);
        relativeLayoutFollowupDate.setOnClickListener(this);
        relativeLayoutFollowupStatus.setOnClickListener(this);
        relativeLayoutFollowupBy.setOnClickListener(this);
        relativeLayoutDesignation.setOnClickListener(this);
        editTextCallType.setOnClickListener(this);
        editTextCallPurpose.setOnClickListener(this);
        editTextDesignation.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {



        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id) {
            case R.id.layout_call_date:
                break;
            case R.id.layout_planned_date:
                break;
            case R.id.layout_call_type:

                itemList = getResources().getStringArray(R.array.add_call_call_type);
                title = "Call Type";
                startNewActivity(title,itemList);
                break;

            case R.id.et_call_type:
                itemList = getResources().getStringArray(R.array.add_call_call_type);
                title="Call Type";
                startNewActivity(title,itemList);

                break;
            case R.id.layout_call_purpose:
                itemList = getResources().getStringArray(R.array.add_call_purpose);
                title ="Call Purpose";
                startNewActivity(title,itemList);
                break;

            case R.id.et_call_purpose:
                itemList = getResources().getStringArray(R.array.add_call_purpose);
                title ="Call Purpose";
                startNewActivity(title,itemList);
                break;
            case R.id.layout_followup_action:
                break;
            case R.id.layout_followup_date:
                break;
            case R.id.layout_followup_by:
                break;
            case R.id.layout_status:
                break;
            case R.id.layout_mode:
                itemList = getResources().getStringArray(R.array.add_call_mode);
                break;
            case R.id.layout_designation:
                itemList = getResources().getStringArray(R.array.add_call_designation);
                break;
        }

    }

    private void startNewActivity(String title, String[] itemList) {

        intent = new Intent(AddCallActivity1.this, CustomListActivity.class);
        intent.putExtra(ITEM_LIST, itemList);
        intent.putExtra(TITLE,title);
        startActivityForResult(intent,1000);


    }




    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if(resultCode == RESULT_OK) {
                String selectedItem = data.getStringExtra(SELECTED_ITEM);
                String title = data.getStringExtra(TITLE);
                if(title.equals("Call Purpose")){
                    editTextCallPurpose.setText(selectedItem);

                }else if(title.equals("Call Type"))
                {
                    editTextCallType.setText(selectedItem);
                }


            }
        }
    }
}
