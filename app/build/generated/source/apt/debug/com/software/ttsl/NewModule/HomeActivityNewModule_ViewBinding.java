// Generated code from Butter Knife. Do not modify!
package com.software.ttsl.NewModule;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.software.ttsl.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeActivityNewModule_ViewBinding implements Unbinder {
  private HomeActivityNewModule target;

  @UiThread
  public HomeActivityNewModule_ViewBinding(HomeActivityNewModule target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HomeActivityNewModule_ViewBinding(HomeActivityNewModule target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeActivityNewModule target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
  }
}
