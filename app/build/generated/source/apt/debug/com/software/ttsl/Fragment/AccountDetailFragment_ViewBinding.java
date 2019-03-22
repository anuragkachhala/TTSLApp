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

public class AccountDetailFragment_ViewBinding implements Unbinder {
  private AccountDetailFragment target;

  @UiThread
  public AccountDetailFragment_ViewBinding(AccountDetailFragment target, View source) {
    this.target = target;

    target.linearLayoutAccountOwner = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_owner, "field 'linearLayoutAccountOwner'", LinearLayout.class);
    target.linearLayoutAccountRating = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_rating, "field 'linearLayoutAccountRating'", LinearLayout.class);
    target.linearLayoutAccountName = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_name, "field 'linearLayoutAccountName'", LinearLayout.class);
    target.linearLayoutAccountPhone = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_phone, "field 'linearLayoutAccountPhone'", LinearLayout.class);
    target.linearLayoutAccountSite = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_site, "field 'linearLayoutAccountSite'", LinearLayout.class);
    target.linearLayoutAccountFax = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_fax, "field 'linearLayoutAccountFax'", LinearLayout.class);
    target.linearLayoutAccountParent = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_parent, "field 'linearLayoutAccountParent'", LinearLayout.class);
    target.linearLayoutAccountWebsite = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_website, "field 'linearLayoutAccountWebsite'", LinearLayout.class);
    target.linearLayoutAccountNumber = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_number, "field 'linearLayoutAccountNumber'", LinearLayout.class);
    target.linearLayoutAccountTickerSymbol = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_ticker_symbol, "field 'linearLayoutAccountTickerSymbol'", LinearLayout.class);
    target.linearLayoutAccountType = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_type, "field 'linearLayoutAccountType'", LinearLayout.class);
    target.linearLayoutAccountOwnerShip = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_ownership, "field 'linearLayoutAccountOwnerShip'", LinearLayout.class);
    target.linearLayoutAccountIndustry = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_industry, "field 'linearLayoutAccountIndustry'", LinearLayout.class);
    target.linearLayoutAccountEmployee = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_employeee, "field 'linearLayoutAccountEmployee'", LinearLayout.class);
    target.linearLayoutAccountRevenue = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_revenue, "field 'linearLayoutAccountRevenue'", LinearLayout.class);
    target.linearLayoutAccountSicCode = Utils.findRequiredViewAsType(source, R.id.linear_layout_account_sic_code, "field 'linearLayoutAccountSicCode'", LinearLayout.class);
    target.linearLayoutAddressInfo = Utils.findRequiredViewAsType(source, R.id.layout_address_info, "field 'linearLayoutAddressInfo'", TextView.class);
    target.linearLayoutBillingStreet = Utils.findRequiredViewAsType(source, R.id.layout_account_bill_address_street, "field 'linearLayoutBillingStreet'", LinearLayout.class);
    target.linearLayoutBillingAddressCity = Utils.findRequiredViewAsType(source, R.id.layout_account_bill_address_city, "field 'linearLayoutBillingAddressCity'", LinearLayout.class);
    target.linearLayoutBillingAddressState = Utils.findRequiredViewAsType(source, R.id.layout_account_bill_address_state, "field 'linearLayoutBillingAddressState'", LinearLayout.class);
    target.linearLayoutBillingAddressCode = Utils.findRequiredViewAsType(source, R.id.layout_account_bill_address_code, "field 'linearLayoutBillingAddressCode'", LinearLayout.class);
    target.linearLayoutBillAddressCountry = Utils.findRequiredViewAsType(source, R.id.layout_account_bill_address_country, "field 'linearLayoutBillAddressCountry'", LinearLayout.class);
    target.linearLayoutShippingAddressStreet = Utils.findRequiredViewAsType(source, R.id.layout_account_shipping_address_street, "field 'linearLayoutShippingAddressStreet'", LinearLayout.class);
    target.linearLayoutShippingAddressCity = Utils.findRequiredViewAsType(source, R.id.layout_account_shipping_address_city, "field 'linearLayoutShippingAddressCity'", LinearLayout.class);
    target.linearLayoutShippingAddressState = Utils.findRequiredViewAsType(source, R.id.layout_account_shipping_address_state, "field 'linearLayoutShippingAddressState'", LinearLayout.class);
    target.linearLayoutShippingAddressCode = Utils.findRequiredViewAsType(source, R.id.layout_account_shipping_address_code, "field 'linearLayoutShippingAddressCode'", LinearLayout.class);
    target.linearLayoutShippingAddressCountry = Utils.findRequiredViewAsType(source, R.id.layout_account_shipping_address_country, "field 'linearLayoutShippingAddressCountry'", LinearLayout.class);
    target.LayoutAccountDescription = Utils.findRequiredViewAsType(source, R.id.layout_tv_account_description, "field 'LayoutAccountDescription'", LinearLayout.class);
    target.linearLayoutAccountDescription = Utils.findRequiredViewAsType(source, R.id.layout_account_description, "field 'linearLayoutAccountDescription'", LinearLayout.class);
    target.textViewAccountOwner = Utils.findRequiredViewAsType(source, R.id.tv_account_owner, "field 'textViewAccountOwner'", TextView.class);
    target.textViewAccountRating = Utils.findRequiredViewAsType(source, R.id.tv_account_rating, "field 'textViewAccountRating'", TextView.class);
    target.textViewAccountName = Utils.findRequiredViewAsType(source, R.id.tv_account_name, "field 'textViewAccountName'", TextView.class);
    target.textViewAccountPhone = Utils.findRequiredViewAsType(source, R.id.tv_account_phone, "field 'textViewAccountPhone'", TextView.class);
    target.textViewAccountSite = Utils.findRequiredViewAsType(source, R.id.tv_account_site, "field 'textViewAccountSite'", TextView.class);
    target.textViewAccountFax = Utils.findRequiredViewAsType(source, R.id.tv_account_fax, "field 'textViewAccountFax'", TextView.class);
    target.textViewAccountParent = Utils.findRequiredViewAsType(source, R.id.tv_account_parent, "field 'textViewAccountParent'", TextView.class);
    target.textViewAccountWebsite = Utils.findRequiredViewAsType(source, R.id.tv_account_website, "field 'textViewAccountWebsite'", TextView.class);
    target.textViewAccountNumber = Utils.findRequiredViewAsType(source, R.id.tv_account_number, "field 'textViewAccountNumber'", TextView.class);
    target.textViewAccountTickerSymbol = Utils.findRequiredViewAsType(source, R.id.tv_account_ticker_symbol, "field 'textViewAccountTickerSymbol'", TextView.class);
    target.textViewAccountType = Utils.findRequiredViewAsType(source, R.id.tv_account_type, "field 'textViewAccountType'", TextView.class);
    target.textViewAccountOwnerShip = Utils.findRequiredViewAsType(source, R.id.tv_account_ownership, "field 'textViewAccountOwnerShip'", TextView.class);
    target.textViewAccountIndustry = Utils.findRequiredViewAsType(source, R.id.tv_account_industry, "field 'textViewAccountIndustry'", TextView.class);
    target.textViewAccountEmployees = Utils.findRequiredViewAsType(source, R.id.tv_account_employees, "field 'textViewAccountEmployees'", TextView.class);
    target.textViewAccountRevenue = Utils.findRequiredViewAsType(source, R.id.tv_account_revenue, "field 'textViewAccountRevenue'", TextView.class);
    target.textViewAccountSicCode = Utils.findRequiredViewAsType(source, R.id.tv_account_sic_code, "field 'textViewAccountSicCode'", TextView.class);
    target.textViewAccountCreatedBy = Utils.findRequiredViewAsType(source, R.id.tv_account_created_by, "field 'textViewAccountCreatedBy'", TextView.class);
    target.textViewAccountModifyBy = Utils.findRequiredViewAsType(source, R.id.tv_account_modify_by, "field 'textViewAccountModifyBy'", TextView.class);
    target.textViewAccountCreatedTime = Utils.findRequiredViewAsType(source, R.id.tv_account_created_time, "field 'textViewAccountCreatedTime'", TextView.class);
    target.textViewAccountModfifyTime = Utils.findRequiredViewAsType(source, R.id.tv_account_modify_time, "field 'textViewAccountModfifyTime'", TextView.class);
    target.textViewAccountBillStreet = Utils.findRequiredViewAsType(source, R.id.tv_account_bill_street, "field 'textViewAccountBillStreet'", TextView.class);
    target.textViewAccountBillCity = Utils.findRequiredViewAsType(source, R.id.tv_account_bill_address_city, "field 'textViewAccountBillCity'", TextView.class);
    target.textViewAccountBillState = Utils.findRequiredViewAsType(source, R.id.tv_account_bill_address_state, "field 'textViewAccountBillState'", TextView.class);
    target.textViewAccountBillCode = Utils.findRequiredViewAsType(source, R.id.tv_account_bill_address_code, "field 'textViewAccountBillCode'", TextView.class);
    target.textViewAccountBillCountry = Utils.findRequiredViewAsType(source, R.id.tv_account_bill_address_country, "field 'textViewAccountBillCountry'", TextView.class);
    target.textViewAccountShippingSteet = Utils.findRequiredViewAsType(source, R.id.tv_account_shipping_street, "field 'textViewAccountShippingSteet'", TextView.class);
    target.textViewAccountShippingCity = Utils.findRequiredViewAsType(source, R.id.tv_account_shipping_address_city, "field 'textViewAccountShippingCity'", TextView.class);
    target.textViewAccountShippingState = Utils.findRequiredViewAsType(source, R.id.tv_account_shipping_address_state, "field 'textViewAccountShippingState'", TextView.class);
    target.textViewAccountShippingCode = Utils.findRequiredViewAsType(source, R.id.tv_account_shipping_address_code, "field 'textViewAccountShippingCode'", TextView.class);
    target.textViewAccountShippingCountry = Utils.findRequiredViewAsType(source, R.id.tv_account_shipping_address_country, "field 'textViewAccountShippingCountry'", TextView.class);
    target.textViewDescription = Utils.findRequiredViewAsType(source, R.id.tv_account_description, "field 'textViewDescription'", TextView.class);
    target.textViewSmartView = Utils.findRequiredViewAsType(source, R.id.tv_smart_view, "field 'textViewSmartView'", TextView.class);
    target.textViewAccountLabelName = Utils.findRequiredViewAsType(source, R.id.tv_account_label_name, "field 'textViewAccountLabelName'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AccountDetailFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.linearLayoutAccountOwner = null;
    target.linearLayoutAccountRating = null;
    target.linearLayoutAccountName = null;
    target.linearLayoutAccountPhone = null;
    target.linearLayoutAccountSite = null;
    target.linearLayoutAccountFax = null;
    target.linearLayoutAccountParent = null;
    target.linearLayoutAccountWebsite = null;
    target.linearLayoutAccountNumber = null;
    target.linearLayoutAccountTickerSymbol = null;
    target.linearLayoutAccountType = null;
    target.linearLayoutAccountOwnerShip = null;
    target.linearLayoutAccountIndustry = null;
    target.linearLayoutAccountEmployee = null;
    target.linearLayoutAccountRevenue = null;
    target.linearLayoutAccountSicCode = null;
    target.linearLayoutAddressInfo = null;
    target.linearLayoutBillingStreet = null;
    target.linearLayoutBillingAddressCity = null;
    target.linearLayoutBillingAddressState = null;
    target.linearLayoutBillingAddressCode = null;
    target.linearLayoutBillAddressCountry = null;
    target.linearLayoutShippingAddressStreet = null;
    target.linearLayoutShippingAddressCity = null;
    target.linearLayoutShippingAddressState = null;
    target.linearLayoutShippingAddressCode = null;
    target.linearLayoutShippingAddressCountry = null;
    target.LayoutAccountDescription = null;
    target.linearLayoutAccountDescription = null;
    target.textViewAccountOwner = null;
    target.textViewAccountRating = null;
    target.textViewAccountName = null;
    target.textViewAccountPhone = null;
    target.textViewAccountSite = null;
    target.textViewAccountFax = null;
    target.textViewAccountParent = null;
    target.textViewAccountWebsite = null;
    target.textViewAccountNumber = null;
    target.textViewAccountTickerSymbol = null;
    target.textViewAccountType = null;
    target.textViewAccountOwnerShip = null;
    target.textViewAccountIndustry = null;
    target.textViewAccountEmployees = null;
    target.textViewAccountRevenue = null;
    target.textViewAccountSicCode = null;
    target.textViewAccountCreatedBy = null;
    target.textViewAccountModifyBy = null;
    target.textViewAccountCreatedTime = null;
    target.textViewAccountModfifyTime = null;
    target.textViewAccountBillStreet = null;
    target.textViewAccountBillCity = null;
    target.textViewAccountBillState = null;
    target.textViewAccountBillCode = null;
    target.textViewAccountBillCountry = null;
    target.textViewAccountShippingSteet = null;
    target.textViewAccountShippingCity = null;
    target.textViewAccountShippingState = null;
    target.textViewAccountShippingCode = null;
    target.textViewAccountShippingCountry = null;
    target.textViewDescription = null;
    target.textViewSmartView = null;
    target.textViewAccountLabelName = null;
  }
}
