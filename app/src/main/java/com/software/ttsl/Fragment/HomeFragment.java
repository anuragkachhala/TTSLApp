package com.software.ttsl.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.software.ttsl.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.btn_select_data)
    Button buttonSelectDate;



    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this,view);
        setClickListener();
        return view;
    }

    private void setClickListener() {
        buttonSelectDate.setOnClickListener(this);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_select_data:
                openCalender();
                break;
        }
    }

    private void openCalender() {

    }
}
