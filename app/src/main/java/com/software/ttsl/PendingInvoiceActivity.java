package com.software.ttsl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.software.ttsl.Adapter.BillPendingAdapter;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.model.PendingInvoice;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PendingInvoiceActivity extends AppCompatActivity implements BillPendingAdapter.BillPendingAdapterListener, View.OnClickListener, AdapterView.OnItemSelectedListener {

    @BindView(R.id.my_toolbar)
    Toolbar toolbar;

    @BindView(R.id.checkbox_select_all)
    CheckBox mSelectAll;

    @BindView(R.id.spinner_sorting)
    Spinner mSortInvoiceSp;

    @BindView(R.id.tv_total_amount)
    TextView mTotalAmount;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.btn_bill_pay)
    Button mBillPayBtn;



    BillPendingAdapter billPendingAdapter;
    List<PendingInvoice> pendingInvoiceList = new ArrayList<>();
    private List<PendingInvoice> pendingInvoiceListDummy = new ArrayList<>();
    String invoiceNo,invoiceNoChange;

    String[] sortOrder;


    double amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_invoice);

        ButterKnife.bind(this);





        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        setRecyclerView();

        billPendingAdapter = new BillPendingAdapter(this, pendingInvoiceList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(billPendingAdapter);
        mRecyclerView.setHasFixedSize(true);

        mSelectAll.setOnClickListener(this);

        setSpinner();


        preparePendingInvoiceData();


    }

    private void setRecyclerView() {

    }

    private void setSpinner() {

        sortOrder = getResources().getStringArray(R.array.sort_bill);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_layout, sortOrder);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mSortInvoiceSp.setAdapter(adapter);
        mSortInvoiceSp.setOnItemSelectedListener(this);


    }

    private void preparePendingInvoiceData() {

        DataBaseAdapter dataBaseAdapter = new DataBaseAdapter(this);
        pendingInvoiceListDummy.clear();
        pendingInvoiceListDummy.addAll(dataBaseAdapter.getAllPendingInvoice());
        pendingInvoiceList.clear();
        if(pendingInvoiceListDummy.size()!=0) {
            invoiceNo = pendingInvoiceListDummy.get(0).getBillNumber();
            pendingInvoiceList.add(pendingInvoiceListDummy.get(0));
            for (PendingInvoice pendingInvoice : pendingInvoiceListDummy) {
                invoiceNoChange = pendingInvoice.getBillNumber();
                if (!invoiceNo.equals(invoiceNoChange)) {
                    pendingInvoiceList.add(pendingInvoice);
                    invoiceNo = invoiceNoChange;
                }
            }
            billPendingAdapter.notifyDataSetChanged();

        }

    }



    //for checkbox clicked
    @Override
    public void onCheckBoxClicked(int position) {

    }

    @Override
    public void onItemClicked(int position) {
        try {

            PendingInvoice pendingInvoice = pendingInvoiceList.get(position);
            String billAmount;
            if (!pendingInvoice.getChecked()) {
                pendingInvoice.setChecked(true);
                amount = pendingInvoice.getAmount();
                billAmount = mTotalAmount.getText().toString().trim();
                if (!billAmount.equals("")) {
                    amount = amount + Double.valueOf(billAmount);
                    mTotalAmount.setText(String.valueOf(amount));
                } else {
                    mTotalAmount.setText(String.valueOf(amount));
                }
            } else {
                pendingInvoice.setChecked(false);
                amount = pendingInvoice.getAmount();
                billAmount = mTotalAmount.getText().toString().trim();
                if (!billAmount.equals("")) {
                    amount = Double.valueOf(billAmount) - amount;
                }
                if (amount > 0) {
                    mTotalAmount.setText(String.valueOf(amount));

                } else {
                    mTotalAmount.setText("");
                }
            }


            billPendingAdapter.notifyDataSetChanged();

        } catch (NumberFormatException nfe) {
            mTotalAmount.setText("sorry");
        }


    }

    public void selectAllBill() {
        double amounts = 0;
        for (PendingInvoice pendingInvoice : pendingInvoiceList) {
            if (!pendingInvoice.getChecked()) {
                pendingInvoice.setChecked(true);
            }
            amounts = amounts + (pendingInvoice.getAmount());
        }

        mTotalAmount.setText(String.valueOf(amounts));
        billPendingAdapter.notifyDataSetChanged();
    }

    public void unSelectedAllBill() {
        for (PendingInvoice pendingInvoice : pendingInvoiceList) {
            if (pendingInvoice.getChecked()) {
                pendingInvoice.setChecked(false);
            }

        }

        mTotalAmount.setText("");
        billPendingAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_pending_invoice, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.checkbox_select_all) {
            if (mSelectAll.isChecked()) {
                selectAllBill();
            } else {
                unSelectedAllBill();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mSelectAll.setChecked(false);
      String[] selectedItem = adapterView.getItemAtPosition(i).toString().split("-");
      String orderBy = selectedItem[0];
      String orderType =selectedItem[1];
      getPendingInvoiceBySortingOrder(orderBy,orderType);


    }

    private void getPendingInvoiceBySortingOrder(String orderBy, String orderType) {

        DataBaseAdapter dataBaseAdapter = new DataBaseAdapter(this);
        pendingInvoiceListDummy.clear();
        pendingInvoiceListDummy.addAll(dataBaseAdapter.getAllPendingInvoicesBy(orderBy,orderType));
        pendingInvoiceList.clear();
        if(pendingInvoiceListDummy.size()!=0) {
            invoiceNo = pendingInvoiceListDummy.get(0).getBillNumber();
            pendingInvoiceList.add(pendingInvoiceListDummy.get(0));
            for (PendingInvoice pendingInvoice : pendingInvoiceListDummy) {
                invoiceNoChange = pendingInvoice.getBillNumber();
                if (!invoiceNo.equals(invoiceNoChange)) {
                    pendingInvoiceList.add(pendingInvoice);
                    invoiceNo = invoiceNoChange;
                }
            }
            billPendingAdapter.notifyDataSetChanged();

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
