package com.software.ttsl;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.software.ttsl.Adapter.AccountNameListAdapter;
import com.software.ttsl.Interface.ItemClickListener;
import com.software.ttsl.Request.AccountDataModel;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.EmployConstantUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountNameListActivity extends AppCompatActivity implements ItemClickListener, View.OnClickListener {

    public static final String TAG= AccountNameListActivity.class.getName();
    public static final String PARENT_ACCOUNT="ParentAccount";


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.tv_msg)
    TextView textViewMsg;

    @BindView(R.id.fab)
    FloatingActionButton fabAddContact;



    private AccountNameListAdapter accountListFragmentAdapter;
    private List<AccountDataModel> accountDataModelList= new ArrayList<AccountDataModel>();
    private DataBaseAdapter dataBaseAdapter;
    private String selectedItem;
    private long accountId;
    private Intent intent;
    private boolean firstParent = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_name_list);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        dataBaseAdapter = new DataBaseAdapter(this);



        accountListFragmentAdapter = new AccountNameListAdapter(this,accountDataModelList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(accountListFragmentAdapter);
        recyclerView.setHasFixedSize(true);

        updateUI();
        setClickListener();
    }

    private void updateUI() {
        accountDataModelList.clear();
        accountDataModelList.addAll(dataBaseAdapter.getAllAccounts());
        if(accountDataModelList.size()==0){
            textViewMsg.setVisibility(View.VISIBLE );
            firstParent= false;
        }else {
            textViewMsg.setVisibility(View.GONE);
            accountListFragmentAdapter.notifyDataSetChanged();
        }

    }

    private void setClickListener() {
        fabAddContact.setOnClickListener(this);
    }


    @Override
    public void onItemClick(int position) {
        selectedItem = accountDataModelList.get(position).getAccountName();
        accountId =accountDataModelList.get(position).getId();
        backActivity(selectedItem);


    }



    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_custom_data_list, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.add:



               /* if (setLeadData()) {
                    addLeadData();

                }*/


                break;
        }

        return super.onOptionsItemSelected(item);


    }

    private void backActivity(String selectedItem) {
        Intent intent = new Intent();
        intent.putExtra(EmployConstantUtil.SELECTED_ITEM, selectedItem);
        intent.putExtra(EmployConstantUtil.KEY_ACCOUNT_ID,accountId);
        intent.putExtra(EmployConstantUtil.TITLE,getResources().getString(R.string.title_account_list_activity));
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
                startNewActivity(AddAccountActivity.class);

        }
    }

    private void startNewActivity(Class<?> cls) {
        intent = new Intent(AccountNameListActivity.this, cls);
        intent.putExtra(PARENT_ACCOUNT,firstParent);
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


}
