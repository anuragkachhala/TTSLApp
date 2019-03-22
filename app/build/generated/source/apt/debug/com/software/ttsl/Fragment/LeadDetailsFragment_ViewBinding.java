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

public class LeadDetailsFragment_ViewBinding implements Unbinder {
  private LeadDetailsFragment target;

  @UiThread
  public LeadDetailsFragment_ViewBinding(LeadDetailsFragment target, View source) {
    this.target = target;

    target.textViewSmartView = Utils.findRequiredViewAsType(source, R.id.tv_smart_view, "field 'textViewSmartView'", TextView.class);
    target.textViewLabelLeadCompany = Utils.findRequiredViewAsType(source, R.id.tv_label_lead_company, "field 'textViewLabelLeadCompany'", TextView.class);
    target.textViewLabelLeadName = Utils.findRequiredViewAsType(source, R.id.tv_label_lead_name, "field 'textViewLabelLeadName'", TextView.class);
    target.textViewLeadDiscriptionInfo = Utils.findRequiredViewAsType(source, R.id.layout_discription_info, "field 'textViewLeadDiscriptionInfo'", TextView.class);
    target.textViewLeadAddressInfo = Utils.findRequiredViewAsType(source, R.id.layout_address_info, "field 'textViewLeadAddressInfo'", TextView.class);
    target.textViewLeadOwner = Utils.findRequiredViewAsType(source, R.id.tv_lead_owner, "field 'textViewLeadOwner'", TextView.class);
    target.textViewLeadCompany = Utils.findRequiredViewAsType(source, R.id.tv_lead_company, "field 'textViewLeadCompany'", TextView.class);
    target.textViewFirstName = Utils.findRequiredViewAsType(source, R.id.tv_lead_first_name, "field 'textViewFirstName'", TextView.class);
    target.textViewLastName = Utils.findRequiredViewAsType(source, R.id.tv_lead_last_name, "field 'textViewLastName'", TextView.class);
    target.linearLayoutLeadStatus = Utils.findRequiredViewAsType(source, R.id.layout_lead_status, "field 'linearLayoutLeadStatus'", LinearLayout.class);
    target.textViewLeadStatus = Utils.findRequiredViewAsType(source, R.id.tv_lead_status, "field 'textViewLeadStatus'", TextView.class);
    target.textViewTitle = Utils.findRequiredViewAsType(source, R.id.tv_lead_title, "field 'textViewTitle'", TextView.class);
    target.textViewEmailId = Utils.findRequiredViewAsType(source, R.id.tv_lead_email_id, "field 'textViewEmailId'", TextView.class);
    target.textViewLeadPhone = Utils.findRequiredViewAsType(source, R.id.tv_lead_phone, "field 'textViewLeadPhone'", TextView.class);
    target.textViewLeadFax = Utils.findRequiredViewAsType(source, R.id.tv_lead_fax, "field 'textViewLeadFax'", TextView.class);
    target.textViewLeadMobile = Utils.findRequiredViewAsType(source, R.id.tv_lead_mobile, "field 'textViewLeadMobile'", TextView.class);
    target.textViewLeadWebsite = Utils.findRequiredViewAsType(source, R.id.tv_lead_website, "field 'textViewLeadWebsite'", TextView.class);
    target.textViewLeadSource = Utils.findRequiredViewAsType(source, R.id.tv_lead_source, "field 'textViewLeadSource'", TextView.class);
    target.textViewIndustry = Utils.findRequiredViewAsType(source, R.id.tv_lead_industry, "field 'textViewIndustry'", TextView.class);
    target.textViewEmployees = Utils.findRequiredViewAsType(source, R.id.tv_lead_employee, "field 'textViewEmployees'", TextView.class);
    target.textViewModifiedBy = Utils.findRequiredViewAsType(source, R.id.tv_lead_Modified_By, "field 'textViewModifiedBy'", TextView.class);
    target.textViewLeadRevenue = Utils.findRequiredViewAsType(source, R.id.tv_lead_revenue, "field 'textViewLeadRevenue'", TextView.class);
    target.textViewRating = Utils.findRequiredViewAsType(source, R.id.tv_lead_rating, "field 'textViewRating'", TextView.class);
    target.textViewLeadCreatedBy = Utils.findRequiredViewAsType(source, R.id.tv_lead_created_by, "field 'textViewLeadCreatedBy'", TextView.class);
    target.textViewEmailOption = Utils.findRequiredViewAsType(source, R.id.tv_lead_email_opt, "field 'textViewEmailOption'", TextView.class);
    target.textViewLeadCreatedTime = Utils.findRequiredViewAsType(source, R.id.tv_lead_created_time, "field 'textViewLeadCreatedTime'", TextView.class);
    target.textViewLeadModifiedTime = Utils.findRequiredViewAsType(source, R.id.tv_lead_modified_time, "field 'textViewLeadModifiedTime'", TextView.class);
    target.textViewSecondaryMail = Utils.findRequiredViewAsType(source, R.id.tv_lead_secondry_mail, "field 'textViewSecondaryMail'", TextView.class);
    target.textViewLastActivityTime = Utils.findRequiredViewAsType(source, R.id.tv_lead_last_activity_time, "field 'textViewLastActivityTime'", TextView.class);
    target.textViewLeadStreet = Utils.findRequiredViewAsType(source, R.id.tv_lead_address_street, "field 'textViewLeadStreet'", TextView.class);
    target.textViewLeadCity = Utils.findRequiredViewAsType(source, R.id.tv_lead_address_city, "field 'textViewLeadCity'", TextView.class);
    target.textViewLeadState = Utils.findRequiredViewAsType(source, R.id.tv_lead_state, "field 'textViewLeadState'", TextView.class);
    target.textViewLeadZipCode = Utils.findRequiredViewAsType(source, R.id.tv_lead_address_zip_code, "field 'textViewLeadZipCode'", TextView.class);
    target.textViewLeadCountry = Utils.findRequiredViewAsType(source, R.id.tv_lead_address_country, "field 'textViewLeadCountry'", TextView.class);
    target.textViewDescription = Utils.findRequiredViewAsType(source, R.id.tv_lead_description, "field 'textViewDescription'", TextView.class);
    target.linearLayoutLeadOwner = Utils.findRequiredViewAsType(source, R.id.linear_layout_lead_owner, "field 'linearLayoutLeadOwner'", LinearLayout.class);
    target.linearLayoutFistName = Utils.findRequiredViewAsType(source, R.id.layout_lead_first_name, "field 'linearLayoutFistName'", LinearLayout.class);
    target.linearLayoutLastName = Utils.findRequiredViewAsType(source, R.id.layout_lead_last_name, "field 'linearLayoutLastName'", LinearLayout.class);
    target.linearLayoutIndustry = Utils.findRequiredViewAsType(source, R.id.layout_lead_industry, "field 'linearLayoutIndustry'", LinearLayout.class);
    target.linearLayoutLeadCompany = Utils.findRequiredViewAsType(source, R.id.layout_lead_company, "field 'linearLayoutLeadCompany'", LinearLayout.class);
    target.linearLayoutEmailId = Utils.findRequiredViewAsType(source, R.id.layout_lead_email_id, "field 'linearLayoutEmailId'", LinearLayout.class);
    target.linearLayoutLeadPhone = Utils.findRequiredViewAsType(source, R.id.layout_lead_phone, "field 'linearLayoutLeadPhone'", LinearLayout.class);
    target.linearLayoutLeadFax = Utils.findRequiredViewAsType(source, R.id.layout_lead_fax, "field 'linearLayoutLeadFax'", LinearLayout.class);
    target.linearLayoutLeadMobile = Utils.findRequiredViewAsType(source, R.id.layout_lead_mobile, "field 'linearLayoutLeadMobile'", LinearLayout.class);
    target.linearLayoutLeadTitle = Utils.findRequiredViewAsType(source, R.id.layout_lead_title, "field 'linearLayoutLeadTitle'", LinearLayout.class);
    target.linearLayoutLeadRating = Utils.findRequiredViewAsType(source, R.id.layout_lead_rating, "field 'linearLayoutLeadRating'", LinearLayout.class);
    target.linearLayoutLeadRevenue = Utils.findRequiredViewAsType(source, R.id.layout_lead_revenue, "field 'linearLayoutLeadRevenue'", LinearLayout.class);
    target.linearLayoutEmaployees = Utils.findRequiredViewAsType(source, R.id.layout_lead_employee, "field 'linearLayoutEmaployees'", LinearLayout.class);
    target.linearLayoutLeadSecondryMail = Utils.findRequiredViewAsType(source, R.id.layout_lead_secondry_mail, "field 'linearLayoutLeadSecondryMail'", LinearLayout.class);
    target.linearLayoutLeadDescription = Utils.findRequiredViewAsType(source, R.id.layout_lead_description, "field 'linearLayoutLeadDescription'", LinearLayout.class);
    target.linearLayoutWebSite = Utils.findRequiredViewAsType(source, R.id.layout_lead_website, "field 'linearLayoutWebSite'", LinearLayout.class);
    target.linearLayoutLeadStreet = Utils.findRequiredViewAsType(source, R.id.layout_lead_street, "field 'linearLayoutLeadStreet'", LinearLayout.class);
    target.linearLayoutLeadCity = Utils.findRequiredViewAsType(source, R.id.layout_lead_city, "field 'linearLayoutLeadCity'", LinearLayout.class);
    target.linearLayoutLeadState = Utils.findRequiredViewAsType(source, R.id.layout_lead_state, "field 'linearLayoutLeadState'", LinearLayout.class);
    target.linearLayoutLeadZipCode = Utils.findRequiredViewAsType(source, R.id.layout_lead_zip_code, "field 'linearLayoutLeadZipCode'", LinearLayout.class);
    target.linearLayoutLeadCountry = Utils.findRequiredViewAsType(source, R.id.layout_lead_country, "field 'linearLayoutLeadCountry'", LinearLayout.class);
    target.linearLayoutLeadSource = Utils.findRequiredViewAsType(source, R.id.layout_lead_source, "field 'linearLayoutLeadSource'", LinearLayout.class);
    target.textViewLeadSolution = Utils.findRequiredViewAsType(source, R.id.tv_lead_solution, "field 'textViewLeadSolution'", TextView.class);
    target.linearLayoutLeadSolution = Utils.findRequiredViewAsType(source, R.id.layout_lead_solution, "field 'linearLayoutLeadSolution'", LinearLayout.class);
    target.linearLayoutSkypeId = Utils.findRequiredViewAsType(source, R.id.layout_lead_skype_id, "field 'linearLayoutSkypeId'", LinearLayout.class);
    target.textViewSkypeId = Utils.findRequiredViewAsType(source, R.id.tv_lead_skype_id, "field 'textViewSkypeId'", TextView.class);
    target.linearLayoutLeadTwitter = Utils.findRequiredViewAsType(source, R.id.layout_lead_twitter, "field 'linearLayoutLeadTwitter'", LinearLayout.class);
    target.textViewLeadTwitter = Utils.findRequiredViewAsType(source, R.id.tv_lead_twitter, "field 'textViewLeadTwitter'", TextView.class);
    target.linearLayoutLeadEmailOpt = Utils.findRequiredViewAsType(source, R.id.layout_lead_email_opt, "field 'linearLayoutLeadEmailOpt'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LeadDetailsFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.textViewSmartView = null;
    target.textViewLabelLeadCompany = null;
    target.textViewLabelLeadName = null;
    target.textViewLeadDiscriptionInfo = null;
    target.textViewLeadAddressInfo = null;
    target.textViewLeadOwner = null;
    target.textViewLeadCompany = null;
    target.textViewFirstName = null;
    target.textViewLastName = null;
    target.linearLayoutLeadStatus = null;
    target.textViewLeadStatus = null;
    target.textViewTitle = null;
    target.textViewEmailId = null;
    target.textViewLeadPhone = null;
    target.textViewLeadFax = null;
    target.textViewLeadMobile = null;
    target.textViewLeadWebsite = null;
    target.textViewLeadSource = null;
    target.textViewIndustry = null;
    target.textViewEmployees = null;
    target.textViewModifiedBy = null;
    target.textViewLeadRevenue = null;
    target.textViewRating = null;
    target.textViewLeadCreatedBy = null;
    target.textViewEmailOption = null;
    target.textViewLeadCreatedTime = null;
    target.textViewLeadModifiedTime = null;
    target.textViewSecondaryMail = null;
    target.textViewLastActivityTime = null;
    target.textViewLeadStreet = null;
    target.textViewLeadCity = null;
    target.textViewLeadState = null;
    target.textViewLeadZipCode = null;
    target.textViewLeadCountry = null;
    target.textViewDescription = null;
    target.linearLayoutLeadOwner = null;
    target.linearLayoutFistName = null;
    target.linearLayoutLastName = null;
    target.linearLayoutIndustry = null;
    target.linearLayoutLeadCompany = null;
    target.linearLayoutEmailId = null;
    target.linearLayoutLeadPhone = null;
    target.linearLayoutLeadFax = null;
    target.linearLayoutLeadMobile = null;
    target.linearLayoutLeadTitle = null;
    target.linearLayoutLeadRating = null;
    target.linearLayoutLeadRevenue = null;
    target.linearLayoutEmaployees = null;
    target.linearLayoutLeadSecondryMail = null;
    target.linearLayoutLeadDescription = null;
    target.linearLayoutWebSite = null;
    target.linearLayoutLeadStreet = null;
    target.linearLayoutLeadCity = null;
    target.linearLayoutLeadState = null;
    target.linearLayoutLeadZipCode = null;
    target.linearLayoutLeadCountry = null;
    target.linearLayoutLeadSource = null;
    target.textViewLeadSolution = null;
    target.linearLayoutLeadSolution = null;
    target.linearLayoutSkypeId = null;
    target.textViewSkypeId = null;
    target.linearLayoutLeadTwitter = null;
    target.textViewLeadTwitter = null;
    target.linearLayoutLeadEmailOpt = null;
  }
}
