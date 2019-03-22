
package com.software.ttsl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.software.ttsl.Adapter.BillListAdapter;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.model.BillListData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BillListActivity extends AppCompatActivity implements BillListAdapter.BillListItemListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    BillListAdapter billListAdapter;
    List<BillListData> billListDataList=new ArrayList<>();
    List<BillListData> billListDataListDummy= new ArrayList<>();
    public String billNumber,newBillNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_list);
        ButterKnife.bind(this);


        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        billListAdapter= new BillListAdapter(billListDataList,this,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(billListAdapter);
        recyclerView.setHasFixedSize(true);

        prepareBillOfLadingList();


    }

    private void prepareBillOfLadingList() {
        DataBaseAdapter dataBaseAdapter = new DataBaseAdapter(this);
        billListDataList.clear();
        billListDataListDummy.clear();
        billListDataListDummy.addAll(dataBaseAdapter.getAllBillOfLading());


        for (BillListData billListData: billListDataListDummy) {
            boolean isFound = false;
            // check if the event name exists in noRepeat
            for (BillListData billListData1 : billListDataList) {
                if (billListData1.getBookingNumber().equals(billListData.getBookingNumber()) || (billListData1.equals(billListData))) {
                    isFound = true;
                    break;
                }
            }
            if (!isFound) billListDataList.add(billListData);
        }

        billListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPrintClick(int position) {

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
