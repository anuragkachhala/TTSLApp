// Generated code from Butter Knife. Do not modify!
package com.software.ttsl.NewModule;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.software.ttsl.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SailScheduleListActivity_ViewBinding implements Unbinder {
  private SailScheduleListActivity target;

  @UiThread
  public SailScheduleListActivity_ViewBinding(SailScheduleListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SailScheduleListActivity_ViewBinding(SailScheduleListActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerview, "field 'recyclerView'", RecyclerView.class);
    target.relativeLayoutLayoutPort = Utils.findRequiredViewAsType(source, R.id.layout_port, "field 'relativeLayoutLayoutPort'", RelativeLayout.class);
    target.textViewScheduleCount = Utils.findRequiredViewAsType(source, R.id.tv_schedule_count, "field 'textViewScheduleCount'", TextView.class);
    target.textViewFromPort = Utils.findRequiredViewAsType(source, R.id.tv_from_port, "field 'textViewFromPort'", TextView.class);
    target.textViewToPort = Utils.findRequiredViewAsType(source, R.id.tv_to_port, "field 'textViewToPort'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SailScheduleListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.recyclerView = null;
    target.relativeLayoutLayoutPort = null;
    target.textViewScheduleCount = null;
    target.textViewFromPort = null;
    target.textViewToPort = null;
  }
}
