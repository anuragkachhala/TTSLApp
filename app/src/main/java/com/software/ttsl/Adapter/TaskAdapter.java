package com.software.ttsl.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.software.ttsl.R;
import com.software.ttsl.Request.TaskDataModel;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    public static final String TAG = TaskAdapter.class.getName();

    private Context context;
    private List<TaskDataModel> taskDataModelsList;

    public TaskAdapter(Context context, List<TaskDataModel> taskDataModels) {
        this.context = context;
        this.taskDataModelsList = taskDataModels;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_task_list, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        TaskDataModel taskDataModel = taskDataModelsList.get(position);



    }

    @Override
    public int getItemCount() {
        return taskDataModelsList.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBoxTask;
        TextView taskSubject;
        TextView taskContact;
        TextView taskImage;
        TextView taskPriority;
        public TaskViewHolder(View itemView) {
            super(itemView);
            checkBoxTask =(CheckBox)itemView.findViewById(R.id.checkBox_task);
            taskSubject= (TextView)itemView.findViewById(R.id.tv_task_subject);
            taskContact=(TextView)itemView.findViewById(R.id.tv_task_contact);
            taskPriority=(TextView)itemView.findViewById(R.id.tv_task_priority);
            taskImage=(TextView)itemView.findViewById(R.id.tv_task_owner_image);
        }
    }
}
