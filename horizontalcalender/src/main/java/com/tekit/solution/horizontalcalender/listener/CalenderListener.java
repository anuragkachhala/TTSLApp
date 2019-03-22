package com.tekit.solution.horizontalcalender.listener;


import org.joda.time.LocalDateTime;



public abstract class CalenderListener {


    /**
     * listener notify if select date picker
     */
    public abstract void onSelectPicker();

    /**
     * Notify when date selected
     *
     * @param mSelectedDate
     */
    public abstract void onSelectDate(LocalDateTime mSelectedDate);




}
