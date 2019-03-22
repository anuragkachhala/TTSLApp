// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.software.ttsl.Fragment.NonSwipeableViewPager;
import java.lang.IllegalStateException;
import java.lang.Override;

public class QuotationFormActivity_ViewBinding implements Unbinder {
  private QuotationFormActivity target;

  @UiThread
  public QuotationFormActivity_ViewBinding(QuotationFormActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public QuotationFormActivity_ViewBinding(QuotationFormActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.mmStepViewLayout = Utils.findRequiredViewAsType(source, R.id.layout_step_view, "field 'mmStepViewLayout'", LinearLayout.class);
    target.mStepView = Utils.findRequiredViewAsType(source, R.id.iv_step_view, "field 'mStepView'", ImageView.class);
    target.buttonNext = Utils.findRequiredViewAsType(source, R.id.btn_next, "field 'buttonNext'", Button.class);
    target.buttonPre = Utils.findRequiredViewAsType(source, R.id.btn_previous, "field 'buttonPre'", Button.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.viewpager, "field 'viewPager'", NonSwipeableViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    QuotationFormActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.mmStepViewLayout = null;
    target.mStepView = null;
    target.buttonNext = null;
    target.buttonPre = null;
    target.viewPager = null;
  }
}
