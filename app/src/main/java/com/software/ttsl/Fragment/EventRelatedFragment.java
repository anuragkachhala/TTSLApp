package com.software.ttsl.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.software.ttsl.AccountDetailsActivity;
import com.software.ttsl.ContactDetailActivity;
import com.software.ttsl.EventDetailActivity;
import com.software.ttsl.LeadDetailActivity;
import com.software.ttsl.R;
import com.software.ttsl.Request.EventDataModel;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.AlertDialogManager;
import com.software.ttsl.Utils.DateAndTimeUtil;
import com.software.ttsl.Utils.EmployConstantUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventRelatedFragment extends Fragment implements View.OnClickListener {


    @BindView(R.id.tv_event_title)
    TextView textViewEventTitle;

    @BindView(R.id.tv_event_date)
    TextView textViewEventDate;

    @BindView(R.id.tv_event_time)
    TextView textViewEventTime;

    @BindView(R.id.tv_event_location)
    TextView textViewEventLocation;

    @BindView(R.id.layout_event_contact)
    LinearLayout linearLayoutEventContact;

    @BindView(R.id.tv_event_contact)
    TextView textViewEventContact;

    @BindView(R.id.tv_label_contact)
    TextView textViewLabelContact;

    @BindView(R.id.layout_event_account)
    LinearLayout linearLayoutEventAccount;

    @BindView(R.id.tv_event_account)
    TextView textViewEventAccount;


    private long eventID;
    private DataBaseAdapter dataBaseAdapter;
    private EventDataModel eventDataModel;
    private EventDetailActivity eventDetailActivity;
    private long contactID, leadId,accountID;
    private String eventFromDate, eventToDate;
    private boolean isLead = false;

    public EventRelatedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_reletad, container, false);
        ButterKnife.bind(this, view);
        dataBaseAdapter = new DataBaseAdapter(getContext());
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            eventID = bundle.getLong(EmployConstantUtil.KEY_EVENT_ID, 0);
        }


        eventDetailActivity = (EventDetailActivity) getActivity();
        eventDataModel = eventDetailActivity.getEventData();

        setClickListener();
        updateUI();

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    public void  updateUI(){
        eventDataModel = eventDetailActivity.getEventData();
        setEventInfo();
    }


    private void setEventInfo() {
        eventFromDate = DateAndTimeUtil.longToDate(eventDataModel.getFromDate(), EmployConstantUtil.DATE_FORMAT);
        eventToDate = DateAndTimeUtil.longToDate(eventDataModel.getToDate(), EmployConstantUtil.DATE_FORMAT);
        textViewEventTitle.setText(eventDataModel.getTitle());
        if (eventDataModel.getAllDay()) {
            textViewEventDate.setText(eventFromDate + " " + "To\n" + eventToDate);
            textViewEventTime.setText("All Day");
        } else {
            textViewEventDate.setText(eventFromDate);
            textViewEventTime.setText(DateAndTimeUtil.longToTime(eventDataModel.getFromTime()) + " To " + DateAndTimeUtil.longToTime(eventDataModel.getToTime()));
        }

        if (!eventDataModel.getContact().isEmpty()) {
            linearLayoutEventContact.setVisibility(View.VISIBLE);
            textViewEventContact.setText(eventDataModel.getContact());
            contactID = eventDataModel.getContactId();
        }

       if(!eventDataModel.getAccount().isEmpty()){
            linearLayoutEventAccount.setVisibility(View.VISIBLE);
            textViewEventAccount.setText(eventDataModel.getAccount());
            accountID = eventDataModel.getAccountId();

        }
        if (!eventDataModel.getContact().isEmpty()) {
            linearLayoutEventContact.setVisibility(View.VISIBLE);
            textViewEventContact.setText(eventDataModel.getContact());
            contactID = eventDataModel.getContactId();
            leadId = 0;
            textViewLabelContact.setText("Contact");

        }
        if (!eventDataModel.getLeadName().isEmpty()) {
            linearLayoutEventContact.setVisibility(View.VISIBLE);
            textViewEventContact.setText(eventDataModel.getLeadName());
            leadId = eventDataModel.getLeadId();
            contactID=0;
            isLead = true;
            textViewLabelContact.setText("Lead");
        }


    }

    private void setClickListener() {
        linearLayoutEventContact.setOnClickListener(this);
        linearLayoutEventAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id) {

            case  R.id.layout_event_account:
                if(dataBaseAdapter.getAccountById(accountID)!=null) {
                    startNewActivity(AccountDetailsActivity.class, accountID, EmployConstantUtil.KEY_ACCOUNT_ID);
                }else {
                    new AlertDialogManager().showAlertDialog(getContext(),"","This account has been Deleted",false);
                }
                break;

            case R.id.layout_event_contact:
                if(isLead){
                    if(dataBaseAdapter.getLeadByID(leadId)!=null  ){

                        startNewActivity(LeadDetailActivity.class,leadId,EmployConstantUtil.KEY_LEAD_ID);
                    }
                    else {
                        new AlertDialogManager().showAlertDialog(getContext(),"","This Lead has been Deleted",false);
                    }
                }else {
                    if (dataBaseAdapter.getContactById(contactID) != null) {
                        startNewActivity(ContactDetailActivity.class, contactID, EmployConstantUtil.KEY_CONTACT_ID);
                    }
                    else {
                        new AlertDialogManager().showAlertDialog(getContext(),"","This Contact has been Deleted",false);
                    }
                }
                break;
        }

    }

    private void startNewActivity(Class<?> cls, long id, String key) {
        Intent intent = new Intent(getContext(), cls);
        intent.putExtra(key, id);
        startActivity(intent);
    }
}
