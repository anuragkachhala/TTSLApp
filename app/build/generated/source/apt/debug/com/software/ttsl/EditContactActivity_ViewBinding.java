// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EditContactActivity_ViewBinding implements Unbinder {
  private EditContactActivity target;

  @UiThread
  public EditContactActivity_ViewBinding(EditContactActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EditContactActivity_ViewBinding(EditContactActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.relativeLayoutContactImage = Utils.findRequiredViewAsType(source, R.id.layout_contact_image, "field 'relativeLayoutContactImage'", RelativeLayout.class);
    target.textViewContactOwner = Utils.findRequiredViewAsType(source, R.id.tv_contact_owner, "field 'textViewContactOwner'", TextView.class);
    target.textViewLeadSource = Utils.findRequiredViewAsType(source, R.id.tv_contact_lead_source, "field 'textViewLeadSource'", TextView.class);
    target.editTextContactFistName = Utils.findRequiredViewAsType(source, R.id.et_contact_first_name, "field 'editTextContactFistName'", EditText.class);
    target.editTextContactLastName = Utils.findRequiredViewAsType(source, R.id.et_contact_last_name, "field 'editTextContactLastName'", EditText.class);
    target.textViewContactAccountName = Utils.findRequiredViewAsType(source, R.id.tv_contact_account_name, "field 'textViewContactAccountName'", TextView.class);
    target.editTextContactEmail = Utils.findRequiredViewAsType(source, R.id.et_contact_email, "field 'editTextContactEmail'", EditText.class);
    target.editTextContactTitle = Utils.findRequiredViewAsType(source, R.id.et_contact_title, "field 'editTextContactTitle'", EditText.class);
    target.editTextContactDeprtment = Utils.findRequiredViewAsType(source, R.id.et_contact_department, "field 'editTextContactDeprtment'", EditText.class);
    target.editTextContactPhone = Utils.findRequiredViewAsType(source, R.id.et_contact_phone, "field 'editTextContactPhone'", EditText.class);
    target.getEditTextContactHomePhone = Utils.findRequiredViewAsType(source, R.id.et_contact_home_phone, "field 'getEditTextContactHomePhone'", EditText.class);
    target.editTextContactOtherPhone = Utils.findRequiredViewAsType(source, R.id.et_contact_other_phone, "field 'editTextContactOtherPhone'", EditText.class);
    target.editTextContactFax = Utils.findRequiredViewAsType(source, R.id.et_contact_fax, "field 'editTextContactFax'", EditText.class);
    target.editTextContactMobile = Utils.findRequiredViewAsType(source, R.id.et_contact_mobile, "field 'editTextContactMobile'", EditText.class);
    target.textViewContactDOB = Utils.findRequiredViewAsType(source, R.id.tv_contact_date_of_birth, "field 'textViewContactDOB'", TextView.class);
    target.editTextContactAssistant = Utils.findRequiredViewAsType(source, R.id.et_contact_assistant, "field 'editTextContactAssistant'", EditText.class);
    target.editTextContactAssistantPhone = Utils.findRequiredViewAsType(source, R.id.et_contact_assistant_phone, "field 'editTextContactAssistantPhone'", EditText.class);
    target.editTextContactReportTo = Utils.findRequiredViewAsType(source, R.id.et_contact_report_to, "field 'editTextContactReportTo'", EditText.class);
    target.editTextContactSkypeId = Utils.findRequiredViewAsType(source, R.id.et_contact_skypeid, "field 'editTextContactSkypeId'", EditText.class);
    target.textViewContactSolution = Utils.findRequiredViewAsType(source, R.id.tv_contact_solution, "field 'textViewContactSolution'", TextView.class);
    target.editTextSecondaryMail = Utils.findRequiredViewAsType(source, R.id.et_contact_secondary_mail, "field 'editTextSecondaryMail'", EditText.class);
    target.editTextContactTwitter = Utils.findRequiredViewAsType(source, R.id.et_contact_twitter, "field 'editTextContactTwitter'", EditText.class);
    target.editTextDiscription = Utils.findRequiredViewAsType(source, R.id.et_contact_description, "field 'editTextDiscription'", EditText.class);
    target.editTextMailStreet = Utils.findRequiredViewAsType(source, R.id.et_account_mail_street, "field 'editTextMailStreet'", EditText.class);
    target.editTextMailCity = Utils.findRequiredViewAsType(source, R.id.et_account_mail_address_city, "field 'editTextMailCity'", EditText.class);
    target.editTextMailState = Utils.findRequiredViewAsType(source, R.id.et_account_mail_address_state, "field 'editTextMailState'", EditText.class);
    target.editTextMailCode = Utils.findRequiredViewAsType(source, R.id.et_account_mail_address_code, "field 'editTextMailCode'", EditText.class);
    target.editTextMailCountry = Utils.findRequiredViewAsType(source, R.id.et_account_mail_address_country, "field 'editTextMailCountry'", EditText.class);
    target.editTextShippingStreet = Utils.findRequiredViewAsType(source, R.id.et_account_shipping_street, "field 'editTextShippingStreet'", EditText.class);
    target.editTextShippingCity = Utils.findRequiredViewAsType(source, R.id.et_account_shipping_address_city, "field 'editTextShippingCity'", EditText.class);
    target.editTextShippingState = Utils.findRequiredViewAsType(source, R.id.et_account_shipping_address_state, "field 'editTextShippingState'", EditText.class);
    target.editTextShippingCode = Utils.findRequiredViewAsType(source, R.id.et_account_shipping_address_code, "field 'editTextShippingCode'", EditText.class);
    target.editTextShippingCountry = Utils.findRequiredViewAsType(source, R.id.et_account_shipping_address_country, "field 'editTextShippingCountry'", EditText.class);
    target.textViewSmartView = Utils.findRequiredViewAsType(source, R.id.tv_smart_view, "field 'textViewSmartView'", TextView.class);
    target.linearLayoutContactOwner = Utils.findRequiredViewAsType(source, R.id.linear_layout_contact_owner, "field 'linearLayoutContactOwner'", LinearLayout.class);
    target.linearLayoutContactLeadSource = Utils.findRequiredViewAsType(source, R.id.linear_layout_contact_lead_source, "field 'linearLayoutContactLeadSource'", LinearLayout.class);
    target.linearLayoutContactTitle = Utils.findRequiredViewAsType(source, R.id.linear_layout_contact_title, "field 'linearLayoutContactTitle'", LinearLayout.class);
    target.linearLayoutContactDepartment = Utils.findRequiredViewAsType(source, R.id.linear_layout_contact_department, "field 'linearLayoutContactDepartment'", LinearLayout.class);
    target.linearLayoutContactHomePhone = Utils.findRequiredViewAsType(source, R.id.linear_layout_contact_home_phone, "field 'linearLayoutContactHomePhone'", LinearLayout.class);
    target.linearLayoutContactotherPhone = Utils.findRequiredViewAsType(source, R.id.linear_layout_contact_other_phone, "field 'linearLayoutContactotherPhone'", LinearLayout.class);
    target.linearLayoutContactFax = Utils.findRequiredViewAsType(source, R.id.linear_layout_contact_fax, "field 'linearLayoutContactFax'", LinearLayout.class);
    target.aSwitchContactEmailOpt = Utils.findRequiredViewAsType(source, R.id.switch_contact_email_opt, "field 'aSwitchContactEmailOpt'", Switch.class);
    target.linearLayoutContactMobile = Utils.findRequiredViewAsType(source, R.id.linear_layout_contact_mobile, "field 'linearLayoutContactMobile'", LinearLayout.class);
    target.linearLayoutContactDOB = Utils.findRequiredViewAsType(source, R.id.linear_layout_date_of_birth, "field 'linearLayoutContactDOB'", LinearLayout.class);
    target.linearLayoutContactAssistant = Utils.findRequiredViewAsType(source, R.id.linear_layout_assistant, "field 'linearLayoutContactAssistant'", LinearLayout.class);
    target.linearLayoutContactAssistantPhone = Utils.findRequiredViewAsType(source, R.id.linear_layout_contact_assistant_phone, "field 'linearLayoutContactAssistantPhone'", LinearLayout.class);
    target.linearLayoutContactReportTo = Utils.findRequiredViewAsType(source, R.id.linear_layout_contact_report_to, "field 'linearLayoutContactReportTo'", LinearLayout.class);
    target.linearLayoutContactSkypeId = Utils.findRequiredViewAsType(source, R.id.linear_layout_contact_skypeid, "field 'linearLayoutContactSkypeId'", LinearLayout.class);
    target.linearLayoutContactSolution = Utils.findRequiredViewAsType(source, R.id.linear_layout_contact_solution, "field 'linearLayoutContactSolution'", LinearLayout.class);
    target.linearLayoutContactSecondaryMail = Utils.findRequiredViewAsType(source, R.id.linear_layout_contact_secondary_mail, "field 'linearLayoutContactSecondaryMail'", LinearLayout.class);
    target.linearLayoutContactTwitter = Utils.findRequiredViewAsType(source, R.id.linear_layout_contact_twitter, "field 'linearLayoutContactTwitter'", LinearLayout.class);
    target.linearLayoutContactDescription = Utils.findRequiredViewAsType(source, R.id.linear_layout_add_contact_description, "field 'linearLayoutContactDescription'", LinearLayout.class);
    target.linearLayoutContactInformation = Utils.findRequiredViewAsType(source, R.id.linear_layout_address_information, "field 'linearLayoutContactInformation'", LinearLayout.class);
    target.linearLayoutContactAddress = Utils.findRequiredViewAsType(source, R.id.linear_layout_contact_address, "field 'linearLayoutContactAddress'", LinearLayout.class);
    target.linearLayoutMailCity = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_mail_city, "field 'linearLayoutMailCity'", LinearLayout.class);
    target.linearLayoutMailState = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_mail_state, "field 'linearLayoutMailState'", LinearLayout.class);
    target.linearLayoutMailCode = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_mail_code, "field 'linearLayoutMailCode'", LinearLayout.class);
    target.linearLayoutMailCountry = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_mail_country, "field 'linearLayoutMailCountry'", LinearLayout.class);
    target.linearLayoutShippingAddress = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_shipping_address, "field 'linearLayoutShippingAddress'", LinearLayout.class);
    target.linearLayoutShippingCity = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_shipping_city, "field 'linearLayoutShippingCity'", LinearLayout.class);
    target.linearLayoutShippingState = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_shipping_state, "field 'linearLayoutShippingState'", LinearLayout.class);
    target.linearLayoutShippingCode = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_shipping_code, "field 'linearLayoutShippingCode'", LinearLayout.class);
    target.linearLayoutShippingCountry = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_shipping_country, "field 'linearLayoutShippingCountry'", LinearLayout.class);
    target.textViewHeaderDiscription = Utils.findRequiredViewAsType(source, R.id.tv_header_discription, "field 'textViewHeaderDiscription'", TextView.class);
    target.textViewContactLastName = Utils.findRequiredViewAsType(source, R.id.tv_contact_last_name, "field 'textViewContactLastName'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EditContactActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.relativeLayoutContactImage = null;
    target.textViewContactOwner = null;
    target.textViewLeadSource = null;
    target.editTextContactFistName = null;
    target.editTextContactLastName = null;
    target.textViewContactAccountName = null;
    target.editTextContactEmail = null;
    target.editTextContactTitle = null;
    target.editTextContactDeprtment = null;
    target.editTextContactPhone = null;
    target.getEditTextContactHomePhone = null;
    target.editTextContactOtherPhone = null;
    target.editTextContactFax = null;
    target.editTextContactMobile = null;
    target.textViewContactDOB = null;
    target.editTextContactAssistant = null;
    target.editTextContactAssistantPhone = null;
    target.editTextContactReportTo = null;
    target.editTextContactSkypeId = null;
    target.textViewContactSolution = null;
    target.editTextSecondaryMail = null;
    target.editTextContactTwitter = null;
    target.editTextDiscription = null;
    target.editTextMailStreet = null;
    target.editTextMailCity = null;
    target.editTextMailState = null;
    target.editTextMailCode = null;
    target.editTextMailCountry = null;
    target.editTextShippingStreet = null;
    target.editTextShippingCity = null;
    target.editTextShippingState = null;
    target.editTextShippingCode = null;
    target.editTextShippingCountry = null;
    target.textViewSmartView = null;
    target.linearLayoutContactOwner = null;
    target.linearLayoutContactLeadSource = null;
    target.linearLayoutContactTitle = null;
    target.linearLayoutContactDepartment = null;
    target.linearLayoutContactHomePhone = null;
    target.linearLayoutContactotherPhone = null;
    target.linearLayoutContactFax = null;
    target.aSwitchContactEmailOpt = null;
    target.linearLayoutContactMobile = null;
    target.linearLayoutContactDOB = null;
    target.linearLayoutContactAssistant = null;
    target.linearLayoutContactAssistantPhone = null;
    target.linearLayoutContactReportTo = null;
    target.linearLayoutContactSkypeId = null;
    target.linearLayoutContactSolution = null;
    target.linearLayoutContactSecondaryMail = null;
    target.linearLayoutContactTwitter = null;
    target.linearLayoutContactDescription = null;
    target.linearLayoutContactInformation = null;
    target.linearLayoutContactAddress = null;
    target.linearLayoutMailCity = null;
    target.linearLayoutMailState = null;
    target.linearLayoutMailCode = null;
    target.linearLayoutMailCountry = null;
    target.linearLayoutShippingAddress = null;
    target.linearLayoutShippingCity = null;
    target.linearLayoutShippingState = null;
    target.linearLayoutShippingCode = null;
    target.linearLayoutShippingCountry = null;
    target.textViewHeaderDiscription = null;
    target.textViewContactLastName = null;
  }
}
