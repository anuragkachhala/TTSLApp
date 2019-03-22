package com.software.ttsl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActiviy extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.et_username)
    EditText mUsername;

    @BindView(R.id.et_password)
    EditText mPassowrd;

    @BindView(R.id.et_email)
    EditText mEmail;

    @BindView(R.id.tv_username)
    TextView usernameTV;

    @BindView(R.id.tv_password)
    TextView passwordTV;

    @BindView(R.id.tv_email)
    TextView emailTV;

    @BindView(R.id.edit_username)
    ImageView usernameEdit;

    @BindView(R.id.edit_email)
    ImageView emailEdit;

    @BindView(R.id.edit_password)
    ImageView passwordEdit;

    @BindView(R.id.btn_save_profile)
    Button mSaveProfileBtn;

    String userName,password,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_activiy);
        ButterKnife.bind(this);

       setSupportActionBar(toolbar);
       getSupportActionBar().setHomeButtonEnabled(true);
       getSupportActionBar().setHomeButtonEnabled(true);

       setClickListener();
    }

    private void setClickListener() {
        usernameEdit.setOnClickListener(this);
        emailEdit.setOnClickListener(this);
        passwordEdit.setOnClickListener(this);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view) {
        int id =view.getId();
        switch (id){
            case R.id.edit_email:
                email = emailTV.getText().toString().trim();
                mEmail.setText(email);
                emailTV.setVisibility(View.GONE);
                mEmail.setVisibility(View.VISIBLE);
                emailEdit.setVisibility(View.GONE);
                break;
            case R.id.edit_password:
                password= passwordTV.getText().toString().trim();
                mPassowrd.setText(password);
                passwordTV.setVisibility(View.GONE);
                mPassowrd.setVisibility(View.VISIBLE);
                passwordEdit.setVisibility(View.GONE);
                break;
            case R.id.edit_username:
                userName = usernameTV.getText().toString().trim();
                mPassowrd.setText(userName);
                usernameTV.setVisibility(View.GONE);
                mUsername.setVisibility(View.VISIBLE);
                usernameEdit.setVisibility(View.GONE);
                break;
        }

    }
}
