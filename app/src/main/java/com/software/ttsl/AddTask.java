package com.software.ttsl;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AddTask extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.widget_start_date)
    TextInputLayout mStartDateTextInputLayout;

    @BindView(R.id.et_start_date)
    EditText mStartDateEditText;

    @BindView(R.id.widget_due_date)
    TextInputLayout mDueDateTextInputLayout;

    @BindView(R.id.et_due_date)
    TextInputEditText mDueDateEditText;

    @BindView(R.id.widget_completion_date)
    TextInputLayout mCompletionDateInputTextLayout;

    @BindView(R.id.et_completion_date)
    TextInputEditText mCompletionDateEditText;

    @BindView(R.id.widget_contact_mode)
    TextInputLayout mContactModeInputTextLayout;

    @BindView(R.id.et_contact_mode)
    EditText mContactModeEditText;


    int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task2);

        ButterKnife.bind(this);

        setClickListener();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void setClickListener() {
        mStartDateTextInputLayout.setOnClickListener(this);
        mDueDateTextInputLayout.setOnClickListener(this);
        mCompletionDateInputTextLayout.setOnClickListener(this);
        mStartDateEditText.setOnClickListener(this);
        mDueDateEditText.setOnClickListener(this);
        mCompletionDateEditText.setOnClickListener(this);
        mContactModeInputTextLayout.setOnClickListener(this);
        mContactModeEditText.setOnClickListener(this);

    }

    public void setDate(View view) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_task,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.widget_start_date:{
                openCalenderDialog(view);
                break;
            }
            case R.id.et_start_date:{
                openCalenderDialog(view);
                break;
            }
            case R.id.widget_due_date:{
                openCalenderDialog(view);
                break;
            }
            case R.id.et_due_date:{
                openCalenderDialog(view);
                break;
            }
            case R.id.widget_completion_date:{
                openCalenderDialog(view);
                break;
            }
            case R.id.et_completion_date:{
                openCalenderDialog(view);
                break;
            }case R.id.widget_contact_mode:{
                Intent i =new Intent(AddTask.this,ContactModeActivity.class);
                startActivityForResult(i, 1);
                overridePendingTransition(R.anim.anim_enter, R.anim.amin_exit);
                break;
            }case R.id.et_contact_mode:{
                Intent i =new Intent(AddTask.this,ContactModeActivity.class);
                startActivityForResult(i, 1);
                overridePendingTransition(R.anim.anim_enter, R.anim.amin_exit);
                break;
            }

        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String strEditText = data.getStringExtra("editTextValue");
                mContactModeEditText.setText(strEditText);
            }
        }
    }

    public void openCalenderDialog(final View view)  {

        String date = null;
        if(!mStartDateEditText.getText().toString().isEmpty()){
             date =mStartDateEditText.getText().toString().trim();
            Date selectedDate = convetStringToDate(date);
        }

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


         DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker dateView, int year, int monthOfYear, int dayOfMonth) {
                 String date = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                 if (view.getId() == R.id.widget_start_date || view.getId()==R.id.et_start_date) {

                    mStartDateEditText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);


                } else if (view.getId() == R.id.widget_due_date|| view.getId() == R.id.et_due_date) {
                    mDueDateEditText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                } else if(view.getId() ==R.id.widget_completion_date|| view.getId()==R.id.et_completion_date){
                     mCompletionDateEditText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                 }

            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();


    }

    private Date convetStringToDate(String date) {
        Date date1= null;
        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }
}
