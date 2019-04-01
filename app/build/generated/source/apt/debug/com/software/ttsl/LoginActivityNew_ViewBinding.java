// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivityNew_ViewBinding implements Unbinder {
  private LoginActivityNew target;

  @UiThread
  public LoginActivityNew_ViewBinding(LoginActivityNew target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivityNew_ViewBinding(LoginActivityNew target, View source) {
    this.target = target;

    target.editTextEmail = Utils.findRequiredViewAsType(source, R.id.et_email, "field 'editTextEmail'", EditText.class);
    target.editTextPassword = Utils.findRequiredViewAsType(source, R.id.et_password, "field 'editTextPassword'", EditText.class);
    target.spinnerOffice = Utils.findRequiredViewAsType(source, R.id.sp_office, "field 'spinnerOffice'", Spinner.class);
    target.buttonLogin = Utils.findRequiredViewAsType(source, R.id.btn_login, "field 'buttonLogin'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivityNew target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.editTextEmail = null;
    target.editTextPassword = null;
    target.spinnerOffice = null;
    target.buttonLogin = null;
  }
}
