package com.software.ttsl.Fragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.software.ttsl.R;
import com.software.ttsl.Utils.SpinnerManager;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CargoSecondFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String TAG = CargoSecondFragment.class.getName();
    
    @BindView(R.id.spinner_packing_type)
    Spinner mPackingTypeSpinner; 
    
    @BindView(R.id.et_commodity)
    EditText mCommodityName;
    
    @BindView(R.id.et_from_port)
    EditText mSourcePort;
    
    @BindView(R.id.et_to_port)
    EditText  mDestinationPort;
    
    @BindView(R.id.widget_commodity)
    TextInputLayout mCommodityLayout;
    
    @BindView(R.id.widget_from_port)
    TextInputLayout  mSourcePortLayout;
    
    @BindView(R.id.widget_to_port)
    TextInputLayout mDestinationPortLayout;
    
    
    private Switch mSwitch;
    private String packingType[];

    public CargoSecondFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cargo_second, container, false);

        ButterKnife.bind(this,view);
        mDestinationPort.addTextChangedListener(new InputTextWatcher(mDestinationPort));
        mCommodityName.addTextChangedListener(new InputTextWatcher(mCommodityName));
        packingType = getResources().getStringArray(R.array.packing_type);
        mPackingTypeSpinner.setAdapter(SpinnerManager.setSpinner(getContext(),packingType));
        return view;
        }

   

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public boolean checkEntries() {
        if (!checkCommodityName()) {
            return false;
        }
        if (!checkPackingType()) {
            return false;
        }
        if (!checkFromPort()) {
            return false;
        }
        if (!checkToPort()) {
            return false;
        }
        return true;
    }

    private boolean checkCommodityName() {
        if (mCommodityName.getText().toString().trim().isEmpty()) {
            mCommodityLayout.setErrorEnabled(true);
            mCommodityLayout.setError(getString(R.string.err_msg_commodity_name));
            return false;

        } else {
            mCommodityLayout.setErrorEnabled(false);

        }

        return true;
    }


    private boolean checkPackingType() {
        int selectedItemSpinner = mPackingTypeSpinner.getSelectedItemPosition();


        if (selectedItemSpinner == 0) {
            View selectedView = mPackingTypeSpinner.getSelectedView();
            if (selectedView != null && selectedView instanceof TextView) {
                mPackingTypeSpinner.requestFocus();
                TextView selectedTextView = (TextView) selectedView;
                selectedTextView.setError(""); // any name of the error will do
                selectedTextView.setTextColor(getResources().getColor(R.color.colorAccent)); //text color in which you want your error message to be displayed
                selectedTextView.setText(getString(R.string.err_msg_packing_type));// actual error message
                mPackingTypeSpinner.performClick(); // to open the spinner list if error is found.

            }

            return false;
        }
        return true;
    }


    private boolean checkToPort() {
        if (mDestinationPort.getText().toString().trim().isEmpty()) {
            mDestinationPortLayout.setErrorEnabled(true);
            mDestinationPortLayout.setError(getString(R.string.err_msg_from_port));
            return false;
        } else {
            mDestinationPortLayout.setErrorEnabled(false);
        }

        return true;
    }

    private boolean checkFromPort() {
        if (mSourcePort.getText().toString().trim().isEmpty()) {
            mSourcePortLayout.setErrorEnabled(true);
            mSourcePortLayout.setError(getString(R.string.err_msg_from_port));
            return false;
        } else {
            mSourcePortLayout.setErrorEnabled(false);
        }
        return true;
    }


    private class InputTextWatcher implements TextWatcher {

        private View view;

        private InputTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.et_commodity:
                    checkCommodityName();
                    break;

                case R.id.et_from_port:
                    checkFromPort();
                    break;
                case R.id.et_to_port:
                    checkToPort();
                    break;
            }
        }
    }
}
