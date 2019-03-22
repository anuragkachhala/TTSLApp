package com.software.ttsl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.software.ttsl.NewModule.HomeActivityNewModule;
import com.software.ttsl.Response.PortDataResponse;
import com.software.ttsl.Response.TrackingNoConstraintResponse;
import com.software.ttsl.Response.VesselListResponse;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.AlertDialogManager;
import com.software.ttsl.Utils.ConnectivityReceiver;
import com.software.ttsl.Utils.SessionManager;
import com.software.ttsl.Utils.SingletonTrackingConstraint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SplashScreenActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {

    public static final String TAG = SplashScreenActivity.class.getName();

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    AlertDialogManager alertDialogManager = new AlertDialogManager();
    DataBaseAdapter dataBaseAdapter;
    List<TrackingNoConstraintResponse> trackingNoConstraintResponses = new ArrayList<>();
    boolean firstRun;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
        SessionManager.setContext(getApplicationContext());
        sessionManager = SessionManager.getInstance();
        TransportApp.getInstance().setConnectivityListener(this);
        dataBaseAdapter = new DataBaseAdapter(SplashScreenActivity.this);
        firstRun = sessionManager.isFirstRun();
        Log.d(TAG, "First Run Value " + firstRun);
        checkFirstRun(firstRun);


    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (isConnected) {
            sessionManager.setFirstRun();
            Log.e(TAG, "Network is connected ");
            Toast.makeText(this, "Network is connected" + isConnected, Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.VISIBLE);
            if(firstRun) {
                getPortData();
            }
        }


    }




    private void addVesselList() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<VesselListResponse>> listCall = apiInterface.getVesselList();
        listCall.enqueue(new Callback<List<VesselListResponse>>() {
            @Override
            public void onResponse(Call<List<VesselListResponse>> call, Response<List<VesselListResponse>> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    if (response.body() instanceof List) {
                        List<VesselListResponse> vesselListResponseList = response.body();
                        DataBaseAdapter dataBaseAdapter = new DataBaseAdapter(SplashScreenActivity.this);
                        dataBaseAdapter.openDataBase();
                        dataBaseAdapter.addVessels(vesselListResponseList);
                        progressBar.setVisibility(View.GONE);
                        startNewActivity(WelcomeActivity.class);

                    }

                }
            }

            @Override
            public void onFailure(Call<List<VesselListResponse>> call, Throwable t) {


            }
        });

    }


    private void getPortData() {
        ApiInterface apiServices = ApiClient.getClient().create(ApiInterface.class);
        Call<Map<String, List<PortDataResponse>>> portDataResponse = apiServices.getPortData();
        portDataResponse.enqueue(new Callback<Map<String, List<PortDataResponse>>>() {
            @Override
            public void onResponse(Call<Map<String, List<PortDataResponse>>> call, Response<Map<String, List<PortDataResponse>>> response) {

                int statusCode = response.code();
                if (response.body() instanceof Map) {
                    Map<String, List<PortDataResponse>> listPortData = response.body();
                    DataBaseAdapter dataBaseAdapter = new DataBaseAdapter(SplashScreenActivity.this);
                    dataBaseAdapter.openDataBase();
                    dataBaseAdapter.addPortData(listPortData);
                    addVesselList();
                }

            }

            @Override
            public void onFailure(Call<Map<String, List<PortDataResponse>>> call, Throwable t) {


            }
        });
    }


    public void startNewActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
        finish();
    }


    private boolean checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        if (!isConnected) {

            alertDialogManager.showAlertDialog(this, "NetWork Error", "Check NetWork Connection", true);
            return false;
        }

        return true;
    }

    private void checkFirstRun(boolean firstRun) {
        if (firstRun)//if running for first time

        {


            Log.d(TAG, "Inside if(first run)" + firstRun);


            if (checkConnection())


            {

                Log.d(TAG, " Inside if( check Connection)" + checkConnection());
                sessionManager.setFirstRun();
                progressBar.setVisibility(View.VISIBLE);
                getPortData();


            }
        } else {


            Log.d(TAG, "Inside else first run ");
            trackingNoConstraintResponses.addAll(dataBaseAdapter.getTrackingNoConstatraint());
            HashMap<String, TrackingNoConstraintResponse> constraintHashMap = SingletonTrackingConstraint.getInstance().getHashMap();
            for (TrackingNoConstraintResponse noConstraintResponse : trackingNoConstraintResponses) {
                constraintHashMap.put(noConstraintResponse.getTrackType(), noConstraintResponse);


            }

            boolean isLoggedIn = sessionManager.isLoggedIn();
            Log.d(TAG, "Value of is Logged In " + isLoggedIn);


            if (isLoggedIn) {


                Log.d(TAG, " Inside if(IsLoggedIn) true " + isLoggedIn);
                if (sessionManager.isEmployee()) {

                    Log.d(TAG, " Inside if(isEmployee) true  start EmployeeHome");
                    startNewActivity(EmployeeHomeActivity.class);
                    return;
                } else if (sessionManager.isUser()) {

                    Log.d(TAG, " Inside if(isUser) true  start UserHome");
                    startNewActivity(HomeActivityNewModule.class);
                    return;
                } else
                    Log.d(TAG, " Inside if(Admin) false start MainActivity ");
                startNewActivity(MainActivity.class);

            } else {

                Log.d(TAG, "Inside else(if is login)= true start WelcomeActivity");
                startNewActivity(WelcomeActivity.class);

            }
        }
    }

     /*private class LogoLuncher extends Thread
    {

        public void run(){

            try{

               sleep(1000*SLEEP_TIMER);

            }catch (InterruptedException e)
            {
                e.printStackTrace();

            }
            Log.d(TAG, "Inside LogoLuncher start WelcomeActivity");

            // startNewActivity(WelcomeActivity.class);
            getPortData();

        }
    }
*/
}
