package com.software.ttsl.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.software.ttsl.R;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class  BaseUtilFragment extends Fragment {


    public BaseUtilFragment() {
        // Required empty public constructor
    }


    public BaseUtilFragment newInstance()
    {

        Bundle args = new Bundle();
        BaseUtilFragment fragment = provideYourFragment();
        fragment.setArguments(args);
        return fragment;

    }



    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater,ViewGroup parent, Bundle savedInstanseState)
    {
        View view = provideYourFragmentView(inflater,parent,savedInstanseState);
        return view;
    }

    public abstract BaseUtilFragment provideYourFragment();

    public abstract View provideYourFragmentView(LayoutInflater inflater,ViewGroup parent, Bundle savedInstanceState);

    public abstract  void startActivityForResult1(Intent intent, int requestCode);


}


