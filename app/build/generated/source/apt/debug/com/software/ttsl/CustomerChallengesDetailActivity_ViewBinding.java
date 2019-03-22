// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CustomerChallengesDetailActivity_ViewBinding implements Unbinder {
  private CustomerChallengesDetailActivity target;

  @UiThread
  public CustomerChallengesDetailActivity_ViewBinding(CustomerChallengesDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CustomerChallengesDetailActivity_ViewBinding(CustomerChallengesDetailActivity target,
      View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.textViewAddCustomer = Utils.findRequiredViewAsType(source, R.id.tv_add_customer, "field 'textViewAddCustomer'", TextView.class);
    target.textViewCustomerContact = Utils.findRequiredViewAsType(source, R.id.tv_customer_contact, "field 'textViewCustomerContact'", TextView.class);
    target.textViewCustomerLogDate = Utils.findRequiredViewAsType(source, R.id.tv_customer_log_date, "field 'textViewCustomerLogDate'", TextView.class);
    target.textViewCustomerPriority = Utils.findRequiredViewAsType(source, R.id.tv_customer_priority, "field 'textViewCustomerPriority'", TextView.class);
    target.textViewCustomerOrigin = Utils.findRequiredViewAsType(source, R.id.tv_customer_origin, "field 'textViewCustomerOrigin'", TextView.class);
    target.textViewCustomerReason = Utils.findRequiredViewAsType(source, R.id.tv_customer_reason, "field 'textViewCustomerReason'", TextView.class);
    target.textViewCustomerDueDate = Utils.findRequiredViewAsType(source, R.id.tv_customer_due_date, "field 'textViewCustomerDueDate'", TextView.class);
    target.textViewClosedOn = Utils.findRequiredViewAsType(source, R.id.tv_closed_on, "field 'textViewClosedOn'", TextView.class);
    target.textViewCustomerIncharge = Utils.findRequiredViewAsType(source, R.id.tv_customer_incharge, "field 'textViewCustomerIncharge'", TextView.class);
    target.textViewCCMailId = Utils.findRequiredViewAsType(source, R.id.tv_cc_mail_id, "field 'textViewCCMailId'", TextView.class);
    target.textViewCustomerStatus = Utils.findRequiredViewAsType(source, R.id.tv_customer_status, "field 'textViewCustomerStatus'", TextView.class);
    target.textViewCustomerSubject = Utils.findRequiredViewAsType(source, R.id.tv_customer_subject, "field 'textViewCustomerSubject'", TextView.class);
    target.textViewCustomerNote = Utils.findRequiredViewAsType(source, R.id.tv_customer_note, "field 'textViewCustomerNote'", TextView.class);
    target.textViewCustomerDescription = Utils.findRequiredViewAsType(source, R.id.tv_customer_description, "field 'textViewCustomerDescription'", TextView.class);
    target.textViewInternalNote = Utils.findRequiredViewAsType(source, R.id.tv_internal_note, "field 'textViewInternalNote'", TextView.class);
    target.textViewCustomerFeedBack = Utils.findRequiredViewAsType(source, R.id.tv_customer_feedback, "field 'textViewCustomerFeedBack'", TextView.class);
    target.textViewCustomerCreatedBY = Utils.findRequiredViewAsType(source, R.id.tv_customer_created_by, "field 'textViewCustomerCreatedBY'", TextView.class);
    target.textViewModifyBY = Utils.findRequiredViewAsType(source, R.id.tv_customer_modify_by, "field 'textViewModifyBY'", TextView.class);
    target.textViewCreatedTime = Utils.findRequiredViewAsType(source, R.id.tv_customer_created_time, "field 'textViewCreatedTime'", TextView.class);
    target.textViewModifyTime = Utils.findRequiredViewAsType(source, R.id.tv_customer_modify_time, "field 'textViewModifyTime'", TextView.class);
    target.linearLayoutCustomerPriority = Utils.findRequiredViewAsType(source, R.id.linear_layout_customer_priority, "field 'linearLayoutCustomerPriority'", LinearLayout.class);
    target.linearLayoutCustomerOrigin = Utils.findRequiredViewAsType(source, R.id.linear_layout_customer_origin, "field 'linearLayoutCustomerOrigin'", LinearLayout.class);
    target.linearLayoutCustomerReason = Utils.findRequiredViewAsType(source, R.id.linear_layout_customer_reason, "field 'linearLayoutCustomerReason'", LinearLayout.class);
    target.linearLayoutIncharge = Utils.findRequiredViewAsType(source, R.id.linear_layout_incharge, "field 'linearLayoutIncharge'", LinearLayout.class);
    target.linearLayoutCCMailId = Utils.findRequiredViewAsType(source, R.id.linear_layout_cc_mail_id, "field 'linearLayoutCCMailId'", LinearLayout.class);
    target.linearLayoutStatus = Utils.findRequiredViewAsType(source, R.id.linear_layout_status, "field 'linearLayoutStatus'", LinearLayout.class);
    target.linearLayoutCreatedBY = Utils.findRequiredViewAsType(source, R.id.linear_layout_created_by, "field 'linearLayoutCreatedBY'", LinearLayout.class);
    target.linearLayoutCustomerSubject = Utils.findRequiredViewAsType(source, R.id.linear_layout_customer_subject, "field 'linearLayoutCustomerSubject'", LinearLayout.class);
    target.linearLayoutCustomerNote = Utils.findRequiredViewAsType(source, R.id.linear_layout_customer_note, "field 'linearLayoutCustomerNote'", LinearLayout.class);
    target.linearLayoutCustomerDescription = Utils.findRequiredViewAsType(source, R.id.linear_layout_customer_description, "field 'linearLayoutCustomerDescription'", LinearLayout.class);
    target.linearLayoutInternalNote = Utils.findRequiredViewAsType(source, R.id.linear_layout_internal_note, "field 'linearLayoutInternalNote'", LinearLayout.class);
    target.linearLayoutCustomerFeedback = Utils.findRequiredViewAsType(source, R.id.linear_layout_customer_feedback, "field 'linearLayoutCustomerFeedback'", LinearLayout.class);
    target.linearLayoutCustomerContact = Utils.findRequiredViewAsType(source, R.id.linear_layout_customer_contact, "field 'linearLayoutCustomerContact'", LinearLayout.class);
    target.textViewSmartView = Utils.findRequiredViewAsType(source, R.id.tv_smart_view, "field 'textViewSmartView'", TextView.class);
    target.textViewCustomerChallengeLabel = Utils.findRequiredViewAsType(source, R.id.tv_contact_label, "field 'textViewCustomerChallengeLabel'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CustomerChallengesDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.textViewAddCustomer = null;
    target.textViewCustomerContact = null;
    target.textViewCustomerLogDate = null;
    target.textViewCustomerPriority = null;
    target.textViewCustomerOrigin = null;
    target.textViewCustomerReason = null;
    target.textViewCustomerDueDate = null;
    target.textViewClosedOn = null;
    target.textViewCustomerIncharge = null;
    target.textViewCCMailId = null;
    target.textViewCustomerStatus = null;
    target.textViewCustomerSubject = null;
    target.textViewCustomerNote = null;
    target.textViewCustomerDescription = null;
    target.textViewInternalNote = null;
    target.textViewCustomerFeedBack = null;
    target.textViewCustomerCreatedBY = null;
    target.textViewModifyBY = null;
    target.textViewCreatedTime = null;
    target.textViewModifyTime = null;
    target.linearLayoutCustomerPriority = null;
    target.linearLayoutCustomerOrigin = null;
    target.linearLayoutCustomerReason = null;
    target.linearLayoutIncharge = null;
    target.linearLayoutCCMailId = null;
    target.linearLayoutStatus = null;
    target.linearLayoutCreatedBY = null;
    target.linearLayoutCustomerSubject = null;
    target.linearLayoutCustomerNote = null;
    target.linearLayoutCustomerDescription = null;
    target.linearLayoutInternalNote = null;
    target.linearLayoutCustomerFeedback = null;
    target.linearLayoutCustomerContact = null;
    target.textViewSmartView = null;
    target.textViewCustomerChallengeLabel = null;
  }
}
