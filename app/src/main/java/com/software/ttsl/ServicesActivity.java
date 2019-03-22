package com.software.ttsl;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;

import com.software.ttsl.Adapter.ServicesAdapter;
import com.software.ttsl.model.ServicesData;

import java.util.ArrayList;
import java.util.List;

public class ServicesActivity extends AppCompatActivity {


    ServicesData servicesData;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private ServicesAdapter servicesAdapter;
    private List<ServicesData> servicesDataList;
    String servicesList[];
    TypedArray serviceImagearray;
    String servicesDesription[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        servicesList = getResources().getStringArray(R.array.service_type);
        servicesDesription=getResources().getStringArray(R.array.service_dis);
        serviceImagearray=getResources().obtainTypedArray(R.array.service_image);

        servicesDataList = new ArrayList<>();
        servicesAdapter = new ServicesAdapter(this, servicesDataList);


        setRecyclerView();

        prepareServicesData();


    }

    private void prepareServicesData() {
            int i =0;
            for (String service : servicesList) {
            servicesData = new ServicesData(service, servicesDesription[i],serviceImagearray.getResourceId(i,-1));
            i++;
            servicesDataList.add(servicesData);
        }

        servicesAdapter.notifyDataSetChanged();
    }

    private void setRecyclerView() {

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(servicesAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }
    }
}
