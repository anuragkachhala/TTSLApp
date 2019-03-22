// Generated code from Butter Knife. Do not modify!
package com.software.ttsl.Adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.software.ttsl.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BillPendingAdapter$PendingBillViewHolder_ViewBinding implements Unbinder {
  private BillPendingAdapter.PendingBillViewHolder target;

  @UiThread
  public BillPendingAdapter$PendingBillViewHolder_ViewBinding(BillPendingAdapter.PendingBillViewHolder target,
      View source) {
    this.target = target;

    target.isChecked = Utils.findRequiredViewAsType(source, R.id.check, "field 'isChecked'", CheckBox.class);
    target.dueMonth = Utils.findRequiredViewAsType(source, R.id.tv_due_month, "field 'dueMonth'", TextView.class);
    target.dueDate = Utils.findRequiredViewAsType(source, R.id.tv_due_date, "field 'dueDate'", TextView.class);
    target.billNumber = Utils.findRequiredViewAsType(source, R.id.tv_bill_no, "field 'billNumber'", TextView.class);
    target.billAmount = Utils.findRequiredViewAsType(source, R.id.tv_bill_amount, "field 'billAmount'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BillPendingAdapter.PendingBillViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.isChecked = null;
    target.dueMonth = null;
    target.dueDate = null;
    target.billNumber = null;
    target.billAmount = null;
  }
}
