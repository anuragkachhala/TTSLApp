// Generated code from Butter Knife. Do not modify!
package com.software.ttsl.Fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.software.ttsl.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EventDetailFragment_ViewBinding implements Unbinder {
  private EventDetailFragment target;

  @UiThread
  public EventDetailFragment_ViewBinding(EventDetailFragment target, View source) {
    this.target = target;

    target.textViewEventTitle = Utils.findRequiredViewAsType(source, R.id.tv_event_title, "field 'textViewEventTitle'", TextView.class);
    target.textViewEventFrom = Utils.findRequiredViewAsType(source, R.id.tv_event_from, "field 'textViewEventFrom'", TextView.class);
    target.textViewDetailLabel = Utils.findRequiredViewAsType(source, R.id.tv_title_label, "field 'textViewDetailLabel'", TextView.class);
    target.textViewEventLocation = Utils.findRequiredViewAsType(source, R.id.tv_event_location, "field 'textViewEventLocation'", TextView.class);
    target.textViewEventTo = Utils.findRequiredViewAsType(source, R.id.tv_event_to, "field 'textViewEventTo'", TextView.class);
    target.textViewEventHost = Utils.findRequiredViewAsType(source, R.id.tv_event_host, "field 'textViewEventHost'", TextView.class);
    target.textViewEventContact = Utils.findRequiredViewAsType(source, R.id.tv_event_contact, "field 'textViewEventContact'", TextView.class);
    target.textViewEventCreatedBy = Utils.findRequiredViewAsType(source, R.id.tv_event_created_by, "field 'textViewEventCreatedBy'", TextView.class);
    target.textViewEventModifiedBy = Utils.findRequiredViewAsType(source, R.id.tv_event_modified_by, "field 'textViewEventModifiedBy'", TextView.class);
    target.textViewCreateTime = Utils.findRequiredViewAsType(source, R.id.tv_event_create_time, "field 'textViewCreateTime'", TextView.class);
    target.textViewEventModifiedTime = Utils.findRequiredViewAsType(source, R.id.tv_event_modified_time, "field 'textViewEventModifiedTime'", TextView.class);
    target.textViewEventDescription = Utils.findRequiredViewAsType(source, R.id.tv_event_description, "field 'textViewEventDescription'", TextView.class);
    target.linearLayoutEventLocation = Utils.findRequiredViewAsType(source, R.id.linear_layout_event_location, "field 'linearLayoutEventLocation'", LinearLayout.class);
    target.linearLayoutEventContact = Utils.findRequiredViewAsType(source, R.id.linear_layout_event_contact, "field 'linearLayoutEventContact'", LinearLayout.class);
    target.linearLayoutDescription = Utils.findRequiredViewAsType(source, R.id.layout_event_description, "field 'linearLayoutDescription'", LinearLayout.class);
    target.textViewSmartView = Utils.findRequiredViewAsType(source, R.id.tv_smart_view, "field 'textViewSmartView'", TextView.class);
    target.linearLayoutEventAddDay = Utils.findRequiredViewAsType(source, R.id.linear_layout_event_all_day, "field 'linearLayoutEventAddDay'", LinearLayout.class);
    target.textViewEventAllDay = Utils.findRequiredViewAsType(source, R.id.tv_event_all_day, "field 'textViewEventAllDay'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EventDetailFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.textViewEventTitle = null;
    target.textViewEventFrom = null;
    target.textViewDetailLabel = null;
    target.textViewEventLocation = null;
    target.textViewEventTo = null;
    target.textViewEventHost = null;
    target.textViewEventContact = null;
    target.textViewEventCreatedBy = null;
    target.textViewEventModifiedBy = null;
    target.textViewCreateTime = null;
    target.textViewEventModifiedTime = null;
    target.textViewEventDescription = null;
    target.linearLayoutEventLocation = null;
    target.linearLayoutEventContact = null;
    target.linearLayoutDescription = null;
    target.textViewSmartView = null;
    target.linearLayoutEventAddDay = null;
    target.textViewEventAllDay = null;
  }
}
