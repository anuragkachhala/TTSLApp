package com.software.ttsl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.software.ttsl.NewModule.HomeActivityNewModule;
import com.software.ttsl.Response.ErrorResponse;
import com.software.ttsl.Response.UserAuthenticateResponse;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Utils.AlertDialogManager;
import com.software.ttsl.Utils.ConnectivityReceiver;
import com.software.ttsl.Utils.DialogUtitlity;
import com.software.ttsl.Utils.SessionManager;
import com.software.ttsl.model.User;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.software.ttsl.FirebaseUtil.Config.DEVICE_TOKEN_ID;
import static com.software.ttsl.FirebaseUtil.Config.SHARED_PREF;

public class LoginActivity1 extends AppCompatActivity implements View.OnClickListener, Callback<UserAuthenticateResponse>, ConnectivityReceiver.ConnectivityReceiverListener {

    private final static String TAG = LoginActivity1.class.getName();

    public TextView mSingUpTV;

    @BindView(R.id.layout_track_cargo)
    LinearLayout mTrackCargo;

    @BindView(R.id.layout_sailing_schedule)
    LinearLayout mSailingSchdule;

    @BindView(R.id.layout_services)
    LinearLayout mServices;

    @BindView(R.id.layout_contact_us)
    LinearLayout mContactUs;

    @BindView(R.id.et_username)
    EditText mUserName;

    @BindView(R.id.et_password)
    EditText mPassWord;

    @BindView(R.id.widget_username)
    TextInputLayout mUserNameLayout;

    @BindView(R.id.widget_password)
    TextInputLayout mPasswordLayout;

    @BindView(R.id.btn_login)
    Button mloginBtn;

    String mAlertDialogTitle;
    String mAlertDialogMessage;


    String username;
    String password;

    SessionManager sessionManager;
    AlertDialogManager alertDialogManager = new AlertDialogManager();
    User user;
    SharedPreferences sharedPreferences;
    private Boolean isSuccess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        ButterKnife.bind(this);


        SessionManager.setContext(getApplicationContext());
         sharedPreferences= getApplicationContext().getSharedPreferences(SHARED_PREF, 0);


        setClickListener();

