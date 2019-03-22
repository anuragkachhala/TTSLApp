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

import com.software.ttsl.Fragment.DealDetailFragment;
import com.software.ttsl.Fragment.DealRelatedFragment;
import com.software.ttsl.Interfacce.AlertDialogCallback;
import com.software.ttsl.Request.DealDataModel;
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

public class DealDetailsActivity extends AppCompatActivity implements AlertDialogCallback, Callback<String> {

    public static final String TAG =DealDetailsActivity.class.getName();

    private static final int REQUEST_CODE_EDIT_DEAL = 1000;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    Long dealId;

    private DataBaseAdapter dataBaseAdapter ;
    private DealDataModel   dealDataModel;
    private boolean isSync;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_details);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        dealId = intent.getLongExtra(EmployConstantUtil.KEY_DEAL_ID, 0);
        Log.e(TAG, "Deal id in deal detail activity  "+dealId);

        dataBaseAdapter = new DataBaseAdapter(this);
        updateUI();
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        updateUI();
    }

    public void updateUI(){
        dealDataModel = dataBaseAdapter.getDealById(dealId);
        isSync  = dealDataModel.isSync();

    }


    private void setupViewPager(ViewPager viewPager) {
        Bundle bundle = new Bundle();
        bundle.putLong(EmployConstantUtil.KEY_DEAL_ID, dealId);
        DealRelatedFragment dealRelatedFragment = new DealRelatedFragment();
        dealRelatedFragment.setArguments(bundle);
        DealDetailFragment dealDetailFragment= new DealDetailFragment();
        dealDetailFragment.setArguments(bundle);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(dealRelatedFragment, "Related");
        adapter.addFragment(dealDetailFragment, "Detail");
        viewPager.setAdapter(adapter);
    }


    public DealDataModel getDealDataModel(){
        return dealDataModel;
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
                Intent intent = new Intent(DealDetailsActivity.this, AddDealActivity.class);
                intent.putExtra(EmployConstantUtil.KEY_DEAL_ID, dealId);
                Log.e(TAG,"send deal id to edit deal"+dealId);
                startActivityForResult(intent,REQUEST_CODE_EDIT_DEAL);
                break;

            case R.id.action_delete:
                deleteDeal();
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

    private void deleteDeal() {

     //   AlertDialogManager.showAlertDialogMessage(DealDetailsActivity.this, "", "Are you sure to delete deal", false, this);
        if(!isSync){
            if (dataBaseAdapter.deleteDeal(dealId)) {
                Toast.makeText(this, "Deal has been Deleted   not sync", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(this, "Please try again later ", Toast.LENGTH_SHORT).show();
            }

        }else {

            if (checkConnection()) {
                AlertDialogManager.showAlertDialogMessage(this, "", "Are you sure to delete Deal", false, this);
            } else {
                Toast.makeText(this, "Please check your Internet connection ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void alertDialogCallbackOk() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        final Call<String> response = apiService.removeDeal(dealId);
        response.enqueue(this);
        /*Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();*/

    }

    @Override
    public void alertDialogCallbackCancel() {

    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        int statusCode = response.code();
        if (statusCode == 200) {
            if (dataBaseAdapter.deleteDeal(dealId)) {
                Toast.makeText(this, "Deal has been Deleted ", Toast.LENGTH_SHORT).show();
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_EDIT_DEAL:
                    updateUI();
                    viewPager.setCurrentItem(0);
                    break;
                case RESULT_CANCELED:
                    viewPager.setCurrentItem(0);
                    break;
                default:

            }

        }
    }
}
