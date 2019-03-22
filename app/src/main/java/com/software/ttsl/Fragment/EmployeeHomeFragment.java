package com.software.ttsl.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.software.ttsl.R;
import com.tekit.solution.horizontalcalender.RWeekCalendar;
import com.tekit.solution.horizontalcalender.listener.CalenderListener;

import org.joda.time.LocalDateTime;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmployeeHomeFragment extends Fragment {

    public static final String TAG = EmployeeHomeFragment.class.getName();
    public int mYear, mMonth, mDay;
    RWeekCalendar rCalendarFragment;
   // TextView mDateSelectedTv;

    public EmployeeHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_employee_home, container, false);


      //  mDateSelectedTv = (TextView) view.findViewById(R.id.txt_date);


        rCalendarFragment = new RWeekCalendar();

        /*Define you startDate and end Date*/
        rCalendarFragment.startDate(2000, 9, 1);//Start date
        rCalendarFragment.endDate(2022, 12, 31);//Ending date

        Bundle args = new Bundle();

        /*Should add this attribute if you adding  the NOW_BACKGROUND or DATE_SELECTOR_BACKGROUND Attribute*/
        args.putString(RWeekCalendar.PACKAGENAME, getContext().getPackageName());

        args.putInt(RWeekCalendar.CALENDER_TYPE, RWeekCalendar.NORMAL_CALENDER);

        args.putString(RWeekCalendar.DATE_SELECTOR_BACKGROUND, "bg_red");

        //args.putString(RWeekCalendar.NOW_BACKGROUND,"ovel");

        args.putInt(RWeekCalendar.CALENDER_BACKGROUND, ContextCompat.getColor(getContext(), R.color.colorAccent));
        args.putInt(RWeekCalendar.CURRENT_DATE_BACKGROUND, ContextCompat.getColor(getContext(), R.color.colorAccent));//set color to the currentdate

        args.putInt(RWeekCalendar.PRIMARY_BACKGROUND, ContextCompat.getColor(getContext(), R.color.colorWhite));//Set color to the primary views (Month name and dates)

        args.putInt(RWeekCalendar.SECONDARY_BACKGROUND, ContextCompat.getColor(getContext(), R.color.colorWhite));//Set color to the secondary views (now view and week names)

        args.putInt(RWeekCalendar.CURRENT_DATE_BACKGROUND, ContextCompat.getColor(getContext(), R.color.colorBlack));

        rCalendarFragment.setArguments(args);

        // Attach to the activity
        FragmentTransaction t = getFragmentManager().beginTransaction();
        t.replace(R.id.container, rCalendarFragment);
        t.commit();

        CalenderListener listener = new CalenderListener() {

            @Override
            public void onSelectPicker() {

                /*DatePickerDialog.newInstance(MainActivity.this, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show(getFragmentManager(), "datePicker");*/
                openCalender();
            }

            @Override
            public void onSelectDate(LocalDateTime mSelectedDate) {
               // mDateSelectedTv.setText("" + mSelectedDate.getDayOfMonth() + "-" + mSelectedDate.getMonthOfYear() + "-" + mSelectedDate.getYear());

            }
        };


        rCalendarFragment.setCalenderListener(listener);

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




}
