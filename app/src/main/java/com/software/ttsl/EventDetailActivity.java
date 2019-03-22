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

import com.software.ttsl.Fragment.EventDetailFragment;
import com.software.ttsl.Fragment.EventRelatedFragment;
import com.software.ttsl.Interfacce.AlertDialogCallback;
import com.software.ttsl.Request.EventDataModel;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.AlertDialogManager;
import com.software.ttsl.Utils.ConnectivityReceiver;
import com.software.ttsl.Utils.EmployConstantUtil;
import com.software.ttsl.Utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventDetailActivity extends AppCompatActivity implements AlertDialogCallback, Callback<String> {


    public static final String TAG = EventDetailActivity.class.getName();
    public static final int REQUEST_EDIT_EVENT = 1000;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    Long eventId;
    private DataBaseAdapter dataBaseAdapter;
    private EventDataModel eventDataModel;
    private boolean isSync;
    private SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sessionManager = SessionManager.getInstance();



        Intent intent = getIntent();
        eventId = intent.getLongExtra(EmployConstantUtil.KEY_EVENT_ID, 0);
        Log.v(TAG, "Event id in event detail activity  " + eventId);

        dataBaseAdapter = new DataBaseAdapter(this);
        eventDataModel = dataBaseAdapter.getEventByID(eventId);
        isSync = eventDataModel.isSync();
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

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
                Intent intent = new Intent(EventDetailActivity.this, AddEventActivity.class);
                intent.putExtra(EmployConstantUtil.KEY_EVENT_ID, eventId);
                Log.v(TAG, "send event id to edit event  " + eventId);
                startActivityForResult(intent, REQUEST_EDIT_EVENT);
                break;

            case R.id.action_delete:
                DeleteEvent();
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

    private void DeleteEvent() {


        if (!isSync) {
            if (dataBaseAdapter.deleteEvent(eventId)) {
                Toast.makeText(this, "Event has been Deleted   not sync", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(this, "Please try again later ", Toast.LENGTH_SHORT).show();
            }

        } else {

            if (checkConnection()) {
                AlertDialogManager.showAlertDialogMessage(this, "", "Are you sure to delete Event", false, EventDetailActivity.this);
            } else {
                Toast.makeText(this, "Please check your Internet connection ", Toast.LENGTH_SHORT).show();
            }
        }
        /*Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();*/
    }


    public EventDataModel getEventData() {

        return eventDataModel;
    }

    private void setupViewPager(ViewPager viewPager) {
        Bundle bundle = new Bundle();
        bundle.putLong(EmployConstantUtil.KEY_EVENT_ID, eventId);
        EventRelatedFragment eventRelatedFragment = new EventRelatedFragment();
        eventRelatedFragment.setArguments(bundle);

        EventDetailFragment eventDetailFragment = new EventDetailFragment();
        eventDetailFragment.setArguments(bundle);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(eventRelatedFragment, "Related");
        adapter.addFragment(eventDetailFragment, "Detail");
        viewPager.setAdapter(adapter);
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        eventDataModel = dataBaseAdapter.getEventByID(eventId);
    }


    @Override
    public void alertDialogCallbackOk() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        final Call<String> response = apiService.removeEvent(eventId,sessionManager.getUserKeyId());
        response.enqueue(this);
    }

    @Override
    public void alertDialogCallbackCancel() {

    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        int statusCode = response.code();
        if (statusCode == 204) {
            if (dataBaseAdapter.deleteEvent(eventId)) {
                Toast.makeText(this, "Event has been Deleted ", Toast.LENGTH_SHORT).show();
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
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_EDIT_EVENT:
                    eventDataModel = dataBaseAdapter.getEventByID(eventId);
                    viewPager.setCurrentItem(0);
                    break;

                    /*EventRelatedFragment fragment =(EventRelatedFragment) (position);*/

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
