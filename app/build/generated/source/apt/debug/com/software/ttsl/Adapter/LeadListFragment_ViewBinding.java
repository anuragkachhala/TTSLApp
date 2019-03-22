// Generated code from Butter Knife. Do not modify!
package com.software.ttsl.Adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.software.ttsl.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LeadListFragment_ViewBinding implements Unbinder {
  private LeadListFragment target;

  @UiThread
  public LeadListFragment_ViewBinding(LeadListFragment target, View source) {
    this.target = target;

    target.fab = Utils.findRequiredViewAsType(source, R.id.fab, "field 'fab'", FloatingActionButton.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LeadListFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.fab = null;
  }
}
