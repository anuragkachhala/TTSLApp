// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.software.ttsl.Utils.MutedVideoView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WelcomeActivity_ViewBinding implements Unbinder {
  private WelcomeActivity target;

  @UiThread
  public WelcomeActivity_ViewBinding(WelcomeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WelcomeActivity_ViewBinding(WelcomeActivity target, View source) {
    this.target = target;

    target.mutedVideoView = Utils.findRequiredViewAsType(source, R.id.video_view, "field 'mutedVideoView'", MutedVideoView.class);
    target.layoutTrackCargo = Utils.findRequiredViewAsType(source, R.id.layout_track_cargo, "field 'layoutTrackCargo'", LinearLayout.class);
    target.layoutSailingSchedule = Utils.findRequiredViewAsType(source, R.id.layout_sailing_schedule, "field 'layoutSailingSchedule'", LinearLayout.class);
    target.layoutServices = Utils.findRequiredViewAsType(source, R.id.layout_services, "field 'layoutServices'", LinearLayout.class);
    target.layoutQuotation = Utils.findRequiredViewAsType(source, R.id.layout_quotation, "field 'layoutQuotation'", LinearLayout.class);
    target.layoutLoginButton = Utils.findRequiredViewAsType(source, R.id.layout_login_button, "field 'layoutLoginButton'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WelcomeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mutedVideoView = null;
    target.layoutTrackCargo = null;
    target.layoutSailingSchedule = null;
    target.layoutServices = null;
    target.layoutQuotation = null;
    target.layoutLoginButton = null;
  }
}
