// Generated code from Butter Knife. Do not modify!
package com.software.ttsl.Fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.software.ttsl.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TaskRelatedFragment_ViewBinding implements Unbinder {
  private TaskRelatedFragment target;

  @UiThread
  public TaskRelatedFragment_ViewBinding(TaskRelatedFragment target, View source) {
    this.target = target;

    target.linearLayoutContact = Utils.findRequiredViewAsType(source, R.id.layout_contact, "field 'linearLayoutContact'", LinearLayout.class);
    target.textViewContactName = Utils.findRequiredViewAsType(source, R.id.tv_contact_name, "field 'textViewContactName'", TextView.class);
    target.linearLayoutAccount = Utils.findRequiredViewAsType(source, R.id.layout_account, "field 'linearLayoutAccount'", LinearLayout.class);
    target.textViewAccountName = Utils.findRequiredViewAsType(source, R.id.tv_account_name, "field 'textViewAccountName'", TextView.class);
    target.checkBoxTask = Utils.findRequiredViewAsType(source, R.id.checkBox_task, "field 'checkBoxTask'", CheckBox.class);
    target.textViewTaskSubject = Utils.findRequiredViewAsType(source, R.id.tv_task_subject, "field 'textViewTaskSubject'", TextView.class);
    target.textViewTaskPriority = Utils.findRequiredViewAsType(source, R.id.tv_task_priority, "field 'textViewTaskPriority'", TextView.class);
    target.textViewTaskStatus = Utils.findRequiredViewAsType(source, R.id.tv_task_status, "field 'textViewTaskStatus'", TextView.class);
    target.textViewTaskDueDate = Utils.findRequiredViewAsType(source, R.id.tv_task_due_date, "field 'textViewTaskDueDate'", TextView.class);
    target.textViewTaskOwnerImage = Utils.findRequiredViewAsType(source, R.id.tv_task_owner_image, "field 'textViewTaskOwnerImage'", TextView.class);
    target.textViewLabelContact = Utils.findRequiredViewAsType(source, R.id.tv_contact, "field 'textViewLabelContact'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TaskRelatedFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.linearLayoutContact = null;
    target.textViewContactName = null;
    target.linearLayoutAccount = null;
    target.textViewAccountName = null;
    target.checkBoxTask = null;
    target.textViewTaskSubject = null;
    target.textViewTaskPriority = null;
    target.textViewTaskStatus = null;
    target.textViewTaskDueDate = null;
    target.textViewTaskOwnerImage = null;
    target.textViewLabelContact = null;
  }
}
