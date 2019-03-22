// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SelectPortActivity_ViewBinding implements Unbinder {
  private SelectPortActivity target;

  @UiThread
  public SelectPortActivity_ViewBinding(SelectPortActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SelectPortActivity_ViewBinding(SelectPortActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.mSearchPort = Utils.findRequiredViewAsType(source, R.id.et_search_port, "field 'mSearchPort'", EditText.class);
    target.mClearSearchIcon = Utils.findRequiredViewAsType(source, R.id.iv_clear_search, "field 'mClearSearchIcon'", ImageView.class);
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SelectPortActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.mSearchPort = null;
    target.mClearSearchIcon = null;
    target.mRecyclerView = null;
  }
}