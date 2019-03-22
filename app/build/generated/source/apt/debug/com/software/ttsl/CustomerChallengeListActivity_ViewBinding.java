// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CustomerChallengeListActivity_ViewBinding implements Unbinder {
  private CustomerChallengeListActivity target;

  @UiThread
  public CustomerChallengeListActivity_ViewBinding(CustomerChallengeListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CustomerChallengeListActivity_ViewBinding(CustomerChallengeListActivity target,
      View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.floatingActionButton = Utils.findRequiredViewAsType(source, R.id.fab, "field 'floatingActionButton'", FloatingActionButton.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CustomerChallengeListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.floatingActionButton = null;
  }
}
