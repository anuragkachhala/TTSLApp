package com.software.ttsl;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class ContactUs extends AppCompatActivity {

    public TextView mEmailTV, mPhoneNoTV;
    public Toolbar mToolbar;
    public String mContactEmail, mPhoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        Initialize();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    private void Initialize() {
        mEmailTV = (TextView) findViewById(R.id.email_tv);
        mPhoneNoTV = (TextView) findViewById(R.id.phoneNo_tv);
        mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
    }

    /*public void PhoneCall(View view) {
        mPhoneNo = mPhoneNoTV.getText().toString().replaceAll("-", "");
        Intent phoneCallIntent = new Intent(Intent.ACTION_CALL);
        phoneCallIntent.setData(Uri.parse("tel:" +mPhoneNo));
       // phoneCallIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(phoneCallIntent);
    }

    public void SendMail(View view) {
        mContactEmail = mEmailTV.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { mContactEmail });
        //intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
        //intent.putExtra(Intent.EXTRA_TEXT, "mail body");
        startActivity(Intent.createChooser(intent, ""));

    }*/

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
