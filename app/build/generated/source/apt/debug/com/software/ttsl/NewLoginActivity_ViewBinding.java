// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NewLoginActivity_ViewBinding implements Unbinder {
  private NewLoginActivity target;

  @UiThread
  public NewLoginActivity_ViewBinding(NewLoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public NewLoginActivity_ViewBinding(NewLoginActivity target, View source) {
    this.target = target;

    target.imageViewExit = Utils.findRequiredViewAsType(source, R.id.iv_cancel, "field 'imageViewExit'", ImageView.class);
    target.mUserName = Utils.findRequiredViewAsType(source, R.id.et_username, "field 'mUserName'", EditText.class);
    target.mPassWord = Utils.findRequiredViewAsType(source, R.id.et_password, "field 'mPassWord'", EditText.class);
    target.mUserNameLayout = Utils.findRequiredViewAsType(source, R.id.widget_username, "field 'mUserNameLayout'", TextInputLayout.class);
    target.mPasswordLayout = Utils.findRequiredViewAsType(source, R.id.widget_password, "field 'mPasswordLayout'", TextInputLayout.class);
    target.mloginBtn = Utils.findRequiredViewAsType(source, R.id.btn_login, "field 'mloginBtn'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NewLoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imageViewExit = null;
    target.mUserName = null;
    target.mPassWord = null;
    target.mUserNameLayout = null;
    target.mPasswordLayout = null;
    target.mloginBtn = null;
  }
}
