package com.software.ttsl;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.software.ttsl.Response.PendingInvoiceResponce;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.DialogUtitlity;
import com.software.ttsl.Utils.SessionManager;
import com.software.ttsl.model.BillListData;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener

{
    private static final String TAG = MainActivity.class.getName();
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_USER_NAME = "user_name";
    public static  final String KEY_USER_TYPE="user_type";

    @BindView(R.id.layout_logout)
    LinearLayout mLogout;

    @BindView(R.id.layout_track_cargo)
    LinearLayout mTrackCargo;

    @BindView(R.id.layout_pending_invoice)
    LinearLayout mPendingInvoice;

    @BindView(R.id.layout_sailing_schedule)
    LinearLayout mSailingSchedule;

    @BindView(R.id.layout_getquotation)
    LinearLayout mGetQuotation;

    @BindView(R.id.layout_billList)
    LinearLayout mBillList;

    @BindView(R.id.layout_profile)
    LinearLayout mProfile;

    @BindView(R.id.layout_contact_us)
    LinearLayout mContactUs;

    @BindView(R.id.layout_about_us)
    LinearLayout mAboutUs;

    SessionManager sessionManager;

    private HashMap<String,String> userDetailsMap ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        SessionManager.setContext(this);
        sessionManager = SessionManager.getInstance();
        userDetailsMap = sessionManager.getUserDetails();


        getPendingInvoice();

        getBLList();



        setClickListener();
    }

    private void getBLList() {
        DialogUtitlity.showLoading(MainActivity.this);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<BillListData>> listCall = apiInterface.getBLLists(userDetailsMap.get(KEY_USER_TYPE),userDetailsMap.get(KEY_USER_ID));
        listCall.enqueue(new Callback<List<BillListData>>() {
            @Override
            public void onResponse(Call<List<BillListData>> call, Response<List<BillListData>> response) {
                DialogUtitlity.hideLoading();
                int statusCode = response.code();
                if(statusCode==200){
                    if(response.body() instanceof  List){
                        List<BillListData> billOfLadingList= response.body();
                        DataBaseAdapter dataBaseAdapter = new DataBaseAdapter(MainActivity.this);
                        dataBaseAdapter.openDataBase();
                        dataBaseAdapter.addBillOfLadingList(billOfLadingList);

                    }

                }
            }

            @Override
            public void onFailure(Call<List<BillListData>> call, Throwable t) {
                DialogUtitlity.hideLoading();

            }
        });
    }


    private void getPendingInvoice() {
        DialogUtitlity.showLoading(MainActivity.this);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<PendingInvoiceResponce>> listCall = apiInterface.getPendingInvoices(userDetailsMap.get(KEY_USER_NAME));
        listCall.enqueue(new Callback<List<PendingInvoiceResponce>>() {
            @Override
            public void onResponse(Call<List<PendingInvoiceResponce>> call, Response<List<PendingInvoiceResponce>> response) {
                DialogUtitlity.hideLoading();
                int statusCode = response.code();
                if(statusCode==200){
                    if(response.body() instanceof  List){
                        List<PendingInvoiceResponce> pendingInvoiceResponceList = response.body();
                        DataBaseAdapter dataBaseAdapter = new DataBaseAdapter(MainActivity.this);
                        dataBaseAdapter.openDataBase();
                        dataBaseAdapter.addPendingInvoices(pendingInvoiceResponceList);

                    }

                }
            }

            @Override
            public void onFailure(Call<List<PendingInvoiceResponce>> call, Throwable t) {
                DialogUtitlity.hideLoading();

            }
        });
    }

    private void setClickListener() {
        mLogout.setOnClickListener(this);
        mAboutUs.setOnClickListener(this);
        mSailingSchedule.setOnClickListener(this);
        mPendingInvoice.setOnClickListener(this);
        mBillList.setOnClickListener(this);
        mProfile.setOnClickListener(this);
        mGetQuotation.setOnClickListener(this);
        mTrackCargo.setOnClickListener(this);
        mContactUs.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.layout_logout:
                logoutApplication();
                break;
            case R.id.layout_track_cargo:
                startNewActivity(TrackCargoActivity.class);
                break;
            case R.id.layout_billList:
                startNewActivity(BillListActivity.class);
                break;
            case R.id.layout_pending_invoice:
                startNewActivity(PendingInvoiceActivity.class);
                break;
            case R.id.layout_about_us:
                startNewActivity(AboutUs.class);
                break;
            case R.id.layout_contact_us:
                startNewActivity(ContactUs.class);
                break;
            case R.id.layout_profile:
                startNewActivity(ProfileActiviy.class);
                break;
            case R.id.layout_getquotation:
                startNewActivity(GetQuotationActivity.class);
                break;
            case R.id.layout_sailing_schedule:
                startNewActivity(SailingScheduleActivity.class);
                break;

        }
    }

    private void logoutApplication() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(getResources().getString(R.string.msg_logout_application)).setCancelable(false).setPositiveButton(getResources().getString(R.string.btn_alert_dialog_positave), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                sessionManager = SessionManager.getInstance();
                sessionManager.logoutUser();
                DataBaseAdapter dataBaseAdapter = new DataBaseAdapter(MainActivity.this);
                dataBaseAdapter.openDataBase();
                dataBaseAdapter.dropTables();
                finish();
            }
        }).setNegativeButton(getResources().getString(R.string.btn_alert_dialog_negetive), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                dialog.cancel();
            }
        });

        //Creating dialog box
        AlertDialog alert = builder.create();
        alert.show();







    }

    public void startNewActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
}
