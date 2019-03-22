package com.software.ttsl.NewModule;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import com.software.ttsl.TransportApp;
import com.software.ttsl.Utils.ConnectivityReceiver;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */


public class TrackCargoFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener, ConnectivityReceiver.ConnectivityReceiverListener {

    public static final String TAG = TrackCargoFragment.class.getName();

    public static final String CONTAINER_NO = "container_no";
    public static final String HBL_NO = "hbl_no";


    @BindView(R.id.spinner_cargo_type)
    Spinner spinnerCargoType;

    @BindView(R.id.et_track_no)
    EditText editTextTrackNo;

    @BindView(R.id.btn_track_cargo)
    Button buttonTrackCargo;

    String[] trackingNoList;
    String selectedTrackType;
    String dialogMsg;
    private Intent intent;


    public TrackCargoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_track_cargo2, container, false);
        ButterKnife.bind(this, view);

        trackingNoList = getResources().getStringArray(R.array.tracking_list);
        setSpinner();
        TransportApp.getInstance().setConnectivityListener(this);
        buttonTrackCargo.setOnClickListener(this);
        return view;
    }


    public void setSpinner() {
        // Spinner click listener
        spinnerCargoType.setOnItemSelectedListener(this);


        String[] tackingNoList = new String[trackingNoList.length + 1];

        tackingNoList[0] = "Select Category...";
        int i = 1;
        for (String trackingType : trackingNoList) {
            tackingNoList[i] = trackingType;
            i++;
        }


        /* Create an ArrayAdapter using the string array and a default spinner layout */
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, tackingNoList) {

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
        spinnerCargoType.setAdapter(adapter);
    }


    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id) {
            case R.id.btn_track_cargo:
                if (checkTrackValidation() && checkConnection()) {
                    switch (selectedTrackType) {
                        case "Container Number":
                            intent = new Intent(getContext(), ContainerDetailActivity.class);
                            intent.putExtra(CONTAINER_NO, editTextTrackNo.getText().toString().toUpperCase());
                            startActivity(intent);
                            break;
                        case "HBL Number":
                            intent = new Intent(getContext(), HBLDetailsActivity.class);
                            intent.putExtra(HBL_NO, editTextTrackNo.getText().toString().toUpperCase());
                            startActivity(intent);
                            break;

                    }
                }
        }
    }

    private boolean checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        if (!isConnected) {
            dialogMsg = "check your network connection";
            openDialog();
            return false;
        }

        return true;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        selectedTrackType = parent.getItemAtPosition(position).toString();
        if (!selectedTrackType.equals("Select Category...")) {
            // trackingId = SingletonTrackingConstraint.getInstance().getHashMap().get(selectedTrackType).getTrackingId();

        }
        editTextTrackNo.setHint("Enter " + selectedTrackType);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    public boolean checkTrackValidation() {
        String trackNo = editTextTrackNo.getText().toString().trim();
        if (spinnerCargoType.getSelectedItem().equals("Select Category...")) {
            dialogMsg = getString(R.string.err_msg_track_cargo_dialog);
            openDialog();
            return false;
        } else if (trackNo.isEmpty()) {
            dialogMsg = "Please enter " + selectedTrackType;
            openDialog();
            return false;

        } else if (spinnerCargoType.getSelectedItem().equals("Container Number")) {
            String regex = "[A-Za-z]+";
            String nRegex = "[0-9]+";

            int length = trackNo.length();
            int index = length;
            if (length > 4) {
                index = 4;
            }
            if (!trackNo.substring(0, index).matches(regex)) {
                dialogMsg = selectedTrackType + " first  4 letter should be char ";
                openDialog();
                return false;
            } else if (length > 4 && !trackNo.substring(4).matches(nRegex)) {
                dialogMsg = selectedTrackType + " should have numeric except initial 4 chars. ";
                openDialog();
                return false;
            } else if (length < 11) {
                dialogMsg = selectedTrackType + " minimum length should be 11 digit";
                openDialog();
                return false;
            } else if (length > 11) {
                dialogMsg = selectedTrackType + " maximum length should be 11 digit";
                openDialog();
                return false;

            } else {
                return true;
            }
        } else if (spinnerCargoType.getSelectedItem().equals("HBL Number")) {
            int length = trackNo.length();
            if (length < 16) {
                dialogMsg = selectedTrackType + " minimum length should be 16 digit";
                openDialog();
                return false;
            } else if (length > 20) {
                dialogMsg = selectedTrackType + " maximum length should be 20 digit";
                openDialog();
                return false;

            } else {
                return true;
            }
        }

        return true;
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


    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

    }
}
