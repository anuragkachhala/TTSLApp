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

public class SearchVesselActivity_ViewBinding implements Unbinder {
  private SearchVesselActivity target;

  @UiThread
  public SearchVesselActivity_ViewBinding(SearchVesselActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SearchVesselActivity_ViewBinding(SearchVesselActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.editTextSearchVessel = Utils.findRequiredViewAsType(source, R.id.et_search_vessel, "field 'editTextSearchVessel'", EditText.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    target.imageViewClearSearch = Utils.findRequiredViewAsType(source, R.id.iv_clear_search, "field 'imageViewClearSearch'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SearchVesselActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.editTextSearchVessel = null;
    target.recyclerView = null;
    target.imageViewClearSearch = null;
  }
}
