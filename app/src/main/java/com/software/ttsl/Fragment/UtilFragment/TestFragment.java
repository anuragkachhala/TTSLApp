package com.software.ttsl.Fragment.UtilFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.software.ttsl.CallDetailActivity;
import com.software.ttsl.EventDetailActivity;
import com.software.ttsl.Interface.ItemClickListener;
import com.software.ttsl.R;
import com.software.ttsl.Request.CallDataModel;
import com.software.ttsl.Request.EventDataModel;
import com.software.ttsl.Request.TaskDataModel;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.TaskDetailActivity;
import com.software.ttsl.Utils.DateAndTimeUtil;
import com.software.ttsl.Utils.EmployConstantUtil;
import com.tekit.solution.horizontalcalender.RWeekCalendar;
import com.tekit.solution.horizontalcalender.listener.CalenderListener;

import org.joda.time.LocalDateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestFragment extends Fragment implements View.OnClickListener, ItemClickListener {

    public int mYear, mMonth, mDay;
    @BindView(R.id.employee_home_event_tv)
    TextView employee_home_event_tv;
    @BindView(R.id.employee_home_task_tv)
    TextView employee_home_task_tv;
    @BindView(R.id.employee_home_call_tv)
    TextView employee_home_call_tv;
    @BindView(R.id.tv)
    TextView mDateSelectedTv;
    @BindView(R.id.employee_layout_event_data)
    LinearLayout employee_layout_event_data;
    @BindView(R.id.employee_linear_layout_event_data)
    LinearLayout employee_linear_layout_event_data;
    @BindView(R.id.employee_layout_taks_data)
    LinearLayout employee_layout_task_data;
    @BindView(R.id.employee_layout_call_date)
    LinearLayout employee_layout_call_data;
    @BindView(R.id.employee_iv_add_event)
    ImageView employee_iv_add_event;
    @BindView(R.id.employee_iv_add_task)
    ImageView employee_iv_add_task;
    @BindView(R.id.employee_iv_add_calls)
    ImageView employee_iv_add_calls;
    LinearLayout mLinearLayout = null;

    RWeekCalendar rCalendarFragment;

    private DataBaseAdapter dataBaseAdapter;
    private List<EventDataModel> eventDataModelList;
    private List<TaskDataModel> taskDataModelList;
    private List<CallDataModel> callDataModelList;
    private String selectedDate;
    private long eventID;
    private long taskID;
    private long callID;


    public TestFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBaseAdapter = new DataBaseAdapter(getContext());


    }

    @Override
    public void onResume() {
        super.onResume();
        employee_layout_event_data.removeAllViews();
        employee_layout_task_data.removeAllViews();
        employee_layout_call_data.removeAllViews();
        addEventRow();
        addTaskRow();
        addCallRow();
    }

    private void addEventRow() {
        eventDataModelList = dataBaseAdapter.getAllEvents();
        employee_layout_event_data.removeAllViews();
        //      employee_layout_event_data.removeAllViews();
        DateFormat df = new SimpleDateFormat("dd-M-yyyy");
        for (EventDataModel eventDataModel : eventDataModelList) {


            if (df.format(eventDataModel.getCreatedTime()).equals(selectedDate)) {

                    final View view = getLayoutInflater().inflate(R.layout.calender_row_event_list, mLinearLayout, true);
                    TextView tv_title_event = view.findViewById(R.id.tv_title_event);

                    tv_title_event.setText(eventDataModel.getTitle());
                    employee_layout_event_data.addView(view);

                    view.setTag(eventDataModel.getEventId());

                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            eventID = (long) view.getTag();
                            Intent eventIntent = new Intent(getContext(), EventDetailActivity.class);
                            eventIntent.putExtra(EmployConstantUtil.KEY_EVENT_ID, eventID);
                            Log.d("test", "TAGView: " + eventID);
                            startActivityForResult(eventIntent, 1000);
                        }
                    });

            }
        }


    }

    private void addTaskRow() {
        taskDataModelList = dataBaseAdapter.getAllTask();

       employee_layout_task_data.removeAllViews();
        //DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        for (TaskDataModel taskDataModel : taskDataModelList) {
            if (DateAndTimeUtil.longToDate1(taskDataModel.getTaskCreatedTime()).equals(selectedDate)) {
                final View view = getLayoutInflater().inflate(R.layout.row_task_list, mLinearLayout, true);
                    TextView tv_task_subject = view.findViewById(R.id.tv_task_subject);
                    TextView tv_task_priority = view.findViewById(R.id.tv_task_priority);
                    TextView tv_task_contact = view.findViewById(R.id.tv_task_contact);

                    tv_task_subject.setText(taskDataModel.getTaskOwner());
                    tv_task_priority.setText(taskDataModel.getTaskPriority());
                    tv_task_contact.setText("");

                    employee_layout_task_data.addView(view);
                    view.setTag(taskDataModel.getTaskId());
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            taskID = (long) view.getTag();
                            Intent taskIntent = new Intent(getContext(), TaskDetailActivity.class);
                            taskIntent.putExtra(EmployConstantUtil.KEY_TASK_ID, taskID);
                            Log.d("test", "TAGView: " + taskID);
                            startActivityForResult(taskIntent, 1000);
                        }
                    });
                }


        }
    }

    private String checkCallDuration(long callDuration) {

        return callDuration == 0 ? "--:--" : DateAndTimeUtil.longToTime(callDuration, EmployConstantUtil.TIME_FORMAT_DURATION);
    }

    private void addCallRow() {
        callDataModelList = dataBaseAdapter.getAllCall();
        employee_layout_call_data.removeAllViews();
        //     employee_layout_call_data.removeAllViews();
        DateFormat df = new SimpleDateFormat("dd-M-yyyy");
        for (CallDataModel callDataModel : callDataModelList) {
            if (df.format(callDataModel.getCreatedTime()).equals(selectedDate)) {
                    final View view = getLayoutInflater().inflate(R.layout.row_call_list, mLinearLayout, true);
                    TextView tv_call_subject = view.findViewById(R.id.tv_call_subject);
                    TextView tv_call_duration = view.findViewById(R.id.tv_call_duration);
                    TextView tv_call_time = view.findViewById(R.id.tv_call_time);

                    tv_call_subject.setText(callDataModel.getSubject());
                    tv_call_duration.setText(checkCallDuration(callDataModel.getCallDuration()));
                    tv_call_time.setText(DateAndTimeUtil.longToTime(callDataModel.getCallStartTime()));
                    employee_layout_call_data.addView(view);
                    view.setTag(callDataModel.getCallId());
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            callID = (long) view.getTag();
                            Intent callIntent = new Intent(getContext(), CallDetailActivity.class);
                            callIntent.putExtra(EmployConstantUtil.KEY_CALL_ID, callID);
                            Log.d("test", "TAGView: " + callID);
                            startActivityForResult(callIntent, 1000);
                        }
                    });
                }
            }




    }


    private void setClickListener() {
        employee_iv_add_event.setOnClickListener(this);
        employee_iv_add_task.setOnClickListener(this);
        employee_iv_add_calls.setOnClickListener(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        ButterKnife.bind(this, view);

        rCalendarFragment = new RWeekCalendar();

        rCalendarFragment.startDate(2000, 9, 1);//Start date
        rCalendarFragment.endDate(2022, 12, 31);//Ending date

        Bundle args = new Bundle();

        /*Should add this attribute if you adding  the NOW_BACKGROUND or DATE_SELECTOR_BACKGROUND Attribute*/
        args.putString(RWeekCalendar.PACKAGENAME, getContext().getPackageName());

        args.putInt(RWeekCalendar.CALENDER_TYPE, RWeekCalendar.FDF_CALENDER );

        args.putString(RWeekCalendar.DATE_SELECTOR_BACKGROUND, "bg_red");

        args.putString(RWeekCalendar.NOW_BACKGROUND, "ovel");
        args.putInt(RWeekCalendar.CURRENT_DATE_BACKGROUND, ContextCompat.getColor(getContext(), R.color.event_color_02));

        rCalendarFragment.setArguments(args);

        FragmentManager childFragMan = getChildFragmentManager();

        FragmentTransaction childFragTrans = childFragMan.beginTransaction();
        childFragTrans.replace(R.id.container, rCalendarFragment);
        childFragTrans.addToBackStack(null);
        childFragTrans.commit();


        CalenderListener listener = new CalenderListener() {

            @Override
            public void onSelectPicker() {

                /*DatePickerDialog.newInstance(MainActivity.this, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show(getFragmentManager(), "datePicker");*/
                openCalender();
            }

            @Override
            public void onSelectDate(LocalDateTime mSelectedDate) {

                //mDateSelectedTv.setText("" + mSelectedDate.getDayOfMonth() + "-" + mSelectedDate.getMonthOfYear() + "-" + mSelectedDate.getYear());
                
                selectedDate = "" + mSelectedDate.getDayOfMonth() + "-" + mSelectedDate.getMonthOfYear() + "-" + mSelectedDate.getYear();
                if(selectedDate.charAt(1)=='-') selectedDate = "0"+selectedDate;
                if(selectedDate.charAt(4)=='-') selectedDate = selectedDate.substring(0,3)+ "0"+ selectedDate.substring(3) ;
                mDateSelectedTv.setText(selectedDate);
                addEventRow();
                addTaskRow();
                addCallRow();
            }
        };


        rCalendarFragment.setCalenderListener(listener);





        setClickListener();

        addEventRow();
        addTaskRow();
        addCallRow();

        return view;
    }


    private void openCalender() {


        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        android.app.DatePickerDialog datePickerDialog = new android.app.DatePickerDialog(getContext(), new android.app.DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker dateView, int year, int monthOfYear, int dayOfMonth) {
                String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;

                c.set(year, monthOfYear, dayOfMonth);
                rCalendarFragment.setDateWeek(c);
            }
        }, mYear, mMonth, mDay);

        datePickerDialog.show();


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.employee_iv_add_event:
                Toast.makeText(getContext(), "Events was clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.employee_iv_add_task:
                Toast.makeText(getContext(), "Tasks was clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.employee_iv_add_calls:
                Toast.makeText(getContext(), "Calls was clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @Override
    public void onItemClick(int position) {

    }

}