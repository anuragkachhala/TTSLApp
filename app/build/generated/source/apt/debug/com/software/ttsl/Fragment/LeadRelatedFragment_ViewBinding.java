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

public class LeadRelatedFragment_ViewBinding implements Unbinder {
  private LeadRelatedFragment target;

  @UiThread
  public LeadRelatedFragment_ViewBinding(LeadRelatedFragment target, View source) {
    this.target = target;

    target.textViewLeadName = Utils.findRequiredViewAsType(source, R.id.tv_lead_name, "field 'textViewLeadName'", TextView.class);
    target.textViewLeadEmailId = Utils.findRequiredViewAsType(source, R.id.tv_lead_email_id, "field 'textViewLeadEmailId'", TextView.class);
    target.textViewLeadMobileNo = Utils.findRequiredViewAsType(source, R.id.tv_lead_mobile_no, "field 'textViewLeadMobileNo'", TextView.class);
    target.imageViewLeadimage = Utils.findRequiredViewAsType(source, R.id.iv_lead_image, "field 'imageViewLeadimage'", ImageView.class);
    target.textViewLeadOwner = Utils.findRequiredViewAsType(source, R.id.tv_lead_owner, "field 'textViewLeadOwner'", TextView.class);
    target.imageViewAddNotes = Utils.findRequiredViewAsType(source, R.id.iv_add_notes, "field 'imageViewAddNotes'", ImageView.class);
    target.imageViewAddAttachment = Utils.findRequiredViewAsType(source, R.id.iv_add_attachment, "field 'imageViewAddAttachment'", ImageView.class);
    target.imageViewAddTask = Utils.findRequiredViewAsType(source, R.id.employee_iv_add_task, "field 'imageViewAddTask'", ImageView.class);
    target.imageViewAddCall = Utils.findRequiredViewAsType(source, R.id.iv_add_call, "field 'imageViewAddCall'", ImageView.class);
    target.imageViewAddEvent = Utils.findRequiredViewAsType(source, R.id.employee_iv_add_event, "field 'imageViewAddEvent'", ImageView.class);
    target.linearLayoutTaskContainer = Utils.findRequiredViewAsType(source, R.id.task_container, "field 'linearLayoutTaskContainer'", LinearLayout.class);
    target.linearLayoutEventContainer = Utils.findRequiredViewAsType(source, R.id.event_container, "field 'linearLayoutEventContainer'", LinearLayout.class);
    target.linearLayoutCallContainer = Utils.findRequiredViewAsType(source, R.id.call_container, "field 'linearLayoutCallContainer'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LeadRelatedFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.textViewLeadName = null;
    target.textViewLeadEmailId = null;
    target.textViewLeadMobileNo = null;
    target.imageViewLeadimage = null;
    target.textViewLeadOwner = null;
    target.imageViewAddNotes = null;
    target.imageViewAddAttachment = null;
    target.imageViewAddTask = null;
    target.imageViewAddCall = null;
    target.imageViewAddEvent = null;
    target.linearLayoutTaskContainer = null;
    target.linearLayoutEventContainer = null;
    target.linearLayoutCallContainer = null;
  }
}
