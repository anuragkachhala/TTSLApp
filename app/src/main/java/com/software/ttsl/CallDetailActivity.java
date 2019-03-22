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

import com.software.ttsl.Fragment.CallDetailFragment;
import com.software.ttsl.Fragment.CallRelatedFragment;
import com.software.ttsl.Interfacce.AlertDialogCallback;
import com.software.ttsl.Request.CallDataModel;
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

import static com.software.ttsl.TaskListActivity.REQUEST_CODE_ADD_TASK;

public class CallDetailActivity extends AppCompatActivity implements AlertDialogCallback, Callback<String> {


    public static final String TAG = CallDetailActivity.class.getName();
    public static final int REQUEST_CODE_EDIT_CALL= 2000;


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    Long callId;

    private DataBaseAdapter dataBaseAdapter;
    private CallDataModel callDataModel;
    private boolean isSync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_detail);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        callId = intent.getLongExtra(EmployConstantUtil.KEY_CALL_ID, 0);
        Log.e(TAG, "call id in call detail activity  " + callId);

        dataBaseAdapter = new DataBaseAdapter(this);
        callDataModel = dataBaseAdapter.getCallByID(callId);
        isSync = callDataModel.isSync();
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    public CallDataModel getCallDataModel() {
        return callDataModel;
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
                Intent intent = new Intent(CallDetailActivity.this, AddCallActivity.class);
                intent.putExtra(EmployConstantUtil.KEY_CALL_ID, callId);
                Log.e(TAG, "send call id to edit call" + callId);
                startActivityForResult(intent,REQUEST_CODE_EDIT_CALL);
                break;

            case R.id.action_delete:
                deleteCall();
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

    private void deleteCall() {
        // AlertDialogManager.showAlertDialogMessage(this, "", "Are you sure to delete call", false, this);

        if (!isSync) {
            if (dataBaseAdapter.deleteCall(callId)) {
                Toast.makeText(this, "Call has been Deleted   not sync", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(this, "Please try again later ", Toast.LENGTH_SHORT).show();
            }

        } else {

            if (checkConnection()) {
                AlertDialogManager.showAlertDialogMessage(this, "", "Are you sure to delete Call", false, this);
            } else {
                Toast.makeText(this, "Please check your Internet connection ", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void setupViewPager(ViewPager viewPager) {
        Bundle bundle = new Bundle();
        bundle.putLong(EmployConstantUtil.KEY_CALL_ID, callId);
        CallRelatedFragment callRelatedFragment = new CallRelatedFragment();
        callRelatedFragment.setArguments(bundle);
        CallDetailFragment callDetailFragment = new CallDetailFragment();
        callDetailFragment.setArguments(bundle);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(callRelatedFragment, "Related");
        adapter.addFragment(callDetailFragment, "Detail");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void alertDialogCallbackOk() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        final Call<String> response = apiService.removeCall(callId);
        response.enqueue(this);
    }

    @Override
    public void alertDialogCallbackCancel() {

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        callDataModel = dataBaseAdapter.getCallByID(callId);
    }


    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        int statusCode = response.code();
        if (statusCode == 200) {
            if (dataBaseAdapter.deleteCall(callId)) {
                Toast.makeText(this, "Call has been Deleted ", Toast.LENGTH_SHORT).show();
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
        if(resultCode==RESULT_OK){
            switch (requestCode) {
                case REQUEST_CODE_ADD_TASK:
                 /*setResult(RESULT_OK, data);
                 finish();*/
                    break;
                case REQUEST_CODE_EDIT_CALL:
                    callDataModel = dataBaseAdapter.getCallByID(callId);
                    viewPager.setCurrentItem(0);
                    break;

            }
        }else if(requestCode==RESULT_CANCELED){

            viewPager.setCurrentItem(1);


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
