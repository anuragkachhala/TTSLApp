package com.software.ttsl.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.software.ttsl.Adapter.CustomerChallengeAdapter;
import com.software.ttsl.CustomerChallengesDetailActivity;
import com.software.ttsl.Interface.ItemClickListener;
import com.software.ttsl.R;
import com.software.ttsl.SalesDetailActivity;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.DateAndTimeUtil;
import com.software.ttsl.Utils.EmployConstantUtil;
import com.software.ttsl.model.CustomerChallengeModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerChallengeFragment extends Fragment implements ItemClickListener {


    public static final String TAG = CustomerChallengeFragment.class.getName();


    public static final int ORDER_BY = 100;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    long customerChallengeId;
    private DataBaseAdapter dataBaseAdapter;
    private CustomerChallengeAdapter customerChallengeAdapter;
    private CustomerChallengeModel customerChallengeModel;
    private List<CustomerChallengeModel> customerChallengeList = new ArrayList<>();
    private List<CustomerChallengeModel> list;
    private String createdTime;
    private String createdTimeChanged;


    public CustomerChallengeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_challenge_list, container, false);

        ButterKnife.bind(this, view);

        dataBaseAdapter = new DataBaseAdapter(getContext());
        customerChallengeAdapter = new CustomerChallengeAdapter(customerChallengeList, getContext(), this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(customerChallengeAdapter);
        recyclerView.setHasFixedSize(true);

        updateUI();
        return view;
    }

    private void updateUI() {

        customerChallengeList.clear();
        customerChallengeList.addAll(dataBaseAdapter.getAllCustomerChallenges());
        if (!customerChallengeList.isEmpty()) {
            createdTime = DateAndTimeUtil.longToDate(customerChallengeList.get(0).getCreatedTime());
            for (CustomerChallengeModel customerChallengeModel : customerChallengeList) {
                createdTimeChanged = DateAndTimeUtil.longToDate(customerChallengeModel.getCreatedTime());
                if (!createdTime.equals(createdTimeChanged)) {
                    customerChallengeModel.setHeadershow(true);
                    createdTime = createdTimeChanged;
                }

                Log.e(TAG, " "+customerChallengeModel.getHeadershow());
            }

            customerChallengeList.get(0).setHeadershow(true);




        }


        customerChallengeAdapter.notifyDataSetChanged();

    }





    @Override
    public void onItemClick(int position) {
        customerChallengeId = customerChallengeList.get(position).getCustomerId();
        Log.e(TAG, "customer challenge id IN add customer challenge FRAGMENT  " + customerChallengeId);
        CustomerChallengesDetailActivity();
    }

    private void CustomerChallengesDetailActivity() {
        Intent intent = new Intent(getContext(), CustomerChallengesDetailActivity.class);
        intent.putExtra(EmployConstantUtil.KEY_CUSTOMER_CHALLENGE_ID, customerChallengeId);
        startActivityForResult(intent,1000);


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1000:
                    //TODO get intent data
                    updateUI();
                    customerChallengeAdapter.notifyDataSetChanged();
                    break;


            }
        }
    }
}
