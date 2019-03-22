// Generated code from Butter Knife. Do not modify!
package com.software.ttsl.Fragment.UtilFragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.software.ttsl.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TestFragment_ViewBinding implements Unbinder {
  private TestFragment target;

  @UiThread
  public TestFragment_ViewBinding(TestFragment target, View source) {
    this.target = target;

    target.employee_home_event_tv = Utils.findRequiredViewAsType(source, R.id.employee_home_event_tv, "field 'employee_home_event_tv'", TextView.class);
    target.employee_home_task_tv = Utils.findRequiredViewAsType(source, R.id.employee_home_task_tv, "field 'employee_home_task_tv'", TextView.class);
    target.employee_home_call_tv = Utils.findRequiredViewAsType(source, R.id.employee_home_call_tv, "field 'employee_home_call_tv'", TextView.class);
    target.mDateSelectedTv = Utils.findRequiredViewAsType(source, R.id.tv, "field 'mDateSelectedTv'", TextView.class);
    target.employee_layout_event_data = Utils.findRequiredViewAsType(source, R.id.employee_layout_event_data, "field 'employee_layout_event_data'", LinearLayout.class);
    target.employee_linear_layout_event_data = Utils.findRequiredViewAsType(source, R.id.employee_linear_layout_event_data, "field 'employee_linear_layout_event_data'", LinearLayout.class);
    target.employee_layout_task_data = Utils.findRequiredViewAsType(source, R.id.employee_layout_taks_data, "field 'employee_layout_task_data'", LinearLayout.class);
    target.employee_layout_call_data = Utils.findRequiredViewAsType(source, R.id.employee_layout_call_date, "field 'employee_layout_call_data'", LinearLayout.class);
    target.employee_iv_add_event = Utils.findRequiredViewAsType(source, R.id.employee_iv_add_event, "field 'employee_iv_add_event'", ImageView.class);
    target.employee_iv_add_task = Utils.findRequiredViewAsType(source, R.id.employee_iv_add_task, "field 'employee_iv_add_task'", ImageView.class);
    target.employee_iv_add_calls = Utils.findRequiredViewAsType(source, R.id.employee_iv_add_calls, "field 'employee_iv_add_calls'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TestFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.employee_home_event_tv = null;
    target.employee_home_task_tv = null;
    target.employee_home_call_tv = null;
    target.mDateSelectedTv = null;
    target.employee_layout_event_data = null;
    target.employee_linear_layout_event_data = null;
    target.employee_layout_task_data = null;
    target.employee_layout_call_data = null;
    target.employee_iv_add_event = null;
    target.employee_iv_add_task = null;
    target.employee_iv_add_calls = null;
  }
}
