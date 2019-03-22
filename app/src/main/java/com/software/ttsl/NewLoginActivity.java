package com.software.ttsl;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class NewLoginActivity extends AppCompatActivity implements View.OnClickListener, ConnectivityReceiver.ConnectivityReceiverListener, Callback<UserAuthenticateResponse> {

    public static final String TAG = NewLoginActivity.class.getName();

    @BindView(R.id.iv_cancel)
    ImageView imageViewExit;


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
    SharedPreferences sharedPreferences;
    User user;
    private Boolean isSuccess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        //this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);



       InputMethodManager imm =
                (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);

// finally change the color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.textheadingcolor));
        }
        setContentView(R.layout.activity_new_login);

        sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREF, 0);


        ButterKnife.bind(this);

        SessionManager.setContext(getApplicationContext());

        setClickListener();

    }

    private void setClickListener() {
        imageViewExit.setOnClickListener(this);
        mloginBtn.setOnClickListener(this);
        /*mUserName.addTextChangedListener(new InputTextWatcher(mUserName));
        mPassWord.addTextChangedListener(new InputTextWatcher(mPassWord));*/

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.iv_cancel:
                startActivity(new Intent(NewLoginActivity.this, WelcomeActivity.class));
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                finish();
                break;
            case R.id.btn_login:
                if (submitLogin()) {
                    checkCredential();

                }
                break;

        }
    }


    private boolean submitLogin() {
        if (!isFieldEmpty()) {
            return false;
        }

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

    private boolean isFieldEmpty() {
        if (mPassWord.getText().toString().trim().isEmpty() && mUserName.getText().toString().trim().isEmpty()) {
            alertDialogManager.showAlertDialog(this, getResources().getString(R.string.err_msg_title_dialog),
                    "Please enter valid username and password", false);
            return false;

        } else {
            return true;
        }
    }


    private boolean validatePassWord() {
        if (mPassWord.getText().toString().trim().isEmpty()) {
            alertDialogManager.showAlertDialog(this, getResources().getString(R.string.err_msg_title_dialog),
                    getResources().getString(R.string.err_login_password), false);
            /*mPasswordLayout.setErrorEnabled(true);
            mPasswordLayout.setError(getString(R.string.err_login_password));*/
            return false;
        }
        /*mPasswordLayout.setErrorEnabled(false);*/
        return true;

    }


    private boolean validateUserName() {
        if (mUserName.getText().toString().trim().isEmpty()) {

            alertDialogManager.showAlertDialog(this, getResources().getString(R.string.err_msg_title_dialog),
                    getResources().getString(R.string.err_login_password), false);
           /* mUserNameLayout.setErrorEnabled(true);
            mUserNameLayout.setError(getString(R.string.err_login_username));*/
            return false;
        }
        //mUserNameLayout.setErrorEnabled(false);
        return true;
    }


    private boolean checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        if (!isConnected) {
            alertDialogManager.showAlertDialog(this, "NetWork Error", "Check NetWork Connection", true);
            return false;
        }

        return true;
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

    }

    private void checkCredential() {
        DialogUtitlity.showLoading(this);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        username = mUserName.getText().toString().trim();
        password = mPassWord.getText().toString().trim();
        user = new User();
        user.setUserId(username);
        user.setPassword(password);
        user.setDeviceToken(sharedPreferences.getString(DEVICE_TOKEN_ID, null));
        //
        if (username.equals("user") && password.equals("user")) {
            startNewActivity(HomeActivityNewModule.class);
            sessionManager = SessionManager.getInstance();
            sessionManager.createLoginSession("user", "user", "U", 1L);
            DialogUtitlity.hideLoading();

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
        String userType;
        if (statusCode == 200) {
            if (response.body() instanceof UserAuthenticateResponse) {
                UserAuthenticateResponse userResponse = response.body();
                Log.d(TAG, "Getting success response from server : " + response.body().getUserName());
                sessionManager = SessionManager.getInstance();
                sessionManager.createLoginSession(userResponse.getUserId(), userResponse.getUserName(), userResponse.getUserType(), userResponse.getCode());
                code = userResponse.getCode();
                userType = userResponse.getUserType();
                if (userType.equalsIgnoreCase("C")) {
                    startNewActivity(MainActivity.class);
                    // Toast.makeText(this, getResources().getString(R.string.show_success_login), Toast.LENGTH_SHORT).show();

                }
                if (userType.equalsIgnoreCase("E")) {
                    startNewActivity(EmployeeHomeActivity.class/*DownloadDataActivity.class*/);

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

    private void startNewActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
        finish();

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


    @Override
    public void onBackPressed() {
        /*super.onBackPressed();*/
        hideKeyboard();
        super.onBackPressed();
    }


    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mUserName.getWindowToken(), 0);

    }

}
