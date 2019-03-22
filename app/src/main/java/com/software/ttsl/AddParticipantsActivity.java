package com.software.ttsl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.software.ttsl.Adapter.ParticipantsListAdapter;
import com.software.ttsl.Interfacce.AlertDialogCallback;
import com.software.ttsl.Interface.ItemClickListener;
import com.software.ttsl.Request.Participant;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.AlertDialogManager;
import com.software.ttsl.Utils.EmployConstantUtil;
import com.software.ttsl.model.ParticipantsData;

import java.util.ArrayList;
import java.util.HashSet;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddParticipantsActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, ItemClickListener, AlertDialogCallback {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.iv_add_participants)
    ImageView ivAddParticipants;

    @BindView(R.id.tv_participants)
    TextView textViewParticipants;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private Intent intent;

    private String titleContactName, titleLeadName, msgDeleteParticipants, msgNoOfParticipants;
    private DataBaseAdapter dataBaseAdapter;
    private ArrayList<ParticipantsData> participantsList;
    private ArrayList<ParticipantsData> participantsLeads = new ArrayList<>();
    private Participant participant;
    private ParticipantsData participantsData;
    private ArrayList<Participant> participants = new ArrayList<>();
    private ParticipantsListAdapter participantsListAdapter;
    private long eventId;
    private int position;
    private boolean isList = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_participants);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intent = getIntent();
        eventId = intent.getLongExtra(EmployConstantUtil.KEY_EVENT_ID, -1);
        dataBaseAdapter = new DataBaseAdapter(this);

        participantsListAdapter = new ParticipantsListAdapter(this, participantsLeads, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(participantsListAdapter);
        recyclerView.setHasFixedSize(true);

        isList = intent.getBooleanExtra("isList", false);
        if (isList) {
            getParticipantList();
        }

        getStringResources();


        //setOnClickLister();
    }

    private void getParticipantList() {
        participants.clear();
        participants.addAll(dataBaseAdapter.getAllParticipants(eventId));
        setParticipantsToView();
    }

    private void setParticipantsToView() {
        participantsLeads.clear();
        for (Participant participant : participants) {
            participantsData = new ParticipantsData();
            participantsData.setParticipantsName(participant.getParticipantName());
            participantsData.setId(participant.getParticipant());
            participantsLeads.add(participantsData);
        }
        participantsListAdapter.notifyDataSetChanged();
        participants.clear();
        if(participantsLeads.size()!=0){
            textViewParticipants.setText(participantsLeads.size()+" "+getResources().getString(R.string.msg_no_of_participants));
        }else {
            textViewParticipants.setText(getResources().getString(R.string.msg_no_participants));
        }
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

                    setParticipants();

                break;
        }

        return super.onOptionsItemSelected(item);


    }


    private void setParticipants() {

        for (ParticipantsData participantsData : participantsLeads) {
            participant = new Participant();
            participant.setParticipant(participantsData.getId());
            participant.setParticipantName(participantsData.getParticipantsName());
            participant.setParticipantEmail(participantsData.getParticipantsEmail());
            participant.setConstant(participantsData.getConstant());
            participant.setEventId(eventId);
            participants.add(participant);

        }
        if(isList){

            dataBaseAdapter.updateParticipants(participants,eventId);
        }else {
            dataBaseAdapter.addParticipants(participants);
        }

        intent = new Intent();
        intent.putExtra(EmployConstantUtil.KEY_PARTICIPANT_LIST_SIZE, participants.size());
        setResult(RESULT_OK, intent);
        finish();
    }

    private void getStringResources() {
        titleContactName = getResources().getString(R.string.title_contact_list_activity);
        titleLeadName = getResources().getString(R.string.title_lead_list_activity);
        msgDeleteParticipants = getResources().getString(R.string.msg_delete_participants);
        msgNoOfParticipants = getResources().getString(R.string.msg_no_of_participants);
    }


    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        popup.setOnMenuItemClickListener(this);
        inflater.inflate(R.menu.menu_add_participants, popup.getMenu());
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.contact:
                startNewActivity(titleContactName, ParticipantsContactList.class);
                return true;
            case R.id.lead:
                startNewActivity(titleLeadName, ParticipantsLeadListActivity.class);
                return true;
            default:
                return false;
        }
    }


    private void startNewActivity(String title, Class<?> cls) {
        intent = new Intent(AddParticipantsActivity.this, cls);
        intent.putExtra(EmployConstantUtil.TITLE, titleContactName);
        intent.putExtra(EmployConstantUtil.KEY_EVENT_ID,eventId);
        startActivityForResult(intent, 1000);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == RESULT_OK) {

                participantsList = data.getParcelableArrayListExtra(EmployConstantUtil.SELECTED_PARTICIPANTS_LIST);
                participantsList.addAll(participantsLeads);
                participantsLeads.clear();
                participantsLeads.addAll(new HashSet<>(participantsList));
                participantsListAdapter.notifyDataSetChanged();
                if(participantsLeads.size()!=0){
                    textViewParticipants.setText(participantsLeads.size()+" "+getResources().getString(R.string.msg_no_of_participants));
                }else {
                    textViewParticipants.setText(getResources().getString(R.string.msg_no_participants));
                }

            }
        }
    }


    @Override
    public void onItemClick(int position) {
        this.position = position;
        AlertDialogManager.showAlertDialogMessage(this, "", msgDeleteParticipants, false, this);
    }

    @Override
    public void alertDialogCallbackOk() {
        participantsLeads.remove(position);
        participantsListAdapter.notifyDataSetChanged();

        if(participantsLeads.size()!=0){
            textViewParticipants.setText(participantsLeads.size()+" "+getResources().getString(R.string.msg_no_of_participants));
        }else {
            textViewParticipants.setText(getResources().getString(R.string.msg_no_participants));
        }
    }

    @Override
    public void alertDialogCallbackCancel() {

    }
}
