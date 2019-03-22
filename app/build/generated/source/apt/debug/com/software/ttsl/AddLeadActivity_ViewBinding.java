// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddLeadActivity_ViewBinding implements Unbinder {
  private AddLeadActivity target;

  @UiThread
  public AddLeadActivity_ViewBinding(AddLeadActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddLeadActivity_ViewBinding(AddLeadActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.linearLayoutLeadSource = Utils.findRequiredViewAsType(source, R.id.linear_layout_lead_source, "field 'linearLayoutLeadSource'", LinearLayout.class);
    target.relativeLayoutLeadSource = Utils.findRequiredViewAsType(source, R.id.layout_lead_source, "field 'relativeLayoutLeadSource'", RelativeLayout.class);
    target.relativeLayoutLeadStatus = Utils.findRequiredViewAsType(source, R.id.layout_lead_status, "field 'relativeLayoutLeadStatus'", RelativeLayout.class);
    target.textViewLeadSource = Utils.findRequiredViewAsType(source, R.id.et_lead_source, "field 'textViewLeadSource'", TextView.class);
    target.linearLayoutLeadStatus = Utils.findRequiredViewAsType(source, R.id.linear_layout_lead_status, "field 'linearLayoutLeadStatus'", LinearLayout.class);
    target.textViewLeadStatus = Utils.findRequiredViewAsType(source, R.id.et_lead_status, "field 'textViewLeadStatus'", TextView.class);
    target.linearLayoutLeadOwner = Utils.findRequiredViewAsType(source, R.id.linear_layout_lead_owner, "field 'linearLayoutLeadOwner'", LinearLayout.class);
    target.relativeLayoutLeadOwner = Utils.findRequiredViewAsType(source, R.id.layout_lead_owner, "field 'relativeLayoutLeadOwner'", RelativeLayout.class);
    target.textViewLeadOwner = Utils.findRequiredViewAsType(source, R.id.et_lead_owner, "field 'textViewLeadOwner'", TextView.class);
    target.editTextLeadCompany = Utils.findRequiredViewAsType(source, R.id.et_lead_company, "field 'editTextLeadCompany'", EditText.class);
    target.editTextLeadFirstName = Utils.findRequiredViewAsType(source, R.id.et_lead_first_name, "field 'editTextLeadFirstName'", EditText.class);
    target.editTextLeadLastName = Utils.findRequiredViewAsType(source, R.id.et_lead_last_name, "field 'editTextLeadLastName'", EditText.class);
    target.linearLayoutLeadTitle = Utils.findRequiredViewAsType(source, R.id.linear_layout_lead_title, "field 'linearLayoutLeadTitle'", LinearLayout.class);
    target.editTextLeadTitle = Utils.findRequiredViewAsType(source, R.id.et_lead_title, "field 'editTextLeadTitle'", EditText.class);
    target.switchLeadEmailOpt = Utils.findRequiredViewAsType(source, R.id.switch_lead_email_opt, "field 'switchLeadEmailOpt'", Switch.class);
    target.editTextLeadPhone = Utils.findRequiredViewAsType(source, R.id.et_lead_phone, "field 'editTextLeadPhone'", EditText.class);
    target.linearLayoutLeadFax = Utils.findRequiredViewAsType(source, R.id.linear_layout_lead_fax, "field 'linearLayoutLeadFax'", LinearLayout.class);
    target.editTextLeadFax = Utils.findRequiredViewAsType(source, R.id.et_lead_fax, "field 'editTextLeadFax'", EditText.class);
    target.editTextEmail = Utils.findRequiredViewAsType(source, R.id.et_lead_email, "field 'editTextEmail'", EditText.class);
    target.linearLayoutLeadMobile = Utils.findRequiredViewAsType(source, R.id.linear_layout_lead_mobile, "field 'linearLayoutLeadMobile'", LinearLayout.class);
    target.editTextLeadMobile = Utils.findRequiredViewAsType(source, R.id.et_lead_mobile, "field 'editTextLeadMobile'", EditText.class);
    target.linearLayoutLeadWebsite = Utils.findRequiredViewAsType(source, R.id.linear_layout_lead_website, "field 'linearLayoutLeadWebsite'", LinearLayout.class);
    target.editTextLeadWebsite = Utils.findRequiredViewAsType(source, R.id.et_lead_website, "field 'editTextLeadWebsite'", EditText.class);
    target.linearLayoutIndustry = Utils.findRequiredViewAsType(source, R.id.linear_layout_industry, "field 'linearLayoutIndustry'", LinearLayout.class);
    target.relativeLayoutLeadIndustry = Utils.findRequiredViewAsType(source, R.id.layout_lead_industry, "field 'relativeLayoutLeadIndustry'", RelativeLayout.class);
    target.textViewLeadIndustry = Utils.findRequiredViewAsType(source, R.id.et_lead_industry, "field 'textViewLeadIndustry'", TextView.class);
    target.linearLayoutLeadEmployees = Utils.findRequiredViewAsType(source, R.id.linear_layout_lead_employee, "field 'linearLayoutLeadEmployees'", LinearLayout.class);
    target.editTextLeadEmployees = Utils.findRequiredViewAsType(source, R.id.et_lead_employee, "field 'editTextLeadEmployees'", EditText.class);
    target.linearLayoutLeadRevenue = Utils.findRequiredViewAsType(source, R.id.linear_layout_lead_revenue, "field 'linearLayoutLeadRevenue'", LinearLayout.class);
    target.editTextLeadRevenue = Utils.findRequiredViewAsType(source, R.id.et_lead_revenue, "field 'editTextLeadRevenue'", EditText.class);
    target.linearLayoutLeadRating = Utils.findRequiredViewAsType(source, R.id.linear_layout_rating, "field 'linearLayoutLeadRating'", LinearLayout.class);
    target.relativeLayoutLeadRating = Utils.findRequiredViewAsType(source, R.id.layout_lead_rating, "field 'relativeLayoutLeadRating'", RelativeLayout.class);
    target.textViewLeadRating = Utils.findRequiredViewAsType(source, R.id.et_lead_rating, "field 'textViewLeadRating'", TextView.class);
    target.relativeLayoutEmailOpt = Utils.findRequiredViewAsType(source, R.id.layout_lead_email_opt, "field 'relativeLayoutEmailOpt'", RelativeLayout.class);
    target.editTextEmailOpt = Utils.findRequiredViewAsType(source, R.id.et_lead_email_opt, "field 'editTextEmailOpt'", EditText.class);
    target.linearLayoutLeadSkypeId = Utils.findRequiredViewAsType(source, R.id.linear_layout_lead_skype_id, "field 'linearLayoutLeadSkypeId'", LinearLayout.class);
    target.linearLayoutSolution = Utils.findRequiredViewAsType(source, R.id.linear_layout_solution, "field 'linearLayoutSolution'", LinearLayout.class);
    target.relativeLayoutLeadSolution = Utils.findRequiredViewAsType(source, R.id.layout_lead_solution, "field 'relativeLayoutLeadSolution'", RelativeLayout.class);
    target.textViewLeadSolution = Utils.findRequiredViewAsType(source, R.id.et_lead_solution, "field 'textViewLeadSolution'", TextView.class);
    target.linearLayoutLeadSecondryMail = Utils.findRequiredViewAsType(source, R.id.linear_layout_lead_secondry_mail, "field 'linearLayoutLeadSecondryMail'", LinearLayout.class);
    target.editTextSecondaryMail = Utils.findRequiredViewAsType(source, R.id.et_lead_secondry_mail, "field 'editTextSecondaryMail'", EditText.class);
    target.linearLayoutLeadTwitter = Utils.findRequiredViewAsType(source, R.id.linear_layout_lead_twitter, "field 'linearLayoutLeadTwitter'", LinearLayout.class);
    target.editTextLeadTwitter = Utils.findRequiredViewAsType(source, R.id.et_lead_twitter, "field 'editTextLeadTwitter'", EditText.class);
    target.editTextLeadSkypeId = Utils.findRequiredViewAsType(source, R.id.et_lead_skype_id, "field 'editTextLeadSkypeId'", EditText.class);
    target.editTextLeadCity = Utils.findRequiredViewAsType(source, R.id.et_lead_city, "field 'editTextLeadCity'", EditText.class);
    target.editTextLeadZipCode = Utils.findRequiredViewAsType(source, R.id.et_lead_zip_code, "field 'editTextLeadZipCode'", EditText.class);
    target.editTextLeadCountry = Utils.findRequiredViewAsType(source, R.id.et_lead_country, "field 'editTextLeadCountry'", EditText.class);
    target.editTextLeadStreet = Utils.findRequiredViewAsType(source, R.id.et_lead_street, "field 'editTextLeadStreet'", EditText.class);
    target.editTextLeadState = Utils.findRequiredViewAsType(source, R.id.et_lead_state, "field 'editTextLeadState'", EditText.class);
    target.editTextLeadDiscription = Utils.findRequiredViewAsType(source, R.id.et_lead_description, "field 'editTextLeadDiscription'", EditText.class);
    target.imageViewLeadPhoto = Utils.findRequiredViewAsType(source, R.id.iv_lead_photo, "field 'imageViewLeadPhoto'", ImageView.class);
    target.linearLayoutAddressInformation = Utils.findRequiredViewAsType(source, R.id.linear_layout_address_information, "field 'linearLayoutAddressInformation'", LinearLayout.class);
    target.relativeLayoutLeadImage = Utils.findRequiredViewAsType(source, R.id.layout_lead_image, "field 'relativeLayoutLeadImage'", RelativeLayout.class);
    target.imageViewLeadCamera = Utils.findRequiredViewAsType(source, R.id.iv_lead_camera, "field 'imageViewLeadCamera'", ImageView.class);
    target.textViewAddPhoto = Utils.findRequiredViewAsType(source, R.id.tv_add_photo, "field 'textViewAddPhoto'", TextView.class);
    target.textViewSmartView = Utils.findRequiredViewAsType(source, R.id.tv_smart_view, "field 'textViewSmartView'", TextView.class);
    target.textViewLeadCompany = Utils.findRequiredViewAsType(source, R.id.tv_lead_company, "field 'textViewLeadCompany'", TextView.class);
    target.textViewLeadLastName = Utils.findRequiredViewAsType(source, R.id.tv_lead_last_name, "field 'textViewLeadLastName'", TextView.class);
    target.textViewErrorCompany = Utils.findRequiredViewAsType(source, R.id.tv_error_company, "field 'textViewErrorCompany'", TextView.class);
    target.textViewErrorLastName = Utils.findRequiredViewAsType(source, R.id.tv_error_last_name, "field 'textViewErrorLastName'", TextView.class);
    target.textViewErrorPhone = Utils.findRequiredViewAsType(source, R.id.tv_error_phone, "field 'textViewErrorPhone'", TextView.class);
    target.textViewErrorMail = Utils.findRequiredViewAsType(source, R.id.tv_error_mail, "field 'textViewErrorMail'", TextView.class);
    target.textViewErrorFax = Utils.findRequiredViewAsType(source, R.id.tv_error_fax, "field 'textViewErrorFax'", TextView.class);
    target.textViewErrorMobile = Utils.findRequiredViewAsType(source, R.id.tv_error_mobile, "field 'textViewErrorMobile'", TextView.class);
    target.textViewErrorWebsite = Utils.findRequiredViewAsType(source, R.id.tv_error_website, "field 'textViewErrorWebsite'", TextView.class);
    target.textViewErrorSkypId = Utils.findRequiredViewAsType(source, R.id.tv_error_skype_id, "field 'textViewErrorSkypId'", TextView.class);
    target.textViewErrorTitle = Utils.findRequiredViewAsType(source, R.id.tv_error_lead_title, "field 'textViewErrorTitle'", TextView.class);
    target.textViewErrorFirstName = Utils.findRequiredViewAsType(source, R.id.tv_error_first_name, "field 'textViewErrorFirstName'", TextView.class);
    target.textViewErrorSecondaryMail = Utils.findRequiredViewAsType(source, R.id.tv_error_secondary_mail, "field 'textViewErrorSecondaryMail'", TextView.class);
    target.textViewErrorTwitter = Utils.findRequiredViewAsType(source, R.id.tv_error_twitter, "field 'textViewErrorTwitter'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AddLeadActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.linearLayoutLeadSource = null;
    target.relativeLayoutLeadSource = null;
    target.relativeLayoutLeadStatus = null;
    target.textViewLeadSource = null;
    target.linearLayoutLeadStatus = null;
    target.textViewLeadStatus = null;
    target.linearLayoutLeadOwner = null;
    target.relativeLayoutLeadOwner = null;
    target.textViewLeadOwner = null;
    target.editTextLeadCompany = null;
    target.editTextLeadFirstName = null;
    target.editTextLeadLastName = null;
    target.linearLayoutLeadTitle = null;
    target.editTextLeadTitle = null;
    target.switchLeadEmailOpt = null;
    target.editTextLeadPhone = null;
    target.linearLayoutLeadFax = null;
    target.editTextLeadFax = null;
    target.editTextEmail = null;
    target.linearLayoutLeadMobile = null;
    target.editTextLeadMobile = null;
    target.linearLayoutLeadWebsite = null;
    target.editTextLeadWebsite = null;
    target.linearLayoutIndustry = null;
    target.relativeLayoutLeadIndustry = null;
    target.textViewLeadIndustry = null;
    target.linearLayoutLeadEmployees = null;
    target.editTextLeadEmployees = null;
    target.linearLayoutLeadRevenue = null;
    target.editTextLeadRevenue = null;
    target.linearLayoutLeadRating = null;
    target.relativeLayoutLeadRating = null;
    target.textViewLeadRating = null;
    target.relativeLayoutEmailOpt = null;
    target.editTextEmailOpt = null;
    target.linearLayoutLeadSkypeId = null;
    target.linearLayoutSolution = null;
    target.relativeLayoutLeadSolution = null;
    target.textViewLeadSolution = null;
    target.linearLayoutLeadSecondryMail = null;
    target.editTextSecondaryMail = null;
    target.linearLayoutLeadTwitter = null;
    target.editTextLeadTwitter = null;
    target.editTextLeadSkypeId = null;
    target.editTextLeadCity = null;
    target.editTextLeadZipCode = null;
    target.editTextLeadCountry = null;
    target.editTextLeadStreet = null;
    target.editTextLeadState = null;
    target.editTextLeadDiscription = null;
    target.imageViewLeadPhoto = null;
    target.linearLayoutAddressInformation = null;
    target.relativeLayoutLeadImage = null;
    target.imageViewLeadCamera = null;
    target.textViewAddPhoto = null;
    target.textViewSmartView = null;
    target.textViewLeadCompany = null;
    target.textViewLeadLastName = null;
    target.textViewErrorCompany = null;
    target.textViewErrorLastName = null;
    target.textViewErrorPhone = null;
    target.textViewErrorMail = null;
    target.textViewErrorFax = null;
    target.textViewErrorMobile = null;
    target.textViewErrorWebsite = null;
    target.textViewErrorSkypId = null;
    target.textViewErrorTitle = null;
    target.textViewErrorFirstName = null;
    target.textViewErrorSecondaryMail = null;
    target.textViewErrorTwitter = null;
  }
}
