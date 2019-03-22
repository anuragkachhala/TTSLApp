// Generated code from Butter Knife. Do not modify!
package com.software.ttsl.NewModule;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.software.ttsl.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AgentInfo_ViewBinding implements Unbinder {
  private AgentInfo target;

  @UiThread
  public AgentInfo_ViewBinding(AgentInfo target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AgentInfo_ViewBinding(AgentInfo target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.scrollView = Utils.findRequiredViewAsType(source, R.id.scrollView, "field 'scrollView'", ScrollView.class);
    target.textViewMsg = Utils.findRequiredViewAsType(source, R.id.tv_message, "field 'textViewMsg'", TextView.class);
    target.textViewAgentInfo = Utils.findRequiredViewAsType(source, R.id.tv_agent_info, "field 'textViewAgentInfo'", TextView.class);
    target.textViewAgentDestCharge = Utils.findRequiredViewAsType(source, R.id.tv_agent_destination_charges, "field 'textViewAgentDestCharge'", TextView.class);
    target.textViewAgentDestCharge1 = Utils.findRequiredViewAsType(source, R.id.tv_agent_destination_charges1, "field 'textViewAgentDestCharge1'", TextView.class);
    target.textViewAgentNote = Utils.findRequiredViewAsType(source, R.id.tv_agent_note, "field 'textViewAgentNote'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AgentInfo target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.scrollView = null;
    target.textViewMsg = null;
    target.textViewAgentInfo = null;
    target.textViewAgentDestCharge = null;
    target.textViewAgentDestCharge1 = null;
    target.textViewAgentNote = null;
  }
}
