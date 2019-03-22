package com.software.ttsl;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.software.ttsl.Response.TrackingHeader;
import com.software.ttsl.Response.TrackingResponse;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Utils.DialogUtitlity;
import com.software.ttsl.model.TraceResponse;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TraceCargoActivity extends AppCompatActivity implements View.OnClickListener, Callback<TrackingResponse> {

    public static final String TAG = TraceCargoActivity.class.getName();
    public static final String KEY_TRACKING_ID = "tracking_id";

    public static final String KEY_TRACKING_NO = "tracking_no";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_heading_bill_number)
    TextView textViewHeadingBillNumber;


    @BindView(R.id.tv_status)
    TextView textViewStatus;

    @BindView(R.id.tv_transit_time)
    TextView textViewTransitTime;

    @BindView(R.id.tv_departed_time)
    TextView textViewDepartedTime;

    @BindView(R.id.tv_departed_port_name)
    TextView textViewPOD;

    @BindView(R.id.tv_departed_port_code)
    TextView textViewPODCode;
    @BindView(R.id.tv_arrival_time)
    TextView textViewArrivalTime;

    @BindView(R.id.tv_arrival_port_name)
    TextView textViewPOL;

    @BindView(R.id.tv_arrival_port_code)
    TextView textViewPOLCode;


    @BindView(R.id.iv_toggle_carted)
    ImageView imageViewToggleCarted;

    @BindView(R.id.layout_carted_details)
    LinearLayout linearLayoutCartedDetails;

    @BindView(R.id.tv_carting_date)
    TextView textViewCartingDate;

    @BindView(R.id.iv_toggle_received)
    ImageView imageViewToggleReceived;

    @BindView(R.id.tv_customer_clearance_date)
    TextView textViewCustomerClearanceDate;

    @BindView(R.id.layout_received_details)
    LinearLayout linearLayoutReceivedDetails;

    @BindView(R.id.iv_toggle_booked)
    ImageView imageViewToggleBooked;

    @BindView(R.id.layout_booked_details)
    LinearLayout linearLayoutBookedDetails;

    @BindView(R.id.tv_booked_date)
    TextView textViewBookingDate;

    @BindView(R.id.tv_booking_no)
    TextView textViewBookingNo;

    @BindView(R.id.iv_toggle_stuffed)
    ImageView imageViewToggleStuffed;

    @BindView(R.id.layout_stuffed_details)
    LinearLayout linearLayoutStuffedDetails;

    @BindView(R.id.tv_container_no)
    TextView textViewContainerNo;

    @BindView(R.id.tv_container_volume)
    TextView textViewContainerVolume;

    @BindView(R.id.iv_toggle_generated)
    ImageView imageViewToggelGenerated;

    @BindView(R.id.layout_generated_details)
    LinearLayout linearLayoutGeneratedDetails;

    @BindView(R.id.tv_generated_date)
    TextView textViewGeneratedDate;

    @BindView(R.id.tv_job_no)
    TextView textViewJobNo;

    @BindView(R.id.iv_toggle_loaded)
    ImageView imageViewToggleLoaded;

    @BindView(R.id.layout_loaded_details)
    LinearLayout linearLayoutLoadedDetails;

    @BindView(R.id.tv_sob_date)
    TextView textViewSobDate;

    private String headingBillNumber;
    private String status;
    private Long trackingID;
    private String trackingNo;
    private TraceResponse traceResponse;
    private View customView;
    private TextView firstRow, secondRow;
    private int selectedColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trace_cargo);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        trackingID = intent.getLongExtra(KEY_TRACKING_ID, 0);
        trackingNo = intent.getStringExtra(KEY_TRACKING_NO);


        setClickListener();


        getTracingResponse();

    }

    private void setClickListener() {
        imageViewToggleCarted.setOnClickListener(this);
        imageViewToggleReceived.setOnClickListener(this);
        imageViewToggleBooked.setOnClickListener(this);
        imageViewToggleStuffed.setOnClickListener(this);
        imageViewToggelGenerated.setOnClickListener(this);
        imageViewToggleLoaded.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.iv_toggle_carted:
                showAndHideLayout(imageViewToggleCarted, linearLayoutCartedDetails);
                break;

            case R.id.iv_toggle_received:
                showAndHideLayout(imageViewToggleReceived, linearLayoutReceivedDetails
                );
                break;
            case R.id.iv_toggle_booked:
                showAndHideLayout(imageViewToggleBooked, linearLayoutBookedDetails);
                break;
            case R.id.iv_toggle_stuffed:
                showAndHideLayout(imageViewToggleStuffed, linearLayoutStuffedDetails);
                break;
            case R.id.iv_toggle_generated:
                showAndHideLayout(imageViewToggelGenerated, linearLayoutGeneratedDetails);
                break;
            case R.id.iv_toggle_loaded:
                showAndHideLayout(imageViewToggleLoaded, linearLayoutLoadedDetails);
                break;


        }

    }


    private void getTracingResponse() {
        DialogUtitlity.showLoading(TraceCargoActivity.this);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        final Call<TrackingResponse> clientCall = apiService.getTraceingData( 1, "GIMSE1117000002");
        clientCall.enqueue(this);

    }


    public void setData(List<TraceResponse> traceResponseList) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int i = 0;


        for (TraceResponse traceResponse : traceResponseList) {

            textViewHeadingBillNumber.setText(traceResponse.getBookingNumber());
            setCustomView(inflater, i);
            linearLayoutGeneratedDetails.addView(customView);
            firstRow.setText("Date : " + traceResponse.getCreateDate());
            secondRow.setText("JOB ID : " + String.valueOf(traceResponse.getJobId()));


            setCustomView(inflater, i);
            linearLayoutLoadedDetails.addView(customView);
            firstRow.setText("SOB Date : " + traceResponse.getSobDate());
            secondRow.setVisibility(View.GONE);


            setCustomView(inflater, i);
            linearLayoutStuffedDetails.addView(customView);
            firstRow.setText("Container No : " + traceResponse.getContainerNumber());
            secondRow.setText("Container Volume : " + traceResponse.getVolume());

            //textViewHeadingBillNumber.setText("Shipment " + traceResponse.getShippingBillNumber());
            setCustomView(inflater, i);
            linearLayoutBookedDetails.addView(customView);
            firstRow.setText("Booking Date : " + traceResponse.getBookingDate());
            secondRow.setText("Booking Number : " + traceResponse.getBookingNumber());

            setCustomView(inflater, i);
            firstRow.setText("Clearance Date : " + traceResponse.getCustomerClearanceDate());
            secondRow.setVisibility(View.GONE);
            linearLayoutReceivedDetails.addView(customView);

            setCustomView(inflater, i);
            linearLayoutCartedDetails.addView(customView);
            firstRow.setText("Carting Date : " + traceResponse.getCartingDate());
            secondRow.setVisibility(View.GONE);


            //textViewGeneratedDate.setText("Date : " + traceResponse.getCreateDate());
            //textViewJobNo.setText("JOB ID : " + String.valueOf(traceResponse.getJobId()));
            //textViewSobDate.setText("SOB Date : " + traceResponse.getSobDate());
            //textViewContainerNo.setText("Container No : " + traceResponse.getContainerNumber());
            //textViewContainerVolume.setText("Container Volume : " + traceResponse.getVolume());
            //textViewBookingDate.setText("Booking Date : " + traceResponse.getBookingDate());
            //textViewBookingNo.setText("Booking Number : " + traceResponse.getBookingNumber());
            //textViewCustomerClearanceDate.setText("Clearance Date : " + traceResponse.getCustomerClearanceDate());
            //textViewCartingDate.setText("Carting Date : " + traceResponse.getCartingDate());


        }


    }

    private void setCustomView(LayoutInflater inflater, int i) {
        customView = inflater.inflate(R.layout.row_tacking_cago, null);
        firstRow = (TextView) customView.findViewById(R.id.tv_generated_date1);
        secondRow = (TextView) customView.findViewById(R.id.tv_job_no1);
       /* if(i%2==0) {
            customView.setBackgroundColor(getResources().getColor(R.color.colorSelectedTile));
        }else {
            customView.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        }*/
    }


    private void showAndHideLayout(ImageView imageView, LinearLayout linearLayout) {
        float rotation = imageView.getRotation();
        imageView.animate().rotation(rotation + 180).start();
        if (linearLayout.getVisibility() == View.VISIBLE) {
            linearLayout.setVisibility(View.GONE);

            //toggleDiscription.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_arrow_down));
        } else {
            linearLayout.setVisibility(View.VISIBLE);

            //toggleDiscription.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_arrow_up));
        }


    }


    public String getCountOfDays(String arrivalDate, String departedDate) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        Date firstDate = null;
        Date secondDate =null;
        try {
            firstDate = sdf.parse(departedDate);
            secondDate = sdf.parse(arrivalDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        return String.valueOf(diff);




    }


    @Override
    public void onResponse(Call<TrackingResponse> call, Response<TrackingResponse> response) {
        DialogUtitlity.hideLoading();
        int statusCode = response.code();
        if (statusCode == 200) {
            if (response.body() instanceof TrackingResponse) {
                TrackingResponse trackingResponse = response.body();
                List<TraceResponse> responseList = trackingResponse.getTrace();
                //Log.e("response", traceResponse.getCfsReceivedId());
                TrackingHeader trackingHeader = trackingResponse.getHeader();
                setData(responseList);
                setHeader(trackingHeader);


            }
        }
    }

    private void setHeader(TrackingHeader trackingHeader) {
        String status1 = trackingHeader.getCurrentStatus();
        status = "Status : " + status1;
        SpannableString spannableString = new SpannableString(status);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorAccent)), status.indexOf(status1), status.length(), 0);
        textViewStatus.setText(spannableString);
        textViewTransitTime.setText("Transit Time : " + getCountOfDays( getFormattedDate(trackingHeader.getEta()) ,getFormattedDate(trackingHeader.getEtd())) +" Days");
        textViewStatus.setText(trackingHeader.getCurrentStatus());
        textViewDepartedTime.setText("Departure: " + getFormattedDate(trackingHeader.getEtd()));
        textViewArrivalTime.setText("Arrival: " + getFormattedDate(trackingHeader.getEta()));
        textViewPOD.setText(trackingHeader.getPodCode());
        textViewPOL.setText(trackingHeader.getPolCode());
    }

    private String getFormattedDate(String date) {
        String formattedDate = date;
        Date formatted = null;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S", Locale.getDefault());
        try {
            formatted = format.parse(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.e(TAG," Date 1"+formattedDate);

        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Log.e(TAG,"date formate "+ formatted);
        formattedDate = sdf.format(formatted);

        Log.e(TAG," Date "+formattedDate);
        return formattedDate;
    }


    @Override
    public void onFailure(Call<TrackingResponse> call, Throwable t) {
        DialogUtitlity.hideLoading();

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}



