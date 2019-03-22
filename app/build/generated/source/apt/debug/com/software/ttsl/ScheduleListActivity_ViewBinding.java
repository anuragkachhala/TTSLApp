// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ScheduleListActivity_ViewBinding implements Unbinder {
  private ScheduleListActivity target;

  @UiThread
  public ScheduleListActivity_ViewBinding(ScheduleListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ScheduleListActivity_ViewBinding(ScheduleListActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerview, "field 'recyclerView'", RecyclerView.class);
    target.textViewScheduleCount = Utils.findRequiredViewAsType(source, R.id.tv_schedule_count, "field 'textViewScheduleCount'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ScheduleListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.recyclerView = null;
    target.textViewScheduleCount = null;
  }
}
