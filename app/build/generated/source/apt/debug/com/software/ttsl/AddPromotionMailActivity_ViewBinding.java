// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddPromotionMailActivity_ViewBinding implements Unbinder {
  private AddPromotionMailActivity target;

  @UiThread
  public AddPromotionMailActivity_ViewBinding(AddPromotionMailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddPromotionMailActivity_ViewBinding(AddPromotionMailActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.editTextMailFrom = Utils.findRequiredViewAsType(source, R.id.et_mail_from, "field 'editTextMailFrom'", EditText.class);
    target.editTextMailTo = Utils.findRequiredViewAsType(source, R.id.et_mail_to, "field 'editTextMailTo'", EditText.class);
    target.editTextCommercial = Utils.findRequiredViewAsType(source, R.id.et_mail_commercial, "field 'editTextCommercial'", EditText.class);
    target.editTextEmailSendBy = Utils.findRequiredViewAsType(source, R.id.et_mail_send_by, "field 'editTextEmailSendBy'", EditText.class);
    target.editTextMailSubject = Utils.findRequiredViewAsType(source, R.id.et_mail_subject, "field 'editTextMailSubject'", EditText.class);
    target.editTextMailCountry = Utils.findRequiredViewAsType(source, R.id.et_mail_country, "field 'editTextMailCountry'", EditText.class);
    target.editTextMailAttachment = Utils.findRequiredViewAsType(source, R.id.et_mail_attachment, "field 'editTextMailAttachment'", EditText.class);
    target.editTextMailBody = Utils.findRequiredViewAsType(source, R.id.et_mail_body, "field 'editTextMailBody'", EditText.class);
    target.editTextMailCategory = Utils.findRequiredViewAsType(source, R.id.et_mail_category, "field 'editTextMailCategory'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AddPromotionMailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.editTextMailFrom = null;
    target.editTextMailTo = null;
    target.editTextCommercial = null;
    target.editTextEmailSendBy = null;
    target.editTextMailSubject = null;
    target.editTextMailCountry = null;
    target.editTextMailAttachment = null;
    target.editTextMailBody = null;
    target.editTextMailCategory = null;
  }
}
