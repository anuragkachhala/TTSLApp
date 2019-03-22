package com.software.ttsl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.software.ttsl.Interface.ItemClickListener;
import com.software.ttsl.Sql.DataBaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParticipantSelectList extends AppCompatActivity implements ParticipantSelectListAdapter.ParticipantsAdapterListener {

    public static final String TAG = ParticipantSelectList.class.getName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private DataBaseAdapter dataBaseAdapter;
    private ParticipantSelectListAdapter participantSelectListAdapter;
    private List<String>  stringList = new ArrayList<String>();
    private String selectedItem;
    private long contactId;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participent_select_list);


        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        dataBaseAdapter = new DataBaseAdapter(this);

        participantSelectListAdapter = new ParticipantSelectListAdapter(this,stringList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(participantSelectListAdapter);
        recyclerView.setHasFixedSize(true);

        updateUI();


        setClickListener();


    }

    private void updateUI() {

        stringList.add("anurag");
        stringList.add("abhishek");
        stringList.add("arpan");
        stringList.add("vijay");
        participantSelectListAdapter.notifyDataSetChanged();

    }

    public void setClickListener(){}


    @Override
    public void onCheckBoxClicked(int position) {

    }

    @Override
    public void onItemClicked(int position) {

    }
}
