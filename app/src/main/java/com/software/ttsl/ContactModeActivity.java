package com.software.ttsl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactModeActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_mail)
    TextView mailTextView;

    @BindView(R.id.tv_in_person)
    TextView inPersonTextView;


    @BindView(R.id.tv_phone)
    TextView phoneTextView;

    String contactMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_mode);

        ButterKnife.bind(this);
        setClickListener();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    private void setClickListener() {
           mailTextView.setOnClickListener(this);
           phoneTextView.setOnClickListener(this);
           inPersonTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.tv_phone:
             contactMode = phoneTextView.getText().toString().trim();
             backActivity(contactMode);
             break;
            case R.id.tv_in_person:
                contactMode =inPersonTextView.getText().toString().trim();
                backActivity(contactMode);
                break;
            case R.id.tv_mail:
                contactMode = mailTextView.getText().toString().trim();
                backActivity(contactMode);
                break;

        }
    }

    private void backActivity(String contactMode) {
        Intent intent = new Intent();
        intent.putExtra("editTextValue", contactMode);
        setResult(RESULT_OK, intent);
        finish();
    }
}
