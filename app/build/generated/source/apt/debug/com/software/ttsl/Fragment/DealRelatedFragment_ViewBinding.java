// Generated code from Butter Knife. Do not modify!
package com.software.ttsl.Fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.software.ttsl.CustomView.CustomProgressBar;
import com.software.ttsl.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DealRelatedFragment_ViewBinding implements Unbinder {
  private DealRelatedFragment target;

  @UiThread
  public DealRelatedFragment_ViewBinding(DealRelatedFragment target, View source) {
    this.target = target;

    target.textViewDealName = Utils.findRequiredViewAsType(source, R.id.tv_deal_name, "field 'textViewDealName'", TextView.class);
    target.textViewDealOwner = Utils.findRequiredViewAsType(source, R.id.tv_deal_owner, "field 'textViewDealOwner'", TextView.class);
    target.textViewDealClosingDate = Utils.findRequiredViewAsType(source, R.id.tv_deal_closing_date, "field 'textViewDealClosingDate'", TextView.class);
    target.textViewAccountName = Utils.findRequiredViewAsType(source, R.id.tv_account_name, "field 'textViewAccountName'", TextView.class);
    target.textViewStage = Utils.findRequiredViewAsType(source, R.id.tv_stage_name, "field 'textViewStage'", TextView.class);
    target.customProgressBar = Utils.findRequiredViewAsType(source, R.id.progress_bar, "field 'customProgressBar'", CustomProgressBar.class);
    target.linearLayoutContact = Utils.findRequiredViewAsType(source, R.id.layout_contact, "field 'linearLayoutContact'", LinearLayout.class);
    target.textViewContactName = Utils.findRequiredViewAsType(source, R.id.tv_contact_name, "field 'textViewContactName'", TextView.class);
    target.linearLayoutAccount = Utils.findRequiredViewAsType(source, R.id.layout_account, "field 'linearLayoutAccount'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DealRelatedFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.textViewDealName = null;
    target.textViewDealOwner = null;
    target.textViewDealClosingDate = null;
    target.textViewAccountName = null;
    target.textViewStage = null;
    target.customProgressBar = null;
    target.linearLayoutContact = null;
    target.textViewContactName = null;
    target.linearLayoutAccount = null;
  }
}
