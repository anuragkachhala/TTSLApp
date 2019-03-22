package com.software.ttsl.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.software.ttsl.CallDetailActivity;
import com.software.ttsl.ContactDetailActivity;
import com.software.ttsl.R;
import com.software.ttsl.Request.CallDataModel;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.DateAndTimeUtil;
import com.software.ttsl.Utils.EmployConstantUtil;
import com.software.ttsl.model.AddContactData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CallDetailFragment extends Fragment {

    @BindView(R.id.linear_layout_call_type)
    LinearLayout linearLayoutCallType;

    @BindView(R.id.linear_layout_call_contact)
    LinearLayout linearLayoutCallContact;

    @BindView(R.id.linear_layout_call_purpose)
    LinearLayout linearLayoutCallPurpose;

    @BindView(R.id.linear_layout_call_account)
    LinearLayout linearLayoutCallAccount;

    @BindView(R.id.linear_layout_call_description)
    LinearLayout linearLayoutCallDescription;

    @BindView(R.id.linear_layout_call_result)
    LinearLayout linearLayoutCallResult;

    @BindView(R.id.tv_call_type)
    TextView textViewCallType;

    @BindView(R.id.tv_call_contact)
    TextView textViewCallContact;

    @BindView(R.id.tv_call_subject)
    TextView textViewCallSubject;

    @BindView(R.id.tv_call_purpose)
    TextView textViewCallPurpose;

    @BindView(R.id.tv_call_account)
    TextView textViewCallAccount;

    @BindView(R.id.tv_call_type1)
    TextView textViewCallType1;

    @BindView(R.id.tv_call_start_time)
    TextView textViewCallStartTime;

    @BindView(R.id.tv_call_duration)
    TextView textViewCallDuration;

    @BindView(R.id.tv_call_description)
    TextView textViewCallDescription;

    @BindView(R.id.tv_call_result)
    TextView textViewCallResult;

    @BindView(R.id.tv_call_created_by)
    TextView textViewCallCreatedBy;

    @BindView(R.id.tv_call_modified_by)
    TextView textViewCallModifedBy;

    @BindView(R.id.tv_call_created_time)
    TextView textViewCallCreatedTime;

    @BindView(R.id.tv_call_modified_time)
    TextView textViewCallModifedTime;

    @BindView(R.id.tv_smart_view)
    TextView textViewSmartView;

    @BindView(R.id.tv_header_call_subject)
    TextView textViewHeaderCallSubject;

    @BindView(R.id.tv_header_call_duration)
    TextView textViewHeaderCallDuration;

    @BindView(R.id.tv_header_call_time)
    TextView textViewHeaderCallTime;

    @BindView(R.id.tv_header_call_type1)
    TextView textViewHeaderCallType1;


    @BindView(R.id.tv_call_contact_label)
    TextView textViewContactCallLabel;


    private DataBaseAdapter dataBaseAdapter;
    private Long callID;
    private CallDataModel callDataModel;
    private CallDetailActivity callDetailActivity;

    public CallDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_call_detail, container, false);

        ButterKnife.bind(this,view);

        dataBaseAdapter = new DataBaseAdapter(getContext());
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            callID = bundle.getLong(EmployConstantUtil.KEY_CALL_ID, 0);
        }




        textViewSmartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smartView();
            }
        });


        getCallData();

        return view;
    }

    private void smartView() {

        if (textViewSmartView.getText().toString().equals(getResources().getString(R.string.footer_show_all_fields))) {
            textViewSmartView.setText(getResources().getString(R.string.footer_smart_view));
            linearLayoutCallPurpose.setVisibility(View.VISIBLE);
            linearLayoutCallResult.setVisibility(View.VISIBLE);
            linearLayoutCallDescription.setVisibility(View.VISIBLE);
            linearLayoutCallAccount.setVisibility(View.VISIBLE);
            linearLayoutCallContact.setVisibility(View.VISIBLE);
            linearLayoutCallType.setVisibility(View.VISIBLE);
        }else {
            textViewSmartView.setText(getResources().getString(R.string.footer_show_all_fields));
            linearLayoutCallPurpose.setVisibility(View.GONE);
            linearLayoutCallResult.setVisibility(View.GONE);
            linearLayoutCallDescription.setVisibility(View.GONE);
            linearLayoutCallAccount.setVisibility(View.GONE);
            linearLayoutCallContact.setVisibility(View.GONE);
            linearLayoutCallType.setVisibility(View.GONE);
        }
    }

    private void getCallData() {
        textViewHeaderCallType1.setText(Html.fromHtml(EmployConstantUtil.ASTERISK+getResources().getString(R.string.label_call_type1)));
        textViewHeaderCallSubject.setText(Html.fromHtml(EmployConstantUtil.ASTERISK+getResources().getString(R.string.label_call_subject)));
        textViewHeaderCallDuration.setText(Html.fromHtml(EmployConstantUtil.ASTERISK+getResources().getString(R.string.label_call_duration)));
        textViewHeaderCallTime.setText(Html.fromHtml(EmployConstantUtil.ASTERISK+getResources().getString(R.string.label_call_start_time)));
        callDetailActivity =(CallDetailActivity)getActivity();
        callDataModel  = callDetailActivity.getCallDataModel();
        setCallDetails();
    }

    private void setCallDetails() {
        textViewCallSubject.setText(callDataModel.getSubject());
        textViewCallCreatedBy.setText(callDataModel.getCreatedBy());
        textViewCallModifedBy.setText(callDataModel.getModifiedBy());
        textViewCallCreatedTime.setText(DateAndTimeUtil.longToDate(callDataModel.getCreatedTime(),EmployConstantUtil.DATE_FORMAT));
        textViewCallModifedTime.setText(DateAndTimeUtil.longToDate(callDataModel.getModifiedTime(),EmployConstantUtil.DATE_FORMAT));
        textViewCallType1.setText(callDataModel.getCallType());
        textViewCallStartTime.setText(DateAndTimeUtil.longToDate(callDataModel.getCallStartDate(),EmployConstantUtil.DATE_FORMAT)+ " "+DateAndTimeUtil.longToDate(callDataModel.getCallStartTime(),EmployConstantUtil.TIME_FORMAT));
        textViewCallDuration.setText(DateAndTimeUtil.longToTime(callDataModel.getCallDuration(),EmployConstantUtil.TIME_FORMAT_DURATION));
        if(!callDataModel.getContact().isEmpty()){
            linearLayoutCallContact.setVisibility(View.VISIBLE);
            textViewContactCallLabel.setText("Contact");
            textViewCallContact.setText(callDataModel.getContact());
        }
        if(callDataModel.getContactLeadName()!=null && !callDataModel.getContactLeadName().isEmpty()){
            linearLayoutCallContact.setVisibility(View.VISIBLE);
            textViewContactCallLabel.setText("Lead");
            textViewCallContact.setText(callDataModel.getContactLeadName());
        }

        if(callDataModel.getAccount()!=null && !callDataModel.getAccount().isEmpty()){
            linearLayoutCallAccount.setVisibility(View.VISIBLE);
            textViewCallAccount.setText(callDataModel.getAccount());
        }if(!callDataModel.getDescription().isEmpty()){
            linearLayoutCallDescription.setVisibility(View.VISIBLE);
            textViewCallDescription.setText(callDataModel.getDescription());
        }if(!callDataModel.getCallResult().isEmpty()){
            linearLayoutCallResult.setVisibility(View.VISIBLE);
            textViewCallResult.setText(callDataModel.getCallResult());
        }if(!callDataModel.getCallPurpose().isEmpty()){
            linearLayoutCallPurpose.setVisibility(View.VISIBLE);
            textViewCallPurpose.setText(callDataModel.getCallPurpose());
        }if(!callDataModel.getType().isEmpty()){
            linearLayoutCallType.setVisibility(View.VISIBLE);
            textViewCallType.setText(callDataModel.getType());
        }

    }

}
