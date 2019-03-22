package com.software.ttsl;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.software.ttsl.Fragment.UtilFragment.TestFragment;
import com.software.ttsl.Response.DealStage;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.DialogUtitlity;
import com.software.ttsl.Utils.SessionManager;
import com.software.ttsl.Utils.SharedPrefUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, Callback<List<DealStage>> {


    private static final String TAG_ABC = "abc";
    private static final String TAG_HOME = "home";
    private static final String TITLE = "HOME";
    private static final String TAG_TASK = "task";
    private static final String TAG_FEEDS = "feeds";
    private static final String TAG_DEALS = "deals";
    private static final String TAG_LEAD = "leads";
    private static final String TAG_CALL = "call";
    private static final String TAG_PROMOTIONAL_MAIL = "promotional mail";
    public static String CURRENT_TAG = TAG_HOME;


    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.layout_track_cargo)
    LinearLayout linearLayoutTackCargo;

    @BindView(R.id.layout_sailing_schedule)
    LinearLayout linearLayoutSailingSchedule;

    @BindView(R.id.layout_get_quotation)
    LinearLayout linearLayoutGetQuotation;


    SessionManager sessionManager;
    Fragment fragment = null;
    private SharedPrefUtil sharedPrefUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_home);

        ButterKnife.bind(this);
        SessionManager.setContext(getApplicationContext());
        sessionManager = SessionManager.getInstance();
        setSupportActionBar(toolbar);


        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setClickListener();
        SharedPrefUtil.setContext(getApplicationContext());
        sharedPrefUtil = SharedPrefUtil.getInstance();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);


        loadFragment(new TestFragment());


        getDealState();


      /*  if (!sessionManager.isFirstEmployeeLogin()) {
            startActivityForResult(new Intent(this, DownloadServerDataActivity.class), 1000);
        }*/

    }

    private void getDealState() {

        if (sharedPrefUtil.getAccountType() == null) {
            DialogUtitlity.showLoading(EmployeeHomeActivity.this);
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);
            final Call<List<DealStage>> response = apiService.getDealStage();
            response.enqueue(this);
        }
    }

    /*private void getLeadData() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        final Call<List<AccountType>> response = apiService.getLeadData();
        response.enqueue(new Callback<List<AccountType>>() {
            @Override
            public void onResponse(Call<List<AccountType>> call, Response<List<AccountType>> response) {

            }

            @Override
            public void onFailure(Call<List<AccountType>> call, Throwable t) {

            }
        });
    }
*/

    @Override
    public void onResponse(Call<List<DealStage>> call, Response<List<DealStage>> response) {
        DialogUtitlity.hideLoading();
        int statusCode = response.code();
        if (statusCode == 200) {
            if (response.body() instanceof List) {
                List<DealStage> dealStageList = response.body();
                if (!dealStageList.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    for (DealStage dealStage : dealStageList) {
                        sb.append(dealStage.getStage()).append(",");

                    }

                    sharedPrefUtil.setDealStage(sb.toString());

                } else {

                }

            }
        }

    }


    @Override
    public void onFailure(Call<List<DealStage>> call, Throwable t) {
        DialogUtitlity.hideLoading();

    }

    private void setClickListener() {
        linearLayoutGetQuotation.setOnClickListener(this);
        linearLayoutSailingSchedule.setOnClickListener(this);
        linearLayoutTackCargo.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private Boolean loadFragment(android.support.v4.app.Fragment fragment) {

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
            return true;
        }
        return false;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
           /* case android.R.id.home:
                onBackPressed();
                break;*/
            case R.id.sync:
                Intent syncIntent = new Intent(EmployeeHomeActivity.this, SyncActivity.class);
                startActivity(syncIntent);

                break;
        }

        return super.onOptionsItemSelected(item);


    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_new:
                fragment = new TestFragment();
                toolbar.setTitle(TITLE);
                loadFragment(fragment);
                Log.d("testing", "After loadFragment");
                break;
            case R.id.nav_task:
                startNewActivity(TaskListActivity.class);
                break;
            case R.id.nav_lead_entry:
                startNewActivity(LeadListActivity.class);
                break;
            case R.id.nav_account:
                startNewActivity(AccountListActivity.class);
                break;
            case R.id.nav_deal:
                startNewActivity(DealListActivity.class);
                break;
            case R.id.nav_calls:
                startNewActivity(CallListActivity.class);
                break;
            case R.id.nav_contact:
                startNewActivity(ContactListActivity.class);
                break;
            case R.id.nav_budget:
                startNewActivity(AddSalesBudgetListActivity.class);
                break;
            case R.id.nav_event:
                startNewActivity(EventListActivity.class);
                break;
            // todo we have to this after ......
            //case R.id.nav_promotion_mail:
            case R.id.nav_customer_challenge:
                startNewActivity(CustomerChallengeListActivity.class);
                break;
            case R.id.nav_contact_us:
                startNewActivity(ContactUs.class);
                break;
            case R.id.nav_about_us:
                startNewActivity(AboutUs.class);
                break;
            case R.id.nav_logout:
                logoutApplication();


        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logoutApplication() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(getResources().getString(R.string.msg_logout)).setCancelable(false).setPositiveButton(getResources().getString(R.string.btn_alert_dialog_ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                new DataBaseAdapter(EmployeeHomeActivity.this).dropCRMTable();
                sessionManager.logoutUser();
                finish();
            }
        }).setNegativeButton(getResources().getString(R.string.btn_alert_dialog_cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //  Action for 'NO' Button
                dialog.cancel();
            }
        });


        AlertDialog alert = builder.create();
        alert.show();


    }


    public void startNewActivity(Class<?> cls) {
        Intent intent = new Intent(EmployeeHomeActivity.this, cls);
        startActivity(intent);
        drawer.closeDrawers();

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.layout_track_cargo:
                startNewActivity(TrackCargoActivity.class);
                break;
            case R.id.layout_sailing_schedule:
                startNewActivity(SailingScheduleActivity.class);
                break;
            case R.id.layout_get_quotation:
                startNewActivity(GetQuotationActivity.class);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        sessionManager.setFirstEmployeeLogin();

    }
}
