package com.software.ttsl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.software.ttsl.Adapter.OfficeListAdapter;
import com.software.ttsl.Response.LoginResponse;
import com.software.ttsl.Response.OfficeListResponse;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Utils.SessionManager;
import com.ttspl.android.DownloadServerDataActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class  LoginActivityNew extends AppCompatActivity implements Callback<List<OfficeListResponse>>, AdapterView.OnItemSelectedListener, View.OnClickListener {
    private static final String TAG = LoginActivityNew.class.getName();


    @BindView(R.id.et_email)
    EditText editTextEmail;

    @BindView(R.id.et_password)
    EditText editTextPassword;

    @BindView(R.id.sp_office)
    Spinner spinnerOffice;

    @BindView(R.id.btn_login)
    Button buttonLogin;
    String token;
    private LoginResponse loginResponse;
    private List<OfficeListResponse> officeListResponseList;
    private OfficeListAdapter officeListAdapter;
    private Integer officeId = 1;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new);
        ButterKnife.bind(this);
        getOfficeList();
        SessionManager.setContext(getApplicationContext());
        sessionManager = SessionManager.getInstance();

        sessionManager.setFirstAPI(true);

        setListener();


    }

    private void setListener() {
        spinnerOffice.setOnItemSelectedListener(this);
        buttonLogin.setOnClickListener(this);
    }

    private void getOfficeList() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<OfficeListResponse>> listCall = apiInterface.getOfficesList();
        Log.i(TAG, "office List");
        listCall.enqueue(this);

    }

    @Override
    public void onResponse(Call<List<OfficeListResponse>> call, Response<List<OfficeListResponse>> response) {
        int statusCode = response.code();
        if (statusCode == 200) {
            if (response.body() instanceof List) {
                officeListResponseList = response.body();
                officeListAdapter = new OfficeListAdapter(this, officeListResponseList);
                spinnerOffice.setAdapter(officeListAdapter);
                sessionManager.setFirstAPI(false);

            }
        }
    }

    @Override
    public void onFailure(Call<List<OfficeListResponse>> call, Throwable t) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        officeId = officeListResponseList.get(position).getOfficeId();
        Toast.makeText(this, officeId + "", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                getAccessToken();
        }

    }

    private void getAccessToken() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> listCall = apiInterface.getLoginAccessToken(editTextEmail.getText().toString(), editTextPassword.getText().toString(), officeId);
        Log.i(TAG, "office List");
        listCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    if (response.body() instanceof String) {
                        token = response.body().replaceAll("^\"|\"$", "");;
                        Log.e(TAG, token);
                        sessionManager.setAccessToken(token);
                        getLoginResponse();

                    }
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }

    Intent intent;
    private void getLoginResponse() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<LoginResponse> listCall = apiInterface.getLoginResponse("Bearer "+sessionManager.getAccessToken());
        Log.i(TAG, "office List");
        listCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    if (response.body() instanceof LoginResponse) {
                        loginResponse = response.body();
                        Log.e(TAG, loginResponse.getMetaView().getRoleId()+"");
                        sessionManager.setLogin();
                         startActivity();
                    }
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }

    private void startActivity(){
        Intent intent = new Intent(this,DownloadServerDataActivity.class);
        startActivity(intent);
    }
}
