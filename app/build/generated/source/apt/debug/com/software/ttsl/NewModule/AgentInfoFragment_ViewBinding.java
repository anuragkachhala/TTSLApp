// Generated code from Butter Knife. Do not modify!
package com.software.ttsl.NewModule;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.software.ttsl.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AgentInfoFragment_ViewBinding implements Unbinder {
  private AgentInfoFragment target;

  @UiThread
  public AgentInfoFragment_ViewBinding(AgentInfoFragment target, View source) {
    this.target = target;

    target.buttonAgentInfo = Utils.findRequiredViewAsType(source, R.id.btn_get_agent_info, "field 'buttonAgentInfo'", Button.class);
    target.textViewPortNo = Utils.findRequiredViewAsType(source, R.id.et_port_no, "field 'textViewPortNo'", TextView.class);
    target.textViewHint = Utils.findRequiredViewAsType(source, R.id.hint, "field 'textViewHint'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AgentInfoFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.buttonAgentInfo = null;
    target.textViewPortNo = null;
    target.textViewHint = null;
  }
}
