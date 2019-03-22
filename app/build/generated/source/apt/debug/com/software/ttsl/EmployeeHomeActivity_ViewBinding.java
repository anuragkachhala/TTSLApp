// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EmployeeHomeActivity_ViewBinding implements Unbinder {
  private EmployeeHomeActivity target;

  @UiThread
  public EmployeeHomeActivity_ViewBinding(EmployeeHomeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EmployeeHomeActivity_ViewBinding(EmployeeHomeActivity target, View source) {
    this.target = target;

    target.drawer = Utils.findRequiredViewAsType(source, R.id.drawer_layout, "field 'drawer'", DrawerLayout.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.navigationView = Utils.findRequiredViewAsType(source, R.id.nav_view, "field 'navigationView'", NavigationView.class);
    target.linearLayoutTackCargo = Utils.findRequiredViewAsType(source, R.id.layout_track_cargo, "field 'linearLayoutTackCargo'", LinearLayout.class);
    target.linearLayoutSailingSchedule = Utils.findRequiredViewAsType(source, R.id.layout_sailing_schedule, "field 'linearLayoutSailingSchedule'", LinearLayout.class);
    target.linearLayoutGetQuotation = Utils.findRequiredViewAsType(source, R.id.layout_get_quotation, "field 'linearLayoutGetQuotation'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EmployeeHomeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.drawer = null;
    target.toolbar = null;
    target.navigationView = null;
    target.linearLayoutTackCargo = null;
    target.linearLayoutSailingSchedule = null;
    target.linearLayoutGetQuotation = null;
  }
}
