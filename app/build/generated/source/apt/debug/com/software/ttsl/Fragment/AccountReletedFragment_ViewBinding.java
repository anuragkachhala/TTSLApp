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

public class AccountReletedFragment_ViewBinding implements Unbinder {
  private AccountReletedFragment target;

  @UiThread
  public AccountReletedFragment_ViewBinding(AccountReletedFragment target, View source) {
    this.target = target;

    target.textViewAccountName = Utils.findRequiredViewAsType(source, R.id.tv_account_name, "field 'textViewAccountName'", TextView.class);
    target.textViewAccountWebsite = Utils.findRequiredViewAsType(source, R.id.tv_account_website, "field 'textViewAccountWebsite'", TextView.class);
    target.textViewAccountMobileNo = Utils.findRequiredViewAsType(source, R.id.tv_account_mobile_no, "field 'textViewAccountMobileNo'", TextView.class);
    target.textViewAccountOwner = Utils.findRequiredViewAsType(source, R.id.tv_account_owner, "field 'textViewAccountOwner'", TextView.class);
    target.textViewAccountParent = Utils.findRequiredViewAsType(source, R.id.tv_account_parent, "field 'textViewAccountParent'", TextView.class);
    target.linearLayoutParentAccount = Utils.findRequiredViewAsType(source, R.id.layout_parent_account, "field 'linearLayoutParentAccount'", LinearLayout.class);
    target.linearLayoutTaskContainer = Utils.findRequiredViewAsType(source, R.id.task_container, "field 'linearLayoutTaskContainer'", LinearLayout.class);
    target.linearLayoutCallContainer = Utils.findRequiredViewAsType(source, R.id.call_container, "field 'linearLayoutCallContainer'", LinearLayout.class);
    target.linearLayoutEventContainer = Utils.findRequiredViewAsType(source, R.id.event_container, "field 'linearLayoutEventContainer'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AccountReletedFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.textViewAccountName = null;
    target.textViewAccountWebsite = null;
    target.textViewAccountMobileNo = null;
    target.textViewAccountOwner = null;
    target.textViewAccountParent = null;
    target.linearLayoutParentAccount = null;
    target.linearLayoutTaskContainer = null;
    target.linearLayoutCallContainer = null;
    target.linearLayoutEventContainer = null;
  }
}
