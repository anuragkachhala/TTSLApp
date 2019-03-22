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

public class EditSalesBudgetActivity_ViewBinding implements Unbinder {
  private EditSalesBudgetActivity target;

  @UiThread
  public EditSalesBudgetActivity_ViewBinding(EditSalesBudgetActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EditSalesBudgetActivity_ViewBinding(EditSalesBudgetActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.editTextBudgetYear = Utils.findRequiredViewAsType(source, R.id.et_budget_year, "field 'editTextBudgetYear'", EditText.class);
    target.editTextBudgetSalesman_sector = Utils.findRequiredViewAsType(source, R.id.et_budget_salesman_sector, "field 'editTextBudgetSalesman_sector'", EditText.class);
    target.editTextBudgetSalesman = Utils.findRequiredViewAsType(source, R.id.et_budget_salesman, "field 'editTextBudgetSalesman'", EditText.class);
    target.editTextBudgetSector = Utils.findRequiredViewAsType(source, R.id.et_budget_sector, "field 'editTextBudgetSector'", EditText.class);
    target.editTextBudgetRoe = Utils.findRequiredViewAsType(source, R.id.et_budget_roe, "field 'editTextBudgetRoe'", EditText.class);
    target.editTextBudgetNote = Utils.findRequiredViewAsType(source, R.id.et_budget_note, "field 'editTextBudgetNote'", EditText.class);
    target.editTextBudgetCurrency = Utils.findRequiredViewAsType(source, R.id.et_budget_currency, "field 'editTextBudgetCurrency'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EditSalesBudgetActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.editTextBudgetYear = null;
    target.editTextBudgetSalesman_sector = null;
    target.editTextBudgetSalesman = null;
    target.editTextBudgetSector = null;
    target.editTextBudgetRoe = null;
    target.editTextBudgetNote = null;
    target.editTextBudgetCurrency = null;
  }
}
