// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LeadSettingActivity_ViewBinding implements Unbinder {
  private LeadSettingActivity target;

  @UiThread
  public LeadSettingActivity_ViewBinding(LeadSettingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LeadSettingActivity_ViewBinding(LeadSettingActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.linearLayoutGroupBy = Utils.findRequiredViewAsType(source, R.id.layout_group_by, "field 'linearLayoutGroupBy'", LinearLayout.class);
    target.textViewGroupBy = Utils.findRequiredViewAsType(source, R.id.tv_group_by, "field 'textViewGroupBy'", TextView.class);
    target.linearLayoutPrimarySort = Utils.findRequiredViewAsType(source, R.id.layout_primary_sort, "field 'linearLayoutPrimarySort'", LinearLayout.class);
    target.textViewPrimarySort = Utils.findRequiredViewAsType(source, R.id.tv_primary_sort, "field 'textViewPrimarySort'", TextView.class);
    target.linearLayoutSecondarySort = Utils.findRequiredViewAsType(source, R.id.layout_secondary_sort, "field 'linearLayoutSecondarySort'", LinearLayout.class);
    target.textViewSecondarySort = Utils.findRequiredViewAsType(source, R.id.tv_secondary_sort, "field 'textViewSecondarySort'", TextView.class);
    target.linearLayoutSearchBy = Utils.findRequiredViewAsType(source, R.id.layout_search_by, "field 'linearLayoutSearchBy'", LinearLayout.class);
    target.textViewSearchBy = Utils.findRequiredViewAsType(source, R.id.tv_search_by, "field 'textViewSearchBy'", TextView.class);
    target.linearLayoutDisplayField = Utils.findRequiredViewAsType(source, R.id.layout_display_field, "field 'linearLayoutDisplayField'", LinearLayout.class);
    target.textViewDisplayField = Utils.findRequiredViewAsType(source, R.id.tv_dispaly_field, "field 'textViewDisplayField'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LeadSettingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.linearLayoutGroupBy = null;
    target.textViewGroupBy = null;
    target.linearLayoutPrimarySort = null;
    target.textViewPrimarySort = null;
    target.linearLayoutSecondarySort = null;
    target.textViewSecondarySort = null;
    target.linearLayoutSearchBy = null;
    target.textViewSearchBy = null;
    target.linearLayoutDisplayField = null;
    target.textViewDisplayField = null;
  }
}
