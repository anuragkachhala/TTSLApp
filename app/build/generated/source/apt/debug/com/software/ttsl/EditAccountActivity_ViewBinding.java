// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EditAccountActivity_ViewBinding implements Unbinder {
  private EditAccountActivity target;

  @UiThread
  public EditAccountActivity_ViewBinding(EditAccountActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EditAccountActivity_ViewBinding(EditAccountActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.editTextAccountOwner = Utils.findRequiredViewAsType(source, R.id.et_account_owner, "field 'editTextAccountOwner'", EditText.class);
    target.editTextAccountRating = Utils.findRequiredViewAsType(source, R.id.et_account_rating, "field 'editTextAccountRating'", EditText.class);
    target.editTextAccountName = Utils.findRequiredViewAsType(source, R.id.et_account_name, "field 'editTextAccountName'", EditText.class);
    target.editTextAccountPhone = Utils.findRequiredViewAsType(source, R.id.et_account_phone, "field 'editTextAccountPhone'", EditText.class);
    target.editTextAccountSite = Utils.findRequiredViewAsType(source, R.id.et_account_site, "field 'editTextAccountSite'", EditText.class);
    target.editTextAccountFax = Utils.findRequiredViewAsType(source, R.id.et_account_fax, "field 'editTextAccountFax'", EditText.class);
    target.editTextAccountWebSite = Utils.findRequiredViewAsType(source, R.id.et_account_website, "field 'editTextAccountWebSite'", EditText.class);
    target.editTextAccountNumber = Utils.findRequiredViewAsType(source, R.id.et_account_number, "field 'editTextAccountNumber'", EditText.class);
    target.editTextAccountTickerSymbol = Utils.findRequiredViewAsType(source, R.id.et_account_ticker_symbol, "field 'editTextAccountTickerSymbol'", EditText.class);
    target.editTextAccountEmployee = Utils.findRequiredViewAsType(source, R.id.et_account_employee, "field 'editTextAccountEmployee'", EditText.class);
    target.editTextAccountRevenue = Utils.findRequiredViewAsType(source, R.id.et_account_revenue, "field 'editTextAccountRevenue'", EditText.class);
    target.editTextAccountParent = Utils.findRequiredViewAsType(source, R.id.et_account_parent, "field 'editTextAccountParent'", EditText.class);
    target.editTextAccountType = Utils.findRequiredViewAsType(source, R.id.et_account_type, "field 'editTextAccountType'", EditText.class);
    target.editTextAccountOwnerShip = Utils.findRequiredViewAsType(source, R.id.et_account_ownership, "field 'editTextAccountOwnerShip'", EditText.class);
    target.editTextAccountIndustry = Utils.findRequiredViewAsType(source, R.id.et_account_industry, "field 'editTextAccountIndustry'", EditText.class);
    target.editTextAccountSicCode = Utils.findRequiredViewAsType(source, R.id.et_account_sic_code, "field 'editTextAccountSicCode'", EditText.class);
    target.editTextBillAddressStreet = Utils.findRequiredViewAsType(source, R.id.et_account_bill_street, "field 'editTextBillAddressStreet'", EditText.class);
    target.editTextBillAddressCode = Utils.findRequiredViewAsType(source, R.id.et_account_bill_address_code, "field 'editTextBillAddressCode'", EditText.class);
    target.editTextBillAddressCountry = Utils.findRequiredViewAsType(source, R.id.et_account_bill_address_country, "field 'editTextBillAddressCountry'", EditText.class);
    target.editTextBillAddressState = Utils.findRequiredViewAsType(source, R.id.et_account_bill_address_state, "field 'editTextBillAddressState'", EditText.class);
    target.editTextBillAddressCity = Utils.findRequiredViewAsType(source, R.id.et_account_bill_address_city, "field 'editTextBillAddressCity'", EditText.class);
    target.editTextAccountShippingStreet = Utils.findRequiredViewAsType(source, R.id.et_account_shipping_street, "field 'editTextAccountShippingStreet'", EditText.class);
    target.editTextAccountShippingCode = Utils.findRequiredViewAsType(source, R.id.et_account_shipping_address_code, "field 'editTextAccountShippingCode'", EditText.class);
    target.editTextAccountShippingState = Utils.findRequiredViewAsType(source, R.id.et_account_shipping_address_state, "field 'editTextAccountShippingState'", EditText.class);
    target.editTextAccountShippingCity = Utils.findRequiredViewAsType(source, R.id.et_account_shipping_address_city, "field 'editTextAccountShippingCity'", EditText.class);
    target.editTextAccountShippingCountry = Utils.findRequiredViewAsType(source, R.id.et_account_shipping_address_country, "field 'editTextAccountShippingCountry'", EditText.class);
    target.editTextAccountDescription = Utils.findRequiredViewAsType(source, R.id.et_account_description, "field 'editTextAccountDescription'", EditText.class);
    target.linearLayoutAccountOwner = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_owner, "field 'linearLayoutAccountOwner'", LinearLayout.class);
    target.linearLayoutAccountRating = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_rating, "field 'linearLayoutAccountRating'", LinearLayout.class);
    target.linearLayoutAccountSite = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_site, "field 'linearLayoutAccountSite'", LinearLayout.class);
    target.linearLayoutAccountFax = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_fax, "field 'linearLayoutAccountFax'", LinearLayout.class);
    target.linearLayoutAccountParent = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_parent, "field 'linearLayoutAccountParent'", LinearLayout.class);
    target.linearLayoutAccountNumber = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_number, "field 'linearLayoutAccountNumber'", LinearLayout.class);
    target.linearLayoutAccountTickerSymbol = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_ticker_symbol, "field 'linearLayoutAccountTickerSymbol'", LinearLayout.class);
    target.linearLayoutAccountType = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_type, "field 'linearLayoutAccountType'", LinearLayout.class);
    target.linearLayoutAccountOwnerShip = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_ownership, "field 'linearLayoutAccountOwnerShip'", LinearLayout.class);
    target.linearLayoutAccountIndustry = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_industry, "field 'linearLayoutAccountIndustry'", LinearLayout.class);
    target.linearLayoutAccountEmployee = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_employeee, "field 'linearLayoutAccountEmployee'", LinearLayout.class);
    target.linearLayoutAccountRevenue = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_revenue, "field 'linearLayoutAccountRevenue'", LinearLayout.class);
    target.linearLayoutAccountSicCode = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_sic_code, "field 'linearLayoutAccountSicCode'", LinearLayout.class);
    target.linearLayoutAccountAddressInformation = Utils.findRequiredViewAsType(source, R.id.linear_layout_address_information, "field 'linearLayoutAccountAddressInformation'", LinearLayout.class);
    target.linearLayoutAccountDiscriptionInfo = Utils.findRequiredViewAsType(source, R.id.linear_layout_description_information, "field 'linearLayoutAccountDiscriptionInfo'", LinearLayout.class);
    target.textViewSmartView = Utils.findRequiredViewAsType(source, R.id.tv_smart_view, "field 'textViewSmartView'", TextView.class);
    target.textViewAccountName = Utils.findRequiredViewAsType(source, R.id.tv_account_name, "field 'textViewAccountName'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EditAccountActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.editTextAccountOwner = null;
    target.editTextAccountRating = null;
    target.editTextAccountName = null;
    target.editTextAccountPhone = null;
    target.editTextAccountSite = null;
    target.editTextAccountFax = null;
    target.editTextAccountWebSite = null;
    target.editTextAccountNumber = null;
    target.editTextAccountTickerSymbol = null;
    target.editTextAccountEmployee = null;
    target.editTextAccountRevenue = null;
    target.editTextAccountParent = null;
    target.editTextAccountType = null;
    target.editTextAccountOwnerShip = null;
    target.editTextAccountIndustry = null;
    target.editTextAccountSicCode = null;
    target.editTextBillAddressStreet = null;
    target.editTextBillAddressCode = null;
    target.editTextBillAddressCountry = null;
    target.editTextBillAddressState = null;
    target.editTextBillAddressCity = null;
    target.editTextAccountShippingStreet = null;
    target.editTextAccountShippingCode = null;
    target.editTextAccountShippingState = null;
    target.editTextAccountShippingCity = null;
    target.editTextAccountShippingCountry = null;
    target.editTextAccountDescription = null;
    target.linearLayoutAccountOwner = null;
    target.linearLayoutAccountRating = null;
    target.linearLayoutAccountSite = null;
    target.linearLayoutAccountFax = null;
    target.linearLayoutAccountParent = null;
    target.linearLayoutAccountNumber = null;
    target.linearLayoutAccountTickerSymbol = null;
    target.linearLayoutAccountType = null;
    target.linearLayoutAccountOwnerShip = null;
    target.linearLayoutAccountIndustry = null;
    target.linearLayoutAccountEmployee = null;
    target.linearLayoutAccountRevenue = null;
    target.linearLayoutAccountSicCode = null;
    target.linearLayoutAccountAddressInformation = null;
    target.linearLayoutAccountDiscriptionInfo = null;
    target.textViewSmartView = null;
    target.textViewAccountName = null;
  }
}
