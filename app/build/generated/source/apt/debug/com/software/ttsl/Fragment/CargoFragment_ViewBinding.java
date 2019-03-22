// Generated code from Butter Knife. Do not modify!
package com.software.ttsl.Fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.software.ttsl.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CargoFragment_ViewBinding implements Unbinder {
  private CargoFragment target;

  @UiThread
  public CargoFragment_ViewBinding(CargoFragment target, View source) {
    this.target = target;

    target.mTrackCargoSpinner = Utils.findRequiredViewAsType(source, R.id.spinner_cargo_type, "field 'mTrackCargoSpinner'", Spinner.class);
    target.mTrackBtn = Utils.findRequiredViewAsType(source, R.id.btn_track_cargo, "field 'mTrackBtn'", Button.class);
    target.mTrackNo = Utils.findRequiredViewAsType(source, R.id.et_track_no, "field 'mTrackNo'", EditText.class);
    target.textViewHint = Utils.findRequiredViewAsType(source, R.id.tv_hint, "field 'textViewHint'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CargoFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTrackCargoSpinner = null;
    target.mTrackBtn = null;
    target.mTrackNo = null;
    target.textViewHint = null;
  }
}
