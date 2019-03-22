package com.software.ttsl.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import com.software.ttsl.R;
import com.software.ttsl.Response.TrackingNoConstraintResponse;
import com.software.ttsl.TraceOrderActivity;
import com.software.ttsl.TrackCargoActivity;
import com.software.ttsl.Utils.SingletonTrackingConstraint;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CargoFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {


    @BindView(R.id.spinner_cargo_type)
    Spinner mTrackCargoSpinner;

    @BindView(R.id.btn_track_cargo)
    Button mTrackBtn;

    @BindView(R.id.et_track_no)
    EditText mTrackNo;

    @BindView(R.id.tv_hint)
    TextView textViewHint;


    String[] mBillCategory;
    String[] trackingNumberList;
    TrackingNoConstraintResponse trackingNoConstraintResponse;
    String mSelectedType, mDialogMsg;
    String selectedTrackNo, dialogMsg;
    View view;

    public CargoFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        view = inflater.inflate(R.layout.fragment_track_cargo, null);

        ButterKnife.bind(this,view);


        //initialization();
        //set spinner.........
        setSpinner();

        //add listenter....
        mTrackBtn.setOnClickListener(this);

        return view;


    }


    /*public void setSpinner() {
        // Spinner click listener
        mTrackCargoSpinner.setOnItemSelectedListener(this);

        // get all category from resource file
        mBillCategory = getResources().getStringArray(R.array.track_type);

        *//* Create an ArrayAdapter using the string array and a default spinner layout *//*
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, mBillCategory) {

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
*/


    public void setSpinner() {
        // Spinner click listener
        mTrackCargoSpinner.setOnItemSelectedListener(this);

        // get all category from resource file
        //trackingNumberList= getResources().getStringArray(R.array.track_type);

        Set<String> trackingNoList = SingletonTrackingConstraint.getInstance().getHashMap().keySet();
        trackingNumberList = new String[trackingNoList.size() + 1];

        trackingNumberList[0] = "Select Category...";
        int i = 1;
        for (String trackingType : trackingNoList) {

            trackingNumberList[i] = trackingType;
            i++;

        }

        /* Create an ArrayAdapter using the string array and a default spinner layout */
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, trackingNumberList) {

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

    /*private void initialization() {
        //spinner element
        mTrackCargoSpinner = (Spinner) view.findViewById(R.id.spinner_cargo_type);
        mTrackBtn = (Button) view.findViewById(R.id.btn_track);
        mTrackNo = (EditText) view.findViewById(R.id.et_track_no);
    }*/

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        mSelectedType = parent.getItemAtPosition(position).toString();
        if(position!=0) {
            mTrackNo.setHint("Enter " + mSelectedType);
        }

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

    /*public void checkTrackValidation() {
        String trackNo = mTrackNo.getText().toString().trim();
        if (mTrackCargoSpinner.getSelectedItem().equals("Select Category...")) {
            mDialogMsg = getString(R.string.err_msg_track_cargo_dialog);
            openDialog();
        } else if (trackNo.isEmpty()) {
            mDialogMsg = "Please enter " + mSelectedType;
            openDialog();

        } else {
            Intent TraceOrderIntent = new Intent(getContext(), TrackCargoActivity.class);
            startActivity(TraceOrderIntent);
        }

    }
*/

    public void checkTrackValidation() {
        String SelectedType = mTrackCargoSpinner.getSelectedItem().toString().trim();
        String trackNo = mTrackNo.getText().toString().trim();
        String providersHint = "";

        if (SingletonTrackingConstraint.getInstance().getHashMap().containsKey(selectedTrackNo)) {
            trackingNoConstraintResponse = SingletonTrackingConstraint.getInstance().getHashMap().get(selectedTrackNo);

        }
        if (mTrackCargoSpinner.getSelectedItem().equals("Select Category...")) {
            dialogMsg = getString(R.string.err_msg_track_cargo_dialog);
            openDialog();
        } else if (trackNo.isEmpty()) {
            dialogMsg = "Please enter " + selectedTrackNo;
            openDialog();


        } else if (!trackingNoConstraintResponse.getStartWith().isEmpty()) {
            if (trackingNoConstraintResponse.getStartWith().equals("C")) {
                String regex = "[a-z]+";

                int i = trackNo.length();
                if (i > 4) {
                    i = 4;
                }
                if (!trackNo.substring(0, i).matches(regex)) {
                    dialogMsg = selectedTrackNo + " first  " + trackingNoConstraintResponse.getStartWithLength() + " letter should be char ";
                    openDialog();
                } else if (trackNo.length() < trackingNoConstraintResponse.getMinLength()) {
                    dialogMsg = selectedTrackNo + " minimum length should be " + trackingNoConstraintResponse.getMinLength();
                    openDialog();
                } else if (trackNo.length() > trackingNoConstraintResponse.getMaxLength()) {
                    dialogMsg = selectedTrackNo + " maximum length should be " + trackingNoConstraintResponse.getMaxLength();
                    openDialog();


                } else {
                    Intent TraceOrderIntent = new Intent(getContext(), TraceOrderActivity.class);
                    startActivity(TraceOrderIntent);
                }


            }

        } else if (trackNo.length() < trackingNoConstraintResponse.getMinLength()) {
            dialogMsg = selectedTrackNo + " minimum length should be " + trackingNoConstraintResponse.getMinLength();
            openDialog();
        } else if (trackNo.length() > trackingNoConstraintResponse.getMaxLength()) {
            dialogMsg = selectedTrackNo + " maximum length should be " + trackingNoConstraintResponse.getMaxLength();
            openDialog();


        } else {
            Intent TraceOrderIntent = new Intent(getContext(), TraceOrderActivity.class);
            startActivity(TraceOrderIntent);
        }

    }
    public void openDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext()).setTitle(getString(R.string.err_msg_title_dialog)).setMessage(dialogMsg).setCancelable(true).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
