package com.software.ttsl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.software.ttsl.Adapter.VesselListAdapter;
import com.software.ttsl.Response.VesselListResponse;
import com.software.ttsl.Sql.DataBaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchVesselActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher, VesselListAdapter.VesselListAdapterListener {


    public static final String SELECTED_VESSEL ="vessel_name";
    public static final String SELECTED_VESSEL_ID ="vessel_id";
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.et_search_vessel)
    EditText editTextSearchVessel;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.iv_clear_search)
    ImageView imageViewClearSearch;

    private VesselListAdapter vesselListAdapter;
    VesselListResponse vessel ;
    String vesselName;
    long vesselId;

    List<VesselListResponse> vesselList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_vessel);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setClickListener();

        vesselListAdapter= new VesselListAdapter(this, vesselList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(vesselListAdapter);


    }

    private void setClickListener() {
        editTextSearchVessel.addTextChangedListener(this);
        imageViewClearSearch.setOnClickListener(this);
        editTextSearchVessel.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.iv_clear_search) {
            editTextSearchVessel.setText("");
        }

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

        if (!editTextSearchVessel.getText().toString().trim().isEmpty()) {
            imageViewClearSearch.setVisibility(View.VISIBLE);
        } else {
            imageViewClearSearch.setVisibility(View.GONE);
        }

        if (editable.toString().length() >= 3) {
            vesselList.clear();
            vesselList.addAll(dataBaseAdapter.retrieveVesseData(editable.toString()));
            vesselListAdapter.notifyDataSetChanged();
        } else {
            vesselList.clear();
            vesselListAdapter.notifyDataSetChanged();
        }



    }

    @Override
    public void onVesselSelected(int  position) {

        vessel =  vesselList.get(position);
        vesselId =vessel.getVesselId();
        vesselName=vessel.getVesselName();
        Intent intent = new Intent();
        intent.putExtra(SELECTED_VESSEL,vesselName);
        intent.putExtra(SELECTED_VESSEL_ID,vesselId);
        setResult(RESULT_OK,intent);
        finish();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
