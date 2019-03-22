// Generated code from Butter Knife. Do not modify!
package com.software.ttsl.Fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.software.ttsl.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TaskDetailFragment_ViewBinding implements Unbinder {
  private TaskDetailFragment target;

  @UiThread
  public TaskDetailFragment_ViewBinding(TaskDetailFragment target, View source) {
    this.target = target;

    target.textViewTaskOwner = Utils.findRequiredViewAsType(source, R.id.tv_task_owner, "field 'textViewTaskOwner'", TextView.class);
    target.textViewTaskSubject = Utils.findRequiredViewAsType(source, R.id.tv_task_subject, "field 'textViewTaskSubject'", TextView.class);
    target.textViewDueDate = Utils.findRequiredViewAsType(source, R.id.tv_task_due_date, "field 'textViewDueDate'", TextView.class);
    target.textViewContact = Utils.findRequiredViewAsType(source, R.id.tv_task_contact, "field 'textViewContact'", TextView.class);
    target.textViewTaskAccount = Utils.findRequiredViewAsType(source, R.id.tv_task_account, "field 'textViewTaskAccount'", TextView.class);
    target.textViewTaskStatus = Utils.findRequiredViewAsType(source, R.id.tv_task_status, "field 'textViewTaskStatus'", TextView.class);
    target.textViewPriority = Utils.findRequiredViewAsType(source, R.id.tv_task_priority, "field 'textViewPriority'", TextView.class);
    target.textViewCreatedBy = Utils.findRequiredViewAsType(source, R.id.tv_task_created_by, "field 'textViewCreatedBy'", TextView.class);
    target.textViewModifiedBy = Utils.findRequiredViewAsType(source, R.id.tv_task_modified_by, "field 'textViewModifiedBy'", TextView.class);
    target.textViewCreatedTime = Utils.findRequiredViewAsType(source, R.id.tv_task_created_time, "field 'textViewCreatedTime'", TextView.class);
    target.textViewModifiedTime = Utils.findRequiredViewAsType(source, R.id.tv_task_modified_time, "field 'textViewModifiedTime'", TextView.class);
    target.textViewDescription = Utils.findRequiredViewAsType(source, R.id.tv_task_description, "field 'textViewDescription'", TextView.class);
    target.linearLayoutDueDate = Utils.findRequiredViewAsType(source, R.id.linear_layout_task_due_date, "field 'linearLayoutDueDate'", LinearLayout.class);
    target.linearLayoutTaskContact = Utils.findRequiredViewAsType(source, R.id.linear_layout_task_contact, "field 'linearLayoutTaskContact'", LinearLayout.class);
    target.linearLayoutTaskAccount = Utils.findRequiredViewAsType(source, R.id.linear_layout_task_account, "field 'linearLayoutTaskAccount'", LinearLayout.class);
    target.linearLayoutTaskDescription = Utils.findRequiredViewAsType(source, R.id.layout_task_description, "field 'linearLayoutTaskDescription'", LinearLayout.class);
    target.textViewSmartView = Utils.findRequiredViewAsType(source, R.id.tv_smart_view, "field 'textViewSmartView'", TextView.class);
    target.textViewTaskContactLabel = Utils.findRequiredViewAsType(source, R.id.tv_task_contact_label, "field 'textViewTaskContactLabel'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TaskDetailFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.textViewTaskOwner = null;
    target.textViewTaskSubject = null;
    target.textViewDueDate = null;
    target.textViewContact = null;
    target.textViewTaskAccount = null;
    target.textViewTaskStatus = null;
    target.textViewPriority = null;
    target.textViewCreatedBy = null;
    target.textViewModifiedBy = null;
    target.textViewCreatedTime = null;
    target.textViewModifiedTime = null;
    target.textViewDescription = null;
    target.linearLayoutDueDate = null;
    target.linearLayoutTaskContact = null;
    target.linearLayoutTaskAccount = null;
    target.linearLayoutTaskDescription = null;
    target.textViewSmartView = null;
    target.textViewTaskContactLabel = null;
  }
}
