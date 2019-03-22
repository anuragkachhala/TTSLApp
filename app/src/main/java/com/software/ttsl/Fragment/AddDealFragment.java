package com.software.ttsl.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.software.ttsl.AccountDetailsActivity;
import com.software.ttsl.Adapter.ContactListFragmentAdapter;
import com.software.ttsl.Adapter.DealListFragmentAdapter;
import com.software.ttsl.DealDetailsActivity;
import com.software.ttsl.Interface.ItemClickListener;
import com.software.ttsl.R;
import com.software.ttsl.Request.DealDataModel;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.DealListSortingData;
import com.software.ttsl.model.AddContactData;
import com.software.ttsl.model.OrderByData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddDealFragment extends Fragment implements ItemClickListener {

    public static final String TAG = AddDealFragment.class.getName();

    public static final String KEY_DEAL_ID ="deal_id";

    public static final int ORDERBY=100;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    Long dealId;
    DataBaseAdapter dataBaseAdapter;

    DealListSortingData dealListSortingData;
    List<OrderByData> orderByDataList = new ArrayList<>();
    ArrayList<DealDataModel> dealDataModelList = new ArrayList<>();
    DealListFragmentAdapter dealListFragmentAdapter;


    public AddDealFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_deal, container, false);

        ButterKnife.bind(this,view);

        dataBaseAdapter = new DataBaseAdapter(getContext());


        updateUI();

        dealListFragmentAdapter = new DealListFragmentAdapter(getContext(),dealDataModelList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(dealListFragmentAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);





      return  view;
    }

    public void updateUI() {
        dealListSortingData = null;
        dealListSortingData  = dataBaseAdapter.getAllDeals("nothing","asc","nothing","desc");
        dealDataModelList.clear();
        orderByDataList.clear();

        if(dealListSortingData.getDealDataModelList().size()!=0) {
            orderByDataList.addAll(dealListSortingData.getOrderByDataList());
            dealDataModelList.addAll(dealListSortingData.getDealDataModelList());
            int total = 0;

            for (OrderByData orderByData : orderByDataList) {
                int count = orderByData.getCount();
                total = total + count;
                if (dealDataModelList.size() > total) dealDataModelList.get(total).setHeaderShow(true);

            }

            dealDataModelList.get(0).setHeaderShow(true);

        }
        int i =0;
        for (DealDataModel dealDataModel: dealDataModelList){
            Log.e(TAG, i + "   " + dealDataModel.isHeaderShow() + " ");
            i++;
        }






    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode ==RESULT_OK){
            switch (requestCode){
                case 1000:
                    //TODO get intent data
                    updateUI();
                    dealListFragmentAdapter.notifyDataSetChanged();
                    break;


                case ORDERBY:
                    break;




            }
        }
    }

    @Override
    public void onItemClick(int position) {
        dealId  = dealDataModelList.get(position).getDealId();
        Log.e(TAG,"deal id IN ADD deal FRAGMENT  "+dealId);
        OpenLeadDetailActivity();
    }

    private void OpenLeadDetailActivity() {
        Intent intent = new Intent(getContext(), DealDetailsActivity.class);
        intent.putExtra(KEY_DEAL_ID,dealId);
        startActivityForResult(intent,1000);


    }
}
