package com.software.ttsl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeadSettingActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int ORDERBY=100;



    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.layout_group_by)
    LinearLayout linearLayoutGroupBy;

    @BindView(R.id.tv_group_by)
    TextView textViewGroupBy;

    @BindView(R.id.layout_primary_sort)
    LinearLayout linearLayoutPrimarySort;

    @BindView(R.id.tv_primary_sort)
    TextView textViewPrimarySort;

    @BindView(R.id.layout_secondary_sort)
    LinearLayout linearLayoutSecondarySort;

    @BindView(R.id.tv_secondary_sort)
    TextView textViewSecondarySort;

    @BindView(R.id.layout_search_by)
    LinearLayout linearLayoutSearchBy;


    @BindView(R.id.tv_search_by)
    TextView textViewSearchBy;

    @BindView(R.id.layout_display_field)
    LinearLayout linearLayoutDisplayField;

    @BindView(R.id.tv_dispaly_field)
    TextView textViewDisplayField;

    String groupBy;
    String groupBySortType;
    String OrderBy;
    String OrderBySortTyp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead_setting);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setClickListener();

    }

    private void setClickListener() {
        linearLayoutGroupBy.setOnClickListener(this);
        linearLayoutPrimarySort.setOnClickListener(this);
        linearLayoutSecondarySort.setOnClickListener(this);
        linearLayoutSearchBy.setOnClickListener(this);
        linearLayoutDisplayField.setOnClickListener(this);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:

                onBackPressed();
                break;

        }

        return super.onOptionsItemSelected(item);



    }





    @Override
    public void onClick(View view) {



    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("OrderBy","FullName");
        setResult(RESULT_OK,intent);
        finish();

        super.onBackPressed();
    }
}
