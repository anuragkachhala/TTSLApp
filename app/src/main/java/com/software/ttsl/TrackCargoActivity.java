package com.software.ttsl;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.software.ttsl.Response.TrackingNoConstraintResponse;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.DialogUtitlity;
import com.software.ttsl.Utils.SingletonTrackingConstraint;

import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackCargoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private static final String TAG =  TrackCargoActivity.class.getName();

    public static final String KEY_TRACKING_ID= "tracking_id";

    public static final String KEY_TRACKING_NO="tracking_no";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.spinner_cargo_type)
    Spinner mTrackCargoSpinner;

    @BindView(R.id.btn_track_cargo)
    Button mTrackBtn;

    @BindView(R.id.et_track_no)
    EditText mTrackNo;

    @BindView(R.id.tv_hint)
    TextView textViewHint;


    private String[] trackingNumberList;
    private String selectedTrackType, dialogMsg;
    private TrackingNoConstraintResponse trackingNoConstraintResponse;
    private Long trackingId;
    Set<String> trackingNoList ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_cargo);

        ButterKnife.bind(this);


        trackingNoList = SingletonTrackingConstraint.getInstance().getHashMap().keySet();

        if(trackingNoList.size()==0){
            getTrackingConstraints();
        }else {
            setSpinner();
        }





        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //add listenter....
        mTrackBtn.setOnClickListener(this);


        mTrackNo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    checkTrackValidation(); //Do whatever you intend to do when user click on search button in keyboard.
                    return true;
                }else {
                    return false;
                }

            }


    });


    }


    private void getTrackingConstraints() {
        DialogUtitlity.showLoading(TrackCargoActivity.this);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<TrackingNoConstraintResponse>> listCall = apiInterface.getTrackingNoConstraint();
        listCall.enqueue(new Callback<List<TrackingNoConstraintResponse>>() {
            @Override
            public void onResponse(Call<List<TrackingNoConstraintResponse>> call, Response<List<TrackingNoConstraintResponse>> response) {

                DialogUtitlity.hideLoading();
                int statusCode = response.code();
                if(response.body() instanceof List){
                    List<TrackingNoConstraintResponse> trackingNoConstraintResponseList = response.body();

                    DataBaseAdapter dataBaseAdapter = new DataBaseAdapter(TrackCargoActivity.this);
                    dataBaseAdapter.openDataBase();
                    dataBaseAdapter.addTrackingNoConstraint(trackingNoConstraintResponseList);
                    trackingNoList = SingletonTrackingConstraint.getInstance().getHashMap().keySet();
                    setSpinner();
                }
            }

            @Override
            public void onFailure(Call<List<TrackingNoConstraintResponse>> call, Throwable t) {
                DialogUtitlity.hideLoading();
            }
        });
    }


    public void setSpinner() {
        // Spinner click listener
        mTrackCargoSpinner.setOnItemSelectedListener(this);

        // get all category from resource file
        //trackingNumberList= getResources().getStringArray(R.array.track_type);

        //Set<String> trackingNoList = SingletonTrackingConstraint.getInstance().getHashMap().keySet();
        trackingNumberList = new String[trackingNoList.size() + 1];

        trackingNumberList[0] = "Select Category...";
        int i = 1;
        for (String trackingType : trackingNoList) {

            trackingNumberList[i] = trackingType;
            i++;

        }

        /* Create an ArrayAdapter using the string array and a default spinner layout */
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, trackingNumberList) {

            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };


        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mTrackCargoSpinner.setAdapter(adapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        selectedTrackType = parent.getItemAtPosition(position).toString();
        if(!selectedTrackType.equals("Select Category...")) {
            trackingId = SingletonTrackingConstraint.getInstance().getHashMap().get(selectedTrackType).getTrackingId();

        }
        mTrackNo.setHint("Enter " + selectedTrackType);


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_track_cargo:
                checkTrackValidation();
                break;
        }

    }





    public void checkTrackValidation() {
        String SelectedType = mTrackCargoSpinner.getSelectedItem().toString().trim();
        String trackNo = mTrackNo.getText().toString().trim();
        String providersHint = "";

        if (SingletonTrackingConstraint.getInstance().getHashMap().containsKey(selectedTrackType)) {
            trackingNoConstraintResponse = SingletonTrackingConstraint.getInstance().getHashMap().get(selectedTrackType);

        }
        if (mTrackCargoSpinner.getSelectedItem().equals("Select Category...")) {
            dialogMsg = getString(R.string.err_msg_track_cargo_dialog);
            openDialog();
        } else if (trackNo.isEmpty()) {
            dialogMsg = "Please enter " + selectedTrackType;
            openDialog();


        } else if (!trackingNoConstraintResponse.getStartWith().isEmpty()) {
            if (trackingNoConstraintResponse.getStartWith().equals("C")) {
                String regex = "[a-z]+";
                String nRegex = "[0-9]+";

                int length = trackNo.length();
                int index = length;
                if (length > 4) {
                    index = 4;
                }
                if (!trackNo.substring(0, index).matches(regex)) {
                    dialogMsg = selectedTrackType + " first  " + trackingNoConstraintResponse.getStartWithLength() + " letter should be char ";
                    openDialog();
                } else if(length > 4 && !trackNo.substring(4).matches(nRegex)){
                    dialogMsg = selectedTrackType+" should have numeric except initial 4 chars. ";
                    openDialog();
                }
                else if (length < trackingNoConstraintResponse.getMinLength()) {
                    dialogMsg = selectedTrackType + " minimum length should be " + trackingNoConstraintResponse.getMinLength();
                    openDialog();
                } else if (length > trackingNoConstraintResponse.getMaxLength()) {
                    dialogMsg = selectedTrackType + " maximum length should be " + trackingNoConstraintResponse.getMaxLength();
                    openDialog();


                } else {
                    Intent TraceOrderIntent = new Intent(TrackCargoActivity.this, TraceOrderActivity.class);
                    startActivity(TraceOrderIntent);
                }


            }

        } else if (trackNo.length() < trackingNoConstraintResponse.getMinLength()) {
            dialogMsg = selectedTrackType + " minimum length should be " + trackingNoConstraintResponse.getMinLength();
            openDialog();
        } else if (trackNo.length() > trackingNoConstraintResponse.getMaxLength()) {
            dialogMsg = selectedTrackType + " maximum length should be " + trackingNoConstraintResponse.getMaxLength();
            openDialog();


        } else {

            trackingId = trackingNoConstraintResponse.getTrackingId();
            Intent traceOrderIntent = new Intent(TrackCargoActivity.this, TraceCargoActivity.class);
            traceOrderIntent.putExtra(KEY_TRACKING_NO,trackNo);
            traceOrderIntent.putExtra(KEY_TRACKING_ID,trackingId);
            startActivity(traceOrderIntent);
        }

    }


    public void openDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this).setTitle(getString(R.string.err_msg_title_dialog)).setMessage(dialogMsg).setCancelable(true).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
