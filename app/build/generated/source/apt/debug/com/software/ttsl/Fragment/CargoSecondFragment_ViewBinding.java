// Generated code from Butter Knife. Do not modify!
package com.software.ttsl.Fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.software.ttsl.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CargoSecondFragment_ViewBinding implements Unbinder {
  private CargoSecondFragment target;

  @UiThread
  public CargoSecondFragment_ViewBinding(CargoSecondFragment target, View source) {
    this.target = target;

    target.mPackingTypeSpinner = Utils.findRequiredViewAsType(source, R.id.spinner_packing_type, "field 'mPackingTypeSpinner'", Spinner.class);
    target.mCommodityName = Utils.findRequiredViewAsType(source, R.id.et_commodity, "field 'mCommodityName'", EditText.class);
    target.mSourcePort = Utils.findRequiredViewAsType(source, R.id.et_from_port, "field 'mSourcePort'", EditText.class);
    target.mDestinationPort = Utils.findRequiredViewAsType(source, R.id.et_to_port, "field 'mDestinationPort'", EditText.class);
    target.mCommodityLayout = Utils.findRequiredViewAsType(source, R.id.widget_commodity, "field 'mCommodityLayout'", TextInputLayout.class);
    target.mSourcePortLayout = Utils.findRequiredViewAsType(source, R.id.widget_from_port, "field 'mSourcePortLayout'", TextInputLayout.class);
    target.mDestinationPortLayout = Utils.findRequiredViewAsType(source, R.id.widget_to_port, "field 'mDestinationPortLayout'", TextInputLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CargoSecondFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mPackingTypeSpinner = null;
    target.mCommodityName = null;
    target.mSourcePort = null;
    target.mDestinationPort = null;
    target.mCommodityLayout = null;
    target.mSourcePortLayout = null;
    target.mDestinationPortLayout = null;
  }
}
