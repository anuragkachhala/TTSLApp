// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(MainActivity target, View source) {
    this.target = target;

    target.mLogout = Utils.findRequiredViewAsType(source, R.id.layout_logout, "field 'mLogout'", LinearLayout.class);
    target.mTrackCargo = Utils.findRequiredViewAsType(source, R.id.layout_track_cargo, "field 'mTrackCargo'", LinearLayout.class);
    target.mPendingInvoice = Utils.findRequiredViewAsType(source, R.id.layout_pending_invoice, "field 'mPendingInvoice'", LinearLayout.class);
    target.mSailingSchedule = Utils.findRequiredViewAsType(source, R.id.layout_sailing_schedule, "field 'mSailingSchedule'", LinearLayout.class);
    target.mGetQuotation = Utils.findRequiredViewAsType(source, R.id.layout_getquotation, "field 'mGetQuotation'", LinearLayout.class);
    target.mBillList = Utils.findRequiredViewAsType(source, R.id.layout_billList, "field 'mBillList'", LinearLayout.class);
    target.mProfile = Utils.findRequiredViewAsType(source, R.id.layout_profile, "field 'mProfile'", LinearLayout.class);
    target.mContactUs = Utils.findRequiredViewAsType(source, R.id.layout_contact_us, "field 'mContactUs'", LinearLayout.class);
    target.mAboutUs = Utils.findRequiredViewAsType(source, R.id.layout_about_us, "field 'mAboutUs'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mLogout = null;
    target.mTrackCargo = null;
    target.mPendingInvoice = null;
    target.mSailingSchedule = null;
    target.mGetQuotation = null;
    target.mBillList = null;
    target.mProfile = null;
    target.mContactUs = null;
    target.mAboutUs = null;
  }
}
