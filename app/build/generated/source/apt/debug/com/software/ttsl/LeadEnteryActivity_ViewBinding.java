// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LeadEnteryActivity_ViewBinding implements Unbinder {
  private LeadEnteryActivity target;

  @UiThread
  public LeadEnteryActivity_ViewBinding(LeadEnteryActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LeadEnteryActivity_ViewBinding(LeadEnteryActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LeadEnteryActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
  }
}
