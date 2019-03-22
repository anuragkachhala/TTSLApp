// Generated code from Butter Knife. Do not modify!
package com.software.ttsl.Fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.software.ttsl.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CallRelatedFragment_ViewBinding implements Unbinder {
  private CallRelatedFragment target;

  @UiThread
  public CallRelatedFragment_ViewBinding(CallRelatedFragment target, View source) {
    this.target = target;

    target.imageViewCallType = Utils.findRequiredViewAsType(source, R.id.iv_call_type, "field 'imageViewCallType'", ImageView.class);
    target.textViewCallSubject = Utils.findRequiredViewAsType(source, R.id.tv_call_subject, "field 'textViewCallSubject'", TextView.class);
    target.textViewCallType = Utils.findRequiredViewAsType(source, R.id.tv_call_type, "field 'textViewCallType'", TextView.class);
    target.textViewCallTime = Utils.findRequiredViewAsType(source, R.id.tv_call_time, "field 'textViewCallTime'", TextView.class);
    target.textViewCallDuration = Utils.findRequiredViewAsType(source, R.id.tv_call_duration, "field 'textViewCallDuration'", TextView.class);
    target.textViewContactName = Utils.findRequiredViewAsType(source, R.id.tv_contact_name, "field 'textViewContactName'", TextView.class);
    target.textViewAccountName = Utils.findRequiredViewAsType(source, R.id.tv_account_name, "field 'textViewAccountName'", TextView.class);
    target.linearLayoutContact = Utils.findRequiredViewAsType(source, R.id.layout_contact, "field 'linearLayoutContact'", LinearLayout.class);
    target.linearLayoutAccount = Utils.findRequiredViewAsType(source, R.id.layout_account, "field 'linearLayoutAccount'", LinearLayout.class);
    target.textViewLabelContact = Utils.findRequiredViewAsType(source, R.id.tv_contact, "field 'textViewLabelContact'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CallRelatedFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imageViewCallType = null;
    target.textViewCallSubject = null;
    target.textViewCallType = null;
    target.textViewCallTime = null;
    target.textViewCallDuration = null;
    target.textViewContactName = null;
    target.textViewAccountName = null;
    target.linearLayoutContact = null;
    target.linearLayoutAccount = null;
    target.textViewLabelContact = null;
  }
}
