package com.software.ttsl;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.software.ttsl.Fragment.AddContactFragment;
import com.software.ttsl.Fragment.AddLeadFragment;
import com.software.ttsl.Request.AccountDataModel;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.DialogUtitlity;
import com.software.ttsl.model.AddContactData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactListActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG  = ContactListActivity.class.getName();
    public static final int ORDERBY=100;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;
    AddContactFragment addContactFragment;
    DataBaseAdapter dataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        ButterKnife.bind(this);


        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataBaseAdapter = new DataBaseAdapter(this);

        setClickListener();


        if(dataBaseAdapter.getContacts().size()==0) {
           // getAllContact();
        }

        if (savedInstanceState == null) {
            // Let's first dynamically add a fragment into a frame container
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.fragment_container, new AddContactFragment(), "ADD_CONTACT").
                    commit();

        }
        // Now later we can lookup the fragment by tag
        addContactFragment = (AddContactFragment) getSupportFragmentManager().findFragmentByTag("ADD_CONTACT");


    }

    private void getAllContact(){
        DialogUtitlity.showLoading(ContactListActivity.this);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<AddContactData>> listCall = apiInterface.getAllContact(0);
        listCall.enqueue(new Callback<List<AddContactData>>() {
            @Override
            public void onResponse(Call<List<AddContactData>> call, Response<List<AddContactData>> response) {
                DialogUtitlity.hideLoading();
                Log.i(TAG, "inside getAll lead from server");
                int statusCode = response.code();
                Log.i(TAG, String.valueOf(statusCode));
                if (statusCode == 200) {
                    if (response.body() instanceof List) {
                        List<AddContactData> contactDataList= response.body();
                        dataBaseAdapter.setAllContacts(contactDataList);


                    }
                }
            }

            @Override
            public void onFailure(Call<List<AddContactData>> call, Throwable t) {

            }
        });
    }

    private void setClickListener() {
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startActivityForResult(new Intent(ContactListActivity.this, AddContactActivity.class), 1000);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1000:
                    //TODO get intent data
                    addContactFragment = (AddContactFragment) getSupportFragmentManager().findFragmentByTag("ADD_CONTACT");
                    addContactFragment.onActivityResult(requestCode, resultCode, data);
                    break;

               /* case ORDERBY:

                    addLeadFragment = (AddLeadFragment) getSupportFragmentManager().findFragmentByTag("ADDLEAD");
                    addLeadFragment.onActivityResult(requestCode, resultCode, data);
                    break;*/
            }
        }



    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
