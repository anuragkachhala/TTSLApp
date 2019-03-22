package com.software.ttsl.NewModule;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.software.ttsl.Fragment.SailingScheduleFragment;
import com.software.ttsl.R;
import com.software.ttsl.SearchVesselActivity;
import com.software.ttsl.SelectPortActivity;
import com.software.ttsl.TransportApp;
import com.software.ttsl.Utils.AlertDialogManager;
import com.software.ttsl.Utils.ConnectivityReceiver;
import com.software.ttsl.Utils.DateAndTimeUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class SailScheduleFragment extends Fragment implements View.OnClickListener, ConnectivityReceiver.ConnectivityReceiverListener {

    public static final String TAG = SailingScheduleFragment.class.getName();

    public static final String SELECTED_PORT = "selected_port";
    public static final String SELECTED_VESSEL = "vessel_name";
    public static final String SELECTED_VESSEL_ID = "vessel_id";
    public static final String SELECTED_PORT_ID = "selected_port_id";
    public static final String SELECTED_PORT_COUNTRY = "selected_port_country";
    public static final String SELECTED_PORT_STATE = "selected_port_state";
    public static final String SELECTED_FROM_PORT = "selected_from_port";
    public static final String SELECTED_TO_PORT = "selected_to_port";
    public static final String LOADING_PORT="loading_port";
    public static final String DISCHARGE_PORT="discharge_port";
    public static final String FROM_ETD = "from_etd";
    public static final String TO_ETD = "to_etd";
    public static final String FROM_ETA = "from_eta";
    public static final String TO_ETA = "to_eta";
    public static final int REQUEST_LOADING_PORT = 1000;
    public static final int REQUEST_DISCHARGE_PORT = 2000;
    public static final int REQUEST_VESSEL = 3000;


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


    int mYear, mMonth, mDay;
    long milliseconds;
    Date formatedDate;
    private int fromPortID = 0;
    private int toPortId = 0;
    private Intent intent;
    private Long vesselId = 0L;
    private long date;
    private String fromPortName, toPortName;
    private AlertDialogManager alertDialogManager;


    public SailScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sail_schedule, container, false);
        ButterKnife.bind(this, view);
        alertDialogManager = new AlertDialogManager();
        TransportApp.getInstance().setConnectivityListener(this);
        setClickListener();
        return view;
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


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.layout_loading:
                startActivityForResult(SelectPortActivity.class, REQUEST_LOADING_PORT);
                break;
            case R.id.layout_discharge:
                startActivityForResult(SelectPortActivity.class, REQUEST_DISCHARGE_PORT);
                break;

            case R.id.tv_vessel:
                startActivityForResult(SearchVesselActivity.class, REQUEST_VESSEL);
                break;


            case R.id.layout_from_departure_date:
                Log.i(TAG, " IN from departure date ");
                clearSelectedDate(new TextView[]{textViewFromArrivalDate, textViewToArrivalDate, textViewToDepartureDate},
                        new ImageView[]{imageViewClearFromETA, imageViewClearToETA, imageViewClearToETD});
                date = System.currentTimeMillis() - 1000;
                openCalenderDialouge(relativeLayoutFromDepartureDate, date);

                break;
            case R.id.layout_to_departure_date:
                Log.i(TAG, " IN to departure date ");
                clearSelectedDate(new TextView[]{textViewFromArrivalDate, textViewToArrivalDate}, new ImageView[]{imageViewClearFromETA, imageViewClearToETA});
                openCalenderForSelectedDate(relativeLayoutToDepartureDate, textViewFromDepartureDate.getText().toString().trim());

                break;
            case R.id.layout_from_arrival_date:
                Log.e(TAG, " IN from_arrival_date ");
                clearSelectedDate(new TextView[]{textViewToArrivalDate}, new ImageView[]{imageViewClearToETA});
                openCalenderForSelectedDate(relativeLayoutFromArrivalDate, textViewToDepartureDate.getText().toString().trim());
                break;
            case R.id.layout_to_arrival_date:
                Log.e(TAG, " IN to_arrival_date ");
                openCalenderForSelectedDate(relativeLayoutToArrivalDate, textViewFromArrivalDate.getText().toString().trim());
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
            alertDialogManager.showAlertDialog(getContext(), title, getResources().getString(R.string.err_loading_port_empty), true);
        } else if (toPortId == 0 || textViewDischargePort.getText().toString().trim().isEmpty()) {
            alertDialogManager.showAlertDialog(getContext(), title, getResources().getString(R.string.err_discharge_port_empty), true);
        }/* else if (fromPortID == toPortId) {
            alertDialogManager.showAlertDialog(getContext(), title, getResources().getString(R.string.err_port_is_same), true);
        }*/
        else if(!checkConnection()){
            alertDialogManager.showAlertDialog(getContext(), "Network Error", "check your network connection", true);

        }else {
            intent = new Intent(getContext(), SailScheduleListActivity.class);
            intent.putExtra(SELECTED_FROM_PORT, fromPortName);
            intent.putExtra(SELECTED_TO_PORT, toPortName);

            intent.putExtra(FROM_ETD, dateFormatter(textViewFromDepartureDate.getText().toString().trim()));
            intent.putExtra(TO_ETD, dateFormatter(textViewToDepartureDate.getText().toString().trim()));
            intent.putExtra(FROM_ETA, dateFormatter(textViewFromArrivalDate.getText().toString().trim()));
            intent.putExtra(TO_ETA, dateFormatter(textViewToArrivalDate.getText().toString().trim()));
            intent.putExtra(LOADING_PORT,textViewLoadingPort.getText().toString());
            intent.putExtra(DISCHARGE_PORT,textViewDischargePort.getText().toString());
            startActivity(intent);
        }

    }

    private String dateFormatter(String date){
        if(date!=null && !date.isEmpty()) {
            String formattedDate1 = date.replace("-", ".");
            String formattedDate2 = formattedDate1.substring(0, formattedDate1.length() - 4) + formattedDate1.substring(8, formattedDate1.length());
            return formattedDate2;
        }else {
            return null;
        }
    }

    private void openCalenderForSelectedDate(RelativeLayout relativeLayout, String selectedDate) {
        if (!selectedDate.isEmpty()) {
            openCalenderDialouge(relativeLayout, convertDateToLong(DateAndTimeUtil.getNextDate(selectedDate)));
        } else {
            openCalenderDialouge(relativeLayout, 0);

        }

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


    private void clearSelectedDate(TextView[] textViews, ImageView[] imageViews) {
        int i = 0;
        for (TextView textView : textViews) {
            textView.setText("");
            imageViews[i].setVisibility(View.GONE);
            i++;
        }
    }


    public void openCalenderDialouge(final View view, long date) {

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker dateView, int year, int monthOfYear, int dayOfMonth) {
                String date = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                int id = view.getId();
                switch (id) {
                    case R.id.layout_from_departure_date:
                        setScheduleDate(textViewFromDepartureDate, imageViewClearFromETD, date);
                        break;
                    case R.id.layout_to_departure_date:
                        Log.e(TAG, " ToDepartureDate " + date);
                        setScheduleDate(textViewToDepartureDate, imageViewClearToETD, date);
                        break;
                    case R.id.layout_from_arrival_date:
                        Log.e(TAG, "FromArrivalDate " + date);
                        setScheduleDate(textViewFromArrivalDate, imageViewClearFromETA, date);
                        break;
                    case R.id.layout_to_arrival_date:
                        Log.e(TAG, "ToArrivalDate" + date);
                        setScheduleDate(textViewToArrivalDate, imageViewClearToETA, date);
                        break;
                }


            }
        }, mYear, mMonth, mDay);

        if (date != 0) {
            datePickerDialog.getDatePicker().setMinDate(date);
        }
        datePickerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        datePickerDialog.setTitle("");
        datePickerDialog.show();


    }

    private void setScheduleDate(TextView dateTextView, ImageView clearIconImageView, String selectedDate) {
        dateTextView.setText(selectedDate);
        clearIconImageView.setVisibility(View.VISIBLE);
    }


    private void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(new Intent(getContext(), cls), requestCode);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_LOADING_PORT:
                    fromPortName = data.getStringExtra(SELECTED_PORT_STATE).trim();
                    textViewLoadingPort.setText(data.getStringExtra(SELECTED_PORT).trim() + "," + data.getStringExtra(SELECTED_PORT_COUNTRY).trim() + "," + data.getStringExtra(SELECTED_PORT_STATE).trim());
                    fromPortID = data.getIntExtra(SELECTED_PORT_ID, 0);
                    break;
                case REQUEST_DISCHARGE_PORT:
                    toPortName = data.getStringExtra(SELECTED_PORT_STATE).trim();
                    textViewDischargePort.setText(data.getStringExtra(SELECTED_PORT).trim() + "," + data.getStringExtra(SELECTED_PORT_COUNTRY).trim() + "," + data.getStringExtra(SELECTED_PORT_STATE).trim());
                    toPortId = data.getIntExtra(SELECTED_PORT_ID, 0);
                    break;
                case REQUEST_VESSEL:
                    textViewVessel.setText(data.getStringExtra(SELECTED_VESSEL));
                    vesselId = data.getLongExtra(SELECTED_VESSEL_ID, 1);
                    break;
            }
        }




    }

    private boolean checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        if (!isConnected) {

            return false;
        }

        return true;
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

    }
}
