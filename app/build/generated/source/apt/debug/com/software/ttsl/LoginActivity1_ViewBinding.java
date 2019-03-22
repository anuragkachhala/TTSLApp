// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity1_ViewBinding implements Unbinder {
  private LoginActivity1 target;

  @UiThread
  public LoginActivity1_ViewBinding(LoginActivity1 target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity1_ViewBinding(LoginActivity1 target, View source) {
    this.target = target;

    target.mTrackCargo = Utils.findRequiredViewAsType(source, R.id.layout_track_cargo, "field 'mTrackCargo'", LinearLayout.class);
    target.mSailingSchdule = Utils.findRequiredViewAsType(source, R.id.layout_sailing_schedule, "field 'mSailingSchdule'", LinearLayout.class);
    target.mServices = Utils.findRequiredViewAsType(source, R.id.layout_services, "field 'mServices'", LinearLayout.class);
    target.mContactUs = Utils.findRequiredViewAsType(source, R.id.layout_contact_us, "field 'mContactUs'", LinearLayout.class);
    target.mUserName = Utils.findRequiredViewAsType(source, R.id.et_username, "field 'mUserName'", EditText.class);
    target.mPassWord = Utils.findRequiredViewAsType(source, R.id.et_password, "field 'mPassWord'", EditText.class);
    target.mUserNameLayout = Utils.findRequiredViewAsType(source, R.id.widget_username, "field 'mUserNameLayout'", TextInputLayout.class);
    target.mPasswordLayout = Utils.findRequiredViewAsType(source, R.id.widget_password, "field 'mPasswordLayout'", TextInputLayout.class);
    target.mloginBtn = Utils.findRequiredViewAsType(source, R.id.btn_login, "field 'mloginBtn'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity1 target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTrackCargo = null;
    target.mSailingSchdule = null;
    target.mServices = null;
    target.mContactUs = null;
    target.mUserName = null;
    target.mPassWord = null;
    target.mUserNameLayout = null;
    target.mPasswordLayout = null;
    target.mloginBtn = null;
  }
}
