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

public class DealDetailFragment_ViewBinding implements Unbinder {
  private DealDetailFragment target;

  @UiThread
  public DealDetailFragment_ViewBinding(DealDetailFragment target, View source) {
    this.target = target;

    target.linearLayoutDealAmount = Utils.findRequiredViewAsType(source, R.id.linear_layout_deal_amount, "field 'linearLayoutDealAmount'", LinearLayout.class);
    target.linearLayoutDealType = Utils.findRequiredViewAsType(source, R.id.linear_layout_deal_type, "field 'linearLayoutDealType'", LinearLayout.class);
    target.linearLayoutDealProbability = Utils.findRequiredViewAsType(source, R.id.linear_layout_deal_probability, "field 'linearLayoutDealProbability'", LinearLayout.class);
    target.linearLayoutDealNextStep = Utils.findRequiredViewAsType(source, R.id.linear_layout_deal_next_step, "field 'linearLayoutDealNextStep'", LinearLayout.class);
    target.linearLayoutDealExRevenue = Utils.findRequiredViewAsType(source, R.id.linear_layout_deal_ex_revenue, "field 'linearLayoutDealExRevenue'", LinearLayout.class);
    target.linearLayoutDealLeadSource = Utils.findRequiredViewAsType(source, R.id.linear_layout_deal_lead_source, "field 'linearLayoutDealLeadSource'", LinearLayout.class);
    target.linearLayoutDealCampaignSource = Utils.findRequiredViewAsType(source, R.id.linear_layout_deal_campaign_source, "field 'linearLayoutDealCampaignSource'", LinearLayout.class);
    target.linearLayoutDealContactName = Utils.findRequiredViewAsType(source, R.id.linear_layout_deal_contact_name, "field 'linearLayoutDealContactName'", LinearLayout.class);
    target.linearLayoutDealDescription = Utils.findRequiredViewAsType(source, R.id.layout_deal_description, "field 'linearLayoutDealDescription'", LinearLayout.class);
    target.textViewDealOwner = Utils.findRequiredViewAsType(source, R.id.tv_deal_owner, "field 'textViewDealOwner'", TextView.class);
    target.textViewDealAmount = Utils.findRequiredViewAsType(source, R.id.tv_deal_amount, "field 'textViewDealAmount'", TextView.class);
    target.textViewDealName = Utils.findRequiredViewAsType(source, R.id.tv_deal_name, "field 'textViewDealName'", TextView.class);
    target.textViewDealClosingData = Utils.findRequiredViewAsType(source, R.id.tv_deal_closing_date, "field 'textViewDealClosingData'", TextView.class);
    target.textViewDealAccountName = Utils.findRequiredViewAsType(source, R.id.tv_deal_account_name, "field 'textViewDealAccountName'", TextView.class);
    target.textViewDealStage = Utils.findRequiredViewAsType(source, R.id.tv_deal_stage, "field 'textViewDealStage'", TextView.class);
    target.textViewDealType = Utils.findRequiredViewAsType(source, R.id.tv_deal_type, "field 'textViewDealType'", TextView.class);
    target.textViewDealProbability = Utils.findRequiredViewAsType(source, R.id.tv_deal_probability, "field 'textViewDealProbability'", TextView.class);
    target.textViewDealNextStep = Utils.findRequiredViewAsType(source, R.id.tv_deal_next_step, "field 'textViewDealNextStep'", TextView.class);
    target.textViewDealExRevenue = Utils.findRequiredViewAsType(source, R.id.tv_deal_ex_revenue, "field 'textViewDealExRevenue'", TextView.class);
    target.textViewDealLeadSource = Utils.findRequiredViewAsType(source, R.id.tv_deal_lead_source, "field 'textViewDealLeadSource'", TextView.class);
    target.textViewDealCampaignSource = Utils.findRequiredViewAsType(source, R.id.tv_deal_campaign_source, "field 'textViewDealCampaignSource'", TextView.class);
    target.textViewDealContactName = Utils.findRequiredViewAsType(source, R.id.tv_deal_contact_name, "field 'textViewDealContactName'", TextView.class);
    target.textViewDealCreatedBy = Utils.findRequiredViewAsType(source, R.id.tv_deal_created_by, "field 'textViewDealCreatedBy'", TextView.class);
    target.textViewModifyBy = Utils.findRequiredViewAsType(source, R.id.tv_deal_modify_by, "field 'textViewModifyBy'", TextView.class);
    target.textViewDealCreatedTime = Utils.findRequiredViewAsType(source, R.id.tv_deal_created_time, "field 'textViewDealCreatedTime'", TextView.class);
    target.textViewModifyTime = Utils.findRequiredViewAsType(source, R.id.tv_deal_modify_time, "field 'textViewModifyTime'", TextView.class);
    target.textViewDealDescription = Utils.findRequiredViewAsType(source, R.id.tv_deal_description, "field 'textViewDealDescription'", TextView.class);
    target.textViewSmartView = Utils.findRequiredViewAsType(source, R.id.tv_smart_view, "field 'textViewSmartView'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DealDetailFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.linearLayoutDealAmount = null;
    target.linearLayoutDealType = null;
    target.linearLayoutDealProbability = null;
    target.linearLayoutDealNextStep = null;
    target.linearLayoutDealExRevenue = null;
    target.linearLayoutDealLeadSource = null;
    target.linearLayoutDealCampaignSource = null;
    target.linearLayoutDealContactName = null;
    target.linearLayoutDealDescription = null;
    target.textViewDealOwner = null;
    target.textViewDealAmount = null;
    target.textViewDealName = null;
    target.textViewDealClosingData = null;
    target.textViewDealAccountName = null;
    target.textViewDealStage = null;
    target.textViewDealType = null;
    target.textViewDealProbability = null;
    target.textViewDealNextStep = null;
    target.textViewDealExRevenue = null;
    target.textViewDealLeadSource = null;
    target.textViewDealCampaignSource = null;
    target.textViewDealContactName = null;
    target.textViewDealCreatedBy = null;
    target.textViewModifyBy = null;
    target.textViewDealCreatedTime = null;
    target.textViewModifyTime = null;
    target.textViewDealDescription = null;
    target.textViewSmartView = null;
  }
}
