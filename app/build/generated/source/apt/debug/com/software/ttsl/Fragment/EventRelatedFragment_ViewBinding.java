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

public class EventRelatedFragment_ViewBinding implements Unbinder {
  private EventRelatedFragment target;

  @UiThread
  public EventRelatedFragment_ViewBinding(EventRelatedFragment target, View source) {
    this.target = target;

    target.textViewEventTitle = Utils.findRequiredViewAsType(source, R.id.tv_event_title, "field 'textViewEventTitle'", TextView.class);
    target.textViewEventDate = Utils.findRequiredViewAsType(source, R.id.tv_event_date, "field 'textViewEventDate'", TextView.class);
    target.textViewEventTime = Utils.findRequiredViewAsType(source, R.id.tv_event_time, "field 'textViewEventTime'", TextView.class);
    target.textViewEventLocation = Utils.findRequiredViewAsType(source, R.id.tv_event_location, "field 'textViewEventLocation'", TextView.class);
    target.linearLayoutEventContact = Utils.findRequiredViewAsType(source, R.id.layout_event_contact, "field 'linearLayoutEventContact'", LinearLayout.class);
    target.textViewEventContact = Utils.findRequiredViewAsType(source, R.id.tv_event_contact, "field 'textViewEventContact'", TextView.class);
    target.textViewLabelContact = Utils.findRequiredViewAsType(source, R.id.tv_label_contact, "field 'textViewLabelContact'", TextView.class);
    target.linearLayoutEventAccount = Utils.findRequiredViewAsType(source, R.id.layout_event_account, "field 'linearLayoutEventAccount'", LinearLayout.class);
    target.textViewEventAccount = Utils.findRequiredViewAsType(source, R.id.tv_event_account, "field 'textViewEventAccount'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EventRelatedFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.textViewEventTitle = null;
    target.textViewEventDate = null;
    target.textViewEventTime = null;
    target.textViewEventLocation = null;
    target.linearLayoutEventContact = null;
    target.textViewEventContact = null;
    target.textViewLabelContact = null;
    target.linearLayoutEventAccount = null;
    target.textViewEventAccount = null;
  }
}
