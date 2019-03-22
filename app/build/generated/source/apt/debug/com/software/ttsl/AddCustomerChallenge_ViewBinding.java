// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddCustomerChallenge_ViewBinding implements Unbinder {
  private AddCustomerChallenge target;

  @UiThread
  public AddCustomerChallenge_ViewBinding(AddCustomerChallenge target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddCustomerChallenge_ViewBinding(AddCustomerChallenge target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.editTextCustomer = Utils.findRequiredViewAsType(source, R.id.et_customer, "field 'editTextCustomer'", EditText.class);
    target.textViewCustomerContact1 = Utils.findRequiredViewAsType(source, R.id.et_customer_contact, "field 'textViewCustomerContact1'", TextView.class);
    target.textViewCustomerLogDate = Utils.findRequiredViewAsType(source, R.id.et_customer_log_date, "field 'textViewCustomerLogDate'", TextView.class);
    target.textViewCustomerPriority = Utils.findRequiredViewAsType(source, R.id.et_customer_priority, "field 'textViewCustomerPriority'", TextView.class);
    target.textViewCustomerOrigin = Utils.findRequiredViewAsType(source, R.id.et_customer_origin, "field 'textViewCustomerOrigin'", TextView.class);
    target.editTextCustomerReason = Utils.findRequiredViewAsType(source, R.id.et_customer_reason, "field 'editTextCustomerReason'", EditText.class);
    target.textViewCustomerDueDate = Utils.findRequiredViewAsType(source, R.id.et_customer_due_date, "field 'textViewCustomerDueDate'", TextView.class);
    target.textViewCustomerClosedOn = Utils.findRequiredViewAsType(source, R.id.et_closed_on, "field 'textViewCustomerClosedOn'", TextView.class);
    target.editTextCustomerIncharge = Utils.findRequiredViewAsType(source, R.id.et_incharge, "field 'editTextCustomerIncharge'", EditText.class);
    target.editTextCCMailId = Utils.findRequiredViewAsType(source, R.id.et_cc_mail_id, "field 'editTextCCMailId'", EditText.class);
    target.textViewCustomerStatus = Utils.findRequiredViewAsType(source, R.id.et_status, "field 'textViewCustomerStatus'", TextView.class);
    target.editTextSubject = Utils.findRequiredViewAsType(source, R.id.et_subject, "field 'editTextSubject'", EditText.class);
    target.editTextNote = Utils.findRequiredViewAsType(source, R.id.et_note, "field 'editTextNote'", EditText.class);
    target.editTextDiscription = Utils.findRequiredViewAsType(source, R.id.et_discription, "field 'editTextDiscription'", EditText.class);
    target.editTextInternalNote = Utils.findRequiredViewAsType(source, R.id.et_internal_note, "field 'editTextInternalNote'", EditText.class);
    target.editTextCustomerFeedBack = Utils.findRequiredViewAsType(source, R.id.et_customer_feedback, "field 'editTextCustomerFeedBack'", EditText.class);
    target.imageViewCustomerLogDate = Utils.findRequiredViewAsType(source, R.id.iv_customer_log_date, "field 'imageViewCustomerLogDate'", ImageView.class);
    target.imageViewCustomerClosedOn = Utils.findRequiredViewAsType(source, R.id.iv_closed_on, "field 'imageViewCustomerClosedOn'", ImageView.class);
    target.imageViewCustomerDueDate = Utils.findRequiredViewAsType(source, R.id.iv_customer_due_date, "field 'imageViewCustomerDueDate'", ImageView.class);
    target.imageViewCustomerContact = Utils.findRequiredViewAsType(source, R.id.iv_customer_contact, "field 'imageViewCustomerContact'", ImageView.class);
    target.textViewCustomerContact = Utils.findRequiredViewAsType(source, R.id.tv_customer_contact, "field 'textViewCustomerContact'", TextView.class);
    target.textViewLabelCustomer = Utils.findRequiredViewAsType(source, R.id.tv_customer, "field 'textViewLabelCustomer'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AddCustomerChallenge target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.editTextCustomer = null;
    target.textViewCustomerContact1 = null;
    target.textViewCustomerLogDate = null;
    target.textViewCustomerPriority = null;
    target.textViewCustomerOrigin = null;
    target.editTextCustomerReason = null;
    target.textViewCustomerDueDate = null;
    target.textViewCustomerClosedOn = null;
    target.editTextCustomerIncharge = null;
    target.editTextCCMailId = null;
    target.textViewCustomerStatus = null;
    target.editTextSubject = null;
    target.editTextNote = null;
    target.editTextDiscription = null;
    target.editTextInternalNote = null;
    target.editTextCustomerFeedBack = null;
    target.imageViewCustomerLogDate = null;
    target.imageViewCustomerClosedOn = null;
    target.imageViewCustomerDueDate = null;
    target.imageViewCustomerContact = null;
    target.textViewCustomerContact = null;
    target.textViewLabelCustomer = null;
  }
}
