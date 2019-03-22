package com.software.ttsl;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.software.ttsl.Adapter.SalesBudgetFragmentAdapter;
import com.software.ttsl.Fragment.AddSalesFragment;
import com.software.ttsl.Interface.ItemClickListener;
import com.software.ttsl.Request.CallDataModel;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.DateAndTimeUtil;
import com.software.ttsl.Utils.EmployConstantUtil;
import com.software.ttsl.model.SalesBudgetModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddCallFragment extends Fragment implements ItemClickListener {

    private final static String TAG = AddCallFragment.class.getName();


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    private long callID;

    private DataBaseAdapter dataBaseAdapter;
    private CallFragmentAdapter callFragmentAdapter;
    private ArrayList<CallDataModel> callDataModels= new ArrayList<>();
    private List<CallDataModel> callDataModelsList;
    private String callStartDate;
    private String callStartDateChanged;



    public AddCallFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_call, container, false);
        ButterKnife.bind(this,view);

        dataBaseAdapter = new DataBaseAdapter(getContext());
        callFragmentAdapter = new CallFragmentAdapter(getContext(),callDataModels,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(callFragmentAdapter);
        recyclerView.setHasFixedSize(true);

        updateUI();

        return view;
    }

    private void updateUI() {
        callDataModels.clear();
        callDataModelsList = dataBaseAdapter.getAllCall();
        callDataModels.addAll(callDataModelsList);
        if(!callDataModelsList.isEmpty()) {
            callStartDate = DateAndTimeUtil.longToDate(callDataModelsList.get(0).getCallStartDate(),EmployConstantUtil.DATE_FORMAT);
            for (CallDataModel callDataModel: callDataModels) {
                callStartDateChanged = DateAndTimeUtil.longToDate(callDataModel.getCallStartDate(),EmployConstantUtil.DATE_FORMAT);;
                if (!callStartDate.equals(callStartDateChanged)) {
                    callDataModel.setHeaderShow(true);
                    callStartDate = callStartDateChanged;
                }
            }

            callDataModelsList.get(0).setHeaderShow(true);

        }

        callFragmentAdapter.notifyDataSetChanged();


    }
    @Override
    public void onItemClick(int position) {
        callID = callDataModels.get(position).getCallId();
        Log.e(TAG,"call id in add call FRAGMENT  "+callID);
        OpenLeadDetailActivity();
    }

    private void OpenLeadDetailActivity() {
        Intent intent = new Intent(getContext(), CallDetailActivity.class);
        intent.putExtra(EmployConstantUtil.KEY_CALL_ID,callID);
        startActivityForResult(intent,1000);


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode ==RESULT_OK){
            switch (requestCode){
                case 1000:
                    //TODO get intent data
                    updateUI();
                    break;




            }
        }
    }
}
