package com.software.ttsl;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.software.ttsl.Utils.AlertDialogManager;
import com.software.ttsl.model.SailingScheduleRequestDate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SailingScheduleActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    public static final String TAG = SailingScheduleActivity.class.getName();

    public static final String SELECTED_PORT = "selected_port";
    public static final String SELECTED_VESSEL = "vessel_name";
    public static final String SELECTED_VESSEL_ID = "vessel_id";
    public static final String SELECTED_PORT_ID = "selected_port_id";
    public static final String SELECTED_PORT_COUNTRY = "selected_port_country";
    public static final String SELECTED_PORT_STATE = "selected_port_state";
    public static final int REQUEST_LOADING_PORT = 1000;
    public static final int REQUEST_DISCHARGE_PORT = 2000;
    public static final int REQUEST_VESSEL = 3000;
    private static final String KEY_PARCEL_DATA = "parcel_data";
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.btn_search)
    Button buttonSearchSchedule;

    @BindView(R.id.tv_vessel)
    TextView textViewVessel;


    @BindView(R.id.layout_loading)
    RelativeLayout relativeLayoutLoadingPort;

    @BindView(R.id.layout_discharge)
    RelativeLayout relativeLayoutDischargePort;

    @BindView(R.id.tv_loading_port)
    TextView textViewLoadingPort;

    @BindView(R.id.tv_discharge_port)
    TextView textViewDischargePort;

    @BindView(R.id.layout_from_departure_date)
    RelativeLayout relativeLayoutFromDepartureDate;

    @BindView(R.id.layout_to_departure_date)
    RelativeLayout relativeLayoutToDepartureDate;

    @BindView(R.id.layout_from_arrival_date)
    RelativeLayout relativeLayoutFromArrivalDate;

    @BindView(R.id.layout_to_arrival_date)
    RelativeLayout relativeLayoutToArrivalDate;


    @BindView(R.id.tv_from_departure_date)
    TextView textViewFromDepartureDate;

    @BindView(R.id.tv_to_departure_date)
    TextView textViewToDepartureDate;

    @BindView(R.id.tv_from_arrival_date)
    TextView textViewFromArrivalDate;

    @BindView(R.id.tv_to_arrival_date)
    TextView textViewToArrivalDate;

    @BindView(R.id.iv_clear_from_etd)
    ImageView imageViewClearFromETD;

    @BindView(R.id.iv_clear_to_etd)
    ImageView imageViewClearToETD;


    @BindView(R.id.iv_clear_from_eta)
    ImageView imageViewClearFromETA;

    @BindView(R.id.iv_clear_to_eta)
    ImageView imageViewClearToETA;

    SailingScheduleRequestDate sailingScheduleRequestDate;
    int mYear, mMonth, mDay;
    long milliseconds;
    long mydate;
    Date formatedDate;
    AlertDialogManager alertDialogManager = new AlertDialogManager();
    private int fromPortID = 0;
    private int toPortId = 0;
    private Intent intent;
    private Long vesselId = 0L;
    private long date;
    private String selectedDate;
    private String portCode, portCountry, portState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sailing_schedule);


        ButterKnife.bind(this);

        setClickListener();
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void setClickListener() {
        relativeLayoutLoadingPort.setOnClickListener(this);
        relativeLayoutDischargePort.setOnClickListener(this);
        relativeLayoutFromDepartureDate.setOnClickListener(this);
        relativeLayoutToArrivalDate.setOnClickListener(this);
        relativeLayoutToDepartureDate.setOnClickListener(this);
        relativeLayoutFromArrivalDate.setOnClickListener(this);
        textViewVessel.setOnClickListener(this);
        buttonSearchSchedule.setOnClickListener(this);
        imageViewClearFromETD.setOnClickListener(this);
        imageViewClearToETD.setOnClickListener(this);
        imageViewClearFromETA.setOnClickListener(this);
        imageViewClearToETA.setOnClickListener(this);
    }


    public void openCalenderDialouge(final View view, long date) {

        mydate = date;

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker dateView, int year, int monthOfYear, int dayOfMonth) {
                String date = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                if (view.getId() == R.id.layout_from_departure_date) {

                    textViewFromDepartureDate.setText(date);
                    Log.e(TAG, "FromDepartureDate " + date);
                    imageViewClearFromETD.setVisibility(View.VISIBLE);
                } else if (view.getId() == R.id.layout_to_departure_date) {
                    textViewToDepartureDate.setText(date);
                    Log.e(TAG, " ToDepartureDate " + date);
                    imageViewClearToETD.setVisibility(View.VISIBLE);
                } else if (view.getId() == R.id.layout_from_arrival_date) {
                    textViewFromArrivalDate.setText(date);
                    Log.e(TAG, "FromArrivalDate " + date);
                    imageViewClearFromETA.setVisibility(View.VISIBLE);
                } else if (view.getId() == R.id.layout_to_arrival_date) {
                    textViewToArrivalDate.setText(date);
                    Log.e(TAG, "ToArrivalDate" + date);
                    imageViewClearToETA.setVisibility(View.VISIBLE);
                }

            }
        }, mYear, mMonth, mDay);

        if (mydate != 0) {
            datePickerDialog.getDatePicker().setMinDate(mydate);
        }
        datePickerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        datePickerDialog.setTitle("");
        datePickerDialog.show();


    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.layout_loading:
                intent = new Intent(SailingScheduleActivity.this, SelectPortActivity.class);
                startActivityForResult(intent, REQUEST_LOADING_PORT);
                break;
            case R.id.layout_discharge:
                intent = new Intent(SailingScheduleActivity.this, SelectPortActivity.class);
                startActivityForResult(intent, REQUEST_DISCHARGE_PORT);
                break;

            case R.id.tv_vessel:
                intent = new Intent(SailingScheduleActivity.this, SearchVesselActivity.class);
                startActivityForResult(intent, REQUEST_VESSEL);
                break;


            case R.id.layout_from_departure_date:
                Log.e(TAG, " IN from_departure_date ");
                textViewFromArrivalDate.setText("");
                textViewToArrivalDate.setText("");
                textViewToDepartureDate.setText("");
                date = System.currentTimeMillis() - 1000;
                openCalenderDialouge(relativeLayoutFromDepartureDate, date);

                break;
            case R.id.layout_to_departure_date:
                Log.e(TAG, " IN to_departure_date ");
                textViewFromArrivalDate.setText("");
                textViewToArrivalDate.setText("");
                selectedDate = textViewFromDepartureDate.getText().toString().trim();
                if (!selectedDate.isEmpty()) {
                    /*alertDialogManager.showAlertDialog(this, getResources().getString(R.string.err_msg_title_dialog),
                            getResources().getString(R.string.err_msg_select_from_etd_first), true);*/
                    date = convertDateToLong(getNextDate(selectedDate));


                    openCalenderDialouge(relativeLayoutToDepartureDate, date);

                } else {
                    /*date = convertdatetoLong(getNextDate(selectedDate));*/
                    openCalenderDialouge(relativeLayoutToDepartureDate, 0);

                }

                break;
            case R.id.layout_from_arrival_date:
                Log.e(TAG, " IN from_arrival_date ");
                selectedDate = textViewToDepartureDate.getText().toString().trim();
                textViewToArrivalDate.setText("");
                if (!selectedDate.isEmpty()) {
                    /*alertDialogManager.showAlertDialog(this, getResources().getString(R.string.err_msg_title_dialog),
                            getResources().getString(R.string.err_msg_select_to_etd_first), true);*/
                    date = convertDateToLong(getNextDate(selectedDate));

                    openCalenderDialouge(relativeLayoutFromArrivalDate, date);
                } else {
                    /*date = convertdatetoLong(getNextDate(selectedDate));*/
                    openCalenderDialouge(relativeLayoutFromArrivalDate, 0);

                }


                break;
            case R.id.layout_to_arrival_date:
                Log.e(TAG, " IN to_arrival_date ");
                selectedDate = textViewFromArrivalDate.getText().toString().trim();
                if (!selectedDate.isEmpty()) {
                    /*alertDialogManager.showAlertDialog(this, getResources().getString(R.string.err_msg_title_dialog),
                            getResources().getString(R.string.err_msg_select_from_eta_first), true);*/
                    date = convertDateToLong(getNextDate(selectedDate));

                    openCalenderDialouge(relativeLayoutToArrivalDate, date);
                } else {

                    /*date = convertdatetoLong(getNextDate(selectedDate));*/
                    openCalenderDialouge(relativeLayoutToArrivalDate, 0);

                }


                break;


            case R.id.btn_search:
                //      Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
                checkEntry();
                break;
            case R.id.iv_clear_from_etd:
                textViewFromDepartureDate.setText("");
                imageViewClearFromETD.setVisibility(View.GONE);
                break;
            case R.id.iv_clear_to_etd:
                textViewToDepartureDate.setText("");
                imageViewClearToETD.setVisibility(View.GONE);
                break;
            case R.id.iv_clear_from_eta:
                textViewFromArrivalDate.setText("");
                imageViewClearFromETA.setVisibility(View.GONE);
                break;
            case R.id.iv_clear_to_eta:
                textViewToArrivalDate.setText("");
                imageViewClearToETA.setVisibility(View.GONE);
                break;


        }
    }

    private void checkEntry() {
        String title = getResources().getString(R.string.err_msg_title_dialog);
        if (fromPortID == 0 || textViewLoadingPort.getText().toString().trim().isEmpty()) {
            alertDialogManager.showAlertDialog(this, title, getResources().getString(R.string.err_loading_port_empty), true);
        } else if (toPortId == 0 || textViewDischargePort.getText().toString().trim().isEmpty()) {
            alertDialogManager.showAlertDialog(this, title, getResources().getString(R.string.err_discharge_port_empty), true);
        } else if (fromPortID == toPortId) {
            alertDialogManager.showAlertDialog(this, title, getResources().getString(R.string.err_port_is_same), true);
        }
        /*else if (textViewFromArrivalDate.getText().toString().trim().isEmpty()) {
            alertDialogManager.showAlertDialog(this, title, getResources().getString(R.string.err_from_etd_port_empty), true);
        } else if (textViewToDepartureDate.getText().toString().trim().isEmpty()) {
            alertDialogManager.showAlertDialog(this, title, getResources().getString(R.string.err_to_etd_port_empty), true);
        } else if (textViewFromArrivalDate.getText().toString().trim().isEmpty()) {
            alertDialogManager.showAlertDialog(this, title, getResources().getString(R.string.err_from_eta_port_empty), true);
        } else if (textViewToArrivalDate.getText().toString().trim().isEmpty()) {
            alertDialogManager.showAlertDialog(this, title, getResources().getString(R.string.err_to_eta_port_empty), true);
        } else if (vesselId == 0L || textViewVessel.getText().toString().trim().isEmpty()) {
            alertDialogManager.showAlertDialog(this, title, getResources().getString(R.string.err_vessel_empty), true);
        } */
        else {


            setScheduleRequestData();


        }

    }

    private void setScheduleRequestData() {

        sailingScheduleRequestDate = new SailingScheduleRequestDate();
        sailingScheduleRequestDate.setLoadingPortId(fromPortID);
        sailingScheduleRequestDate.setDischargePortId(toPortId);
        if (textViewFromDepartureDate.getText().toString().trim().isEmpty() && textViewToDepartureDate.getText().toString().trim().isEmpty()
                && textViewFromArrivalDate.getText().toString().trim().isEmpty() && textViewToArrivalDate.getText().toString().trim().isEmpty()) {
            sailingScheduleRequestDate.setFromETD(null);
            sailingScheduleRequestDate.setToETD(null);
            sailingScheduleRequestDate.setFromETA(null);
            sailingScheduleRequestDate.setToETA(null);

        } else {
            sailingScheduleRequestDate.setFromETD(convertDateFormat(textViewFromDepartureDate.getText().toString().trim()));
            sailingScheduleRequestDate.setToETD(convertDateFormat(textViewToDepartureDate.getText().toString().trim()));
            sailingScheduleRequestDate.setFromETA(convertDateFormat(textViewFromArrivalDate.getText().toString().trim()));
            sailingScheduleRequestDate.setToETA(convertDateFormat(textViewToArrivalDate.getText().toString().trim()));
            sailingScheduleRequestDate.setVesselId(vesselId);
        }


        Intent intent = new Intent(this, ScheduleListActivity.class);
        intent.putExtra(KEY_PARCEL_DATA, sailingScheduleRequestDate);
        startActivity(intent);

    }

    private String getTomorrow() {
        // get a calendar instance, which defaults to "now"
        Calendar calendar = Calendar.getInstance();
        // get a date to represent "today"
        Date today = calendar.getTime();
        // add one day to the date/calendar
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        // now get "tomorrow"
        Date tomorrow = calendar.getTime();

        // print out tomorrow's date
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String date = formatter.format(tomorrow);

        return date;

    }


    public String getNextDate(String curDate) {
        final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = format.parse(curDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return format.format(calendar.getTime());
    }


    public long convertDateToLong(String date) {


        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        try {
            formatedDate = (Date) formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        milliseconds = formatedDate.getTime();

        return milliseconds;

    }


    public String convertDateFormat(String date) {
        String strDate = null;


        try {
            Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(date);

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            strDate = dateFormat.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return strDate;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1000:
                    textViewLoadingPort.setText(data.getStringExtra(SELECTED_PORT).trim() + "," + data.getStringExtra(SELECTED_PORT_COUNTRY).trim() + "," + data.getStringExtra(SELECTED_PORT_STATE).trim());
                    fromPortID = data.getIntExtra(SELECTED_PORT_ID, 0);

                    break;
                case 2000:
                    textViewDischargePort.setText(data.getStringExtra(SELECTED_PORT).trim() + "," + data.getStringExtra(SELECTED_PORT_COUNTRY).trim() + "," + data.getStringExtra(SELECTED_PORT_STATE).trim());
                    toPortId = data.getIntExtra(SELECTED_PORT_ID, 0);
                    break;
                case 3000:
                    textViewVessel.setText(data.getStringExtra(SELECTED_VESSEL));
                    vesselId = data.getLongExtra(SELECTED_VESSEL_ID, 1);
                    break;
            }
        }

    }
}
