// Generated code from Butter Knife. Do not modify!
package com.software.ttsl.Fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.software.ttsl.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ContactRelatedFragment_ViewBinding implements Unbinder {
  private ContactRelatedFragment target;

  @UiThread
  public ContactRelatedFragment_ViewBinding(ContactRelatedFragment target, View source) {
    this.target = target;

    target.textViewContactName = Utils.findRequiredViewAsType(source, R.id.tv_contact_name, "field 'textViewContactName'", TextView.class);
    target.textViewContactEmailId = Utils.findRequiredViewAsType(source, R.id.tv_contact_email_id, "field 'textViewContactEmailId'", TextView.class);
    target.textViewContactMobileNo = Utils.findRequiredViewAsType(source, R.id.tv_contact_mobile_no, "field 'textViewContactMobileNo'", TextView.class);
    target.textViewAccountName = Utils.findRequiredViewAsType(source, R.id.tv_account_name, "field 'textViewAccountName'", TextView.class);
    target.linearLayoutContactAccount = Utils.findRequiredViewAsType(source, R.id.layout_contact_account, "field 'linearLayoutContactAccount'", LinearLayout.class);
    target.recyclerViewDeals = Utils.findRequiredViewAsType(source, R.id.recycler_view_deals, "field 'recyclerViewDeals'", RecyclerView.class);
    target.linearLayoutCallContainer = Utils.findRequiredViewAsType(source, R.id.call_container, "field 'linearLayoutCallContainer'", LinearLayout.class);
    target.linearLayoutTaskContainer = Utils.findRequiredViewAsType(source, R.id.task_container, "field 'linearLayoutTaskContainer'", LinearLayout.class);
    target.linearLayoutEventContainer = Utils.findRequiredViewAsType(source, R.id.event_container, "field 'linearLayoutEventContainer'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ContactRelatedFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.textViewContactName = null;
    target.textViewContactEmailId = null;
    target.textViewContactMobileNo = null;
    target.textViewAccountName = null;
    target.linearLayoutContactAccount = null;
    target.recyclerViewDeals = null;
    target.linearLayoutCallContainer = null;
    target.linearLayoutTaskContainer = null;
    target.linearLayoutEventContainer = null;
  }
}
