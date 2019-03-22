// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CustomListActivity_ViewBinding implements Unbinder {
  private CustomListActivity target;

  @UiThread
  public CustomListActivity_ViewBinding(CustomListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CustomListActivity_ViewBinding(CustomListActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.listView = Utils.findRequiredViewAsType(source, R.id.list_view, "field 'listView'", ListView.class);
    target.textViewMsg = Utils.findRequiredViewAsType(source, R.id.tv_msg, "field 'textViewMsg'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CustomListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.listView = null;
    target.textViewMsg = null;
  }
}
