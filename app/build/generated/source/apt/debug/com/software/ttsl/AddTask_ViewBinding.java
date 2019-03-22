// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddTask_ViewBinding implements Unbinder {
  private AddTask target;

  @UiThread
  public AddTask_ViewBinding(AddTask target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddTask_ViewBinding(AddTask target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.mStartDateTextInputLayout = Utils.findRequiredViewAsType(source, R.id.widget_start_date, "field 'mStartDateTextInputLayout'", TextInputLayout.class);
    target.mStartDateEditText = Utils.findRequiredViewAsType(source, R.id.et_start_date, "field 'mStartDateEditText'", EditText.class);
    target.mDueDateTextInputLayout = Utils.findRequiredViewAsType(source, R.id.widget_due_date, "field 'mDueDateTextInputLayout'", TextInputLayout.class);
    target.mDueDateEditText = Utils.findRequiredViewAsType(source, R.id.et_due_date, "field 'mDueDateEditText'", TextInputEditText.class);
    target.mCompletionDateInputTextLayout = Utils.findRequiredViewAsType(source, R.id.widget_completion_date, "field 'mCompletionDateInputTextLayout'", TextInputLayout.class);
    target.mCompletionDateEditText = Utils.findRequiredViewAsType(source, R.id.et_completion_date, "field 'mCompletionDateEditText'", TextInputEditText.class);
    target.mContactModeInputTextLayout = Utils.findRequiredViewAsType(source, R.id.widget_contact_mode, "field 'mContactModeInputTextLayout'", TextInputLayout.class);
    target.mContactModeEditText = Utils.findRequiredViewAsType(source, R.id.et_contact_mode, "field 'mContactModeEditText'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AddTask target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.mStartDateTextInputLayout = null;
    target.mStartDateEditText = null;
    target.mDueDateTextInputLayout = null;
    target.mDueDateEditText = null;
    target.mCompletionDateInputTextLayout = null;
    target.mCompletionDateEditText = null;
    target.mContactModeInputTextLayout = null;
    target.mContactModeEditText = null;
  }
}
