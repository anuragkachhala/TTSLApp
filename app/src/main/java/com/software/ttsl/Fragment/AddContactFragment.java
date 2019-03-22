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

import com.software.ttsl.Adapter.ContactListFragmentAdapter;
import com.software.ttsl.ContactDetailActivity;
import com.software.ttsl.Interface.ItemClickListener;
import com.software.ttsl.LeadDetailActivity;
import com.software.ttsl.R;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.ContactListSortingData;
import com.software.ttsl.Utils.DateAndTimeUtil;
import com.software.ttsl.Utils.EmployConstantUtil;
import com.software.ttsl.model.AddContactData;
import com.software.ttsl.model.AddLeadData;
import com.software.ttsl.model.OrderByData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddContactFragment extends Fragment implements ItemClickListener {


    public static final String TAG = AddLeadFragment.class.getName();


    public static final int ORDERBY=100;


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    Long contactId;


    List<AddContactData> addContactDataList= new ArrayList<>();
    DataBaseAdapter dataBaseAdapter;
    ContactListFragmentAdapter contactListFragmentAdapter;
    private String createdTime,createdTimeChanged;



    public AddContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_contact, container, false);
        ButterKnife.bind(this,view);

        dataBaseAdapter = new DataBaseAdapter(getContext());


        contactListFragmentAdapter = new ContactListFragmentAdapter(getContext(),addContactDataList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(contactListFragmentAdapter);
        recyclerView.setHasFixedSize(true);

        updateUI();
        return  view;
    }

    private void updateUI() {

        addContactDataList.clear();
        addContactDataList.addAll(dataBaseAdapter.getAllContact());

        if(!addContactDataList.isEmpty()) {
            createdTime = DateAndTimeUtil.longToDate(addContactDataList.get(0).getCreatedDate());
            for (AddContactData addContactData: addContactDataList) {
                createdTimeChanged = DateAndTimeUtil.longToDate(addContactData.getCreatedDate());
                if(!createdTime.equals(createdTimeChanged)){
                    addContactData.setHeaderShow(true);
                    createdTime = createdTimeChanged;
                }

                Log.e(TAG, " "+addContactData.isHeaderShow());
            }

            addContactDataList.get(0).setHeaderShow(true);

        }

        contactListFragmentAdapter.notifyDataSetChanged();


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


                case ORDERBY:
                    String data1 =  data.getStringExtra("OrderBy");



            }
        }
    }



    @Override
    public void onItemClick(int position) {
        contactId = addContactDataList.get(position).getContactId();
        Log.e(TAG,"CONTACT ID IN ADD CONTACT FRAGMENT  "+contactId);
        OpenLeadDetailActivity(contactId);
    }

    private void OpenLeadDetailActivity(Long leadId) {
        Intent intent = new Intent(getContext(), ContactDetailActivity.class);
        intent.putExtra(EmployConstantUtil.KEY_CONTACT_ID,contactId);
        startActivityForResult(intent,1000);


    }

}
