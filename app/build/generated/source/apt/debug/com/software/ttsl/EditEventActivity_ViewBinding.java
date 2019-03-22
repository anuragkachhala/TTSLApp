// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EditEventActivity_ViewBinding implements Unbinder {
  private EditEventActivity target;

  @UiThread
  public EditEventActivity_ViewBinding(EditEventActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EditEventActivity_ViewBinding(EditEventActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.editTextEventTitle = Utils.findRequiredViewAsType(source, R.id.et_event_title, "field 'editTextEventTitle'", EditText.class);
    target.editTextEventLocation = Utils.findRequiredViewAsType(source, R.id.et_event_location, "field 'editTextEventLocation'", EditText.class);
    target.editTextEventFrom = Utils.findRequiredViewAsType(source, R.id.et_event_from, "field 'editTextEventFrom'", EditText.class);
    target.editTextEventFromTime = Utils.findRequiredViewAsType(source, R.id.et_event_from_time, "field 'editTextEventFromTime'", EditText.class);
    target.editTextEventToTime = Utils.findRequiredViewAsType(source, R.id.et_event_to_time, "field 'editTextEventToTime'", EditText.class);
    target.editTextEventTo = Utils.findRequiredViewAsType(source, R.id.et_event_to, "field 'editTextEventTo'", EditText.class);
    target.editTextEventHost = Utils.findRequiredViewAsType(source, R.id.et_event_host, "field 'editTextEventHost'", EditText.class);
    target.editTextEventParticipants = Utils.findRequiredViewAsType(source, R.id.et_event_participants, "field 'editTextEventParticipants'", EditText.class);
    target.editTextEventContact = Utils.findRequiredViewAsType(source, R.id.et_event_contact, "field 'editTextEventContact'", EditText.class);
    target.editTextEventAccount = Utils.findRequiredViewAsType(source, R.id.et_event_account, "field 'editTextEventAccount'", EditText.class);
    target.editTextEventDescription = Utils.findRequiredViewAsType(source, R.id.et_event_description, "field 'editTextEventDescription'", EditText.class);
    target.aSwitchEventAllDay = Utils.findRequiredViewAsType(source, R.id.switch_event_all_day, "field 'aSwitchEventAllDay'", Switch.class);
    target.linearLayoutEventHost = Utils.findRequiredViewAsType(source, R.id.linear_layout_event_host, "field 'linearLayoutEventHost'", LinearLayout.class);
    target.linearLayoutEventParticipants = Utils.findRequiredViewAsType(source, R.id.linear_layout_event_participants, "field 'linearLayoutEventParticipants'", LinearLayout.class);
    target.linearLayoutEventContact = Utils.findRequiredViewAsType(source, R.id.linear_layout_event_contact, "field 'linearLayoutEventContact'", LinearLayout.class);
    target.linearLayoutEventAccount = Utils.findRequiredViewAsType(source, R.id.linear_layout_event_account, "field 'linearLayoutEventAccount'", LinearLayout.class);
    target.linearLayoutDescription = Utils.findRequiredViewAsType(source, R.id.linear_layout_description_information, "field 'linearLayoutDescription'", LinearLayout.class);
    target.linearLayoutEventFromTime = Utils.findRequiredViewAsType(source, R.id.linear_layout_event_from_time, "field 'linearLayoutEventFromTime'", LinearLayout.class);
    target.linearLayoutEventToTime = Utils.findRequiredViewAsType(source, R.id.linear_layout_event_to_time, "field 'linearLayoutEventToTime'", LinearLayout.class);
    target.textViewLabelContact = Utils.findRequiredViewAsType(source, R.id.tv_event_contact, "field 'textViewLabelContact'", TextView.class);
    target.textViewSmartView = Utils.findRequiredViewAsType(source, R.id.tv_smart_view, "field 'textViewSmartView'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EditEventActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.editTextEventTitle = null;
    target.editTextEventLocation = null;
    target.editTextEventFrom = null;
    target.editTextEventFromTime = null;
    target.editTextEventToTime = null;
    target.editTextEventTo = null;
    target.editTextEventHost = null;
    target.editTextEventParticipants = null;
    target.editTextEventContact = null;
    target.editTextEventAccount = null;
    target.editTextEventDescription = null;
    target.aSwitchEventAllDay = null;
    target.linearLayoutEventHost = null;
    target.linearLayoutEventParticipants = null;
    target.linearLayoutEventContact = null;
    target.linearLayoutEventAccount = null;
    target.linearLayoutDescription = null;
    target.linearLayoutEventFromTime = null;
    target.linearLayoutEventToTime = null;
    target.textViewLabelContact = null;
    target.textViewSmartView = null;
  }
}
