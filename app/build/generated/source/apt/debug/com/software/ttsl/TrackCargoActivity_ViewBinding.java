// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TrackCargoActivity_ViewBinding implements Unbinder {
  private TrackCargoActivity target;

  @UiThread
  public TrackCargoActivity_ViewBinding(TrackCargoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TrackCargoActivity_ViewBinding(TrackCargoActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.mTrackCargoSpinner = Utils.findRequiredViewAsType(source, R.id.spinner_cargo_type, "field 'mTrackCargoSpinner'", Spinner.class);
    target.mTrackBtn = Utils.findRequiredViewAsType(source, R.id.btn_track_cargo, "field 'mTrackBtn'", Button.class);
    target.mTrackNo = Utils.findRequiredViewAsType(source, R.id.et_track_no, "field 'mTrackNo'", EditText.class);
    target.textViewHint = Utils.findRequiredViewAsType(source, R.id.tv_hint, "field 'textViewHint'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TrackCargoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.mTrackCargoSpinner = null;
    target.mTrackBtn = null;
    target.mTrackNo = null;
    target.textViewHint = null;
  }
}
