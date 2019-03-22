// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EditTaskActivity_ViewBinding implements Unbinder {
  private EditTaskActivity target;

  @UiThread
  public EditTaskActivity_ViewBinding(EditTaskActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EditTaskActivity_ViewBinding(EditTaskActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.linearLayoutTaskOwner = Utils.findRequiredViewAsType(source, R.id.linear_layout_task_owner, "field 'linearLayoutTaskOwner'", LinearLayout.class);
    target.linearLayoutTaskSubject = Utils.findRequiredViewAsType(source, R.id.linear_layout_task_subject, "field 'linearLayoutTaskSubject'", LinearLayout.class);
    target.linearLayoutTaskDueDate = Utils.findRequiredViewAsType(source, R.id.linear_layout_task_due_date, "field 'linearLayoutTaskDueDate'", LinearLayout.class);
    target.linearLayoutTaskAccount = Utils.findRequiredViewAsType(source, R.id.linear_layout_task_account, "field 'linearLayoutTaskAccount'", LinearLayout.class);
    target.linearLayoutTaskContact = Utils.findRequiredViewAsType(source, R.id.linear_layout_task_contact, "field 'linearLayoutTaskContact'", LinearLayout.class);
    target.linearLayoutTaskStatus = Utils.findRequiredViewAsType(source, R.id.linear_layout_task_status, "field 'linearLayoutTaskStatus'", LinearLayout.class);
    target.linearLayoutTaskPriority = Utils.findRequiredViewAsType(source, R.id.linear_layout_task_priority, "field 'linearLayoutTaskPriority'", LinearLayout.class);
    target.linearLayoutTaskNotificationMail = Utils.findRequiredViewAsType(source, R.id.linear_layout_task_notification_mail, "field 'linearLayoutTaskNotificationMail'", LinearLayout.class);
    target.linearLayoutTaskReminder = Utils.findRequiredViewAsType(source, R.id.linear_layout_task_reminder, "field 'linearLayoutTaskReminder'", LinearLayout.class);
    target.linearLayoutTaskRepeat = Utils.findRequiredViewAsType(source, R.id.linear_layout_task_repeat, "field 'linearLayoutTaskRepeat'", LinearLayout.class);
    target.linearLayoutTaskDiscription = Utils.findRequiredViewAsType(source, R.id.linear_layout_task_description, "field 'linearLayoutTaskDiscription'", LinearLayout.class);
    target.textViewHeaderDescription = Utils.findRequiredViewAsType(source, R.id.tv_header_discription, "field 'textViewHeaderDescription'", TextView.class);
    target.textViewTaskOwner = Utils.findRequiredViewAsType(source, R.id.et_task_owner, "field 'textViewTaskOwner'", TextView.class);
    target.textViewTaskSubject = Utils.findRequiredViewAsType(source, R.id.et_task_subject, "field 'textViewTaskSubject'", TextView.class);
    target.textViewTaskDueDate = Utils.findRequiredViewAsType(source, R.id.et_task_due_date, "field 'textViewTaskDueDate'", TextView.class);
    target.textViewTaskContact = Utils.findRequiredViewAsType(source, R.id.et_task_contact, "field 'textViewTaskContact'", TextView.class);
    target.textViewTaskAccount = Utils.findRequiredViewAsType(source, R.id.et_task_account, "field 'textViewTaskAccount'", TextView.class);
    target.textViewTaskStatus = Utils.findRequiredViewAsType(source, R.id.et_task_status, "field 'textViewTaskStatus'", TextView.class);
    target.textViewTaskPriority = Utils.findRequiredViewAsType(source, R.id.et_task_priority, "field 'textViewTaskPriority'", TextView.class);
    target.textViewTaskNotificationMail = Utils.findRequiredViewAsType(source, R.id.et_task_notification_mail, "field 'textViewTaskNotificationMail'", TextView.class);
    target.aSwitchTaskNotificationMail = Utils.findRequiredViewAsType(source, R.id.switch_task_notification_mail, "field 'aSwitchTaskNotificationMail'", Switch.class);
    target.textViewTaskReminder = Utils.findRequiredViewAsType(source, R.id.et_task_reminder, "field 'textViewTaskReminder'", TextView.class);
    target.textViewTaskRepeat = Utils.findRequiredViewAsType(source, R.id.et_task_repeat, "field 'textViewTaskRepeat'", TextView.class);
    target.editTextTaskDescription = Utils.findRequiredViewAsType(source, R.id.et_task_description, "field 'editTextTaskDescription'", EditText.class);
    target.textViewSmartView = Utils.findRequiredViewAsType(source, R.id.tv_smart_view, "field 'textViewSmartView'", TextView.class);
    target.textViewLabelContact = Utils.findRequiredViewAsType(source, R.id.tv_task_contact, "field 'textViewLabelContact'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EditTaskActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.linearLayoutTaskOwner = null;
    target.linearLayoutTaskSubject = null;
    target.linearLayoutTaskDueDate = null;
    target.linearLayoutTaskAccount = null;
    target.linearLayoutTaskContact = null;
    target.linearLayoutTaskStatus = null;
    target.linearLayoutTaskPriority = null;
    target.linearLayoutTaskNotificationMail = null;
    target.linearLayoutTaskReminder = null;
    target.linearLayoutTaskRepeat = null;
    target.linearLayoutTaskDiscription = null;
    target.textViewHeaderDescription = null;
    target.textViewTaskOwner = null;
    target.textViewTaskSubject = null;
    target.textViewTaskDueDate = null;
    target.textViewTaskContact = null;
    target.textViewTaskAccount = null;
    target.textViewTaskStatus = null;
    target.textViewTaskPriority = null;
    target.textViewTaskNotificationMail = null;
    target.aSwitchTaskNotificationMail = null;
    target.textViewTaskReminder = null;
    target.textViewTaskRepeat = null;
    target.editTextTaskDescription = null;
    target.textViewSmartView = null;
    target.textViewLabelContact = null;
  }
}
