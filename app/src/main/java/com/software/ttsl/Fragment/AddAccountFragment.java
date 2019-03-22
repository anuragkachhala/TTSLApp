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
import com.software.ttsl.Adapter.AccountListFragmentAdapter;
import com.software.ttsl.ContactDetailActivity;
import com.software.ttsl.Interface.ItemClickListener;
import com.software.ttsl.R;
import com.software.ttsl.Request.AccountDataModel;
import com.software.ttsl.Response.ImageResponse;
import com.software.ttsl.RestApi.ApiClient;
import com.software.ttsl.RestApi.ApiInterface;
import com.software.ttsl.Sql.DataBaseAdapter;
import com.software.ttsl.Utils.EmployConstantUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddAccountFragment extends Fragment implements ItemClickListener {

    private final static String TAG = AddAccountFragment.class.getName();


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private long accountId;

    private DataBaseAdapter dataBaseAdapter;
    private AccountListFragmentAdapter accountListFragmentAdapter;
    private AccountDataModel accountDataModel;
    private List<AccountDataModel> accountDataModelList = new ArrayList<>();
    private List<AccountDataModel> list;
    private String firstLatter;
    private String firstLatterChanged;




    public AddAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_add_account, container, false);
        ButterKnife.bind(this,view);

        dataBaseAdapter = new DataBaseAdapter(getContext());
        accountListFragmentAdapter= new AccountListFragmentAdapter(getContext(),accountDataModelList,this);
        updateUI();


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(accountListFragmentAdapter);
        recyclerView.setHasFixedSize(true);
        //getAllAccount();
        return view;
    }

    private void getAllAccount() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<AccountDataModel>> image= apiInterface.getAllAccount(-1);
        Log.e(TAG, "Inside get Lead Image");
        image.enqueue(new Callback<List<AccountDataModel>>() {
            @Override
            public void onResponse(Call<List<AccountDataModel>> call, Response<List<AccountDataModel>> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    if (response.body() instanceof List) {
                      List<AccountDataModel> list = response.body();
                      dataBaseAdapter.setAllAccount(list);

                    }
                    }
            }

            @Override
            public void onFailure(Call<List<AccountDataModel>> call, Throwable t) {

            }
        });

    }

    private void updateUI() {
        accountDataModelList.clear();
        list = dataBaseAdapter.getAllAccounts();
        accountDataModelList.addAll(list);
        if(!list.isEmpty()) {
            firstLatter = accountDataModelList.get(0).getAccountName().substring(0, 1).toUpperCase();
            for (AccountDataModel accountDataModel : accountDataModelList) {
                firstLatterChanged = accountDataModel.getAccountName().substring(0, 1).toUpperCase();
                if (!firstLatter.equals(firstLatterChanged)) {
                    accountDataModel.setHeaderShow(true);
                }
            }

            accountDataModelList.get(0).setHeaderShow(true);

        }

        accountListFragmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
         accountId  = accountDataModelList.get(position).getId();
        Log.e(TAG,"Account id IN ADD account FRAGMENT  "+accountId);
        OpenLeadDetailActivity();
    }

    private void OpenLeadDetailActivity() {
        Intent intent = new Intent(getContext(), AccountDetailsActivity.class);
        intent.putExtra(EmployConstantUtil.KEY_ACCOUNT_ID,accountId);
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
