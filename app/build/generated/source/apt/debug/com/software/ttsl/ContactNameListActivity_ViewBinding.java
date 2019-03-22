// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ContactNameListActivity_ViewBinding implements Unbinder {
  private ContactNameListActivity target;

  @UiThread
  public ContactNameListActivity_ViewBinding(ContactNameListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ContactNameListActivity_ViewBinding(ContactNameListActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    target.textViewMsg = Utils.findRequiredViewAsType(source, R.id.tv_msg, "field 'textViewMsg'", TextView.class);
    target.spinnerModule = Utils.findRequiredViewAsType(source, R.id.sp_module, "field 'spinnerModule'", Spinner.class);
    target.fabAddContact = Utils.findRequiredViewAsType(source, R.id.fab, "field 'fabAddContact'", FloatingActionButton.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ContactNameListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.recyclerView = null;
    target.textViewMsg = null;
    target.spinnerModule = null;
    target.fabAddContact = null;
  }
}
