package com.software.ttsl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.software.ttsl.Adapter.GetQuotationAdapter;
import com.software.ttsl.Utils.DataSingleton;
import com.software.ttsl.model.GetQuotation;
import com.software.ttsl.model.GetQuotationFormData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GetQuotationActivity extends AppCompatActivity implements GetQuotationAdapter.GetQuotationAdapterListener, View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycler_view)
     RecyclerView recyclerView;

    @BindView(R.id.btn_next)
    Button mNextBtn;

    public String mShippingMode;
    private GetQuotation getQuotation;
    private List<GetQuotation> getQuotationList = new ArrayList<GetQuotation>();
    private GetQuotationAdapter mGetQuotationAdapter;
    private GetQuotationFormData getQuotationFormData;
    private DataSingleton mDataSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_quotation);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mNextBtn.setOnClickListener(this);
        mDataSingleton = DataSingleton.getInstance();
        prepareQuotationData();
        setRecyclerView();


    }

    private void setRecyclerView() {
        mGetQuotationAdapter = new GetQuotationAdapter(getQuotationList, this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(mGetQuotationAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);
    }

    private void prepareQuotationData() {
        String[] quotationTypeList = getResources().getStringArray(R.array.quotation_type);
        String[] quotationDiscriptionList = getResources().getStringArray(R.array.quotation_type_description);
        int i = 0;
        for (String quotationType : quotationTypeList) {
            getQuotation = new GetQuotation(quotationTypeList[i], quotationDiscriptionList[i]);
            getQuotationList.add(getQuotation);

            if (i == 0) {
                getQuotation.setChecked(true);
                getQuotation.setBackgroundColor(getResources().getColor(R.color.selected_item_background));
            }
            i++;
        }
    }

    @Override
    public void onItemClicked(int position) {
        GetQuotation getQuotation = getQuotationList.get(position);

        for (GetQuotation getQuotation1 : getQuotationList) {
            getQuotation1.setChecked(false);
            getQuotation1.setBackgroundColor(-1);
        }
        getQuotation.setChecked(true);
        getQuotation.setBackgroundColor(getResources().getColor(R.color.selected_item_background));
        mShippingMode = getQuotation.getShippingModeId();
        mGetQuotationAdapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View view) {
        getQuotationFormData = mDataSingleton.getQuotationFormData();
        getQuotationFormData.setShippingMode(mShippingMode);
        Intent getQuotationIntent = new Intent(GetQuotationActivity.this, QuotationFormActivity.class);
        startActivity(getQuotationIntent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
