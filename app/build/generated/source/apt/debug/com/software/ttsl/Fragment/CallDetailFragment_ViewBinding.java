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

public class CallDetailFragment_ViewBinding implements Unbinder {
  private CallDetailFragment target;

  @UiThread
  public CallDetailFragment_ViewBinding(CallDetailFragment target, View source) {
    this.target = target;

    target.linearLayoutCallType = Utils.findRequiredViewAsType(source, R.id.linear_layout_call_type, "field 'linearLayoutCallType'", LinearLayout.class);
    target.linearLayoutCallContact = Utils.findRequiredViewAsType(source, R.id.linear_layout_call_contact, "field 'linearLayoutCallContact'", LinearLayout.class);
    target.linearLayoutCallPurpose = Utils.findRequiredViewAsType(source, R.id.linear_layout_call_purpose, "field 'linearLayoutCallPurpose'", LinearLayout.class);
    target.linearLayoutCallAccount = Utils.findRequiredViewAsType(source, R.id.linear_layout_call_account, "field 'linearLayoutCallAccount'", LinearLayout.class);
    target.linearLayoutCallDescription = Utils.findRequiredViewAsType(source, R.id.linear_layout_call_description, "field 'linearLayoutCallDescription'", LinearLayout.class);
    target.linearLayoutCallResult = Utils.findRequiredViewAsType(source, R.id.linear_layout_call_result, "field 'linearLayoutCallResult'", LinearLayout.class);
    target.textViewCallType = Utils.findRequiredViewAsType(source, R.id.tv_call_type, "field 'textViewCallType'", TextView.class);
    target.textViewCallContact = Utils.findRequiredViewAsType(source, R.id.tv_call_contact, "field 'textViewCallContact'", TextView.class);
    target.textViewCallSubject = Utils.findRequiredViewAsType(source, R.id.tv_call_subject, "field 'textViewCallSubject'", TextView.class);
    target.textViewCallPurpose = Utils.findRequiredViewAsType(source, R.id.tv_call_purpose, "field 'textViewCallPurpose'", TextView.class);
    target.textViewCallAccount = Utils.findRequiredViewAsType(source, R.id.tv_call_account, "field 'textViewCallAccount'", TextView.class);
    target.textViewCallType1 = Utils.findRequiredViewAsType(source, R.id.tv_call_type1, "field 'textViewCallType1'", TextView.class);
    target.textViewCallStartTime = Utils.findRequiredViewAsType(source, R.id.tv_call_start_time, "field 'textViewCallStartTime'", TextView.class);
    target.textViewCallDuration = Utils.findRequiredViewAsType(source, R.id.tv_call_duration, "field 'textViewCallDuration'", TextView.class);
    target.textViewCallDescription = Utils.findRequiredViewAsType(source, R.id.tv_call_description, "field 'textViewCallDescription'", TextView.class);
    target.textViewCallResult = Utils.findRequiredViewAsType(source, R.id.tv_call_result, "field 'textViewCallResult'", TextView.class);
    target.textViewCallCreatedBy = Utils.findRequiredViewAsType(source, R.id.tv_call_created_by, "field 'textViewCallCreatedBy'", TextView.class);
    target.textViewCallModifedBy = Utils.findRequiredViewAsType(source, R.id.tv_call_modified_by, "field 'textViewCallModifedBy'", TextView.class);
    target.textViewCallCreatedTime = Utils.findRequiredViewAsType(source, R.id.tv_call_created_time, "field 'textViewCallCreatedTime'", TextView.class);
    target.textViewCallModifedTime = Utils.findRequiredViewAsType(source, R.id.tv_call_modified_time, "field 'textViewCallModifedTime'", TextView.class);
    target.textViewSmartView = Utils.findRequiredViewAsType(source, R.id.tv_smart_view, "field 'textViewSmartView'", TextView.class);
    target.textViewHeaderCallSubject = Utils.findRequiredViewAsType(source, R.id.tv_header_call_subject, "field 'textViewHeaderCallSubject'", TextView.class);
    target.textViewHeaderCallDuration = Utils.findRequiredViewAsType(source, R.id.tv_header_call_duration, "field 'textViewHeaderCallDuration'", TextView.class);
    target.textViewHeaderCallTime = Utils.findRequiredViewAsType(source, R.id.tv_header_call_time, "field 'textViewHeaderCallTime'", TextView.class);
    target.textViewHeaderCallType1 = Utils.findRequiredViewAsType(source, R.id.tv_header_call_type1, "field 'textViewHeaderCallType1'", TextView.class);
    target.textViewContactCallLabel = Utils.findRequiredViewAsType(source, R.id.tv_call_contact_label, "field 'textViewContactCallLabel'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CallDetailFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.linearLayoutCallType = null;
    target.linearLayoutCallContact = null;
    target.linearLayoutCallPurpose = null;
    target.linearLayoutCallAccount = null;
    target.linearLayoutCallDescription = null;
    target.linearLayoutCallResult = null;
    target.textViewCallType = null;
    target.textViewCallContact = null;
    target.textViewCallSubject = null;
    target.textViewCallPurpose = null;
    target.textViewCallAccount = null;
    target.textViewCallType1 = null;
    target.textViewCallStartTime = null;
    target.textViewCallDuration = null;
    target.textViewCallDescription = null;
    target.textViewCallResult = null;
    target.textViewCallCreatedBy = null;
    target.textViewCallModifedBy = null;
    target.textViewCallCreatedTime = null;
    target.textViewCallModifedTime = null;
    target.textViewSmartView = null;
    target.textViewHeaderCallSubject = null;
    target.textViewHeaderCallDuration = null;
    target.textViewHeaderCallTime = null;
    target.textViewHeaderCallType1 = null;
    target.textViewContactCallLabel = null;
  }
}
