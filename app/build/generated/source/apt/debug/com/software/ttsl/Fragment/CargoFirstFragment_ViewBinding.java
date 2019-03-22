// Generated code from Butter Knife. Do not modify!
package com.software.ttsl.Fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.software.ttsl.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CargoFirstFragment_ViewBinding implements Unbinder {
  private CargoFirstFragment target;

  @UiThread
  public CargoFirstFragment_ViewBinding(CargoFirstFragment target, View source) {
    this.target = target;

    target.mWeightSpinner = Utils.findRequiredViewAsType(source, R.id.spinner_weight_unit, "field 'mWeightSpinner'", Spinner.class);
    target.mVolumeSpinner = Utils.findRequiredViewAsType(source, R.id.spinner_volume_unit, "field 'mVolumeSpinner'", Spinner.class);
    target.mImoClassSpinner = Utils.findRequiredViewAsType(source, R.id.spinner_imo_class, "field 'mImoClassSpinner'", Spinner.class);
    target.mContainerSpinner = Utils.findRequiredViewAsType(source, R.id.spinner_container_type, "field 'mContainerSpinner'", Spinner.class);
    target.mWeight = Utils.findRequiredViewAsType(source, R.id.et_weight, "field 'mWeight'", EditText.class);
    target.mVolume = Utils.findRequiredViewAsType(source, R.id.et_volume, "field 'mVolume'", EditText.class);
    target.mVolumeView = Utils.findRequiredViewAsType(source, R.id.view_volume, "field 'mVolumeView'", LinearLayout.class);
    target.mWeightLayout = Utils.findRequiredViewAsType(source, R.id.widget_weight, "field 'mWeightLayout'", TextInputLayout.class);
    target.mVolumeLayout = Utils.findRequiredViewAsType(source, R.id.widget_volume, "field 'mVolumeLayout'", TextInputLayout.class);
    target.mContainerCountLayout = Utils.findRequiredViewAsType(source, R.id.container_count_Widget, "field 'mContainerCountLayout'", TextInputLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CargoFirstFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mWeightSpinner = null;
    target.mVolumeSpinner = null;
    target.mImoClassSpinner = null;
    target.mContainerSpinner = null;
    target.mWeight = null;
    target.mVolume = null;
    target.mVolumeView = null;
    target.mWeightLayout = null;
    target.mVolumeLayout = null;
    target.mContainerCountLayout = null;
  }
}
