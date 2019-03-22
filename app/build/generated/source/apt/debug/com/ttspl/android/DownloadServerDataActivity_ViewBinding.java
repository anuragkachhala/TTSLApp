// Generated code from Butter Knife. Do not modify!
package com.ttspl.android;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.software.ttsl.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DownloadServerDataActivity_ViewBinding implements Unbinder {
  private DownloadServerDataActivity target;

  @UiThread
  public DownloadServerDataActivity_ViewBinding(DownloadServerDataActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DownloadServerDataActivity_ViewBinding(DownloadServerDataActivity target, View source) {
    this.target = target;

    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progress_bar, "field 'progressBar'", ProgressBar.class);
    target.textViewMsgDownloads = Utils.findRequiredViewAsType(source, R.id.tv_msg_download, "field 'textViewMsgDownloads'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DownloadServerDataActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.progressBar = null;
    target.textViewMsgDownloads = null;
  }
}
