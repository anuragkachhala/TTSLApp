// Generated code from Butter Knife. Do not modify!
package com.software.ttsl.Fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.software.ttsl.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CargoThirdFragment_ViewBinding implements Unbinder {
  private CargoThirdFragment target;

  @UiThread
  public CargoThirdFragment_ViewBinding(CargoThirdFragment target, View source) {
    this.target = target;

    target.mChargesList = Utils.findRequiredViewAsType(source, R.id.charges_list, "field 'mChargesList'", ListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CargoThirdFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mChargesList = null;
  }
}
