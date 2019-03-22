// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SalesDetailActivity_ViewBinding implements Unbinder {
  private SalesDetailActivity target;

  @UiThread
  public SalesDetailActivity_ViewBinding(SalesDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SalesDetailActivity_ViewBinding(SalesDetailActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.textViewBudgetYear = Utils.findRequiredViewAsType(source, R.id.tv_budget_year, "field 'textViewBudgetYear'", TextView.class);
    target.textViewBudgetSector = Utils.findRequiredViewAsType(source, R.id.tv_budget_sector, "field 'textViewBudgetSector'", TextView.class);
    target.textViewBudgetSalesman = Utils.findRequiredViewAsType(source, R.id.tv_budget_salesman, "field 'textViewBudgetSalesman'", TextView.class);
    target.textViewBudgetCurrency = Utils.findRequiredViewAsType(source, R.id.tv_budget_currency, "field 'textViewBudgetCurrency'", TextView.class);
    target.textViewBudgetRoe = Utils.findRequiredViewAsType(source, R.id.tv_budget_roe, "field 'textViewBudgetRoe'", TextView.class);
    target.textViewBudgetNote = Utils.findRequiredViewAsType(source, R.id.tv_budget_note, "field 'textViewBudgetNote'", TextView.class);
    target.textViewBudgetCreatedBy = Utils.findRequiredViewAsType(source, R.id.tv_budget_created_by, "field 'textViewBudgetCreatedBy'", TextView.class);
    target.textViewBudgetModifyBy = Utils.findRequiredViewAsType(source, R.id.tv_modify_by, "field 'textViewBudgetModifyBy'", TextView.class);
    target.textViewBudgetCreatedTime = Utils.findRequiredViewAsType(source, R.id.tv_budget_created_time, "field 'textViewBudgetCreatedTime'", TextView.class);
    target.textViewBudgetModifyTime = Utils.findRequiredViewAsType(source, R.id.tv_budget_modify_time, "field 'textViewBudgetModifyTime'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SalesDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.textViewBudgetYear = null;
    target.textViewBudgetSector = null;
    target.textViewBudgetSalesman = null;
    target.textViewBudgetCurrency = null;
    target.textViewBudgetRoe = null;
    target.textViewBudgetNote = null;
    target.textViewBudgetCreatedBy = null;
    target.textViewBudgetModifyBy = null;
    target.textViewBudgetCreatedTime = null;
    target.textViewBudgetModifyTime = null;
  }
}
