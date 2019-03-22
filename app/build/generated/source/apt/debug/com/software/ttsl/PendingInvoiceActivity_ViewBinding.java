// Generated code from Butter Knife. Do not modify!
package com.software.ttsl;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PendingInvoiceActivity_ViewBinding implements Unbinder {
  private PendingInvoiceActivity target;

  @UiThread
  public PendingInvoiceActivity_ViewBinding(PendingInvoiceActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PendingInvoiceActivity_ViewBinding(PendingInvoiceActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.my_toolbar, "field 'toolbar'", Toolbar.class);
    target.mSelectAll = Utils.findRequiredViewAsType(source, R.id.checkbox_select_all, "field 'mSelectAll'", CheckBox.class);
    target.mSortInvoiceSp = Utils.findRequiredViewAsType(source, R.id.spinner_sorting, "field 'mSortInvoiceSp'", Spinner.class);
    target.mTotalAmount = Utils.findRequiredViewAsType(source, R.id.tv_total_amount, "field 'mTotalAmount'", TextView.class);
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
    target.mBillPayBtn = Utils.findRequiredViewAsType(source, R.id.btn_bill_pay, "field 'mBillPayBtn'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PendingInvoiceActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.mSelectAll = null;
    target.mSortInvoiceSp = null;
    target.mTotalAmount = null;
    target.mRecyclerView = null;
    target.mBillPayBtn = null;
  }
}
