// Generated code from Butter Knife. Do not modify!
package com.software.ttsl.NewModule;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.software.ttsl.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SailScheduleFragment_ViewBinding implements Unbinder {
  private SailScheduleFragment target;

  @UiThread
  public SailScheduleFragment_ViewBinding(SailScheduleFragment target, View source) {
    this.target = target;

    target.buttonSearchSchedule = Utils.findRequiredViewAsType(source, R.id.btn_search, "field 'buttonSearchSchedule'", Button.class);
    target.textViewVessel = Utils.findRequiredViewAsType(source, R.id.tv_vessel, "field 'textViewVessel'", TextView.class);
    target.relativeLayoutLoadingPort = Utils.findRequiredViewAsType(source, R.id.layout_loading, "field 'relativeLayoutLoadingPort'", RelativeLayout.class);
    target.relativeLayoutDischargePort = Utils.findRequiredViewAsType(source, R.id.layout_discharge, "field 'relativeLayoutDischargePort'", RelativeLayout.class);
    target.textViewLoadingPort = Utils.findRequiredViewAsType(source, R.id.tv_loading_port, "field 'textViewLoadingPort'", TextView.class);
    target.textViewDischargePort = Utils.findRequiredViewAsType(source, R.id.tv_discharge_port, "field 'textViewDischargePort'", TextView.class);
    target.relativeLayoutFromDepartureDate = Utils.findRequiredViewAsType(source, R.id.layout_from_departure_date, "field 'relativeLayoutFromDepartureDate'", RelativeLayout.class);
    target.relativeLayoutToDepartureDate = Utils.findRequiredViewAsType(source, R.id.layout_to_departure_date, "field 'relativeLayoutToDepartureDate'", RelativeLayout.class);
    target.relativeLayoutFromArrivalDate = Utils.findRequiredViewAsType(source, R.id.layout_from_arrival_date, "field 'relativeLayoutFromArrivalDate'", RelativeLayout.class);
    target.relativeLayoutToArrivalDate = Utils.findRequiredViewAsType(source, R.id.layout_to_arrival_date, "field 'relativeLayoutToArrivalDate'", RelativeLayout.class);
    target.textViewFromDepartureDate = Utils.findRequiredViewAsType(source, R.id.tv_from_departure_date, "field 'textViewFromDepartureDate'", TextView.class);
    target.textViewToDepartureDate = Utils.findRequiredViewAsType(source, R.id.tv_to_departure_date, "field 'textViewToDepartureDate'", TextView.class);
    target.textViewFromArrivalDate = Utils.findRequiredViewAsType(source, R.id.tv_from_arrival_date, "field 'textViewFromArrivalDate'", TextView.class);
    target.textViewToArrivalDate = Utils.findRequiredViewAsType(source, R.id.tv_to_arrival_date, "field 'textViewToArrivalDate'", TextView.class);
    target.imageViewClearFromETD = Utils.findRequiredViewAsType(source, R.id.iv_clear_from_etd, "field 'imageViewClearFromETD'", ImageView.class);
    target.imageViewClearToETD = Utils.findRequiredViewAsType(source, R.id.iv_clear_to_etd, "field 'imageViewClearToETD'", ImageView.class);
    target.imageViewClearFromETA = Utils.findRequiredViewAsType(source, R.id.iv_clear_from_eta, "field 'imageViewClearFromETA'", ImageView.class);
    target.imageViewClearToETA = Utils.findRequiredViewAsType(source, R.id.iv_clear_to_eta, "field 'imageViewClearToETA'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SailScheduleFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.buttonSearchSchedule = null;
    target.textViewVessel = null;
    target.relativeLayoutLoadingPort = null;
    target.relativeLayoutDischargePort = null;
    target.textViewLoadingPort = null;
    target.textViewDischargePort = null;
    target.relativeLayoutFromDepartureDate = null;
    target.relativeLayoutToDepartureDate = null;
    target.relativeLayoutFromArrivalDate = null;
    target.relativeLayoutToArrivalDate = null;
    target.textViewFromDepartureDate = null;
    target.textViewToDepartureDate = null;
    target.textViewFromArrivalDate = null;
    target.textViewToArrivalDate = null;
    target.imageViewClearFromETD = null;
    target.imageViewClearToETD = null;
    target.imageViewClearFromETA = null;
    target.imageViewClearToETA = null;
  }
}
