package com.software.ttsl.NewModule;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.software.ttsl.R;
import com.software.ttsl.SelectPortActivity;
import com.software.ttsl.TransportApp;
import com.software.ttsl.Utils.AlertDialogManager;
import com.software.ttsl.Utils.ConnectivityReceiver;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;
import static com.software.ttsl.NewModule.SailScheduleFragment.REQUEST_LOADING_PORT;
import static com.software.ttsl.NewModule.SailScheduleFragment.SELECTED_PORT;
import static com.software.ttsl.NewModule.SailScheduleFragment.SELECTED_PORT_COUNTRY;
import static com.software.ttsl.NewModule.SailScheduleFragment.SELECTED_PORT_STATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class AgentInfoFragment extends Fragment implements View.OnClickListener, ConnectivityReceiver.ConnectivityReceiverListener {

    public static final String PORT_NAME = "port_name";


    @BindView(R.id.btn_get_agent_info)
    Button buttonAgentInfo;

    @BindView(R.id.et_port_no)
    TextView textViewPortNo;

    @BindView(R.id.hint)
    TextView textViewHint;

    private String portName;
    private AlertDialogManager alertDialogManager;

    public AgentInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agent_info, container, false);
        ButterKnife.bind(this, view);
        alertDialogManager = new AlertDialogManager();
        TransportApp.getInstance().setConnectivityListener(this);
        buttonAgentInfo.setOnClickListener(this);
        textViewPortNo.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_get_agent_info:

                if ((checkValidation()) && (checkConnection())) {
                    Intent intent = new Intent(getContext(), AgentInfo.class);
                    intent.putExtra(PORT_NAME, portName);
                    startActivity(intent);
                }
                break;
            case R.id.et_port_no:
                textViewHint.setVisibility(View.INVISIBLE);
                startActivityForResult(SelectPortActivity.class, REQUEST_LOADING_PORT);

                break;

        }
    }

    private boolean checkValidation() {
        if (textViewPortNo.getText().toString().isEmpty()) {
            textViewHint.setVisibility(View.VISIBLE);
            return false;
        }
        return true;
    }


    private boolean checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        if (!isConnected) {
            alertDialogManager.showAlertDialog(getContext(), "Network Error", "check your network connection", true);
            return false;
        }

        return true;
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
                    portName = data.getStringExtra(SELECTED_PORT_STATE).trim();
                    textViewPortNo.setText(data.getStringExtra(SELECTED_PORT).trim() + "," + data.getStringExtra(SELECTED_PORT_COUNTRY).trim() + "," + data.getStringExtra(SELECTED_PORT_STATE).trim());


            }
        }


    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

    }
}
