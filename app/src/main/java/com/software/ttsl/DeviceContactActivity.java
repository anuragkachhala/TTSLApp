package com.software.ttsl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.software.ttsl.Adapter.DeviceContactListAdapter;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.model.DeviceContactData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeviceContactActivity extends AppCompatActivity {

    public static final String TAG = DeviceContactActivity.class.getName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private DataBaseAdapter dataBaseAdapter;
    private DeviceContactListAdapter deviceContactListAdapter;
    private List<DeviceContactData> deviceContactDataList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divice_contact);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        deviceContactListAdapter = new DeviceContactListAdapter(this,deviceContactDataList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(deviceContactListAdapter);
        recyclerView.setHasFixedSize(true);


        updateUI();

    }

    private void updateUI() {

        deviceContactDataList.add(new DeviceContactData("anurag","nurag",false));
        deviceContactListAdapter.notifyDataSetChanged();
    }
}
