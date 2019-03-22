// Generated code from Butter Knife. Do not modify!
package com.software.ttsl.NewModule;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.software.ttsl.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TrackCargoFragment_ViewBinding implements Unbinder {
  private TrackCargoFragment target;

  @UiThread
  public TrackCargoFragment_ViewBinding(TrackCargoFragment target, View source) {
    this.target = target;

    target.spinnerCargoType = Utils.findRequiredViewAsType(source, R.id.spinner_cargo_type, "field 'spinnerCargoType'", Spinner.class);
    target.editTextTrackNo = Utils.findRequiredViewAsType(source, R.id.et_track_no, "field 'editTextTrackNo'", EditText.class);
    target.buttonTrackCargo = Utils.findRequiredViewAsType(source, R.id.btn_track_cargo, "field 'buttonTrackCargo'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TrackCargoFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.spinnerCargoType = null;
    target.editTextTrackNo = null;
    target.buttonTrackCargo = null;
  }
}
