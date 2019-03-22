package com.software.ttsl;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.software.ttsl.Fragment.ContactDetailFragment;
import com.software.ttsl.Fragment.ContactRelatedFragment;
import com.software.ttsl.Fragment.LeadDetailsFragment;
import com.software.ttsl.Fragment.LeadRelatedFragment;
import com.software.ttsl.Interfacce.AlertDialogCallback;
import com.software.ttsl.Request.AccountDataModel;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.AlertDialogManager;
import com.software.ttsl.Utils.ConnectivityReceiver;
import com.software.ttsl.Utils.EmployConstantUtil;
import com.software.ttsl.model.AddContactData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactDetailActivity extends AppCompatActivity implements AlertDialogCallback, Callback<String> {


    public static final String TAG =ContactDetailActivity.class.getName();
    private static final int REQUEST_CODE_ADD_CONTACT = 1000;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private DataBaseAdapter dataBaseAdapter;
    private AddContactData addContactData;
    private boolean isSync;
    Long contactId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        contactId= intent.getLongExtra(EmployConstantUtil.KEY_CONTACT_ID,0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataBaseAdapter = new DataBaseAdapter(this);
        addContactData = dataBaseAdapter.getContactById(contactId);
        isSync  = addContactData.isSync();
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }


    public AddContactData getContactData(){

        return  addContactData;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_ADD_CONTACT:
                    addContactData = dataBaseAdapter.getContactById(contactId);
                    viewPager.setCurrentItem(0);
                    break;
                case RESULT_CANCELED:
                    viewPager.setCurrentItem(0);
                    break;
            }
        }
    }



    private void setupViewPager(ViewPager viewPager) {
        Bundle bundle = new Bundle();
        bundle.putLong(EmployConstantUtil.KEY_CONTACT_ID, contactId);
        ContactRelatedFragment contactRelatedFragment = new ContactRelatedFragment();
        contactRelatedFragment.setArguments(bundle);
        ContactDetailFragment contactDetailFragment= new ContactDetailFragment();
        contactDetailFragment.setArguments(bundle);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(contactRelatedFragment, "Related");
        viewPagerAdapter.addFragment(contactDetailFragment, "Detail");


        viewPager.setAdapter(viewPagerAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lead_details, menu);
        return true;
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        updateUI();
    }

    private void updateUI(){
        addContactData = dataBaseAdapter.getContactById(contactId);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:

                onBackPressed();
                break;
            case R.id.action_edit:

                Intent intent = new Intent(ContactDetailActivity.this, AddContactActivity.class);
                intent.putExtra(EmployConstantUtil.KEY_CONTACT_ID, contactId);
                Log.e(TAG,"send contact id to edit contact"+contactId);
                startActivityForResult(intent, REQUEST_CODE_ADD_CONTACT);
                break;

            case R.id.action_delete:
                deleteContact();
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

    private  void  deleteContact() {

        if(!isSync){
            if (dataBaseAdapter.deleteContact(contactId)) {
                Toast.makeText(this, "Contact has been Deleted   not sync", Toast.LENGTH_SHORT).show();
                AlertDialogManager.showAlertDialogMessage(this, "", "Are you sure to delete Account", false, this);
            } else {
                Toast.makeText(this, "Please try again later ", Toast.LENGTH_SHORT).show();
            }

        }else {

            if (checkConnection()) {
                AlertDialogManager.showAlertDialogMessage(this, "", "Are you sure to delete Account", false, this);
            } else {
                Toast.makeText(this, "Please check your Internet connection ", Toast.LENGTH_SHORT).show();
            }
        }




    }

    private void finishActivity(){
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void alertDialogCallbackOk() {

        if(!isSync) {
            finishActivity();
        }else {
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);

            final Call<String> response = apiService.removeContact(contactId);
            response.enqueue(this);
        }
    }

    @Override
    public void alertDialogCallbackCancel() {
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        int statusCode = response.code();
        if (statusCode == 204) {
            if (dataBaseAdapter.deleteContact(contactId)) {
                Toast.makeText(this, "Contact has been Deleted ", Toast.LENGTH_SHORT).show();
               finishActivity();
            } else {
                Toast.makeText(this, "Please try again later ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        Log.d("testing", "" + t.getMessage());
        finishActivity();
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
