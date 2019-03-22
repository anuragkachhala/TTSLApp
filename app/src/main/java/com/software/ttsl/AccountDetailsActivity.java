package com.software.ttsl;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.software.ttsl.Fragment.AccountDetailFragment;
import com.software.ttsl.Fragment.AccountReletedFragment;
import com.software.ttsl.Interfacce.AlertDialogCallback;
import com.software.ttsl.Request.AccountDataModel;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.AlertDialogManager;
import com.software.ttsl.Utils.ConnectivityReceiver;
import com.software.ttsl.Utils.EmployConstantUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountDetailsActivity extends AppCompatActivity implements AlertDialogCallback, Callback<String> {


    public static final String TAG = AccountDetailsActivity.class.getName();
    private static final int REQUEST_CODE_ADD_ACCOUNT = 1000;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    Long accountId;
    private DataBaseAdapter dataBaseAdapter;
    private AccountDataModel accountDataModel;
    private boolean isSync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        accountId = intent.getLongExtra(EmployConstantUtil.KEY_ACCOUNT_ID, 0);
        Log.v(TAG, "Account id in account detail activity  " + accountId);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataBaseAdapter = new DataBaseAdapter(this);
        accountDataModel = dataBaseAdapter.getAccountById(accountId);

        isSync = accountDataModel.isSync();

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

    }


    public AccountDataModel getAccountData() {

        return accountDataModel;
    }


    private void setupViewPager(ViewPager viewPager) {
        Bundle bundle = new Bundle();
        bundle.putLong(EmployConstantUtil.KEY_ACCOUNT_ID, accountId);
        AccountReletedFragment accountReletedFragment = new AccountReletedFragment();
        accountReletedFragment.setArguments(bundle);
        AccountDetailFragment accountDetailFragment = new AccountDetailFragment();
        accountDetailFragment.setArguments(bundle);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(accountReletedFragment, "Related");
        adapter.addFragment(accountDetailFragment, "Detail");
        viewPager.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_account_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:

                onBackPressed();
                break;
            case R.id.action_edit:
                Intent intent = new Intent(AccountDetailsActivity.this, AddAccountActivity.class);
                intent.putExtra(EmployConstantUtil.KEY_ACCOUNT_ID, accountId);
                Log.v(TAG, "send account id to add account  " + accountId);
                startActivityForResult(intent, REQUEST_CODE_ADD_ACCOUNT);
                break;

            case R.id.action_delete:
                DeleteAccount();
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


    private void DeleteAccount() {

        //      AlertDialogManager.showAlertDialogMessage(this, "", "Are you sure to delete Account", false, this);

        if (!isSync) {
            if (dataBaseAdapter.deleteAccount(accountId)) {
                Toast.makeText(this, "Account has been Deleted not sync", Toast.LENGTH_SHORT).show();
                AlertDialogManager.showAlertDialogMessage(this, "", "Are you sure to delete Account", false, this);

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


    private void finishActivity() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        updateUI();
    }

    private void updateUI() {
        accountDataModel = dataBaseAdapter.getAccountById(accountId);

    }

    @Override
    public void alertDialogCallbackOk() {
        if (!isSync) {
            finishActivity();
        } else {
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);

            final Call<String> response = apiService.removeAccount(accountId);
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
            if (dataBaseAdapter.deleteAccount(accountId)) {
                Toast.makeText(this, "Account has been Deleted ", Toast.LENGTH_SHORT).show();
                finishActivity();
            } else {
                Toast.makeText(this, "Please try again later ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        Log.d("testing", "" + t.getMessage());
        if (dataBaseAdapter.deleteAccount(accountId)) {
            Toast.makeText(this, "Account has been Deleted ", Toast.LENGTH_SHORT).show();
            finishActivity();
        } else {
            Toast.makeText(this, "Please try again later ", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_ADD_ACCOUNT:
                    accountDataModel = dataBaseAdapter.getAccountById(accountId);
                    viewPager.setCurrentItem(0);
                    break;
                case RESULT_CANCELED:
                    viewPager.setCurrentItem(0);
                    break;
            }
        }
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

