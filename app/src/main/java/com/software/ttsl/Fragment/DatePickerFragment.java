package com.software.ttsl.Fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar =Calendar.getInstance();
        int year =calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day =calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(),(DatePickerDialog.OnDateSetListener)getActivity(),year,month+1,day);
    }



    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        int mYear = year;
        int mMonth = monthOfYear + 1;
        int mDay = dayOfMonth;

        String selectedDate = dayOfMonth + " - " + mMonth + " - " + year;

        //Toast.makeText(getActivity(), "Selected " + selectedDate, Toast.LENGTH_LONG).show();
       /* Intent i = new Intent();
        i.putExtra("RETURNED_DATE",selectedDate);
        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);*/

    }




}
