package com.software.ttsl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public  abstract  class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initialiseView();
        super.onCreate(savedInstanceState);
    }

    public abstract void initialiseView();

    public abstract void showNextScreen();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setUpToolBar(Toolbar toolBar) {
        setSupportActionBar(toolBar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    public void setUpToolBar(Toolbar toolBar, String title) {
        setUpToolBar(toolBar);
        toolBar.setTitle(title);
    }
}
