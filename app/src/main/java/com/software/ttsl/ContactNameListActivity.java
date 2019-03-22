package com.software.ttsl;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.software.ttsl.Adapter.AccountNameListAdapter;
import com.software.ttsl.Adapter.ContactNameListAdapter;
import com.software.ttsl.Interface.ItemClickListener;
import com.software.ttsl.Request.AccountDataModel;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.DateAndTimeUtil;
import com.software.ttsl.Utils.EmployConstantUtil;
import com.software.ttsl.model.AddContactData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactNameListActivity extends AppCompatActivity implements ItemClickListener, View.OnClickListener, AdapterView.OnItemSelectedListener {
    public static final String TAG = ContactNameListActivity.class.getName();


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.tv_msg)
    TextView textViewMsg;

    @BindView(R.id.sp_module)
    Spinner spinnerModule;

    @BindView(R.id.fab)
    FloatingActionButton fabAddContact;


    private boolean isLead = false;
    private ContactNameListAdapter contactNameListAdapter;
    private List<AddContactData> addContactDataList = new ArrayList<>();
    private DataBaseAdapter dataBaseAdapter;
    private String selectedItem;
    private long contactId;
    private Intent intent;
    private String emptyListMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_name_list);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        dataBaseAdapter = new DataBaseAdapter(this);


        contactNameListAdapter = new ContactNameListAdapter(this, addContactDataList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(contactNameListAdapter);
        recyclerView.setHasFixedSize(true);

        updateUI();


        setClickListener();
    }

    private void updateUI(){
       /* if(isLead){

        }
        addContactDataList.clear();
        addContactDataList.addAll(dataBaseAdapter.getAllContact());
        if (addContactDataList.size() == 0) {
            textViewMsg.setVisibility(View.VISIBLE);

        }else {
            textViewMsg.setVisibility(View.GONE);
            contactNameListAdapter.notifyDataSetChanged();
        }

*/

        if(isLead){
            addContactDataList.clear();
            addContactDataList.addAll(dataBaseAdapter.getAllLeads());
            emptyListMessage = "No lead are available. \n Please create lead first";



        }else {

            addContactDataList.clear();
            addContactDataList.addAll(dataBaseAdapter.getAllContact());
            emptyListMessage = "No contact are available. \n Please create contact first";


        }

        if (addContactDataList.size() == 0) {
            textViewMsg.setVisibility(View.VISIBLE);
            textViewMsg.setText(emptyListMessage);

        }else {
            textViewMsg.setVisibility(View.GONE);

            contactNameListAdapter.notifyDataSetChanged();
        }


    }

    private void setClickListener() {
        fabAddContact.setOnClickListener(this);
        spinnerModule.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemClick(int position) {

        selectedItem = (DateAndTimeUtil.firstLatterUpper(addContactDataList.get(position).getFirstName())+ " " +
                DateAndTimeUtil.firstLatterUpper(addContactDataList.get(position).getLastName())).trim();
        contactId = addContactDataList.get(position).getContactId();
        backActivity(selectedItem,contactId);


    }

    private void backActivity(String selectedItem,long contactId) {
        Intent intent = new Intent();
        if(isLead){
            intent.putExtra(EmployConstantUtil.KEY_IS_LEAD,isLead);
        }
        intent.putExtra(EmployConstantUtil.SELECTED_ITEM, selectedItem);
        intent.putExtra(EmployConstantUtil.KEY_CONTACT_ID,contactId);
        intent.putExtra(EmployConstantUtil.TITLE, getResources().getString(R.string.title_contact_list_activity));
        setResult(RESULT_OK, intent);
        finish();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.fab:
                if (isLead) {
                    startNewActivity(AddLeadActivity.class);
                }
                else {
                    startNewActivity(AddContactActivity.class);
                }
                break;



        }
    }


    private void startNewActivity(Class<?> cls) {
        intent = new Intent(ContactNameListActivity.this, cls);
        startActivityForResult(intent, 1000);

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == RESULT_OK) {

                updateUI();

            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selectedItem = adapterView.getItemAtPosition(i).toString();
        if(selectedItem.equals("Leads")){
            addContactDataList.clear();
            addContactDataList.addAll(dataBaseAdapter.getAllLeads());
            emptyListMessage = "No lead are available. \n Please create lead first";

            isLead = true;

        }else {

            addContactDataList.clear();
            addContactDataList.addAll(dataBaseAdapter.getAllContact());
            emptyListMessage = "No contact are available. \n Please create contact first";
            isLead  = false;

        }

        if (addContactDataList.size() == 0) {
            textViewMsg.setVisibility(View.VISIBLE);
            textViewMsg.setText(emptyListMessage);

        }else {
            textViewMsg.setVisibility(View.GONE);

            contactNameListAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}
