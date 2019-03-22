// Generated code from Butter Knife. Do not modify!
package com.software.ttsl.NewModule;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.software.ttsl.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ContainerDetailActivity_ViewBinding implements Unbinder {
  private ContainerDetailActivity target;

  @UiThread
  public ContainerDetailActivity_ViewBinding(ContainerDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ContainerDetailActivity_ViewBinding(ContainerDetailActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.scrollView = Utils.findRequiredViewAsType(source, R.id.scrollView, "field 'scrollView'", ScrollView.class);
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progress_bar, "field 'progressBar'", ProgressBar.class);
    target.textViewMessage = Utils.findRequiredViewAsType(source, R.id.tv_message, "field 'textViewMessage'", TextView.class);
    target.linearLayoutHeaderContainer = Utils.findRequiredViewAsType(source, R.id.header_container, "field 'linearLayoutHeaderContainer'", LinearLayout.class);
    target.linearLayoutHBLContainerDetails = Utils.findRequiredViewAsType(source, R.id.hbl_container_details, "field 'linearLayoutHBLContainerDetails'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ContainerDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.scrollView = null;
    target.progressBar = null;
    target.textViewMessage = null;
    target.linearLayoutHeaderContainer = null;
    target.linearLayoutHBLContainerDetails = null;
  }
}
