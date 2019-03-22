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

public class AddCallActivity_ViewBinding implements Unbinder {
  private AddCallActivity target;

  @UiThread
  public AddCallActivity_ViewBinding(AddCallActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddCallActivity_ViewBinding(AddCallActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.textViewCallContact = Utils.findRequiredViewAsType(source, R.id.et_call_contact, "field 'textViewCallContact'", TextView.class);
    target.editTextCallSubject = Utils.findRequiredViewAsType(source, R.id.et_call_subject, "field 'editTextCallSubject'", EditText.class);
    target.textViewCallPurpose = Utils.findRequiredViewAsType(source, R.id.et_call_purpose, "field 'textViewCallPurpose'", TextView.class);
    target.textViewCallAccount = Utils.findRequiredViewAsType(source, R.id.et_call_account, "field 'textViewCallAccount'", TextView.class);
    target.textViewCallType1 = Utils.findRequiredViewAsType(source, R.id.et_call_type1, "field 'textViewCallType1'", TextView.class);
    target.TextViewCallType = Utils.findRequiredViewAsType(source, R.id.et_call_type, "field 'TextViewCallType'", TextView.class);
    target.textViewCallDuration = Utils.findRequiredViewAsType(source, R.id.et_call_duration, "field 'textViewCallDuration'", TextView.class);
    target.editTextCallDescription = Utils.findRequiredViewAsType(source, R.id.et_call_description, "field 'editTextCallDescription'", EditText.class);
    target.editTextCallResult = Utils.findRequiredViewAsType(source, R.id.et_call_result, "field 'editTextCallResult'", EditText.class);
    target.textViewCallStartDate = Utils.findRequiredViewAsType(source, R.id.et_call_start_date, "field 'textViewCallStartDate'", TextView.class);
    target.textViewCallStrartTime = Utils.findRequiredViewAsType(source, R.id.et_call_start_time, "field 'textViewCallStrartTime'", TextView.class);
    target.textViewCallSubject = Utils.findRequiredViewAsType(source, R.id.tv_call_subject, "field 'textViewCallSubject'", TextView.class);
    target.textViewCallType = Utils.findRequiredViewAsType(source, R.id.tv_call_type1, "field 'textViewCallType'", TextView.class);
    target.textViewCallStartDateLabel = Utils.findRequiredViewAsType(source, R.id.tv_call_start_date, "field 'textViewCallStartDateLabel'", TextView.class);
    target.textViewCallStartTimeLabel = Utils.findRequiredViewAsType(source, R.id.tv_call_start_time, "field 'textViewCallStartTimeLabel'", TextView.class);
    target.textViewCallDurationLabel = Utils.findRequiredViewAsType(source, R.id.tv_call_duration, "field 'textViewCallDurationLabel'", TextView.class);
    target.textViewCallContactLabel = Utils.findRequiredViewAsType(source, R.id.tv_call_contact, "field 'textViewCallContactLabel'", TextView.class);
    target.textViewErrorSubject = Utils.findRequiredViewAsType(source, R.id.tv_error_subject, "field 'textViewErrorSubject'", TextView.class);
    target.textViewErrorCallDuration = Utils.findRequiredViewAsType(source, R.id.tv_error_call_duration, "field 'textViewErrorCallDuration'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AddCallActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.textViewCallContact = null;
    target.editTextCallSubject = null;
    target.textViewCallPurpose = null;
    target.textViewCallAccount = null;
    target.textViewCallType1 = null;
    target.TextViewCallType = null;
    target.textViewCallDuration = null;
    target.editTextCallDescription = null;
    target.editTextCallResult = null;
    target.textViewCallStartDate = null;
    target.textViewCallStrartTime = null;
    target.textViewCallSubject = null;
    target.textViewCallType = null;
    target.textViewCallStartDateLabel = null;
    target.textViewCallStartTimeLabel = null;
    target.textViewCallDurationLabel = null;
    target.textViewCallContactLabel = null;
    target.textViewErrorSubject = null;
    target.textViewErrorCallDuration = null;
  }
}
