package com.software.ttsl.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.software.ttsl.Adapter.EventListAdapter;
import com.software.ttsl.AddEventActivity;
import com.software.ttsl.EventDetailActivity;
import com.software.ttsl.Interface.ItemClickListener;
import com.software.ttsl.R;
import com.software.ttsl.Request.EventDataModel;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.EmployConstantUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddEventFragment extends Fragment implements ItemClickListener {

    private static final String TAG = AddEventActivity.class.getName();

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private long eventID;
    private DataBaseAdapter dataBaseAdapter;
    private EventListAdapter eventListAdapter;
    private ArrayList<EventDataModel> eventList = new ArrayList<>();



    public AddEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_event, container, false);

        ButterKnife.bind(this, view);

        dataBaseAdapter = new DataBaseAdapter(getContext());
        eventListAdapter = new EventListAdapter(getContext(), eventList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(eventListAdapter);
        recyclerView.setHasFixedSize(true);


        updateUI();

        return view;
    }

    private void updateUI() {
        eventList.clear();
        eventList.addAll(dataBaseAdapter.getAllEvents());
        eventListAdapter.notifyDataSetChanged();


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1000:
                    //TODO get intent data
                    updateUI();
                    break;


            }
        }
    }

    @Override
    public void onItemClick(int position) {

        eventID = eventList.get(position).getEventId();
        Log.e(TAG, "event ID IN ADD event FRAGMENT  " + eventID);
        openEventDetailActivity(eventID);

    }

    private void openEventDetailActivity(Long eventID) {
        Intent intent = new Intent(getContext(), EventDetailActivity.class);
        intent.putExtra(EmployConstantUtil.KEY_EVENT_ID, eventID);
        startActivityForResult(intent, 1000);
    }
}
