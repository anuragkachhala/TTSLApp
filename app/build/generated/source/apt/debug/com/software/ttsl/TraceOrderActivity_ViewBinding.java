// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TraceOrderActivity_ViewBinding implements Unbinder {
  private TraceOrderActivity target;

  @UiThread
  public TraceOrderActivity_ViewBinding(TraceOrderActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TraceOrderActivity_ViewBinding(TraceOrderActivity target, View source) {
    this.target = target;

    target.layout_generated = Utils.findRequiredViewAsType(source, R.id.linear_generated, "field 'layout_generated'", LinearLayout.class);
    target.layout_loaded = Utils.findRequiredViewAsType(source, R.id.linear_loaded, "field 'layout_loaded'", LinearLayout.class);
    target.layout_stuffed = Utils.findRequiredViewAsType(source, R.id.linear_stuffed, "field 'layout_stuffed'", LinearLayout.class);
    target.layout_booked = Utils.findRequiredViewAsType(source, R.id.linear_booked, "field 'layout_booked'", LinearLayout.class);
    target.layout_received = Utils.findRequiredViewAsType(source, R.id.linear_booked1, "field 'layout_received'", LinearLayout.class);
    target.layout_carted = Utils.findRequiredViewAsType(source, R.id.linear_carted, "field 'layout_carted'", LinearLayout.class);
    target.generatedIV = Utils.findRequiredViewAsType(source, R.id.iv_generated, "field 'generatedIV'", ImageView.class);
    target.loadedIV = Utils.findRequiredViewAsType(source, R.id.iv_loaded, "field 'loadedIV'", ImageView.class);
    target.stuffedIV = Utils.findRequiredViewAsType(source, R.id.iv_stuffed, "field 'stuffedIV'", ImageView.class);
    target.bookedIV = Utils.findRequiredViewAsType(source, R.id.iv_booked, "field 'bookedIV'", ImageView.class);
    target.receivedIV = Utils.findRequiredViewAsType(source, R.id.iv_booked1, "field 'receivedIV'", ImageView.class);
    target.cartedIV = Utils.findRequiredViewAsType(source, R.id.iv_carted, "field 'cartedIV'", ImageView.class);
    target.button = Utils.findRequiredViewAsType(source, R.id.next, "field 'button'", Button.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.textViewCfsPort = Utils.findRequiredViewAsType(source, R.id.tv_cfs_port, "field 'textViewCfsPort'", TextView.class);
    target.textViewPolPort = Utils.findRequiredViewAsType(source, R.id.tv_pol_port, "field 'textViewPolPort'", TextView.class);
    target.tvPolId = Utils.findRequiredViewAsType(source, R.id.tv_pol, "field 'tvPolId'", TextView.class);
    target.textViewPodPort = Utils.findRequiredViewAsType(source, R.id.tv_pod_port, "field 'textViewPodPort'", TextView.class);
    target.textViewStuffedId = Utils.findRequiredViewAsType(source, R.id.tv_stuffed_id, "field 'textViewStuffedId'", TextView.class);
    target.textViewBookedDate = Utils.findRequiredViewAsType(source, R.id.tv_booked_date, "field 'textViewBookedDate'", TextView.class);
    target.textViewBookedNo = Utils.findRequiredViewAsType(source, R.id.tv_booked_no, "field 'textViewBookedNo'", TextView.class);
    target.textViewReceivedDate = Utils.findRequiredViewAsType(source, R.id.tv_received_date, "field 'textViewReceivedDate'", TextView.class);
    target.textViewReceivedId = Utils.findRequiredViewAsType(source, R.id.tv_received_id, "field 'textViewReceivedId'", TextView.class);
    target.textViewCartedData = Utils.findRequiredViewAsType(source, R.id.tv_carted_date, "field 'textViewCartedData'", TextView.class);
    target.textViewCartedNo = Utils.findRequiredViewAsType(source, R.id.tv_carted_no, "field 'textViewCartedNo'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TraceOrderActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.layout_generated = null;
    target.layout_loaded = null;
    target.layout_stuffed = null;
    target.layout_booked = null;
    target.layout_received = null;
    target.layout_carted = null;
    target.generatedIV = null;
    target.loadedIV = null;
    target.stuffedIV = null;
    target.bookedIV = null;
    target.receivedIV = null;
    target.cartedIV = null;
    target.button = null;
    target.toolbar = null;
    target.textViewCfsPort = null;
    target.textViewPolPort = null;
    target.tvPolId = null;
    target.textViewPodPort = null;
    target.textViewStuffedId = null;
    target.textViewBookedDate = null;
    target.textViewBookedNo = null;
    target.textViewReceivedDate = null;
    target.textViewReceivedId = null;
    target.textViewCartedData = null;
    target.textViewCartedNo = null;
  }
}
