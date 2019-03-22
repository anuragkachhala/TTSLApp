package com.software.ttsl.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.software.ttsl.QuotationFormActivity;
import com.software.ttsl.R;
import com.software.ttsl.Utils.DataSingleton;
import com.software.ttsl.Utils.SpinnerManager;
import com.software.ttsl.model.GetQuotationFormData;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CargoFirstFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String TAG = CargoFirstFragment.class.getName();
    
    @BindView(R.id.spinner_weight_unit)
     Spinner mWeightSpinner;
    
    @BindView(R.id.spinner_volume_unit)
    Spinner mVolumeSpinner;
    
    @BindView(R.id.spinner_imo_class)
    Spinner mImoClassSpinner;
    
    @BindView(R.id.spinner_container_type)
    Spinner mContainerSpinner;
    
    @BindView(R.id.et_weight)
    EditText mWeight;
    
    @BindView(R.id.et_volume)
    EditText mVolume;
    
    @BindView(R.id.view_volume)
    LinearLayout mVolumeView;
    
    @BindView(R.id.widget_weight)
    TextInputLayout mWeightLayout;

    @BindView(R.id.widget_volume)
    TextInputLayout mVolumeLayout;

    @BindView(R.id.container_count_Widget)
    TextInputLayout mContainerCountLayout;
    
    public String[] mWeightType;
    public String[] mVolumeType;
    public String[] mImoClassType;
    public String[] mContainerType;
    public String[] mWagonType;
    public String[] mCargoType;
    public boolean mSwitch = false;
    QuotationFormActivity mQuotationFormActivity;
    ArrayAdapter<String> arrayAdapter;

    
    private GetQuotationFormData getQuotationFormData;
    private DataSingleton mDataSingleton;
    private String errMsgWeight, errMsgVolume;

    public CargoFirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cargo_first, container, false);
        mQuotationFormActivity = (QuotationFormActivity) getActivity();

         ButterKnife.bind(this,view);

        mVolumeLayout.setErrorEnabled(false);
        mWeightLayout.setErrorEnabled(false);


        getResourcesData();
        //set listener to view ..........
        setListener();



        //setSpinnerData(mImoClassType, mImoClassSpinner);

        mImoClassSpinner.setAdapter(SpinnerManager.setSpinner(getContext(),mImoClassType));
        mWeightSpinner.setAdapter(SpinnerManager.setSpinner(getContext(),mWeightType));
        mVolumeSpinner.setAdapter(SpinnerManager.setSpinner(getContext(),mVolumeType));


        mDataSingleton = DataSingleton.getInstance();
        getQuotationFormData = mDataSingleton.getQuotationFormData();
        setFormView(getQuotationFormData.getShippingMode());


        return view;
    }

    private void getResourcesData() {
        mWeightType = getResources().getStringArray(R.array.weight_type);
        mWagonType = getResources().getStringArray(R.array.wagon_type);
        mCargoType = getResources().getStringArray(R.array.cargo_type);
        mVolumeType = getResources().getStringArray(R.array.volume_type);
        mImoClassType = getResources().getStringArray(R.array.imo_class_type);
        mContainerType = getResources().getStringArray(R.array.container_type);
    }

    private void setListener() {
        mWeight.addTextChangedListener(new InputTextWatcher(mWeight));
        mVolume.addTextChangedListener(new InputTextWatcher(mVolume));
        mContainerSpinner.setOnItemSelectedListener(this);
        mImoClassSpinner.setOnItemSelectedListener(this);
        mVolumeSpinner.setOnItemSelectedListener(this);
        mWeightSpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public boolean checkEntries() {

        errMsgWeight = getString(R.string.err_msg_weight);
        errMsgVolume = getString(R.string.erro_msg_volume);

        if (mContainerSpinner.getVisibility() == View.VISIBLE) {
            if (!CheckContainer()) {
                return false;
            }

        }
        if (!checkWeightAndVolume(mWeight, mWeightSpinner, mWeightLayout, errMsgWeight)) {
            return false;
        }

        if (!checkWeightAndVolumeUnit(mWeightSpinner)) {
            return false;
        }
        if (mVolumeView.getVisibility() == View.VISIBLE) {


            if (!checkWeightAndVolume(mVolume, mVolumeSpinner, mVolumeLayout, errMsgVolume)) {
                return false;
            }

            if (!checkWeightAndVolumeUnit(mVolumeSpinner)) {
                return false;
            }
        }

        return true;

    }

    private boolean checkWeightAndVolume(EditText editText, Spinner spinner, TextInputLayout textInputLayout, String errorMassage) {
        TextView textView = (TextView) spinner.getSelectedView();
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) spinner.getLayoutParams();
        if (editText.getText().toString().trim().equals("")) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(errorMassage);
            editText.requestFocus();
            lp.setMargins(0, 0, 0, 38);
            spinner.setLayoutParams(lp);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
            textInputLayout.setError("");
            lp.setMargins(0, 0, 0, 0);
            spinner.setLayoutParams(lp);
            return true;
        }

    }


    private boolean checkWeightAndVolumeUnit(Spinner spinner) {
        int selectedItemSpinner = spinner.getSelectedItemPosition();
        if (selectedItemSpinner == 0) {
            View selectedView = spinner.getSelectedView();
            if (selectedView != null && selectedView instanceof TextView) {
                spinner.requestFocus();
                TextView selectedTextView = (TextView) selectedView;
                selectedTextView.setError(""); // any name of the error will do
                selectedTextView.setTextColor(getResources().getColor(R.color.colorAccent)); //text color in which you want your error message to be displayed
                selectedTextView.setText(getString(R.string.err_msg_weight_unit));
                spinner.performClick(); // to open the spinner list if error is found.

            }

            return false;
        }
        return true;

    }

    private boolean CheckContainer() {
        int selectedItemSpinner = mContainerSpinner.getSelectedItemPosition();
        if (selectedItemSpinner == 0) {
            View selectedView = mContainerSpinner.getSelectedView();
            if (selectedView != null && selectedView instanceof TextView) {
                mContainerSpinner.requestFocus();
                TextView selectedTextView = (TextView) selectedView;
                selectedTextView.setError(""); // any name of the error will do
                selectedTextView.setTextColor(getResources().getColor(R.color.colorAccent)); //text color in which you want your error message to be displayed
                String shippingMode = getQuotationFormData.getShippingMode();
                if (Integer.parseInt(shippingMode) == 1) {
                    selectedTextView.setText(getString(R.string.err_msg_container_type));
                } else if (Integer.parseInt(shippingMode) == 2) {
                    selectedTextView.setText(getString(R.string.err_msg_cargo_type));
                } else {
                    selectedTextView.setText(getString(R.string.err_msg_wagon_type));
                }// actual error message
                mContainerSpinner.performClick(); // to open the spinner list if error is found.

            }

            return false;
        }
        return true;
    }


    public void setFormView(String formView) {
        if (formView == null) {
            formView = "0";
        }
        if (Integer.parseInt(formView) == 1) {
            mContainerSpinner.setAdapter(SpinnerManager.setSpinner(getContext(),mContainerType));
            mContainerSpinner.setVisibility(View.VISIBLE);
            mVolumeView.setVisibility(View.GONE);
            mContainerCountLayout.setVisibility(View.VISIBLE);
        } else if (Integer.parseInt(formView) == 2) {
            mContainerSpinner.setAdapter(SpinnerManager.setSpinner(getContext(),mCargoType));
            mContainerSpinner.setVisibility(View.VISIBLE);
            mVolumeView.setVisibility(View.VISIBLE);
            mContainerCountLayout.setVisibility(View.GONE);
        } else if (Integer.parseInt(formView) == 5) {
            mContainerSpinner.setAdapter(SpinnerManager.setSpinner(getContext(),mWagonType));
            mContainerSpinner.setVisibility(View.VISIBLE);
            mVolumeView.setVisibility(View.VISIBLE);
            mContainerCountLayout.setVisibility(View.VISIBLE);
        } else {
            mVolumeView.setVisibility(View.VISIBLE);
            mContainerSpinner.setVisibility(View.GONE);
            mContainerCountLayout.setVisibility(View.GONE);
        }
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
                case R.id.et_weight:
                    checkWeightAndVolume(mWeight, mWeightSpinner, mWeightLayout, errMsgWeight);
                    break;
                case R.id.et_volume:
                    checkWeightAndVolume(mVolume, mVolumeSpinner, mVolumeLayout, errMsgVolume);
                    break;

            }
        }
    }
}
