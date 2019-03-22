package com.software.ttsl.NewModule;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import com.software.ttsl.R;
import com.software.ttsl.Utils.AlertDialogManager;
import com.software.ttsl.Utils.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivityNewModule extends AppCompatActivity {

    private TextView mTextMessage;

    @BindView(R.id.toolbar)
    Toolbar toolbar;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_track_cargo:
                   loadFragment(new TrackCargoFragment());
                    toolbar.setTitle(R.string.title_track_cargo);
                    return true;
                case R.id.navigation_sail_schedule:
                    toolbar.setTitle(R.string.title_sail_schedule);
                    loadFragment(new SailScheduleFragment());
                    return true;
                case R.id.navigation_agent_info:
                    toolbar.setTitle(R.string.title_agent_info);
                    loadFragment(new AgentInfoFragment());


                    return true;
            }
            return false;
        }
    };

    AlertDialogManager alertDialogManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_actvity_new_module);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        alertDialogManager = new AlertDialogManager();
        loadFragment(new TrackCargoFragment());
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nevigation_home_new_module, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_log_out:

                 logoutApplication();

                break;
        }

        return super.onOptionsItemSelected(item);


    }


    private void logoutApplication() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Are you sure to logout this application ?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                SessionManager.getInstance().logoutUser();
                finish();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //  Action for 'NO' Button
                dialog.cancel();
            }
        });


        AlertDialog alert = builder.create();
        alert.show();


    }


}
