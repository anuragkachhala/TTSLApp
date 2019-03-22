package com.software.ttsl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.software.ttsl.Adapter.PortDataAdapter;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.model.PortData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectPortActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener, PortDataAdapter.PortAdapterListener{

    public static final String SELECTED_PORT ="selected_port";
    public static final String SELECTED_PORT_ID ="selected_port_id";
    public static final String SELECTED_PORT_COUNTRY="selected_port_country";
    public static final String SELECTED_PORT_STATE="selected_port_state";

    public static final int REQUEST_LOADING_PORT = 1000;

    public static final int REQUEST_DISCHARGE_PORT =2000;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.et_search_port)
    EditText mSearchPort;

    @BindView(R.id.iv_clear_search)
    ImageView mClearSearchIcon;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    PortDataAdapter mPortDataAdapter;

    PortData portData;

    String selectedPortCode;



    List<PortData> portDataList = new ArrayList<>();

    int selectedPortId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_port);
        ButterKnife.bind(this);


        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setClickListener();

        mPortDataAdapter = new PortDataAdapter(this, portDataList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mPortDataAdapter);

    }


    private void setClickListener() {
        mSearchPort.addTextChangedListener(this);
        mSearchPort.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        mClearSearchIcon.setOnClickListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


    }

    @Override
    public void afterTextChanged(Editable editable) {

        DataBaseAdapter dataBaseAdapter = new DataBaseAdapter(this);
        dataBaseAdapter.openDataBase();

        if (!mSearchPort.getText().toString().trim().isEmpty()) {
            mClearSearchIcon.setVisibility(View.VISIBLE);
        } else {
            mClearSearchIcon.setVisibility(View.GONE);
        }

        if (editable.toString().length() >= 3) {
            portDataList.clear();
            portDataList.addAll(dataBaseAdapter.retrievePortData(editable.toString()));
            mPortDataAdapter.notifyDataSetChanged();
        } else {
            portDataList.clear();
            mPortDataAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_clear_search) {
            mSearchPort.setText("");
        }
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onPortSelected(int position) {
        portData = portDataList.get(position);
        selectedPortCode =portData.getPortCode();
        selectedPortId = portData.getId();
        Intent intent = new Intent();
        intent.putExtra(SELECTED_PORT,selectedPortCode);
        intent.putExtra(SELECTED_PORT_ID,selectedPortId);
        intent.putExtra(SELECTED_PORT_COUNTRY,portData.getCountry());
        intent.putExtra(SELECTED_PORT_STATE,portData.getState());

        setResult(RESULT_OK,intent);
        finish();



    }
}
