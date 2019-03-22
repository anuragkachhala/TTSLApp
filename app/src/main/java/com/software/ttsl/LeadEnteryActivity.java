package com.software.ttsl;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeadEnteryActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    CalendarView calendarView;
    Button /*previousyr, latteryr, datepick, prmonth, latmonth*/ selectDate;
    String selectedDate;
    TextView selectdatetv;
    int mYear,mMonth,mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead_entery);


        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        //calendarView = (CalendarView) findViewById(R.id.calendar);
        /*previousyr = (Button) findViewById(R.id.priviousyr);
        latteryr = (Button) findViewById(R.id.latteryr);
        datepick = (Button) findViewById(R.id.datepick);
        prmonth = (Button) findViewById(R.id.premonth);
        latmonth = (Button) findViewById(R.id.lattermonth);*/
        selectDate=(Button) findViewById(R.id.selectdate);
        selectdatetv=(TextView) findViewById(R.id.selectdateTv);
       /* previousyr.setOnClickListener(this);
        latteryr.setOnClickListener(this);
        datepick.setOnClickListener(this);*/
        //calendarView.setOnDateChangeListener(this);
        selectDate.setOnClickListener(this);
        /*prmonth.setOnClickListener(this);
        latmonth.setOnClickListener(this);*/
        Calendar c = Calendar.getInstance();
        SimpleDateFormat ss = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        selectedDate = ss.format(date);
        float scalingFactor = 0.8f; // scale down to half the size
        //calendarView.setScaleX(scalingFactor);
        //calendarView.setScaleY(scalingFactor);

    }


    @Override
    public void onClick(View view) {
         switch (view.getId()) {
             /*case R.id.priviousyr: {
                 long datelong = calendarView.getDate();
                 SimpleDateFormat ss = new SimpleDateFormat("dd/MM/yyyy");
                 Date date1 = new Date(datelong);
                 DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
                 selectedDate = ss.format(date1);
                 String parts[] = selectedDate.split("/");

                 int day = Integer.parseInt(parts[0]);
                 int month = Integer.parseInt(parts[1])-13;
                 int year = Integer.parseInt(parts[2]) ;
                 Calendar calendar = Calendar.getInstance();
                 calendar.set(Calendar.YEAR, year);
                 calendar.set(Calendar.MONTH, month);
                 calendar.set(Calendar.DAY_OF_MONTH, day);
                 long milliTime = calendar.getTimeInMillis();
                 calendarView.setDate(milliTime, true, true);
                 break;
             }


             case R.id.latteryr: {
                 long datelong = calendarView.getDate();
                 SimpleDateFormat ss = new SimpleDateFormat("dd/MM/yyyy");
                 Date date1 = new Date(datelong);
                 selectedDate = ss.format(date1);
                 String parts[] = selectedDate.split("/");
                 int day = Integer.parseInt(parts[0]);
                 int month = Integer.parseInt(parts[1])+11;
                 int year = Integer.parseInt(parts[2]) ;
                 Calendar calendar = Calendar.getInstance();
                 calendar.set(Calendar.YEAR, year);
                 calendar.set(Calendar.MONTH, month);
                 calendar.set(Calendar.DAY_OF_MONTH, day);
                 long milliTime = calendar.getTimeInMillis();
                 calendarView.setDate(milliTime, true, true);
                 break;
             }

             case R.id.datepick :{
                 Calendar calendar = Calendar.getInstance();
                 long milliTime = calendar.getTimeInMillis();
                 calendarView.setDate(milliTime, true, true);
                    break;

             }
             case R.id.lattermonth :
                 {
                 long datelong = calendarView.getDate();
                 SimpleDateFormat ss = new SimpleDateFormat("dd/MM/yyyy");
                 Date date1 = new Date(datelong);
                 selectedDate = ss.format(date1);
                 String parts[] = selectedDate.split("/");
                 int day = Integer.parseInt(parts[0]);
                 int month = Integer.parseInt(parts[1]) + 0;
                 int year = Integer.parseInt(parts[2]);
                 Calendar calendar = Calendar.getInstance();
                 calendar.set(Calendar.YEAR, year);
                 calendar.set(Calendar.MONTH, month);
                 calendar.set(Calendar.DAY_OF_MONTH, day);
                 long milliTime = calendar.getTimeInMillis();
                 calendarView.setDate(milliTime, true, true);
                 break;

             }
             case R.id.premonth :{
                 long datelong = calendarView.getDate();
                 SimpleDateFormat ss = new SimpleDateFormat("dd/MM/yyyy");
                 Date date1 = new Date(datelong);
                 selectedDate = ss.format(date1);
                 String parts[] = selectedDate.split("/");
                 int day = Integer.parseInt(parts[0]);
                 int month = Integer.parseInt(parts[1])-2;
                 int year = Integer.parseInt(parts[2]);
                 Calendar calendar = Calendar.getInstance();
                 calendar.set(Calendar.YEAR, year);
                 calendar.set(Calendar.MONTH, month);
                 calendar.set(Calendar.DAY_OF_MONTH, day);
                 long milliTime = calendar.getTimeInMillis();
                 calendarView.setDate(milliTime, true, true);
                  break;
             }*/
             case R.id.selectdate:{

                /* DialogFragment dialogfragment = new datepickerClass();

                 dialogfragment.show(getFragmentManager(), "DatePickerDialog");*/
                 final Calendar c = Calendar.getInstance();
                 mYear = c.get(Calendar.YEAR);
                 mMonth = c.get(Calendar.MONTH);
                 mDay = c.get(Calendar.DAY_OF_MONTH);


                 DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                         new DatePickerDialog.OnDateSetListener() {

                             @Override
                             public void onDateSet(DatePicker view, int year,
                                                   int monthOfYear, int dayOfMonth) {
                                 String date =dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                                 selectdatetv.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);


                             }
                         }, mYear, mMonth, mDay);
                 datePickerDialog.show();

             }

         }

    }


  /*  @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int date) {


        selectedDate = date+"/"+month+"/"+year;
        Toast.makeText(getApplicationContext(), selectedDate, Toast.LENGTH_LONG).show();

    }*/

  @SuppressLint("ValidFragment")
    public class datepickerClass extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            final Calendar calendar = Calendar.getInstance();

            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);

            DatePickerDialog datepickerdialog = new DatePickerDialog(LeadEnteryActivity.this,
                    AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,this,year,month,day);
            return datepickerdialog;

        }


        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            String date =dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
            selectdatetv.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
            // TODO Auto-generated method stub








        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
