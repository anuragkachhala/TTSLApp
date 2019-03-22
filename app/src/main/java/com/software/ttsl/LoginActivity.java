package com.software.ttsl;

import android.app.ProgressDialog;
import android.content.Intent;
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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.software.ttsl.Response.UserAuthenticateResponse;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Utils.AlertDialogManager;
import com.software.ttsl.Utils.ConnectivityReceiver;
import com.software.ttsl.Utils.SessionManager;
import com.software.ttsl.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ConnectivityReceiver.ConnectivityReceiverListener {

    private static final String TAG = LoginActivity.class.getSimpleName();

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

    private ProgressDialog pDialog;


    String username;
    String password;

    String mAlertDialogTitle;
    String mAlertDialogMessage;

    SessionManager sessionManager;
    AlertDialogManager alertDialogManager = new AlertDialogManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);


        ButterKnife.bind(this);


        SessionManager.setContext(getApplicationContext());

        setClickListener();


        String signUpMsg = getResources().getString(R.string.singup_text_signin);
        SpannableString ss = new SpannableString(signUpMsg);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
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
        Intent intent = new Intent(LoginActivity.this, cls);
        startActivity(intent);

    }




    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_login:
                if (submitLogin()) {
                    startNewActivity(MainActivity.class);
                    finish();

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
                startNewActivity(ContactUs.class);
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
        if (!checkCredential()) {
            return false;
        }
        return true;
    }

    private boolean checkCredential() {

       // checkConnection();

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        username = mUserName.getText().toString().trim();
        password = mPassWord.getText().toString().trim();
        User user = new User();
        user.setUserId(username);
        user.setPassword(password);
        final Call<UserAuthenticateResponse> response = apiService.authenticateUser(user,username);

        response.enqueue(new Callback<UserAuthenticateResponse>() {
            @Override
            public void onResponse(Call<UserAuthenticateResponse> call, Response<UserAuthenticateResponse> response) {


                String userName=  response.body().getUserName();



                /*sessionManager.createLoginSession("","","");*/
                startNewActivity(EmployeeHomeActivity.class);

            }

            @Override
            public void onFailure(Call<UserAuthenticateResponse> call, Throwable t) {




            }
        });

        if (username.equals("admin") && password.equals("admin")) {

            //sessionManager.createLoginSession("admin", "admin");
            return true;

        } else {

            mAlertDialogTitle = getResources().getString(R.string.alert_authentication_title);
            mAlertDialogMessage =getResources().getString(R.string.alert_authentication_msg);

            alertDialogManager.showAlertDialog(this, mAlertDialogTitle, mAlertDialogMessage, true);
            return false;
        }


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


    // Method to manually check connection status
    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        alertDialogManager.showAlertDialog(this, mAlertDialogTitle, "Sorry! Not connected to internet", true);


        //showSnack(isConnected);
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
        //showSnack(isConnected);
    }


   /* private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }*/
}
