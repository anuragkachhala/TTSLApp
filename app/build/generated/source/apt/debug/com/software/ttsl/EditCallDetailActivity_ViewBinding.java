// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EditCallDetailActivity_ViewBinding implements Unbinder {
  private EditCallDetailActivity target;

  @UiThread
  public EditCallDetailActivity_ViewBinding(EditCallDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EditCallDetailActivity_ViewBinding(EditCallDetailActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.editTextCallContact = Utils.findRequiredViewAsType(source, R.id.et_call_contact, "field 'editTextCallContact'", EditText.class);
    target.editTextCallSubject = Utils.findRequiredViewAsType(source, R.id.et_call_subject, "field 'editTextCallSubject'", EditText.class);
    target.editTextCallPurpose = Utils.findRequiredViewAsType(source, R.id.et_call_purpose, "field 'editTextCallPurpose'", EditText.class);
    target.editTextCallAccount = Utils.findRequiredViewAsType(source, R.id.et_call_account, "field 'editTextCallAccount'", EditText.class);
    target.editTextCallType1 = Utils.findRequiredViewAsType(source, R.id.et_call_type1, "field 'editTextCallType1'", EditText.class);
    target.editTextCallType = Utils.findRequiredViewAsType(source, R.id.et_call_type, "field 'editTextCallType'", EditText.class);
    target.editTextCallDuration = Utils.findRequiredViewAsType(source, R.id.et_call_duration, "field 'editTextCallDuration'", EditText.class);
    target.editTextCallDescription = Utils.findRequiredViewAsType(source, R.id.et_call_description, "field 'editTextCallDescription'", EditText.class);
    target.editTextCallResult = Utils.findRequiredViewAsType(source, R.id.et_call_result, "field 'editTextCallResult'", EditText.class);
    target.editTextCallStartDate = Utils.findRequiredViewAsType(source, R.id.et_call_start_date, "field 'editTextCallStartDate'", EditText.class);
    target.editTextCallStrartTime = Utils.findRequiredViewAsType(source, R.id.et_call_start_time, "field 'editTextCallStrartTime'", EditText.class);
    target.textViewCallSubject = Utils.findRequiredViewAsType(source, R.id.tv_call_subject, "field 'textViewCallSubject'", TextView.class);
    target.textViewCallType = Utils.findRequiredViewAsType(source, R.id.tv_call_type1, "field 'textViewCallType'", TextView.class);
    target.textViewCallStartDate = Utils.findRequiredViewAsType(source, R.id.tv_call_start_date, "field 'textViewCallStartDate'", TextView.class);
    target.textViewCallStartTime = Utils.findRequiredViewAsType(source, R.id.tv_call_start_time, "field 'textViewCallStartTime'", TextView.class);
    target.textViewCallDuration = Utils.findRequiredViewAsType(source, R.id.tv_call_duration, "field 'textViewCallDuration'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EditCallDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.editTextCallContact = null;
    target.editTextCallSubject = null;
    target.editTextCallPurpose = null;
    target.editTextCallAccount = null;
    target.editTextCallType1 = null;
    target.editTextCallType = null;
    target.editTextCallDuration = null;
    target.editTextCallDescription = null;
    target.editTextCallResult = null;
    target.editTextCallStartDate = null;
    target.editTextCallStrartTime = null;
    target.textViewCallSubject = null;
    target.textViewCallType = null;
    target.textViewCallStartDate = null;
    target.textViewCallStartTime = null;
    target.textViewCallDuration = null;
  }
}
