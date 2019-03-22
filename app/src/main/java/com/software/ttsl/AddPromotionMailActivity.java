package com.software.ttsl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.software.ttsl.Request.PromotionMailDataModel;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.AlertDialogManager;
import com.software.ttsl.Utils.SessionManager;
import com.software.ttsl.Utils.SharedPrefUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddPromotionMailActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = AddPromotionMailActivity.class.getName();



    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.et_mail_from)
    EditText editTextMailFrom;

    @BindView(R.id.et_mail_to)
    EditText editTextMailTo;

    @BindView(R.id.et_mail_commercial)
    EditText editTextCommercial;

    @BindView(R.id.et_mail_send_by)
    EditText editTextEmailSendBy;

    @BindView(R.id.et_mail_subject)
    EditText editTextMailSubject;

    @BindView(R.id.et_mail_country)
    EditText editTextMailCountry;

    @BindView(R.id.et_mail_attachment)
    EditText editTextMailAttachment;

    @BindView(R.id.et_mail_body)
    EditText editTextMailBody;

    @BindView(R.id.et_mail_category)
    EditText editTextMailCategory;


    private PromotionMailDataModel promotionMailDataModel;
    private AlertDialogManager alertDialogManager;
    private SharedPrefUtil sharedPrefUtil;
    private DataBaseAdapter dataBaseAdapter;
    private Intent intent;
    private String[] itemList;
    private String title,errorTitle;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_promotion_mail);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        SessionManager.setContext(getApplicationContext());

        alertDialogManager = new AlertDialogManager();
        dataBaseAdapter = new DataBaseAdapter(this);

        setClickListener();
        setStringResource();




    }

    private void setStringResource() {


    }

    private void setClickListener() {
        editTextCommercial.setOnClickListener(this);
        editTextEmailSendBy.setOnClickListener(this);
        editTextMailCountry.setOnClickListener(this);
        editTextMailCategory.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.et_mail_commercial:
                break;
            case R.id.et_mail_send_by:
                break;
            case R.id.et_mail_country:
                break;
            case R.id.et_mail_category:
                break;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.add_task:

               /* if (setLeadData()) {
                    addLeadData();

                }*/
                if (checkValidation())
                    addPromotionMail();

                break;
        }

        return super.onOptionsItemSelected(item);


    }

    private void addPromotionMail() {
    }

    private boolean checkValidation() {

        return true;
    }


    public void showErrorDialog(String errMessage) {
        alertDialogManager.showAlertDialog(this, errorTitle, errMessage, false);

    }
}
