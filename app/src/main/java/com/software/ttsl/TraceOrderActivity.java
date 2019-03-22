package com.software.ttsl;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.software.ttsl.Response.TrackingResponse;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Utils.DialogUtitlity;
import com.software.ttsl.model.TraceResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TraceOrderActivity extends AppCompatActivity implements View.OnClickListener {


    public static final String KEY_TRACKING_ID = "tracking_id";

    public static final String KEY_TRACKING_NO = "tracking_no";

    @BindView(R.id.linear_generated)
    LinearLayout layout_generated;

    @BindView(R.id.linear_loaded)
    LinearLayout layout_loaded;

    @BindView(R.id.linear_stuffed)
    LinearLayout layout_stuffed;

    @BindView(R.id.linear_booked)
    LinearLayout layout_booked;

    @BindView(R.id.linear_booked1)
    LinearLayout layout_received;

    @BindView(R.id.linear_carted)
    LinearLayout layout_carted;

    @BindView(R.id.iv_generated)
    ImageView generatedIV;

    @BindView(R.id.iv_loaded)
    ImageView loadedIV;

    @BindView(R.id.iv_stuffed)
    ImageView stuffedIV;

    @BindView(R.id.iv_booked)
    ImageView bookedIV;


    @BindView(R.id.iv_booked1)
    ImageView receivedIV;

    @BindView(R.id.iv_carted)
    ImageView cartedIV;

    @BindView(R.id.next)
    Button button;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_cfs_port)
    TextView textViewCfsPort;

    @BindView(R.id.tv_pol_port)
    TextView textViewPolPort;

    @BindView(R.id.tv_pol)
    TextView tvPolId;



    @BindView(R.id.tv_pod_port)
    TextView textViewPodPort;


    @BindView(R.id.tv_stuffed_id)
    TextView textViewStuffedId;

    @BindView(R.id.tv_booked_date)
    TextView textViewBookedDate;

    @BindView(R.id.tv_booked_no)
    TextView textViewBookedNo;

    @BindView(R.id.tv_received_date)
    TextView textViewReceivedDate;


    @BindView(R.id.tv_received_id)
    TextView textViewReceivedId;

    @BindView(R.id.tv_carted_date)
    TextView textViewCartedData;


    @BindView(R.id.tv_carted_no)
    TextView textViewCartedNo;




  /*  @BindView(R.id.tv_stuffed_id)
    TextView textViewStuffedId;*/

   /* @BindView(R.id.tv_booked_date)
    TextView textViewBookedDate;
*/
   /* @BindView(R.id.tv_booked_no)
    TextView textViewBookNo;*/

  /*  @BindView(R.id.tv_carted_date)
    TextView textViewCartedDate;

    @BindView(R.id.tv_carted_no)
    TextView textViewCartedNo;*/

    Long count;
    int status = 3;

    private Long trackingID;
    private String trackingNo;

    private TraceResponse traceResponse;

    private int drawableTimelinePast;
    private int drawableTimeLineView;
    private int drawableTimeLineFeatureView;
    private int selectedTile;
    private int unSelectedTile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trace_order);

        ButterKnife.bind(this);

        setResources();

        Intent intent = getIntent();
        trackingID = intent.getLongExtra(KEY_TRACKING_ID, 0);
        trackingNo = intent.getStringExtra(KEY_TRACKING_NO);

        button.setOnClickListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        getTracingResponse();


        setCargoStatus();


    }

    private void setResources() {
        drawableTimeLineFeatureView = R.drawable.time_line_future_view;
        drawableTimelinePast = R.drawable.time_line_past;
        drawableTimeLineView = R.drawable.circle_time_line_view;
        unSelectedTile= getResources().getColor(R.color.colorWhite);
        selectedTile  = getResources().getColor(R.color.colorSelectedTile);
    }

    private void setCargoStatus() {
        switch (status) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
    }

    /*private void setView(Drawable ){

    }*/

    private void getTracingResponse() {
        DialogUtitlity.showLoading(TraceOrderActivity.this);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        final Call<TrackingResponse> clientCall = apiService.getTraceingData(6, trackingNo);
        clientCall.enqueue(new Callback<TrackingResponse>() {
            @Override
            public void onResponse(Call<TrackingResponse> call, Response<TrackingResponse> response) {
                DialogUtitlity.hideLoading();
                int statusCode = response.code();
                if (statusCode == 200) {
                    if (response.body() instanceof TrackingResponse) {
                        TrackingResponse responseList= response.body();
                        count = traceResponse.getStatus();
                        Log.e("response", traceResponse.getCfsReceivedId());



                    }
                }

            }

            @Override
            public void onFailure(Call<TrackingResponse> call, Throwable t) {
                DialogUtitlity.hideLoading();

            }
        });

    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.next) {

        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    public void setData(TraceResponse traceResponse){
         count = traceResponse.getStatus();
         textViewCfsPort.setText(traceResponse.getCfsFdcCode());
         textViewPolPort.setText(traceResponse.getCfsPolCode());
         textViewPodPort.setText(traceResponse.getCfsPodCode());
         textViewBookedNo.setText(traceResponse.getBookingId());
         textViewReceivedId.setText(traceResponse.getCfsReceivedId());





        if(count==1) {
            setView(drawableTimeLineView, drawableTimeLineFeatureView, drawableTimeLineFeatureView, drawableTimeLineFeatureView, drawableTimeLineFeatureView, drawableTimeLineFeatureView, selectedTile, unSelectedTile, count);
        }else if(count ==2 ){
            setView(drawableTimelinePast ,drawableTimeLineView, drawableTimeLineFeatureView, drawableTimeLineFeatureView, drawableTimeLineFeatureView, drawableTimeLineFeatureView, selectedTile, unSelectedTile, count);
        }else if(count ==3 ){
            setView(drawableTimelinePast ,drawableTimelinePast,drawableTimeLineView, drawableTimeLineFeatureView, drawableTimeLineFeatureView, drawableTimeLineFeatureView, selectedTile, unSelectedTile, count);
        }else if(count ==4 ){
            setView(drawableTimelinePast ,drawableTimelinePast ,drawableTimelinePast ,drawableTimeLineView,drawableTimeLineFeatureView, drawableTimeLineFeatureView, selectedTile, unSelectedTile, count);
        }else if(count ==5 ){
            setView(drawableTimelinePast ,drawableTimelinePast ,drawableTimelinePast ,drawableTimelinePast ,drawableTimeLineView,  drawableTimeLineFeatureView, selectedTile, unSelectedTile, count);
        }else if(count ==6 ){
            setView(drawableTimelinePast ,drawableTimelinePast ,drawableTimelinePast ,drawableTimelinePast ,drawableTimelinePast ,drawableTimeLineView, selectedTile, unSelectedTile, count);
        } else {
            generatedIV.setImageDrawable(getResources().getDrawable(R.drawable.time_line_past));
        }

    }


    public void setView(int imageGenerated,int imageLoaded,int imageStuffed,int imageBooked,int imageReceived,int imageCarted,int colorSelected, int colorUnSelected,long status){
        generatedIV.setImageResource(imageGenerated);
        loadedIV.setImageResource(imageLoaded);
        stuffedIV.setImageResource(imageStuffed);
        bookedIV.setImageResource(imageBooked);
        receivedIV.setImageResource(imageReceived);
        cartedIV.setImageResource(imageCarted);

        if(status==1){
            layout_generated.setBackgroundColor(selectedTile);
            layout_loaded.setBackgroundColor(unSelectedTile);
            layout_stuffed.setBackgroundColor(unSelectedTile);
            layout_booked.setBackgroundColor(unSelectedTile);
            layout_received.setBackgroundColor(unSelectedTile);
            layout_carted.setBackgroundColor(unSelectedTile);
        }
         else if(status==2){
            layout_generated.setBackgroundColor(unSelectedTile);
            layout_loaded.setBackgroundColor(selectedTile);
            layout_stuffed.setBackgroundColor(unSelectedTile);
            layout_booked.setBackgroundColor(unSelectedTile);
            layout_received.setBackgroundColor(unSelectedTile);
            layout_carted.setBackgroundColor(unSelectedTile);
        }
        else if(status==3){
            layout_generated.setBackgroundColor(unSelectedTile);
            layout_loaded.setBackgroundColor(unSelectedTile);
            layout_stuffed.setBackgroundColor(selectedTile);
            layout_booked.setBackgroundColor(unSelectedTile);
            layout_received.setBackgroundColor(unSelectedTile);
            layout_carted.setBackgroundColor(unSelectedTile);
        }
       else if(status==4){
            layout_generated.setBackgroundColor(unSelectedTile);
            layout_loaded.setBackgroundColor(unSelectedTile);
            layout_stuffed.setBackgroundColor(unSelectedTile);
            layout_booked.setBackgroundColor(selectedTile);
            layout_received.setBackgroundColor(unSelectedTile);
            layout_carted.setBackgroundColor(unSelectedTile);
        }
        else if(status==5){
            layout_generated.setBackgroundColor(unSelectedTile);
            layout_loaded.setBackgroundColor(unSelectedTile);
            layout_stuffed.setBackgroundColor(unSelectedTile);
            layout_booked.setBackgroundColor(unSelectedTile);
            layout_received.setBackgroundColor(selectedTile);
            layout_carted.setBackgroundColor(unSelectedTile);
        }
        else  if(status==6){
            layout_generated.setBackgroundColor(unSelectedTile);
            layout_loaded.setBackgroundColor(unSelectedTile);
            layout_stuffed.setBackgroundColor(unSelectedTile);
            layout_booked.setBackgroundColor(unSelectedTile);
            layout_received.setBackgroundColor(unSelectedTile);
            layout_carted.setBackgroundColor(selectedTile);
        }






    }


}
