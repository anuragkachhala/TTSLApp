// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ProfileActiviy_ViewBinding implements Unbinder {
  private ProfileActiviy target;

  @UiThread
  public ProfileActiviy_ViewBinding(ProfileActiviy target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ProfileActiviy_ViewBinding(ProfileActiviy target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.mUsername = Utils.findRequiredViewAsType(source, R.id.et_username, "field 'mUsername'", EditText.class);
    target.mPassowrd = Utils.findRequiredViewAsType(source, R.id.et_password, "field 'mPassowrd'", EditText.class);
    target.mEmail = Utils.findRequiredViewAsType(source, R.id.et_email, "field 'mEmail'", EditText.class);
    target.usernameTV = Utils.findRequiredViewAsType(source, R.id.tv_username, "field 'usernameTV'", TextView.class);
    target.passwordTV = Utils.findRequiredViewAsType(source, R.id.tv_password, "field 'passwordTV'", TextView.class);
    target.emailTV = Utils.findRequiredViewAsType(source, R.id.tv_email, "field 'emailTV'", TextView.class);
    target.usernameEdit = Utils.findRequiredViewAsType(source, R.id.edit_username, "field 'usernameEdit'", ImageView.class);
    target.emailEdit = Utils.findRequiredViewAsType(source, R.id.edit_email, "field 'emailEdit'", ImageView.class);
    target.passwordEdit = Utils.findRequiredViewAsType(source, R.id.edit_password, "field 'passwordEdit'", ImageView.class);
    target.mSaveProfileBtn = Utils.findRequiredViewAsType(source, R.id.btn_save_profile, "field 'mSaveProfileBtn'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ProfileActiviy target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.mUsername = null;
    target.mPassowrd = null;
    target.mEmail = null;
    target.usernameTV = null;
    target.passwordTV = null;
    target.emailTV = null;
    target.usernameEdit = null;
    target.emailEdit = null;
    target.passwordEdit = null;
    target.mSaveProfileBtn = null;
  }
}
