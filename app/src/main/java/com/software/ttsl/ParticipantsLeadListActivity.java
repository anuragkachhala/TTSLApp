package com.software.ttsl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.software.ttsl.Adapter.ParticipantsLeadAdapter;
import com.software.ttsl.Interface.ParticipantsAdapterListener;
import com.software.ttsl.Request.Participant;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.EmployConstantUtil;
import com.software.ttsl.model.ParticipantsData;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParticipantsLeadListActivity extends AppCompatActivity implements ParticipantsAdapterListener {

    public static final String TAG = ParticipantSelectList.class.getName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private DataBaseAdapter dataBaseAdapter;
    private ArrayList<ParticipantsData> leadsList= new ArrayList<>();
    private ArrayList<ParticipantsData> selectedParticipants = new ArrayList<>();
    private ParticipantsLeadAdapter participantsLeadAdapter;
    private  ParticipantsData participantsData;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participants_lead_list);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        dataBaseAdapter = new DataBaseAdapter(this);

        participantsLeadAdapter = new ParticipantsLeadAdapter(this,leadsList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(participantsLeadAdapter);
        recyclerView.setHasFixedSize(true);



        setAllLeads();




    }

    private void setAllLeads() {
        leadsList.addAll(dataBaseAdapter.getLeads());
        participantsLeadAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCheckBoxClicked(int position) {

    }

    @Override
    public void onItemClicked(int position) {
        participantsData = leadsList.get(position);
        if(!participantsData.isChecked()){
            selectedParticipants.add(participantsData);
            leadsList.get(position).setChecked(true);
            leadsList.get(position).setConstant(EmployConstantUtil.PARTICIPANTS_CONSTANT_LEAD);
        }else {
            selectedParticipants.remove(participantsData);
            leadsList.get(position).setChecked(false);
        }

        participantsLeadAdapter.notifyItemChanged(position);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_data, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.add_data:
                intent = new Intent();
                intent.putParcelableArrayListExtra(EmployConstantUtil.SELECTED_PARTICIPANTS_LIST,selectedParticipants);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);


    }

}