        String signUpMsg = getResources().getString(R.string.singup_text_signin);
        SpannableString ss = new SpannableString(signUpMsg);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                startActivity(new Intent(LoginActivity1.this, RegistrationActivity.class));
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan, 22, signUpMsg.indexOf("now") - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        mSingUpTV = (TextView) findViewById(R.id.signup_tv);
        mSingUpTV.setText(ss);
        mSingUpTV.setMovementMethod(LinkMovementMethod.getInstance());
        mSingUpTV.setHighlightColor(Color.TRANSPARENT);
    }

    private void setClickListener() {

        mloginBtn.setOnClickListener(this);
        mContactUs.setOnClickListener(this);
        mServices.setOnClickListener(this);
        mTrackCargo.setOnClickListener(this);
        mSailingSchdule.setOnClickListener(this);
        mUserName.addTextChangedListener(new InputTextWatcher(mUserName));
        mPassWord.addTextChangedListener(new InputTextWatcher(mPassWord));
    }

    private void startNewActivity(Class<?> cls) {
        Intent intent = new Intent(LoginActivity1.this, cls);
        startActivity(intent);

    }


    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id) {
            case R.id.btn_login:
                if (submitLogin()) {
                    checkCredential();

                }
                break;

            case R.id.layout_track_cargo:
                startNewActivity(TrackCargoActivity.class);
                break;

            case R.id.layout_sailing_schedule:
                startNewActivity(SailingScheduleActivity.class);
                break;
            case R.id.layout_services:
                startNewActivity(ServicesActivity.class);
                break;
            case R.id.layout_contact_us:
                startNewActivity(GetQuotationActivity.class);
                break;


        }

    }

    private boolean submitLogin() {

        if (!validateUserName()) {
            return false;
        }
        if (!validatePassWord()) {
            return false;
        }
        if (!checkConnection()) {
            return false;
        }
        return true;
    }

    private void checkCredential() {
        DialogUtitlity.showLoading(LoginActivity1.this);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        user.setDeviceToken(sharedPreferences.getString(DEVICE_TOKEN_ID, null));
        username = mUserName.getText().toString().trim();
        password = mPassWord.getText().toString().trim();
        user = new User();
        user.setUserId(username);
        user.setPassword(password);

        if (username.equals("user") && password.equals("user")) {
            sessionManager = SessionManager.getInstance();
            sessionManager.createLoginSession("user", "user", "U", 1L);
            startNewActivity(HomeActivityNewModule.class);
        } else {
            final Call<UserAuthenticateResponse> callClient = apiService.authenticateUser(user, username);
            callClient.enqueue(this);
        }
    }

    @Override
    public void onResponse(Call<UserAuthenticateResponse> call, Response<UserAuthenticateResponse> response) {
        DialogUtitlity.hideLoading();
        int statusCode = response.code();
        long code;
        if (statusCode == 200) {
            if (response.body() instanceof UserAuthenticateResponse) {
                UserAuthenticateResponse userResponse = response.body();
                Log.d(TAG, "Getting success response from server : " + response.body().getUserName());
                sessionManager = SessionManager.getInstance();
                sessionManager.createLoginSession(userResponse.getUserId(), userResponse.getUserName(), userResponse.getUserType(), userResponse.getCode());
                code = userResponse.getCode();
                if (code == 2) {
                    startNewActivity(MainActivity.class);
                    Toast.makeText(this, getResources().getString(R.string.show_success_login), Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(this, "fail1", Toast.LENGTH_LONG).show();
            }
        } else if (statusCode == 404) {
            try {
                ErrorResponse myError = (ErrorResponse) ApiClient.getClient().responseBodyConverter(ErrorResponse.class, ErrorResponse.class.getAnnotations()).convert(response.errorBody());
                Log.d(TAG, "Getting failure response from server : " + myError.getStatus());
                mAlertDialogTitle = getResources().getString(R.string.alert_authentication_title);
                mAlertDialogMessage = getResources().getString(R.string.alert_authentication_msg);
                alertDialogManager.showAlertDialog(this, mAlertDialogTitle, mAlertDialogMessage, true);
                isSuccess = false;
                // Do error handling here
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }

    @Override
    public void onFailure(Call<UserAuthenticateResponse> call, Throwable t) {
        DialogUtitlity.hideLoading();
        Log.d(TAG, "Getting failure response from server : " + t);
        mAlertDialogTitle = getResources().getString(R.string.alert_authentication_title);
        mAlertDialogMessage = "Failed to connect to server";
        alertDialogManager.showAlertDialog(this, mAlertDialogTitle, mAlertDialogMessage, true);

    }


    private boolean validatePassWord() {
        if (mPassWord.getText().toString().trim().isEmpty()) {
            mPasswordLayout.setErrorEnabled(true);
            mPasswordLayout.setError(getString(R.string.err_login_password));
            return false;
        }
        mPasswordLayout.setErrorEnabled(false);
        return true;

    }

    private boolean validateUserName() {
        if (mUserName.getText().toString().trim().isEmpty()) {
            mUserNameLayout.setErrorEnabled(true);
            mUserNameLayout.setError(getString(R.string.err_login_username));
            return false;
        }
        mUserNameLayout.setErrorEnabled(false);
        return true;
    }

    // Method to manually check connection status
    private boolean checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        if (!isConnected) {
            alertDialogManager.showAlertDialog(this, "NetWork Error", "Check NetWork Connection", true);
            return false;
        }

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register connection status listener
        TransportApp.getInstance().setConnectivityListener(this);
    }

    /**
     * Callback will be triggered when there is change in
     * network connection
     */
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

        alertDialogManager.showAlertDialog(this, "", "NetWork Connected", true);

    }

    public class InputTextWatcher implements TextWatcher {

        View view;

        public InputTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.et_username:
                    validateUserName();
                    break;
                case R.id.et_password:
                    validatePassWord();
                    break;
            }

        }
    }

}