// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SplashScreenActivity_ViewBinding implements Unbinder {
  private SplashScreenActivity target;

  @UiThread
  public SplashScreenActivity_ViewBinding(SplashScreenActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SplashScreenActivity_ViewBinding(SplashScreenActivity target, View source) {
    this.target = target;

    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progress_bar, "field 'progressBar'", ProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SplashScreenActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.progressBar = null;
  }
}
