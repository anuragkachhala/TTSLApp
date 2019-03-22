// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ContactModeActivity_ViewBinding implements Unbinder {
  private ContactModeActivity target;

  @UiThread
  public ContactModeActivity_ViewBinding(ContactModeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ContactModeActivity_ViewBinding(ContactModeActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.mailTextView = Utils.findRequiredViewAsType(source, R.id.tv_mail, "field 'mailTextView'", TextView.class);
    target.inPersonTextView = Utils.findRequiredViewAsType(source, R.id.tv_in_person, "field 'inPersonTextView'", TextView.class);
    target.phoneTextView = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'phoneTextView'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ContactModeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.mailTextView = null;
    target.inPersonTextView = null;
    target.phoneTextView = null;
  }
}
