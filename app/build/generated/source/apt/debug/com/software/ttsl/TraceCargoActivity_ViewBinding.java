// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TraceCargoActivity_ViewBinding implements Unbinder {
  private TraceCargoActivity target;

  @UiThread
  public TraceCargoActivity_ViewBinding(TraceCargoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TraceCargoActivity_ViewBinding(TraceCargoActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.textViewHeadingBillNumber = Utils.findRequiredViewAsType(source, R.id.tv_heading_bill_number, "field 'textViewHeadingBillNumber'", TextView.class);
    target.textViewStatus = Utils.findRequiredViewAsType(source, R.id.tv_status, "field 'textViewStatus'", TextView.class);
    target.textViewTransitTime = Utils.findRequiredViewAsType(source, R.id.tv_transit_time, "field 'textViewTransitTime'", TextView.class);
    target.textViewDepartedTime = Utils.findRequiredViewAsType(source, R.id.tv_departed_time, "field 'textViewDepartedTime'", TextView.class);
    target.textViewPOD = Utils.findRequiredViewAsType(source, R.id.tv_departed_port_name, "field 'textViewPOD'", TextView.class);
    target.textViewPODCode = Utils.findRequiredViewAsType(source, R.id.tv_departed_port_code, "field 'textViewPODCode'", TextView.class);
    target.textViewArrivalTime = Utils.findRequiredViewAsType(source, R.id.tv_arrival_time, "field 'textViewArrivalTime'", TextView.class);
    target.textViewPOL = Utils.findRequiredViewAsType(source, R.id.tv_arrival_port_name, "field 'textViewPOL'", TextView.class);
    target.textViewPOLCode = Utils.findRequiredViewAsType(source, R.id.tv_arrival_port_code, "field 'textViewPOLCode'", TextView.class);
    target.imageViewToggleCarted = Utils.findRequiredViewAsType(source, R.id.iv_toggle_carted, "field 'imageViewToggleCarted'", ImageView.class);
    target.linearLayoutCartedDetails = Utils.findRequiredViewAsType(source, R.id.layout_carted_details, "field 'linearLayoutCartedDetails'", LinearLayout.class);
    target.textViewCartingDate = Utils.findRequiredViewAsType(source, R.id.tv_carting_date, "field 'textViewCartingDate'", TextView.class);
    target.imageViewToggleReceived = Utils.findRequiredViewAsType(source, R.id.iv_toggle_received, "field 'imageViewToggleReceived'", ImageView.class);
    target.textViewCustomerClearanceDate = Utils.findRequiredViewAsType(source, R.id.tv_customer_clearance_date, "field 'textViewCustomerClearanceDate'", TextView.class);
    target.linearLayoutReceivedDetails = Utils.findRequiredViewAsType(source, R.id.layout_received_details, "field 'linearLayoutReceivedDetails'", LinearLayout.class);
    target.imageViewToggleBooked = Utils.findRequiredViewAsType(source, R.id.iv_toggle_booked, "field 'imageViewToggleBooked'", ImageView.class);
    target.linearLayoutBookedDetails = Utils.findRequiredViewAsType(source, R.id.layout_booked_details, "field 'linearLayoutBookedDetails'", LinearLayout.class);
    target.textViewBookingDate = Utils.findRequiredViewAsType(source, R.id.tv_booked_date, "field 'textViewBookingDate'", TextView.class);
    target.textViewBookingNo = Utils.findRequiredViewAsType(source, R.id.tv_booking_no, "field 'textViewBookingNo'", TextView.class);
    target.imageViewToggleStuffed = Utils.findRequiredViewAsType(source, R.id.iv_toggle_stuffed, "field 'imageViewToggleStuffed'", ImageView.class);
    target.linearLayoutStuffedDetails = Utils.findRequiredViewAsType(source, R.id.layout_stuffed_details, "field 'linearLayoutStuffedDetails'", LinearLayout.class);
    target.textViewContainerNo = Utils.findRequiredViewAsType(source, R.id.tv_container_no, "field 'textViewContainerNo'", TextView.class);
    target.textViewContainerVolume = Utils.findRequiredViewAsType(source, R.id.tv_container_volume, "field 'textViewContainerVolume'", TextView.class);
    target.imageViewToggelGenerated = Utils.findRequiredViewAsType(source, R.id.iv_toggle_generated, "field 'imageViewToggelGenerated'", ImageView.class);
    target.linearLayoutGeneratedDetails = Utils.findRequiredViewAsType(source, R.id.layout_generated_details, "field 'linearLayoutGeneratedDetails'", LinearLayout.class);
    target.textViewGeneratedDate = Utils.findRequiredViewAsType(source, R.id.tv_generated_date, "field 'textViewGeneratedDate'", TextView.class);
    target.textViewJobNo = Utils.findRequiredViewAsType(source, R.id.tv_job_no, "field 'textViewJobNo'", TextView.class);
    target.imageViewToggleLoaded = Utils.findRequiredViewAsType(source, R.id.iv_toggle_loaded, "field 'imageViewToggleLoaded'", ImageView.class);
    target.linearLayoutLoadedDetails = Utils.findRequiredViewAsType(source, R.id.layout_loaded_details, "field 'linearLayoutLoadedDetails'", LinearLayout.class);
    target.textViewSobDate = Utils.findRequiredViewAsType(source, R.id.tv_sob_date, "field 'textViewSobDate'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TraceCargoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.textViewHeadingBillNumber = null;
    target.textViewStatus = null;
    target.textViewTransitTime = null;
    target.textViewDepartedTime = null;
    target.textViewPOD = null;
    target.textViewPODCode = null;
    target.textViewArrivalTime = null;
    target.textViewPOL = null;
    target.textViewPOLCode = null;
    target.imageViewToggleCarted = null;
    target.linearLayoutCartedDetails = null;
    target.textViewCartingDate = null;
    target.imageViewToggleReceived = null;
    target.textViewCustomerClearanceDate = null;
    target.linearLayoutReceivedDetails = null;
    target.imageViewToggleBooked = null;
    target.linearLayoutBookedDetails = null;
    target.textViewBookingDate = null;
    target.textViewBookingNo = null;
    target.imageViewToggleStuffed = null;
    target.linearLayoutStuffedDetails = null;
    target.textViewContainerNo = null;
    target.textViewContainerVolume = null;
    target.imageViewToggelGenerated = null;
    target.linearLayoutGeneratedDetails = null;
    target.textViewGeneratedDate = null;
    target.textViewJobNo = null;
    target.imageViewToggleLoaded = null;
    target.linearLayoutLoadedDetails = null;
    target.textViewSobDate = null;
  }
}
