// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EditDealActivity_ViewBinding implements Unbinder {
  private EditDealActivity target;

  @UiThread
  public EditDealActivity_ViewBinding(EditDealActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EditDealActivity_ViewBinding(EditDealActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.linearLayoutDealOwner = Utils.findRequiredViewAsType(source, R.id.linear_layout_deal_owner, "field 'linearLayoutDealOwner'", LinearLayout.class);
    target.linearLayoutDealType = Utils.findRequiredViewAsType(source, R.id.linear_layout_deal_type, "field 'linearLayoutDealType'", LinearLayout.class);
    target.linearLayoutDealProbability = Utils.findRequiredViewAsType(source, R.id.linear_layout_deal_probability, "field 'linearLayoutDealProbability'", LinearLayout.class);
    target.linearLayoutDealNextStep = Utils.findRequiredViewAsType(source, R.id.linear_layout_deal_next_step, "field 'linearLayoutDealNextStep'", LinearLayout.class);
    target.linearLayoutLeadSource = Utils.findRequiredViewAsType(source, R.id.linear_layout_lead_source, "field 'linearLayoutLeadSource'", LinearLayout.class);
    target.linearLayoutDealCampaignSource = Utils.findRequiredViewAsType(source, R.id.linear_layout_deal_campaign_source, "field 'linearLayoutDealCampaignSource'", LinearLayout.class);
    target.linearLayoutContactName = Utils.findRequiredViewAsType(source, R.id.linear_layout_contact_name, "field 'linearLayoutContactName'", LinearLayout.class);
    target.linearLayoutDescriptionInformation = Utils.findRequiredViewAsType(source, R.id.linear_layout_description_information, "field 'linearLayoutDescriptionInformation'", LinearLayout.class);
    target.editTextDealOwner = Utils.findRequiredViewAsType(source, R.id.et_deal_owner, "field 'editTextDealOwner'", EditText.class);
    target.editTextDealAmount = Utils.findRequiredViewAsType(source, R.id.et_deal_amount, "field 'editTextDealAmount'", EditText.class);
    target.editTextDealName = Utils.findRequiredViewAsType(source, R.id.et_deal_name, "field 'editTextDealName'", EditText.class);
    target.editTextClosingDate = Utils.findRequiredViewAsType(source, R.id.et_deal_closing_date, "field 'editTextClosingDate'", EditText.class);
    target.editTextAccountName = Utils.findRequiredViewAsType(source, R.id.et_account_name, "field 'editTextAccountName'", EditText.class);
    target.editTextDealStage = Utils.findRequiredViewAsType(source, R.id.et_deal_stage, "field 'editTextDealStage'", EditText.class);
    target.editTextDealType = Utils.findRequiredViewAsType(source, R.id.et_deal_type, "field 'editTextDealType'", EditText.class);
    target.editTextDealProbability = Utils.findRequiredViewAsType(source, R.id.et_deal_probability, "field 'editTextDealProbability'", EditText.class);
    target.editTextNextStep = Utils.findRequiredViewAsType(source, R.id.et_next_step, "field 'editTextNextStep'", EditText.class);
    target.editTextDealExRevenue = Utils.findRequiredViewAsType(source, R.id.et_deal_ex_revenue, "field 'editTextDealExRevenue'", EditText.class);
    target.editTextLeadSource = Utils.findRequiredViewAsType(source, R.id.et_lead_source, "field 'editTextLeadSource'", EditText.class);
    target.editTextDealCampaignSource = Utils.findRequiredViewAsType(source, R.id.et_deal_campaign_source, "field 'editTextDealCampaignSource'", EditText.class);
    target.editTextContactName = Utils.findRequiredViewAsType(source, R.id.et_contact_name, "field 'editTextContactName'", EditText.class);
    target.editTextDealDescription = Utils.findRequiredViewAsType(source, R.id.et_deal_description, "field 'editTextDealDescription'", EditText.class);
    target.imageViewDealOwner = Utils.findRequiredViewAsType(source, R.id.iv_deal_owner, "field 'imageViewDealOwner'", ImageView.class);
    target.imageViewDealClosingDate = Utils.findRequiredViewAsType(source, R.id.iv_deal_closing_date, "field 'imageViewDealClosingDate'", ImageView.class);
    target.imageViewAccountName = Utils.findRequiredViewAsType(source, R.id.iv_account_name, "field 'imageViewAccountName'", ImageView.class);
    target.imageViewDealStage = Utils.findRequiredViewAsType(source, R.id.iv_deal_stage, "field 'imageViewDealStage'", ImageView.class);
    target.imageViewDealType = Utils.findRequiredViewAsType(source, R.id.iv_deal_type, "field 'imageViewDealType'", ImageView.class);
    target.imageViewLeadSource = Utils.findRequiredViewAsType(source, R.id.iv_lead_source, "field 'imageViewLeadSource'", ImageView.class);
    target.imageViewDealCampaignSource = Utils.findRequiredViewAsType(source, R.id.iv_deal_campaign_source, "field 'imageViewDealCampaignSource'", ImageView.class);
    target.imageViewContactName = Utils.findRequiredViewAsType(source, R.id.iv_contact_name, "field 'imageViewContactName'", ImageView.class);
    target.textViewSmartView = Utils.findRequiredViewAsType(source, R.id.tv_smart_view, "field 'textViewSmartView'", TextView.class);
    target.textViewDealName = Utils.findRequiredViewAsType(source, R.id.tv_deal_name, "field 'textViewDealName'", TextView.class);
    target.textViewDealClosingDate = Utils.findRequiredViewAsType(source, R.id.tv_deal_closing_date, "field 'textViewDealClosingDate'", TextView.class);
    target.textViewDealAccountName = Utils.findRequiredViewAsType(source, R.id.tv_account_name, "field 'textViewDealAccountName'", TextView.class);
    target.textViewDealStage = Utils.findRequiredViewAsType(source, R.id.tv_deal_stage, "field 'textViewDealStage'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EditDealActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.linearLayoutDealOwner = null;
    target.linearLayoutDealType = null;
    target.linearLayoutDealProbability = null;
    target.linearLayoutDealNextStep = null;
    target.linearLayoutLeadSource = null;
    target.linearLayoutDealCampaignSource = null;
    target.linearLayoutContactName = null;
    target.linearLayoutDescriptionInformation = null;
    target.editTextDealOwner = null;
    target.editTextDealAmount = null;
    target.editTextDealName = null;
    target.editTextClosingDate = null;
    target.editTextAccountName = null;
    target.editTextDealStage = null;
    target.editTextDealType = null;
    target.editTextDealProbability = null;
    target.editTextNextStep = null;
    target.editTextDealExRevenue = null;
    target.editTextLeadSource = null;
    target.editTextDealCampaignSource = null;
    target.editTextContactName = null;
    target.editTextDealDescription = null;
    target.imageViewDealOwner = null;
    target.imageViewDealClosingDate = null;
    target.imageViewAccountName = null;
    target.imageViewDealStage = null;
    target.imageViewDealType = null;
    target.imageViewLeadSource = null;
    target.imageViewDealCampaignSource = null;
    target.imageViewContactName = null;
    target.textViewSmartView = null;
    target.textViewDealName = null;
    target.textViewDealClosingDate = null;
    target.textViewDealAccountName = null;
    target.textViewDealStage = null;
  }
}
