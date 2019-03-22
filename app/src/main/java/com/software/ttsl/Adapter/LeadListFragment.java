package com.software.ttsl.Adapter;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.software.ttsl.AddTask;
import com.software.ttsl.AddTaskActivity;
import com.software.ttsl.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LeadListFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.fab)
    FloatingActionButton fab;


    public LeadListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_lead_list, container, false);
        ButterKnife.bind(this,view);
        setClickListener();
        return view;
    }

    private void setClickListener() {
    fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
    if(view.getId()==R.id.fab){
        startActivity(new Intent(getContext(),AddTask.class));
    }

    }




}
