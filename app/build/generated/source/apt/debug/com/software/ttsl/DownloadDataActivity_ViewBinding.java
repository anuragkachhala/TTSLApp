// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.software.ttsl.CustomView.CustomProgressBar;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DownloadDataActivity_ViewBinding implements Unbinder {
  private DownloadDataActivity target;

  @UiThread
  public DownloadDataActivity_ViewBinding(DownloadDataActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DownloadDataActivity_ViewBinding(DownloadDataActivity target, View source) {
    this.target = target;

    target.customProgressBar = Utils.findRequiredViewAsType(source, R.id.progress_bar_2, "field 'customProgressBar'", CustomProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DownloadDataActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.customProgressBar = null;
  }
}
