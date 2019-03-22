// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SyncActivity_ViewBinding implements Unbinder {
  private SyncActivity target;

  @UiThread
  public SyncActivity_ViewBinding(SyncActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SyncActivity_ViewBinding(SyncActivity target, View source) {
    this.target = target;

    target.toolbar_sync = Utils.findRequiredViewAsType(source, R.id.toolbar_sync, "field 'toolbar_sync'", Toolbar.class);
    target.leads = Utils.findRequiredViewAsType(source, R.id.leads, "field 'leads'", TextView.class);
    target.account = Utils.findRequiredViewAsType(source, R.id.account, "field 'account'", TextView.class);
    target.contacts = Utils.findRequiredViewAsType(source, R.id.contacts, "field 'contacts'", TextView.class);
    target.task = Utils.findRequiredViewAsType(source, R.id.task, "field 'task'", TextView.class);
    target.event = Utils.findRequiredViewAsType(source, R.id.event, "field 'event'", TextView.class);
    target.deals = Utils.findRequiredViewAsType(source, R.id.deals, "field 'deals'", TextView.class);
    target.call = Utils.findRequiredViewAsType(source, R.id.call, "field 'call'", TextView.class);
    target.customers = Utils.findRequiredViewAsType(source, R.id.customers, "field 'customers'", TextView.class);
    target.sales = Utils.findRequiredViewAsType(source, R.id.sales, "field 'sales'", TextView.class);
    target.leadsImage = Utils.findRequiredViewAsType(source, R.id.leadsImage, "field 'leadsImage'", ImageView.class);
    target.accountImage = Utils.findRequiredViewAsType(source, R.id.accountImage, "field 'accountImage'", ImageView.class);
    target.contactsImage = Utils.findRequiredViewAsType(source, R.id.contactsImage, "field 'contactsImage'", ImageView.class);
    target.taskImage = Utils.findRequiredViewAsType(source, R.id.taskImage, "field 'taskImage'", ImageView.class);
    target.eventImage = Utils.findRequiredViewAsType(source, R.id.eventImage, "field 'eventImage'", ImageView.class);
    target.dealsImage = Utils.findRequiredViewAsType(source, R.id.dealsImage, "field 'dealsImage'", ImageView.class);
    target.callImage = Utils.findRequiredViewAsType(source, R.id.callImage, "field 'callImage'", ImageView.class);
    target.customerImage = Utils.findRequiredViewAsType(source, R.id.customerImage, "field 'customerImage'", ImageView.class);
    target.salesImage = Utils.findRequiredViewAsType(source, R.id.salesImage, "field 'salesImage'", ImageView.class);
    target.leads_container = Utils.findRequiredViewAsType(source, R.id.leads_container, "field 'leads_container'", LinearLayout.class);
    target.account_container = Utils.findRequiredViewAsType(source, R.id.account_container, "field 'account_container'", LinearLayout.class);
    target.contacts_container = Utils.findRequiredViewAsType(source, R.id.contacts_container, "field 'contacts_container'", LinearLayout.class);
    target.task_container = Utils.findRequiredViewAsType(source, R.id.task_container, "field 'task_container'", LinearLayout.class);
    target.event_container = Utils.findRequiredViewAsType(source, R.id.event_container, "field 'event_container'", LinearLayout.class);
    target.deals_container = Utils.findRequiredViewAsType(source, R.id.deals_container, "field 'deals_container'", LinearLayout.class);
    target.call_container = Utils.findRequiredViewAsType(source, R.id.call_container, "field 'call_container'", LinearLayout.class);
    target.customer_container = Utils.findRequiredViewAsType(source, R.id.customer_container, "field 'customer_container'", LinearLayout.class);
    target.sales_container = Utils.findRequiredViewAsType(source, R.id.sales_container, "field 'sales_container'", LinearLayout.class);
    target.imageViewSyncAll = Utils.findRequiredViewAsType(source, R.id.syncAllImage, "field 'imageViewSyncAll'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SyncActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar_sync = null;
    target.leads = null;
    target.account = null;
    target.contacts = null;
    target.task = null;
    target.event = null;
    target.deals = null;
    target.call = null;
    target.customers = null;
    target.sales = null;
    target.leadsImage = null;
    target.accountImage = null;
    target.contactsImage = null;
    target.taskImage = null;
    target.eventImage = null;
    target.dealsImage = null;
    target.callImage = null;
    target.customerImage = null;
    target.salesImage = null;
    target.leads_container = null;
    target.account_container = null;
    target.contacts_container = null;
    target.task_container = null;
    target.event_container = null;
    target.deals_container = null;
    target.call_container = null;
    target.customer_container = null;
    target.sales_container = null;
    target.imageViewSyncAll = null;
  }
}
