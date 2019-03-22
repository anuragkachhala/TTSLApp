// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddParticipantsActivity_ViewBinding implements Unbinder {
  private AddParticipantsActivity target;

  @UiThread
  public AddParticipantsActivity_ViewBinding(AddParticipantsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddParticipantsActivity_ViewBinding(AddParticipantsActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.ivAddParticipants = Utils.findRequiredViewAsType(source, R.id.iv_add_participants, "field 'ivAddParticipants'", ImageView.class);
    target.textViewParticipants = Utils.findRequiredViewAsType(source, R.id.tv_participants, "field 'textViewParticipants'", TextView.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AddParticipantsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.ivAddParticipants = null;
    target.textViewParticipants = null;
    target.recyclerView = null;
  }
}
