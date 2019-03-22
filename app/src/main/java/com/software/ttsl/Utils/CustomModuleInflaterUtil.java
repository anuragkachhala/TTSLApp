package com.software.ttsl.Utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.software.ttsl.EventDetailActivity;
import com.software.ttsl.Fragment.BaseUtilFragment;
import com.software.ttsl.Fragment.LeadRelatedFragment;
import com.software.ttsl.R;
import com.software.ttsl.Request.CallDataModel;
import com.software.ttsl.Request.EventDataModel;
import com.software.ttsl.Request.TaskDataModel;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.TaskDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class CustomModuleInflaterUtil {
    private static List<TaskDataModel> taskDataModelList = new ArrayList<>();
    private static List<EventDataModel> eventDataModelList = new ArrayList<>();
    private static List<CallDataModel> callDataModelList = new ArrayList<>();
    private Context context;
    private DataBaseAdapter dataBaseAdapter;
    private LayoutInflater layoutInflater;
    private long taskId;
    private BaseUtilFragment baseUtilFragment;

    public CustomModuleInflaterUtil(Context context, BaseUtilFragment baseUtilFragment) {
        this.context = context;
        this.baseUtilFragment = baseUtilFragment;
        dataBaseAdapter = new DataBaseAdapter(context);
        layoutInflater = LayoutInflater.from(context);
    }

    public void setAllEvent(String type, long id, LinearLayout linearLayoutEventContainer) {
        linearLayoutEventContainer.removeAllViews();
        eventDataModelList.clear();
        eventDataModelList.addAll(dataBaseAdapter.getEventById(type, id));
        for (EventDataModel eventDataModel : eventDataModelList) {
            View view = layoutInflater.inflate(R.layout.row_event_list, null);
            TextView textViewFromDate = (TextView) view.findViewById(R.id.tv_from_date);
            TextView textViewToDate = (TextView) view.findViewById(R.id.tv_to_date);
            TextView textViewTo = (TextView) view.findViewById(R.id.tv_to);
            TextView textViewTitle = (TextView) view.findViewById(R.id.tv_title);
            ImageView imageViewCalender = (ImageView) view.findViewById(R.id.iv_calender);
            view.setTag(eventDataModel.getEventId());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    long eventId = (long) view.getTag();
                    Log.d("test", "ID: " + view.getTag());
                    Intent eventIntent = new Intent(context, EventDetailActivity.class);
                    eventIntent.putExtra(EmployConstantUtil.KEY_EVENT_ID, eventId);
                    baseUtilFragment.startActivityForResult1(eventIntent, 1000);

                }
            });

            if (eventDataModel.getAllDay()) {
                if (!DateAndTimeUtil.longToDate(eventDataModel.getFromDate()).equals(DateAndTimeUtil.longToDate(eventDataModel.getToDate()))) {

                    textViewFromDate.setText(DateAndTimeUtil.longToDate(eventDataModel.getFromDate()));
                    textViewToDate.setText(DateAndTimeUtil.longToDate(eventDataModel.getToDate()));
                    //holder.imageViewCalender.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_multidate_cal));
                    imageViewCalender.setImageDrawable(setVectorForPreLollipop(R.drawable.ic_multidate_cal, context));


                } else {

                    textViewFromDate.setText(DateAndTimeUtil.longToDate(eventDataModel.getFromDate()));
                    textViewTo.setVisibility(View.GONE);
                    textViewToDate.setText("All Day");
                    imageViewCalender.setImageDrawable(setVectorForPreLollipop(R.drawable.ic_event, context));

                }

            } else {
                textViewFromDate.setText(DateAndTimeUtil.longToDate(eventDataModel.getFromDate()));
                textViewTo.setVisibility(View.GONE);
                textViewToDate.setText(DateAndTimeUtil.longToTime(eventDataModel.getFromTime()));
                //holder.imageViewCalender.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_event_time));
                imageViewCalender.setImageDrawable(setVectorForPreLollipop(R.drawable.ic_event_time, context));
            }

            textViewTitle.setText(eventDataModel.getTitle());

            linearLayoutEventContainer.addView(view);


        }


    }

    public void     setAllTask(String type, long id, LinearLayout linearLayoutTaskContainer) {

        linearLayoutTaskContainer.removeAllViews();
        taskDataModelList.clear();
        taskDataModelList.addAll(dataBaseAdapter.getTaskById(type, id));
        for (TaskDataModel taskDataModel : taskDataModelList) {
            View view = layoutInflater.inflate(R.layout.row_task_list, null);
            CheckBox checkBoxTask = (CheckBox) view.findViewById(R.id.checkBox_task);
            TextView taskSubject = (TextView) view.findViewById(R.id.tv_task_subject);
            TextView taskContact = (TextView) view.findViewById(R.id.tv_task_contact);
            TextView taskImage = (TextView) view.findViewById(R.id.tv_task_owner_image);
            TextView taskPriority = (TextView) view.findViewById(R.id.tv_task_priority);
            view.setTag(taskDataModel.getTaskId());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    taskId = (long) view.getTag();
                    Log.d("CustomMo", "ID: " + view.getTag());
                    Intent taskIntent = new Intent(context, TaskDetailActivity.class);
                    taskIntent.putExtra(EmployConstantUtil.KEY_TASK_ID, taskId);
                    taskIntent.putExtra("A", 1);
                    baseUtilFragment.startActivityForResult1(taskIntent, 1000);

                }
            });

            if (taskDataModel.getTaskStatus().equals("Completed")) {
                checkBoxTask.setChecked(true);

            }
            taskPriority.setText(taskDataModel.getTaskPriority());
            taskContact.setText(taskDataModel.getTaskContact());
            taskImage.setText(taskDataModel.getTaskOwner().substring(0, 2).toUpperCase());
            taskSubject.setText(taskDataModel.getTaskSubject());
            linearLayoutTaskContainer.addView(view);


        }
    }

    public Drawable setVectorForPreLollipop(int resourceId, Context activity) {
        Drawable icon;
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            icon = VectorDrawableCompat.create(activity.getResources(), resourceId, activity.getTheme());
        } else {
            icon = activity.getResources().getDrawable(resourceId, activity.getTheme());
        }

        return icon;
    }


    public void setAllCall(String type, long id, LinearLayout linearLayoutCallContainer) {
        linearLayoutCallContainer.removeAllViews();
        callDataModelList.clear();
        callDataModelList.addAll(dataBaseAdapter.getCallById(type, id));
        for (CallDataModel callDataModel : callDataModelList) {
            View view = layoutInflater.inflate(R.layout.row_call_list, null);
            ImageView imageViewCallType = (ImageView) view.findViewById(R.id.iv_call_type);
            TextView textViewCallSubject = (TextView) view.findViewById(R.id.tv_call_subject);
            TextView textViewCallContact = (TextView) view.findViewById(R.id.tv_call_contact);
            TextView textViewCallTime = (TextView) view.findViewById(R.id.tv_call_time);
            TextView textViewCallDuration = (TextView) view.findViewById(R.id.tv_call_duration);
            ImageView imageViewCallDot = (ImageView) view.findViewById(R.id.iv_call_dot);
            textViewCallSubject.setText(callDataModel.getSubject());
            textViewCallTime.setText(DateAndTimeUtil.longToTime(callDataModel.getCallStartTime()));
            textViewCallDuration.setText(checkCallDuration(callDataModel.getCallDuration()));
            if (!callDataModel.getContact().isEmpty()) {
                textViewCallContact.setText(callDataModel.getContact());
                imageViewCallDot.setVisibility(View.VISIBLE);
            }
            if (callDataModel.getCallType().equals("Inbound")) {
                imageViewCallType.setImageResource(R.drawable.cmd_phone_incoming);
            } else {
                imageViewCallType.setImageResource(R.drawable.cmd_phone_outgoing);
            }


            linearLayoutCallContainer.addView(view);
        }
    }


    private String checkCallDuration(long callDuration) {

        return callDuration == 0 ? "--:--" : DateAndTimeUtil.longToTime(callDuration, EmployConstantUtil.TIME_FORMAT_DURATION);
    }


}
