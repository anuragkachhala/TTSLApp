package com.software.ttsl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.software.ttsl.Adapter.AccountListFragmentAdapter;
import com.software.ttsl.Interface.ItemClickListener;
import com.software.ttsl.Request.TaskDataModel;
import com.software.ttsl.Utils.DateAndTimeUtil;

import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskListViewHolder> {
    private  static final String TAG= TaskListAdapter.class.getName();

    private Context context;
    private List<TaskDataModel> taskList;
    private ItemClickListener listener;
    private  OnItemCheckListener itemCheckListener;

    public  interface OnItemCheckListener {
        void onItemCheck(int position);
        void onItemUncheck(int position );
    }



    public TaskListAdapter(Context context, List<TaskDataModel> taskList,ItemClickListener listener, OnItemCheckListener onItemCheckListener) {
        this.context = context;
        this.taskList = taskList;
        this.listener= listener;
        this.itemCheckListener= onItemCheckListener;
    }

    @Override
    public TaskListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_task_list, parent, false);
        return new TaskListViewHolder(view);

    }

    @Override
    public void onBindViewHolder(TaskListViewHolder holder, int position) {
        TaskDataModel taskDataModel = taskList.get(position);
        if(taskDataModel.getTaskStatus().equals("Completed")){
            holder.checkBoxTask.setChecked(true);

        }holder.taskPriority.setText(taskDataModel.getTaskPriority());
        holder.taskContact.setText(taskDataModel.getTaskContact());
        holder.taskImage.setText(taskDataModel.getTaskOwner().substring(0,2).toUpperCase());
        holder.taskSubject.setText(taskDataModel.getTaskSubject());
        if (taskDataModel.isHeaderShow()) {

            Log.e(TAG, position + " " + taskDataModel.isHeaderShow());
            holder.taskHeader.setVisibility(View.VISIBLE);
            holder.taskViewHeader.setVisibility(View.VISIBLE);
            holder.taskHeader.setText(DateAndTimeUtil.longToDate(taskDataModel.getTaskCreatedTime()));
        } else {
            Log.e(TAG, position + " " + taskDataModel.isHeaderShow());

            //holder.taskViewHeader.setVisibility(View.VISIBLE);


        }
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }


    public class TaskListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CheckBox checkBoxTask;
        TextView taskSubject;
        TextView taskHeader;
        TextView taskContact;
        View taskViewHeader;
        TextView taskImage;
        TextView taskPriority;

        public TaskListViewHolder(View itemView) {
            super(itemView);
            checkBoxTask =(CheckBox)itemView.findViewById(R.id.checkBox_task);
            taskHeader = (TextView)itemView.findViewById(R.id.tv_task_header);
            taskViewHeader=(View)itemView.findViewById(R.id.view_header);
            taskSubject= (TextView)itemView.findViewById(R.id.tv_task_subject);
            taskContact=(TextView)itemView.findViewById(R.id.tv_task_contact);
            taskPriority=(TextView)itemView.findViewById(R.id.tv_task_priority);
            taskImage=(TextView)itemView.findViewById(R.id.tv_task_owner_image);
            itemView.setOnClickListener(this);
            checkBoxTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checkBoxTask.isChecked()) {

                        itemCheckListener.onItemCheck(getAdapterPosition());
                    }
                    else {
                        itemCheckListener.onItemUncheck(getAdapterPosition());
                    }


                }
            });

        }

        @Override
        public void onClick(View view) {

            listener.onItemClick(getAdapterPosition());
        }
    }
}
