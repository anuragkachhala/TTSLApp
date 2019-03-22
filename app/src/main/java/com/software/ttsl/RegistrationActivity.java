package com.software.ttsl;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {

    public static final String TAG  =RegistrationActivity.class.getName();
    TextView mSignInTV;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        String hello = getResources().getString(R.string.signin_text_signup);
        SpannableString ss = new SpannableString(hello);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan, hello.indexOf("Sign in"),hello.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        mSignInTV= (TextView) findViewById(R.id.signup_tv);
        mSignInTV.setText(ss);
        mSignInTV.setMovementMethod(LinkMovementMethod.getInstance());
        mSignInTV.setHighlightColor(Color.TRANSPARENT);
    }
}


