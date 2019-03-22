// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddCallActivity1_ViewBinding implements Unbinder {
  private AddCallActivity1 target;

  @UiThread
  public AddCallActivity1_ViewBinding(AddCallActivity1 target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddCallActivity1_ViewBinding(AddCallActivity1 target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.relativeLayoutCallType = Utils.findRequiredViewAsType(source, R.id.layout_call_type, "field 'relativeLayoutCallType'", RelativeLayout.class);
    target.relativeLayoutCallDate = Utils.findRequiredViewAsType(source, R.id.layout_call_date, "field 'relativeLayoutCallDate'", RelativeLayout.class);
    target.relativeLayoutPlannedDate = Utils.findRequiredViewAsType(source, R.id.layout_planned_date, "field 'relativeLayoutPlannedDate'", RelativeLayout.class);
    target.relativeLayoutCallPurpose = Utils.findRequiredViewAsType(source, R.id.layout_call_purpose, "field 'relativeLayoutCallPurpose'", RelativeLayout.class);
    target.relativeLayoutMode = Utils.findRequiredViewAsType(source, R.id.layout_mode, "field 'relativeLayoutMode'", RelativeLayout.class);
    target.relativeLayoutFollowupAction = Utils.findRequiredViewAsType(source, R.id.layout_followup_action, "field 'relativeLayoutFollowupAction'", RelativeLayout.class);
    target.relativeLayoutFollowupDate = Utils.findRequiredViewAsType(source, R.id.layout_followup_date, "field 'relativeLayoutFollowupDate'", RelativeLayout.class);
    target.relativeLayoutFollowupStatus = Utils.findRequiredViewAsType(source, R.id.layout_status, "field 'relativeLayoutFollowupStatus'", RelativeLayout.class);
    target.relativeLayoutFollowupBy = Utils.findRequiredViewAsType(source, R.id.layout_followup_by, "field 'relativeLayoutFollowupBy'", RelativeLayout.class);
    target.relativeLayoutDesignation = Utils.findRequiredViewAsType(source, R.id.layout_designation, "field 'relativeLayoutDesignation'", RelativeLayout.class);
    target.editTextCallDate = Utils.findRequiredViewAsType(source, R.id.et_call_date, "field 'editTextCallDate'", EditText.class);
    target.editTextCustomer = Utils.findRequiredViewAsType(source, R.id.et_customer, "field 'editTextCustomer'", EditText.class);
    target.editTextSalesman = Utils.findRequiredViewAsType(source, R.id.et_salesman, "field 'editTextSalesman'", EditText.class);
    target.editTextSalesTerritory = Utils.findRequiredViewAsType(source, R.id.et_sales_territory, "field 'editTextSalesTerritory'", EditText.class);
    target.editTextPlannedDate = Utils.findRequiredViewAsType(source, R.id.et_planned_date, "field 'editTextPlannedDate'", EditText.class);
    target.editTextCSPerson = Utils.findRequiredViewAsType(source, R.id.et_cs_person, "field 'editTextCSPerson'", EditText.class);
    target.editTextCallType = Utils.findRequiredViewAsType(source, R.id.et_call_type, "field 'editTextCallType'", EditText.class);
    target.editTextCallPurpose = Utils.findRequiredViewAsType(source, R.id.et_call_purpose, "field 'editTextCallPurpose'", EditText.class);
    target.editTextMode = Utils.findRequiredViewAsType(source, R.id.et_mode, "field 'editTextMode'", EditText.class);
    target.editTextDiscription = Utils.findRequiredViewAsType(source, R.id.et_discription, "field 'editTextDiscription'", EditText.class);
    target.editTextFollowupAction = Utils.findRequiredViewAsType(source, R.id.et_followup_action, "field 'editTextFollowupAction'", EditText.class);
    target.editTextFollowupDate = Utils.findRequiredViewAsType(source, R.id.et_followup_date, "field 'editTextFollowupDate'", EditText.class);
    target.editTextFollowupStatus = Utils.findRequiredViewAsType(source, R.id.et_followup_status, "field 'editTextFollowupStatus'", EditText.class);
    target.editTextFollowupBy = Utils.findRequiredViewAsType(source, R.id.et_followup_by, "field 'editTextFollowupBy'", EditText.class);
    target.editTextFollowupName = Utils.findRequiredViewAsType(source, R.id.et_name, "field 'editTextFollowupName'", EditText.class);
    target.editTextDesignation = Utils.findRequiredViewAsType(source, R.id.et_designation, "field 'editTextDesignation'", EditText.class);
    target.editTextEmail = Utils.findRequiredViewAsType(source, R.id.et_email, "field 'editTextEmail'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AddCallActivity1 target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.relativeLayoutCallType = null;
    target.relativeLayoutCallDate = null;
    target.relativeLayoutPlannedDate = null;
    target.relativeLayoutCallPurpose = null;
    target.relativeLayoutMode = null;
    target.relativeLayoutFollowupAction = null;
    target.relativeLayoutFollowupDate = null;
    target.relativeLayoutFollowupStatus = null;
    target.relativeLayoutFollowupBy = null;
    target.relativeLayoutDesignation = null;
    target.editTextCallDate = null;
    target.editTextCustomer = null;
    target.editTextSalesman = null;
    target.editTextSalesTerritory = null;
    target.editTextPlannedDate = null;
    target.editTextCSPerson = null;
    target.editTextCallType = null;
    target.editTextCallPurpose = null;
    target.editTextMode = null;
    target.editTextDiscription = null;
    target.editTextFollowupAction = null;
    target.editTextFollowupDate = null;
    target.editTextFollowupStatus = null;
    target.editTextFollowupBy = null;
    target.editTextFollowupName = null;
    target.editTextDesignation = null;
    target.editTextEmail = null;
  }
}
