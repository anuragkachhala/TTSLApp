package com.software.ttsl.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.software.ttsl.EventDetailActivity;
import com.software.ttsl.R;
import com.software.ttsl.Request.EventDataModel;
import com.software.ttsl.Request.TaskDataModel;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.TaskDetailActivity;
import com.software.ttsl.Utils.DateAndTimeUtil;
import com.software.ttsl.Utils.EmployConstantUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventDetailFragment extends Fragment {


    @BindView(R.id.tv_event_title)
    TextView textViewEventTitle;

    @BindView(R.id.tv_event_from)
    TextView textViewEventFrom;

    @BindView(R.id.tv_title_label)
    TextView textViewDetailLabel;

    @BindView(R.id.tv_event_location)
    TextView textViewEventLocation;

    @BindView(R.id.tv_event_to)
    TextView textViewEventTo;

    @BindView(R.id.tv_event_host)
    TextView textViewEventHost;

    @BindView(R.id.tv_event_contact)
    TextView textViewEventContact;

    @BindView(R.id.tv_event_created_by)
    TextView textViewEventCreatedBy;

    @BindView(R.id.tv_event_modified_by)
    TextView textViewEventModifiedBy;

    @BindView(R.id.tv_event_create_time)
    TextView textViewCreateTime;

    @BindView(R.id.tv_event_modified_time)
    TextView textViewEventModifiedTime;

    @BindView(R.id.tv_event_description)
    TextView textViewEventDescription;

    @BindView(R.id.linear_layout_event_location)
    LinearLayout linearLayoutEventLocation;

    @BindView(R.id.linear_layout_event_contact)
    LinearLayout linearLayoutEventContact;

    @BindView(R.id.layout_event_description)
    LinearLayout linearLayoutDescription;

    @BindView(R.id.tv_smart_view)
    TextView textViewSmartView;

    @BindView(R.id.linear_layout_event_all_day)
    LinearLayout linearLayoutEventAddDay;

    @BindView(R.id.tv_event_all_day)
    TextView textViewEventAllDay;


    private DataBaseAdapter dataBaseAdapter;
    private Long eventId;
    private EventDataModel eventDataModel;
    private EventDetailActivity eventDetailActivity;

    public EventDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_detail, container, false);
        ButterKnife.bind(this,view);

        textViewDetailLabel.setText(Html.fromHtml(EmployConstantUtil.ASTERISK+ getResources().getString(R.string.label_event_title)));
        dataBaseAdapter = new DataBaseAdapter(getContext());
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            eventId = bundle.getLong(EmployConstantUtil.KEY_EVENT_ID, 0);
        }


        textViewSmartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smartView();
            }
        });


        getEvent();
        setEventInfo();

        return view;
    }

    private void setEventInfo() {
        textViewEventTitle.setText(eventDataModel.getTitle());
        textViewEventCreatedBy.setText(eventDataModel.getCreatedBy());
        textViewEventModifiedBy.setText(eventDataModel.getModifyBy());
        textViewCreateTime.setText(DateAndTimeUtil.longToDate(eventDataModel.getCreatedTime(),EmployConstantUtil.DATE_FORMAT));
        textViewEventModifiedTime.setText(DateAndTimeUtil.longToDate(eventDataModel.getModifyTime(),EmployConstantUtil.DATE_FORMAT));
        textViewEventFrom.setText(DateAndTimeUtil.longToDate(eventDataModel.getFromDate(),EmployConstantUtil.DATE_FORMAT)+", "+DateAndTimeUtil.longToTime(eventDataModel.getFromTime()));
        textViewEventTo.setText(DateAndTimeUtil.longToDate(eventDataModel.getToDate(),EmployConstantUtil.DATE_FORMAT+", "+DateAndTimeUtil.longToTime(eventDataModel.getToTime())));
        textViewEventHost.setText(eventDataModel.getHost());
        if(!eventDataModel.getLocation().isEmpty()){
            linearLayoutEventLocation.setVisibility(View.VISIBLE);
            textViewEventLocation.setText(eventDataModel.getLocation());
        }if(!eventDataModel.getContact().isEmpty()){
            linearLayoutEventContact.setVisibility(View.VISIBLE);
            textViewEventContact.setText(eventDataModel.getContact());
        }if(!eventDataModel.getDescription().isEmpty()){
            linearLayoutDescription.setVisibility(View.VISIBLE);
            textViewEventDescription.setText(eventDataModel.getDescription());
        }if(eventDataModel.getAllDay()){
            linearLayoutEventAddDay.setVisibility(View.VISIBLE);
            textViewEventAllDay.setText("YES");
        }



    }

    private void smartView() {
        if (textViewSmartView.getText().toString().equals(getResources().getString(R.string.footer_show_all_fields))) {
            textViewSmartView.setText(getResources().getString(R.string.footer_smart_view));
            linearLayoutDescription.setVisibility(View.VISIBLE);
            linearLayoutEventContact.setVisibility(View.VISIBLE);
            linearLayoutEventLocation.setVisibility(View.VISIBLE);
        }else {
            textViewSmartView.setText(getResources().getString(R.string.footer_show_all_fields));
            linearLayoutDescription.setVisibility(View.GONE);
            linearLayoutEventContact.setVisibility(View.GONE);
            linearLayoutEventLocation.setVisibility(View.GONE);

            //setEventInfo();

        }

    }

    private void getEvent() {
        eventDetailActivity = (EventDetailActivity) getActivity();
        eventDataModel = eventDetailActivity.getEventData();
    }


    @Override
    public void onResume() {
        super.onResume();

    }
}
